package maze.cli;

public class Hero extends Point {

	private char symbol = 'H';
	private boolean alive;

	public Hero() {
		this.setXY(1, 1);
		alive = true;
	}

	public Hero(Point p) {
		this.setPosition(p);
		symbol = 'H';
		alive = true;
	}

	public void setPosition(Point p) {
		this.setXY(p.getX(), p.getY());
	}

	public void setSymbol(char new_symbol) {
		symbol = new_symbol;
	}

	public void setAlive(boolean a) {
		alive = a;
	}

	public boolean isAlive(){
		return alive;
	}

	public Point getPosition() {
		return (Point) this;
	}

	public char getSymbol() {
		return symbol;
	}

	public void equipArmor() {
		setSymbol('A');
	}
}
