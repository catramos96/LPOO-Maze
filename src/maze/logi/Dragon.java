package maze.logi;

public class Dragon extends Point {

	/**************************************** ATTRIBUTES */
	
	private boolean alive = true;
	private boolean sleep_mode = false;
	private boolean paralysed_mode = false;
	private char symbol = 'D';

	/**************************************** FUNCTIONS */
	
	/*****************
	 * CONSTRUCTOR *
	 *****************/

	public Dragon() {
		this.setXY(1, 1);
		alive = true;
	}

	public Dragon(Point p) {
		this.setPosition(p);
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

	public boolean getSleepMode() {
		return sleep_mode;
	}

	public boolean getParalysedMode() {
		return paralysed_mode;
	}

	/*****************
	 * SETS *
	 *****************/

	public void setPosition(Point p) {
		this.setXY(p.getX(), p.getY());
	}

	public void setSymbol(char s) {
		symbol = s;
	}

	public void setAlive(boolean l) {
		alive = l;
	}

	public void setSleepMode(boolean sleep) {
		sleep_mode = sleep;
	}

	public void setParalysedMode(boolean paralysed) {
		paralysed_mode = paralysed;
	}

	/*****************
	 * BOOLEAN *
	 *****************/

	public boolean isAlive() {
		return alive;
	}
}
