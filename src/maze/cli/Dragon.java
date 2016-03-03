package maze.cli;

public class Dragon extends Point {

	private boolean alive = true;
	private char symbol = 'D';

	public Dragon() {
		this.setPosition(1, 1);
	}

	public void setPosition(int xi, int yi) {
		this.setXY(xi, yi);
	}

	public void setSymbol(char mark) {
		symbol = mark;
	}

	public void setAlive(boolean live) {
		alive = live;
	}

	public Point getPosition() {
		return (Point)this;
	}

	public boolean isAlive() {
		return alive;
	}

	public char getSymbol() {
		return symbol;
	}

	public Dragon(int new_x, int new_y) {
		this.setPosition(new_x, new_y);
	}

}
