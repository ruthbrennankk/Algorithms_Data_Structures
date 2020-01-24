

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class CompetitionDijkstra {

    private int slow, mid, fast;

    private EdgeWeightedDigraph graph;

    /**
     * @param filename: A filename containing the details of the city road network
     * @param sA,       sB, sC: speeds for 3 contestants
     */
    CompetitionDijkstra(String filename, int sA, int sB, int sC) {
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

    //the worst case scenario is that the slowest walker walks the longest path so this function returns the time value for that scenario
    int timeRequiredforCompetition() {
        if (graph != null) {
        	if (slow < 50 || fast > 100) return -1;

            double[][] dists = new double[graph.getV()][graph.getV()];
            for (int i = 0; i < graph.getV(); i++) {
                dists[i] = getDistance(i);
            }

            double distance = 0;
            for (double[] array : dists) {
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

    //This method gets the shortest distance at a vertex 
    private double[] getDistance(int v) {
        double[] distTo = new double[graph.getV()];

        for (int i = 0; i < graph.getV(); i++) {
        	distTo[i] = Double.POSITIVE_INFINITY;
        }  
        
        distTo[v] = 0.0;//distance to itself

        Comparator<Route> comparator = new MyComparator();
        PriorityQueue<Route> pq = new PriorityQueue<Route>(graph.getV(), comparator);

        pq.add(new Route(v, distTo[v]));
        while (!pq.isEmpty()) {
        	Route route = pq.poll();
        	if (route != null) {
        		for (DirectedEdge edge : graph.getAdj()[route.startVertex]) {
                    int startVertex = edge.v;
                    int endVertex = edge.w;
                    if (distTo[endVertex] > distTo[startVertex] + edge.weight) {
                        distTo[endVertex] = distTo[startVertex] + edge.weight;

                        boolean contains = false;
                        for (Route temp : pq) {
                            if (temp.startVertex == endVertex)
                                contains = true;
                        }
                        if (!contains) {
                        	pq.add(new Route(endVertex, distTo[endVertex]));
                        	
                        } else {
                        	for (Route temp : pq) {
                                if (temp.startVertex == endVertex) {
                                	pq.remove(temp);
                                	temp.weight = distTo[endVertex];
                                    pq.add(temp);
                                    break;
                                }
                            }
                        }
                    }
        		}
            
            }
        }
        return distTo;
    }
}
