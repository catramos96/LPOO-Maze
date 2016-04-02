package maze.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
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
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize(screenSize);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
	
		JButton buttonPlay = new JButton("Jogar");
		buttonPlay.setForeground(Color.WHITE);
		buttonPlay.setOpaque(false);
		buttonPlay.setContentAreaFilled(false);
		buttonPlay.setBorderPainted(false);
		buttonPlay.setBounds(250, 350,90,40);
		frame.getContentPane().add(buttonPlay);

		JButton buttonHowToPlay = new JButton("Como jogar");
		buttonHowToPlay.setForeground(Color.WHITE);
		buttonHowToPlay.setOpaque(false);
		buttonHowToPlay.setContentAreaFilled(false);
		buttonHowToPlay.setBorderPainted(false);
		buttonHowToPlay.setBounds(250, 400, 120,40);
		frame.getContentPane().add(buttonHowToPlay);

		JButton buttonExit = new JButton("Sair");
		buttonExit.setForeground(Color.WHITE);
		buttonExit.setOpaque(false);
		buttonExit.setContentAreaFilled(false);
		buttonExit.setBorderPainted(false);
		buttonExit.setBounds(250, 450, 90,40);
		frame.getContentPane().add(buttonExit);

		Image image_background = new Image("resources//background.png");
		
		image_background.setBounds(0, 0, ((Image) image_background).getBackGWidth(), ((Image) image_background).getBackGHeight());
		image_background.addImage("resources//Title.png",100, 100, 500,200);
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
