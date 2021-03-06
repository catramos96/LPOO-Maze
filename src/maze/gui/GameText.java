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
import java.awt.Font;

import javax.swing.JTextField;

public class GameText extends JFrame {
	
	private JTextArea mazeArea;
	private JTextField infoLabel = new JTextField();
	private JButton[] movButtons;
	private Board board = new Board();
	private JPanel image_background = new JPanel();
	
	/**
	 * Create the frame.
	 */
	public GameText(Board b) {
		setAlwaysOnTop(true);
		
		setTitle("Jogo do Labirinto");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		board = b;
		setBounds(100, 100, 450, 263);
		getContentPane().setLayout(null);
		setVisible(true);
		
		mazeArea = new JTextArea();
		mazeArea.setFont(new Font("Courier New", Font.PLAIN, 13));
		mazeArea.setEditable(false);
		mazeArea.setToolTipText("");
		mazeArea.setBounds(40, 162, 285, 285);
		getContentPane().add(mazeArea);
		
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
		
		JButton btnVoltarAoMenu = new JButton("Voltar Atras");
		btnVoltarAoMenu.setForeground(Color.WHITE);
		btnVoltarAoMenu.setBackground(Color.DARK_GRAY);
		btnVoltarAoMenu.setBounds(295, 44, 127, 25);
		getContentPane().add(btnVoltarAoMenu);
		

		 movButtons = new JButton[] { buttonUp, buttonDown, buttonRight, buttonLeft };

		// Enable dos bot�es de jogo
				setButtons(true, movButtons);
				
		mazeArea.setBounds(50, 150, 8*board.getBoard().length, 15*board.getBoard().length);
		mazeArea.setText(board.toString());
		if(9*board.getBoard().length+100 <= getWidth())
			setBounds(500, 300, 450, 225 + 15*board.getBoard().length);
		else
			setBounds(500, 300, 9*board.getBoard().length+50, 225 + 15*board.getBoard().length);
		getContentPane().add(mazeArea);
		
		image_background = new Image("resources//backg.png");
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
		mazeArea.setBounds(50, 150, 8*board.getBoard().length, 15*board.getBoard().length);
		mazeArea.setText(board.toString());
		if(9*board.getBoard().length+100 <= getWidth())
			setBounds(500, 300, 450, 225 + 15*board.getBoard().length);
		else
			setBounds(500, 300, 9*board.getBoard().length+50, 225 + 15*board.getBoard().length);
		image_background.setBounds(0, 0, 450, 225 + 15*board.getBoard().length);	
		}

	public void updateInfo(JTextField infoLabel2){
		if (!board.getSword().inUse())
			infoLabel2.setText("Apanha a espada");
		else if (!board.dragonsAllDead())
			infoLabel2.setText("Mata os dragoes todos");
		else if (board.dragonsAllDead())
			infoLabel2.setText("Vai para a saida");	
		if (board.exitBoard()) {
			if (board.heroWins())
				infoLabel2.setText("Ganhaste !");
			else
				infoLabel2.setText("Perdeste !");
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
			board.moveRandomDragons();
			board.moveHero(d);
			board.updateBoard();
			mazeArea.setText(board.toString());
			boolean teste = board.exitBoard();
			if(board.exitBoard())
				setButtons(false, movButtons);
				
		}

	}
}
