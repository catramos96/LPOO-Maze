package maze.logi;

/**
 * Class that defines coordinates (X,Y).
 */
public class Point {

	/**************************************** ATTRIBUTES */

	private int x;
	private int y;

	/**************************************** FUNCTIONS */

	/*****************
	 * CONSTRUCTOR *
	 *****************/

	/**
	 * Default constructor for class point.
	 * Initialize coordinate x and y at value 0.
	 */
	public Point() {
		x = 0;
		y = 0;
	};

	/**
	 * Constructor for class point.
	 * Initialize coordinate x and y at desired values.
	 * @param xi   Initial value of coordinate x
	 * @param yi   Initial value of coordinate y
	 */
	public Point(int xi, int yi) {
		x = xi;
		y = yi;
	}

	/*****************
	 * GETS *
	 *****************/

	/**
	 * Returns  coordinate x of that point
	 * @return coordinate x
	 */
	public int getX() {
		return x;
	}

	/**
	 * Returns  coordinate y of that point
	 * @return coordinate y
	 */
	public int getY() {
		return y;
	}

	/*****************
	 * SETS *
	 *****************/

	/**
	 * Set a new position
	 * @param new_x	 	new value of x
	 * @param new_y 	new value of y 
	 */
	public void setXY(int new_x, int new_y) {
		x = new_x;
		y = new_y;
	}


	/**
	 * Set a new value to x
	 * @param new_x new value of x
	 */
	public void setX(int new_x) {
		x = new_x;
	}

	/**
	 * Set a new value of Y
	 * @param new_y int
	 */
	public void setY(int new_y) {
		y = new_y;
	}

	/**
	 * Only compares Point to Point, other type of object will return false
	 */
	@Override
	public boolean equals(Object p) {
		if(p instanceof Point)
			if (((Point) p).getX() == x && ((Point) p).getY() == y)
				return true;
			else
				return false;
		
		return false;

	}
}
