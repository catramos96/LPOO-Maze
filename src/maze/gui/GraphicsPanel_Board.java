package maze.gui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;

public class GraphicsPanel_Board extends JPanel {

	private static ArrayList<BufferedImage> hero;
	private ArrayList<BufferedImage> dragon;
	private BufferedImage wall;
	private BufferedImage ground;
	private Timer myTimer;

	private static JFrame buildFrame() {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setSize(400, 400);
		frame.setVisible(true);
		return frame;
	}

	public GraphicsPanel_Board() {
		try {
			hero = Spritesheet("bahamut.png");
			dragon = Spritesheet("ifrit.png");
			wall = ImageIO.read(new File("ground.png"));
			ground = ImageIO.read(new File("wall.png"));
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public void paintComponent(java.awt.Graphics g) {
		super.paintComponent(g);
		g.drawImage(hero.get(0), 0, 0, 31, 47, 0, 0, 32, 48, null);

	}

	public ArrayList<BufferedImage> Spritesheet(String image) throws IOException {
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

	public static void main(String[] args) throws IOException {
		JFrame frame = buildFrame();
		JPanel pane = new JPanel() {
			protected void paintComponent(java.awt.Graphics g) {
				super.paintComponent(g);
				g.drawImage(hero.get(0), 0, 0, hero.get(0).getHeight(), hero.get(0).getWidth(), 0, 0, 31, 47, null);
			}
		};
		frame.add(pane);
	}
}
