package maze.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.DropMode;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.Font;

public class GameInfo extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameInfo frame = new GameInfo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GameInfo() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 581, 386);
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);	
		contentPane.setLayout(null);
		setVisible(true);
		
		JTextArea txtrComoJogar = new JTextArea();
		txtrComoJogar.setFont(new Font("Courier New", Font.PLAIN, 13));
		txtrComoJogar.setEditable(false);
		txtrComoJogar.setBackground(Color.DARK_GRAY);
		txtrComoJogar.setForeground(Color.WHITE);
		txtrComoJogar.setText("COMO JOGAR\r\n\r\n\tPara jogar este jogo temos a opcao de criar o nosso \r\nproprio labirinto ou gerar um aleatoriamente. Podemos \r\nselecionar o numero de dragoes, o tamanho do labirinto \r\ne o tipo de dragoes.\r\n\tO tipo de dragoes sao: paralizado, aleatorio e \r\nadormecido. No primeiro, os dragoes nao se movem, no \r\nsegundo, movem-se e por ultimo, movem-se podendo \r\nainda ficar adormecidos nao mexendo de lugar.\r\n\tPara ganhar o jogo sera necessario equipar o heroi \r\ncom a espada e matar todos os dragoes. Se estiveres num\r\n quandrado adjacente ao dragao e nao estiveres armado \r\nperdes. Por fim deveras ir para a porta de saida.");
		txtrComoJogar.setBounds(45, 23, 478, 291);
		contentPane.add(txtrComoJogar);
		
		JPanel image_background = new Image("resources//backg.png");
		image_background.setBounds(0, 0, getWidth(), getHeight());
		getContentPane().add(image_background);
		image_background.setLayout(null);
	}
}
