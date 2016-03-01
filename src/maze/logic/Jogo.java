package maze.logic;
import java.util.Scanner;
import maze.cli.Tabuleiro;

public class Jogo {
	public static void main(String[] args) {
		Tabuleiro t = new Tabuleiro();
		char d = 'd';
		Scanner s = new Scanner(System.in);

		t.fillTab();
		t.displayTab();

		do{			
			if(s.hasNextLine())
			{
				d = s.next().charAt(0); // primeiro char
				t.moverHeroi(d);
				t.moverDragao();
				t.Heroi_Dragao_colisao();
				t.displayTab();
			}

		}while(!t.saidaTabuleiro());// Enquanto nao chegar a saida
		s.close();
	}
}
