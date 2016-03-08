package maze.logi;

import java.util.Random;

public class Board {

	/**************************************** ATTRIBUTES */

	private char board[][];
	private Hero hero = new Hero();
	private Dragon dragon = new Dragon();
	private Sword sword = new Sword();
	private Point exit = new Point();
	private Random rnd = new Random();

	/**************************************** FUNCTIONS */

	/*****************
	 * CONSTRUCTOR *
	 *****************/
	public Board() {
		char[][] b = { {} };
		board = b;
	}

	public Board(char[][] b) {
		setBoard(b);
	}

	/*****************
	 * GETS *
	 *****************/

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

	/*****************
	 * SETS *
	 *****************/

	public void setBoard(char[][] b) {
		board = b;
		Point pos;
		// hero
		pos = getPositionSymbol('H');
		if (pos == null)
			hero.setPosition(getPositionSymbol('A')); // LAN�AR EXCE��O
														// CASO N�O
		else
			hero.setPosition(pos);
		// dragon
		pos = getPositionSymbol('D');
		if (pos == null)
			dragon.setPosition(getPositionSymbol('F'));// --------------- null ?
		else
			dragon.setPosition(pos);
		// sword
		pos = getPositionSymbol('E');
		if (pos == null)
			sword.setPosition(getPositionSymbol('F'));// -------------------
		// null?
		else
			sword.setPosition(pos);

		// exit
		exit.setXY(getPositionSymbol('S').getX(), getPositionSymbol('S').getY());
	}

	public void setDragonBehaviour(char dragon_MODE) {
		if (dragon_MODE == 'P'){
			dragon.setParalysedMode(true);
			dragon.setSleepMode(false);
		}
		else if (dragon_MODE == 'S'){
			dragon.setSleepMode(true);
			dragon.setParalysedMode(false);
		}
	}

	/*****************
	 * MOVEMENTS *
	 *****************/

	public void heroNextPosition(Point new_pos) {
		Point ini_pos = hero.getPosition();
		if (!betweenBoardLimits(new_pos)) // just in case ...
			return;
		if (getBoardSymbol(new_pos) == 'X' || getBoardSymbol(new_pos) == 'd')
			return;
		else if (getBoardSymbol(new_pos) == 'E') {
			hero.equipArmor();
			sword.setUse(true);
		}
		cleanPosition(ini_pos);
		hero.setPosition(new_pos);
		heroDragonCollision();
	}

	public void moveHero(char direction) {

		Point new_pos = new Point();

		switch (direction) {
		case 'a': // esquerda
		{
			new_pos.setXY(hero.getX() - 1, hero.getY());
			heroNextPosition(new_pos);
			break;
		}
		case 's': // baixo
		{
			new_pos.setXY(hero.getX(), hero.getY() + 1);
			heroNextPosition(new_pos);
			break;
		}
		case 'd': // direita
		{
			new_pos.setXY(hero.getX() + 1, hero.getY());
			heroNextPosition(new_pos);
			break;
		}
		case 'w': // cima
		{
			new_pos.setXY(hero.getX(), hero.getY() - 1);
			heroNextPosition(new_pos);
			break;
		}
		default:
			break;
		}
	}

	private boolean dragonNextPosition(Point new_pos) {
		Point ini_pos = dragon.getPosition();
		if (!betweenBoardLimits(new_pos)) // just in case
			return false;
		if (getBoardSymbol(new_pos) == 'X' || getBoardSymbol(new_pos) == 'S')
			return false;
		else if (getBoardSymbol(new_pos) == 'E')
			dragon.setSymbol('F');
		else
			dragon.setSymbol('D');
		cleanPosition(ini_pos);
		dragon.setPosition(new_pos);
		return true;
	}

	public boolean moveDragon(int d) {
		/*
		 * d - 0 down d - 1 up d - 2 left d - 3 right
		 */
		if (dragon.getParalysedMode())
			return false;
		
		if(dragon.getSleepMode())
			return false;

		Point new_pos = new Point();
		boolean move = false;

		switch (d) {
		case 0:// down
			new_pos.setXY(dragon.getX(), dragon.getY() + 1);
			move = dragonNextPosition(new_pos);
			break;
		case 1:// up
			new_pos.setXY(dragon.getX(), dragon.getY() - 1);
			move = dragonNextPosition(new_pos);
			break;
		case 2:// left
			new_pos.setXY(dragon.getX() - 1, dragon.getY());
			move = dragonNextPosition(new_pos);
			break;
		case 3:// right
			new_pos.setXY(dragon.getX() + 1, dragon.getY());
			move = dragonNextPosition(new_pos);
			break;
		case 4:
			move = true;// Sleep
			dragon.setSymbol('d');
			break;
		}
		return move;
	}

	public void moveRandomDragon() {
		int mov;
		if (dragon.getParalysedMode())
			return;
		boolean move = false;
		do {
			if (dragon.getSleepMode())
				mov = rnd.nextInt(5);
			else
				mov = rnd.nextInt() % 4;
			move = moveDragon(mov);
		} while (!move);
	}
	
	/*****************
	 * BOOLEAN *
	 *****************/

	public boolean exitBoard() {

		if (heroWins()) {
			return true;
		} else if (hero.isAlive() == false) {
			return true;
		} else
			return false;
	}

	//for tests
	public boolean betweenBoardLimits(Point p) {
		if (p.getX() >= board.length || p.getY() >= board.length)
			return false;
		else
			return true;
	}
	
	public boolean heroWins() {
		if (hero.getPosition().equals(exit) && dragon.isAlive() == false)
			return true;
		else
			return false;
	}

	/*****************
	 * OTHERS *
	 *****************/

	public void updateBoard() {
		heroDragonCollision();
		// update sword
		if (!sword.inUse())
			placeOnBoard(sword.getPosition(), sword.getSymbol());
		// update dragon
		if (dragon.isAlive() == true)
			placeOnBoard(dragon.getPosition(), dragon.getSymbol());
		// update hero
		if (hero.isAlive())
			placeOnBoard(hero.getPosition(), hero.getSymbol());
	}

	public void cleanPosition(Point p) {
		placeOnBoard(p, ' ');
	}

	public void placeOnBoard(Point position, char symbol) {
		board[position.getY()][position.getX()] = symbol;
	}

	public void heroDragonCollision() {
		int dist_x = Math.abs(hero.getPosition().getX() - dragon.getPosition().getX());
		int dist_y = Math.abs(hero.getPosition().getY() - dragon.getPosition().getY());
		int dist = (int) Math.sqrt(dist_y * dist_y + dist_x * dist_x);
		int emlinha = dist_x + dist_y;// ANULAR A dist em diagonal
		if ((dist == 0 || dist == 1) && (emlinha == 1 || emlinha == 0))
			if (hero.getSymbol() == 'H' && (dragon.getSymbol() == 'D'))
				hero.setAlive(false);
			else if (hero.getSymbol() == 'A')
				dragon.setAlive(false);
	}

}
