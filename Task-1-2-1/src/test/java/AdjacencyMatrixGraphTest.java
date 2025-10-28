import org.example.AdjacencyMatrixGraph;
import org.example.Graph;

public class AdjacencyMatrixGraphTest extends BasicOperationsTest {
    @Override
    protected Graph createGraph() {
        return new AdjacencyMatrixGraph();
    }
}
