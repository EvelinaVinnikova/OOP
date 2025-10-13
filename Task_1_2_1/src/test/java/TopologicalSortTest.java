import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.example.Graph;
import org.example.AdjacencyListGraph;
import org.example.AdjacencyMatrixGraph;
import org.example.IncidenceMatrixGraph;
import org.example.TopologicalSort;

import org.junit.jupiter.api.Test;
import java.util.List;

/**
 * Tests for topological sorting algorithms.
 */
class TopologicalSortTest {

    /**
     * Checks DFS-based topological sort on a small diamond-like DAG.
     * Validates relative order constraints implied by edges.
     */
    @Test
    void testTopologicalSortDfs() {
        Graph graph = new AdjacencyListGraph();
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);

        List<Integer> sorted = TopologicalSort.sort(graph);

        assertEquals(4, sorted.size());
        assertTrue(sorted.indexOf(0) < sorted.indexOf(1));
        assertTrue(sorted.indexOf(0) < sorted.indexOf(2));
        assertTrue(sorted.indexOf(1) < sorted.indexOf(3));
        assertTrue(sorted.indexOf(2) < sorted.indexOf(3));
    }

    /**
     * Ensures DFS-based topological sort throws on cycles.
     */
    @Test
    void testTopologicalSortWithCycle() {
        Graph graph = new AdjacencyListGraph();
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);

        assertThrows(IllegalArgumentException.class, () -> {
            TopologicalSort.sort(graph);
        });
    }

    /**
     * Checks Kahn’s algorithm on a classic DAG; validates key precedence pairs.
     */
    @Test
    void testKahnSort() {
        Graph graph = new AdjacencyMatrixGraph();
        graph.addEdge(5, 2);
        graph.addEdge(5, 0);
        graph.addEdge(4, 0);
        graph.addEdge(4, 1);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);

        List<Integer> sorted = TopologicalSort.kahnSort(graph);

        assertEquals(6, sorted.size());
        assertTrue(sorted.indexOf(5) < sorted.indexOf(2));
        assertTrue(sorted.indexOf(5) < sorted.indexOf(0));
        assertTrue(sorted.indexOf(4) < sorted.indexOf(1));
        assertTrue(sorted.indexOf(2) < sorted.indexOf(3));
        assertTrue(sorted.indexOf(3) < sorted.indexOf(1));
    }

    /**
     * Ensures Kahn’s algorithm throws an exception when a cycle is present.
     */
    @Test
    void testKahnSortWithCycle() {
        Graph graph = new IncidenceMatrixGraph();
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);

        assertThrows(IllegalArgumentException.class, () -> {
            TopologicalSort.kahnSort(graph);
        });
    }

    /**
     * DFS-based topological sort returns an empty list for an empty graph.
     */
    @Test
    void testEmptyGraphTopologicalSort() {
        Graph graph = new AdjacencyListGraph();

        List<Integer> sorted = TopologicalSort.sort(graph);

        assertTrue(sorted.isEmpty());
    }

    /**
     * Kahn’s algorithm returns an empty list for an empty graph.
     */
    @Test
    void testEmptyGraphKahnSort() {
        Graph graph = new AdjacencyMatrixGraph();

        List<Integer> sorted = TopologicalSort.kahnSort(graph);

        assertTrue(sorted.isEmpty());
    }

    /**
     * DFS-based topological sort on a single-vertex graph
     * should return a singleton list with that vertex.
     */
    @Test
    void testSingleVertexTopologicalSort() {
        Graph graph = new AdjacencyMatrixGraph();
        graph.addVertex(42);

        List<Integer> sorted = TopologicalSort.sort(graph);

        assertEquals(1, sorted.size());
        assertEquals(42, sorted.get(0));
    }

    /**
     * Kahn’s algorithm on a single-vertex graph
     * should return a singleton list with that vertex.
     */
    @Test
    void testSingleVertexKahnSort() {
        Graph graph = new IncidenceMatrixGraph();
        graph.addVertex(99);

        List<Integer> sorted = TopologicalSort.kahnSort(graph);

        assertEquals(1, sorted.size());
        assertEquals(99, sorted.get(0));
    }

    /**
     * DFS-based topological sort on a linear chain should return
     * vertices in the exact chain order.
     */
    @Test
    void testLinearGraphTopologicalSort() {
        Graph graph = new IncidenceMatrixGraph();
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);

        List<Integer> sorted = TopologicalSort.sort(graph);

        assertEquals(List.of(1, 2, 3, 4, 5), sorted);
    }

    /**
     * Kahn’s algorithm on a linear chain should return
     * vertices in the exact chain order.
     */
    @Test
    void testLinearGraphKahnSort() {
        Graph graph = new AdjacencyListGraph();
        graph.addEdge(10, 20);
        graph.addEdge(20, 30);
        graph.addEdge(30, 40);

        List<Integer> sorted = TopologicalSort.kahnSort(graph);

        assertEquals(List.of(10, 20, 30, 40), sorted);
    }

    /**
     * Sanity check: both algorithms work and agree across all three
     * graph representations on the same simple DAG.
     */
    @Test
    void testBothAlgorithmsWorkWithAllRepresentations() {
        Graph[] graphs = new Graph[] {
            new AdjacencyListGraph(),
            new AdjacencyMatrixGraph(),
            new IncidenceMatrixGraph()
        };

        for (Graph graph : graphs) {
            graph.addEdge(0, 1);
            graph.addEdge(1, 2);
            graph.addEdge(0, 2);

            List<Integer> sortedDfs = TopologicalSort.sort(graph);
            List<Integer> sortedKahn = TopologicalSort.kahnSort(graph);

            assertEquals(3, sortedDfs.size());
            assertEquals(3, sortedKahn.size());

            assertTrue(sortedDfs.indexOf(0) < sortedDfs.indexOf(1));
            assertTrue(sortedDfs.indexOf(1) < sortedDfs.indexOf(2));

            assertTrue(sortedKahn.indexOf(0) < sortedKahn.indexOf(1));
            assertTrue(sortedKahn.indexOf(1) < sortedKahn.indexOf(2));
        }
    }

    /**
     * DFS-based sort on a diamond DAG returns a valid ordering:
     * source first, sink last, and both middle nodes before the sink.
     */
    @Test
    void testComplexDag() {
        Graph graph = new AdjacencyListGraph();
        // diamond-shaped DAG
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);

        List<Integer> sorted = TopologicalSort.sort(graph);

        assertEquals(5, sorted.size());
        assertEquals(1, sorted.get(0));
        assertEquals(5, sorted.get(4));
        assertTrue(sorted.indexOf(2) < sorted.indexOf(4));
        assertTrue(sorted.indexOf(3) < sorted.indexOf(4));
    }

    /**
     * A self-loop constitutes a cycle; both algorithms must throw.
     */
    @Test
    void testSelfLoopDetectedAsCycle() {
        Graph graph = new AdjacencyMatrixGraph();
        graph.addEdge(1, 1);

        assertThrows(IllegalArgumentException.class, () -> {
            TopologicalSort.sort(graph);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            TopologicalSort.kahnSort(graph);
        });
    }
}