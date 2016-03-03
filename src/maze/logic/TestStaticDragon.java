package maze.logic;


import static org.junit.Assert.*;
import org.junit.Test;

import maze.cli.Point;

public class TestStaticDragon {

	char[][] m1 = { { 'X', 'X', 'X', 'X', 'X' }, { 'X', ' ', ' ', 'H', 'S' }, { 'X', ' ', 'X', ' ', 'X' },
			{ 'X', 'E', ' ', 'D', 'X' }, { 'X', 'X', 'X', 'X', 'X' } };

	@Test
	public void testMoveHeroToFreeCell() {
		Game game = new Game(m1);
		assertEquals(new Point(1, 3), game.getBoard().getHeroPosition());
		game.moveHeroLeft();
		assertEquals(new Point(1, 2), game.getBoard().getHeroPosition());
	}

	@Test
	public void testHeroDies() {
		Game g = new Game(m1);
		assertEquals(g.getBoard().getHero().getSymbol(), g.getBoard().getBoardSymbol(g.getBoard().getHero().getX(),g.getBoard().getHero().getY()));
		g.moveHeroDown();
		assertEquals(false, g.heroAlive());
	}

}
