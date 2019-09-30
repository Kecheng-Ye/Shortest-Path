import java.util.Scanner;

public class StreetMap {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		controlTable project=new controlTable();
		if(!(args[0].equals("ur.txt")||args[0].equals("monroe.txt")||args[0].equals("nys.txt"))){
			System.out.println("wrong data input");
		}else {
			String a=args[0];
			project=new controlTable(a,a.replace(a.substring(a.indexOf("."),a.length()),""));
			for(int i=0;i<args.length;i++) {
				if(args[i].contains("show")) {
					project.constructWindow();
				}
				if(args[i].contains("direction")) {
					project.FindShortestPath(args[i+1], args[i+2]);
				}
			}
		}
//		project=new controlTable("ur.txt","ur");
//		project.constructWindow();
//		project.FindShortestPath("SUEB", "CSB");
	}
}
