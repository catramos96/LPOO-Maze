package maze.gui;

import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Color;

public class GamePaly {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GamePaly window = new GamePaly();
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
	public GamePaly() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new GraphicsPanel_Board();
		panel.setBackground(Color.WHITE);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
	}

	public static class GraphicsPanel_Board extends JPanel {

		private static ArrayList<BufferedImage> hero;
		private ArrayList<BufferedImage> dragon = new ArrayList<BufferedImage>();
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
				hero = Spritesheet("resources\\bahamut.png");
				dragon = Spritesheet("resources\\ifrit.png");
				wall = ImageIO.read(new File("resources\\wall.png"));
				ground = ImageIO.read(new File("resources\\ground.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		public void paintComponent(java.awt.Graphics g) {
			super.paintComponent(g);
			g.drawImage(hero.get(2), 0, 0, 75, 75, 0, 0, 100, 100, null);
			/*
			 * drawImage(imagem,resizeXi,resizeYi,resizeXf,resizeYf,xi,yi,xf,yf,
			 * obs);
			 */

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

		/*
		 * public static void main(String[] args) throws IOException { JFrame
		 * frame = buildFrame(); JPanel pane = new JPanel() { protected void
		 * paintComponent(java.awt.Graphics g) { super.paintComponent(g);
		 * g.drawImage(hero.get(0), 0, 0, hero.get(0).getHeight(),
		 * hero.get(0).getWidth(), 0, 0, 31, 47, null); } };
		 * frame.getContentPane().add(pane); }
		 */
	}

}
