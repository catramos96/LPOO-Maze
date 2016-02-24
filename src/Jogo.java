import java.util.NoSuchElementException;
import java.util.Scanner;

public class Jogo {
	public static void main(String[] args) {
		Tabuleiro t = new Tabuleiro();
		char d;
		Scanner s = new Scanner(System.in);

		t.fillTab();
		t.displayTab();
		
		do{
			d = 'K';
			//System.out.print("\n");
			
			try
			{
				d = s.nextLine().charAt(0); // primeiro char
			}
			catch(NoSuchElementException ex)
			{
				//pode rebentar na linha a seguir,corrigir
				System.out.println("Por favor introduza uma direção ");
				continue;
			}
			if(d=='K')continue;
			t.moverHeroi(d);
			t.moverDragao();
			t.displayTab();

		}while(!t.saidaTabuleiro());// Enquanto não chegar a saida
		s.close();
	}
}
