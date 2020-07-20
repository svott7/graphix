package graphix;

public class RGB {

	public static int getColors(int pixel) {
		return pixel & 0x00ffffff;
	}

	public static boolean isWhite(int pixel) {
		return maskRGB(pixel) == 0x00000000;
	}

	public static boolean isBlack(int pixel) {
		return maskRGB(pixel) == 0x00ffffff;
	}

	public static int maskRGB(int pixel) {
		return pixel & 0x00ffffff;
	}

	public static int maskAlpha(int pixel) {
		return pixel & 0xff000000;
	}

	public static int red(int pixel) {
		return (pixel & 0x00ff0000) >> 16;
	}

	public static int green(int pixel) {
		return (pixel & 0x0000ff00) >> 8;
	}

	public static int blue(int pixel) {
		return pixel & 0x000000ff;
	}

	public static String rgbToHexString(int pixel) {
		return String.format("0x%x", pixel);
	}
}
