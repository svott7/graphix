package graphix.io;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ReadImageTest {

	/**
	 * Prepared picture with particular properties<br>
	 * - size 50 x 50,<br>
	 * - white pixels in lower right corner, and<br>
	 * - black pixels in upper left corner
	 */
	private static final String TEST_IMAGE = "img/random50x50.png";

	static BufferedImage bufferedImage;

	@BeforeAll
	static void initImage() throws IOException {
		var inputFile = Paths.get(TEST_IMAGE).toFile();
		bufferedImage = ImageIO.read(inputFile);
	}

	@Test
	void readImage() {
		assertEquals(50, bufferedImage.getWidth());
		assertEquals(50, bufferedImage.getHeight());
	}

	@Test
	void testWhitePixel() {
		assertEquals(0xFFFFFFFF, bufferedImage.getRGB(bufferedImage.getWidth() - 1, bufferedImage.getHeight() - 1));
	}

	@Test
	void testBlackPixel() {
		assertEquals(0xFF000000, bufferedImage.getRGB(0, 0));
	}

}
