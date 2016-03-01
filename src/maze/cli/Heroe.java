package maze.cli;
public class Heroe {
	
private int x;
private int y;
private char symbol;
private boolean alive;

public Heroe(int xi, int yi){
	x = xi;
	y = yi;
	symbol = 'H';
	alive = true;
}
public void setPosition(int new_x,int new_y){
	this.x = new_x;
	this.y = new_y;
}
public void setSymbol(char new_symbol){
	symbol = new_symbol;
}
public void setAlive(boolean a){
	alive = a;
}
public boolean isAlive()
{
	return alive;
}
public int getPositionX(){
	return this.x;
}
public int getPositionY(){
	return this.y;
}
public char getSymbol(){
	return symbol;
}
public void equipArmor(){
	setSymbol('A');
}
}
