package test;

import static org.junit.Assert.*;
import org.junit.Test;

import maze.logi.Board;
import maze.logi.Point;

public class TestRandomDragon {

	char[][] m1 = { { 'X', 'X', 'X', 'X', 'X' }, { 'X', ' ', ' ', 'H', 'S' }, { 'X', ' ', 'X', ' ', 'X' },
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

		boolean movement = false, sleep = false;
		while (true) {
			b.moveRandomDragon();
			if (b.getDragon().getPosition() != new Point(3, 3)) {
				movement = true;
				break;
			} else if (b.getDragon().getSleepMode()) {
				sleep = true;
				break;
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

	public void testDragonMovesAgainstWall() {
		Board b = new Board(m1);
		assertFalse(b.moveDragon(1));
	}

}