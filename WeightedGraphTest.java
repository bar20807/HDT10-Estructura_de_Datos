import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class WeightedGraphTest {

	WeightedGraph graph = new WeightedGraph("testGraph.txt");
    Integer[][] graphMatrix = graph.getGraphMatrix();
    @Test
    public void addEdge() {
        graph.addEdge("centercity","secondcity",9);
        int weight = graphMatrix[2][1];
        assertEquals(weight, 9);
    }

    @Test
    public void removeEdge() {
        graph.removeEdge("mycity","secondcity");
        int weight = graphMatrix[0][1];
        assertEquals(weight, 9999999);
    }

    @Test
    public void containsNode() {
        assertTrue(graph.containsNode("mycity"));
    }
}
