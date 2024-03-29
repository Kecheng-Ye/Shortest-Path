import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Graph<T> {          // cited from https://codereview.stackexchange.com/questions/67970/graph-implementation-in-java-8;

    Map<T, Node<T>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>();
    }

    /**
     * Adds a vertex to the graph.
     * @param vertex        vertex to add
     */
    public boolean addVertex(T vertex,double latitude,double longtitude) {
        if (adjacencyList.containsKey(vertex)) {
            return false;
        }
        adjacencyList.put(vertex, new Node<>(vertex,latitude,longtitude));
        return true;
    }

    /**
     * Adds a directed edge between two vertices in the graph.
     * @param vertex1       vertex where the (directed) edge begins
     * @param vertex2       vertex where the (directed) edge ends
     */
    public boolean addEdge(T vertex1, T vertex2) {
        return addEdge(vertex1, vertex2, 0);
    }

    /**
     * Adds a weighted directed edge between two vertices in the graph.
     * @param vertex1       vertex where the (directed) edge begins
     * @param vertex2       vertex where the (directed) edge ends
     * @param d        weight of the edge
     */
    public boolean addEdge(T vertex1, T vertex2, double d) {
        if (!containsVertex(vertex1) || !containsVertex(vertex2)) {
            throw new RuntimeException("Vertex does not exist");
        }

        // add the edge
        Node<T> node1 = getNode(vertex1);
        Node<T> node2 = getNode(vertex2);
        return node1.addEdge(node2, d);
    }

    /**
     * Remove a vertex from the graph.
     * @param vertex        vertex to be removed
     * @return      true if the vertex was removed, false if no such vertex was found.
     */
    public boolean removeVertex(T vertex) {
        if (!adjacencyList.containsKey(vertex)) {
            return false;
        }

        // get node to be removed
        final Node<T> toRemove = getNode(vertex);

        // remove all incoming edges to node
        adjacencyList.values().forEach(node -> node.removeEdge(toRemove));

        // remove the node
        adjacencyList.remove(vertex);
        return true;
    }

    /**
     * Method to remove a directed edge between two vertices in the graph.
     * @param vertex1       vertex where the (directed) edge begins
     * @param vertex2       vertex where the (directed) edge ends
     * @return  true if the edge was removed, false if no such edge was found.
     */
    public boolean removeEdge(T vertex1, T vertex2) {
        if (!containsVertex(vertex1) || !containsVertex(vertex2)) {
            return false;
        }
        return getNode(vertex1).removeEdge(getNode(vertex2));
    }

    /**
     * Method to get the number of vertices in the graph.
     * @return      count of vertices
     */
    public int vertexCount() {
        return adjacencyList.keySet().size();
    }

    /**
     * Method to get the number of edges in the graph.
     * @return      count of edges
     */
    public int edgeCount() {
        return adjacencyList.values()
                .stream()
                .mapToInt(Node::getEdgeCount)
                .sum();
    }

    /**
     * Method to check if a vertex exists in the graph.
     * @param vertex    vertex which is to be checked
     * @return  returns true if the graph contains the vertex, false otherwise
     */
    public boolean containsVertex(T vertex) {
        return adjacencyList.containsKey(vertex);
    }

    /**
     * Method to check if an edge exists in the graph.
     * @param vertex1       vertex where the (directed) edge begins
     * @param vertex2       vertex where the (directed) edge ends
     * @return   returns true if the graph contains the edge, false otherwise
     */
    public boolean containsEdge(T vertex1, T vertex2) {
        if (!containsVertex(vertex1) || !containsVertex(vertex2)) {
            return false;
        }
        return getNode(vertex1).hasEdge(getNode(vertex2));
    }

    /**
     * Method to get the shortest path from startVertex to endVertex.
     * @param startVertex   vertex where the path begins
     * @param endVertex     vertex where the path ends
     * @return  list of vertices in the shortest path from startVertex to endVertex,
     *          null if no such path exists.
     */


    private void runBFS(T startVertex) {
        if (!containsVertex(startVertex)) {
            throw new RuntimeException("Vertex does not exist.");
        }

        // reset the graph
        resetGraph();

        // init the queue
        Queue<Node<T>> queue = new LinkedList<>();
        Node<T> start = getNode(startVertex);
        queue.add(start);

        // explore the graph
        while (!queue.isEmpty()) {
            Node<T> first = queue.remove();
            first.setVisited(true);
            first.edges().forEach(edge -> {
                Node<T> neighbour = edge.toNode();
                if (!neighbour.isVisited()) {
                    neighbour.setParent(first);
                    queue.add(neighbour);
                }
            });
        }
    }

    Node<T> getNode(T value) {
        return adjacencyList.get(value);
    }

    private void resetGraph() {
        adjacencyList.keySet().forEach(key -> {
            Node<T> node = getNode(key);
            node.setParent(null);
            node.setVisited(false);
        });
    }
    
    public List<T> shortestPath(T startVertex, T endVertex) {
        if (!containsVertex(startVertex) || !containsVertex(endVertex)) {
            return null;
        }
        dijkstra(startVertex);

        List<T> path = new ArrayList<>();
        Node<T> end = getNode(endVertex);
        
        while (end != null && end != getNode(startVertex)) {
            path.add(end.vertex());
            end = end.parent();
        }
        if (end == null) {
            return null;
        }
        else {
            path.add(startVertex);
        	Collections.reverse(path);
        }
        return path;
    }
    
    public void dijkstra(T a) {
    	PriorityQueue<Node<T>> queue=new PriorityQueue<Node<T>>();
        adjacencyList.keySet().forEach(key -> {
            Node<T> node = getNode(key);
            node.setParent(null);
            node.setVisited(false);
            node.setDistance(Double.MAX_VALUE);
            queue.add(node);
        });
        Node<T> start=getNode(a);
        queue.remove(start);
        start.setDistance(0);
        start.setVisited(true);
        queue.add(start);
             
        while(!queue.isEmpty()) {
        	Node<T> u=queue.poll();
        	u.setVisited(true);
        	for(Edge edge:u.edges()) {
            Node<T> neighbour = edge.toNode();
            if(neighbour.isVisited()==true) {
            	continue;
            }
            relax(u,neighbour,edge,queue);
        	}
        }
    }
  
    public void relax(Node<T> u,Node<T> v,Edge<T> edge,PriorityQueue<Node<T>> queue) {
    	queue.remove(v);
    	if((u.distance()+edge.weight())<v.distance()) {
    		v.setDistance(u.distance()+edge.weight());
    		v.setParent(u);
    	}
    	queue.add(v);
    }
}