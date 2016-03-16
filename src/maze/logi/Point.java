package maze.logi;

public class Point {

	/**************************************** ATTRIBUTES */

	private int x;
	private int y;

	/**************************************** FUNCTIONS */

	/*****************
	 * CONSTRUCTOR *
	 *****************/

	public Point() {
		x = 0;
		y = 0;
	};

	public Point(int xi, int yi) {
		x = xi;
		y = yi;
	}

	/*****************
	 * GETS *
	 *****************/

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	/*****************
	 * SETS *
	 *****************/

	public void setXY(int new_x, int new_y) {
		x = new_x;
		y = new_y;
	}

	public void setX(int new_x) {
		x = new_x;
	}

	public void setY(int new_y) {
		y = new_y;
	}

	@Override
	public boolean equals(Object p) {
		if (((Point) p).getX() == x && ((Point) p).getY() == y)
			return true;
		else
			return false;
	}
}
