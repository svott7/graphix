package graphix;

import java.util.List;

public class DetectedObject {

	BoundaryBox boundaries; // caches

	List<Coordinate> coords;
	
	public DetectedObject(List<Coordinate> pixels) {
		this.coords = pixels;
		this.boundaries = new BoundaryBox(pixels);
	}
}
