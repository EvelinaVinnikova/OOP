package org.example;

import java.io.IOException;
import java.util.List;

/**
 * Interface for graph data structure with basic operations.
 * Supports directed graphs with vertices identified by integers.
 */
public interface Graph {
    /**
     * Adds a vertex to the graph.
     *
     * @param vertex the vertex to add
     */
    void addVertex(int vertex);

    /**
     * Removes a vertex from the graph along with all its edges.
     *
     * @param vertex the vertex to remove
     */
    void removeVertex(int vertex);

    /**
     * Adds a directed edge from source to destination.
     *
     * @param from the source vertex
     * @param to the destination vertex
     */
    void addEdge(int from, int to);

    /**
     * Removes an edge from the graph.
     *
     * @param from the source vertex
     * @param to the destination vertex
     */
    void removeEdge(int from, int to);

    /**
     * Gets all neighbors (adjacent vertices) of a given vertex.
     *
     * @param vertex the vertex to get neighbors for
     * @return list of neighboring vertices
     */
    List<Integer> getNeighbors(int vertex);

    /**
     * Reads graph from a file in the specified format.
     * Format: first line contains number of vertices,
     * following lines contain edges as "from to" pairs.
     *
     * @param filename the path to the file
     * @throws IOException if file reading fails
     */
    void readFromFile(String filename) throws IOException;

    /**
     * Gets all vertices in the graph.
     *
     * @return list of all vertices
     */
    List<Integer> getVertices();

    /**
     * Checks if an edge exists between two vertices.
     *
     * @param from the source vertex
     * @param to the destination vertex
     * @return true if edge exists, false otherwise
     */
    boolean hasEdge(int from, int to);

    /**
     * Gets the number of vertices in the graph.
     *
     * @return number of vertices
     */
    int getVertexCount();
}