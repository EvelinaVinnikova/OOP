import org.example.AdjacencyListGraph;
import org.example.Graph;

public class AdjacencyListGraphTest extends BasicOperationsTest {
    @Override
    protected Graph createGraph() {
        return new AdjacencyListGraph();
    }
}