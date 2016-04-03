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
	 * Default Dragon constructor. Creates a dragon at position x = 1 & y = 1  and awake
	 */
	public Dragon() {
		this.setXY(1, 1);
		alive = true;
		awake = true;
	}

	/**
	 * Creates a Dragon at a determinate point
	 * @param p
	 */
	public Dragon(Point p) {
		this.setPosition(p);
	}

	/*****************
	 * GETS *
	 *****************/

	/**
	 * Returns dragon position
	 * @return
	 */
	public Point getPosition() {
		return (Point) this;
	}

	/**
	 * Returns dragons symbol
	 * @return
	 */
	public char getSymbol() {
		return symbol;
	}
	/**
	 * Return true if dragon is in sleepy mode
	 * @return
	 */
	public boolean getSleepMode() {
		return sleep_mode;
	}
	
	/**
	 * Return true if dragon is in paralysed mode
	 * @return
	 */
	public boolean getParalysedMode() {
		return paralysed_mode;
	}

	/*****************
	 * SETS *
	 *****************/

	/**
	 * Set new dragon position
	 * @param p
	 */
	public void setPosition(Point p) {
		this.setXY(p.getX(), p.getY());
	}

	/**
	 * Set new dragon symbol
	 * @param s
	 */
	public void setSymbol(char s) {
		symbol = s;
	}

	/**
	 * Set Dragon life state
	 * @param l
	 */
	public void setAlive(boolean l) {
		alive = l;
	}

	/**
	 * Enable/Disable Sleep Mode
	 * @param sleep
	 */
	public void setSleepMode(boolean sleep) {
		sleep_mode = sleep;
	}

	/**
	 * Enable/Disable Paralyzed Mode
	 * @param paralysed
	 */
	public void setParalysedMode(boolean paralysed) {
		paralysed_mode = paralysed;
	}
	
	/**
	 * Set dragon state awake or sleepy
	 * @param b
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
	 * @return
	 */
	public boolean isAlive() {
		return alive;
	}
	
	/**
	 * Return true if dragon is awake 
	 * Return false if dragon is sleepy
	 * @return
	 */
	public boolean isAwake() {
		return awake;
	}
}
