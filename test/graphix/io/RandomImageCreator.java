package graphix.io;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.stream.IntStream;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class RandomImageCreator {

	@TempDir
	File tempDir;
	
	@Test
	void test() {
		int width = 50;
		int height = width;

		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);


		IntStream.range(0, height).forEach(y -> {
			IntStream.range(0, width).forEach(x -> {
				int alpha = (int) (Math.random() * 256);
				int red = (int) (Math.random() * 256);
				int green = (int) (Math.random() * 256);
				int blue = (int) (Math.random() * 256);

				int rgb = (alpha << 24) | (red << 16) | (green << 8) | blue;

				img.setRGB(x, y, rgb);
			});
		});
		
		try {
			File outputFile = new File("random.png");
			ImageIO.write(img, "png", outputFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
