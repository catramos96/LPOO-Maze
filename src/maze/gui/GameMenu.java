package maze.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameMenu {

	private JFrame frame;
	private JFrame option = new JFrame();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameMenu window = new GameMenu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GameMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Menu de Jogo");
		frame.setResizable(false);
		frame.setBounds(100, 100, 500, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel game_name = new JLabel("Jogo do Labirinto");
		game_name.setForeground(new Color(153, 0, 0));
		game_name.setFont(new Font("Stencil", Font.BOLD, 34));
		game_name.setBounds(62, 52, 379, 40);
		frame.getContentPane().add(game_name);

		JButton buttonPlay = new JButton("Jogar");
		buttonPlay.setForeground(Color.WHITE);
		buttonPlay.setBackground(Color.DARK_GRAY);
		buttonPlay.setBounds(153, 170, 172, 25);
		frame.getContentPane().add(buttonPlay);

		JButton buttonHowToPlay = new JButton("Como jogar");
		buttonHowToPlay.setForeground(Color.WHITE);
		buttonHowToPlay.setBackground(Color.DARK_GRAY);
		buttonHowToPlay.setBounds(153, 240, 172, 25);
		frame.getContentPane().add(buttonHowToPlay);

		JButton buttonExit = new JButton("Sair");
		buttonExit.setForeground(Color.WHITE);
		buttonExit.setBackground(Color.DARK_GRAY);
		buttonExit.setBounds(153, 310, 172, 25);
		frame.getContentPane().add(buttonExit);

		JPanel image_background = new Image("resources\\backg.png");
		((Image) image_background).addImageOfSpriteSheet("resources\\golbez.png",4,0,220,105,40,65);
		((Image) image_background).addImageOfSpriteSheet("resources\\bahamut.png",4,5,360,290,110,130);
		image_background.setBounds(0, 0, 494, 415);
		frame.getContentPane().add(image_background);

		/*
		 * BUTTONS LISTENERS
		 */

		buttonPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(option.getClass() != GameChooser.class || !option.isDisplayable()){
					option.dispose();
					option = new GameChooser();
				}
			}
		});


		buttonHowToPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});


		buttonExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
}
