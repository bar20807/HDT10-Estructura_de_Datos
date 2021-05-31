/**
 * <h1>Graph</h1>
 * Interface used to get weighted
 * digraph operations.
 * <p>
 *
 * @author José Rodrigo Barrera García
 * Carnet: 20807
 * Universidad del Valle de Guatemala
 **/
public interface Graph {
    // Adds a node
    void addNode(String name);

    // Gets a node
    int getNode(String name);

    // Checks if the graph contains a node
    boolean containsNode(String name);

    // Adds/edits an edge
    void addEdge(String origin, String destiny, int weight);

    // Removes an edge
    void removeEdge(String origin, String destiny);

}
