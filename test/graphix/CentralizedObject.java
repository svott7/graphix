package graphix;

import java.util.ArrayList;
import java.util.List;

public class CentralizedObject {

	DetectedObject detectedObject;

	List<Coordinate> centralizedCoords;

	public CentralizedObject(DetectedObject input) {
		this.detectedObject = input;
		this.centralizedCoords = centralize(input);
	}

	public static List<Coordinate> centralize(DetectedObject input) {
		var boundary = input.boundaries;
		var centerX = (boundary.minX() + boundary.maxX()) / 2;
		var centerY = (boundary.minY() + boundary.maxX()) / 2;

		var centralizedCoords = new ArrayList<Coordinate>();
		for (var c : input.coords) {
			var cx = centerX - c.x();
			var cy = centerY - c.y();
			var centralizedCoord = new Coordinate(cx, cy);
			centralizedCoords.add(centralizedCoord);
		}

		return centralizedCoords;
	}
	
	public static int average(int a, int b) {
		return (a + b)/2;
	}

}
