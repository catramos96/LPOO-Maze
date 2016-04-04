package maze.logi;

/**
 * Class that represents the element of the game Sword.
 * A sword can be in use (state = true) or not.
 * His default symbol is 'E'.
 */
public class Sword extends Point {

	/**************************************** ATTRIBUTES */

	private char symbol = 'E';
	private boolean state;

	/**************************************** FUNCTIONS */

	/*****************
	 * CONSTRUCTOR *
	 *****************/
    /**
     * Default constructor.
     * Initialize coordinate x and y at value 1.
     */
	public Sword() {
		this.setXY(1, 1);
		state = false;
	}
	/**
	 * Constructor  of class Sword with specific coordinates
	 * @param x Initial value of x coordinate
	 * @param y Initial value of y coordinate
	 */
	public Sword(int x, int y) {
		this.setXY(x, y);
		state = false;
	}

	/*****************
	 * GETS *
	 *****************/

	/**
	 * Return a Point which contains sword position
	 * @return Point Contains x and y coordinates.
	 */
	public Point getPosition() {
		return (Point) this;
	}

	/**
	 * Return  char that represents the Sword
	 * @return char symbol
	 */
	public char getSymbol() {
		return symbol;
	}

	/*****************
	 * SETS *
	 *****************/
	
	
	/**
	 * Set new sword position  with  the Point passed in argument
	 * 
	 * @param p  new position
	 */
	public void setPosition(Point p) {
		this.setXY(p.getX(), p.getY());
	}

	
	/**
	 * Set sword state, if it is equipped  or not
	 * 
	 * @param equipped  Sword state
	 */
	public void setUse(boolean equipped) {
		state = equipped;
	}

	/*****************
	 * BOOLEAN *
	 *****************/

	/**
	 * Return current state of sword, if is equipped or not
	 * @return boolean state
	 */
	public boolean inUse() {
		return state;
	}
}
