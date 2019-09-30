Name: KeCheng Ye
NetId:30642683
Class Id: 75
Lab Time: TUE THR 1650-1805
Assignment Number: Project 4
My TA: Sifan Ye
My Workshop leader: MeliSSa Kagaju
I did not collaborate with anyone on this assignment



There are two cited source code in my project 
1. The graph implementation(including Graph.java, Edge.java, Vertex.java)
   Cited from https://codereview.stackexchange.com/questions/67970/graph-implementation-in-java-8
2. The calculating formula of two Georgraphical coordinate(CalculateDistance function in controlTable.java)
   Cited from https://www.movable-type.co.uk/scripts/latlong.html

The whole structure of my project
There are four classes that was all written by my own which is StreetMap.java(Main), controlTable.java(importing data,constructing graph, 
make frame if it is necessary), frame.java and panel.java(Graphical classes made for visualizing the graph). For the other three cited classes
(Graph.java, Edge.java, Vertex.java), here are some small changes i made: I add new varaiables to the Vertex class which are distance(the data
used to implement Dijkstra),latitude, longitude,x(x coordinate of this point in the graphical window),y(y coordinate), and also I made the whole
class comparable (in order to make the vertex can be functioned in priority queue). For Graph class, I just write the Dijkstra and the findshortest
path function which can return the result of Dijkstra in the form of a list. 

The whole project function in this order
1.importdata from the raw document and then construct the edges and vertexs for the graph.
2.construct a new frame class which extends JFrame in order to construct a window if necessary.
3.In the frame class, it will envoke the panel class which extends JPanel, printing actual stuf on the panel.
4.In the panel class, it will first access the constructed graph and change the x y coordinate based on each vertex's longitude latitude and the window 
size. Then it will print each vertex on the window as long as the edges. Also, there is a default empty list in this class. In the situation that the foundshortestPath
is invoked, this class will contain the path and the panel will highlight the path with Color red correspondingly.

Run time Analysis:
For my whole project, Except the importdata, changeCoordinate, windowSize, paintComponent and the Dijkstra function, all the rest's time complexcity are clearly O(1). For
the mentioned functions above, all of them except Dijkstra are O(V) since they are nothing but just going through the whole AdjacencyList which will cost the programme to 
run the number of vertexs times for each function. While for Dijkstra, it both go through all the vertices and go through all the edges which will add up the time complexcity
to O(ElogV)