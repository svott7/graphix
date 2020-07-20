package graphix;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import graphix.Pictogram.HorizontalLine;
import graphix.Pictogram.PerfectRectangle;

class ClassificationTest {
	
	@Test
	void equalLine() {
		assertEquals(Pictogram.NUMBER_OF_PIXELS, matchingOvelap(new HorizontalLine(), new HorizontalLine()));
	}

	@Test
	void matchingLine() {

		Pictogram input = new Pictogram();
		input.setLine(0, Pictogram.LENGTH / 2, 1, 0, Pixel.PMAX);

		var line = new HorizontalLine();

		int matches = matchingOvelap(input, line);
		
		var overlap = Pictogram.overlap(input, line);
		
		assertEquals(Pictogram.NUMBER_OF_PIXELS, matches);
	}
	
	@Test
	void optLineIsLine() {
		assertEquals(new Line(0), classify(new HorizontalLine()));
	}
	
	@Test
	void optRectIsRect() {
		assertEquals(new Trapeziod(0), classify(new PerfectRectangle()));
	}
	
	@Test
	void aLineIsALine() {
		var line = new Pictogram();
		line.setLine(0, 1, 1, 0, Pixel.PMAX);
		assertEquals(new Line(0), classify(line));
	}
	
	public Geometrical classify(Pictogram input) {
		int max = 0;
		int lineMatching = matchingOvelap(new HorizontalLine(), input);
		int rectMatching = matchingOvelap(new PerfectRectangle(), input);
		
		System.out.println(lineMatching);
		System.out.println(rectMatching);
		
		if (lineMatching > rectMatching) {
			return new Line(0); 
		} else {
			return new Trapeziod(0);
		}
	}

	public int matchingOvelap(Pictogram expectedPicto, Pictogram actualPicto) {
		int matches = 0;
		for (int x = 0; x < Pictogram.LENGTH; x++) {
			for (int y = 0; y < Pictogram.LENGTH; y++) {
				var expected = actualPicto.get(x, y);
				var actual = expectedPicto.get(x, y);
				
				if (expected.alpha() == actual.alpha()) {
					matches++;
				}
			}
		}
		return matches;
	}

}
