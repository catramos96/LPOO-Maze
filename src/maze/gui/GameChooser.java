package maze.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

import maze.logi.Board;
import maze.logi.MazeBuilder;
import java.awt.Color;

public class GameChooser extends JFrame {

	private JTextField mazeSize;
	private JTextField numberOfDragons;
	private Board board = new Board();

	/**
	 * Create the application.
	 */
	public GameChooser() {	
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize() {
		setResizable(false);

		setTitle("Jogo do Labirinto");
		setBounds(100, 100, 450, 396);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setVisible(true);

		String[] dragonState = { "Estatico", "Aleatorio", "Aleatorio e Sonolento" };
		JComboBox dragonType = new JComboBox(dragonState);
		dragonType.setBackground(Color.LIGHT_GRAY);
		dragonType.setFont(new Font("Courier New", Font.PLAIN, 13));
		dragonType.setBounds(185, 108, 194, 22);
		getContentPane().add(dragonType);
		JLabel lblNewLabel = new JLabel("Dimens\u00E3o do Labirinto");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(40, 40, 182, 16);
		getContentPane().add(lblNewLabel);

		mazeSize = new JTextField();
		mazeSize.setBackground(Color.LIGHT_GRAY);
		mazeSize.setText("11");
		mazeSize.setBounds(234, 38, 50, 22);
		getContentPane().add(mazeSize);
		mazeSize.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("N\u00FAmero de Drag\u00F5es");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(40, 75, 149, 16);
		getContentPane().add(lblNewLabel_1);

		numberOfDragons = new JTextField();
		numberOfDragons.setBackground(Color.LIGHT_GRAY);
		numberOfDragons.setText("1");
		numberOfDragons.setBounds(234, 73, 50, 22);
		getContentPane().add(numberOfDragons);
		numberOfDragons.setColumns(10);

		JLabel lblTipoDeDrages = new JLabel("Tipo de Drag\u00F5es");
		lblTipoDeDrages.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTipoDeDrages.setForeground(Color.WHITE);
		lblTipoDeDrages.setBounds(40, 111, 133, 16);
		getContentPane().add(lblTipoDeDrages);

		JLabel label = new JLabel("[         ,         ]");
		label.setFont(new Font("Tahoma", Font.BOLD, 15));
		label.setForeground(Color.RED);
		label.setBackground(Color.LIGHT_GRAY);
		label.setBounds(304, 75, 91, 16);
		getContentPane().add(label);

		JLabel n_dragons_min = new JLabel("1");
		n_dragons_min.setFont(new Font("Tahoma", Font.BOLD, 15));
		n_dragons_min.setForeground(Color.RED);
		n_dragons_min.setBounds(320, 76, 27, 16);
		getContentPane().add(n_dragons_min);

		JLabel n_dragons_max = new JLabel(" 4");
		n_dragons_max.setForeground(Color.RED);
		n_dragons_max.setFont(new Font("Tahoma", Font.BOLD, 15));
		n_dragons_max.setBackground(Color.LIGHT_GRAY);
		n_dragons_max.setBounds(359, 76, 61, 16);
		getContentPane().add(n_dragons_max);

		JButton buttonNewMaze = new JButton("Gerar novo Labirinto");
		buttonNewMaze.setForeground(Color.WHITE);
		buttonNewMaze.setBackground(Color.DARK_GRAY);
		buttonNewMaze.setBounds(143, 218, 149, 25);
		getContentPane().add(buttonNewMaze);

		JButton buttonExit = new JButton("Terminar Programa");	
		buttonExit.setBackground(Color.DARK_GRAY);
		buttonExit.setForeground(Color.WHITE);
		buttonExit.setBounds(143, 269, 149, 25);
		getContentPane().add(buttonExit);
		
		JLabel lblTipoDeJogo = new JLabel("Tipo de Jogo");
		lblTipoDeJogo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTipoDeJogo.setForeground(Color.WHITE);
		lblTipoDeJogo.setBounds(40, 148, 117, 16);
		getContentPane().add(lblTipoDeJogo);
		
		String[] gameType = { "Texto", "Gráfico"};
		JComboBox game_type = new JComboBox(gameType);
		game_type.setBackground(Color.LIGHT_GRAY);
		game_type.setFont(new Font("Courier New", Font.PLAIN, 13));
		game_type.setBounds(185, 145, 194, 22);
		getContentPane().add(game_type);
		
		JPanel image_background = new Image("resources\\wall_plain.png");
		image_background.setBounds(0, 0, 444, 361);
		getContentPane().add(image_background);

		/*
		 * LISTENERS
		 */

		//combo box - type of dragons
		dragonType.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
			}
		});

		// exit
		buttonExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		//new maze
		buttonNewMaze.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MazeBuilder mz = new MazeBuilder();

				int mz_size = Integer.parseInt(mazeSize.getText());
				int mz_n_dragons = Integer.parseInt(numberOfDragons.getText());
				char dragon_t = ' ';

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
				
				JFrame game = new JFrame();
				
				if(game_type.getSelectedIndex() != 0)
					game = new GameGraphics(board);
			}
		});

	}
}
