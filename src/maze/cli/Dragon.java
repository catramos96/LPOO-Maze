package maze.cli;
public class Dragon {

	private int x;
	private int y;
	private boolean alive = true;
	char symbol = 'D';

	public void setPosition(int xx, int yy)
	{
		x=xx;
		y=yy;
	}
	public void setAlive(boolean a)
	{
		alive = a;
	}
	public int getPositionX()
	{
		return x;
	}
	public int getPositionY()
	{
		return y;
	}
	public boolean isAlive()
	{
		return alive;
	}
	public char getSymbol()
	{
		return symbol;
	}
	public Dragon(int xx,int yy)
	{
		x = xx;
		y = yy;
	}
	
}
