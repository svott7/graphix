package graphix.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class ImageViewerTest {

	public static void main(String[] args) {
		var img = new BufferedImage(150, 150, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) img.getGraphics();
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, img.getWidth(), img.getHeight());
		
		g.setColor(Color.BLUE);
		g.setStroke(new BasicStroke(10));
		g.drawLine(5, img.getHeight() / 2, img.getWidth()-5, img.getHeight() / 2);
		
		var viewer = new ImageViewer(img);
	}
}
