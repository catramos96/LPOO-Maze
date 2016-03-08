package maze.logi;
import java.util.LinkedList;

import java.util.Random;

public class MazeGenerator {


	private  static char board [][];
	private  static char visited [][];
	private  static Point start;
	private  static Random rnd = new Random();
	private  static Point heroi;
	private static Point  dragon;

	public static void main(String[] args) {
		int size = 10;
		generateBoard(size);
		carvePath();
		placeHero(size);
		placeDragon(size,3);
		placeSword(size);

		System.out.println("GENERATED BOARD");
		for(int i = 0; i < size ; i ++)
		{
			for(int t = 0; t < board[i].length; t++)
				System.out.print(board[i][t]+ " ");

			System.out.println("");
		}
	}
	private static void generateBoard(int Nsize)//NEEED REFACT
	{
		board = new char[Nsize][Nsize];
		visited = new char[Nsize][Nsize];
		fillBoard(Nsize);
	}

	private static void fillBoard(int Nsize) {
		for (int i = 0; i < Nsize; i++) {
			for (int t = 0; t < Nsize; t++) {
				board[i][t] = 'X';
				// DONT CARVE WALL ON BOUNDS
				if (i == 0 || i + 1 == Nsize || t == 0 || t + 1 == Nsize)
					visited[i][t] = 'V'; // V = VISITED
				else
					visited[i][t] = 'N'; // N = NON VISITED
			}
		}
		generateExit(Nsize);
	}

	private static boolean validateExitPosition(int x, int y, int Nsize) {
		// CORNERS
		if (x == 0 && y == 0) // top left corner

			return false;
		else if (x == 0 && y == Nsize - 1) // top right corner
			return false;
		else if (x == Nsize - 1 && y == 0) // bottom left corner
			return false;
		else if (x == Nsize - 1 && y == Nsize - 1) // botton right corner
			return false;
		// Validate border positions
		if (x == 0 || x == Nsize - 1) // first and final line
		{
			if (0 < y && y < Nsize - 1)
				return true;
			else
				return false;
		} else if (y == 0 || y == Nsize - 1)
			return true;
		return false;
	}
	private static void generateExit(int Nsize) {
		Random gen = new Random();

		int exit_x;
		int exit_y;
		do {
			exit_x = gen.nextInt(Nsize);
			exit_y = gen.nextInt(Nsize);
		}while(!validateExitPosition(exit_x,exit_y,Nsize));
		board[exit_x][exit_y] = 'S';
		start_point (exit_x,exit_y,Nsize);
	}
	private static void  start_point (int x, int y,int Nsize)
	{

		int y_start = y;
		int x_start = x;
		System.out.println("x:" +x+"y:" +y);
		if(x==0)
			x_start ++;
		else if(x == Nsize-1)
			x_start --;
		else if( 0< x && x < Nsize-1 )
		{
			if(y== 0)
				y_start ++;
			if(y== Nsize-1)
				y_start --;
		}
		start = new Point(x_start,y_start);
		System.out.println("x_start:" +x_start+"y:" +y_start);
		board[x_start][y_start] = ' ';
		visited[x_start][y_start] = 'V';
	}

	private static LinkedList<Point> FreeNeighbourCell(Point last)
	{
		LinkedList<Point> Neighbour = new LinkedList<Point>();
		int x = last.getX();
		int y = last.getY();
		if(visited[x-1][y] == 'N')
			Neighbour.add(new Point(x-1,y));
		if(visited[x+1][y] == 'N')
			Neighbour.add(new Point(x+1,y));
		if(visited[x][y-1] == 'N')
			Neighbour.add(new Point(x,y-1));
		if(visited[x][y+1] == 'N')
			Neighbour.add(new Point(x,y+1));

		return Neighbour;
	}
	private static boolean test2x2(Point test)
	{
		int x = test.getX();
		int y = test.getY();
		if(board[x][y+1] == ' ')
		{
			if(board[x-1][y] == ' ')
				if(board[x-1][y+1] == ' ')
					return false;
			if(board[x+1][y] == ' ')
				if(board[x+1][y+1] == ' ')
					return false;
		}
		if(board[x][y-1] == ' ')
		{
			if(board[x-1][y]== ' ')
				if(board[x-1][y-1] == ' ')
					return false;
			if(board[x+1][y]== ' ')
				if(board[x+1][y-1] == ' ')
					return false;
		}
		if(board [x][y+1] == 'X')
			if(board[x-1][y] == 'X' && board [x-1][y+1] == ' ')
				return false;
			else if(board[x+1][y] == 'X' && board [x+1][y+1] == ' ' && board [x][y+1] == 'X')
				return false;

		if(board [x][y-1] == 'X')
			if(board[x+1][y] == 'X' && board [x+1][y-1] == ' ')
				return false;
			else if(board[x-1][y] == 'X' && board [x-1][y-1] == ' ')
				return false;


		return true;
	}


	private static void carvePath()//can be optimized
	{
		LinkedList<Point> path = new LinkedList<Point>();
		path.add(start);

		do{
			Point lastPoint =path.getLast();
			LinkedList<Point> freecells = FreeNeighbourCell(lastPoint); 

			if(freecells.size() > 0)
			{
				Point newest;
				switch(rnd.nextInt(freecells.size()))
				{
				case 0:
					newest = freecells.get(0);
					if(test2x2(newest))
					{

						path.add(newest);
						board[newest.getX()][newest.getY()] = ' ';
					}
					visited[newest.getX()][newest.getY()] ='V';
					break;
				case 1:
					newest = freecells.get(1);
					if(test2x2(newest))
					{	
						path.add(newest);
						board[newest.getX()][newest.getY()] = ' ';
					}
					visited[newest.getX()][newest.getY()] ='V';
					break;
				case 2:
					newest = freecells.get(2);
					if(test2x2(newest))
					{	
						path.add(newest);
						board[newest.getX()][newest.getY()] = ' ';
					}
					visited[newest.getX()][newest.getY()] ='V';
					break;
				case 3:
					newest = freecells.get(3);
					if(test2x2(newest))
					{
						path.add(newest);
						board[newest.getX()][newest.getY()] = ' ';
					}
					visited[newest.getX()][newest.getY()] ='V';
					break;

				}
			}
			else
			{
				path.removeLast();
			}
		}while(!path.isEmpty());
	}	

	private static void placeHero(int Nsize)
	{
		int x,y;
		boolean valid = false;
		do{
			x = rnd.nextInt(Nsize);
			y = rnd.nextInt(Nsize);
			if(board[x][y] == ' ')
			{
				board[x][y] = 'H';
				heroi = new Point(x,y);
				valid = true;
			}

		}while(!valid);
	}
	private static void placeDragon(int Nsize,int distancetoHero)
	{
		int x,y;
		int h_x = heroi.getX();
		int h_y = heroi.getY();
		boolean valid = false;
		do{
			x = rnd.nextInt(Nsize);
			y = rnd.nextInt(Nsize);
			
			if(board[x][y] == ' ')
			{
				int deltax = Math.abs(x-h_x);
				int deltay = Math.abs(y-h_y);
				int dist = (int) Math.sqrt(deltax*deltax + deltay*deltay);
				if(dist >= distancetoHero )
				{	
					board[x][y] = 'D';
					dragon = new Point(x,y);
					valid = true;
				}
			}

		}while(!valid);
	}
	
	private static void placeSword(int Nsize)
	{
		int x,y;
		boolean valid = false;
		do{
			x = rnd.nextInt(Nsize);
			y = rnd.nextInt(Nsize);
			
			if(board[x][y] == ' ')
			{
				board[x][y] = 'E';
				valid = true;
			}

		}while(!valid);
		
	}
}