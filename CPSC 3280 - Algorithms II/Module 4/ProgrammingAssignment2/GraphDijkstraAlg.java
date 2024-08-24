// Jonathan Elder
// CSPC 3280 - Algorithms 2
// Programming Assignment 2 Module 4
// GraphDijkstraAlg.java

import java.io.*;
import java.util.*;

class GraphDijkstraAlg {
	
    private Map<String, List<Edge>> adjList = new HashMap<>();
	
    private String sourceVertex;

    public void addEdge(String from, String to, int weight) {
        adjList.computeIfAbsent(from, k -> new ArrayList<>()).add(new Edge(to, weight));
    }

    public List<Edge> getEdges(String vertex) {
        return adjList.getOrDefault(vertex, Collections.emptyList());
    }

    public Set<String> getVertices() {
        return adjList.keySet();
    }

    public String getSourceVertex() {
        return sourceVertex;
    }

    public static GraphDijkstraAlg readGraph(String fileName) throws IOException {
		
        GraphDijkstraAlg graph = new GraphDijkstraAlg();
		
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			
            graph.sourceVertex = reader.readLine().trim();
            String line;
			
            while ((line = reader.readLine()) != null) {
                String[] segments = line.split(" ");
                String vertex = segments[0];
                for (int i = 1; i < segments.length; i++) {
                    String[] edgeParts = segments[i].split(",");
                    graph.addEdge(vertex, edgeParts[0], Integer.parseInt(edgeParts[1]));
                }
            }
        }
        return graph;
    }

    class Edge {
        String vertex;
        int weight;

        Edge(String vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }
}
