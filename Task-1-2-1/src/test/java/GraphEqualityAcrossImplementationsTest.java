import static org.junit.jupiter.api.Assertions.assertEquals;

import org.example.AdjacencyListGraph;
import org.example.AdjacencyMatrixGraph;
import org.example.IncidenceMatrixGraph;
import org.example.Graph;

import org.junit.jupiter.api.Test;


class GraphEqualityAcrossImplementationsTest {

    @Test
    void sameGraph_equalAcrossRepresentations() {
        Graph g1 = new AdjacencyListGraph();
        Graph g2 = new AdjacencyMatrixGraph();
        Graph g3 = new IncidenceMatrixGraph();

        for (Graph g : new Graph[]{g1, g2, g3}) {
            g.addEdge(0, 1);
            g.addEdge(1, 2);
            g.addEdge(0, 2);
        }

        assertEquals(g1, g2);
        assertEquals(g2, g3);
        assertEquals(g1, g3);
    }
}
