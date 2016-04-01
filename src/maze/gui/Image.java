package maze.gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Image extends JPanel {
	private ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
	private ArrayList<Integer> images_x = new ArrayList<Integer>();
	private ArrayList<Integer> images_y = new ArrayList<Integer>();
	private ArrayList<Integer> images_sx = new ArrayList<Integer>();
	private ArrayList<Integer> images_sy = new ArrayList<Integer>();
	private BufferedImage background; //image in mosaics
	private int width;
	private int height;

	public Image(){};
	
	public Image(String background_path) {
		try {
			background = ImageIO.read(new File(background_path));
			width = background.getWidth();
			height = background.getHeight();
		} catch (IOException e) {
			e.printStackTrace();
		}       
	}
	
	public Image(String background_path,int bg_image_width,int bg_image_height) {
		try {
			background = ImageIO.read(new File(background_path));
			width = bg_image_width;
			height = bg_image_height;
		} catch (IOException e) {
			e.printStackTrace();
		}        
	}
	
	public void setBackground(String background_path,int bg_image_width,int bg_image_height){
		try {
			background = ImageIO.read(new File(background_path));
			width = bg_image_width;
			height = bg_image_height;
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public void addImage(String image_path,int pos_x,int pos_y,int scale_x,int scale_y){
		try {
			BufferedImage temp = ImageIO.read(new File(image_path));
			images.add(temp);
			images_x.add(pos_x);
			images_y.add(pos_y);
			images_sx.add(scale_x);
			images_sy.add(scale_y);
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public void addImageOfSpriteSheet(String image_path,int n_sprites_x, int number,int pos_x,int pos_y,int scale_x,int scale_y){
		try {
		BufferedImage temp = ImageIO.read(new File(image_path));
		
		int width = temp.getWidth() / n_sprites_x;
		int height = temp.getHeight() / n_sprites_x;
		
		int x;
		if(number < n_sprites_x)
			x = number*width;
		else
			x = number%n_sprites_x*width;
		int y = number / n_sprites_x*height;
		
		images.add(temp.getSubimage(x, y, width, height));
		images_x.add(pos_x);
		images_y.add(pos_y);
		images_sx.add(scale_x);
		images_sy.add(scale_y);
	} catch (IOException e) {
		e.printStackTrace();
	} 
		
	}

	public void paintComponent(java.awt.Graphics g) {
		super.paintComponent(g);
		if(background != null)
			drawBackground(g);
		drawImages(g);
	}
	
	public void drawImages (java.awt.Graphics g)
	{ 
		for (int i = 0; i < images.size(); i++) {
			g.drawImage(images.get(i), images_x.get(i), images_y.get(i), images_x.get(i)+images_sx.get(i), images_y.get(i)+images_sy.get(i), 0, 0,images.get(i).getWidth(), images.get(i).getHeight(), null);
		}
	}
	
	public void drawBackground (java.awt.Graphics g)
	{ 
		int jpanel_width = super.getWidth();
		int jpanel_height = super.getHeight();
		int x = 0, y = 0;
		
		for(y = 0;y < jpanel_height; y+=height){
			for(x=0;x < jpanel_width;x+=width){
				g.drawImage(background, x, y, x+width, y+height, 0, 0, width, height, null);
			}
		}
}
}