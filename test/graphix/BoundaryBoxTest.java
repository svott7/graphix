package graphix;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

class BoundaryBoxTest {

	@Test
	void testException() {
		assertThrows(IllegalArgumentException.class, () -> new BoundaryBox(List.of()));
	}

	public void testBoundaryBox(BoundaryBox box, int minX, int maxX, int minY, int maxY) {
		assertEquals(minX, box.minX());
		assertEquals(maxX, box.maxX());
		assertEquals(minY, box.minY());
		assertEquals(maxY, box.maxY());
	}

	@Test
	void singlePoint() {
		var box = new BoundaryBox(List.of(new Coordinate(1, 2)));
		testBoundaryBox(box, 1, 1, 2, 2);
	}

	@Test
	void line() {
		var box = new BoundaryBox(List.of( //
				new Coordinate(1, 3), //
				new Coordinate(3, 1)));

		testBoundaryBox(box, 1, 3, 1, 3);
	}

	@Test
	void trapeziod() {
		var box = new BoundaryBox(List.of( //
				new Coordinate(1, 3),
				new Coordinate(2, 2),
				new Coordinate(3, 1),
				new Coordinate(4, 2),
				new Coordinate(5, 3),
				new Coordinate(4, 4),
				new Coordinate(3, 5)
				));
		
		testBoundaryBox(box, 1, 5, 1, 5);
	}

}
