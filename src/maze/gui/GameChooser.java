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
	private GameMazeCreator creator;
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
		setBounds(600, 100, 500, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		
		String[] dragonState = { "Estatico", "Aleatorio", "Aleatorio e Sonolento" };
		getContentPane().setLayout(null);

		JComboBox dragonType = new JComboBox(dragonState);
		dragonType.setBounds(234, 108, 215, 22);
		dragonType.setBackground(Color.LIGHT_GRAY);
		dragonType.setFont(new Font("Courier New", Font.PLAIN, 13));
		getContentPane().add(dragonType);

		mazeSize = new JTextField();
		mazeSize.setBounds(234, 38, 50, 22);
		mazeSize.setBackground(Color.LIGHT_GRAY);
		mazeSize.setText("11");
		getContentPane().add(mazeSize);
		mazeSize.setColumns(10);

		numberOfDragons = new JTextField();
		numberOfDragons.setBounds(234, 73, 50, 22);
		numberOfDragons.setBackground(Color.LIGHT_GRAY);
		numberOfDragons.setText("1");
		getContentPane().add(numberOfDragons);
		numberOfDragons.setColumns(10);

		JButton buttonNewMaze = new JButton("Gerar novo Labirinto");
		buttonNewMaze.setBounds(88, 200, 286, 25);
		buttonNewMaze.setForeground(Color.WHITE);
		buttonNewMaze.setBackground(Color.DARK_GRAY);
		getContentPane().add(buttonNewMaze);

		JButton btnCriarUmLabirinto = new JButton("Criar um Labirinto");
		btnCriarUmLabirinto.setBounds(88, 245, 286, 25);
		btnCriarUmLabirinto.setBackground(Color.DARK_GRAY);
		btnCriarUmLabirinto.setForeground(Color.WHITE);
		getContentPane().add(btnCriarUmLabirinto);

		JButton buttonBack = new JButton("Voltar ao Menu");	
		buttonBack.setBounds(88, 285, 286, 25);
		buttonBack.setBackground(Color.DARK_GRAY);
		buttonBack.setForeground(Color.WHITE);
		getContentPane().add(buttonBack);

		String[] gameType = { "Texto", "Gr�fico"};
		JComboBox game_type = new JComboBox(gameType);
		game_type.setBounds(234, 145, 217, 22);
		game_type.setBackground(Color.LIGHT_GRAY);
		game_type.setFont(new Font("Courier New", Font.PLAIN, 13));
		getContentPane().add(game_type);

		ERROR_MSG = new JTextField();
		ERROR_MSG.setBounds(88, 320, 286, 22);
		ERROR_MSG.setEditable(false);
		ERROR_MSG.setForeground(Color.RED);
		ERROR_MSG.setBackground(Color.DARK_GRAY);
		getContentPane().add(ERROR_MSG);
		ERROR_MSG.setColumns(10);
		ERROR_MSG.setVisible(false);

		txtDimensoDoLabirinto = new JTextField();
		txtDimensoDoLabirinto.setBounds(40, 40, 170, 22);
		txtDimensoDoLabirinto.setEditable(false);
		txtDimensoDoLabirinto.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtDimensoDoLabirinto.setForeground(Color.WHITE);
		txtDimensoDoLabirinto.setBackground(Color.DARK_GRAY);
		txtDimensoDoLabirinto.setText("Dimens\u00E3o do Labirinto");
		getContentPane().add(txtDimensoDoLabirinto);
		txtDimensoDoLabirinto.setColumns(10);

		txtNmeroDeDrages = new JTextField();
		txtNmeroDeDrages.setBounds(40, 73, 170, 22);
		txtNmeroDeDrages.setEditable(false);
		txtNmeroDeDrages.setText("N\u00FAmero de Drag\u00F5es");
		txtNmeroDeDrages.setForeground(Color.WHITE);
		txtNmeroDeDrages.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtNmeroDeDrages.setColumns(10);
		txtNmeroDeDrages.setBackground(Color.DARK_GRAY);
		getContentPane().add(txtNmeroDeDrages);

		txtTipoDeDrages = new JTextField();
		txtTipoDeDrages.setBounds(40, 107, 170, 22);
		txtTipoDeDrages.setEditable(false);
		txtTipoDeDrages.setText("Tipo de Drag\u00F5es");
		txtTipoDeDrages.setForeground(Color.WHITE);
		txtTipoDeDrages.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtTipoDeDrages.setColumns(10);
		txtTipoDeDrages.setBackground(Color.DARK_GRAY);
		getContentPane().add(txtTipoDeDrages);

		txtTipoDeJogo = new JTextField();
		txtTipoDeJogo.setBounds(40, 144, 170, 22);
		txtTipoDeJogo.setEditable(false);
		txtTipoDeJogo.setText("Tipo de Jogo");
		txtTipoDeJogo.setForeground(Color.WHITE);
		txtTipoDeJogo.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtTipoDeJogo.setColumns(10);
		txtTipoDeJogo.setBackground(Color.DARK_GRAY);
		getContentPane().add(txtTipoDeJogo);

		dragon_min_max = new JTextField();
		dragon_min_max.setBounds(302, 73, 119, 22);
		dragon_min_max.setEditable(false);
		dragon_min_max.setText("[    1   ,    4   ]");
		dragon_min_max.setForeground(Color.WHITE);
		dragon_min_max.setFont(new Font("Tahoma", Font.BOLD, 13));
		dragon_min_max.setColumns(10);
		dragon_min_max.setBackground(Color.DARK_GRAY);
		getContentPane().add(dragon_min_max);

		JPanel image_background = new Image("resources//backg.png");
		image_background.setBounds(0, 0, 500, 400);
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
					ERROR_MSG.setText("N�mero de drag�es invalido!");
				}
				
				if(mz_size < 6 || mz_size >30){
					mz_size = 0;
					ERROR_MSG.setVisible(true);
					ERROR_MSG.setText("Tamanho do tabuleiro invalido! [5 , 30]");
				}

				Board b = new Board(mz.buildMaze(mz_size, mz_n_dragons));
				board = b;

				switch (dragonType.getSelectedIndex()) {
				case 0: { // est�tico
					board.setDragonsBehaviour('P');
					break;
				}
				case 1: { // Aleat�rio
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
		
		btnCriarUmLabirinto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			creator = new GameMazeCreator();
				
			}});

	}
}
