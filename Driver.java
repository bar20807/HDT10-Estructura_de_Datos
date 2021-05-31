import java.util.Scanner;

/**
 * 
 * @author José Rodrigo Barrera García
 * Carnet: 20807
 * Universidad del Valle de Guatemala
 *
 */

public class Driver {
    public static void main(String[] args) {

        // User input
        Scanner input = new Scanner(System.in);
        String userSelection = "";
        // Origin and destination
        String originCity;
        String destinationCity;
        // Graph and Floyd operations
        WeightedGraph guatemalanGraph = new WeightedGraph("guategrafo.txt");
        FloydAlgorithm floydCalculations = new FloydAlgorithm();

        System.out.println("\n*****************************************************************************************\n" +
                "                   Bienvenido al navegador de centros de vacunación de Guatemala\n" +
                "*****************************************************************************************");

        // Program runs until user exits
        while(!userSelection.equals("4")) {
            System.out.print("\nMenu:\n(1) Calcular la distancia mas corta entre dos ciudades\n(2) Imprimir la ciudad" +
                    " en el centro del grafo\n(3) Modificar el grafo\n" +
                    "(4) Finalizar el programa e imprimir la matriz de adyacencia\n>> ");

            userSelection = input.nextLine();

            // Action to take according to input
            switch (userSelection) {

                // Get shortest distance between cities
                case "1":
                    // Available cities
                    guatemalanGraph.printCities();

                    System.out.print("\nIntroduzca la ciudad de origen:\n>> ");
                    originCity = input.nextLine();

                    System.out.print("Introduzca la ciudad de destino:\n>> ");
                    destinationCity = input.nextLine();

                    // Checks that input is correct
                    if(guatemalanGraph.containsNode(originCity) && guatemalanGraph.containsNode(destinationCity)) {
                        int shortestPath = floydCalculations.shortestPath(guatemalanGraph,
                                originCity.toLowerCase(),destinationCity.toLowerCase());

                        if(shortestPath < 9999999) {
                            // Finds the shortest path
                            System.out.println("El camino mas corto entre estas ciudades es: " + shortestPath + "\n" +
                                    "\n" + floydCalculations.getRoute(originCity, destinationCity));
                        } else {
                            // If not connected
                            System.out.println("No existe camino entre estas ciudades");
                        }

                    } else {
                        // Wrong input
                        System.out.println("Por favor revise que ambas ciudades esten escritas de forma correcta y sin" +
                                " espacios adicionales");
                    }

                    break;

                // Get the center of the graph
                case "2":
                    System.out.println("La ciudad en el centro del grafo es: " +
                            floydCalculations.getCenter(guatemalanGraph));
                    break;

                // Edit the graph
                case "3":
                    System.out.print("Introduzca el nombre de la ciudad de origen\n>> ");
                    originCity = input.nextLine();

                    // Checks that cities are correct
                    if(!guatemalanGraph.containsNode(originCity)) {
                        System.out.println("La ciudad de origen no es correcta");
                        break;
                    }

                    System.out.print("Introduzca el nombre de la ciudad de destino\n>> ");
                    destinationCity = input.nextLine();

                    if (!guatemalanGraph.containsNode(destinationCity)) {
                        System.out.println("La ciudad de destino no es correcta");
                        break;
                    }

                    System.out.print("Introduzca '1' si es una interrupcion en el trafico que no permite llegar o " +
                            "'2' si desea aÃ±adir una nueva conexion o modificar una existente.\n>> ");

                    userSelection = input.nextLine();

                    // Delete an association or edit one
                    if(userSelection.equals("1")) {
                        guatemalanGraph.addEdge(originCity, destinationCity, 9999999);
                    } else if (userSelection.equals("2")) {

                        // Verifies a correct weight has been introduced
                        try {
                            System.out.print("Introduzca el nuevo peso para la ruta:\n>> ");
                            guatemalanGraph.addEdge(originCity, destinationCity, Integer.parseInt(input.nextLine()));

                        } catch (Exception E) {
                            System.out.println("El nuevo peso introducido no es correcto");
                        }

                    } else {
                        System.out.println("La entrada no fue correcta");
                        break;
                    }

                    System.out.println("Se ha realizado la actualizacion, gracias por su retroalimentacion :)");
                    break;

                // Exit the graph and print the adjacency matrix
                case "4":
                    System.out.println("******************************************\n           Matriz de adyacencia" +
                            "\n******************************************");
                    guatemalanGraph.print();
                    break;

                // Option was not correct
                default:
                    System.err.println("La opcion introducida no es correcta");
            }
        }
    }
}
