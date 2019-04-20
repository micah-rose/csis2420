package ce_directed_graphs;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class DirectedDFSModified {
    private boolean[] marked;  
    private int count;         

    public DirectedDFSModified(Digraph G, int s) {
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    public DirectedDFSModified(Digraph G, Iterable<Integer> sources) {
        marked = new boolean[G.V()];
        for (int v : sources) {
            if (!marked[v]) dfs(G, v);
        }
    }

    private void dfs(Digraph G, int v) { 
        count++;
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) dfs(G, w);
        }
    }

    public boolean marked(int v) {
        return marked[v];
    }

    public int count() {
        return count;
    }

    public static void main(String[] args) {
    	String fileName = "src/ce_directed_graphs/tinyDG.txt";
    	int s = 2; // start vertex
    	
        // read in digraph from command-line argument
        In in = new In(fileName);
        Digraph G = new Digraph(in);

        // read in sources from command-line arguments
        Bag<Integer> sources = new Bag<Integer>();
        sources.add(s);

        // multiple-source reachability
        DirectedDFSModified dfs = new DirectedDFSModified(G, sources);
        
        StdOut.println("Adjecency List");
        for (int i = 0; i < G.V(); i++) {
        	String newPrint = i + ": ";
        	for (int adj : G.adj(i)) {
        		newPrint += adj + "->";
        	}
    		newPrint = newPrint.substring(0, newPrint.length() - 2);
    		StdOut.println(newPrint);
        }
        
        StdOut.println("\nDirectedDFS using DiGraph and tinyDG.txt Sources: " + s);

        // print out vertices reachable from sources
        for (int v = 0; v < G.V(); v++) {
            if (dfs.marked(v)) StdOut.print(v + " ");
        }
        StdOut.println();      
    }
}
