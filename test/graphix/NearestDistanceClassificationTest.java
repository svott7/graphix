package graphix;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import graphix.Pictogram.HorizontalLine;
import graphix.Pictogram.PerfectRectangle;
import graphix.Pictogram.VerticalLine;
import graphix.utils.IntRange;

public class NearestDistanceClassificationTest {

	@Test
	void zeroDistanceIfEqual() {
		var orgPic = new HorizontalLine();
		
		List<Coordinate> original = pictogramToCoordinates(orgPic);
		List<Coordinate> objectOfComparison = pictogramToCoordinates(orgPic);

		assertEquals(0, calcDistance(original, objectOfComparison));
	}
	
	@Disabled
	@Test
	void rectVsLine() {
		var originalIsLine = new HorizontalLine();
		var rect = new PerfectRectangle();
		
		var lineCoords = pictogramToCoordinates(originalIsLine);
		var rectCoords = pictogramToCoordinates(rect);
		
		System.out.println(calcDistance(lineCoords, rectCoords));
	}
	
	@Test
	void testClassify() {
		var inputPic = new Pictogram();
		inputPic.fillLine(Pictogram.HALF, 0, 0, 1);
		inputPic.fillLine(0, Pictogram.HALF, 1, 0);
		
		var coords = pictogramToCoordinates(inputPic);
		var result = classify(coords);
		System.out.println("=> " + result.getClass().getSimpleName());
	}
	
	Pictogram classify(List<Coordinate> inputCoords) {
		List<Pictogram> pics = List.of(new HorizontalLine(), new VerticalLine(), new PerfectRectangle());
		List<Integer> values = new ArrayList<>(pics.size());
		
		for (var p : pics) {
			var pCoords = pictogramToCoordinates(p);
			
			var d = calcDistance(inputCoords, pCoords);
			values.add(d);
			System.out.println("%s: %d".formatted(p.getClass().getSimpleName(), d));
		}
		
		assertEquals(pics.size(), values.size());
		
		// TODO two equal results
		int min = 0;
		for (var i : IntRange.zeroToExclusive(values)) {
			var value = values.get(i);
			var minValue = values.get(min);
			
			if (value < minValue) {
				min = i;
			}
		}
		
		return pics.get(min); 
	}
	
	int calcDistance(List<Coordinate> object1, List<Coordinate> object2) {
		int globalDistance = 0;
		for (var c1 : object1) {
			int localDistance = Integer.MAX_VALUE;
			for (var c2 : object2) {
				int newDistance = distance(c1, c2);
				if (newDistance < localDistance) {
					localDistance = newDistance;
				}
			}
			globalDistance += localDistance;
		}
		return globalDistance;
	}

	private int distance(Coordinate c1, Coordinate c2) {
		return Math.abs(c1.x() - c2.x()) + Math.abs(c1.y() - c2.y());
	}

	@ParameterizedTest
	@CsvSource({ "0, 2, 2, 2, 2", //
			"4, 1, 1, -1, -1", //
			"4, -1, -1, 1, 1", //
			})
	void testDistance(int result, int c1x, int c1y, int c2x, int c2y) {
		assertEquals(result, distance(new Coordinate(c1x, c1y), new Coordinate(c2x, c2y)));
	}

	List<Coordinate> pictogramToCoordinates(Pictogram pic) {
		List<Coordinate> coords = new ArrayList<>();
		for (var y : IntRange.zeroToExclusive(pic.LENGTH)) {
			for (var x : IntRange.zeroToExclusive(pic.LENGTH)) {
				if (pic.get(x, y).alpha() == 1) {
					coords.add(new Coordinate(x, y));
				}
			}
		}
		return coords;
	}

	@Test
	void testPictogramToCoords() {
		var pic = new Pictogram();
		pic.fillLine(0, Pictogram.HALF, 1, 0);
		var coords = pictogramToCoordinates(pic);
		assertEquals(Pictogram.LENGTH, coords.size());
		assertEquals(new Coordinate(0, Pictogram.HALF), coords.get(0));
		assertEquals(new Coordinate(Pictogram.LENGTH - 1, Pictogram.HALF), coords.get(coords.size() - 1));
	}
}
