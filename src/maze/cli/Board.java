package maze.cli;

import java.util.Random;

import javax.swing.plaf.FontUIResource;

public class Board {
	private char board[][];
	private Hero hero = new Hero();
	private Dragon dragon = new Dragon();
	private Sword sword = new Sword();
	private Point exit = new Point();

	public Board() {
		char[][] b = { {} };
		board = b;
	}

	public void setBoard(char[][] b) {
		board = b;
		Point pos;
		pos = getHeroPosition();
		hero.setPosition(pos.getX(), pos.getY());
		pos.setPointPos(getDragonPosition());
		dragon.setPosition(pos.getX(), pos.getY());
		pos = getSwordPosition();
		sword.setPosition(pos.getX(), pos.getY());
		pos = getExitPosition();
		exit = pos;
	}

	public char[][] getBoard() {
		placeOnBoard(9, 5, 'S');

		if (hero.isAlive() == true) {
			placeOnBoard(hero.getPosition().getX(), hero.getPosition().getY(), hero.getSymbol());
			if (hero.getSymbol() == 'H')
				placeOnBoard(1, 8, 'E');
		}

		if (dragon.isAlive() == true) {
			// if the dragon is colliding with the sword
			if (dragon.getPosition().getX() == 1 && dragon.getPosition().getY() == 8) {
				dragon.setSymbol('F');
				placeOnBoard(dragon.getPosition().getX(), dragon.getPosition().getY(), 'F');
			} else
				placeOnBoard(dragon.getPosition().getX(), dragon.getPosition().getY(), dragon.getSymbol());
		}

		return board;

	}

	public Point getHeroPosition() {
		if (getPositionSymbol('H') == null)
			return getPositionSymbol('A');
		else
			return getPositionSymbol('H');
	}

	public Point getDragonPosition() {
		if (getPositionSymbol('D') == null)
			return getPositionSymbol('F');
		else
			return getPositionSymbol('D');
	}

	public Point getSwordPosition() {
		if (getPositionSymbol('E') == null)
			return getPositionSymbol('F');
		else
			return getPositionSymbol('E');
	}

	public Point getExitPosition() {
		return getPositionSymbol('S');
	}

	public Point getPositionSymbol(char symbol) {
		Point pos = new Point();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (board[i][j] == symbol) {
					pos.setXY(j, i);
					return pos;
				}
			}
		}
		return pos;
	}

	public Hero getHero() {
		return hero;
	}

	public char getBoardSymbol(int x, int y) {
		return board[y][x];
	}

	public void placeOnBoard(int x, int y, char symbol) {
		// x representa as col unas da matriz e y as linhas
		board[y][x] = symbol;
	}

	public void moveHero(char direction) {
		int x = hero.getPosition().getX();
		int y = hero.getPosition().getY();

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
		int x = dragon.getPosition().getX();
		int y = dragon.getPosition().getY();

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
		int dist_x = Math.abs(hero.getPosition().getX() - dragon.getPosition().getX());
		int dist_y = Math.abs(hero.getPosition().getY() - dragon.getPosition().getY());
		int dist = (int) Math.sqrt(dist_y * dist_y + dist_x * dist_x);
		int emlinha = dist_x + dist_y;// ANULAR A dist em diagonal
		if ((dist == 0 || dist == 1) && (emlinha == 1 || emlinha == 0))
			if (hero.getSymbol() == 'H' && (dragon.getSymbol() == 'D' || dragon.getSymbol() == 'd'))
				hero.setAlive(false);
			else if (hero.getSymbol() == 'A')
				dragon.setAlive(false);
	}

	public void newTurn(char direction, boolean dragon_sleep, boolean dragon_paralysed) {
		moveHero(direction);
		moveDragon(dragon_sleep, dragon_paralysed);
		heroeDragonCollision();
	}

	public boolean boardExit() {

		if (hero.getPosition().getX() == 9 && hero.getPosition().getY() == 5 && dragon.isAlive() == false) {
			return true;
		} else if (hero.isAlive() == false) {
			return true;
		} else
			return false;

	}
}
