package graphix.graph;

public record Node(String id, Shape shape, String text) {

	public static Node create(String id) {
		return new Node(id, Shape.Rectangle, "");
	}
}
