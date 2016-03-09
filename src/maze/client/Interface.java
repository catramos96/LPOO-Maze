package maze.client;

import java.util.Scanner;


public class Interface {
	
	/**************************************** ATTRIBUTES */

	private Scanner s = new Scanner(System.in);

	/**************************************** FUNCTIONS */
	
	public Interface() {};

	public  void displayBoard(char[][] board) {
		for (int i = 0; i < board.length; i++) {
			System.out.println(board[i]);
		}
	}

	public void closeInterface()
	{
		s.close();
	}

	public void msgHeroWins()
	{
		System.out.println("The hero wins!");
	}
	
	public void msgHeroLoses()
	{
		System.out.println("Ther hero loses!");
	}

	public char DragonSelector()
	{
		char dragon_MODE = 'd';
		do {
			System.out.println("Choose dragon mode (P - PARALYSED ; R - RANDOM MOVE ; S - RANDOM MOVE & SLEEP ): ");
			if (s.hasNextLine())
				dragon_MODE = s.next().charAt(0);
		} while (dragon_MODE != 'P' && dragon_MODE != 'R' && dragon_MODE != 'S');
		return dragon_MODE;
	}
	
	public int mazeSizeBuilder() {
		int size = 0;
		do{
			System.out.println("Choose the maze size: ");
			if(s.hasNextLine())
				size = s.nextInt();
		}while(size <= 5);
		return size;
	}

	public char getNextDirection()
	{	char read = 'Q';	
	do{
		System.out.println("Next Move ( Type a,s,d or w):");
		if (s.hasNextLine())
			read=  s.next().charAt(0);
	}while(read != 'a' && read != 's' && read != 'd' && read != 'w');
	
	return read;
	}
}