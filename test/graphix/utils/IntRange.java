package graphix.utils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class IntRange {

	public static List<Integer> zeroToExclusive(int endExclusive) {
		return exclusive(0, endExclusive);
	}
	
	public static List<Integer> zeroToExclusive(Collection<?> collection) {
		return zeroToExclusive(collection.size());
	}
	
	public static <T> List<Integer> zeroToExclusive(T[] array) {
		return zeroToExclusive(array.length);
	}

	public static List<Integer> exclusive(int startInclusive, int endExclusive) {
		return intStreamToList(IntStream.range(startInclusive, endExclusive));
	}
	
	public static List<Integer> zeroToInclusive(int endInclusive) {
		return inclusive(0, endInclusive);
	}
	
	public static List<Integer> inclusive(int startInclusive, int endInclusive) {
		return intStreamToList(IntStream.rangeClosed(startInclusive, endInclusive));
	}
	
	private static List<Integer> intStreamToList(IntStream intStream) {
		return intStream.boxed().collect(Collectors.toList());
	}
}
