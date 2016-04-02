package maze.gui;


import maze.logi.Board;

import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
	private GameBoard maze;
	private Integer Selector = 0;
	private GameBoard texture;
	private Board image;
	private JComboBox comboBox;
	private JLabel lblValidInfo;
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
		
		comboBox = new JComboBox();
		comboBox.setBounds(172, 12, 80, 24);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Parede", "Saida", "Heroi", "Espada", "Dragao"}));
		Options.add(comboBox);
		
		JLabel lblObjecto = new JLabel("Objecto");
		lblObjecto.setBounds(34, 17, 70, 15);
		Options.add(lblObjecto);
		
		JLabel lblTextura = new JLabel("Textura");
		lblTextura.setBounds(34, 54, 130, 15);
		Options.add(lblTextura);
		
		image = new Board( new char [1][1]);
		image.placeOnBoard(new maze.logi.Point(0,0),'X');
		texture = new GameBoard(new Board());
		texture.setBounds(182, 48, 48, 43);
		texture.setBoard(image);
		Options.add(texture);
		
		JLabel lblTamanhoDoTabuleiro = new JLabel("Tamanho do Tabuleiro");
		lblTamanhoDoTabuleiro.setBounds(318, 17, 195, 15);
		Options.add(lblTamanhoDoTabuleiro);
		
		JSpinner maze_dimension = new JSpinner();
		maze_dimension.setBounds(486, 12, 48, 20);
		Options.add(maze_dimension);
		
		JButton btnGerarTabuleiro = new JButton("Gerar Tabuleiro");
		btnGerarTabuleiro.setBounds(328, 49, 185, 25);
		Options.add(btnGerarTabuleiro);
		
		 maze = new GameBoard (new Board());
		 maze.setBounds(22, 165, 800, 592);
		 frame.getContentPane().add(maze);
		

		JButton btnValidTab = new JButton("Validar Tabuleiro");
		btnValidTab.setBounds(600, 13, 185, 25);
		Options.add(btnValidTab);
		
		lblValidInfo = new JLabel("");
		lblValidInfo.setBounds(600, 35, 400,40);
		Options.add(lblValidInfo);
		
		
		/***
		 * 
		 */
		
		btnGerarTabuleiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 Integer size = (Integer)maze_dimension.getValue();
				 
				 if(size <= 4) return;
				 char[][] ntab = new char[size][size];
				 for(int i =0; i < ntab.length; i++)
					 for(int t = 0; t < ntab[i].length; t++)
						 	ntab[i][t] = ' ';
				 tab = new Board(ntab);
				 maze.setBounds(40, 200, 40*ntab.length, 40*ntab.length);
				 frame.setBounds(100, 100, 825+40*(ntab.length - 5), 250+40*ntab.length);
				 maze.setBoard(tab);
				
			}
		});
		
		
		btnValidTab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean valid = true;
				if(tab == null) return;
				
				if(test3x3()) 
				{
					lblValidInfo.setText("Não é permitido  quadrados  3x3!");
					valid = false;
				}
				
				if(testcross()) 
				{
					lblValidInfo.setText("Não é permitido quadrados paredes"
							        + "\n cruzadas com espaços brancos!");
					valid = false;
				}
				
				if(testborder())
				{
					lblValidInfo.setText("Parede exterior apenas contem Paredes e uma saida");
					valid = false;
				}
				if(testSolution())
				{
					lblValidInfo.setText("Não tem soluçao!");
					valid = false;
				}
				if(testDeadSpaces())
				{
					lblValidInfo.setText("Tem espaços nao jogaveis");
					valid = false;
				}
				
				char [][] m = tab.getBoard();
				if(testChar(m,'D') <= 0)
					{valid = false;
					lblValidInfo.setText("D ");
					}
				if(testChar(m,'H') != 1)
				{valid = false;
				lblValidInfo.setText("H ");
				}
				
				if(testChar(m,'E') != 1)
				{valid = false;
				lblValidInfo.setText("E ");
				}
				
				if(valid)			
					lblValidInfo.setText("OK");
				
					
				
			}
		});
		
		comboBox.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	switch (comboBox.getSelectedIndex()) {
				case 0: { 
					image = new Board( new char [1][1]);
					image.cleanPosition(new maze.logi.Point(0,0));
					image.placeOnBoard(new maze.logi.Point(0,0),'X');
					texture.setBoard(image);
					break;
				}
				case 1: { 
					image = new Board( new char [1][1]);
					image.cleanPosition(new maze.logi.Point(0,0));
					image.placeOnBoard(new maze.logi.Point(0,0),'S');
					texture.setBoard(image);
					break;
				}
			
				case 2: { 
					image = new Board( new char [1][1]);
					image.cleanPosition(new maze.logi.Point(0,0));
					image.placeOnBoard(new maze.logi.Point(0,0),'H');
					texture.setBoard(image);
					break;
				}
				case 3: { 
					image = new Board( new char [1][1]);
					image.cleanPosition(new maze.logi.Point(0,0));
					image.placeOnBoard(new maze.logi.Point(0,0),'E');
					texture.setBoard(image);
					break;
				}
				case 4: { 
					image = new Board( new char [1][1]);
					image.placeOnBoard(new maze.logi.Point(0,0),'D');
					texture.setBoard(image);
					break;
				}
				
				default:
					break;
				}
		    }
		});
		maze.addMouseListener(new MouseListener()
				{

					@Override
					public void mouseClicked(MouseEvent e) {
						
						Integer x = e.getX() / 40 ;
						Integer y = e.getY() / 40 ;
						switch(e.getButton()) 
						{
						case 0:
							break;
						case 1:
							if(tab.getBoard()[y][x] != ' ')return;
							UpdateTab( x,  y);
							maze.setBoard(tab);
							break;
						case 2:
							break;
						case 3:
							tab.placeOnBoard(new maze.logi.Point(x,y), ' ');
							maze.setBoard(tab);
							break;
						default:
							break;
						}
						
					}

					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
			
				});
		
		
	}
	private void UpdateTab(Integer x, Integer  y)
	{
		switch (comboBox.getSelectedIndex()) {
		
		case 0: { 
			tab.placeOnBoard(new maze.logi.Point(x,y), 'X');
			break;
		}
		case 1: { 
			tab.placeOnBoard(new maze.logi.Point(x,y), 'S');
			break;
		}
		case 2: { 
			tab.placeOnBoard(new maze.logi.Point(x,y), 'H');
			break;
		}
		case 3: { 
			tab.placeOnBoard(new maze.logi.Point(x,y), 'E');
			break;
		}
		case 4: { 
			tab.placeOnBoard(new maze.logi.Point(x,y), 'D');
			break;
		}
		
		default:
			break;
		}
		
	}

	private boolean test3x3()
	{
		char[][] t3x3 = { { 'X', 'X', 'X', }, { 'X', 'X', 'X', }, { 'X', 'X', 'X', }, };
		for (int i = 0; i < tab.getBoard().length - t3x3.length; i++)
			for (int j = 0; j < tab.getBoard().length - t3x3.length; j++) {
				boolean match = true;
				for (int y = 0; y < t3x3.length; y++)
					for (int x = 0; x < t3x3.length; x++)
						if (tab.getBoard()[i + y][j + x] != t3x3[y][x])
							match = false;
				if (match)
					return true;
			}
		return false;
	}
	
	private boolean testcross()
	{
		char[][] badSpaces = {
				{' ', ' '},
				{' ', ' '}};
		char[][] badDiagonalDown = {
				{'X', ' '},
				{' ', 'X'}};
		char[][] badDiagonalUp = {
				{' ', 'X'},
				{'X', ' '}};
		
		char [][] maze = tab.getBoard();
		for (int i = 0; i < maze.length - badSpaces.length; i++)
			for (int j = 0; j < maze.length - badSpaces.length; j++) {
				boolean match = true;
				boolean match_Down = true;
				boolean match_Up = true;
				for (int y = 0; y < badSpaces.length; y++)
					for (int x = 0; x < badSpaces.length; x++) {
						if (maze[i+y][j+x] != badSpaces[y][x])
							match = false;
						if (maze[i+y][j+x] != badDiagonalDown[y][x])
							 match_Down = false;
						if (maze[i+y][j+x] != badDiagonalUp[y][x])
							match_Up = false;
					}
				if (match || match_Down ||	 match_Up )
					return true;
			}		
		return false; 	
	}
	
	private boolean testborder()
	{
		int countExit = 0;
		int n = tab.getBoard().length;
		char [][] m = tab.getBoard();
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				if (i == 0 || j == 0 || i == n - 1 || j == n - 1)
					if (m[i][j] == 'S')
						if ((i == 0 || i == n-1) && (j == 0 || j == n-1))
							return false;
						else
							countExit++;
					else if (m[i][j] != 'X')
						return true;
		return countExit != 1;
	}
	private boolean testSolution()
	{	
		
		char[][] maze = tab.getBoard();
		maze.logi.Point p = findPos(maze,'S');
		if(p == null)
			return true;
		boolean [][] visited = new boolean[maze.length] [maze.length];
		
		visit(maze, p.getY(), p.getX(), visited);
		
		for (int i = 0; i < maze.length; i++)
			for (int j = 0; j < maze.length; j++)
				if (maze[i][j] != 'X' && ! visited[i][j] )
					return false;
		
		return true; 
	}
	private boolean testDeadSpaces()
	{
		return false;
	}
	
	private int testChar(char [][] m, char test)
	{
		int count = 0;
		for (int x = 0; x < m.length; x++)			
			for (int y = 0; y < m.length; y++)
				if(test == m[x][y])
					count ++;
		return count;
		
	}
	
	private void visit(char[][] m, int i, int j, boolean [][] visited) {
		if (i < 0 || i >= m.length || j < 0 || j >= m.length)
			return;
		if (m[i][j] == 'X' || visited[i][j])
			return;
		visited[i][j] = true;
		visit(m, i-1, j, visited);
		visit(m, i+1, j, visited);
		visit(m, i, j-1, visited);
		visit(m, i, j+1, visited);
	}
	private maze.logi.Point findPos(char [][] maze, char c) {
		for (int x = 0; x < maze.length; x++)			
			for (int y = 0; y < maze.length; y++)
				if (maze[y][x] == c)
					return new maze.logi.Point(y, x);
		return null;		
	}
	
}
