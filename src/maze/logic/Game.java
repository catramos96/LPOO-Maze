package maze.logic;

import java.util.Scanner;
import maze.cli.Board;
import maze.cli.Point;

public class Game {
	private static Board board = new Board();

	public Game() {
	};

	public Game(char[][] game_board) {
		board.setBoard(game_board);
	}

	public static void displayBoard() {
		board.updateBoard();
		// if (heroAlive())
		board.placeOnBoard(board.getHero().getPosition(), board.getHero().getSymbol());
		if (board.getDragon().isAlive())
			board.placeOnBoard(board.getDragon().getPosition(), board.getDragon().getSymbol());
		if (!board.getSword().inUse())
			board.placeOnBoard(board.getSword().getPosition(), board.getSword().getSymbol());
		board.placeOnBoard(board.getExit(), 'S');
		for (int i = 0; i < board.getBoard().length; i++) {
			System.out.println(board.getBoard()[i]);
		}
	}

	public static boolean heroAlive() {
		return (board.getHero().isAlive());
	}

	public static Board getBoard() {
		return board;
	}

	public static void moveHeroLeft() {
		board.moveHero('a');
	}

	public static void moveHeroRight() {
		board.moveHero('d');
	}

	public static void moveHeroUp() {
		board.moveHero('w');
	}

	public static void moveHeroDown() {
		board.moveHero('s');
	}

	public static void main(String[] args) {

		char[][] b1 = { { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
				{ 'X', 'H', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
				{ 'X', 'D', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
				{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
				{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', 'X', ' ', 'S' },
				{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
				{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
				{ 'X', 'E', 'X', 'X', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } };

		board.setBoard(b1);

		Scanner s = new Scanner(System.in);

		char dragon_MODE = 'd';
		do {
			System.out.println("Choose dragon mode (P - PARALYSED ; R - RANDOM MOVE ; S - RANDOM MOVE & SLEEP ): ");
			if (s.hasNextLine()) {
				dragon_MODE = s.next().charAt(0);
			}
		} while (dragon_MODE != 'P' && dragon_MODE != 'R' && dragon_MODE != 'S');

		boolean paralysed = false;
		boolean sleep = false;

		if (dragon_MODE == 'P')
			paralysed = true;
		else if (dragon_MODE == 'S')
			sleep = true;

		displayBoard();
		do {
			if (s.hasNextLine()) {

				char read = 'd';
				read = s.next().charAt(0); // primeiro char
				board.newTurn(read, sleep, paralysed);
				board.updateBoard();
				displayBoard();
			}

		} while (!board.boardExit());// Enquanto nao chegar a saida

		s.close();

	}

}
