package maze.logi;

import maze.client.Interface;

public class Maze {

	/**************************************** ATTRIBUTES */

	private static Interface ConsoleClient = new Interface();
	private static Board board = new Board();

	/**************************************** FUNCTIONS */

	public static void newTurn(char direction) {
		board.moveHero(direction);
		board.moveRandomDragons();
		board.updateBoard();
	}

	public void initializeMaze() {
		MazeBuilder mz = new MazeBuilder();
		Board b = new Board(mz.buildMaze(ConsoleClient.mazeSizeBuilder()));
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

	/*public static void main(String[] args) {
		do {
			initializeMaze();
			gamePlay();
			endGame();
		} while (!ConsoleClient.exitGame());
	}*/
}
