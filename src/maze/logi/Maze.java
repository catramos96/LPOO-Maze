package maze.logi;

import maze.client.Interface;

public class Maze {

	/**************************************** ATTRIBUTES */

	private static Interface ConsoleClient = new Interface();
	private static Board board = new Board();

	/**************************************** FUNCTIONS */

	public static void newTurn(char direction) {
		board.moveHero(direction);
		board.moveRandomDragon();
		board.updateBoard();
	}
	
	public static void main(String[] args) {
		/*
		 * char[][] b1 = { { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
		 * { 'X', 'H', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', ' ',
		 * 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' }, { 'X', 'D', 'X', 'X', ' ',
		 * 'X', ' ', 'X', ' ', 'X' }, { 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X',
		 * ' ', 'X' }, { 'X', ' ', ' ', ' ', ' ', ' ', ' ', 'X', ' ', 'S' }, {
		 * 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' }, { 'X', ' ', 'X',
		 * 'X', ' ', 'X', ' ', 'X', ' ', 'X' }, { 'X', 'E', 'X', 'X', ' ', ' ',
		 * ' ', ' ', ' ', 'X' }, { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X',
		 * 'X' } };
		 */

		char[][] b1 = { { 'X', 'X', 'X', 'X', 'X' }, { 'X', ' ', ' ', 'H', 'S' }, { 'X', ' ', 'X', ' ', 'X' },
				{ 'X', 'E', ' ', 'D', 'X' }, { 'X', 'X', 'X', 'X', 'X' } };

		board.setBoard(b1);
		board.setDragonBehaviour(ConsoleClient.DragonSelector());
		board.updateBoard();
		ConsoleClient.displayBoard(board.getBoard());

		do {
			char read = ConsoleClient.getNextDirection();
			newTurn(read);
			ConsoleClient.displayBoard(board.getBoard());
		} while (!board.exitBoard());

	}

}
