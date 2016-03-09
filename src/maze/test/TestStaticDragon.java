package maze.test;

import static org.junit.Assert.*;
import org.junit.Test;

import maze.logi.Board;
import maze.logi.Dragon;
import maze.logi.Point;
import maze.logi.Sword;

public class TestStaticDragon {

	char[][] m1 = { 
			{ 'X', 'X', 'X', 'X', 'X' }, 
			{ 'X', ' ', ' ', 'H', 'S' }, 
			{ 'X', ' ', 'X', ' ', 'X' },
			{ 'X', 'E', ' ', 'D', 'X' }, 
			{ 'X', 'X', 'X', 'X', 'X' } };

	// x - the columns
	// y - the lines
	// Point(x,y);

	public void testParalyzedMode() {
		Board b = new Board(m1);

		assertFalse(b.getDragon().getParalysedMode());
		b.getDragon().setParalysedMode(true);
		assertTrue(b.getDragon().getParalysedMode());

		int n = 0;
		while (n != 20 || b.getDragon().getPosition() == new Point(3, 3)) {
			n++;
			b.moveDragon(0); // up
		}
		assertFalse(b.getDragon().getPosition() == new Point(3, 3));
		assertFalse(b.getDragon().getSleepMode());
	}

	@Test
	public void testMoveHeroToFreeCell() {
		Board b = new Board(m1);
		assertEquals(new Point(3, 1), b.getHero().getPosition());
		assertEquals(' ', b.getBoardSymbol(new Point(2, 1)));
		b.moveHero('a'); // move to left
		assertEquals(new Point(2, 1), b.getHero().getPosition());
	}

	@Test
	public void testHeroDies() {
		//
		Board b = new Board(m1);
		assertEquals(b.getHero().getSymbol(), b.getBoardSymbol(b.getHero().getPosition()));
		b.moveHero('s'); // move down
		assertEquals(false, b.getHero().isAlive());
		assertEquals(true, b.exitBoard()); // end of the game
		assertEquals(false, b.heroWins());
	}

	@Test
	public void testMoveHeroToWall() {
		Board b = new Board(m1);
		assertEquals('X', b.getBoardSymbol(new Point(3, 0)));
		b.moveHero('w');// up
		// same position before moveHero was called
		assertEquals(new Point(3, 1), b.getHero().getPosition());
	}

	@Test
	public void testHeroDisarmedKilled() {
		Board b = new Board(m1);
		assertNotEquals('A', b.getHero().getSymbol());
		assertEquals(false, b.getSword().inUse());
		b.moveHero('s'); // down to 1 position from the dragon
		assertEquals(false, b.getHero().isAlive());
		assertEquals(true, b.exitBoard()); // end of the game
	}

	@Test
	public void testHeroEquipedKillDragon() {
		Board b = new Board(m1);
		b.moveHero('a'); // left
		b.moveHero('a'); // left
		b.moveHero('s'); // down
		assertNotEquals('A', b.getHero().getSymbol());
		b.moveHero('s'); // down
		assertEquals('A', b.getHero().getSymbol());
		assertEquals(true, b.getDragon().isAlive());
		assertTrue(b.getSword().inUse());
		b.moveHero('d'); // right
		assertEquals(false, b.getDragon().isAlive());
		assertEquals(true, b.getHero().isAlive());
	}

	@Test
	public void testHeroWins() {
		Board b = new Board(m1);
		b.moveHero('a'); // left
		b.moveHero('a'); // left
		b.moveHero('s'); // down
		b.moveHero('s'); // down - hero is equiped with the sword
		b.moveHero('d'); // right - kills the dragon
		b.moveHero('d'); // right
		b.moveHero('w'); // up
		b.moveHero('w'); // up
		b.moveHero('d'); // right
		assertEquals(b.getExit(), b.getHero().getPosition());
		assertTrue(b.exitBoard());
		assertFalse(b.getDragon().isAlive());
		assertTrue(b.heroWins());
	}

	@Test
	public void testHeroMovesToExitAndSwordNotTaken() {
		Board b = new Board(m1);
		b.moveHero('d'); // right
		assertEquals(b.getExit(), b.getHero().getPosition());
		assertEquals(false, b.getSword().inUse());
		assertEquals(false, b.exitBoard());
	}

	@Test
	public void testHeroMovesToExitAndDragonNotKilled() {
		Board b = new Board(m1);
		b.moveHero('a'); // left
		b.moveHero('a'); // left
		b.moveHero('s'); // down
		b.moveHero('s'); // down - hero is equiped with the sword
		b.moveHero('w'); // up
		b.moveHero('w'); // up
		b.moveHero('d'); // right
		b.moveHero('d'); // right
		b.moveHero('d'); // right - exit
		assertEquals(b.getExit(), b.getHero().getPosition());
		assertEquals(true, b.getSword().inUse());
		assertEquals(true, b.getDragon().isAlive());
		assertEquals(false, b.exitBoard());
	}
 
	
}
