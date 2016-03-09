package maze.test;

import static org.junit.Assert.*;
import org.junit.Test;

import maze.logi.Board;
import maze.logi.Point;

public class TestRandomDragon {

	char[][] m1 = { { 'X', 'X', 'X', 'X', 'X' }, 
				    { 'X', ' ', ' ', 'H', 'S' }, 
		            { 'X', ' ', 'X', ' ', 'X' },
			  { 'X', 'E', ' ', 'D', 'X' },
			{ 'X', 'X', 'X', 'X', 'X' } };

	/*
	 * moveDragon(int d) d - 0 down d - 1 up d - 2 left d - 3 right d - 4 sleep
	 */

	@Test(timeout = 1000)
	public void testSomeRandomBehavior() {
		Board b = new Board(m1);

		assertTrue(b.getDragons().get(0).isAlive());
		assertEquals('D', b.getDragons().get(0).getSymbol());
		assertFalse(b.getDragons().get(0).getSleepMode());
		assertFalse(b.getDragons().get(0).getParalysedMode());

		boolean movement = false, sleep = false;
		while (movement == false && sleep == false) {
			b.moveRandomDragons();
			if (b.getDragons().get(0).getPosition() != new Point(3, 3)) {
				movement = true;
			} else if (b.getDragons().get(0).getSleepMode()) {
				sleep = true;
			} else
				fail("ERRO!\n");
		}
		if (movement == true)
			// Point (3,3) is the old position
			assertFalse(b.getDragons().get(0).getPosition() == new Point(3, 3));
		if (sleep == true) {
			assertEquals('d', b.getDragons().get(0).getSymbol());
			assertTrue(b.getDragons().get(0).getPosition() == new Point(3, 3));
		}
	}

	@Test
	public void testDragonCollisionSword() {
		Board b = new Board(m1);
		assertTrue(b.moveDragon(2,b.getDragons().get(0))); // left
		b.updateBoard();
		assertEquals('D', b.getDragons().get(0).getSymbol());
		assertEquals(new Point(2, 3), b.getDragons().get(0).getPosition());
		b.moveDragon(2,b.getDragons().get(0));
		b.updateBoard();
		assertEquals(new Point(1, 3), b.getDragons().get(0).getPosition());
		assertEquals('F', b.getDragons().get(0).getSymbol());
		assertFalse(b.getSword().inUse() == true);
	}

	@Test
	public void testDragonMoveKillsHero() {
		Board b = new Board(m1);
		assertTrue(b.getHero().isAlive());
		b.moveDragon(1,b.getDragons().get(0));
		b.updateBoard();
		b.heroDragonsCollision();
		assertFalse(b.getHero().isAlive());
		assertTrue(b.getDragons().get(0).isAlive() == true);
		assertTrue(b.exitBoard());
		assertFalse(b.heroWins());
	}

	@Test
	public void testDragonMovesAgainstWall() {
		Board b = new Board(m1);
		assertFalse(b.moveDragon(0,b.getDragons().get(0)));
	}

	@Test
	public void testCellFreeAfterMove() {
		Board b = new Board(m1);
		b.moveDragon(2,b.getDragons().get(0));
		assertEquals(' ', b.getBoardSymbol(new Point(3, 3)));
		b.moveHero('a');
		assertEquals(' ', b.getBoardSymbol(new Point(3, 1)));
	}
	@Test
	public void testDragonGotoSleep()
	{
		Board b = new Board(m1);
		b.getDragons().get(0).setSleepMode(true);
		b.moveDragon(4,b.getDragons().get(0));
		assertEquals('d',b.getDragons().get(0).getSymbol());
	}
	@Test
	public void testDragonMoveAfterAwakeFromSleep(){
		Board b = new Board(m1);
		b.setDragonsBehaviour('S');
		assertTrue(b.getDragons().get(0).getSleepMode());
		b.getDragons().get(0).setAwake(false);
		assertEquals('d',b.getDragons().get(0).getSymbol());
		b.getDragons().get(0).setAwake(true);
		assertEquals('D',b.getDragons().get(0).getSymbol());
		assertTrue(b.moveDragon(1,b.getDragons().get(0))); //up
	}

	@Test
	public void testDragonSleepingHeroIsAlive(){
		Board b = new Board(m1);
		b.getDragons().get(0).setAwake(false);
		assertTrue(b.getDragons().get(0).getSymbol() == 'd');
		b.moveHero('s');
		b.updateBoard();
		assertTrue(b.getHero().isAlive());
		b.moveHero('s');//nao consegue se mover porque colide com o dragao adormecido
		b.updateBoard();
		assertTrue(b.getHero().isAlive());
		assertEquals(new Point(3,2),b.getHero().getPosition());
	}

	@Test
	public void testChangeDragonMode(){
		System.out.println("FIm teste");
		Board b = new Board(m1);
		assertFalse(b.getDragons().get(0).getSleepMode());
		assertFalse(b.getDragons().get(0).getParalysedMode());
		b.setDragonsBehaviour('P');
		assertTrue(b.getDragons().get(0).getParalysedMode());
		assertFalse(b.getDragons().get(0).getSleepMode());
		b.setDragonsBehaviour('S');
		assertTrue(b.getDragons().get(0).getSleepMode());
		assertFalse(b.getDragons().get(0).getParalysedMode());

	}

	@Test(timeout=1000)
	public void testRandomDragonAndHeroMovement(){
		Board b = new Board(m1);

		assertFalse(b.getDragons().get(0).getSleepMode());
		assertFalse(b.getDragons().get(0).getParalysedMode());
		assertEquals(new Point(3,3),b.getDragons().get(0).getPosition());

		b.moveHero('a');  //hero mover with dragon
	 	b.moveRandomDragons();
		assertEquals(new Point(2,1),b.getHero().getPosition());
		
		if(b.getDragons().get(0).getSleepMode())
			assertEquals('d',b.getDragons().get(0).getSymbol());
		else {
			assertFalse(b.getDragons().get(0).getPosition() == new Point(3,3));
		}
		
		
	}
}