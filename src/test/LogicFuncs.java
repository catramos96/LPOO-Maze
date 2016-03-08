package test;

import static org.junit.Assert.*;

import org.junit.Test;

import maze.logi.Board;
import maze.logi.Dragon;
import maze.logi.Hero;
import maze.logi.Point;
import maze.logi.Sword;

public class LogicFuncs {

	@Test
	public void testDragonConstrutor()
	{
		Dragon test = new Dragon(new Point(2,3));
		assertEquals(2,test.getX());
		assertEquals(3,test.getY());
	}
	
	@Test
	public void testSwordFunc()
	{ 
		Sword test = new Sword (2,3);
		assertEquals(2,test.getX());
		assertEquals(3,test.getY());
		
		test.setPosition(new Point(3,4));
		assertEquals(3,test.getX());
		assertEquals(4,test.getY());
		
		test = new Sword();
		assertEquals(1,test.getX());
		assertEquals(1,test.getY());
	}
	
	@Test
	public void testPointCoordinatesSet()
	{
		Point test = new Point(1,1);
		test.setX(4);
		assertEquals(4, test.getX());
		test.setY(5);
		assertEquals(5,test.getY());
	}
	
	@Test
	public void testHeroConstrutor()
	{
		Hero test = new Hero(new Point(4,5));
		assertEquals(4, test.getX());
		assertEquals(5,test.getY());
	}
	
	@Test
	public void testBoardConstrutor()
	{
		Board test = new Board();
		char b[][] = { {}  };
		assertEquals(b.length,test.getBoard().length);
	}
	
	
}
