
public class Heroi {
	
private int x;
private int y;
private char simbolo;
private boolean vivo;

public Heroi(int xi, int yi){
	x = xi;
	y = yi;
	simbolo = 'H';
	vivo = true;
}

public void setPosition(int novox,int novoy){
	this.x = novox;
	this.y = novoy;
}

public void setSimbolo(char novoSimbolo){
	simbolo = novoSimbolo;
}

public void setVida(boolean v){
	vivo = v;
}
public boolean getVida()
{
	return vivo;
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
