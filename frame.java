import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class frame extends JFrame{
	private static Double length;
	private static Double upmost;
	private static Double width;
	private static Double leftmost;
	static panel panel;
	private static HashMap<Object,Double>Latitude;
	private static HashMap<Object,Double>Longtitude;
	
	public frame(String name,Graph graph) {
		length=null;
		upmost=null;
		width=null;
		leftmost=null;
		ArrayList<Integer> Size=WindowSize(graph);
		setSize(Size.get(1),Size.get(0));
		setTitle(name+" map");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel=new panel(graph,length,upmost,width,leftmost);
		add(panel);
		setVisible(true);
	}
	
	public frame() {
		panel=null;
	}	
	
	public static ArrayList<Integer> WindowSize(Graph graph){
		ArrayList<Integer> result=new ArrayList<Integer>();
		ArrayList<Double> latitude=new ArrayList<Double>();
		ArrayList<Double> longtitude=new ArrayList<Double>();
        graph.adjacencyList.keySet().forEach(key -> {
            Node<Object> node = graph.getNode(key);
            latitude.add(node.getLat());
			longtitude.add(node.getlong());
        });
        
		length=Collections.max(latitude)-Collections.min(latitude);
		upmost=Collections.max(latitude);
		width=Collections.max(longtitude)-Collections.min(longtitude);
		double window=length/width;
		leftmost=Collections.min(longtitude);
		result.add((int)((double)window*1000));
		result.add(1000);
		return result;
	}
	
	public static void ShortestPath(List<Object> list) {
		//System.out.println("check");
		panel.drawShortestPath(list);
	}
}

