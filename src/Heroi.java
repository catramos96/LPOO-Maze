
public class Heroi {
	
private int x;
private int y;
private char simbolo;

public Heroi(int xi, int yi){
	x = xi;
	y = yi;
	simbolo = 'H';
}

public void setPosition(int novox,int novoy){
	this.x = novox;
	this.y = novoy;
}

public void setSimbolo(char novoSimbolo){
	simbolo = novoSimbolo;
}

public int getPositionX(){
	return this.x;
}

public int getPositionY(){
	return this.y;
}

public char getSimbolo(){
	return simbolo;
}

public void equiparArmadura(){
	setSimbolo('A');
}
}
