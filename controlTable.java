import java.util.List;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class controlTable {
	private String inputfile;
	private static Graph<Object> graph;
	private static frame frame;
	private static String name;
	
	public controlTable(String file,String name) {
		this.name=name;
		frame=new frame();
		inputfile=file;
		graph=new Graph();
		importdata(inputfile);
	}
	
	public controlTable() {
		
	}
	
	public static void importdata (String inputfile) {
		try {
			FileInputStream file = new FileInputStream(inputfile);	 
			BufferedReader br = new BufferedReader(new InputStreamReader(file));
			String line = null;
			while ((line = br.readLine()) != null) {
				while(line.indexOf("\t")!=-1){
					line=line.replace("\t", " ");
					}
				String[] parts=line.split(" ");
				if(parts[0].equals("i")) {
					graph.addVertex(parts[1],Double.parseDouble(parts[2]),Double.parseDouble(parts[3]));	 //construct vertex
				}else if(parts[0].equals("r")) {
					graph.addEdge(parts[2], parts[3],CalculateDistance(graph.getNode(parts[2]),graph.getNode(parts[3]))); //construct edge
					graph.addEdge(parts[3], parts[2],CalculateDistance(graph.getNode(parts[2]),graph.getNode(parts[3])));
					}
			}
			}catch (IOException e){
				return;
			}finally{
			}
	}
		
	public static double CalculateDistance(Node a, Node b) {  //Algorithm cited from https://www.movable-type.co.uk/scripts/latlong.html
		double R=6371000;
		double lat1=a.getLat();
		double lat2=b.getLat();
		double lon1=a.getlong();
		double lon2=b.getlong();
		double r1 = toRadians(lat1);
		double r2 = toRadians(lat2);
		double rd = toRadians((lat2-lat1));
		double ld = toRadians((lon2-lon1));
		double A = Math.sin(rd/2) * Math.sin(rd/2) +
		        Math.cos(r1) * Math.cos(r2) *
		        Math.sin(ld/2) * Math.sin(ld/2);
		double c = 2 * Math.atan2(Math.sqrt(A), Math.sqrt(1-A));
		double d = R * c;
		return d;
	}
	
	public static double toRadians(double a) {
		return a*(Math.PI/180);
	}
	
	public static void FindShortestPath(Object start,Object end) {
		if(graph.containsVertex(start)!=true) {
			System.out.println("no such "+start+" location found!");
			return;
		}
		if(graph.containsVertex(end)!=true) {
			System.out.println("no such "+end+" location found!");
			return;
		}
		List<Object> result=graph.shortestPath(start, end);
		System.out.println("the path is "+result);
		System.out.println("total cost= "+graph.getNode(end).distance()/1609+" mile");
		if(frame.panel!=null) {
		frame.ShortestPath(result);
		}
	}
	
	public static void constructWindow() {
		frame=new frame(name,graph);
	}
}
