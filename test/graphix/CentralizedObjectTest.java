package graphix;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CentralizedObjectTest {

	@ParameterizedTest
	@MethodSource("getSame")
	void averageEqualNumbers(int a) {
		assertEquals(a, CentralizedObject.average(a, a));
	}

	static Stream<Arguments> getSame() {
		return Stream.of( //
				Arguments.of(0),
				Arguments.of(2)
				// FIXME
//				Arguments.of(Integer.MIN_VALUE),
//				Ar8guments.of(Integer.MAX_VALUE)
				);
	}

}
