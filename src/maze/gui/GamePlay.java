package maze.gui;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

import maze.logi.Board;
import maze.logi.MazeBuilder;

public  class GamePlay extends  JPanel{

	private ArrayList<BufferedImage> hero;
	private ArrayList<BufferedImage> dragon;
	private BufferedImage wall;
	private BufferedImage ground;
	private BufferedImage sword;
	private Timer myTimer;
	// TEMPORARIO
	private Board board = new Board((new MazeBuilder()).buildMaze(10,3));

	public GamePlay() {
		try {
			dragon = Spritesheet("resources\\bahamut.png");
			hero = Spritesheet("resources\\golbez.png");
			wall = ImageIO.read(new File("resources\\wall2.png"));
			ground = ImageIO.read(new File("resources\\wall_plain.png"));
			sword = ImageIO.read(new File("resources\\Sword.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		myTimer = new Timer(10, (arg) -> {imageAnimationStep();} );
		myTimer.start();
	    requestFocusInWindow();// to receive keyboard events           
	}
	
	public GamePlay(Board b){
		//this.addKeyListener(this);
		
		board = b;
		
		try {
			dragon = Spritesheet("resources\\bahamut.png");
			hero = Spritesheet("resources\\golbez.png");
			wall = ImageIO.read(new File("resources\\wall2.png"));
			ground = ImageIO.read(new File("resources\\ground.png"));
			sword = ImageIO.read(new File("resources\\Sword.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		myTimer = new Timer(10, (arg) -> {imageAnimationStep();} );
		myTimer.start();
	    requestFocusInWindow();// to receive keyboard events  
	}
	
	public void setBoard(Board b){
		board = b;
		repaint();
	}

	private void imageAnimationStep() {
		// TODO Auto-generated method stub

	}

	public void paintComponent(java.awt.Graphics g) {
		super.paintComponent(g);
		drawBoard (board,  g);

		/*
		 * drawImage(imagem,resizeXi,resizeYi,resizeXf,resizeYf,xi,yi,xf,yf,
		 * obs);
		 */

	}

	public  ArrayList<BufferedImage> Spritesheet(String image) throws IOException {
		ArrayList<BufferedImage> spritesheet = new ArrayList<BufferedImage>();
		BufferedImage temp = ImageIO.read(new File(image));
		int x = 0;
		int y = 0;
		int width = temp.getWidth() / 4;
		int height = temp.getHeight() / 4;
		for (int t = 0; t < 4; t++) {
			for (int i = 0; i < 4; i++) {
				spritesheet.add(temp.getSubimage(x, y, width, height));
				x += width;
			}
			x = 0;
			y += height;

		}
		return spritesheet;
	}		
	public void drawBoard (Board t, java.awt.Graphics g)
	{ 
		char [][] temp = t.getBoard();
		int x = 0;
		int y = 0;
		for(int i = 0; i < temp.length; i++)
		{
			for(int j = 0; j < temp[i].length; j++)
			{
				g.drawImage(ground, x, y, x+40, y+40, 0, 0, ground.getWidth(), ground.getHeight(), null);
				if(temp[i][j] == 'X')
					g.drawImage(wall, x, y, x+40, y+40,0, 0, wall.getWidth(), wall.getHeight(), null);
				else if(temp[i][j] == 'D' || temp[i][j] == 'd' || temp[i][j] == 'F')
					g.drawImage(dragon.get(0),x, y, x+40, y+40,0, 0, dragon.get(1).getWidth(), dragon.get(1).getHeight(), null);
				else if(temp[i][j] == 'H' || temp[i][j] == 'A')
					g.drawImage(hero.get(0),x+10, y, x+30, y+30,0, 0, hero.get(1).getWidth(), hero.get(1).getHeight(), null);
				else if(temp[i][j] == 'E')
					g.drawImage(sword, x, y, x+40, y+40,0, 0, sword.getWidth(), sword.getHeight(), null);
				x += 35;
			}
			x = 0;
			y += 35;
		}

	}

	/*@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode())
		{
		case KeyEvent.VK_LEFT: 
			gameTurn('a');
			break;

		case KeyEvent.VK_RIGHT: 
			gameTurn('d');
			break;

		case KeyEvent.VK_UP: 
			gameTurn('w');
			break;

		case KeyEvent.VK_DOWN: 
			gameTurn('s');
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
	
	public void gameTurn(char d){
		if(!board.exitBoard())
		{
			board.moveHero(d);
			board.moveRandomDragons();
			board.updateBoard();
			repaint();
		}
	}*/
}
