package graphix.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ImageViewer extends JFrame {

	public ImageViewer(BufferedImage image) {
		super();

		JPanel pane = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(image, 0, 0, null);
			}
		};
		
		add(pane);
		setSize(image.getWidth() + 100, image.getHeight() + 100);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Img Viewer");
		setVisible(true);
	}
}
