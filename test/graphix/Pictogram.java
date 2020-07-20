package graphix;

import java.util.Arrays;

import graphix.utils.IntRange;

public class Pictogram {

	public static final int LENGTH = 10;
	public static final int HALF = LENGTH / 2;
	public static final int NUMBER_OF_PIXELS = LENGTH * LENGTH; 

	private Pixel[][] data;

	public void set(int x, int y, Pixel value) {
		data[x][y] = value;
	}

	public void clear(int x, int y) {
		data[x][y] = Pixel.PMIN;
	}

	public void fill(int x, int y) {
		data[x][y] = Pixel.PMAX;
	}

	public void setLine(int startX, int startY, int xDirection, int yDirection, Pixel value) {
		int x = startX;
		int y = startY;
		while (x < LENGTH && y < LENGTH) {
			data[x][y] = value;
			x += xDirection;
			y += yDirection;
		}
	}

	public void fillLine(int startX, int startY, int xDirection, int yDirection) {
		setLine(startX, startY, xDirection, yDirection, Pixel.PMAX);
	}

	public Pixel get(int x, int y) {
		return data[x][y];
	}

	public String printData() {
		StringBuffer sb = new StringBuffer();
		for (var x : IntRange.zeroToExclusive(LENGTH)) {
			for (var y : IntRange.zeroToExclusive(LENGTH)) {
				sb.append(get(x, y) + " ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	public Pictogram() {
		data = new Pixel[LENGTH][LENGTH];
		for (int i = 0; i < data.length; i++) {
			Arrays.fill(data[i], new Pixel(Pixel.MIN));
		}
	}

	public static class HorizontalLine extends Pictogram {
		HorizontalLine() {
			fillLine(0, HALF, 1, 0);
		}
	}
	
	public static class VerticalLine extends Pictogram {
		VerticalLine() {
			fillLine(HALF, 0, 0, 1);
		}
	}

	public static class PerfectRectangle extends Pictogram {
		public PerfectRectangle() {
			fillLine(0, 0, LENGTH, 0);
			fillLine(0, LENGTH, LENGTH, LENGTH);
			fillLine(0, 0, 0, LENGTH);
			fillLine(LENGTH, 0, LENGTH, LENGTH);
		}
	}

	static class Circle extends Pictogram {
		public Circle() {
			// TODO
		}
	}

	static Pictogram overlap(Pictogram... pictos) {
		var overlapping = new Pictogram();
		for (var x : IntRange.zeroToExclusive(LENGTH)) {
			for (var y : IntRange.zeroToExclusive(LENGTH)) {
				for (var picto : pictos) {
					overlapping.set(x, y, new Pixel(overlapping.get(x, y).alpha() + picto.get(x, y).alpha()));
				}
			}
		}
		return overlapping;
	}
	

}
