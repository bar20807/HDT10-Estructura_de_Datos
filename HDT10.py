import networkx as nx
# HDT10 Optional Python version
# José Rodrigo Barrera García
#Carnet: 20807
# Universidad del Valle de Guatemala
# This program builds a weighted graph from a txt file and finds the shortest route between two cities.
# It also allows for graph updates, and printing of the relationships.

# Graph
graph = nx.DiGraph()
# Builds the graph
with open("guategrafo.txt", 'r') as file:
    for line in file:
        graphData = line.split(' ')
        # Adds the nodes
        if not (graph.__contains__(graphData[0].lower())):
            graph.add_node(graphData[0].lower())
        elif not (graph.__contains__(graphData[1].lower())):
            graph.add_node(graphData[1].lower())
        # Adds edges
        graph.add_edge(graphData[0].lower(), graphData[1].lower(), weight=int(graphData[2]))

userInput = ""
# Actions by user
while userInput != "4":
    userInput = input("Menu: \n(1) Calcular la distancia mas corta\n(2) Imprimir la ciudad en el centro del grafo\n"
                      "(3) Modificar el grafo\n(4) Finalizar el programa e imprimir las relaciones\n>> ")
    # Get minimum distance
    if userInput == "1":
        # Origin and destination cities
        origin = input("Introduzca la ciudad de origen:\n>> ").lower()
        destination = input("Introduzca la ciudad de destino\n>> ").lower()

        # Finds the minimum distance if they are in the graph
        if graph.__contains__(origin) and graph.__contains__(destination):
            print("La distancia mas corta es:", nx.floyd_warshall(graph).get(origin)[destination])
        else:
            print("Por favor revise que las ciudades escritas sean correctas y no tengan espacios adicionales")

    # Get center of graph
    elif userInput == "2":
        print("El centro del grafo es: ")
        try:
            center = nx.center(graph)                   # Tries to find the center in the original graph
        except Exception:
            print("Tomando como si ahora las carreteras fueran en ambas direcciones: ")
            center = nx.center(graph.to_undirected())   # If not strongly connected
        print(center)   # Should be puertobarrios, but NetworkX cant find the correct center if not strongly connected

    # Edit the graph
    elif userInput == "3":
        # Origin and destination of edit
        origin = input("Introduzca la ciudad de origen:\n>> ").lower()
        destination = input("Introduzca la ciudad de destino\n>> ").lower()

        # If graph contains those cities
        if graph.__contains__(origin) and graph.__contains__(destination):
            updateMode = input("Introduzca '1' si la conexion entre las ciudades se interrumpio o '2' si desea "
                               "actualizar o agregar una nueva conexion:\n>> ")
            # Deletes the edge
            if updateMode == "1":
                graph.remove_edge(origin, destination)
            # Updates edge weight
            elif updateMode == "2":
                userWeight = input("Introduzca el nuevo peso (distancia) de la travesia:\n>> ")
                try:
                    graph.add_edge(origin, destination, weight=int(userWeight))
                    print("Se ha actualizado el grafo, muchas gracias por su retroalimentacion :)")
                except Exception:
                    print("El peso introducido no es correcto, no se ha podido actualizar")
                    
        # Cities were not correct
        else:
            print("Por favor revise que las ciudades escritas sean correctas y no tengan espacios adicionales")

    # Exit and print relationships
    elif userInput == "4":
        labels = nx.get_edge_attributes(graph, 'weight')
        print("(Origen, Destino): peso")
        print(labels)

    # Input was not correct
    else:
        print("La entrada no es correcta")
