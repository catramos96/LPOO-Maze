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

public class GameGraphics extends JFrame {

	private JPanel mazeArea = new GamePlay();;
	private JLabel infoLabel = new JLabel();
	private JButton[] movButtons;
	private Board board = new Board();

	/**
	 * Create the frame.
	 */
	public GameGraphics(Board b) {
		
		setTitle("Jogo do Labirinto");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		board = b;
		setBounds(100, 100, 671, 267);
		getContentPane().setLayout(null);
		setVisible(true);

		// Imprime o tabuleiro
		//mazeArea.setText(board.toString());

		

		JLabel InfoLabel = new JLabel();
		InfoLabel.setText("Pode Jogar !");

		JButton buttonUp = new JButton("Cima");
		buttonUp.setBounds(274, 13, 97, 25);
		getContentPane().add(buttonUp);

		JButton buttonDown = new JButton("Baixo");
		buttonDown.setBounds(274, 51, 97, 25);
		getContentPane().add(buttonDown);

		JButton buttonLeft = new JButton("Esquerda");
		buttonLeft.setBounds(176, 32, 97, 25);
		getContentPane().add(buttonLeft);

		JButton buttonRight = new JButton("Direita");
		buttonRight.setBounds(372, 32, 97, 25);
		getContentPane().add(buttonRight);

		JButton[] movButtons = new JButton[] { buttonUp, buttonDown, buttonRight, buttonLeft };

		// Enable dos botões de jogo
				setButtons(true, movButtons);
				
		mazeArea.setBounds(0, 100, 40*board.getBoard().length, 40*board.getBoard().length);
		( (GamePlay) mazeArea).setBoard(board);
		setBounds(500, 300, 120 + 40*board.getBoard().length, 200 + 40*board.getBoard().length);
		getContentPane().add(mazeArea);
		
		
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
			mazeArea.repaint();
		}

	}

}
