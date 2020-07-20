package graphix.mermaid;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import graphix.graph.Direction;
import graphix.graph.Graph;
import graphix.graph.Link;
import graphix.graph.LinkType;
import graphix.graph.Node;
import graphix.graph.Shape;

/**
 * examples are copied from https://mermaid-js.github.io/mermaid/#/flowchart
 * 
 * testable with https://mermaid-js.github.io/mermaid-live-editor
 */
public class MermaidTest {

	MermaidPrinter printer = new MermaidPrinter();

	@Test
	void singleNode() {
		var node = new Node("id", Shape.Rectangle, "");

		var nodes = new ArrayList<Node>();
		nodes.add(node);

		var linkes = new ArrayList<Link>();
		var graph = new Graph(Direction.LEFT_TO_RIGHT, nodes, linkes);

		var e = """
				graph LR
					id
				""".trim();

		assertEquals(e, printer.printGraph(graph));
	}

	@Test
	void nodeWithText() {
		singleNode("""
				graph LR
					id1[This is the text in the box]
				""", Shape.Rectangle, "This is the text in the box");
	}

	@Test
	void nodeWithRoundedEdges() {
		singleNode("""
				graph LR
					id1(This is the text in the box)
				""", Shape.Rounded_edges, "This is the text in the box");
	}

	@Test
	void circledNode() {
		singleNode("""
				graph LR
					id1((This is the text in the circle))
				""", Shape.Circle, "This is the text in the circle");
	}

	@Test
	void TrapzeoidDownNode() {
		singleNode("""
				graph LR
					id1[\\Go shopping/]
				""", Shape.Trapzeoid_Down, "Go shopping");
	}

	@Test
	void arrowLink() {
		var expected = """
				graph LR
					A
					B
					A-->B
				""";

		linkTest(expected, LinkType.ARROW, "");
	}

	@Test
	void openLink() {
		var expected = """
				graph LR
					A
					B
					A---B
				""";

		linkTest(expected, LinkType.PLAIN, "");
	}

	@Test
	void linkWithText() {
		var expected = """
				graph LR
					A
					B
					A---|This is the text|B
				""";
		linkTest(expected, LinkType.PLAIN, "This is the text");
	}

	private void linkTest(String expected, LinkType linkType, String linkText) {
		var a = Node.create("A");
		var b = Node.create("B");
		var link = new Link(a, b, linkType, linkText);

		var graph = new Graph(Direction.LEFT_TO_RIGHT, List.of(a, b), List.of(link));
		assertEquals(expected.trim(), printer.printGraph(graph));

	}

	private void singleNode(String expected, Shape shape, String nodeText) {
		var node = new Node("id1", shape, nodeText);
		var graph = new Graph(Direction.LEFT_TO_RIGHT, List.of(node), List.of());
		assertEquals(expected.trim(), printer.printGraph(graph));
	}
}
