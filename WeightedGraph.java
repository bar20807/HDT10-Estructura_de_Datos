import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedHashMap;
import java.util.Set;
/**
 * <h1>WeightedGraph</h1>
 * Class used to implement a weighted digraph
 * to store Guatemalan cities and distances
 * <p>
 *
 * @author José Rodrigo Barrera García
 *Carnet: 20807
 *Universidad del Valle de Guatemala
 **/
public class WeightedGraph implements Graph{

    // Dictionaries that store city names and positions
    private LinkedHashMap<String, Integer> citiesMap = new LinkedHashMap<>();
    private LinkedHashMap<Integer, String> integerMap = new LinkedHashMap<>();
    // Adjacency matrix
    private Integer[][] graphMatrix;
    // Width/height of the matrix
    private int numberOfCities = -1;

    /**
     * Constructor generates a weighted graph from a file
     * @param fileName
     */
    WeightedGraph(String fileName) {
        // Reads the document
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = "";

            while((line = reader.readLine()) != null) {

                String[] graphElement = line.split(" ");
                String origin = graphElement[0].toLowerCase();
                String destination = graphElement[1].toLowerCase();

                // Adds the nodes if necesary
                if(!citiesMap.containsKey(origin)) {
                    addNode(origin);
                }
                if (!citiesMap.containsKey(destination)) {
                    addNode(destination);
                }
            }

            // Initializes the adjacency matrix
            graphMatrix = new Integer[numberOfCities + 1][numberOfCities + 1];
            matrixInitialization();

            // Adds the edges to the matrix
            BufferedReader reader2 = new BufferedReader(new FileReader(fileName));

            while((line = reader2.readLine()) != null) {

                String[] graphElement = line.split(" ");
                addEdge(graphElement[0].toLowerCase(), graphElement[1].toLowerCase(), Integer.parseInt(graphElement[2]));
            }

            // Diagonal has a weight of zero
            for(int i = 0; i < citiesMap.size(); i ++) {
                graphMatrix[i][i] = 0;
            }

        } catch (Exception E) {
            System.err.println("El grafo no se ha podido generar, por favor revise el archivo de origen");
        }
    }

    /**
     * Adds an edge to the matrix
     * @param origin city
     * @param destiny city
     * @param weight of the edge
     */
    public void addEdge(String origin, String destiny, int weight) {
        if(citiesMap.containsKey(origin.toLowerCase()) && citiesMap.containsKey(destiny.toLowerCase())) {
            graphMatrix[citiesMap.get(origin.toLowerCase())][citiesMap.get(destiny.toLowerCase())] = weight;
        }
    }

    /**
     * Initializes the adjacency matrix set to infinity
     */
    private void matrixInitialization(){
        for(int i = 0; i < graphMatrix.length; i++) {
            for(int j = 0; j < graphMatrix.length; j++) {
                graphMatrix[i][j] = 9999999;
            }
        }
    }

    /**
     * Prints the adjacency matrix
     */
    public void print() {

        // Key of the matrix
        Set<String> cities = citiesMap.keySet();
        System.out.println("[Numero] representa la posicion de la ciudad en fila y columna de la matriz de adyacencia");

        for(String city : cities) {
            System.out.print(city + "[" + citiesMap.get(city)+ "] ");
        }

        System.out.println("\n");

        // Prints the matrix
        for(int i = 0; i < citiesMap.size(); i++) {
            for(int j = 0; j < citiesMap.size(); j++) {
                if(graphMatrix[i][j] == 9999999) {
                    System.out.print("INF ");
                } else {
                    System.out.print(graphMatrix[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Prints all available cities
     */
    public void printCities() {
        Set<String> cities = citiesMap.keySet();
        System.out.print("Las ciudades disponibles son: ");
        for(String city : cities) {
            System.out.print(city + " ");
        }
    }

    /**
     * Removes an edge
     * @param origin city
     * @param destiny city
     */
    public void removeEdge(String origin, String destiny) {
        if(graphMatrix[citiesMap.get(origin.toLowerCase())][citiesMap.get(destiny.toLowerCase())] != 9999999) {
            graphMatrix[citiesMap.get(origin.toLowerCase())][citiesMap.get(destiny.toLowerCase())] = 9999999;
        }
    }

    /**
     * Adds a node to the graph
     * @param cityName name of the node
     */
    public void addNode(String cityName) {
        numberOfCities++;
        citiesMap.put(cityName.toLowerCase(), numberOfCities);
        integerMap.put(numberOfCities, cityName.toLowerCase());
    }

    /**
     * Gets the matrix position of the node
     * @param cityName name of the node
     */
    public int getNode(String cityName) {
        return citiesMap.get(cityName.toLowerCase());
    }

    /**
     * Checks if a node is contained in the graph
     * @param name name of the node
     * @return boolean (true if graph contains the node)
     */
    public boolean containsNode(String name) {
        if(citiesMap.containsKey(name.toLowerCase())) {
            return true;
        }
        return false;
    }

    /**
     * Returns the graph adjacency matrix
     * @return the graph adjacency matrix
     */
    public Integer[][] getGraphMatrix() {
        return this.graphMatrix;
    }

    /**
     * Returns the linked hash map that contains position in
     * matrix to city name.
     * @return LinkedHashMap integer to city
     */
    public LinkedHashMap<Integer, String> getIntegerMap() {
        return integerMap;
    }
}
