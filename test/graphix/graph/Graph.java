package graphix.graph;

import java.util.List;

public class Graph {

	public Graph(Direction direction, List<Node> nodes, List<Link> linkes) {
		this.direction = direction;
		this.nodes = nodes;
		this.linkes = linkes;
	}

	public Direction direction;
	public List<Node> nodes;
	public List<Link> linkes;

}
