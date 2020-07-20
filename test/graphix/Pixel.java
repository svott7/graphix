package graphix;

public record Pixel(int alpha) {

	public static final int MIN = 0;
	public static final int MAX = 1;

	public static final Pixel PMAX = new Pixel(MAX);
	public static final Pixel PMIN = new Pixel(MIN);
	
	@Override
	public String toString() {
		return Integer.toString(alpha);
	}
}
