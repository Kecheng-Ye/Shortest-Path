public class Edge<T> {   // cited from https://codereview.stackexchange.com/questions/67970/graph-implementation-in-java-8;
	
    private Node<T> node1;

    private Node<T> node2;

    private double weight;

    public Edge(Node<T> node1, Node<T> node2, double weight) {
    	this.node1 = node1;
        this.node2 = node2;
        this.weight = weight;
    }
    
    public double weight() {
    	return weight;
    }
    
    public Node<T> fromNode() {
        return node1;
    }

    public Node<T> toNode() {
        return node2;
    }

    public boolean isBetween(Node<T> node1, Node<T> node2) {
        return (this.node1 == node1 && this.node2 == node2);
    }
}
