package maze.logi;

import java.util.Random;

public class MazeGenerator {

	/**************************************** ATTRIBUTES */

	private char board[][];
	private char visited[][];
	private int x_start;
	private int y_start;

	/**************************************** FUNCTIONS */

	public void main(String[] args) {

		generateBoard(8);

		System.out.println("GENERATED BOARD");
		for (int i = 0; i < 8; i++) {
			System.out.println(board[i]);
		}

		System.out.println("Visited BOARD");
		for (int i = 0; i < 8; i++) {
			System.out.println(visited[i]);
		}

	}

	private void generateBoard(int Nsize)// NEEED REFACT
	{
		board = new char[Nsize][Nsize];
		visited = new char[Nsize][Nsize];
		fillBoard(Nsize);
	}

	private void fillBoard(int Nsize) {
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

	private boolean validateExitPosition(int x, int y, int Nsize) {
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

	private void generateExit(int Nsize) {
		Random gen = new Random();

		int exit_x;
		int exit_y;
		do {
			exit_x = gen.nextInt(Nsize);
			exit_y = gen.nextInt(Nsize);
		} while (!validateExitPosition(exit_x, exit_y, Nsize));

		board[exit_x][exit_y] = 'S';

	}

	private void start_point(int x, int y, int Nsize) {

		y_start = y;
		x_start = x;

		if (x == 0)
			y_start++;
		else if (x == Nsize - 1)
			y_start--;
		else if (y != 0 && x != Nsize - 1)
			x_start++;

	}

}
