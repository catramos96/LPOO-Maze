package maze.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameMenu {

	private JFrame frmGameMenu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameMenu window = new GameMenu();
					window.frmGameMenu.setVisible(true);
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
		frmGameMenu = new JFrame();
		frmGameMenu.setTitle("Menu de Jogo");
		frmGameMenu.setResizable(false);
		frmGameMenu.setBounds(100, 100, 500, 450);
		frmGameMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGameMenu.getContentPane().setLayout(null);
		
		JLabel game_name = new JLabel("Jogo do Labirinto");
		game_name.setForeground(new Color(153, 0, 0));
		game_name.setFont(new Font("Stencil", Font.BOLD, 34));
		game_name.setBounds(62, 52, 379, 40);
		frmGameMenu.getContentPane().add(game_name);
		
		JButton buttonPlayText = new JButton("Jogar em \u00E1rea de texto");
		buttonPlayText.setForeground(Color.WHITE);
		buttonPlayText.setBackground(Color.DARK_GRAY);
		buttonPlayText.setBounds(153, 150, 172, 25);
		frmGameMenu.getContentPane().add(buttonPlayText);
		
		JButton buttonPlayGraphs = new JButton("Jogar com gr\u00E1ficos");
		buttonPlayGraphs.setBackground(Color.DARK_GRAY);
		buttonPlayGraphs.setForeground(Color.WHITE);
		buttonPlayGraphs.setBounds(153, 210, 172, 25);
		frmGameMenu.getContentPane().add(buttonPlayGraphs);
		
		JButton buttonHowToPlay = new JButton("Como jogar");
		buttonHowToPlay.setForeground(Color.WHITE);
		buttonHowToPlay.setBackground(Color.DARK_GRAY);
		buttonHowToPlay.setBounds(153, 270, 172, 25);
		frmGameMenu.getContentPane().add(buttonHowToPlay);
		
		JButton buttonExit = new JButton("Sair");
		buttonExit.setForeground(Color.WHITE);
		buttonExit.setBackground(Color.DARK_GRAY);
		buttonExit.setBounds(153, 330, 172, 25);
		frmGameMenu.getContentPane().add(buttonExit);
		
		JPanel image_background = new Image("resources\\brick_wall.png");
		((Image) image_background).addImageOfSpriteSheet("resources\\golbez.png",4,0,220,90,40,65);
		((Image) image_background).addImageOfSpriteSheet("resources\\bahamut.png",4,5,360,290,110,130);
		image_background.setBounds(0, 0, 494, 415);
		frmGameMenu.getContentPane().add(image_background);
		
		/*
		 * BUTTONS LISTENERS
		 */
		
		buttonPlayText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		buttonPlayGraphs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
