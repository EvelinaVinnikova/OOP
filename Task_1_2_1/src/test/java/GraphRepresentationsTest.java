import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.example.Graph;
import org.example.AdjacencyListGraph;
import org.example.AdjacencyMatrixGraph;
import org.example.IncidenceMatrixGraph;

import org.junit.jupiter.api.Test;

/**
 * Tests for comparing different graph representations and their equality contracts.
 */
class GraphRepresentationsTest {

    /**
     * Ensures that three different implementations (adjacency matrix/list,
     * incidence matrix) are structurally equal when the same edges are added.
     */
    @Test
    void testEqualsForDifferentRepresentations() {
        Graph matrix = new AdjacencyMatrixGraph();
        Graph list = new AdjacencyListGraph();
        Graph incidence = new IncidenceMatrixGraph();

        matrix.addEdge(1, 2);
        matrix.addEdge(2, 3);

        list.addEdge(1, 2);
        list.addEdge(2, 3);

        incidence.addEdge(1, 2);
        incidence.addEdge(2, 3);

        assertEquals(matrix, list);
        assertEquals(list, incidence);
        assertEquals(matrix, incidence);
    }

    /**
     * Verifies that equal graphs across different implementations
     * produce identical hash codes (hashCode contract).
     */
    @Test
    void testHashCodeConsistency() {
        Graph graph1 = new AdjacencyListGraph();
        Graph graph2 = new AdjacencyMatrixGraph();

        graph1.addEdge(1, 2);
        graph1.addEdge(2, 3);

        graph2.addEdge(1, 2);
        graph2.addEdge(2, 3);

        assertEquals(graph1.hashCode(), graph2.hashCode());
    }

    /**
     * Checks that graphs with different edge sets are not equal
     * even if they share the same vertex set.
     */
    @Test
    void testNotEqualsWithDifferentEdges() {
        Graph graph1 = new AdjacencyListGraph();
        Graph graph2 = new AdjacencyMatrixGraph();

        graph1.addEdge(1, 2);
        graph2.addEdge(1, 3);

        assertNotEquals(graph1, graph2);
    }

    /**
     * Ensures that graphs with different vertex counts are not equal,
     * regardless of representation.
     */
    @Test
    void testNotEqualsWithDifferentVertexCount() {
        Graph graph1 = new AdjacencyListGraph();
        Graph graph2 = new IncidenceMatrixGraph();

        graph1.addVertex(1);
        graph1.addVertex(2);

        graph2.addVertex(1);

        assertNotEquals(graph1, graph2);
    }

    /**
     * Runs a standard “complex” shape across all implementations and asserts
     * equality plus several spot-checks for edges and neighbors.
     */
    @Test
    void testAllRepresentationsWithComplexGraph() {
        Graph[] graphs = {
                new AdjacencyListGraph(),
                new AdjacencyMatrixGraph(),
                new IncidenceMatrixGraph()
        };

        for (Graph graph : graphs) {
            graph.addEdge(0, 1);
            graph.addEdge(0, 2);
            graph.addEdge(1, 2);
            graph.addEdge(2, 0);
            graph.addEdge(2, 3);
            graph.addEdge(3, 3);

            assertEquals(4, graph.getVertexCount());
            assertTrue(graph.hasEdge(0, 1));
            assertTrue(graph.hasEdge(3, 3));

            assertTrue(graph.getNeighbors(2).contains(0));
            assertTrue(graph.getNeighbors(2).contains(3));
        }

        assertEquals(graphs[0], graphs[1]);
        assertEquals(graphs[1], graphs[2]);
    }

    /**
     * Confirms that toString() contains a readable representation
     * that includes explicit edge mentions like “u->v”.
     */
    @Test
    void testToStringContainsVerticesAndEdges() {
        Graph graph = new AdjacencyListGraph();
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);

        String str = graph.toString();

        assertTrue(str.contains("1->2"));
        assertTrue(str.contains("2->3"));
    }

    /**
     * Verifies that empty graphs are equal across different implementations.
     */
    @Test
    void testEmptyGraphsAreEqual() {
        Graph graph1 = new AdjacencyListGraph();
        Graph graph2 = new AdjacencyMatrixGraph();
        Graph graph3 = new IncidenceMatrixGraph();

        assertEquals(graph1, graph2);
        assertEquals(graph2, graph3);
    }

    /**
     * Ensures equality for graphs that contain the same vertex set
     * but no edges.
     */
    @Test
    void testGraphWithOnlyVerticesEqual() {
        Graph graph1 = new AdjacencyListGraph();
        Graph graph2 = new AdjacencyMatrixGraph();

        graph1.addVertex(1);
        graph1.addVertex(2);
        graph1.addVertex(3);

        graph2.addVertex(1);
        graph2.addVertex(2);
        graph2.addVertex(3);

        assertEquals(graph1, graph2);
    }
}