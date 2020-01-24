import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/*
 * 	
 *  
 *  Justify the choice of the data structures used in CompetitionDijkstra and CompetitionFloydWarshall:
 *  Directed Graph for the roads so that the functions will apply
 *  PQ as faster than 2d array but less complicated fibionacci heaps
 *  
 *  Explain theoretical differences in the performance of Dijkstra and Floyd-Warshall algorithms in the given problem. 
 *  Also explain how would their relative performance be affected by the density of the graph. 
 *  Which would you choose in which set of circumstances and why?
 *  
 *  Dijkstra’s Algorithm is one example of a single-source shortest, given a source vertex it finds shortest path from source to all other vertices.
 *  Floyd Warshall Algorithm is an example of all-pairs shortest path algorithm, meaning it computes the shortest path between all pair of nodes.
 *  
 *  Dijkstra has a run time of VlogV, as the graph gets larger it will take longer
 *  Floyd-Warshall has a run time of V^3, as the graph get larger this will take longer.
 *  
 *  Floyd Warshall is better for dense graphs but Dijkstra is better in general
 *  
 *  
 */

public class CompetitionTests {

    @Test
    public void testDijkstra() {
    	
    	int time = new CompetitionDijkstra(null, 62, 83, 94).timeRequiredforCompetition();
        assertEquals("text on null file", time, -1);
        
        time = new CompetitionDijkstra("tinyEWD.txt", 62, 83, 94).timeRequiredforCompetition();
        assertEquals("test on tinyEWD", time, 30);
        
        time = new CompetitionDijkstra("1000EWD.txt", 62, 83, 94).timeRequiredforCompetition();
        assertEquals("test on 1000EWD", time, 23);

        time = new CompetitionDijkstra("input-A.txt", 62, 83, 94).timeRequiredforCompetition();
        assertEquals("test on input-A", time, -1);
        
        time = new CompetitionDijkstra("input-B.txt", 62, 83, 94).timeRequiredforCompetition();
        assertEquals("test on input-B", time, 8065);
        
        time = new CompetitionDijkstra("input-C.txt", 62, 83, 94).timeRequiredforCompetition();
        assertEquals("test on input-C", time, -1);
        
        time = new CompetitionDijkstra("tinyEWD.txt", 32, 48, 62).timeRequiredforCompetition();
        assertEquals("test on speed too slow", time, -1);
        
        time = new CompetitionDijkstra("tinyEWD.txt", 120, 101, 119).timeRequiredforCompetition();
        assertEquals("test on speed too fast", time, -1);
    }
    
//input-H.txt
    @Test
    public void testFW() {
    	int time = new CompetitionFloydWarshall(null, 62, 83, 94).timeRequiredforCompetition();
        assertEquals("test on null file", time, -1);
        
        time = new CompetitionFloydWarshall("tinyEWD.txt", 62, 83, 94).timeRequiredforCompetition();
        assertEquals("test on tinyEWD", time, 30);
        
        time = new CompetitionFloydWarshall("1000EWD.txt", 62, 83, 94).timeRequiredforCompetition();
        assertEquals("test on 1000EWD", time, 23);

        time = new CompetitionFloydWarshall("input-A.txt", 62, 83, 94).timeRequiredforCompetition();
        assertEquals("test on input-A", time, -1);
        
        time = new CompetitionFloydWarshall("input-B.txt", 62, 83, 94).timeRequiredforCompetition();
        assertEquals("test on input-B", time, 8065);
        
        time = new CompetitionFloydWarshall("input-C.txt", 62, 83, 94).timeRequiredforCompetition();
        assertEquals("test on input-C", time, -1);
        
        time = new CompetitionFloydWarshall("tinyEWD.txt", 32, 48, 62).timeRequiredforCompetition();
        assertEquals("test on speed too slow", time, -1);
        
        time = new CompetitionFloydWarshall("tinyEWD.txt", 120, 101, 119).timeRequiredforCompetition();
        assertEquals("test on speed too fast", time, -1);
    }
    
}
