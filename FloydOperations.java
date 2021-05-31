/**
 * <h1>FloydOperations</h1>
 * Interface used to get the shortest path, center, and
 * routes in a weighted digraph.
 * <p>
 *
 * @author Pablo Ruiz (PingMaster99)
 * @version 1.0
 * @since 2020-05-20
 **/
public interface FloydOperations {

    // Shortest path between two nodes
    int shortestPath(WeightedGraph graph, String origin, String destination);

    // Center node of the graph
    String getCenter(WeightedGraph graph);

    // Route to get from a node to another
    String getRoute(String origin, String destination);
}
