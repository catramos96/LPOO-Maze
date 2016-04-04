package maze.logi;

public class Dragon extends Point {

	/**************************************** ATTRIBUTES */
	
	private boolean alive = true;
	private boolean awake = true;
	private boolean sleep_mode = false;
	private boolean paralysed_mode = false;
	private char symbol = 'D';

	/**************************************** FUNCTIONS */
	
	/*****************
	 * CONSTRUCTOR *
	 *****************/
	/**
	 * Default Dragon constructor. Creates a dragon at position (1,1)  and awake
	 */
	public Dragon() {
		this.setXY(1, 1);
		alive = true;
		awake = true;
	}

	/**
	 * Creates a Dragon at a determinate point
	 * @param p Point
	 */
	public Dragon(Point p) {
		this.setPosition(p);
	}

	/*****************
	 * GETS *
	 *****************/

	/**
	 * Returns dragon position
	 * @return Point
	 */
	public Point getPosition() {
		return (Point) this;
	}

	/**
	 * Returns dragons symbol
	 * @return symbol char
	 */
	public char getSymbol() {
		return symbol;
	}
	/**
	 * Return true if dragon is in sleepy mode
	 * @return boolean
	 */
	public boolean getSleepMode() {
		return sleep_mode;
	}
	
	/**
	 * Return true if dragon is in paralyzed mode
	 * @return  boolean
	 */
	public boolean getParalysedMode() {
		return paralysed_mode;
	}

	/*****************
	 * SETS *
	 *****************/

	/**
	 * Set new dragon position
	 * @param p Point
	 */
	public void setPosition(Point p) {
		this.setXY(p.getX(), p.getY());
	}

	/**
	 * Set new dragon symbol
	 * @param s char
	 */
	public void setSymbol(char s) {
		symbol = s;
	}

	/**
	 * Set Dragon life state
	 * @param l boolean
	 */
	public void setAlive(boolean l) {
		alive = l;
	}

	/**
	 * Enable/Disable Sleep Mode
	 * @param sleep boolean
	 */
	public void setSleepMode(boolean sleep) {
		sleep_mode = sleep;
	}

	/**
	 * Enable/Disable Paralyzed Mode
	 * @param paralysed boolean
	 */
	public void setParalysedMode(boolean paralysed) {
		paralysed_mode = paralysed;
	}
	
	/**
	 * Set dragon state awake or sleepy
	 * @param b boolean
	 */
	public void setAwake(boolean b){
		awake = b;
		if(b)
			symbol = 'D';
		else
			symbol = 'd';
	}

	/*****************
	 * BOOLEAN *
	 *****************/

	/**
	 * Return life state
	 * @return boolean
	 */
	public boolean isAlive() {
		return alive;
	}
	
	/**
	 * Return true if dragon is awake 
	 * Return false if dragon is sleepy
	 * @return boolean
	 */
	public boolean isAwake() {
		return awake;
	}
}
