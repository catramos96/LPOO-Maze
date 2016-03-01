package maze.cli;
public class Dragao {

	private int x;
	private int y;
	private boolean vivo = true;
	char simbolo = 'D';

	public void set_pos(int xx, int yy)
	{
		x=xx;
		y=yy;
	}
	public void set_vida(boolean a)
	{
		vivo = a;
	}
	public int get_pos_x()
	{
		return x;
	}
	public int get_pos_y()
	{
		return y;
	}
	public boolean get_vida()
	{
		return vivo;
	}
	public char get_simbolo()
	{
		return simbolo;
	}
	public Dragao(int xx,int yy)
	{
		x = xx;
		y = yy;
	}
	
}
