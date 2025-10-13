package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Utility class for performing topological sorting on a graph.
 */
public class TopologicalSort {

    /**
     * Performs topological sort on a directed acyclic graph using DFS.
     *
     * @param graph the graph to sort
     * @return list of vertices in topologically sorted order
     * @throws IllegalArgumentException if graph contains a cycle
     */
    public static List<Integer> sort(Graph graph) {
        List<Integer> result = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Set<Integer> recursionStack = new HashSet<>();

        for (int vertex : graph.getVertices()) {
            if (!visited.contains(vertex)) {
                if (!dfs(graph, vertex, visited, recursionStack, result)) {
                    throw new IllegalArgumentException(
                            "Graph contains a cycle, topological sort is not possible"
                    );
                }
            }
        }

        List<Integer> sortedResult = new ArrayList<>();
        for (int i = result.size() - 1; i >= 0; i--) {
            sortedResult.add(result.get(i));
        }

        return sortedResult;
    }

    /**
     * Performs Kahn's algorithm for topological sorting.
     * Alternative implementation using in-degree approach.
     *
     * @param graph the graph to sort
     * @return list of vertices in topologically sorted order
     * @throws IllegalArgumentException if graph contains a cycle
     */
    public static List<Integer> kahnSort(Graph graph) {
        List<Integer> result = new ArrayList<>();
        Map<Integer, Integer> inDegree = new HashMap<>();

        for (int vertex : graph.getVertices()) {
            inDegree.put(vertex, 0);
        }

        for (int vertex : graph.getVertices()) {
            for (int neighbor : graph.getNeighbors(vertex)) {
                inDegree.put(neighbor, inDegree.get(neighbor) + 1);
            }
        }

        List<Integer> queue = new ArrayList<>();
        for (int vertex : graph.getVertices()) {
            if (inDegree.get(vertex) == 0) {
                queue.add(vertex);
            }
        }

        while (!queue.isEmpty()) {
            int current = queue.remove(0);
            result.add(current);

            for (int neighbor : graph.getNeighbors(current)) {
                inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                if (inDegree.get(neighbor) == 0) {
                    queue.add(neighbor);
                }
            }
        }

        if (result.size() != graph.getVertexCount()) {
            throw new IllegalArgumentException(
                    "Graph contains a cycle, topological sort is not possible"
            );
        }

        return result;
    }

    private static boolean dfs(Graph graph, int vertex, Set<Integer> visited,
                               Set<Integer> recursionStack, List<Integer> result) {
        visited.add(vertex);
        recursionStack.add(vertex);

        for (int neighbor : graph.getNeighbors(vertex)) {
            if (!visited.contains(neighbor)) {
                if (!dfs(graph, neighbor, visited, recursionStack, result)) {
                    return false;
                }
            } else if (recursionStack.contains(neighbor)) {
                // Cycle detected
                return false;
            }
        }

        recursionStack.remove(vertex);
        result.add(vertex);
        return true;
    }
}