package maze.cli;

import java.util.Random;

public class Board {
	private char board[];
	private Heroe heroe = new Heroe(1, 1);
	private Dragon dragon = new Dragon(1, 4);

	public void fillBoard() {
		board = new char[] { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', '\n', 'X', ' ', ' ', ' ', ' ', ' ',
				' ', ' ', ' ', 'X', '\n', 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X', '\n', 'X', ' ', 'X', 'X',
				' ', 'X', ' ', 'X', ' ', 'X', '\n', 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X', '\n', 'X', ' ',
				' ', ' ', ' ', ' ', ' ', 'X', ' ', 'X', '\n', 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X', '\n',
				'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X', '\n', 'X', 'E', 'X', 'X', ' ', ' ', ' ', ' ', ' ',
				'X', '\n', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', '\n' };
	}

	public void displayBoard() {
		placeOnBoard(9, 5, 'S');
		if (dragon.isAlive() == true) {
			//if the dragon is colliding with the sword
			if (dragon.getPositionX() == 1 && dragon.getPositionY() == 8)
				placeOnBoard(dragon.getPositionX(), dragon.getPositionY(), 'F');
			else
				placeOnBoard(dragon.getPositionX(), dragon.getPositionY(), dragon.getSymbol());
		}
		if (heroe.isAlive() == true)
			placeOnBoard(heroe.getPositionX(), heroe.getPositionY(), heroe.getSymbol());
		System.out.print(board);
	}

	public char getBoardSymbol(int x, int y) {
		return board[x + 11 * y];
	}

	public void placeOnBoard(int x, int y, char rep) {
		board[x + 11 * y] = rep;
	}

	public void moveHeroe(char direction) {
		int x = heroe.getPositionX();
		int y = heroe.getPositionY();

		switch (direction) {
		case 'a': // esquerda
		{
			if (getBoardSymbol(x - 1, y) == 'X')
				break;
			else if (getBoardSymbol(x - 1, y) == 'E')
				heroe.equipArmor();
			heroe.setPosition(x - 1, y);
			placeOnBoard(x, y, ' ');
			break;
		}
		case 's': // baixo
		{
			if (getBoardSymbol(x, y + 1) == 'X')
				break;
			else if (getBoardSymbol(x, y + 1) == 'E')
				heroe.equipArmor();
			heroe.setPosition(x, y + 1);
			placeOnBoard(x, y, ' ');
			break;
		}
		case 'd': // direita
		{
			if (getBoardSymbol(x + 1, y) == 'X')
				break;
			else if (getBoardSymbol(x + 1, y) == 'E')
				heroe.equipArmor();
			heroe.setPosition(x + 1, y);
			placeOnBoard(x, y, ' ');
			break;
		}
		case 'w': // cima
		{
			if (getBoardSymbol(x, y - 1) == 'X')
				break;
			else if (getBoardSymbol(x, y - 1) == 'E')
				heroe.equipArmor();
			heroe.setPosition(x, y - 1);
			placeOnBoard(x, y, ' ');
			break;
		}
		default:
			break;
		}
	}

	public void moveDragon() {
		Random rn = new Random();
		int x = dragon.getPositionX();
		int y = dragon.getPositionY();

		boolean move = false;
		do {
			int mov = rn.nextInt() % 4;
			switch (mov) {
			case 0:// Norte
				if (getBoardSymbol(x, y - 1) == 'X' || getBoardSymbol(x, y - 1) == 'E')
					break;
				else {
					System.out.println("NORTE");
					board[x + 11 * y] = ' ';
					dragon.setPosition(x, y - 1);
					move = true;
				}
				break;
			case 1:// Sul
				if (getBoardSymbol(x, y + 1) == 'X' || getBoardSymbol(x, y + 1) == 'E')
					break;
				else {
					System.out.println("SUL");
					board[x + 11 * y] = ' ';
					dragon.setPosition(x, y + 1);
					move = true;
				}
				break;
			case 2:// Este
				if (getBoardSymbol(x - 1, y) == 'X' || getBoardSymbol(x - 1, y) == 'E')
					break;
				else {
					System.out.println("ESTE");
					board[x + 11 * y] = ' ';
					dragon.setPosition(x - 1, y);
					move = true;
				}
				break;
			case 3:// Oeste
				if (getBoardSymbol(x + 1, y) == 'X' || getBoardSymbol(x + 1, y) == 'E')
					break;
				else {
					System.out.println("OESTE");
					board[x + 11 * y] = ' ';
					dragon.setPosition(x + 1, y);
					move = true;
				}
				break;
			}
		} while (!move);
	}

	public void heroeDragonCollision(){
		int dist_x = Math.abs(heroe.getPositionX() - dragon.getPositionX());
		int dist_y = Math.abs(heroe.getPositionY() - dragon.getPositionY());
		int dist = (int) Math.sqrt(dist_y * dist_y + dist_x * dist_x);
		int emlinha = dist_x + dist_y;// ANULAR A dist em diagonal
		if ((dist == 0 || dist == 1) && (emlinha == 1 || emlinha == 0))
			if (heroe.getSymbol() == 'H')
				heroe.setAlive(false);
			else if (heroe.getSymbol() == 'A')
				dragon.setAlive(false);
	}

	public boolean boardExit() {

		if (heroe.getPositionX() == 9 && heroe.getPositionY() == 5 && dragon.isAlive() == false) {
			System.out.println("O guerreiro ganhou!");
			return true;
		} else if (heroe.isAlive() == false) {
			System.out.println("O dragao ganhou");
			return true;
		} else
			return false;

	}
}
