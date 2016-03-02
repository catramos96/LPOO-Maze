package maze.cli;

import java.util.Random;

public class Board {
	private char board[][] = { { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
			{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' }, { 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
			{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' }, { 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
			{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', 'X', ' ', 'S' }, { 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
			{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' }, { 'X', 'E', 'X', 'X', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } };
	private Hero hero = new Hero(1, 1);
	private Dragon dragon = new Dragon(1, 4);

	public char[][] getBoard() {
		placeOnBoard(9, 5, 'S');

		if (dragon.isAlive() == true) {
			if (hero.isAlive() == true) {
				placeOnBoard(hero.getPositionX(), hero.getPositionY(), hero.getSymbol());
				if (hero.getSymbol() == 'H')
					placeOnBoard(1, 8, 'E');
			}

			// if the dragon is colliding with the sword
			if (dragon.getPositionX() == 1 && dragon.getPositionY() == 8) {
				dragon.setSymbol('F');
				placeOnBoard(dragon.getPositionX(), dragon.getPositionY(), 'F');
			} else
				placeOnBoard(dragon.getPositionX(), dragon.getPositionY(), dragon.getSymbol());
		}

		return board;

	}

	public char getBoardSymbol(int x, int y) {
		return board[y][x];
	}

	public void placeOnBoard(int x, int y, char symbol) {
		// x representa as col unas da matriz e y as linhas
		board[y][x] = symbol;
	}

	public void moveHeroe(char direction) {
		int x = hero.getPositionX();
		int y = hero.getPositionY();

		switch (direction) {
		case 'a': // esquerda
		{
			if (getBoardSymbol(x - 1, y) == 'X')
				break;
			else if (getBoardSymbol(x - 1, y) == 'E')
				hero.equipArmor();
			hero.setPosition(x - 1, y);
			placeOnBoard(x, y, ' ');
			break;
		}
		case 's': // baixo
		{
			if (getBoardSymbol(x, y + 1) == 'X')
				break;
			else if (getBoardSymbol(x, y + 1) == 'E')
				hero.equipArmor();
			hero.setPosition(x, y + 1);
			placeOnBoard(x, y, ' ');
			break;
		}
		case 'd': // direita
		{
			if (getBoardSymbol(x + 1, y) == 'X')
				break;
			else if (getBoardSymbol(x + 1, y) == 'E')
				hero.equipArmor();
			hero.setPosition(x + 1, y);
			placeOnBoard(x, y, ' ');
			break;
		}
		case 'w': // cima
		{
			if (getBoardSymbol(x, y - 1) == 'X')
				break;
			else if (getBoardSymbol(x, y - 1) == 'E')
				hero.equipArmor();
			hero.setPosition(x, y - 1);
			placeOnBoard(x, y, ' ');
			break;
		}
		default:
			break;
		}
	}

	public void moveDragon(boolean sleep, boolean paralyzed) {

		if (paralyzed == true)
			return;
		Random rn = new Random();
		int x = dragon.getPositionX();
		int y = dragon.getPositionY();

		boolean move = false;
		do {
			int mov;
			if (sleep == true)
				mov = rn.nextInt() % 6;
			else
				mov = rn.nextInt() % 4;

			switch (mov) {
			case 0:// Norte
				if (getBoardSymbol(x, y - 1) == 'X' || getBoardSymbol(x, y - 1) == 'E')
					break;
				else {
					placeOnBoard(x, y, ' ');
					dragon.setPosition(x, y - 1);
					dragon.setSymbol('D');
					move = true;
				}
				break;
			case 1:// Sul
				if (getBoardSymbol(x, y + 1) == 'X' || getBoardSymbol(x, y + 1) == 'E')
					break;
				else {
					placeOnBoard(x, y, ' ');
					dragon.setPosition(x, y + 1);
					dragon.setSymbol('D');
					move = true;
				}
				break;
			case 2:// Este
				if (getBoardSymbol(x - 1, y) == 'X' || getBoardSymbol(x - 1, y) == 'E')
					break;
				else {
					placeOnBoard(x, y, ' ');
					dragon.setPosition(x - 1, y);
					dragon.setSymbol('D');
					move = true;
				}
				break;
			case 3:// Oeste
				if (getBoardSymbol(x + 1, y) == 'X' || getBoardSymbol(x + 1, y) == 'E')
					break;
				else {
					placeOnBoard(x, y, ' ');
					dragon.setPosition(x + 1, y);
					dragon.setSymbol('D');
					move = true;
				}
				break;
			case 4:
				move = true;// Sleep
				dragon.setSymbol('d');
				break;
			case 5:
				move = true;// Sleep
				dragon.setSymbol('d');
				break;
			}

		} while (!move);
	}

	public void heroeDragonCollision() {
		int dist_x = Math.abs(hero.getPositionX() - dragon.getPositionX());
		int dist_y = Math.abs(hero.getPositionY() - dragon.getPositionY());
		int dist = (int) Math.sqrt(dist_y * dist_y + dist_x * dist_x);
		int emlinha = dist_x + dist_y;// ANULAR A dist em diagonal
		if ((dist == 0 || dist == 1) && (emlinha == 1 || emlinha == 0))
			if (hero.getSymbol() == 'H' && (dragon.getSymbol() == 'D' || dragon.getSymbol() == 'd'))
				hero.setAlive(false);
			else if (hero.getSymbol() == 'A')
				dragon.setAlive(false);
	}

	public void newTurn(char direction, boolean dragon_sleep, boolean dragon_paralysed) {
		moveHeroe(direction);
		moveDragon(dragon_sleep, dragon_paralysed);
		heroeDragonCollision();
	}

	public boolean boardExit() {

		if (hero.getPositionX() == 9 && hero.getPositionY() == 5 && dragon.isAlive() == false) {
			return true;
		} else if (hero.isAlive() == false) {
			return true;
		} else
			return false;

	}
}
