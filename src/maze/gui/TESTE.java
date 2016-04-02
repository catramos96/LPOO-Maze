package maze.gui;


import maze.logi.Board;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.JButton;

public class TESTE {

	private JFrame frame;
	private Board tab;
	private GamePlay maze;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TESTE window = new TESTE();
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
	public TESTE() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 850, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel Options = new JPanel();
		Options.setBounds(22, 27, 800, 145);
		frame.getContentPane().add(Options);
		Options.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(172, 12, 80, 24);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Dragao", "Saida", "Heroi", "Espada", "Parede"}));
		Options.add(comboBox);
		
		JLabel lblObjecto = new JLabel("Objecto");
		lblObjecto.setBounds(34, 17, 70, 15);
		Options.add(lblObjecto);
		
		JLabel lblTextura = new JLabel("Textura");
		lblTextura.setBounds(34, 54, 130, 15);
		Options.add(lblTextura);
		
		JPanel textura = new JPanel();
		textura.setBounds(182, 48, 48, 43);
		Options.add(textura);
		
		JLabel lblTamanhoDoTabuleiro = new JLabel("Tamanho do Tabuleiro");
		lblTamanhoDoTabuleiro.setBounds(318, 17, 195, 15);
		Options.add(lblTamanhoDoTabuleiro);
		
		JSpinner maze_dimension = new JSpinner();
		maze_dimension.setBounds(486, 12, 48, 20);
		Options.add(maze_dimension);
		
		JButton btnGerarTabuleiro = new JButton("Gerar Tabuleiro");
		btnGerarTabuleiro.setBounds(328, 49, 185, 25);
		Options.add(btnGerarTabuleiro);
		
		 maze = new GamePlay (new Board());
		 maze.setBounds(22, 165, 800, 592);
		 frame.getContentPane().add(maze);
		
		
		/***
		 * 
		 */
		
		btnGerarTabuleiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 Integer size = (Integer)maze_dimension.getValue();
				 
				 if(size <= 4) return;
				 char[][] ntab = new char[size][size];
				 tab = new Board(ntab);
				 maze.setBounds(40, 200, 40*ntab.length, 40*ntab.length);
				 frame.setBounds(100, 100, 825+40*(ntab.length - 5), 250+40*ntab.length);
				 maze.setBoard(tab);
				
			}
		});
		
		
	}

	
}
