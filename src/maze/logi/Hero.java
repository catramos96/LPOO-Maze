package maze.logi;

public class Hero extends Point {
	
	/**************************************** ATTRIBUTES */
	
	private char symbol = 'H';
	private boolean alive;

	/**************************************** FUNCTIONS */
	
	/*****************
	 * CONSTRUCTOR *
	 *****************/
/**
 * Default constructor 
 * Initialize coordinate x and y at value 1 
 */
	public Hero() {
		this.setXY(1, 1);
		alive = true;
	}
/**
 * Constructor of class Hero receives a object of type Point and initialize
 * Hero with that Point
 * @param position
 */
	public Hero(Point position) {
		this.setPosition(position);
		symbol = 'H';
		alive = true;
	}

	/*****************
	 * GETS *
	 *****************/
	/**
	 * Return object of class Point that contains  Hero position 
	 * @return Point
	 */
	public Point getPosition() {
		return (Point) this;
	}

	/**
	 * Return the symbol that represents Hero 
	 * @return char symbol
	 */
	public char getSymbol() {
		return symbol;
	}

	/*****************
	 * SETS *
	 *****************/
	
	/**
	 * Set new hero position. 
	 * @param position 
	 */
	public void setPosition(Point position) {
		this.setXY(position.getX(), position.getY());
	}

	/** Set new hero char representation
	 * 
	 * @param new_symbol
	 */
	public void setSymbol(char new_symbol) {
		symbol = new_symbol;
	}

	/** 
	 * Set new hero life status.
	 * Life equals False if is dead.
	 * Life equals True  if is alive.
	 * @param live
	 */
	public void setAlive(boolean a) {
		alive = a;
	}

	/*****************
	 * BOOLEAN *
	 *****************/
	
	/**
	 * Return hero life status.
	 * @return life status
	 */
	public boolean isAlive() {
		return alive;
	}
	
	/*****************
	 * OTHERS *
	 *****************/
	/**
	 * Equips hero with a armour
	 */
	public void equipArmor() {
		setSymbol('A');
	}
}
