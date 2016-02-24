import java.util.Random;

public class Tabuleiro {
	private char tabuleiro[];
	private Heroi h = new Heroi(1, 1);
	private Dragao dragao = new Dragao(1,3);






	public void fillTab() {
		tabuleiro = new char[] { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', '\n', 'X', ' ', ' ', ' ', ' ', ' ',
				' ', ' ', ' ', 'X', '\n', 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X', '\n', 'X', ' ', 'X', 'X',
				' ', 'X', ' ', 'X', ' ', 'X', '\n', 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X', '\n', 'X', ' ',
				' ', ' ', ' ', ' ', ' ', 'X', ' ', 'X', '\n', 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X', '\n',
				'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X', '\n', 'X', 'A', 'X', 'X', ' ', ' ', ' ', ' ', ' ',
				'X', '\n', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', '\n' };
	}

	public void displayTab() {
		posicionar(9, 5, 'S');
		posicionar(dragao.get_pos_x(),dragao.get_pos_y(),dragao.get_simbolo());
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
			else if (getTabSimbolo(x - 1, y) == 'A')
				h.equiparArmadura();
			h.setPosition(x - 1, y);
			posicionar(x, y, ' ');
			break;
		}
		case 's': // baixo
		{
			if (getTabSimbolo(x, y + 1) == 'X')
				break;
			else if (getTabSimbolo(x, y + 1) == 'A')
				h.equiparArmadura();
			h.setPosition(x, y + 1);
			posicionar(x, y, ' ');
			break;
		}
		case 'd': // direita
		{
			if (getTabSimbolo(x + 1, y) == 'X')
				break;
			else if (getTabSimbolo(x + 1, y) == 'A')
				h.equiparArmadura();
			h.setPosition(x + 1, y);
			posicionar(x, y, ' ');
			break;
		}
		case 'w': // cima
		{
			if (getTabSimbolo(x, y - 1) == 'X')
				break;
			else if (getTabSimbolo(x, y - 1) == 'A')
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
				if (getTabSimbolo(x, y - 1) == 'X')
					break;
				else 
				{
					tabuleiro[x+11*y] = ' ';
					dragao.set_pos(x, y-1);
					move =  true;
				}
			case 1://Sul
				if (getTabSimbolo(x, y + 1) == 'X')
					break;
				else 
				{
					tabuleiro[x+11*y] = ' ';
					dragao.set_pos(x, y+1);
					move =  true;
				}
			case 2://Este
				if (getTabSimbolo(x - 1, y) == 'X')
					break;
				else 
				{
					tabuleiro[x+11*y] = ' ';
					dragao.set_pos(x-1, y);
					move =  true;
				}
			case 3://Oeste
				if (getTabSimbolo(x + 1, y) == 'X')
					break;
				else 
				{
					tabuleiro[x+11*y] = ' ';
					dragao.set_pos(x+1, y);
					move =  true;
				}
			}
		}while(!move);
	}
	public boolean saidaTabuleiro(){
		//adicionar morte do dragão
		if(h.getPositionX() == 9 && h.getPositionY() == 5)
			return true;
		else
			return false;
	}
}
