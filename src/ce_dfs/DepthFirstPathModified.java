package ce_dfs;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class DepthFirstPathModified {
	
	private static boolean[] marked;    // marked[v] = is there an s-v path?
    private static int[] edgeTo;        // edgeTo[v] = last edge on s-v path
    private final int s;         // source vertex

    /**
     * Computes a path between {@code s} and every other vertex in graph {@code G}.
     * @param G the graph
     * @param s the source vertex
     * @throws IllegalArgumentException unless {@code 0 <= s < V}
     */
    public DepthFirstPathModified(Graph G, int s) {
        this.s = s;
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        validateVertex(s);
        dfs(G, s);
    }
    
    // depth first search from v
    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }
    
    /**
     * Is there a path between the source vertex {@code s} and vertex {@code v}?
     * @param v the vertex
     * @return {@code true} if there is a path, {@code false} otherwise
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public boolean hasPathTo(int v) {
        validateVertex(v);
        return marked[v];
    }

    /**
     * Returns a path between the source vertex {@code s} and vertex {@code v}, or
     * {@code null} if no such path.
     * @param  v the vertex
     * @return the sequence of vertices on a path between the source vertex
     *         {@code s} and vertex {@code v}, as an Iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<Integer> pathTo(int v) {
        validateVertex(v);
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        for (int x = v; x != s; x = edgeTo[x])
            path.push(x);
        path.push(s);
        return path;
    }

    
 // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }
    

    /**
     * Unit tests the {@code DepthFirstPaths} data type.
     *
     * @param args the command-line arguments
     */
    @SuppressWarnings("static-access")
	public static void main(String[] args) {
        In in = new In(args[0]);
        Graph G = new Graph(in);
        int s = Integer.parseInt(args[1]);

        DepthFirstPathModified dfs = new DepthFirstPathModified(G, s);
        
        //Adjacency List
        StdOut.println("Adjacency List: ");
        StdOut.println("---------------");
        for (int v = 0; v < G.V(); v++) {
        	if (dfs.hasPathTo(v)) {
                String newPrint = v + ": ";
        		for(Integer pCrawl: G.adj(v)){ 
        			newPrint += pCrawl + "->"; 
                }
        		newPrint = newPrint.substring(0, newPrint.length() - 2);
                StdOut.println(newPrint);
            }
            else {
                StdOut.printf("%d to %d:  not connected\n", s, v);
            }
        }
        
        StdOut.println();
        
        //Loop through marked and edgeTo 
        StdOut.println("marked    edgeTo ");
        StdOut.println("------    ------");
        for (int v = 0; v < G.V(); v++) {
        	StdOut.printf("%-5s %5d\n", dfs.marked[v], dfs.edgeTo[v]); 
        }
        
        StdOut.println();
        
        //Original Output
        StdOut.println("Original Output: ");
        for (int v = 0; v < G.V(); v++) {
            if (dfs.hasPathTo(v)) {
                StdOut.printf("%d to %d:  ", s, v);
                for (int x : dfs.pathTo(v)) {
                    if (x == s) StdOut.print(x);
                    else        StdOut.print("-" + x);
                }
                StdOut.println();
            }

            else {
                StdOut.printf("%d to %d:  not connected\n", s, v);
            }
        }
    }
}
