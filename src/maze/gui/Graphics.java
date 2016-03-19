package maze.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Spring;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.util.Vector;
import java.awt.event.ItemEvent;
import maze.logi.*;
import javax.swing.UIManager;

public class Graphics {

	private JFrame frmJogoDoLabirinto;
	private JTextField mazeSize;
	private JTextField numberOfDragons;
	private Board board = new Board();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Graphics window = new Graphics();
					window.frmJogoDoLabirinto.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Graphics() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize() {
		frmJogoDoLabirinto = new JFrame();
		frmJogoDoLabirinto.setTitle("Jogo do Labirinto");
		frmJogoDoLabirinto.setBounds(100, 100, 611, 532);
		frmJogoDoLabirinto.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmJogoDoLabirinto.getContentPane().setLayout(null);

		/***************** AREA DISPLAY MAZE **********************/

		JTextArea mazeArea = new JTextArea();
		mazeArea.setFont(new Font("Courier New", Font.PLAIN, 13));
		mazeArea.setEditable(false);
		mazeArea.setToolTipText("");
		mazeArea.setBounds(40, 162, 285, 285);
		frmJogoDoLabirinto.getContentPane().add(mazeArea);

		/***************** BUTTONS EXIT **********************/

		JButton buttonExit = new JButton("Terminar Programa");
		buttonExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		buttonExit.setBounds(395, 75, 149, 25);
		frmJogoDoLabirinto.getContentPane().add(buttonExit);

		/***************** COMBO BOX **********************/

		String[] dragonState = { "Estatico", "Aleatorio", "Aleatorio e Sonolento" };
		JComboBox dragonType = new JComboBox(dragonState);
		dragonType.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
			}
		});
		dragonType.setFont(new Font("Courier New", Font.PLAIN, 13));
		dragonType.setBounds(185, 108, 194, 22);
		frmJogoDoLabirinto.getContentPane().add(dragonType);

		/***************** LABELS **********************/

		JLabel lblNewLabel = new JLabel("Dimens\u00E3o do Labirinto");
		lblNewLabel.setBounds(40, 40, 133, 16);
		frmJogoDoLabirinto.getContentPane().add(lblNewLabel);

		mazeSize = new JTextField();
		mazeSize.setText("11");
		mazeSize.setBounds(185, 37, 50, 22);
		frmJogoDoLabirinto.getContentPane().add(mazeSize);
		mazeSize.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("N\u00FAmero de Drag\u00F5es");
		lblNewLabel_1.setBounds(40, 75, 133, 16);
		frmJogoDoLabirinto.getContentPane().add(lblNewLabel_1);

		numberOfDragons = new JTextField();
		numberOfDragons.setText("1");
		numberOfDragons.setBounds(185, 72, 50, 22);
		frmJogoDoLabirinto.getContentPane().add(numberOfDragons);
		numberOfDragons.setColumns(10);

		JLabel lblTipoDeDrages = new JLabel("Tipo de Drag\u00F5es");
		lblTipoDeDrages.setBounds(40, 111, 94, 16);
		frmJogoDoLabirinto.getContentPane().add(lblTipoDeDrages);

		JLabel InfoLabel = new JLabel("Pode gerar um novo Labirinto !");
		InfoLabel.setBounds(40, 458, 207, 16);
		frmJogoDoLabirinto.getContentPane().add(InfoLabel);

		JLabel label = new JLabel("[         ,         ]");
		label.setBounds(251, 75, 94, 16);
		frmJogoDoLabirinto.getContentPane().add(label);

		JLabel n_dragons_min = new JLabel("1");
		n_dragons_min.setBounds(257, 75, 27, 16);
		frmJogoDoLabirinto.getContentPane().add(n_dragons_min);

		JLabel n_dragons_max = new JLabel(" 4");
		n_dragons_max.setBounds(298, 75, 61, 16);
		frmJogoDoLabirinto.getContentPane().add(n_dragons_max);

		/***************** BUTTONS MOVE PLAYER **********************/

		JButton buttonUp = new JButton("Cima");
		buttonUp.setEnabled(false);
		buttonUp.setBounds(414, 161, 97, 25);
		frmJogoDoLabirinto.getContentPane().add(buttonUp);

		JButton buttonDown = new JButton("Baixo");
		buttonDown.setEnabled(false);
		buttonDown.setBounds(414, 224, 97, 25);
		frmJogoDoLabirinto.getContentPane().add(buttonDown);

		JButton buttonLeft = new JButton("Esquerda");
		buttonLeft.setEnabled(false);
		buttonLeft.setBounds(361, 193, 97, 25);
		frmJogoDoLabirinto.getContentPane().add(buttonLeft);

		JButton buttonRight = new JButton("Direita");
		buttonRight.setEnabled(false);
		buttonRight.setBounds(470, 193, 97, 25);
		frmJogoDoLabirinto.getContentPane().add(buttonRight);

		JButton[] movButtons = { buttonUp, buttonDown, buttonRight, buttonLeft };

		// LISTENERS
		// up
		buttonUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				newTurn('w', mazeArea, InfoLabel); // hero goes up
				isGameOver(InfoLabel, movButtons);
			}
		});

		// down
		buttonDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				newTurn('s', mazeArea, InfoLabel); // hero goes down
				isGameOver(InfoLabel, movButtons);
			}
		});

		// left
		buttonLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				newTurn('a', mazeArea, InfoLabel); // hero goes left
				isGameOver(InfoLabel, movButtons);
			}
		});

		// right
		buttonRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newTurn('d', mazeArea, InfoLabel); // hero goes right
				isGameOver(InfoLabel, movButtons);
			}
		});

		/***************** BOTÃO NEW MAZE **********************/

		/*
		 * Gera novos labirintos. O numero de dragoes só pode variar entre [1 ,
		 * (s-2)/2], em que s representa o tamanho do tabuleiro.
		 */

		JButton buttonNewMaze = new JButton("Gerar novo Labirinto");
		buttonNewMaze.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MazeBuilder mz = new MazeBuilder();

				int mz_size = Integer.parseInt(mazeSize.getText());
				int mz_n_dragons = Integer.parseInt(numberOfDragons.getText());

				n_dragons_min.setText("1");
				n_dragons_max.setText((((mz_size - 2) / 2)) + "");

				Board b = new Board(mz.buildMaze(mz_size, mz_n_dragons));
				board = b;

				switch (dragonType.getSelectedIndex()) {
				case 0: { // estático
					board.setDragonsBehaviour('P');
					break;
				}
				case 1: { // Aleatório
					board.setDragonsBehaviour('R');
					break;
				}
				case 2: { // Aleatorio e sonolento
					board.setDragonsBehaviour('S');
					break;
				}
				default:
					break;
				}
				board.updateBoard();

				// Imprime o tabuleiro
				mazeArea.setText(board.toString());

				// Enable dos botões de jogo
				setButtons(true, movButtons);

				InfoLabel.setText("Pode Jogar !");
			}
		});
		buttonNewMaze.setBounds(395, 36, 149, 25);
		frmJogoDoLabirinto.getContentPane().add(buttonNewMaze);

		JLabel label_1 = new JLabel("[         ,         ]");
		label_1.setBounds(251, 40, 94, 16);
		frmJogoDoLabirinto.getContentPane().add(label_1);

		JLabel label_2 = new JLabel("16");
		label_2.setBounds(298, 40, 27, 16);
		frmJogoDoLabirinto.getContentPane().add(label_2);

		JLabel label_3 = new JLabel("5");
		label_3.setBounds(257, 40, 27, 16);
		frmJogoDoLabirinto.getContentPane().add(label_3);
	}

	public void newTurn(char direction, JTextArea area, JLabel l) {
		board.moveHero(direction);
		board.moveRandomDragons();
		board.updateBoard();
		if (!board.getSword().inUse())
			l.setText("Apanha a espada");
		else if (!board.dragonsAllDead())
			l.setText("Mata os dragões todos");
		else if (board.dragonsAllDead())
			l.setText("Vai para a saida");
		area.setText(board.toString());
	}

	public void isGameOver(JLabel l, JButton[] b) {
		if (board.exitBoard()) {
			// desativar botões de movimento
			setButtons(false, b);
			// notificar utilizador
			if (board.heroWins())
				l.setText("Ganhas-te !");
			else
				l.setText("Perdes-te !");
		}
	}

	public void setButtons(boolean bool, JButton[] b) {
		for (int i = 0; i < b.length; i++) {
			b[i].setEnabled(bool);
		}
	}

}