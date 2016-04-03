package maze.logi;

import java.util.LinkedList;
import java.util.Random;

public class Board {

	/**************************************** ATTRIBUTES */

	private char board[][];
	private Hero hero = new Hero();
	private LinkedList<Dragon> dragons = new LinkedList<Dragon>();
	private Sword sword = new Sword();
	private Point exit = new Point();
	private Random rnd = new Random();

	/**************************************** FUNCTIONS */

	/*****************
	 * CONSTRUCTOR *
	 *****************/
	/**
	 * Default board construtor.
	 * Create a empty gameboard.
	 */
	public Board() {
		char[][] b = { {} };
		board = b;
	}
	/**
	 * Create a board with a specific boardgame.
	 * @param b
	 */
	public Board(char[][] b) {
		setBoard(b);
	}

	/*****************
	 * GETS *
	 *****************/
	
	/**
	 * Return game board.
	 * @return char[ ][ ]  game board
	 */
	public char[][] getBoard() {
		return board;
	}
	/**
	 *  Return Hero present in the game
	 * @return
	 */
	public Hero getHero() {
		return hero;
	}
	/**
	 * Return all dragons present in game board
	 * @return LinkedList < Dragon > dragons;
	 */
	public LinkedList<Dragon> getDragons() {
		return dragons;
	}
	/**
	 * Return Sword present in the game
	 * @return Sword
	 */
	public Sword getSword() {
		return sword;
	}
	/**
	 * Return exit available in the game
	 * @return Point exit
	 */
	public Point getExit() {
		return exit;
	}
	/**
	 * Returns what is present in some specific position in game board
	 * @param p Position  on the game board
	 * @return char 
	 */
	public char getBoardSymbol(Point p) {
		// x representa as col unas da matriz e y as linhas
		return board[p.getY()][p.getX()];
	}
	/**
	 * Search for a specific symbol in the board.
	 * @param symbol 
	 * @param ignores    number  of occurrences of symbol that will be ignored
	 * @return int position of symbol
	 */
	public Point getPositionSymbol(char symbol, int ignores) {
		Point pos = new Point();
		int ing = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (board[i][j] == symbol) {
					if (ing == ignores) {
						pos.setXY(j, i);
						return pos;
					} else
						ing++;
				}
			}
		}
		return pos;
	}

	/*****************
	 * SETS *
	 *****************/
	/**
	 * Set a new gameBoard
	 * Update Hero, Dragon, Sword and exit position.
	 * @param b new gameboard
	 */
	public void setBoard(char[][] b){
		board = b;
		Point pos;
		// hero
		pos = getPositionSymbol('H', 0);

		if (pos.getX() == 0 && pos.getY() == 0)

		{
			//LANCAR EXCE��O -------------------------------------------- ???
		} 
		else
			hero.setPosition(pos);
		// dragon
		boolean allDragonsPicked = false;
		int picks = 0;
		do {
			pos = getPositionSymbol('D', picks);
			if (pos.getX() == 0 && pos.getY() == 0)
				allDragonsPicked = true;
			else {
				dragons.add(new Dragon(new Point(pos.getX(), pos.getY())));
				picks++;
			}
		} while (!allDragonsPicked);
		if (picks == 0) {
			// ERRO

		}
		// sword
		pos = getPositionSymbol('E', 0);
		if (pos.getX() == 0 && pos.getY() == 0) {
			// FALTA lancar exception
		} else
			sword.setPosition(pos);

		// exit
		exit.setXY(getPositionSymbol('S', 0).getX(), getPositionSymbol('S', 0).getY());
	}
	/**
	 * Change dragons behaviour.
	 * Modes:
	 * 'P' - Paralysed mode, dragons don't move and don't sleep
	 * 'S' - Dragons move 
	 * @param dragon_MODE
	 */
	public void setDragonsBehaviour(char dragon_MODE) {
		for (int i = 0; i < dragons.size(); i++) {
			Dragon dragon = dragons.get(i);
			if (dragon_MODE == 'P') {
				dragon.setParalysedMode(true);
				dragon.setSleepMode(false);
			} else if (dragon_MODE == 'S') {
				dragon.setSleepMode(true);
				dragon.setParalysedMode(false);
			}
		}
	}

	/*****************
	 * MOVEMENTS *
	 *****************/

	public void heroNextPosition(Point new_pos) {
		Point ini_pos = hero.getPosition();
		if (!betweenBoardLimits(new_pos)) // just in case ...
			return;
		//wall or sleeping dragon
		else if (getBoardSymbol(new_pos) == 'X' || (getBoardSymbol(new_pos) == 'd' && !sword.inUse()) || getBoardSymbol(new_pos) == 'S')
			return;
		//sword
		else if (getBoardSymbol(new_pos) == 'E') {
			hero.equipArmor();
			sword.setUse(true);
		}
		cleanPosition(ini_pos);
		hero.setPosition(new_pos);
		heroDragonsCollision();
	}

	public void moveHero(char direction) {

		Point new_pos = new Point();

		switch (direction) {
		case 'a': // esquerda
		{
			new_pos.setXY(hero.getX() - 1, hero.getY());
			break;
		}
		case 's': // baixo
		{
			new_pos.setXY(hero.getX(), hero.getY() + 1);
			break;
		}
		case 'd': // direita
		{
			new_pos.setXY(hero.getX() + 1, hero.getY());
			break;
		}
		case 'w': // cima
		{
			new_pos.setXY(hero.getX(), hero.getY() - 1);
			break;
		}
		default:
			return;
		}
		heroNextPosition(new_pos);
		placeOnBoard(hero.getPosition(), hero.getSymbol());
	}

	private boolean dragonNextPosition(Point new_pos, Dragon dragon) {
		Point ini_pos = dragon.getPosition();
		if (!betweenBoardLimits(new_pos)) // just in case
			return false;

		// if it collides with a wall, exit, other dragon in sleep or awake mode
		// returns false;
		if (getBoardSymbol(new_pos) == 'X' || getBoardSymbol(new_pos) == 'S' || getBoardSymbol(new_pos) == 'D'
				|| getBoardSymbol(new_pos) == 'd')
			return false;

		else if (getBoardSymbol(new_pos) == 'E')
			dragon.setSymbol('F');
		else
			dragon.setSymbol('D'); // just in case ...
		cleanPosition(ini_pos);
		dragon.setPosition(new_pos);
		heroDragonsCollision();
		return true;
	}

	public boolean moveDragon(int d, Dragon dragon) {
		/*
		 * d - 0 down d - 1 up d - 2 left d - 3 right
		 */
		if (dragon.getParalysedMode())
			return false;

		Point new_pos = new Point();
		boolean move = false;

		switch (d) {
		case 0:// down
			new_pos.setXY(dragon.getX(), dragon.getY() + 1);
			move = dragonNextPosition(new_pos, dragon);
			break;
		case 1:// up
			new_pos.setXY(dragon.getX(), dragon.getY() - 1);
			move = dragonNextPosition(new_pos, dragon);
			break;
		case 2:// left
			new_pos.setXY(dragon.getX() - 1, dragon.getY());
			move = dragonNextPosition(new_pos, dragon);
			break;
		case 3:// right
			new_pos.setXY(dragon.getX() + 1, dragon.getY());
			move = dragonNextPosition(new_pos, dragon);
			break;
		case 4:
			move = true;// Sleep
			dragon.setAwake(false);
			break;
		}
		if(move)
			placeOnBoard(dragon.getPosition(), dragon.getSymbol());
		return move;
	}

	public void moveRandomDragons() {
		int mov;
		for (int i = 0; i < dragons.size(); i++) {
			Dragon dragon = dragons.get(i);
			if (dragon.getParalysedMode())
				return;
			boolean move = false;
			do {
				if (dragon.getSleepMode())
					mov = rnd.nextInt(5);
				else
					mov = rnd.nextInt() % 4;
				move = moveDragon(mov, dragon);
			} while (!move);
		}
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

	// for tests
	public boolean betweenBoardLimits(Point p) {
		if (p.getX() >= board.length || p.getY() >= board.length || p.getX() < 0 || p.getY() <0)
			return false;
		else
			return true;
	}

	public boolean heroWins() {
		int dist_x = Math.abs(hero.getPosition().getX() - exit.getX());
		int dist_y = Math.abs(hero.getPosition().getY() - exit.getY());
		if ((dist_x == 0 && dist_y == 1 || dist_x == 1 && dist_y == 0) && dragonsAllDead())
			return true;
		else
			return false;
	}

	public boolean dragonsAllDead() {
		for (int i = 0; i < dragons.size(); i++) {
			if (dragons.get(i).isAlive())
				return false;
		}
		return true;
	}

	/*****************
	 * OTHERS *
	 *****************/

	public void updateBoard() {
		//if there wasn't a hero or dragon movements
		heroDragonsCollision();
		// update exit
		placeOnBoard(exit, 'S');
		// update sword
		if (!sword.inUse())
			placeOnBoard(sword.getPosition(), sword.getSymbol());
		for (int i = 0; i < dragons.size(); i++) {
			placeOnBoard(dragons.get(i).getPosition(), dragons.get(i).getSymbol());
		}
		placeOnBoard(hero.getPosition(),hero.getSymbol());
		heroDragonsCollision();
	}

	/**
	 * Clean a board position
	 * @param p
	 */
	public void cleanPosition(Point p) {
		placeOnBoard(p, ' ');
	}

	/**
	 * Place on game board in a specific point a symbol
	 * @param position 
	 * @param symbol
	 */
	public void placeOnBoard(Point position, char symbol) {
		board[position.getY()][position.getX()] = symbol;
	}

	/**
	 * Checks if Hero and all Dragons are colliding and treats collision
	 */
	public void heroDragonsCollision() {
		for (int i = 0; i < dragons.size(); i++) {
			Dragon dragon = dragons.get(i);
			int dist_x = Math.abs(hero.getPosition().getX() - dragon.getPosition().getX());
			int dist_y = Math.abs(hero.getPosition().getY() - dragon.getPosition().getY());
			if (dist_x == 0 && dist_y == 1 || dist_x == 1 && dist_y == 0) {
				if (hero.getSymbol() == 'H' && (dragon.getSymbol() == 'D')){
					hero.setAlive(false);
					cleanPosition(hero.getPosition());
				}
				if (hero.getSymbol() == 'A') {
					cleanPosition(dragon.getPosition());
					dragon.setAlive(false);
					dragons.remove(i);
					i--;
				}
			}
		}

	}

	@Override
	public String toString() {
		String s = new String();
		for (int j = 0; j < board.length; j++) {

			for (int i = 0; i < board.length; i++)
				s += board[j][i] + "";
			s += "\n";
		}
		return s;
	}
}
