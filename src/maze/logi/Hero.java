package maze.logi;

public class Hero extends Point {
	
	/**************************************** ATTRIBUTES */
	
	private char symbol = 'H';
	private boolean alive;

	/**************************************** FUNCTIONS */
	
	/*****************
	 * CONSTRUCTOR *
	 *****************/

	public Hero() {
		this.setXY(1, 1);
		alive = true;
	}

	public Hero(Point p) {
		this.setPosition(p);
		symbol = 'H';
		alive = true;
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

	public void setSymbol(char new_symbol) {
		symbol = new_symbol;
	}

	public void setAlive(boolean a) {
		alive = a;
	}

	/*****************
	 * BOOLEAN *
	 *****************/
	
	public boolean isAlive() {
		return alive;
	}
	
	/*****************
	 * OTHERS *
	 *****************/

	public void equipArmor() {
		setSymbol('A');
	}
}
