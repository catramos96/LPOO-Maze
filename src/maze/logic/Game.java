package maze.logic;
import java.util.Scanner;
import maze.cli.Board;

public class Game {
	public static void main(String[] args) {
		Board t = new Board();
		char d = 'd';
		Scanner s = new Scanner(System.in);

		t.fillBoard();
		t.displayBoard();

		do{			
			if(s.hasNextLine())
			{
				d = s.next().charAt(0); // primeiro char
				t.moveHeroe(d);
				t.moveDragon();
				t.heroeDragonCollision();
				t.displayBoard();
			}

		}while(!t.boardExit());// Enquanto nao chegar a saida
		s.close();
	}
}
