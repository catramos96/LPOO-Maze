package test;

import static org.junit.Assert.*;
import org.junit.Test;

import maze.logi.Board;
import maze.logi.Point;

public class TestRandomDragon {

	char[][] m1 = { { 'X', 'X', 'X', 'X', 'X' }, 
			{ 'X', ' ', ' ', 'H', 'S' }, { 'X', ' ', 'X', ' ', 'X' },
			{ 'X', 'E', ' ', 'D', 'X' }, { 'X', 'X', 'X', 'X', 'X' } };

	/*
	 * moveDragon(int d) d - 0 down d - 1 up d - 2 left d - 3 right d - 4 sleep
	 */

	@Test(timeout = 1000)
	public void testSomeRandomBehavior() {
		Board b = new Board(m1);

		assertTrue(b.getDragon().isAlive());
		assertEquals('D', b.getDragon().getSymbol());
		assertFalse(b.getDragon().getSleepMode());
		assertFalse(b.getDragon().getParalysedMode());

		boolean movement = false, sleep = false;
		while (movement == false && sleep == false) {
			b.moveRandomDragon();
			if (b.getDragon().getPosition() != new Point(3, 3)) {
				movement = true;
			} else if (b.getDragon().getSleepMode()) {
				sleep = true;
			} else
				fail("ERRO!\n");
		}
		if (movement == true)
			// Point (3,3) is the old position
			assertFalse(b.getDragon().getPosition() == new Point(3, 3));
		if (sleep == true) {
			assertEquals('d', b.getDragon().getSymbol());
			assertTrue(b.getDragon().getPosition() == new Point(3, 3));
		}
	}

	@Test
	public void testDragonCollisionSword() {
		Board b = new Board(m1);
		assertTrue(b.moveDragon(2)); // left
		b.updateBoard();
		assertEquals('D', b.getDragon().getSymbol());
		assertEquals(new Point(2, 3), b.getDragon().getPosition());
		b.moveDragon(2);
		b.updateBoard();
		assertEquals(new Point(1, 3), b.getDragon().getPosition());
		assertEquals('F', b.getDragon().getSymbol());
		assertFalse(b.getSword().inUse() == true);
	}

	@Test
	public void testDragonMoveKillsHero() {
		Board b = new Board(m1);
		assertTrue(b.getHero().isAlive());
		b.moveDragon(1);
		b.updateBoard();
		b.heroDragonCollision();
		assertFalse(b.getHero().isAlive());
		assertTrue(b.getDragon().isAlive() == true);
		assertTrue(b.exitBoard());
		assertFalse(b.heroWins());
	}

	@Test
	public void testDragonMovesAgainstWall() {
		Board b = new Board(m1);
		assertFalse(b.moveDragon(0));
	}

	@Test
	public void testCellFreeAfterMove() {
		Board b = new Board(m1);
		b.moveDragon(2);
		assertEquals(' ', b.getBoardSymbol(new Point(3, 3)));
		b.moveHero('a');
		assertEquals(' ', b.getBoardSymbol(new Point(3, 1)));
	}
	@Test
	public void testDragonGotoSleep()
	{
		Board b = new Board(m1);
		b.getDragon().setSleepMode(true);
		b.moveDragon(4);
		assertEquals('d',b.getDragon().getSymbol());
	}
	@Test
	public void testDragonFailsToMoveWhileSleeping(){
		Board b = new Board(m1);
		b.getDragon().setSleepMode(true);
		assertFalse(b.moveDragon(2));
		b.moveDragon(4);
	}
	
	@Test
	public void testDragonMoveAfterAwakeFromSleep(){
		Board b = new Board(m1);
		b.setDragonBehaviour('S');
		assertTrue(b.getDragon().getSleepMode());
		assertEquals('d',b.getDragon().getSymbol());
		b.getDragon().setSleepMode(false);
		assertFalse(b.getDragon().getSleepMode());
		assertEquals('D',b.getDragon().getSymbol());
		assertTrue(b.moveDragon(1)); //up
		
	}
	
	@Test
	public void testDragonSleepingHeroIsAlive(){
		Board b = new Board(m1);
		b.getDragon().setSleepMode(true);
		assertTrue(b.getDragon().getSymbol() == 'd');
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
		Board b = new Board(m1);
		assertFalse(b.getDragon().getSleepMode());
		assertFalse(b.getDragon().getParalysedMode());
		b.setDragonBehaviour('P');
		assertTrue(b.getDragon().getParalysedMode());
		assertFalse(b.getDragon().getSleepMode());
		b.setDragonBehaviour('S');
		assertTrue(b.getDragon().getSleepMode());
		assertFalse(b.getDragon().getParalysedMode());
	
	}

	@Test(timeout=1000)
	public void testRandomDragonAndHeroMovement(){
		Board b = new Board(m1);
		
		assertFalse(b.getDragon().getSleepMode());
		assertFalse(b.getDragon().getParalysedMode());
		assertEquals(new Point(3,3),b.getDragon().getPosition());
		
		b.moveHero('a');  //hero mover with dragon
		b.moveRandomDragon();
		assertEquals(new Point(2,1),b.getHero().getPosition());
		if(b.getDragon().getSleepMode())
			assertEquals('d',b.getDragon().getSymbol());
		else {
			assertFalse(b.getDragon().getPosition() == new Point(3,3));
		}
	}
}