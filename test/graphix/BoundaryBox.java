package graphix;

import java.util.List;

public record BoundaryBox(int minX, int minY, int maxX, int maxY) {

	public BoundaryBox(List<Coordinate> coords) {
		if (coords.isEmpty()) {
			throw new IllegalArgumentException("Empty list of coordinates is not allowed!");
		}

		// TODO write faster algo and compare run time
		minX = coords.parallelStream().map(c -> c.x()).reduce(Integer.MAX_VALUE, Integer::min);
		minY = coords.parallelStream().map(c -> c.y()).reduce(Integer.MAX_VALUE, Integer::min);
		maxX = coords.parallelStream().map(c -> c.x()).reduce(Integer.MIN_VALUE, Integer::max);
		maxY = coords.parallelStream().map(c -> c.y()).reduce(Integer.MIN_VALUE, Integer::max);
	}

}
