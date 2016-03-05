package maze.logi;



import maze.client.Interface;

public class Maze {
	private static Interface ConsoleClient = new Interface();
	private static Board board = new Board();
	

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
		board.setDragonBehaviour(ConsoleClient.DragonSelector());
		board.updateBoard();
		ConsoleClient.displayBoard(board.getBoard());	
		
		do {
				char read = ConsoleClient.getNextDirection();
				board.newTurn(read);
				board.updateBoard();
				ConsoleClient.displayBoard(board.getBoard());
		} while (!board.boardExit());

	}
	

}
