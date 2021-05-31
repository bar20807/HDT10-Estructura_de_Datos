import java.util.ArrayList;
import java.util.LinkedHashMap;
/**
 * <h1>FloydAlgorithm</h1>
 * Used to calculate minimum distance, routes, and
 * the center of the graph.
 * <p>
 *
 * @author Pablo Ruiz (PingMaster99)
 * @version 1.0
 * @since 2020-05-20
 **/
public class FloydAlgorithm implements FloydOperations{
    //Contains the route
    private ArrayList<String> route = new ArrayList<>();

    /**
     * Gets the shortest path between cities
     * @param graph
     * @param origin city
     * @param destination city
     * @return int with the length
     */
    public int shortestPath(WeightedGraph graph, String origin, String destination) {
        // Adjacency matrix
        Integer[][] shortestMatrix = shortestPathMatrix(graph);
        // Returns the minimum distance if entry not null
        if(shortestMatrix[graph.getNode(origin)][graph.getNode(destination)] != null) {
            return shortestMatrix[graph.getNode(origin)][graph.getNode(destination)];
        } else {
            return 99999999;
        }
    }

    /**
     * Gets the center of the graph
     * @param graph
     * @return String with the city name
     */
    public String getCenter(WeightedGraph graph) {
        // Adjacency matrix
        Integer[][] shortestPathMatrix = shortestPathMatrix(graph);
        int graphCenter = 0;
        int columnSum = 99999999;
        LinkedHashMap<Integer, String> integerMap = graph.getIntegerMap();
        // Adds every column
        for(int i = 0; i < shortestPathMatrix.length; i++) {
            int currentSum = 0;
            for(int j = 0; j < shortestPathMatrix.length; j++){
                currentSum += shortestPathMatrix[j][i];
            }
            // Updates center if added column is less than the previous minimum
            if(columnSum > currentSum) {
                graphCenter = i;
            }
        }
        route.clear();                      // Clears the route
        return integerMap.get(graphCenter); // Returns city name
    }

    /**
     * Gets the shortest path matrix of the graph
     * @param graph
     * @return Matrix with the shortest paths.
     */
    private Integer[][] shortestPathMatrix(WeightedGraph graph) {
        LinkedHashMap<Integer, String> integerToCity = graph.getIntegerMap();

        // Copies the adjacency matrix to not overwrite the original
        Integer[][] originalMatrix= graph.getGraphMatrix();
        Integer[][] adjacencyMatrix = new Integer[originalMatrix.length][originalMatrix.length];
        for(int i = 0; i < originalMatrix.length; i ++) {
            for(int j = 0; j < originalMatrix.length; j ++) {
                adjacencyMatrix[i][j] = originalMatrix[i][j];
            }
        }

        // Floyd Algorithm
        for(int k = 0; k < adjacencyMatrix.length; k++) {
            for(int i = 0; i < adjacencyMatrix.length; i++) {
                for(int j = 0; j < adjacencyMatrix.length; j++) {
                        if(adjacencyMatrix[i][j] > adjacencyMatrix[i][k] + adjacencyMatrix[k][j]) {
                            adjacencyMatrix[i][j] = adjacencyMatrix[i][k] + adjacencyMatrix[k][j];
                            if(!route.contains(integerToCity.get(k))) {
                                route.add(integerToCity.get(k));
                            }
                        }
                }
            }
        }
        return adjacencyMatrix;
    }

    /**
     * Gets the route between two cities
     * @param origin city
     * @param destination city
     * @return String with the route
     */
    public String getRoute(String origin, String destination) {
        StringBuilder stringRoute = new StringBuilder();
        stringRoute.append("La ruta es: ");
        // Builds the string according to the route
        for(String city : route) {
            if(!city.equals(destination.toLowerCase())) {
                stringRoute.append(city + "-");
            } else {
                stringRoute.append(destination.toLowerCase());
                break;
            }
        }

        route.clear();                  // Clears route
        return stringRoute.toString();  // Returns route in string
    }
}
