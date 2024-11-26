package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class KruskalMSTTest {
    private KruskalMST graph;

    @BeforeEach
    void setUp() {
        graph = new KruskalMST(4); // Create a graph with 4 vertices
    }

    @Test
    void testEmptyGraph() {
        // An empty graph with multiple vertices cannot have an MST
        assertThrows(IllegalStateException.class, () -> graph.findMSTWeight());
    }

    @Test
    void testSimpleGraph() {
        // Create a simple graph
        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 2, 6);
        graph.addEdge(0, 3, 5);
        graph.addEdge(1, 3, 15);
        graph.addEdge(2, 3, 4);

        assertEquals(19, graph.findMSTWeight(), "MST weight should be 19 (edges: 2-3=4, 0-3=5, 0-1=10)");

        // Verify the MST edges
        List<KruskalMST.Edge> mstEdges = graph.getMSTEdges();
        assertEquals(3, mstEdges.size(), "MST should have 3 edges");

        // Verify the edges in order of selection
        assertEquals(4, mstEdges.get(0).weight, "First edge should be weight 4 (2-3)");
        assertEquals(5, mstEdges.get(1).weight, "Second edge should be weight 5 (0-3)");
        assertEquals(10, mstEdges.get(2).weight, "Third edge should be weight 10 (0-1)");
    }

    @Test
    void testDisconnectedGraph() {
        // Create a disconnected graph
        graph.addEdge(0, 1, 1);
        graph.addEdge(2, 3, 2);

        assertThrows(IllegalStateException.class, () -> graph.findMSTWeight(),
                "Should throw exception for disconnected graph");
    }

    @Test
    void testCompleteGraph() {
        KruskalMST complete = new KruskalMST(4);

        // Create a complete graph
        complete.addEdge(0, 1, 1);
        complete.addEdge(0, 2, 2);
        complete.addEdge(0, 3, 3);
        complete.addEdge(1, 2, 4);
        complete.addEdge(1, 3, 5);
        complete.addEdge(2, 3, 6);

        assertEquals(6, complete.findMSTWeight(), "MST weight should be 6 for this complete graph");
    }

    @Test
    void testGetMSTEdges() {
        // Create a simple graph
        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 2, 6);
        graph.addEdge(0, 3, 5);
        graph.addEdge(1, 3, 15);
        graph.addEdge(2, 3, 4);

        List<KruskalMST.Edge> mstEdges = graph.getMSTEdges();

        assertEquals(3, mstEdges.size(), "MST should have n-1 edges");

        // Verify edges are sorted by weight
        for (int i = 0; i < mstEdges.size() - 1; i++) {
            assertTrue(mstEdges.get(i).weight <= mstEdges.get(i + 1).weight,
                    "Edges should be sorted by weight");
        }
    }

    @Test
    void testSameWeightEdges() {
        // Create a graph with multiple edges of the same weight
        graph.addEdge(0, 1, 1);
        graph.addEdge(1, 2, 1);
        graph.addEdge(2, 3, 1);
        graph.addEdge(3, 0, 1);

        assertEquals(3, graph.findMSTWeight(), "MST weight should be 3");
        assertEquals(3, graph.getMSTEdges().size(), "MST should have exactly 3 edges");
    }

    @Test
    void testParallelEdges() {
        // Add parallel edges (multiple edges between same vertices)
        graph.addEdge(0, 1, 5);
        graph.addEdge(0, 1, 2); // Parallel edge with lower weight
        graph.addEdge(1, 2, 3);
        graph.addEdge(2, 3, 4);

        assertEquals(9, graph.findMSTWeight(), "MST should use the minimum weight parallel edge");
    }
}