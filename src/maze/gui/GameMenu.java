package maze.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;

public class GameMenu {

	private JFrame frame;

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
		frame.setResizable(false);
		frame.setBounds(100, 100, 500, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel game_name = new JLabel("Jogo do Labirinto");
		game_name.setForeground(new Color(0, 0, 0));
		game_name.setFont(new Font("Stencil", Font.BOLD, 34));
		game_name.setBounds(62, 62, 379, 40);
		frame.getContentPane().add(game_name);
		
		JButton btnJogarComGrficos = new JButton("Jogar com gr\u00E1ficos");
		btnJogarComGrficos.setBounds(153, 191, 172, 25);
		frame.getContentPane().add(btnJogarComGrficos);
		
		JButton btnComoJogar = new JButton("Como jogar");
		btnComoJogar.setBounds(153, 247, 172, 25);
		frame.getContentPane().add(btnComoJogar);
		
		JButton btnSair = new JButton("Sair");
		btnSair.setBounds(153, 304, 172, 25);
		frame.getContentPane().add(btnSair);
		
		JButton play_game_buttons = new JButton("Jogar em \u00E1rea de texto");
		play_game_buttons.setBounds(153, 137, 172, 25);
		frame.getContentPane().add(play_game_buttons);
		
		JPanel image_background = new Image("resources\\wall_plain.png");
		//((Image) image_background).addImage("resources\\sword.png",10,10,10,50);
		((Image) image_background).addImageOfSpriteSheet("resources\\bahamut.png",5,360,290,110,130);
		image_background.setBounds(0, 0, 494, 415);
		frame.getContentPane().add(image_background);
	}
}
