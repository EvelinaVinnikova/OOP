import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.example.Graph;
import org.example.AdjacencyListGraph;
import org.example.AdjacencyMatrixGraph;
import org.example.IncidenceMatrixGraph;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Tests for reading graphs from files.
 * Format used in tests:
 *   First line: number of vertices (N)
 *   Next lines: pairs "u v" meaning directed edge u->v
 * Empty lines are allowed and should be ignored.
 */
class FileOperationsTest {

    /**
     * Verifies that AdjacencyListGraph correctly reads a small DAG
     * from a file and reconstructs all edges and vertex count.
     */
    @Test
    void testReadFromFileAdjacencyList(@TempDir Path tempDir) throws IOException {
        Path file = tempDir.resolve("graph.txt");
        Files.writeString(file, "4\n0 1\n0 2\n1 3\n2 3\n");

        Graph graph = new AdjacencyListGraph();
        graph.readFromFile(file.toString());

        assertEquals(4, graph.getVertexCount());
        assertTrue(graph.hasEdge(0, 1));
        assertTrue(graph.hasEdge(0, 2));
        assertTrue(graph.hasEdge(1, 3));
        assertTrue(graph.hasEdge(2, 3));
    }

    /**
     * Ensures AdjacencyMatrixGraph correctly parses N and edges,
     * and that non-listed edges remain absent.
     */
    @Test
    void testReadFromFileAdjacencyMatrix(@TempDir Path tempDir) throws IOException {
        Path file = tempDir.resolve("graph.txt");
        Files.writeString(file, "3\n0 1\n1 2\n");

        Graph graph = new AdjacencyMatrixGraph();
        graph.readFromFile(file.toString());

        assertEquals(3, graph.getVertexCount());
        assertTrue(graph.hasEdge(0, 1));
        assertTrue(graph.hasEdge(1, 2));
        assertFalse(graph.hasEdge(0, 2));
    }

    /**
     * Checks that IncidenceMatrixGraph can read a path graph
     * and correctly restore all consecutive edges.
     */
    @Test
    void testReadFromFileIncidenceMatrix(@TempDir Path tempDir) throws IOException {
        Path file = tempDir.resolve("graph.txt");
        Files.writeString(file, "5\n0 1\n1 2\n2 3\n3 4\n");

        Graph graph = new IncidenceMatrixGraph();
        graph.readFromFile(file.toString());

        assertEquals(5, graph.getVertexCount());
        assertTrue(graph.hasEdge(0, 1));
        assertTrue(graph.hasEdge(1, 2));
        assertTrue(graph.hasEdge(2, 3));
        assertTrue(graph.hasEdge(3, 4));
    }

    /**
     * Validates that empty lines in the input are ignored
     * and do not affect graph reconstruction.
     */
    @Test
    void testReadFromFileWithEmptyLines(@TempDir Path tempDir) throws IOException {
        Path file = tempDir.resolve("graph.txt");
        Files.writeString(file, "3\n0 1\n\n1 2\n");

        Graph graph = new AdjacencyListGraph();
        graph.readFromFile(file.toString());

        assertEquals(3, graph.getVertexCount());
        assertTrue(graph.hasEdge(0, 1));
        assertTrue(graph.hasEdge(1, 2));
    }

    /**
     * Ensures parser is robust to extra spaces around numbers
     * and still recognizes the edge.
     */
    @Test
    void testReadFromFileWithExtraSpaces(@TempDir Path tempDir) throws IOException {
        Path file = tempDir.resolve("graph.txt");
        Files.writeString(file, "2\n  0   1  \n");

        Graph graph = new AdjacencyMatrixGraph();
        graph.readFromFile(file.toString());

        assertTrue(graph.hasEdge(0, 1));
    }

    /**
     * Loads a canonical DAG used in topo-sort examples and verifies
     * vertex count and all edges presence.
     */
    @Test
    void testReadFromFileComplexGraph(@TempDir Path tempDir) throws IOException {
        Path file = tempDir.resolve("graph.txt");
        Files.writeString(file, "6\n5 2\n5 0\n4 0\n4 1\n2 3\n3 1\n");

        Graph graph = new AdjacencyListGraph();
        graph.readFromFile(file.toString());

        assertEquals(6, graph.getVertexCount());
        assertTrue(graph.hasEdge(5, 2));
        assertTrue(graph.hasEdge(5, 0));
        assertTrue(graph.hasEdge(4, 0));
        assertTrue(graph.hasEdge(4, 1));
        assertTrue(graph.hasEdge(2, 3));
        assertTrue(graph.hasEdge(3, 1));
    }

    /**
     * Confirms that reading the same file into three different
     * implementations yields structurally equal graphs.
     */
    @Test
    void testReadFromFileAllRepresentationsEqual(@TempDir Path tempDir) throws IOException {
        Path file = tempDir.resolve("graph.txt");
        Files.writeString(file, "4\n0 1\n1 2\n2 3\n");

        Graph list = new AdjacencyListGraph();
        Graph matrix = new AdjacencyMatrixGraph();
        Graph incidence = new IncidenceMatrixGraph();

        list.readFromFile(file.toString());
        matrix.readFromFile(file.toString());
        incidence.readFromFile(file.toString());

        assertEquals(list, matrix);
        assertEquals(matrix, incidence);
    }
}