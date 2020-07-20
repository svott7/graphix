package graphix.mermaid;

import graphix.graph.Graph;
import graphix.graph.Link;
import graphix.graph.Node;

public interface Printer {

	String printGraph(Graph graph);
	
	String printNode(Node node);

	String printLink(Link link);
	
	
}
