import java.util.Random;

public class Tabuleiro {
	private char tabuleiro[];
	private Heroi h = new Heroi(1, 1);
	private Dragao dragao = new Dragao(1,4);






	public void fillTab() {
		tabuleiro = new char[] { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', '\n', 'X', ' ', ' ', ' ', ' ', ' ',
				' ', ' ', ' ', 'X', '\n', 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X', '\n', 'X', ' ', 'X', 'X',
				' ', 'X', ' ', 'X', ' ', 'X', '\n', 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X', '\n', 'X', ' ',
				' ', ' ', ' ', ' ', ' ', 'X', ' ', 'X', '\n', 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X', '\n',
				'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X', '\n', 'X', 'E', 'X', 'X', ' ', ' ', ' ', ' ', ' ',
				'X', '\n', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', '\n' };
	}

	public void displayTab() {
		posicionar(9, 5, 'S');
		if(dragao.get_vida() == true)
		posicionar(dragao.get_pos_x(),dragao.get_pos_y(),dragao.get_simbolo());
		if(h.getVida() == true)
		posicionar(h.getPositionX(), h.getPositionY(), h.getSimbolo());
		System.out.print(tabuleiro);
	}

	public char getTabSimbolo(int x, int y) {
		return tabuleiro[x + 11 * y];
	}

	public void posicionar(int x, int y, char rep) {
		tabuleiro[x + 11 * y] = rep;
	}

	public void moverHeroi(char direccao) {
		int x = h.getPositionX();
		int y = h.getPositionY();

		switch (direccao) {
		case 'a': // esquerda
		{
			if (getTabSimbolo(x - 1, y) == 'X')
				break;
			else if (getTabSimbolo(x - 1, y) == 'E')
				h.equiparArmadura();
			h.setPosition(x - 1, y);
			posicionar(x, y, ' ');
			break;
		}
		case 's': // baixo
		{
			if (getTabSimbolo(x, y + 1) == 'X')
				break;
			else if (getTabSimbolo(x, y + 1) == 'E')
				h.equiparArmadura();
			h.setPosition(x, y + 1);
			posicionar(x, y, ' ');
			break;
		}
		case 'd': // direita
		{
			if (getTabSimbolo(x + 1, y) == 'X')
				break;
			else if (getTabSimbolo(x + 1, y) == 'E')
				h.equiparArmadura();
			h.setPosition(x + 1, y);
			posicionar(x, y, ' ');
			break;
		}
		case 'w': // cima
		{
			if (getTabSimbolo(x, y - 1) == 'X')
				break;
			else if (getTabSimbolo(x, y - 1) == 'E')
				h.equiparArmadura();
			h.setPosition(x, y - 1);
			posicionar(x, y, ' ');
			break;
		}

		default:
			break;
		}
	}

	public void moverDragao()
	{
		Random rn = new Random();
		int x = dragao.get_pos_x();
		int y = dragao.get_pos_y();
		boolean move = false;
		do
		{
			int mov = rn.nextInt() %4;
			switch(mov)
			{
			case 0://Norte
				if (getTabSimbolo(x, y - 1) == 'X' || getTabSimbolo(x, y - 1) == 'E')
					break;
				else 
				{
					System.out.println("NORTE");
					tabuleiro[x+11*y] = ' ';
					dragao.set_pos(x, y-1);
					move =  true;
				}
				break;
			case 1://Sul
				if (getTabSimbolo(x, y + 1) == 'X' || getTabSimbolo(x, y + 1) == 'E')
					break;
				else 
				{
					System.out.println("SUL");
					tabuleiro[x+11*y] = ' ';
					dragao.set_pos(x, y+1);
					move =  true;
				}
				break;
			case 2://Este
				if (getTabSimbolo(x - 1, y) == 'X' || getTabSimbolo(x - 1, y) == 'E')
					break;
				else 
				{
					System.out.println("ESTE");
					tabuleiro[x+11*y] = ' ';
					dragao.set_pos(x-1, y);
					move =  true;
				}
				break;
			case 3://Oeste
				if (getTabSimbolo(x + 1, y) == 'X' || getTabSimbolo(x + 1, y) == 'E')
					break;
				else 
				{
					System.out.println("OESTE");
					tabuleiro[x+11*y] = ' ';
					dragao.set_pos(x+1, y);
					move =  true;
				}
				break;
			}
		}while(!move);
	}

	public void Heroi_Dragao_colisao()
	{		
		int dist_x =Math.abs( h.getPositionX() - dragao.get_pos_x());
		int dist_y =Math.abs (h.getPositionY() - dragao.get_pos_y());
		int dist = (int) Math.sqrt(dist_y*dist_y + dist_x*dist_x);
		int emlinha= dist_x + dist_y ;//ANULAR A dist em diagonal
		if((dist == 0 || dist ==1)&& (emlinha== 1 || emlinha == 0) )
			if(h.getSimbolo()== 'H')
				h.setVida(false);
			else if(h.getSimbolo() == 'A')
				dragao.set_vida(false);
	}




	public boolean saidaTabuleiro(){
		
		if(h.getPositionX() == 9 && h.getPositionY() == 5 && dragao.get_vida() == false)
		{
			System.out.println("O guerreiro ganhou!");
			return true;
		}
		else if(h.getVida() == false)
		{
			System.out.println("O dragao ganhou");
			return true;
		}
		else
			return false;
		
	}
}
