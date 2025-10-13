import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.example.Graph;
import org.example.AdjacencyListGraph;
import org.example.AdjacencyMatrixGraph;
import org.example.IncidenceMatrixGraph;

import org.junit.jupiter.api.Test;
import java.util.List;

/**
 * Tests for basic graph operations: add/remove vertices and edges.
 */
class BasicOperationsTest {

    /**
     * Verifies that adding distinct vertices increases the vertex count
     * and that the vertices collection contains each newly added vertex.
     */
    @Test
    void testAddVertex() {
        Graph graph = new AdjacencyListGraph();
        graph.addVertex(1);
        graph.addVertex(2);

        assertEquals(2, graph.getVertexCount());
        assertTrue(graph.getVertices().contains(1));
        assertTrue(graph.getVertices().contains(2));
    }

    /**
     * Ensures that adding the same vertex twice does not create duplicates
     * and the vertex count remains correct.
     */
    @Test
    void testAddDuplicateVertex() {
        Graph graph = new AdjacencyMatrixGraph();
        graph.addVertex(1);
        graph.addVertex(1);

        assertEquals(1, graph.getVertexCount());
    }

    /**
     * Checks that removing a vertex also removes all incident edges
     * and updates the vertex set accordingly.
     */
    @Test
    void testRemoveVertex() {
        Graph graph = new IncidenceMatrixGraph();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addEdge(1, 2);

        graph.removeVertex(1);

        assertEquals(1, graph.getVertexCount());
        assertFalse(graph.getVertices().contains(1));
        assertFalse(graph.hasEdge(1, 2));
    }

    /**
     * Validates that removing a vertex with multiple incident edges
     * eliminates all related edges while preserving unrelated ones.
     */
    @Test
    void testRemoveVertexWithMultipleEdges() {
        Graph graph = new AdjacencyListGraph();
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);

        graph.removeVertex(1);

        assertEquals(2, graph.getVertexCount());
        assertFalse(graph.hasEdge(1, 2));
        assertFalse(graph.hasEdge(1, 3));
        assertFalse(graph.hasEdge(3, 1));
        assertTrue(graph.hasEdge(2, 3));
    }

    /**
     * Confirms that adding an edge creates the directed connection (u -> v),
     * does not create the reverse edge, and auto-creates missing vertices.
     */
    @Test
    void testAddEdge() {
        Graph graph = new AdjacencyListGraph();
        graph.addEdge(1, 2);

        assertTrue(graph.hasEdge(1, 2));
        assertFalse(graph.hasEdge(2, 1));
        assertEquals(2, graph.getVertexCount());
    }

    /**
     * Ensures that calling addEdge(u, v) on non-existent vertices
     * implicitly creates those vertices in the graph.
     */
    @Test
    void testAddEdgeCreatesVertices() {
        Graph graph = new AdjacencyMatrixGraph();
        graph.addEdge(5, 10);

        assertTrue(graph.getVertices().contains(5));
        assertTrue(graph.getVertices().contains(10));
        assertTrue(graph.hasEdge(5, 10));
    }

    /**
     * Verifies that removing an existing edge deletes only that edge,
     * leaving the involved vertices untouched.
     */
    @Test
    void testRemoveEdge() {
        Graph graph = new AdjacencyMatrixGraph();
        graph.addEdge(1, 2);
        graph.removeEdge(1, 2);

        assertFalse(graph.hasEdge(1, 2));
        assertEquals(2, graph.getVertexCount());
    }

    /**
     * Checks that removing a non-existent edge is a no-op and does not
     * throw or alter the graph state unexpectedly.
     */
    @Test
    void testRemoveNonExistentEdge() {
        Graph graph = new IncidenceMatrixGraph();
        graph.addVertex(1);
        graph.addVertex(2);

        graph.removeEdge(1, 2);

        assertFalse(graph.hasEdge(1, 2));
    }

    /**
     * Ensures that getNeighbors(u) returns exactly the out-neighbors of u
     * for a typical fan-out pattern.
     */
    @Test
    void testGetNeighbors() {
        Graph graph = new AdjacencyListGraph();
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);

        List<Integer> neighbors = graph.getNeighbors(1);

        assertEquals(3, neighbors.size());
        assertTrue(neighbors.contains(2));
        assertTrue(neighbors.contains(3));
        assertTrue(neighbors.contains(4));
    }

    /**
     * Verifies that requesting neighbors of an absent vertex
     * yields an empty list.
     */
    @Test
    void testGetNeighborsOfNonExistentVertex() {
        Graph graph = new AdjacencyMatrixGraph();

        List<Integer> neighbors = graph.getNeighbors(999);

        assertTrue(neighbors.isEmpty());
    }

    /**
     * Confirms that self-loops (u -> u) are allowed and
     * appear among the vertex's neighbors.
     */
    @Test
    void testSelfLoop() {
        Graph graph = new AdjacencyListGraph();
        graph.addEdge(1, 1);

        assertTrue(graph.hasEdge(1, 1));
        List<Integer> neighbors = graph.getNeighbors(1);
        assertTrue(neighbors.contains(1));
    }
}