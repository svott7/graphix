package graphix.io;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.List;

import graphix.Coordinate;
import graphix.utils.IntRange;

public class ImageFactory {

	public static BufferedImage create(int width, int height, Color color) {
		var image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (var y : IntRange.zeroToExclusive(image.getHeight())) {
			for (var x : IntRange.zeroToExclusive(image.getWidth())) {
				image.setRGB(x, y, Color.WHITE.getRGB());
			}
		}
		return image;
	}

	public static void setPixels(BufferedImage image, Color color, List<Coordinate> pixels) {
		for (var p : pixels) {
			image.setRGB(p.x(), p.y(), color.getRGB());
		}
	}
}
