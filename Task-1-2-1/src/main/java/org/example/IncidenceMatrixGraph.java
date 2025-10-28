package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Graph implementation using incidence matrix.
 * Rows represent vertices, columns represent edges.
 * For directed edge from u to v: matrix[u][edge] = -1, matrix[v][edge] = 1.
 */
public class IncidenceMatrixGraph implements Graph {
    private Map<Integer, Integer> vertexToIndex;
    private Map<Integer, Integer> indexToVertex;
    private List<Edge> edges;
    private int[][] matrix;
    private int vertexCount;
    private int edgeCount;
    private int vertexCapacity;
    private int edgeCapacity;

    /**
     * Internal representation of a directed edge.
     */
    private static class Edge {
        int from;
        int to;

        /**
         * Constructs a directed edge from {@code from} to {@code to}.
         *
         * @param from the source vertex
         * @param to the destination vertex
         */
        Edge(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Edge)) {
                return false;
            }
            Edge other = (Edge) obj;
            return this.from == other.from && this.to == other.to;
        }

        @Override
        public int hashCode() {
            return Objects.hash(from, to);
        }
    }

    /**
     * Creates a new graph using incidence matrix representation with initial capacity for 10 vertices and 10 edges.
     */
    public IncidenceMatrixGraph() {
        this.vertexCapacity = 10;
        this.edgeCapacity = 10;
        this.matrix = new int[vertexCapacity][edgeCapacity];
        this.vertexToIndex = new HashMap<>();
        this.indexToVertex = new HashMap<>();
        this.edges = new ArrayList<>();
        this.vertexCount = 0;
        this.edgeCount = 0;
    }

    @Override
    public void addVertex(int vertex) {
        if (vertexToIndex.containsKey(vertex)) {
            return;
        }
        if (vertexCount >= vertexCapacity) {
            resizeVertices();
        }
        vertexToIndex.put(vertex, vertexCount);
        indexToVertex.put(vertexCount, vertex);
        vertexCount++;
    }

    @Override
    public void removeVertex(int vertex) {
        if (!vertexToIndex.containsKey(vertex)) {
            return;
        }

        List<Edge> toRemove = new ArrayList<>();
        for (Edge edge : edges) {
            if (edge.from == vertex || edge.to == vertex) {
                toRemove.add(edge);
            }
        }
        for (Edge edge : toRemove) {
            removeEdge(edge.from, edge.to);
        }

        int index = vertexToIndex.get(vertex);

        if (index < vertexCount - 1) {
            int lastVertex = indexToVertex.get(vertexCount - 1);

            for (int i = 0; i < edgeCount; i++) {
                matrix[index][i] = matrix[vertexCount - 1][i];
            }

            vertexToIndex.put(lastVertex, index);
            indexToVertex.put(index, lastVertex);
        }

        vertexToIndex.remove(vertex);
        indexToVertex.remove(vertexCount - 1);
        vertexCount--;
    }

    @Override
    public void addEdge(int from, int to) {
        addVertex(from);
        addVertex(to);

        Edge newEdge = new Edge(from, to);
        if (edges.contains(newEdge)) {
            return;
        }

        if (edgeCount >= edgeCapacity) {
            resizeEdges();
        }

        edges.add(newEdge);
        int fromIndex = vertexToIndex.get(from);
        int toIndex = vertexToIndex.get(to);

        matrix[fromIndex][edgeCount] = -1;
        matrix[toIndex][edgeCount] = 1;
        edgeCount++;
    }

    @Override
    public void removeEdge(int from, int to) {
        Edge edgeToRemove = new Edge(from, to);
        int edgeIndex = edges.indexOf(edgeToRemove);
        if (edgeIndex == -1) {
            return;
        }

        if (edgeIndex < edgeCount - 1) {
            edges.set(edgeIndex, edges.get(edgeCount - 1));
            for (int i = 0; i < vertexCount; i++) {
                matrix[i][edgeIndex] = matrix[i][edgeCount - 1];
                matrix[i][edgeCount - 1] = 0;
            }
        } else {
            for (int i = 0; i < vertexCount; i++) {
                matrix[i][edgeIndex] = 0;
            }
        }

        edges.remove(edgeCount - 1);
        edgeCount--;
    }

    @Override
    public List<Integer> getNeighbors(int vertex) {
        List<Integer> neighbors = new ArrayList<>();
        if (!vertexToIndex.containsKey(vertex)) {
            return neighbors;
        }
        for (Edge edge : edges) {
            if (edge.from == vertex) {
                neighbors.add(edge.to);
            }
        }
        return neighbors;
    }

    @Override
    public void readFromFile(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            int vertexCountFromFile = Integer.parseInt(reader.readLine().trim());
            for (int i = 0; i < vertexCountFromFile; i++) {
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
        return new ArrayList<>(vertexToIndex.keySet());
    }

    @Override
    public boolean hasEdge(int from, int to) {
        return edges.contains(new Edge(from, to));
    }

    @Override
    public int getVertexCount() {
        return vertexCount;
    }

    /**
     * Resizes the internal matrix to accommodate more vertices.
     * Doubles the current vertex capacity and copies existing data.
     */
    private void resizeVertices() {
        int newCapacity = vertexCapacity * 2;
        int[][] newMatrix = new int[newCapacity][edgeCapacity];
        for (int i = 0; i < vertexCount; i++) {
            System.arraycopy(matrix[i], 0, newMatrix[i], 0, edgeCount);
        }
        matrix = newMatrix;
        vertexCapacity = newCapacity;
    }

    /**
     * Resizes the internal matrix to accommodate more edges.
     * Doubles the current edge capacity and copies existing data.
     */
    private void resizeEdges() {
        int newCapacity = edgeCapacity * 2;
        int[][] newMatrix = new int[vertexCapacity][newCapacity];
        for (int i = 0; i < vertexCount; i++) {
            System.arraycopy(matrix[i], 0, newMatrix[i], 0, edgeCount);
        }
        matrix = newMatrix;
        edgeCapacity = newCapacity;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Graph)) return false;

        Graph other = (Graph) obj;
        if (this.getVertexCount() != other.getVertexCount()) return false;

        List<Integer> vertices = this.getVertices();
        for (int v : vertices) {
            if (!other.getVertices().contains(v)) return false;
        }

        for (int from : vertices) {
            for (int to : vertices) {
                if (this.hasEdge(from, to) != other.hasEdge(from, to)) return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = Objects.hash(vertexCount);
        List<Integer> vertices = new ArrayList<>(vertexToIndex.keySet());
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
        StringBuilder sb = new StringBuilder("IncidenceMatrixGraph{\n");
        sb.append("  vertices: ").append(getVertices()).append("\n");
        sb.append("  edges: [");
        boolean first = true;
        for (Edge edge : edges) {
            if (!first) sb.append(", ");
            sb.append(edge.from).append("->").append(edge.to);
            first = false;
        }
        sb.append("]\n}");
        return sb.toString();
    }
}