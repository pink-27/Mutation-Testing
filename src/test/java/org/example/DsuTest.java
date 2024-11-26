package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class DsuTest {
    private Dsu dsu;
    private static final int TEST_SIZE = 5;

    @BeforeEach
    void setUp() {
        dsu = new Dsu(TEST_SIZE);
    }

    @Test
    void testInitialization() {
        assertEquals(TEST_SIZE, dsu.getSetCount(), "Initial set count should equal number of elements");

        // Test that each element is in its own set initially
        for (int i = 0; i < TEST_SIZE; i++) {
            assertEquals(1, dsu.getSetSize(i), "Initial set size should be 1");
            assertEquals(i, dsu.find(i), "Each element should be its own root initially");
        }
    }

    @Test
    void testFind() {
        // Test basic find
        assertEquals(0, dsu.find(0), "Find should return the element itself initially");

        // Test find after union
        dsu.union(0, 1);
        int root = dsu.find(0);
        assertEquals(root, dsu.find(1), "After union, elements should have the same root");
    }

    @Test
    void testFindWithInvalidInput() {
        // Test negative index
        assertThrows(IllegalArgumentException.class, () -> dsu.find(-1));

        // Test index >= size
        assertThrows(IllegalArgumentException.class, () -> dsu.find(TEST_SIZE));
    }

    @Test
    void testUnion() {
        // Test basic union
        dsu.union(0, 1);
        assertTrue(dsu.connected(0, 1), "Elements should be connected after union");
        assertEquals(TEST_SIZE - 1, dsu.getSetCount(), "Set count should decrease after union");

        // Test union of already united elements
        dsu.union(0, 1);
        assertEquals(TEST_SIZE - 1, dsu.getSetCount(), "Set count should not change when uniting already connected elements");

        // Test multiple unions
        dsu.union(1, 2);
        assertTrue(dsu.connected(0, 2), "Transitivity should work after multiple unions");
        assertEquals(3, dsu.getSetSize(0), "Set size should be 3 after uniting three elements");
    }

    @Test
    void testConnected() {
        // Test initially disconnected elements
        assertFalse(dsu.connected(0, 1), "Elements should not be connected initially");

        // Test after union
        dsu.union(0, 1);
        assertTrue(dsu.connected(0, 1), "Elements should be connected after union");

        // Test transitivity
        dsu.union(1, 2);
        assertTrue(dsu.connected(0, 2), "Elements should be connected transitively");
    }

    @Test
    void testGetSetSize() {
        // Test initial size
        assertEquals(1, dsu.getSetSize(0), "Initial set size should be 1");

        // Test size after union
        dsu.union(0, 1);
        assertEquals(2, dsu.getSetSize(0), "Set size should be 2 after union");
        assertEquals(2, dsu.getSetSize(1), "Set size should be same for all elements in set");

        // Test size after multiple unions
        dsu.union(1, 2);
        assertEquals(3, dsu.getSetSize(0), "Set size should be 3 after second union");
        assertEquals(3, dsu.getSetSize(1), "Set size should be same for all elements in set");
        assertEquals(3, dsu.getSetSize(2), "Set size should be same for all elements in set");
    }

    @Test
    void testGetSetCount() {
        // Test initial count
        assertEquals(TEST_SIZE, dsu.getSetCount(), "Initial set count should equal number of elements");

        // Test count after union
        dsu.union(0, 1);
        assertEquals(TEST_SIZE - 1, dsu.getSetCount(), "Set count should decrease after union");

        // Test count after multiple unions
        dsu.union(1, 2);
        assertEquals(TEST_SIZE - 2, dsu.getSetCount(), "Set count should decrease after each union");

        // Test count stays same for union of already connected elements
        dsu.union(0, 1);
        assertEquals(TEST_SIZE - 2, dsu.getSetCount(), "Set count should not change for union of connected elements");
    }

    @Test
    void testLargeUnions() {
        // Create a larger DSU
        Dsu largeDsu = new Dsu(100);

        // Create a long chain of unions
        for (int i = 0; i < 99; i++) {
            largeDsu.union(i, i + 1);
        }

        // Test that path compression works
        assertEquals(largeDsu.find(0), largeDsu.find(99), "All elements should have same root");
        assertEquals(100, largeDsu.getSetSize(0), "Final set should contain all elements");
        assertEquals(1, largeDsu.getSetCount(), "Should only have one set at the end");
    }
}