package maze.cli;

public class Dragon extends Point {

	private boolean alive = true;
	private char symbol = 'D';

	public Dragon() {
		this.setXY(1,1);
	}

	public Dragon(Point p) {
		this.setPosition(p);
	}
	
	public void setPosition(Point p) {
		this.setXY(p.getX(), p.getY());
	}

	public void setSymbol(char s) {
		symbol = s;
	}

	public void setAlive(boolean l) {
		alive = l;
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

	

}
