package test;

import static org.junit.Assert.*;
import org.junit.Test;

import maze.logi.Board;
import maze.logi.Point;

public class TestStaticDragon {

	char[][] m1 = { { 'X', 'X', 'X', 'X', 'X' }, { 'X', ' ', ' ', 'H', 'S' }, { 'X', ' ', 'X', ' ', 'X' },
			{ 'X', 'E', ' ', 'D', 'X' }, { 'X', 'X', 'X', 'X', 'X' } };
	/*
	@Test
	public void testMoveHeroToFreeCell() {
	Board maze = new Board(m1);
	assertEquals(new Point(1, 3), maze.getHero().getPosition());
	maze.moveHeroLeft();
	assertEquals(new Point(1, 2), maze.getHero().getPosition());
	}
	
	
	@Test
	public void testHeroDies() {
	Board maze = new Board(m1);
	assertEquals(maze.getHero().getSymbol(), maze.getBoardSymbol(maze.getHero().getPosition()));
	maze.moveHeroDown();
	assertEquals(maze.getHero().isAlive(),false);
	}
	*/
}
