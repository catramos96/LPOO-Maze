package maze.logi;

import static org.junit.Assert.assertEquals;

import java.security.InvalidParameterException;
import java.util.LinkedList;
import java.util.Random;
/**
 * Class that represents the game's board.
 * It is composed by a two-dimensional matrix of chars and its elements: dragons, sword, hero and exit.
 * The position of the elements with the method getPosition() corresponds to the char[y][x] position.
 */
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
	 * @param b char [][]
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
	 * @return hero Object type hero
	 */
	public Hero getHero() {
		return hero;
	}
	/**
	 * Return all dragons present in game board
	 * @return LinkedList  dragons;
	 */
	public LinkedList<Dragon> getDragons() {
		return dragons;
	}
	public void addDragon(Point position){
		Dragon d = new Dragon(position);
		dragons.add(d);
	}
	/**
	 * Return Sword present in the game
	 * @return Sword Object type sword
	 */
	public Sword getSword() {
		return sword;
	}
	/**
	 * Return exit available in the game
	 * @return Point exit of board
	 */
	public Point getExit() {
		return exit;
	}
	/**
	 * Returns what is present in some specific position in game board
	 * @param p Position  on the game board
	 * @return char board position
	 */
	public char getBoardSymbol(Point p) {
		// x representa as col unas da matriz e y as linhas
		return board[p.getY()][p.getX()];
	}
	/**
	 * Search for a specific symbol in the board.
	 * @param symbol 	 symbol placed on board
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

		//  For every element of the board checks its position

		// hero
		pos = getPositionSymbol('H', 0);
		if (pos.getX() == 0 && pos.getY() == 0){
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
		}

		// sword
		pos = getPositionSymbol('E', 0);
		if (pos.getX() == 0 && pos.getY() == 0) {
		} else
			sword.setPosition(pos);

		// exit
		exit.setXY(getPositionSymbol('S', 0).getX(), getPositionSymbol('S', 0).getY());
	}
	/**
	 * Change the dragons behavior.
	 * @param dragon_MODE 'P' - Paralyzed mode, dragons don't move and don't sleep 'S' - Dragons move
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
	/**
	 * Evaluates next hero position. If is a valid move Hero moves to next position otherwise hero stays in same position.
	 * @param new_pos new hero position
	 */
	public void heroNextPosition(Point new_pos) {
		Point ini_pos = hero.getPosition();

		//NextPosition = Wall, sleeping dragon or exit(and dragons are alive)
		if (getBoardSymbol(new_pos) == 'X' || (getBoardSymbol(new_pos) == 'd' && !sword.inUse()) || (getBoardSymbol(new_pos) == 'S' && !dragonsAllDead()))
			return;
		//NextPosition = sword
		else if (getBoardSymbol(new_pos) == 'E') {
			hero.equipArmor();
			sword.setUse(true);
		}
		cleanPosition(ini_pos);
		hero.setPosition(new_pos);
	}

	/**
	 * Moves hero in a direction if possible.
	 * @param direction 
	 * char a - Left
	 * char d - Right
	 * char w - Up
	 * char s - Down
	 */
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
		updateBoard(); 
	}

	/**
	 * Evaluates next dragon position
	 * @param new_pos
	 * @param dragon
	 * @return boolean
	 */
	private boolean dragonNextPosition(Point new_pos, Dragon dragon) {
		Point ini_pos = dragon.getPosition();

		//To view if the previous moved dragons are colliding with the actual one

		//NextPosition = Wall, exit or dragon
		if (getBoardSymbol(new_pos) == 'X' || getBoardSymbol(new_pos) == 'S' || getBoardSymbol(new_pos) == 'D' || getBoardSymbol(new_pos) =='d')
			return false;

		//NextPosition = sword
		else if (getBoardSymbol(new_pos) == 'E')
			dragon.setSymbol('F');
		//Just in case the last position was the sword
		else
			dragon.setSymbol('D');
		cleanPosition(ini_pos);
		dragon.setPosition(new_pos);
		return true;
	}

	/**
	 * Move dragon in a random direction.
	 * If in sleep mode, dragon can start sleeping.
	 * @param d int type
	 * @param dragon   Object type dragon
	 * @return boolean
	 */
	public boolean moveDragon(int d, Dragon dragon) {
		/*
		 * d - 0 down d - 1 up d - 2 left d - 3 right
		 */
		if (dragon.getParalysedMode())
			return false;

		Point new_pos = new Point();
		boolean move = false;

		//Random Move
		switch (d) {
		case 0:// down
			new_pos.setXY(dragon.getX(), dragon.getY() + 1);
			break;
		case 1:// up
			new_pos.setXY(dragon.getX(), dragon.getY() - 1);
			break;
		case 2:// left
			new_pos.setXY(dragon.getX() - 1, dragon.getY());
			break;
		case 3:// right
			new_pos.setXY(dragon.getX() + 1, dragon.getY());
			break;
		case 4:
			move = true;// Sleep
			dragon.setAwake(false);
			break;
		}

		move = dragonNextPosition(new_pos, dragon);
		placeOnBoard(dragon.getPosition(), dragon.getSymbol());
		return move;
	}
	/**
	 * Move all dragons in the board randomly.
	 */
	public void moveRandomDragons() {
		int mov;

		//Cleans all dragons on board to check if the new position is a dragon recently moved
		for (int i = 0; i < dragons.size(); i++) {
			cleanPosition(dragons.get(i).getPosition());
		}
		//for each dragon tries to accomplish a move until succeeded
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
	/**
	 * Evaluates  if  game ended.
	 * @return true if game ended otherwise returns false
	 */
	public boolean exitBoard() {

		if (heroWins()) {
			return true;
		} else if (hero.isAlive() == false) {
			return true;
		} else
			return false;
	}
	/**
	 * Check if  hero wins the game.
	 * @return true if hero win otherwise return false
	 */
	public boolean heroWins() {
		if (hero.getPosition().equals(exit) && dragonsAllDead())
			return true;
		else
			return false;
	}

	/**
	 * Check if all dragons are dead
	 * @return true if all dragons dead
	 */
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
	/**
	 *  Update Objects on the board
	 */
	public void updateBoard() {
		//check collision
		heroDragonsCollision();
		// update sword
		if (!sword.inUse())
			placeOnBoard(sword.getPosition(), sword.getSymbol());
		//update dragons (if a dragon is on the same position has the sword it stays visible)
		for (int i = 0; i < dragons.size(); i++) {
			placeOnBoard(dragons.get(i).getPosition(), dragons.get(i).getSymbol());
		}
		//hero
		if(hero.isAlive())
			placeOnBoard(hero.getPosition(),hero.getSymbol());
		// update exit
		placeOnBoard(exit, 'S');
	}

	/**
	 * Clean a board position
	 * @param p Point
	 */
	public void cleanPosition(Point p) {
		placeOnBoard(p, ' ');
	}

	/**
	 * Place on game board in a specific point a symbol
	 * @param position Point
	 * @param symbol char
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
			
			//If the dragon and hero in horizontal or vertical adjacent positions
			if (dist_x == 0 && dist_y <= 1 || dist_x <= 1 && dist_y == 0) {
				//Hero disarmed
				if (hero.getSymbol() == 'H' && (dragon.getSymbol() == 'D')){
					hero.setAlive(false);
					cleanPosition(hero.getPosition());
				}
				//Hero Equipped
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
