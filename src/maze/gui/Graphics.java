package maze.gui;

import java.awt.Desktop.Action;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.AbstractAction;
import javax.swing.*;
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

import maze.logi.Board;
import maze.logi.MazeBuilder;

public class Graphics{

	private JFrame frmJogoDoLabirinto;
	private JTextField mazeSize;
	private JTextField numberOfDragons;
	private Board board = new Board();
	private JButton[] movButtons;
	private JTextArea mazeArea;
	private JPanel mazeAreaG;
	private JLabel InfoLabel;

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
		frmJogoDoLabirinto.setBounds(100, 100, 825, 560);
		frmJogoDoLabirinto.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmJogoDoLabirinto.getContentPane().setLayout(null);
		frmJogoDoLabirinto.setVisible(true);
		/***************** AREA DISPLAY MAZE **********************/

		/*	mazeArea = new JTextArea();
		mazeArea.setFont(new Font("Courier New", Font.PLAIN, 13));
		mazeArea.setEditable(false);
		mazeArea.setToolTipText("");
		mazeArea.setBounds(40, 162, 285, 285);
		frmJogoDoLabirinto.getContentPane().add(mazeArea);*/

		String[] dragonState = { "Estatico", "Aleatorio", "Aleatorio e Sonolento" };
		JComboBox dragonType = new JComboBox(dragonState);
		dragonType.setFont(new Font("Courier New", Font.PLAIN, 13));
		dragonType.setBounds(185, 108, 194, 22);
		frmJogoDoLabirinto.getContentPane().add(dragonType);

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

		InfoLabel = new JLabel("Pode gerar um novo Labirinto !");
		InfoLabel.setBounds(40, 148, 207, 16);
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

		JButton buttonNewMaze = new JButton("Gerar novo Labirinto");
		buttonNewMaze.setBounds(395, 36, 149, 25);
		frmJogoDoLabirinto.getContentPane().add(buttonNewMaze);
		
		JButton buttonExit = new JButton("Terminar Programa");	
		buttonExit.setBounds(395, 75, 149, 25);
		frmJogoDoLabirinto.getContentPane().add(buttonExit);
		
		JButton buttonUp = new JButton("Cima");
		buttonUp.setEnabled(false);
		buttonUp.setBounds(625, 36, 97, 25);
		frmJogoDoLabirinto.getContentPane().add(buttonUp);

		JButton buttonDown = new JButton("Baixo");
		buttonDown.setEnabled(false);
		buttonDown.setBounds(625, 106, 97, 25);
		frmJogoDoLabirinto.getContentPane().add(buttonDown);

		JButton buttonLeft = new JButton("Esquerda");
		buttonLeft.setEnabled(false);
		buttonLeft.setBounds(575, 71, 97, 25);
		frmJogoDoLabirinto.getContentPane().add(buttonLeft);

		JButton buttonRight = new JButton("Direita");
		buttonRight.setEnabled(false);
		buttonRight.setBounds(681, 71, 97, 25);
		frmJogoDoLabirinto.getContentPane().add(buttonRight);

		movButtons = new JButton[] { buttonUp, buttonDown, buttonRight, buttonLeft };
		
		//area onde faz o display do jogo modo gráfico
		mazeAreaG = new GameBoard(board);
		mazeAreaG.setBounds(40, 400, 300, 300);
		frmJogoDoLabirinto.getContentPane().add(mazeAreaG);

		/*
		 * LISTENERS
		 */
		
		//combo box - type of dragons
		dragonType.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
			}
		});
		
		// up
		buttonUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 gameTurn('w');
			}
		});

		// down
		buttonDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 gameTurn('s');
			}
		});

		// left
		buttonLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 gameTurn('a');
			}
		});

		// right
		buttonRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 gameTurn('d');
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
				mazeAreaG.setBounds(40, 200, 40*mz_size, 40*mz_size);
				frmJogoDoLabirinto.setBounds(100, 100, 825, 250+40*mz_size);
				((GameBoard) mazeAreaG).setBoard(board);

				// Imprime o tabuleiro
				//mazeArea.setText(board.toString());

				// Enable dos botões de jogo
				setButtons(true, movButtons);

				InfoLabel.setText("Pode Jogar !");
			}
		});
		
		/***
		 *  KEYBOARD TREATMENT
		 */
		JRootPane  Pane_maze = mazeAreaG.getRootPane();
		
		javax.swing.Action upKeyAction = new javax.swing.AbstractAction("upKeyAction") {

			@Override
			public void actionPerformed(ActionEvent e) {
				 gameTurn('w');
				 updateInfo(InfoLabel);
			} 
		};

		javax.swing.Action downKeyAction = new AbstractAction("downKeyAction") {

			@Override
			public void actionPerformed(ActionEvent e) {
				 gameTurn('s');
				 updateInfo(InfoLabel);
			}
		};
		
		javax.swing.Action rightKeyAction = new AbstractAction("rightKeyAction") {

			@Override
			public void actionPerformed(ActionEvent e) {
				 gameTurn('d');
				 updateInfo(InfoLabel);
			} 
		};

		javax.swing.Action leftKeyAction = new AbstractAction("leftKeyAction") {

			@Override
			public void actionPerformed(ActionEvent e) {
				 gameTurn('a');
				 updateInfo(InfoLabel);
			}
		};

		KeyStroke upKeyStroke = KeyStroke.getKeyStroke("UP");
		KeyStroke downKeyStroke = KeyStroke.getKeyStroke("DOWN");
		KeyStroke rightKeyStroke = KeyStroke.getKeyStroke("RIGHT");
		KeyStroke leftKeyStroke = KeyStroke.getKeyStroke("LEFT");
		Pane_maze.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(upKeyStroke, "upKeyAction");
		Pane_maze.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(downKeyStroke, "downKeyAction");
		Pane_maze.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(rightKeyStroke, "rightKeyAction");
		Pane_maze.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(leftKeyStroke, "leftKeyAction");
		Pane_maze.getActionMap().put("upKeyAction", upKeyAction);
		Pane_maze.getActionMap().put("downKeyAction", downKeyAction);
		Pane_maze.getActionMap().put("rightKeyAction", rightKeyAction);
		Pane_maze.getActionMap().put("leftKeyAction", leftKeyAction);


	}
	
	/*
	 * OTHER FUNCTIONS
	 */
	
	public void newTurn(char direction, JTextArea area, JLabel l) {
		board.moveHero(direction);
		board.moveRandomDragons();
		board.updateBoard();
		updateInfo(l);
		area.setText(board.toString());
		if(board.exitBoard())
			setButtons(false, movButtons);
	}
	
	public void updateInfo(JLabel l){
		if (!board.getSword().inUse())
			l.setText("Apanha a espada");
		else if (!board.dragonsAllDead())
			l.setText("Mata os dragões todos");
		else if (board.dragonsAllDead())
			l.setText("Vai para a saida");	
		if (board.exitBoard()) {
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
	public void gameTurn(char d){
		if(!board.exitBoard())
		{
			board.moveHero(d);
			board.moveRandomDragons();
			board.updateBoard();
			mazeAreaG.repaint();
		}

	}
	
	
}
