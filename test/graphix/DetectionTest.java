package graphix;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import graphix.io.ImageFactory;
import graphix.utils.IntRange;

public class DetectionTest {

	public static final String TEST_FOLDER = "img";
	public static final File TEST_IMG_SHAPES = new File(TEST_FOLDER, "shapes.png");

	private Follow followAlgorithm;

	@Test
	public void shapeImage() throws IOException {
		var img = ImageIO.read(TEST_IMG_SHAPES);
		var analyzable = new AnalyzableImage(img);

		var objects = detectObjects(analyzable, new NoneWhitePixel());
		assertEquals(9, objects.size());
	}

	@Disabled
	@Test
	public void recognizeSingleObject() {
		var img = new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB);
		var g = img.getGraphics();
		g.setColor(Color.BLUE);
		g.drawLine(1, 1, 8, 1);
		g.drawLine(1, 5, 8, 5);

		var analyzable = new AnalyzableImage(img);
		var objects = detectObjects(analyzable, new NoneWhitePixel());

		assertEquals(2, objects.size());
	}

	@Test
	void noDotToDetect() {
		var image = ImageFactory.create(10, 10, Color.WHITE);
		var analyzable = new AnalyzableImage(image);
		var detected = detectObjects(analyzable, new NoneWhitePixel());
		assertEquals(0, detected.size());
	}

	@Test
	void oneDotToDetect() {
		var image = ImageFactory.create(10, 10, Color.WHITE);
		var dots = List.of(new Coordinate(2, 2));
		ImageFactory.setPixels(image, Color.BLACK, dots);
		var analyzable = new AnalyzableImage(image);
		var detected = detectObjects(analyzable, new NoneWhitePixel());
		assertEquals(1, detected.size());
	}

	@Test
	void detectAllDots() {
		var image = ImageFactory.create(10, 10, Color.WHITE);
		var dots = List.of( //
				new Coordinate(0, 0), //
				new Coordinate(2, 2), //
				new Coordinate(5, 5) //
		);
		ImageFactory.setPixels(image, Color.BLACK, dots);

		var analyzable = new AnalyzableImage(image);
		var detected = detectObjects(analyzable, new NoneWhitePixel());
		assertEquals(3, detected.size());
	}

	public List<DetectedObject> detectObjects(AnalyzableImage analyzable, ObjectPixel pixelDetector) {
		var detected = new ArrayList<DetectedObject>();
		followAlgorithm = new Follow(analyzable, pixelDetector, 1);

		for (var y : IntRange.zeroToExclusive(analyzable.getHeight())) {
			for (var x : IntRange.zeroToExclusive(analyzable.getWidth())) {
				if (analyzable.isSet(x, y)) {
					// already visited
					continue;
				}

				analyzable.set(x, y);

				int pixel = analyzable.getImagePixel(x, y);
				if (pixelDetector.is(pixel)) {
					var item = followAlgorithm.followObject(x, y);
					detected.add(new DetectedObject(item));
				}
			}
		}
		return detected;
	}

}
