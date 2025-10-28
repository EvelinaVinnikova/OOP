package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Graph implementation using adjacency list.
 * Each vertex maps to a set of its neighbors.
 */
public class AdjacencyListGraph implements Graph {
    private final Map<Integer, Set<Integer>> adjacencyList;

    /**
     * Creates a new adjacency list graph.
     */
    public AdjacencyListGraph() {
        this.adjacencyList = new HashMap<>();
    }

    @Override
    public void addVertex(int vertex) {
        adjacencyList.putIfAbsent(vertex, new HashSet<>());
    }

    @Override
    public void removeVertex(int vertex) {
        if (!adjacencyList.containsKey(vertex)) {
            return;
        }

        for (Set<Integer> neighbors : adjacencyList.values()) {
            neighbors.remove(vertex);
        }

        adjacencyList.remove(vertex);
    }

    @Override
    public void addEdge(int from, int to) {
        addVertex(from);
        addVertex(to);
        adjacencyList.get(from).add(to);
    }

    @Override
    public void removeEdge(int from, int to) {
        if (adjacencyList.containsKey(from)) {
            adjacencyList.get(from).remove(to);
        }
    }

    @Override
    public List<Integer> getNeighbors(int vertex) {
        if (!adjacencyList.containsKey(vertex)) {
            return new ArrayList<>();
        }
        return new ArrayList<>(adjacencyList.get(vertex));
    }

    @Override
    public void readFromFile(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            int vertexCount = Integer.parseInt(reader.readLine().trim());

            for (int i = 0; i < vertexCount; i++) {
                addVertex(i);
            }

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                if (parts.length >= 2) {
                    int from = Integer.parseInt(parts[0]);
                    int to = Integer.parseInt(parts[1]);
                    addEdge(from, to);
                }
            }
        }
    }

    @Override
    public List<Integer> getVertices() {
        return new ArrayList<>(adjacencyList.keySet());
    }

    @Override
    public boolean hasEdge(int from, int to) {
        if (!adjacencyList.containsKey(from)) {
            return false;
        }
        return adjacencyList.get(from).contains(to);
    }

    @Override
    public int getVertexCount() {
        return adjacencyList.size();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Graph)) {
            return false;
        }

        Graph other = (Graph) obj;

        if (this.getVertexCount() != other.getVertexCount()) {
            return false;
        }

        List<Integer> vertices = this.getVertices();
        for (int v : vertices) {
            if (!other.getVertices().contains(v)) {
                return false;
            }
        }

        for (int from : vertices) {
            for (int to : vertices) {
                if (this.hasEdge(from, to) != other.hasEdge(from, to)) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = Objects.hash(adjacencyList.size());
        List<Integer> vertices = new ArrayList<>(adjacencyList.keySet());
        vertices.sort(Integer::compareTo);

        for (int from : vertices) {
            for (int to : vertices) {
                if (hasEdge(from, to)) {
                    hash = 31 * hash + Objects.hash(from, to);
                }
            }
        }

        return hash;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("AdjacencyListGraph{\n");
        sb.append("  vertices: ").append(getVertices()).append("\n");
        sb.append("  edges: [");

        boolean first = true;
        List<Integer> vertices = new ArrayList<>(adjacencyList.keySet());
        vertices.sort(Integer::compareTo);

        for (int from : vertices) {
            List<Integer> neighbors = new ArrayList<>(adjacencyList.get(from));
            neighbors.sort(Integer::compareTo);
            for (int to : neighbors) {
                if (!first) {
                    sb.append(", ");
                }
                sb.append(from).append("->").append(to);
                first = false;
            }
        }

        sb.append("]\n}");
        return sb.toString();
    }
}