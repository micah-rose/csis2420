package ce_dfs_vs_bfs;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

// /Users/micahlund/eclipse-workspace/2420_GettingStarted/src/ce_dfs_vs_bfs/GraphExercise06.txt

public class DfsVsBfs {
	
	private static int source = 1;      // source vertex
	private static boolean[] marked;    // marked[v] = is there an s-v path?
    private static int[] edgeTo;        // edgeTo[v] = last edge on s-v path
    private int[] distTo;      			// distTo[v] = number of edges shortest s-v path
    private static final int INFINITY = Integer.MAX_VALUE;

	
    @SuppressWarnings("static-access")
	public DfsVsBfs(Graph G, int s) {
        this.source = s;
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        validateVertex(s);
        dfs(G, s);
        
        distTo = new int[G.V()];
        validateVertexBfs(s);
        bfs(G, s);
        
        assert check(G, s);
    }

/********************************************************************************************************/
    //Code for DFS Paths
/********************************************************************************************************/
    
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
        for (int x = v; x != source; x = edgeTo[x])
            path.push(x);
        path.push(source);
        return path;
    }

    
 // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

/********************************************************************************************************/
    //End of DFS Path code
/********************************************************************************************************/
    
    
/********************************************************************************************************/
    //Code for BFS Paths
/********************************************************************************************************/
 // breadth-first search from a single source
    private void bfs(Graph G, int s) {
        Queue<Integer> q = new Queue<Integer>();
        for (int v = 0; v < G.V(); v++)
            distTo[v] = INFINITY;
        distTo[s] = 0;
        marked[s] = true;
        q.enqueue(s);

        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    q.enqueue(w);
                }
            }
        }
    }


    /**
     * Returns the number of edges in a shortest path between the source vertex {@code s}
     * (or sources) and vertex {@code v}?
     * @param v the vertex
     * @return the number of edges in a shortest path
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int distTo(int v) {
        validateVertex(v);
        return distTo[v];
    }


    // check optimality conditions for single source
    private boolean check(Graph G, int s) {

        // check that the distance of s = 0
        if (distTo[s] != 0) {
            StdOut.println("distance of source " + s + " to itself = " + distTo[s]);
            return false;
        }

        // check that for each edge v-w dist[w] <= dist[v] + 1
        // provided v is reachable from s
        for (int v = 0; v < G.V(); v++) {
            for (int w : G.adj(v)) {
                if (hasPathTo(v) != hasPathTo(w)) {
                    StdOut.println("edge " + v + "-" + w);
                    StdOut.println("hasPathTo(" + v + ") = " + hasPathTo(v));
                    StdOut.println("hasPathTo(" + w + ") = " + hasPathTo(w));
                    return false;
                }
                if (hasPathTo(v) && (distTo[w] > distTo[v] + 1)) {
                    StdOut.println("edge " + v + "-" + w);
                    StdOut.println("distTo[" + v + "] = " + distTo[v]);
                    StdOut.println("distTo[" + w + "] = " + distTo[w]);
                    return false;
                }
            }
        }

        // check that v = edgeTo[w] satisfies distTo[w] = distTo[v] + 1
        // provided v is reachable from s
        for (int w = 0; w < G.V(); w++) {
            if (!hasPathTo(w) || w == s) continue;
            int v = edgeTo[w];
            if (distTo[w] != distTo[v] + 1) {
                StdOut.println("shortest path edge " + v + "-" + w);
                StdOut.println("distTo[" + v + "] = " + distTo[v]);
                StdOut.println("distTo[" + w + "] = " + distTo[w]);
                return false;
            }
        }

        return true;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertexBfs(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertices(Iterable<Integer> vertices) {
        if (vertices == null) {
            throw new IllegalArgumentException("argument is null");
        }
        int V = marked.length;
        for (int v : vertices) {
            if (v < 0 || v >= V) {
                throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
            }
        }
    }
    
/********************************************************************************************************/
    //End of BFS Path code
/********************************************************************************************************/


	//@SuppressWarnings("static-access")
	public static void main(String[] args) {
        In in = new In(args[0]); 
        Graph G = new Graph(in);
        
        DfsVsBfs vs = new DfsVsBfs(G, source);
      
        //Adjacency List
        StdOut.println("Adjacency List: ");
        StdOut.println("---------------");
        for (int v = 0; v < G.V(); v++) {
                String newPrint = v + ": ";
        		for(Integer pCrawl: G.adj(v)){ 
        			newPrint += pCrawl + "->"; 
                }
        		newPrint = newPrint.substring(0, newPrint.length() - 2);
                StdOut.println(newPrint);
        }
        
        StdOut.println();
       
        //Print comparisons DFS vs BFS 
        StdOut.println("Paths DFS:    Shortest Paths BFS: ");
        StdOut.println("----------    -------------------");
        for (int v = 0; v < G.V(); v++) {
                for (int x : vs.pathTo(v)) {
                    if (x == source) StdOut.print(x);
                    else        StdOut.print("->" + x);
                }
                StdOut.println();
        }

        StdOut.println("Paths DFS:    Shortest Paths BFS: ");
        StdOut.println("----------    -------------------");
        for (int v = 0; v < G.V(); v++) {
                for (int x : vs.pathTo(v)) {
                    if (x == source) StdOut.print(x);
                    else        StdOut.print("->" + x);
                }
                StdOut.println();
        }
    }
}