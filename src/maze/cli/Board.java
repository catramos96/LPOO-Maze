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

	public Board(char[][] b) {
		setBoard(b);
	}

	public void setBoard(char[][] b) {
		board = b;
		Point pos;
		// hero
		pos = getPositionSymbol('H');
		if (pos == null)
			hero.setPosition(getPositionSymbol('A')); // LANÇAR EXCEÇÃO CASO NÃO
														// ENCONTRO A
		else
			hero.setPosition(pos);
		cleanPosition(pos);
		// dragon
		pos = getPositionSymbol('D');
		if (pos == null)
			dragon.setPosition(getPositionSymbol('F'));// --------------- null ?
		else
			dragon.setPosition(pos);
		cleanPosition(pos);
		// sword
		pos = getPositionSymbol('E');
		if (pos == null)
			sword.setPosition(getPositionSymbol('F'));// -------------------
														// null?
		else
			sword.setPosition(pos);
		cleanPosition(pos);

		// exit
		exit.setXY(getPositionSymbol('S').getX(), getPositionSymbol('S').getY());
		cleanPosition(pos);
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

	public void updateBoard() {
		if (dragon.isAlive() == true) {
			if (dragon.getX() == sword.getX() && dragon.getY() == sword.getY()) {
				dragon.setSymbol('F');
			} else {
				dragon.setSymbol('D');
			}
		}
		// just in case...
		if (hero.getX() == sword.getX() && hero.getY() == sword.getY()) {
			sword.setUse(true);
			hero.equipArmor();
		}
	}

	public void cleanPosition(Point p) {
		placeOnBoard(p, ' ');
	}

	public char[][] getBoard() {
		return board;
	}

	public Hero getHero() {
		return hero;
	}

	public Dragon getDragon() {
		return dragon;
	}

	public Sword getSword() {
		return sword;
	}

	public Point getExit() {
		return exit;
	}

	public char getBoardSymbol(Point p) {
		// x representa as col unas da matriz e y as linhas
		return board[p.getY()][p.getX()];
	}

	public void placeOnBoard(Point position, char symbol) {
		board[position.getY()][position.getX()] = symbol;
	}

	public void moveHero(char direction) {
		Point ini_pos = hero.getPosition();
		Point new_pos = new Point();

		switch (direction) {
		case 'a': // esquerda
		{
			new_pos.setXY(ini_pos.getX() - 1, ini_pos.getY());
			if (getBoardSymbol(new_pos) == 'X')
				break;
			else if (getBoardSymbol(new_pos) == 'E') {
				hero.equipArmor();
				sword.setUse(true);
			}
			cleanPosition(ini_pos);
			hero.setPosition(new_pos);
			break;
		}
		case 's': // baixo
		{
			new_pos.setXY(ini_pos.getX(), ini_pos.getY() + 1);
			if (getBoardSymbol(new_pos) == 'X')
				break;
			else if (getBoardSymbol(new_pos) == 'E') {
				hero.equipArmor();
				sword.setUse(true);
			}
			cleanPosition(ini_pos);
			hero.setPosition(new_pos);
			break;
		}
		case 'd': // direita
		{
			new_pos.setXY(ini_pos.getX() + 1, ini_pos.getY());
			if (getBoardSymbol(new_pos) == 'X')
				break;
			else if (getBoardSymbol(new_pos) == 'E') {
				hero.equipArmor();
				sword.setUse(true);
			}
			cleanPosition(ini_pos);
			hero.setPosition(new_pos);
			break;
		}
		case 'w': // cima
		{
			new_pos.setXY(ini_pos.getX(), ini_pos.getY() - 1);
			if (getBoardSymbol(new_pos) == 'X')
				break;
			else if (getBoardSymbol(new_pos) == 'E') {
				hero.equipArmor();
				sword.setUse(true);
			}
			cleanPosition(ini_pos);
			hero.setPosition(new_pos);
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
		Point ini_pos = dragon.getPosition();
		Point new_pos = new Point();

		boolean move = false;
		do {
			int mov;
			if (sleep == true)
				mov = rn.nextInt() % 6;
			else
				mov = rn.nextInt() % 4;

			switch (mov) {
			case 0:// Norte
				new_pos.setXY(ini_pos.getX(), ini_pos.getY() - 1);
				if (getBoardSymbol(new_pos) == 'X' || getBoardSymbol(new_pos) == 'E')
					break;
				else {
					cleanPosition(ini_pos);
					dragon.setPosition(new_pos);
					dragon.setSymbol('D');
					move = true;
				}
				break;
			case 1:// Sul
				new_pos.setXY(ini_pos.getX(), ini_pos.getY() + 1);
				if (getBoardSymbol(new_pos) == 'X' || getBoardSymbol(new_pos) == 'E')
					break;
				else {
					cleanPosition(ini_pos);
					dragon.setPosition(new_pos);
					dragon.setSymbol('D');
					move = true;
				}
				break;
			case 2:// Este
				new_pos.setXY(ini_pos.getX() - 1, ini_pos.getY());
				if (getBoardSymbol(new_pos) == 'X' || getBoardSymbol(new_pos) == 'E')
					break;
				else {
					cleanPosition(ini_pos);
					dragon.setPosition(new_pos);
					dragon.setSymbol('D');
					move = true;
				}
				break;
			case 3:// Oeste
				new_pos.setXY(ini_pos.getX() + 1, ini_pos.getY());
				if (getBoardSymbol(new_pos) == 'X' || getBoardSymbol(new_pos) == 'E')
					break;
				else {
					cleanPosition(ini_pos);
					dragon.setPosition(new_pos);
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

	public void heroDragonCollision() {
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
		heroDragonCollision();
	}

	public boolean boardExit() {

		if (hero.getPosition().getX() == exit.getX() && hero.getPosition().getY() == exit.getY() && dragon.isAlive() == false) {
			return true;
		} else if (hero.isAlive() == false) {
			return true;
		} else
			return false;

	}
}
