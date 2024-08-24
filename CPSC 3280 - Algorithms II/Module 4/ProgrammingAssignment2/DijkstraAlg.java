// Jonathan Elder
// CSPC 3280 - Algorithms 2
// Programming Assignment 2 Module 4
// DijkstraAlg.java

import java.io.*;
import java.util.*;

public class DijkstraAlg {

    public static void main(String[] args) {
		
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please give the name of the input file to perform Dijkstra's Algorithm on: ");
        String inputFile = scanner.nextLine();
        String outputFile = "outputShortestPaths.txt";

        try {
			
            GraphDijkstraAlg graph = GraphDijkstraAlg.readGraph(inputFile);
			
            Map<String, Integer> distances = dijkstra(graph);
            writeOutput(outputFile, graph, distances, new HashMap<>());
            displayResults(graph, distances, new HashMap<>());
			
            System.out.println("For the given input file " + inputFile + ". The shortest paths were saved to " + outputFile);
        } catch (IOException e) {
            System.out.println("Uh OHH!! A problem happenned: " + e.getMessage());
        }
    }

    public static Map<String, Integer> dijkstra(GraphDijkstraAlg graph) {
		
        String source = graph.getSourceVertex();
		
        Map<String, Integer> distances = new HashMap<>();
        Map<String, String> previous = new HashMap<>();
		
        PriorityQueue<GraphDijkstraAlg.Edge> activeQueue = new PriorityQueue<>(Comparator.comparingInt(edge -> edge.weight));

        graph.getVertices().forEach(v -> {
            distances.put(v, Integer.MAX_VALUE);
            previous.put(v, null);
        });
        distances.put(source, 0);
        activeQueue.add(graph.new Edge(source, 0));

        while (!activeQueue.isEmpty()) {
            GraphDijkstraAlg.Edge current = activeQueue.poll();
			
            for (GraphDijkstraAlg.Edge edge : graph.getEdges(current.vertex)) {
				
                int newDist = distances.get(current.vertex) + edge.weight;
				
                if (newDist < distances.get(edge.vertex)) {
					
                    distances.put(edge.vertex, newDist);
                    previous.put(edge.vertex, current.vertex);
                    activeQueue.add(graph.new Edge(edge.vertex, newDist));
                }
            }
        }
        return distances;
    }

    public static void writeOutput(String fileName, GraphDijkstraAlg graph, Map<String, Integer> distances, Map<String, String> previous) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String vertex : graph.getVertices()) {
                if (!vertex.equals(graph.getSourceVertex())) {
                    writer.write(vertex + ": " + getPath(previous, vertex) + "\n");
                }
            }
        }
    }

    public static void displayResults(GraphDijkstraAlg graph, Map<String, Integer> distances, Map<String, String> previous) {
        System.out.println("Shortest paths from source " + graph.getSourceVertex() + ":");
        for (String vertex : graph.getVertices()) {
            if (!vertex.equals(graph.getSourceVertex())) {
                System.out.println(vertex + ": " + getPath(previous, vertex));
            }
        }
    }

    public static String getPath(Map<String, String> previous, String vertex) {
		
        List<String> path = new ArrayList<>();
		
        while (vertex != null) {
			
            path.add(vertex);
            vertex = previous.get(vertex);
        }
        Collections.reverse(path);
        return String.join(" ", path);
    }
}