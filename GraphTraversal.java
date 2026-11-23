
import java.util.*;

public class GraphTraversal {

    private Map<String, List<String>> adjacencyList;

    // Constructor
    public GraphTraversal() {
        adjacencyList = new LinkedHashMap<>();
    }

    // Add a vertex
    public void addVertex(String vertex) {
        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
    }

    // Add an undirected edge
    public void addEdge(String vertex1, String vertex2) {
        addVertex(vertex1);
        addVertex(vertex2);

        adjacencyList.get(vertex1).add(vertex2);
        adjacencyList.get(vertex2).add(vertex1);
    }

    // Depth-First Search (DFS)
    public List<String> dfs(String start) {
        List<String> result = new ArrayList<>();

        if (!adjacencyList.containsKey(start)) {
            return result;
        }

        Set<String> visited = new HashSet<>();

        dfsRecursive(start, visited, result);

        return result;
    }

    // Recursive DFS helper method
    private void dfsRecursive(
            String vertex,
            Set<String> visited,
            List<String> result) {

        visited.add(vertex);
        result.add(vertex);

        for (String neighbor : adjacencyList.get(vertex)) {
            if (!visited.contains(neighbor)) {
                dfsRecursive(neighbor, visited, result);
            }
        }
    }

    // Breadth-First Search (BFS)
    public List<String> bfs(String start) {
        List<String> result = new ArrayList<>();

        if (!adjacencyList.containsKey(start)) {
            return result;
        }

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        visited.add(start);
        queue.add(start);

        while (!queue.isEmpty()) {

            String vertex = queue.poll();
            result.add(vertex);

            for (String neighbor : adjacencyList.get(vertex)) {

                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }

        return result;
    }

    // Display graph
    public void displayGraph() {
        for (String vertex : adjacencyList.keySet()) {
            System.out.println(
                vertex + " -> " + adjacencyList.get(vertex)
            );
        }
    }

    // Main method
    public static void main(String[] args) {

        GraphTraversal graph = new GraphTraversal();

        // Create graph edges
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "D");
        graph.addEdge("B", "E");
        graph.addEdge("C", "F");
        graph.addEdge("E", "F");

        // Display graph
        System.out.println("GRAPH REPRESENTATION:");
        graph.displayGraph();

        // Run DFS
        System.out.println("\nDEPTH-FIRST SEARCH (DFS):");
        System.out.println(graph.dfs("A"));

        // Run BFS
        System.out.println("\nBREADTH-FIRST SEARCH (BFS):");
        System.out.println(graph.bfs("A"));
    }
}
