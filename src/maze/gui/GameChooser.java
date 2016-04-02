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
	private JFrame game = new JFrame();
	private JTextField txtDimensoDoLabirinto;
	private JTextField txtNmeroDeDrages;
	private JTextField txtTipoDeDrages;
	private JTextField txtTipoDeJogo;
	private JTextField dragon_min_max;
	private JTextField ERROR_MSG;

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
		setBounds(600, 100, 450, 396);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setVisible(true);

		String[] dragonState = { "Estatico", "Aleatorio", "Aleatorio e Sonolento" };

		JComboBox dragonType = new JComboBox(dragonState);
		dragonType.setBackground(Color.LIGHT_GRAY);
		dragonType.setFont(new Font("Courier New", Font.PLAIN, 13));
		dragonType.setBounds(185, 108, 194, 22);
		getContentPane().add(dragonType);

		mazeSize = new JTextField();
		mazeSize.setBackground(Color.LIGHT_GRAY);
		mazeSize.setText("11");
		mazeSize.setBounds(234, 38, 50, 22);
		getContentPane().add(mazeSize);
		mazeSize.setColumns(10);

		numberOfDragons = new JTextField();
		numberOfDragons.setBackground(Color.LIGHT_GRAY);
		numberOfDragons.setText("1");
		numberOfDragons.setBounds(234, 73, 50, 22);
		getContentPane().add(numberOfDragons);
		numberOfDragons.setColumns(10);

		JButton buttonNewMaze = new JButton("Gerar novo Labirinto");
		buttonNewMaze.setForeground(Color.WHITE);
		buttonNewMaze.setBackground(Color.DARK_GRAY);
		buttonNewMaze.setBounds(143, 200, 149, 25);
		getContentPane().add(buttonNewMaze);

		JButton btnCriarUmLabirinto = new JButton("Criar um Labirinto");
		btnCriarUmLabirinto.setBackground(Color.DARK_GRAY);
		btnCriarUmLabirinto.setForeground(Color.WHITE);
		btnCriarUmLabirinto.setBounds(143, 245, 149, 25);
		getContentPane().add(btnCriarUmLabirinto);

		JButton buttonBack = new JButton("Voltar ao Menu");	
		buttonBack.setBackground(Color.DARK_GRAY);
		buttonBack.setForeground(Color.WHITE);
		buttonBack.setBounds(143, 285, 149, 25);
		getContentPane().add(buttonBack);

		String[] gameType = { "Texto", "Gráfico"};
		JComboBox game_type = new JComboBox(gameType);
		game_type.setBackground(Color.LIGHT_GRAY);
		game_type.setFont(new Font("Courier New", Font.PLAIN, 13));
		game_type.setBounds(185, 145, 194, 22);
		getContentPane().add(game_type);

		ERROR_MSG = new JTextField();
		ERROR_MSG.setEditable(false);
		ERROR_MSG.setForeground(Color.RED);
		ERROR_MSG.setBackground(Color.DARK_GRAY);
		ERROR_MSG.setBounds(12, 320, 286, 22);
		getContentPane().add(ERROR_MSG);
		ERROR_MSG.setColumns(10);
		ERROR_MSG.setVisible(false);

		txtDimensoDoLabirinto = new JTextField();
		txtDimensoDoLabirinto.setEditable(false);
		txtDimensoDoLabirinto.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtDimensoDoLabirinto.setForeground(Color.WHITE);
		txtDimensoDoLabirinto.setBackground(Color.DARK_GRAY);
		txtDimensoDoLabirinto.setText("Dimens\u00E3o do Labirinto");
		txtDimensoDoLabirinto.setBounds(40, 40, 149, 22);
		getContentPane().add(txtDimensoDoLabirinto);
		txtDimensoDoLabirinto.setColumns(10);

		txtNmeroDeDrages = new JTextField();
		txtNmeroDeDrages.setEditable(false);
		txtNmeroDeDrages.setText("N\u00FAmero de Drag\u00F5es");
		txtNmeroDeDrages.setForeground(Color.WHITE);
		txtNmeroDeDrages.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtNmeroDeDrages.setColumns(10);
		txtNmeroDeDrages.setBackground(Color.DARK_GRAY);
		txtNmeroDeDrages.setBounds(40, 73, 149, 22);
		getContentPane().add(txtNmeroDeDrages);

		txtTipoDeDrages = new JTextField();
		txtTipoDeDrages.setEditable(false);
		txtTipoDeDrages.setText("Tipo de Drag\u00F5es");
		txtTipoDeDrages.setForeground(Color.WHITE);
		txtTipoDeDrages.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtTipoDeDrages.setColumns(10);
		txtTipoDeDrages.setBackground(Color.DARK_GRAY);
		txtTipoDeDrages.setBounds(40, 107, 117, 22);
		getContentPane().add(txtTipoDeDrages);

		txtTipoDeJogo = new JTextField();
		txtTipoDeJogo.setEditable(false);
		txtTipoDeJogo.setText("Tipo de Jogo");
		txtTipoDeJogo.setForeground(Color.WHITE);
		txtTipoDeJogo.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtTipoDeJogo.setColumns(10);
		txtTipoDeJogo.setBackground(Color.DARK_GRAY);
		txtTipoDeJogo.setBounds(40, 144, 117, 22);
		getContentPane().add(txtTipoDeJogo);

		dragon_min_max = new JTextField();
		dragon_min_max.setEditable(false);
		dragon_min_max.setText("[    1   ,    4   ]");
		dragon_min_max.setForeground(Color.WHITE);
		dragon_min_max.setFont(new Font("Tahoma", Font.BOLD, 13));
		dragon_min_max.setColumns(10);
		dragon_min_max.setBackground(Color.DARK_GRAY);
		dragon_min_max.setBounds(302, 73, 99, 22);
		getContentPane().add(dragon_min_max);

		JPanel image_background = new Image("resources\\backg.png");
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
		buttonBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});

		//new maze
		buttonNewMaze.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MazeBuilder mz = new MazeBuilder();

				int mz_size = Integer.parseInt(mazeSize.getText());
				int mz_n_dragons = Integer.parseInt(numberOfDragons.getText());
				char dragon_t = ' ';
				if(mz_size >= 6)
					dragon_min_max.setText("[    1   ,    "+((mz_size - 2) / 2)+"   ]");

				if(mz_n_dragons > ((mz_size-2)/2)){
					ERROR_MSG.setVisible(true);
					ERROR_MSG.setText("Número de dragões invalido!");
				}
				
				if(mz_size < 6 || mz_size >30){
					mz_size = 0;
					ERROR_MSG.setVisible(true);
					ERROR_MSG.setText("Tamanho do tabuleiro invalido! [5 , 30]");
				}

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

				if(game_type.getSelectedIndex() == 1)
				{
					if(game.getClass() != GameGraphics.class || !game.isDisplayable()){
						game.dispose();
						game = new GameGraphics(board);
					}
					else
						((GameGraphics) game).setBoard(b);
				}
				else if(game_type.getSelectedIndex() == 0)
				{
					if(game.getClass() != GameText.class || !game.isDisplayable()){
						game.dispose();
						game = new GameText(board);
					}
					else
						((GameText) game).setBoard(b);
				}
			}
		});

	}
}
