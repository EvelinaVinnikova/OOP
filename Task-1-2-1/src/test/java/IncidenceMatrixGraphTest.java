import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.example.Graph;
import org.example.IncidenceMatrixGraph;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

public class IncidenceMatrixGraphTest extends BasicOperationsTest {

    @Override
    protected Graph createGraph() {
        return new IncidenceMatrixGraph();
    }

    @Test
    void removeVertex_compactsRows_doesNotMutateEdgeEndpoints() {
        Graph g = createGraph();
        g.addEdge(2, 3);
        g.addEdge(3, 4);
        g.addEdge(1, 2);

        g.removeVertex(1);

        assertTrue(g.hasEdge(2, 3));
        assertTrue(g.hasEdge(3, 4));
        assertFalse(g.hasEdge(1, 2));
    }

    @Test
    void removeNonExistentVertexNoException() {
        Graph g = createGraph();
        g.addVertex(1);
        g.removeVertex(999);
        assertEquals(1, g.getVertexCount());
        assertTrue(g.getVertices().contains(1));
    }

    @Test
    void resizeVerticesTriggersWhenAddingMoreThanInitialCapacity() {
        Graph g = createGraph();
        for (int i = 0; i < 15; i++) {
            g.addVertex(i);
        }
        assertEquals(15, g.getVertexCount());
        assertTrue(g.getVertices().containsAll(IntStream.range(0, 15).boxed().toList()));
    }

    @Test
    void resizeEdgesTriggersWhenAddingMoreThanInitialCapacity() {
        Graph g = createGraph();
        for (int i = 0; i < 15; i++) {
            g.addEdge(i, i + 1);
        }
        for (int i = 0; i < 15; i++) {
            assertTrue(g.hasEdge(i, i + 1), "Edge " + i + "->" + (i + 1) + " should exist");
        }
        assertEquals(16, g.getVertexCount());
    }

    @Test
    void addVertexAndEdgeIdempotent() {
        Graph g = createGraph();
        g.addVertex(5);
        g.addVertex(5);
        g.addEdge(5, 6);
        g.addEdge(5, 6);

        assertEquals(2, g.getVertexCount());
        assertTrue(g.hasEdge(5, 6));
    }

    @Test
    void equals_differentEdges_returnsFalse() {
        Graph g1 = createGraph();
        Graph g2 = createGraph();

        g1.addEdge(0, 1);
        g2.addEdge(0, 2);

        assertNotEquals(g1, g2);
    }



}
