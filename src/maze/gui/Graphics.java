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

		String[] dragonState = { "Estatico", "Aleatorio", "Aleatorio e Sonolento" };
		JComboBox dragonType = new JComboBox(dragonState);
		dragonType.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
			}
		});
		dragonType.setFont(new Font("Courier New", Font.PLAIN, 13));
		dragonType.setBounds(185, 108, 194, 22);
		frmJogoDoLabirinto.getContentPane().add(dragonType);

		JTextArea mazeArea = new JTextArea();
		mazeArea.setFont(new Font("Courier New", Font.PLAIN, 13));
		mazeArea.setEditable(false);
		mazeArea.setToolTipText("");
		mazeArea.setBounds(40, 162, 285, 285);
		frmJogoDoLabirinto.getContentPane().add(mazeArea);

		JLabel InfoLabel = new JLabel("Pode gerar um novo Labirinto !");
		InfoLabel.setBounds(40, 458, 207, 16);
		frmJogoDoLabirinto.getContentPane().add(InfoLabel);

		JButton buttonExit = new JButton("Terminar Programa");
		buttonExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		buttonExit.setBounds(395, 75, 149, 25);
		frmJogoDoLabirinto.getContentPane().add(buttonExit);

		JButton buttonUp = new JButton("Cima");
		buttonUp.setEnabled(false);
		buttonUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		buttonUp.setBounds(414, 161, 97, 25);
		frmJogoDoLabirinto.getContentPane().add(buttonUp);

		JButton buttonDown = new JButton("Baixo");
		buttonDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
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

		JLabel label = new JLabel("[         ,         ]");
		label.setBounds(247, 40, 94, 16);
		frmJogoDoLabirinto.getContentPane().add(label);

		JLabel n_dragons_min = new JLabel("m");
		n_dragons_min.setBounds(257, 75, 27, 16);
		frmJogoDoLabirinto.getContentPane().add(n_dragons_min);

		JLabel n_dragons_max = new JLabel(" M ");
		n_dragons_max.setBounds(298, 75, 61, 16);
		frmJogoDoLabirinto.getContentPane().add(n_dragons_max);

		JButton buttonNewMaze = new JButton("Gerar novo Labirinto");
		buttonNewMaze.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MazeBuilder mz = new MazeBuilder();

				int mz_size = Integer.parseInt(mazeSize.getText());
				int mz_n_dragons = Integer.parseInt(numberOfDragons.getText());
				
				n_dragons_min.setText("1");
				n_dragons_max.setText((((mz_size - 2) / 2))+ "");

				Board b = new Board(mz.buildMaze(mz_size, mz_n_dragons));
				board = b;

				switch (dragonType.getItemCount()) {
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
				buttonUp.setEnabled(true);
				buttonDown.setEnabled(true);
				buttonLeft.setEnabled(true);
				buttonRight.setEnabled(true);

				InfoLabel.setText("Pode Jogar !");
			}
		});
		buttonNewMaze.setBounds(395, 36, 149, 25);
		frmJogoDoLabirinto.getContentPane().add(buttonNewMaze);

	}
}
