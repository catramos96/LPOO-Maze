package maze.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

import maze.logi.Board;
import java.awt.Color;
import javax.swing.JTextField;

public class GameGraphics extends JFrame {

	private JPanel mazeArea = new GameBoard();;
	private JTextField infoLabel = new JTextField();
	private JButton[] movButtons;
	private JPanel image_background;
	private Board board = new Board();

	/**
	 * Create the frame.
	 */
	public GameGraphics(Board b) {
		setAlwaysOnTop(true);

		setTitle("Jogo do Labirinto");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		board = b;
		setBounds(500, 100, 600, 263);
		getContentPane().setLayout(null);
		setVisible(true);


		infoLabel = new JTextField();
		infoLabel.setEditable(false);
		infoLabel.setBackground(Color.DARK_GRAY);
		infoLabel.setForeground(Color.WHITE);
		infoLabel.setBounds(12, 112, 325, 22);
		getContentPane().add(infoLabel);
		infoLabel.setColumns(10);
		infoLabel.setText("Pode Jogar !");

		JButton buttonUp = new JButton("Cima");
		buttonUp.setBackground(Color.DARK_GRAY);
		buttonUp.setForeground(Color.WHITE);
		buttonUp.setBounds(109, 13, 97, 25);
		getContentPane().add(buttonUp);

		JButton buttonDown = new JButton("Baixo");
		buttonDown.setForeground(Color.WHITE);
		buttonDown.setBackground(Color.DARK_GRAY);
		buttonDown.setBounds(109, 74, 97, 25);
		getContentPane().add(buttonDown);

		JButton buttonLeft = new JButton("Esquerda");
		buttonLeft.setForeground(Color.WHITE);
		buttonLeft.setBackground(Color.DARK_GRAY);
		buttonLeft.setBounds(61, 44, 97, 25);
		getContentPane().add(buttonLeft);

		JButton buttonRight = new JButton("Direita");
		buttonRight.setForeground(Color.WHITE);
		buttonRight.setBackground(Color.DARK_GRAY);
		buttonRight.setBounds(162, 44, 97, 25);
		getContentPane().add(buttonRight);

		JButton btnVoltarAoMenu = new JButton("Voltar Atr\u00E1s");
		btnVoltarAoMenu.setForeground(Color.WHITE);
		btnVoltarAoMenu.setBackground(Color.DARK_GRAY);
		btnVoltarAoMenu.setBounds(295, 44, 127, 25);
		getContentPane().add(btnVoltarAoMenu);


		JButton[] movButtons = new JButton[] { buttonUp, buttonDown, buttonRight, buttonLeft };

		// Enable dos botões de jogo
		setButtons(true, movButtons);

		mazeArea.setBounds(50, 150, 40*board.getBoard().length, 40*board.getBoard().length);
		( (GameBoard) mazeArea).setBoard(board);
		if(mazeArea.getWidth()+100 <= getWidth())
			setBounds(850, 100,550, 250 + 40*board.getBoard().length);
		else
			setBounds(850, 100, 100 + 40*board.getBoard().length, 250 + 40*board.getBoard().length);
		getContentPane().add(mazeArea);

		image_background = new Image("resources\\backg.png");
		image_background.setBounds(0, 0, getWidth(), getHeight());
		getContentPane().add(image_background);

		/*
		 * LISTENERS
		 */

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

		// Back
		btnVoltarAoMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		/***
		 *  KEYBOARD TREATMENT
		 */
		JRootPane  Pane_maze = mazeArea.getRootPane();

		javax.swing.Action upKeyAction = new javax.swing.AbstractAction("upKeyAction") {

			@Override
			public void actionPerformed(ActionEvent e) {
				gameTurn('w');
				updateInfo(infoLabel);
			} 
		};

		javax.swing.Action downKeyAction = new AbstractAction("downKeyAction") {

			@Override
			public void actionPerformed(ActionEvent e) {
				gameTurn('s');
				updateInfo(infoLabel);
			}
		};

		javax.swing.Action rightKeyAction = new AbstractAction("rightKeyAction") {

			@Override
			public void actionPerformed(ActionEvent e) {
				gameTurn('d');
				updateInfo(infoLabel);
			} 
		};

		javax.swing.Action leftKeyAction = new AbstractAction("leftKeyAction") {

			@Override
			public void actionPerformed(ActionEvent e) {
				gameTurn('a');
				updateInfo(infoLabel);
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

	public void setBoard(Board b){
		board = b;
		mazeArea.setBounds(50, 150, 40*board.getBoard().length, 40*board.getBoard().length);
		( (GameBoard) mazeArea).setBoard(board);
		if(mazeArea.getWidth()+100 <= getWidth())
			setBounds(850, 300,550, 250 + 40*board.getBoard().length);
		else
			setBounds(850, 300, 100 + 40*board.getBoard().length, 250 + 40*board.getBoard().length);
		image_background.setBounds(0, 0, getWidth(), getHeight());
		//getContentPane().add(image_background);
	}

	public void newTurn(char direction, JTextArea area, JTextField l) {
		board.moveHero(direction);
		board.moveRandomDragons();
		board.updateBoard();
		updateInfo(l);
		area.setText(board.toString());
		if(board.exitBoard())
			setButtons(false, movButtons);
	}

	public void updateInfo(JTextField infoLabel2){
		if (!board.getSword().inUse())
			infoLabel2.setText("Apanha a espada");
		else if (!board.dragonsAllDead())
			infoLabel2.setText("Mata os dragões todos");
		else if (board.dragonsAllDead())
			infoLabel2.setText("Vai para a saida");	
		if (board.exitBoard()) {
			if (board.heroWins())
				infoLabel2.setText("Ganhas-te !");
			else
				infoLabel2.setText("Perdes-te !");
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
			mazeArea.repaint();
		}

	}
}
