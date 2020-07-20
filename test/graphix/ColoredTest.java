package graphix;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Color;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class ColoredTest {

	NoneWhitePixel c = new NoneWhitePixel();

	@ParameterizedTest
	@MethodSource("getColoredPixel")
	void is(Color pixel) {
		assertTrue(c.is(pixel.getRGB()));
	}

	@Test
	void isNot() {
		assertFalse(c.is(Color.WHITE.getRGB()));
	}

	static Stream<?> getColoredPixel() {
		return Stream.of(Color.BLACK, Color.BLUE, Color.RED, Color.GREEN, Color.DARK_GRAY, Color.LIGHT_GRAY);
	}

}
