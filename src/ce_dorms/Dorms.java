package ce_dorms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

import edu.princeton.cs.algs4.In;

public class Dorms {
    static class Edge {
        int source;
        int destination;
        int weight;

        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    static class Graph {
        int vertices;
        ArrayList<Edge> allEdges = new ArrayList<>();
		@SuppressWarnings("unused")
		private In in;

//        Graph(In in) {
//			this.in = in;
//		}
		
        Graph(int vertices) {
            this.vertices = vertices;
        }

		public void addEdge(int source, int destination, int weight) {
            Edge edge = new Edge(source, destination, weight);
            allEdges.add(edge); //add to total edges
        }
        
        public void dormsMST(){
            PriorityQueue<Edge> pq = new PriorityQueue<>(allEdges.size(), Comparator.comparingInt(o -> o.weight));

            //add all the edges to priority queue, //sort the edges on weights
            for (int i = 0; i <allEdges.size() ; i++) {
                pq.add(allEdges.get(i));
            }

            //create a parent []
            int [] parent = new int[vertices];

            //makeset
            makeSet(parent);

            ArrayList<Edge> mst = new ArrayList<>();

            //process vertices - 1 edges
            int index = 0;
            while(index<vertices-1){
                Edge edge = pq.remove();
                //check if adding this edge creates a cycle
                int x_set = find(parent, edge.source);
                int y_set = find(parent, edge.destination);

                if(x_set==y_set){
                    //ignore, will create cycle
                }else {
                    //add it to our final result
                    mst.add(edge);
                    index++;
                    union(parent,x_set,y_set);
                }
            }
            //print MST
            printGraph(mst);
        }

        public void makeSet(int [] parent){
            //Make set- creating a new element with a parent pointer to itself.
            for (int i = 0; i <vertices ; i++) {
                parent[i] = i;
            }
        }

        public int find(int [] parent, int vertex){
            //chain of parent pointers from x upwards through the tree
            // until an element is reached whose parent is itself
            if(parent[vertex]!=vertex)
                return find(parent, parent[vertex]);;
            return vertex;
        }

        public void union(int [] parent, int x, int y){
            int x_set_parent = find(parent, x);
            int y_set_parent = find(parent, y);
            //make x as parent of y
            parent[y_set_parent] = x_set_parent;
        }

        public void printGraph(ArrayList<Edge> edgeList){
        	System.out.println("Dorms need to be connected: ");
            for (int i = 0; i <edgeList.size() ; i++) {
                Edge edge = edgeList.get(i);
                System.out.print(edge.source + "-" + edge.destination + " ");
            }
        	System.out.println("Dorms need a router: ");
            for (int i = 0; i <edgeList.size() ; i++) {
                Edge edge = edgeList.get(i);
                System.out.print(edge.source + " ");
            }
        	System.out.println("Total cost: $");
            for (int i = 0; i <edgeList.size() ; i++) {
                Edge edge = edgeList.get(i);
                System.out.print(edge.weight++);
            }
        }
    }
    
//    public static void main(String[] args) {
//        In in = new In("src/ce_dorms/GraphDorm.txt");
//        Graph graph = new Graph(in);
//        graph.addEdge(0, 1, 20);
//        graph.addEdge(1, 6, 15);
//        graph.addEdge(3, 4, 25);     
//        graph.addEdge(4, 5, 5);
//        graph.dormsMST();
//    }
    
//    6
//    10
//    0 1 20
//    0 3 45
//    0 4 75
//    1 3 50
//    1 6 15
//    3 4 25
//    3 5 30
//    3 6 65
//    4 5 5
//    5 6 45
    
    public static void main(String[] args) {
        int vertices = 6;
        Graph graph = new Graph(vertices);
        graph.addEdge(0, 1, 20);
        graph.addEdge(0, 3, 45);
        graph.addEdge(0, 4, 75);
        graph.addEdge(1, 3, 50);
        graph.addEdge(1, 6, 15);
        graph.addEdge(3, 4, 25);
        graph.addEdge(3, 5, 30);
        graph.addEdge(3, 6, 65);
        graph.addEdge(4, 5, 5);
        graph.addEdge(5, 6, 45);
        
        graph.dormsMST();
    }
}