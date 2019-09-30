import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Node<T> implements Comparable<Node<T>>{  // cited from https://codereview.stackexchange.com/questions/67970/graph-implementation-in-java-8;

    private T vertex;
    
    private Double x;
    
    private Double y;
    
    private double latitude;
    
    private double longtitude;

    private List<Edge<T>> edges;

    private Node<T> parent;

    private boolean isVisited;
    
    private Double distance;

    public Node(T vertex,double latitude,double longtitude) {
        this.distance=null;
        this.parent=null;
        this.x=null;
        this.y=null;
    	this.vertex = vertex;
        this.latitude=latitude;
        this.longtitude=longtitude;
        this.edges = new ArrayList<>();
    }

    public T vertex() {
        return vertex;
    }

    public boolean addEdge(Node<T> node, double weight) {
        if (hasEdge(node)) {
            return false;
        }
        Edge<T> newEdge = new Edge<>(this, node, weight);
        return edges.add(newEdge);
    }

    public boolean removeEdge(Node<T> node) {
        Optional<Edge<T>> optional = findEdge(node);
        if (optional.isPresent()) {
            return edges.remove(optional.get());
        }
        return false;
    }

    public boolean hasEdge(Node<T> node) {
        return findEdge(node).isPresent();
    }

    private Optional<Edge<T>> findEdge(Node<T> node) {
        return edges.stream()
                .filter(edge -> edge.isBetween(this, node))
                .findFirst();
    }

    public List<Edge<T>> edges() {
        return edges;
    }

    public int getEdgeCount() {
        return edges.size();
    }

    public Node<T> parent() {
        return parent;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean isVisited) {
        this.isVisited = isVisited;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }
    
    public double getLat() {
    	return latitude;
    }
    
    public double getlong() {
    	return longtitude;
    }
    
    public void setLat(double lat) {
    	this.latitude=lat;
    }
    
    public void setLong(double lon) {
    	this.longtitude=lon;
    }
    
    public void setDistance(double dis) {
    	this.distance=dis;
    }

    public double distance() {
    	return distance;
    }

    public Double x() {
    	return x;
    }
    
    public Double y() {
    	return y;
    }
    
    public void setX(double x) {
    	this.x=x;
    }
    
    public void setY(double y) {
    	this.y=y;
    }
    
	@Override
	public int compareTo(Node<T> a) {
		// TODO Auto-generated method stub
		return this.distance.compareTo(a.distance());
	}
}
