
import java.util.HashSet;

class EdgeWeightedDigraph {
	private int V;
    private int E;
    private HashSet<DirectedEdge>[] adj;

    EdgeWeightedDigraph(int V, int E) {
        this.V = V;
        this.E = E;

        adj = (HashSet<DirectedEdge>[]) new HashSet[V];
        for (int v = 0; v < V; v++)
            adj[v] = new HashSet<DirectedEdge>();
    }
    
    public int getV() {
    	return V;
    }
    
    public int getE() {
    	return E;
    }
    
    public HashSet<DirectedEdge>[] getAdj() {
    	return adj;
    }
    
    public void setE(int newE) {
    	E = newE;
    }
    
    public void setAdj(HashSet<DirectedEdge>[] newAdj) {
    	adj = newAdj;
    }

    public void addEdge(int startVertex, int endVertex, double weight){
        validateVertex(startVertex);
        validateVertex(endVertex);

        DirectedEdge e = new DirectedEdge(startVertex, endVertex, weight);
        adj[startVertex].add(e);
    }
    
    // throw an IllegalArgumentException unless {@code 0 <= v < V}
 	public void validateVertex(int v) {
 	    if (v < 0 || v >= V)
 	        throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
 	}
}
