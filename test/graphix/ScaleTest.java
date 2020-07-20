package graphix;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ScaleTest {

	@ParameterizedTest
	@CsvSource({ "6, 6, 3, 3", //
			"-4, 1, -2, 0", //
			"-5, 1, -2, 0" })
	void scale(int inputX, int inputY, int expX, int expY) {
		BoundaryBox inputBox = new BoundaryBox(0, 0, 20, 20); // center is 10, 10
		BoundaryBox targetBox = new BoundaryBox(0, 0, 10, 10); // center is 5, 5

		var scale = (double) targetBox.maxX() / inputBox.maxX();
		assertEquals(0.5, scale);

		var inputCoord = new Coordinate(inputX, inputY);
		var targetCoord = new Coordinate((int) (inputCoord.x() * scale), (int) (inputCoord.y() * scale));

		assertEquals(new Coordinate(expX, expY), targetCoord);
	}
}
