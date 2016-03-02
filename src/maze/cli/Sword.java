package maze.cli;

public class Sword extends Point{
	
private char symbol = 'E';
private boolean state;

public Sword(){
	this.setXY(0, 0);
	state = false;
}

public Sword(int x, int y){
	this.setXY(x, y);
	state = false;
}

public Point getPosition(){
	return (Point)this;
}
public void setPosition(int new_x,int new_y){
	this.setXY(new_x, new_y);
}
public boolean inUse(){
	return state;
}

public void setState(boolean b){
	state = b;
}
}
