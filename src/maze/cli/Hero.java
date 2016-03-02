package maze.cli;

public class Hero extends Point {

	private char symbol = 'H';
	private boolean alive;

	public Hero() {
		this.setXY(0, 0);
	}

	public Hero(int xi, int yi) {
		this.setXY(xi, yi);
		symbol = 'H';
		alive = true;
	}

	public void setPosition(int new_x, int new_y) {
		this.setXY(new_x, new_y);
	}

	public void setSymbol(char new_symbol) {
		symbol = new_symbol;
	}

	public void setAlive(boolean a) {
		alive = a;
	}

	public boolean isAlive()

	{
		return alive;
	}

	public Point getPosition() {
		return (Point)this;
	}

	public char getSymbol() {
		return symbol;
	}

	public void equipArmor() {
		setSymbol('A');
	}
}
