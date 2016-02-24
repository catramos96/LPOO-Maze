import java.util.Scanner;

public class Jogo {
	public static void main(String[] args) {
		Tabuleiro t = new Tabuleiro();
		t.fillTab();
		char d;
		Scanner s = new Scanner(System.in);

		// Enquanto não chegar a saida
		while (t.saidaTabuleiro()) {
			t.displayTab();
			System.out.print("\n");
			d = s.nextLine().charAt(0); // primeiro char
			t.moverHeroi(d);
			t.displayTab();
		}

		s.close();
	}
}
