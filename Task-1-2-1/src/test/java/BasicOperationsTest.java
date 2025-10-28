import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.example.Graph;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.HashSet;


abstract class BasicOperationsTest {

    protected abstract Graph createGraph();

    @Test
    void addVertex_idempotent() {
        Graph g = createGraph();
        g.addVertex(1);
        g.addVertex(1);
        assertEquals(1, g.getVertexCount());
        assertTrue(g.getVertices().contains(1));
    }

    @Test
    void addEdge_autoAddsVertices() {
        Graph g = createGraph();
        g.addEdge(10, 20);
        assertTrue(g.hasEdge(10, 20));
        assertTrue(g.getVertices().containsAll(Arrays.asList(10, 20)));
    }

    @Test
    void removeEdge_onlyThatEdgeRemoved() {
        Graph g = createGraph();
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.removeEdge(1, 2);
        assertFalse(g.hasEdge(1, 2));
        assertTrue(g.hasEdge(1, 3));
        assertTrue(g.getVertices().containsAll(Arrays.asList(1, 2, 3)));
    }

    @Test
    void removeVertex_removesIncidentEdges() {
        Graph g = createGraph();
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(1, 3);
        g.removeVertex(2);
        assertFalse(g.hasEdge(1, 2));
        assertFalse(g.hasEdge(2, 3));
        assertTrue(g.hasEdge(1, 3));
        assertFalse(g.getVertices().contains(2));
    }

    @Test
    void neighbors_returnsOutgoingOnly() {
        Graph g = createGraph();
        g.addEdge(5, 6);
        g.addEdge(5, 7);
        g.addEdge(6, 7);

        Set<Integer> expected = new HashSet<>(Arrays.asList(6, 7));
        assertEquals(expected, new HashSet<>(g.getNeighbors(5)));
        assertEquals(Collections.singleton(7), new HashSet<>(g.getNeighbors(6)));
        assertEquals(Collections.emptySet(), new HashSet<>(g.getNeighbors(7)));
    }

    @Test
    void equalsAndHashCode_sameStructure_sameImpl() {
        Graph a = createGraph();
        Graph b = createGraph();
        a.addEdge(0, 1);
        a.addEdge(1, 2);
        b.addEdge(0, 1);
        b.addEdge(1, 2);
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    void readFromFile_parsesFixedFormat() throws Exception {
        File f = File.createTempFile("graph", ".txt");
        f.deleteOnExit();
        try (PrintWriter w = new PrintWriter(new FileWriter(f))) {
            w.println("4");
            w.println("0 1");
            w.println("1 2");
            w.println("2 3");
        }

        Graph g = createGraph();
        g.readFromFile(f.getAbsolutePath());

        assertTrue(g.getVertices().containsAll(Arrays.asList(0, 1, 2, 3)));
        assertTrue(g.hasEdge(0, 1));
        assertTrue(g.hasEdge(1, 2));
        assertTrue(g.hasEdge(2, 3));
        assertFalse(g.hasEdge(0, 3));
    }
}
