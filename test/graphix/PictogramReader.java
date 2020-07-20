package graphix;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;

import javax.imageio.ImageIO;

public class PictogramReader {

	public static Pictogram fromFile(Path file) throws IOException {
		BufferedImage img = ImageIO.read(file.toFile());
		return fromImage(img);
	}

	public static Pictogram fromImage(BufferedImage image) {
		Pictogram pic = new Pictogram();
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				int pixel = image.getRGB(x, y);
				int red = (pixel & 0x00ff0000) >> 16;
				int green = (pixel & 0x0000ff00) >> 8;
				int blue = pixel & 0x000000ff;

				int value = (pixel > 0x555555) ? 1 : 0; // TODO

				pic.set(pixel, y, new Pixel(value));
			}
		}
		
		return pic;
	}
}
