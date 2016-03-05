package maze.logi;

import java.util.Random;



public class Board {
	private char board[][];
	private Hero hero = new Hero();
	private Dragon dragon = new Dragon();
	private Sword sword = new Sword();
	private Point exit = new Point();
	private Random rnd = new Random();


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
			hero.setPosition(getPositionSymbol('A')); // LAN�AR EXCE��O CASO N�O
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
			placeOnBoard(dragon.getPosition(), dragon.getSymbol());
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
		if (hero.isAlive())
			placeOnBoard(hero.getPosition(), hero.getSymbol());
		if (!sword.inUse())
			placeOnBoard(sword.getPosition(), sword.getSymbol());
		
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

	private boolean moveDragonValidMove (Point new_pos, Point ini_pos)
	{
		if (getBoardSymbol(new_pos) == 'X' || getBoardSymbol(new_pos) == 'E')
			return false;
		else {
			cleanPosition(ini_pos);
			dragon.setPosition(new_pos);
			dragon.setSymbol('D');
			return true;
		}
	}

	public void moveDragon() {

		if (dragon.getParalysedMode())
			return;
		
		Point ini_pos = dragon.getPosition();
		Point new_pos = new Point();

		boolean move = false;
		do {
			int mov;
			if (dragon.getSleepMode())
				mov = rnd.nextInt(5) ;
			else
				mov = rnd.nextInt() % 4;
		
			
			switch (mov) {
			case 0:// Norte
				new_pos.setXY(ini_pos.getX(), ini_pos.getY() + 1);
				move = moveDragonValidMove (new_pos,ini_pos);
				break;
			case 1:// Sul
				new_pos.setXY(ini_pos.getX(), ini_pos.getY() - 1);
				move = moveDragonValidMove (new_pos,ini_pos);
				break;
			case 2:// Este
				new_pos.setXY(ini_pos.getX() - 1, ini_pos.getY());
				move = moveDragonValidMove (new_pos,ini_pos);

				break;
			case 3:// Oeste
				new_pos.setXY(ini_pos.getX() + 1, ini_pos.getY());
				move = moveDragonValidMove (new_pos,ini_pos);
				break;
			case 4:
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

	public void newTurn(char direction) {
		moveHero(direction);
		moveDragon();
		heroDragonCollision();
	}

	public  void moveHeroLeft() {
		moveHero('a');
	}

	public  void moveHeroRight() {
		moveHero('d');
	}

	public  void moveHeroUp() {
		moveHero('w');
	}

	public  void moveHeroDown() {

		moveHero('s');
	}

	public boolean boardExit() {


		if (hero.getPosition().getX() == exit.getX() && hero.getPosition().getY() == exit.getY() && dragon.isAlive() == false) {
			return true;
		} else if (hero.isAlive() == false) {
			return true;
		} else
			return false;
	}
	
	public void setDragonBehaviour(char dragon_MODE)
	{
		if (dragon_MODE == 'P')
			dragon.setParalysedMode(true);
		else if (dragon_MODE == 'S')
			dragon.setSleepMode(true);
	}
}
