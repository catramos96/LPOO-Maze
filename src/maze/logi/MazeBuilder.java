package maze.logi;
import java.util.LinkedList;

import java.util.Random;

public class MazeBuilder {


	private  static char board [][];
	private  static char visited [][];
	private  static Point start;
	private  static Random rnd = new Random();
	private  static Point heroi;
	private static Point  dragon;


	public  void main(String[] args) {
		int size = 20;
		//do{
		generateBoard(size);
		carvePath();
		placeHero(size);
		placeDragon(size,1);
		placeSword(size);
	//	}while(test3x3());

		for(int i = 0; i < size; i++)
			System.out.println(board[i]);
	}
	public  char[][] buildMaze(int size)
	{do{
		generateBoard(size);
		carvePath();
		placeHero(size);
		placeDragon(size,3);
		placeSword(size);
		System.out.println("DDD");
	}while(test3x3());
		for(int i = 0; i < size ; i ++)
		{
			System.out.println(board[i]);
		}
		return board;
	}
	private  void generateBoard(int Nsize)//NEEED REFACT
	{
		board = new char[Nsize][Nsize];
		visited = new char[Nsize][Nsize];
		fillBoard(Nsize);
	}


	private  void fillBoard(int Nsize) {
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

	private  boolean validateExitPosition(int x, int y, int Nsize) {
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
	private  void generateExit(int Nsize) {
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
	private  void  start_point (int x, int y,int Nsize)
	{

		int y_start = y;
		int x_start = x;
		//	System.out.println("x:" +x+"y:" +y);
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
		//	System.out.println("x_start:" +x_start+"y:" +y_start);
		board[x_start][y_start] = ' ';
		visited[x_start][y_start] = 'V';
	}

	private  LinkedList<Point> FreeNeighbourCell(Point last)
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
	private  boolean test2x2(Point test)
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
			else if(board[x+1][y] == 'X' && board [x+1][y+1] == ' ')
				return false;

		if(board [x][y-1] == 'X')
			if(board[x+1][y] == 'X' && board [x+1][y-1] == ' ')
				return false;
			else if(board[x-1][y] == 'X' && board [x-1][y-1] == ' ')
				return false;


		return true;
	}

	private  boolean test3x3() {
		char[][] t3x3 = {
				{'X', 'X', 'X',},
				{'X', 'X', 'X',},
				{'X', 'X', 'X',},
		};
		for (int i = 0; i < board.length - t3x3.length; i++)
			for (int j = 0; j < board.length - t3x3.length; j++) {
				boolean match = true;
				for (int y = 0; y < t3x3.length; y++)
					for (int x = 0; x < t3x3.length; x++) 
						if (board[i+y][j+x] != t3x3[y][x])
							match = false;
				if (match)
					return true;
			}		
		return false; 
	}

	private  void carvePath()//can be optimized
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
		System.out.println("Maze");
	}	

	private  void placeHero(int Nsize)
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
		System.out.println("Hero");
	}
	private  void placeDragon(int Nsize,int distancetoHero)
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
				int dist = deltax +deltay;
				if(dist > 1 )
				{	
					board[x][y] = 'D';
					dragon = new Point(x,y);
					valid = true;
				}
				System.out.println(" Dragon Ciclo");
			}

		}while(!valid);
		System.out.println(" Dragon");
	}

	private  void placeSword(int Nsize)
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
		System.out.println(" Sword");
	}
}