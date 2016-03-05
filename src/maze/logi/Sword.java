package maze.logi;

public class Sword extends Point {

	/**************************************** ATTRIBUTES */

	private char symbol = 'E';
	private boolean state;

	/**************************************** FUNCTIONS */

	/*****************
	 * CONSTRUCTOR *
	 *****************/

	public Sword() {
		this.setXY(1, 1);
		state = false;
	}

	public Sword(int x, int y) {
		this.setXY(x, y);
		state = false;
	}

	/*****************
	 * GETS *
	 *****************/

	public Point getPosition() {
		return (Point) this;
	}

	public char getSymbol() {
		return symbol;
	}

	/*****************
	 * SETS *
	 *****************/

	public void setPosition(Point p) {
		this.setXY(p.getX(), p.getY());
	}

	public void setUse(boolean b) {
		state = b;
	}

	/*****************
	 * BOOLEAN *
	 *****************/

	public boolean inUse() {
		return state;
	}
}
