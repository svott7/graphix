package graphix;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import graphix.io.ImageFactory;

class FollowTest {

	static final int WIDTH = 10;
	static final int HEIGTH = 20;

	Follow follow;
	BufferedImage image;
	AnalyzableImage analyzable;
	ObjectPixel everything = p -> true;
	ObjectPixel none = p -> false;
	ObjectPixel black = p -> p == Color.BLACK.getRGB();

	@BeforeEach
	void initImage() {
		image = ImageFactory.create(WIDTH, HEIGTH, Color.WHITE);
		analyzable = new AnalyzableImage(image, 0, 1);
		resetPixelComparison(none);
	}

	private void resetPixelComparison(ObjectPixel p) {
		follow = new Follow(analyzable, p, 1);
	}
	
	private void drawObject(List<Coordinate> originalPixels) {
		resetPixelComparison(black);
		ImageFactory.setPixels(image, Color.BLACK, originalPixels);
	}

	@Test
	void isBlack() {
		assertTrue(black.is(Color.BLACK.getRGB()));
	}

	@Test
	void isNotBlack() {
		assertFalse(black.is(Color.BLUE.getRGB()));
		assertFalse(black.is(Color.WHITE.getRGB()));
	}

	@Test
	void noNeighbors() {
		resetPixelComparison(none);
		var pixels = follow.followObject(0, 0);
		assertEquals(1, pixels.size());
	}

	@Test
	void allArePart() {
		resetPixelComparison(everything);
		var pixels = follow.followObject(0, 0);
		assertEquals(WIDTH * HEIGTH, pixels.size());
	}

	@Test
	void oneNeighbor() {
		resetPixelComparison(black);
		image.setRGB(1, 1, Color.BLACK.getRGB());
		var pixels = follow.followObject(0, 0);
		assertEquals(2, pixels.size());
	}

	@Test
	void adjacentNeighbors() {
		var originalPixels = List.of( //
				new Coordinate(1, 0), //
				new Coordinate(1, 2), //
				new Coordinate(0, 1), //
				new Coordinate(2, 1) //
		);
		drawObject(originalPixels);
		var pixels = follow.followObject(1, 1);
		assertEquals(1 + originalPixels.size(), pixels.size());
	}

	@Test
	void diagonalNeighbors() {
		var originalPixels = List.of( //
				new Coordinate(0, 0), //
				new Coordinate(2, 0), //
				new Coordinate(0, 2), //
				new Coordinate(2, 2) //
		);
		drawObject(originalPixels);
		var pixels = follow.followObject(1, 1);
		assertEquals(1 + originalPixels.size(), pixels.size());
	}

	@Test
	void outsideBoundaries() {
		assertFalse(follow.isPixelPartOfObject(WIDTH, 0));
		assertFalse(follow.isPixelPartOfObject(WIDTH + 10, 0));

		assertFalse(follow.isPixelPartOfObject(0, HEIGTH));
		assertFalse(follow.isPixelPartOfObject(0, HEIGTH + 10));

		assertFalse(follow.isPixelPartOfObject(-1, 0));
		assertFalse(follow.isPixelPartOfObject(0, -1));
	}

	@Test
	void isPart() {
		resetPixelComparison(everything);
		assertTrue(follow.isPixelPartOfObject(0, 0));
	}

	@Test
	void isNotPart() {
		resetPixelComparison(none);
		assertFalse(follow.isPixelPartOfObject(0, 0));
	}

	@Test
	void isAlreadySet() {
		analyzable.set(0, 0);
		assertFalse(follow.isPixelPartOfObject(0, 0));
	}

}
