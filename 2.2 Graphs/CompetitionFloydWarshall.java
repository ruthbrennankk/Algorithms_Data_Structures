

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class CompetitionFloydWarshall {
    
    private int slow, mid, fast;

    private EdgeWeightedDigraph graph;

    CompetitionFloydWarshall(String filename, int sA, int sB, int sC) {

        
      //initalise speeds
    	if (sA<sB && sA<sC) {
    		slow = sA;
    		if (sB< sC) {
    			mid = sB;
    			fast = sC;
    		} else {
    			mid = sC;
    			fast = sB;
    		}
    	} else if (sB<sA && sB<sC) {
    		slow = sB;
    		if (sA< sC) {
    			mid = sA;
    			fast = sC;
    		} else {
    			mid = sC;
    			fast = sA;
    		}
    	} else {
    		slow = sC;
    		if (sB< sA) {
    			mid = sB;
    			fast = sA;
    		} else {
    			mid = sA;
    			fast = sB;
    		}
    	}

		//initalize graphs
    	initaliseGraph(filename);
    }
    
    //read in data line by line, assign the first line to number of vertices and the second to number of edges, create a graph object
    //use the next lines until null to add edges to the graph
    public void initaliseGraph(String filename) {
    	BufferedReader b;
        try {
            if (filename != null) {
                b = new BufferedReader(new FileReader(filename));
                try {
                    String input = b.readLine();
                    int V = Integer.parseInt(input);
                    input = b.readLine();
                    int E = Integer.parseInt(input);
                    if (V==0 && E==0 ) {
                    	graph = null;
                    } else {
                    	graph = new EdgeWeightedDigraph(V, E);
                        input = b.readLine();
                        while (input != null) {
                                String[] temp = input.trim().split("\\s+");
                                graph.addEdge(Integer.parseInt(temp[0]),Integer.parseInt(temp[1]), Double.parseDouble(temp[2]));
                                input = b.readLine();
                        }
                    }
                    
                } catch (IOException e) {
                    e.printStackTrace();
                } 
                try {
                    b.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                graph = null;
            }
        } catch (FileNotFoundException e) {
            graph = null;
        }
    }

    
    public int timeRequiredforCompetition() {
        if (graph != null) {
            if (slow < 50 || fast > 100) return -1;

            double[][] distTo = new double[graph.getV()][graph.getV()];
            DirectedEdge[][] edgeTo = new DirectedEdge[graph.getV()][graph.getV()];

            for (int v = 0; v < graph.getV(); v++) {
                for (int w = 0; w < graph.getV(); w++) {
                    distTo[v][w] = Double.POSITIVE_INFINITY;
                }
            }

            for (int v = 0; v < graph.getV(); v++) {
                for (DirectedEdge e : graph.getAdj()[v]) {
                    distTo[e.v][e.w] = e.weight;
                    edgeTo[e.v][e.w] = e;
                }
                
                if (distTo[v][v] >= 0.0) {
                    distTo[v][v] = 0.0;
                    edgeTo[v][v] = null;
                }
            }

            for (int i = 0; i < graph.getV(); i++) {
                for (int v = 0; v < graph.getV(); v++) {
                    for (int w = 0; w < graph.getV(); w++) {
                        if (distTo[v][w] > distTo[v][i] + distTo[i][w]) {
                            distTo[v][w] = distTo[v][i] + distTo[i][w];
                            edgeTo[v][w] = edgeTo[i][w];
                        }
                    }
                }
            }
            
            double distance = 0; //finding the largest distance
            for (double[] array : distTo) {
                for (double dist : array) {
                    if (distance < dist)
                    	distance = dist;
                }
            }
            if (Double.POSITIVE_INFINITY == distance) {
                return -1;
            } else {
                int time = (int) Math.ceil((distance * 1000) / slow);
                return time;
            }
        }

        return -1;
    }

}