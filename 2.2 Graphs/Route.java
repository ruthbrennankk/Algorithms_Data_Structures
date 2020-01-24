
import java.util.Comparator;

public class Route {
    int startVertex;
    double weight;

    Route(int startVertex, double weight){
        this.startVertex = startVertex;
        this.weight = weight;
    }
}

class MyComparator implements Comparator<Route>{

    public int compare(Route route1, Route route2) {
        return Double.compare(route1.weight, route2.weight);
    }
    
}

