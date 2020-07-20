package graphix;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Follow {

	private int stepWidth; // TODO remove?
	private AnalyzableImage analyzable;
	private ObjectPixel objectPixel;

	public Follow(AnalyzableImage analyzable, ObjectPixel objectPixel, int stepWidth) {
		this.stepWidth = stepWidth;
		this.analyzable = analyzable;
		this.objectPixel = objectPixel;
	}
	
	// assumes that the first one is part of pbject
	public List<Coordinate> followObject(int x, int y) {
		List<Coordinate> item = new ArrayList<>();
		Queue<Coordinate> queue = new LinkedList<>();

		queue.add(new Coordinate(x, y));

		while (!queue.isEmpty()) {
			var next = queue.poll();
			item.add(next);
			analyzable.set(x, y);

			addPixelToQueueIfPart(next, stepWidth, 0, queue);
			addPixelToQueueIfPart(next, stepWidth, stepWidth, queue);
			addPixelToQueueIfPart(next, stepWidth, -stepWidth, queue);
			addPixelToQueueIfPart(next, -stepWidth, 0, queue);
			addPixelToQueueIfPart(next, -stepWidth, stepWidth, queue);
			addPixelToQueueIfPart(next, -stepWidth, -stepWidth, queue);
			addPixelToQueueIfPart(next, 0, stepWidth, queue);
			addPixelToQueueIfPart(next, 0, -stepWidth, queue);
		}

		return item;
	}

	private void addPixelToQueueIfPart(Coordinate current, int xStep, int yStep, Queue<Coordinate> queue) {
		var x = current.x() + xStep;
		var y = current.y() + yStep;

		if (isPixelPartOfObject(x, y)) {
			queue.add(new Coordinate(x, y));
		}
	}

	public boolean isPixelPartOfObject(int x, int y) {
		if (x < 0 || x >= analyzable.getWidth()) {
			return false;
		}

		if (y < 0 || y >= analyzable.getHeight()) {
			return false;
		}

		// TODO separate concerns
		if (analyzable.isNotSet(x, y)) {
			analyzable.set(x, y);
			
			var pixel = analyzable.getImagePixel(x, y);
			if (objectPixel.is(pixel)) {
				return true;
			}
		}

		return false;
	}

}
