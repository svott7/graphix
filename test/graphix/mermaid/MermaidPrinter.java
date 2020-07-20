package graphix.mermaid;

import java.util.stream.Collectors;

import graphix.graph.Direction;
import graphix.graph.Graph;
import graphix.graph.Link;
import graphix.graph.LinkType;
import graphix.graph.Node;
import graphix.graph.Shape;

/**
 * Mermaid 8.4
 *
 */
public class MermaidPrinter implements Printer {

	public String printNode(Node node) {
		StringBuilder sb = new StringBuilder();
		sb.append(node.id());
		if (!node.text().isEmpty()) {
			sb.append(openNodeType(node.shape(), node.text()));
		}
		return sb.toString();
	}

	public String openNodeType(Shape shape, String innerText) {
		var x = switch (shape) {
		case Rectangle -> "[%s]";
		case Circle -> "((%s))";
		case Rounded_edges -> "(%s)";
		case Rhombus -> "{%s}";
		case Hexagon -> "{{%s}}";
		case Parallelogram_Right -> "[/%s/]";
		case Parallelogram_Left -> "[\\%s\\]";
		case Trapezoid_Up -> "[/%s\\]";
		case Trapzeoid_Down -> "[\\%s/]";
		default -> throw new IllegalArgumentException("Unexpected value: " + shape);
		};
		return x.formatted(innerText);
	}

	@Override
	public String printGraph(Graph graph) {
		return "graph %s\n".formatted(printDirection(graph.direction)) //
				+ graph.nodes.stream() //
						.map(n -> printNode(n)) //
						.map(e -> "\t" + e) //
						.collect(Collectors.joining("\n")) //
				+ (graph.linkes.isEmpty() ? "" : "\n") //
				+ graph.linkes.stream() //
						.map(n -> printLink(n)) //
						.map(e -> "\t" + e) //
						.collect(Collectors.joining("\n"));
	}

	String printDirection(Direction direction) {
		return switch (direction) {
		case LEFT_TO_RIGHT -> "LR";
		case TOP_TO_BOTTOM -> "TB";
		};
	}

	@Override
	public String printLink(Link link) {
		return "%s%s%s".formatted(link.start().id(), printLinkType(link.type(), link.text()), link.end().id());
	}

	private String printLinkType(LinkType type, String text) {
		var strLink = switch (type) {
		case PLAIN -> "---";
		case DOTTED -> "-.-";
		case ARROW -> "-->";
		case THICK -> "===";
		case DOTTED_ARROW ->  "-.->";
		};
		return strLink + (text.isEmpty() ? "" : "|%s|".formatted(text));
	}

}
