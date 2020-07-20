package graphix.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

class IntRangeTest {

	@Test
	void exclusiveFrom0() {
		assertEquals(List.of(0, 1, 2), IntRange.zeroToExclusive(3));
	}

	@Test
	void exclusive() {
		assertEquals(List.of(1, 2), IntRange.exclusive(1, 3));
	}
	
	@Test
	void inclusiveFrom0() {
		assertEquals(List.of(0, 1, 2, 3), IntRange.zeroToInclusive(3));
	}

	@Test
	void inclusive() {
		assertEquals(List.of(1, 2, 3), IntRange.inclusive(1, 3));
	}	

}
