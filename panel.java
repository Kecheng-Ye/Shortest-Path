import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JPanel;

public class panel extends JPanel{
	private Graph<Object> graph;
	private static Double length;
	private static Double upmost;
	private static Double width;
	private static Double leftmost;
	private List<Object> list;
	
	public panel(Graph<Object> graph,Double length,Double upmost,Double width,Double leftmost) {
		super();
		this.list=new ArrayList<Object>();
		this.graph=graph;
		this.length=length;
		this.upmost=upmost;
		this.width=width;
		this.leftmost=leftmost;
		
	}
	
	public void paintComponent(Graphics g) {
		changeCoordinate();
		graph.adjacencyList.keySet().forEach(key -> {
			Node<Object> node=graph.getNode(key);
			g.fillOval((int)(double)(node.x()), (int)(double)(node.y()), 1, 1);
        });
		graph.adjacencyList.keySet().forEach(key -> {
			Node<Object> node=graph.getNode(key);
			for(Edge edge:node.edges()) {
				 Node<Object> neighbour = edge.toNode();
				 g.drawLine((int)(double)(node.x()),(int)(double)node.y(),(int)(double)neighbour.x(),(int)(double)neighbour.y());
			}
		});
		
		if(list.isEmpty()!=true) {
			Node temp=graph.getNode(list.get(0));
			for(Object ele:list) {
				Node node=graph.getNode(ele);
				 if(temp==node) {
					 continue;
				 }else {
					 g.setColor(Color.red);
					 g.drawLine((int)(double)node.x(),(int)(double)node.y(),(int)(double)temp.x(),(int)(double)temp.y());
					 repaint();
				 }
				 temp=node;
			}
		}
	}
	
	public void changeCoordinate() {
		int x=getWidth();
		int y=getHeight();
		graph.adjacencyList.keySet().forEach(key -> {
			Node<Object> node=graph.getNode(key);
			double xcoordinate=node.getlong();
			double ycoordinate=node.getLat();
			xcoordinate=Math.abs((xcoordinate-leftmost))*x/width;
			ycoordinate=Math.abs((ycoordinate-upmost))*y/length;
			node.setY(ycoordinate);
			node.setX(xcoordinate);
        });
	}
	
	public void drawShortestPath(List<Object> list) {
		this.list=list;
	}	
}
