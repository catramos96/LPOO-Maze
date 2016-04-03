package maze.gui;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;

import maze.logi.Board;
import maze.logi.MazeBuilder;

public  class GameBoard extends  JPanel{

	private ArrayList<BufferedImage> hero;
	private ArrayList<BufferedImage> dragon;
	private ArrayList<BufferedImage> door;
	private BufferedImage wall;
	private BufferedImage ground;
	private BufferedImage sword;
	private Timer myTimer;

	// TEMPORARIO
	private Board board = new Board((new MazeBuilder()).buildMaze(10,3));

	public GameBoard() {
		try {
			dragon = Spritesheet("resources//bahamut.png",4,4);
			hero = Spritesheet("resources//golbez.png",4,4);
			door = Spritesheet("resources//door.png",2,1);
			wall = ImageIO.read(new File("resources//wall.png"));
			ground = ImageIO.read(new File("resources//ground.png"));
			sword = ImageIO.read(new File("resources//Sword.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		myTimer = new Timer(10, (arg) -> {imageAnimationStep();} );
		myTimer.start();
		//  requestFocusInWindow();// to receive keyboard events           
	}

	public GameBoard(Board b){
		board = b;

		try {
			dragon = Spritesheet("resources//bahamut.png",4,4);
			hero = Spritesheet("resources//golbez.png",4,4);
			door = Spritesheet("resources//door.png",2,1);
			wall = ImageIO.read(new File("resources//wall.png"));
			ground = ImageIO.read(new File("resources//ground.png"));
			sword = ImageIO.read(new File("resources//Sword.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		myTimer = new Timer(10, (arg) -> {imageAnimationStep();} );
		myTimer.start();
		//   requestFocusInWindow();// to receive keyboard events  
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
	}

	public  ArrayList<BufferedImage> Spritesheet(String image,int n_sprites_x,int n_sprites_y) throws IOException {
		ArrayList<BufferedImage> spritesheet = new ArrayList<BufferedImage>();
		BufferedImage temp = ImageIO.read(new File(image));
		int x = 0;
		int y = 0;
		int width = temp.getWidth() / n_sprites_x;
		int height = temp.getHeight() / n_sprites_y;
		for (int t = 0; t < n_sprites_y; t++) {
			for (int i = 0; i < n_sprites_x; i++) {
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
		int i = 0;
		int j = 0;
		int door_x = 0, door_y = 0;
		boolean door_open = !(board.dragonsAllDead());
		for(int y = 0; y < temp.length*40; y+=40)
		{
			for(int x = 0; x < temp[i].length*40; x+=40)
			{

				g.drawImage(ground, x, y, x+40, y+40, 0, 0, ground.getWidth(), ground.getHeight(), null);

				if(temp[i][j] == 'S'){
					door_x = x;
					door_y = y;
					g.drawImage(wall, x, y, x+40, y+40,0, 0, wall.getWidth(), wall.getHeight(), null);
					if(door_open)
						g.drawImage(door.get(0),door_x, door_y, door_x+40, door_y+40,0, 0, door.get(1).getWidth(), door.get(1).getHeight(), null);
					else
						g.drawImage(door.get(1),door_x, door_y, door_x+40, door_y+40,0, 0, door.get(0).getWidth(),door.get(0).getHeight(), null);

				}
				else if(temp[i][j] == 'X')
					g.drawImage(wall, x, y, x+40, y+40,0, 0, wall.getWidth(), wall.getHeight(), null);

				else if(temp[i][j] == 'D' || temp[i][j] == 'd' || temp[i][j] == 'F'){
					g.drawImage(dragon.get(0),x, y, x+40, y+40,0, 0, dragon.get(0).getWidth(), dragon.get(0).getHeight(), null);
					//door_open = false;
				}
				else if(temp[i][j] == 'H' || temp[i][j] == 'A')
					g.drawImage(hero.get(0),x+10, y+5, x+30, y+35,0, 0, hero.get(0).getWidth(), hero.get(0).getHeight(), null);
				else if(temp[i][j] == 'E'){
					g.drawImage(sword, x, y, x+40, y+40,0, 0, sword.getWidth(), sword.getHeight(), null);
					//door_open = false;
				}

				j++;
			}
			i++;
			j = 0;
		}
	}

}
