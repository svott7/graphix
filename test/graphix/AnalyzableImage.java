package graphix;

import java.awt.image.BufferedImage;

public class AnalyzableImage {

	private BufferedImage image;
	private int[][] pixelValue;
	private int clearedValue;
	private int setValue;

	public AnalyzableImage(BufferedImage image, int clearedValue, int setValue) {
		this.image = image;
		this.clearedValue = clearedValue;
		this.setValue = setValue;
		this.pixelValue = new int[getWidth()][getHeight()]; // Java: everything is zero by default
	}
	
	public AnalyzableImage(BufferedImage image) {
		this(image, 0, 1);
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public int getImagePixel(int x, int y) {
		return image.getRGB(x, y);
	}
	
	public int getWidth() {
		return image.getWidth();
	}
	
	public int getHeight() {
		return image.getHeight();
	}

	public int getValue(int x, int y) {
		return pixelValue[x][y];
	}
	
	public boolean isSet(int x, int y) {
		return getValue(x, y) == setValue;
	}
	
	public boolean isNotSet(int x, int y) {
		return getValue(x, y) != setValue;
	}

	public void clearValue(int x, int y) {
		pixelValue[x][y] = clearedValue;
	}

	public void setValueTo(int x, int y, int value) {
		pixelValue[x][y] = value;
	}
	
	public void set(int x, int y) {
		pixelValue[x][y] = setValue;
	}

}
