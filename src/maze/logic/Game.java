package maze.logic;

import java.util.Scanner;
import maze.cli.Board;

public class Game {
	
	public static void displayBoard(Board b)
	{
		char tab [][] = b.getBoard();
		for (int i = 0; i < 10; i++) {
			System.out.println(tab[i]);
		}
	}
	
	public static void main(String[] args) {
		Board b = new Board();
		
		Scanner s = new Scanner(System.in);
		
		
		
		char dragon_MODE = 'd';
		do{
			System.out.println("Choose dragon mode (P - PARALYSED ; R - RANDOM MOVE ; S - RANDOM MOVE & SLEEP ): ");
			if (s.hasNextLine()) 
			{
				dragon_MODE = s.next().charAt(0);
			}
		}while(dragon_MODE != 'P' && dragon_MODE != 'R'&& dragon_MODE != 'S' );
		
		
		
		
		boolean paralysed = false;
		boolean sleep = false;
		
		if(dragon_MODE == 'P')
			paralysed = true;
		else if(dragon_MODE == 'S')
			sleep = true;
		
		displayBoard(b);
		do {
			if (s.hasNextLine()) {
				
				char read = 'd';
				read = s.next().charAt(0); // primeiro char
				b.newTurn(read, sleep, paralysed);
				displayBoard(b);
			}

		} while (!b.boardExit());// Enquanto nao chegar a saida
		
		s.close();
	}
	
	
}
