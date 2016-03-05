package maze.logi;

public class Sword extends Point{
	
private char symbol = 'E';
private boolean state;

public Sword(){
	this.setXY(1, 1);
	state = false;
}

public Sword(int x, int y){
	this.setXY(x, y);
	state = false;
}

public Point getPosition(){
	return (Point)this;
}

public char getSymbol(){
	return symbol;
}

public void setPosition(Point p){
	this.setXY(p.getX(),p.getY());
}
public boolean inUse(){
	return state;
}

public void setUse(boolean b){
	state = b;
}
}
