package maze.logic;

import java.util.Scanner;
import maze.cli.Board;

public class Game {
	public static void main(String[] args) {
		Board b = new Board();
		char d = 'd';
		Scanner s = new Scanner(System.in);

		b.displayBoard();

		do {
			if (s.hasNextLine()) {
				d = s.next().charAt(0); // primeiro char
				b.moveHeroe(d);
				b.moveDragon();
				b.heroeDragonCollision();
				b.displayBoard();
			}

		} while (!b.boardExit());// Enquanto nao chegar a saida
		s.close();
	}
}
