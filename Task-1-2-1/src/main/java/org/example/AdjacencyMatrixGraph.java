package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Objects;

public class AdjacencyMatrixGraph implements Graph {
    private final Map<Integer, Integer> indexOf = new HashMap<>();
    private final List<Integer> verticesByIndex = new ArrayList<>();
    private boolean[][] m = new boolean[0][0];

    public AdjacencyMatrixGraph() { }

    @Override
    public void addVertex(int vertex) {
        if (indexOf.containsKey(vertex)) return;

        int newSize = verticesByIndex.size() + 1;
        boolean[][] next = new boolean[newSize][newSize];

        for (int i = 0; i < m.length; i++) {
            System.arraycopy(m[i], 0, next[i], 0, m.length);
        }
        m = next;

        int idx = verticesByIndex.size();
        verticesByIndex.add(vertex);
        indexOf.put(vertex, idx);
    }

    @Override
    public void removeVertex(int vertex) {
        Integer idxObj = indexOf.get(vertex);
        if (idxObj == null) return;
        int idx = idxObj;

        int n = verticesByIndex.size();
        boolean[][] next = new boolean[n - 1][n - 1];

        for (int i = 0, ii = 0; i < n; i++) {
            if (i == idx) continue;
            for (int j = 0, jj = 0; j < n; j++) {
                if (j == idx) continue;
                next[ii][jj] = m[i][j];
                jj++;
            }
            ii++;
        }

        m = next;
        verticesByIndex.remove(idx);
        indexOf.clear();
        for (int i = 0; i < verticesByIndex.size(); i++) {
            indexOf.put(verticesByIndex.get(i), i);
        }
    }

    @Override
    public void addEdge(int from, int to) {
        addVertex(from);
        addVertex(to);
        m[indexOf.get(from)][indexOf.get(to)] = true;
    }

    @Override
    public void removeEdge(int from, int to) {
        Integer i = indexOf.get(from);
        Integer j = indexOf.get(to);
        if (i == null || j == null) return;
        m[i][j] = false;
    }

    @Override
    public List<Integer> getNeighbors(int vertex) {
        Integer i = indexOf.get(vertex);
        if (i == null) return new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        for (int j = 0; j < m.length; j++) {
            if (m[i][j]) res.add(verticesByIndex.get(j));
        }
        return res;
    }

    @Override
    public void readFromFile(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String first = reader.readLine();
            if (first == null) return;
            int vertexCount = Integer.parseInt(first.trim());
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
        return new ArrayList<>(verticesByIndex);
    }

    @Override
    public boolean hasEdge(int from, int to) {
        Integer i = indexOf.get(from);
        Integer j = indexOf.get(to);
        if (i == null || j == null) return false;
        return m[i][j];
    }

    @Override
    public int getVertexCount() {
        return verticesByIndex.size();
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
        int hash = Objects.hash(verticesByIndex.size());
        List<Integer> vs = new ArrayList<>(verticesByIndex);
        vs.sort(Integer::compareTo);

        for (int from : vs) {
            for (int to : vs) {
                if (hasEdge(from, to)) {
                    hash = 31 * hash + Objects.hash(from, to);
                }
            }
        }
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("AdjacencyMatrixGraph{\n");
        sb.append("  vertices: ").append(getVertices()).append("\n");
        sb.append("  edges: [");

        boolean first = true;
        List<Integer> vs = new ArrayList<>(verticesByIndex);
        vs.sort(Integer::compareTo);

        for (int from : vs) {
            for (int to : vs) {
                if (hasEdge(from, to)) {
                    if (!first) {
                        sb.append(", ");
                    }
                    sb.append(from).append("->").append(to);
                    first = false;
                }
            }
        }

        sb.append("]\n}");
        return sb.toString();
    }
}