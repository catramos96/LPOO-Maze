package maze.logi;

import maze.client.Interface;

public class Maze {

	/**************************************** ATTRIBUTES */

	private static Interface ConsoleClient = new Interface();
	private static Board board = new Board();

	/**************************************** FUNCTIONS */

	public static void newTurn(char direction) {
		board.moveRandomDragons();
		board.moveHero(direction);
		board.updateBoard();
	}

	public static void initializeMaze() {
		MazeBuilder mz = new MazeBuilder();
		int maze_size = ConsoleClient.mazeSizeBuilder();
		int n_dragons;
		do {
			n_dragons = ConsoleClient.getNumberOfDragons();
		} while (n_dragons > (maze_size - 2) / 2);
		Board b = new Board(mz.buildMaze(maze_size, n_dragons));
		board = b;
		board.setDragonsBehaviour(ConsoleClient.DragonSelector());
		board.updateBoard();
	}

	public static void gamePlay() {
		ConsoleClient.displayBoard(board.getBoard());

		do {
			char read = ConsoleClient.getNextDirection();
			newTurn(read);
			ConsoleClient.displayBoard(board.getBoard());
		} while (!board.exitBoard());
	}

	public static void endGame() {
		if (board.heroWins())
			ConsoleClient.msgHeroWins();
		else
			ConsoleClient.msgHeroLoses();
	}

	public static void main(String[] args) {
		do {
			initializeMaze();
			gamePlay();
			endGame();
		} while (!ConsoleClient.exitGame());
	}
}
