import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.example.Graph;
import org.example.IncidenceMatrixGraph;

import org.junit.jupiter.api.Test;

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
}
