package org.example;


import java.util.Arrays;

public class Dsu {
    private int[] parent; // Parent array
    private int[] size;   // Size array for union by size optimization
    private int count;    // Number of disjoint sets

    // Constructor to initialize the data structure
    public Dsu(int n) {
        this.parent = new int[n];
        this.size = new int[n];
        this.count = n; // Initially, every element is its own set

        // Initialize Parent array
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        // Initialize Size array with 1s
        Arrays.fill(size, 1);
    }

    // Finds the representative (root) of the set that contains `i`
    public int find(int i) {
        if (i < 0 || i >= parent.length) {
            throw new IllegalArgumentException("Index out of bounds.");
        }
        if (parent[i] != i) {
            // Path compression: Make the parent of `i` the root of the set
            parent[i] = find(parent[i]);
        }
        return parent[i];
    }

    // Unites the sets that include `i` and `j`
    public void union(int i, int j) {
        int rootI = find(i);
        int rootJ = find(j);

        // If already in the same set, no need to unite
        if (rootI == rootJ) return;

        // Merge smaller tree under larger tree
        if (size[rootI] < size[rootJ]) {
            parent[rootI] = rootJ;
            size[rootJ] += size[rootI];
        } else {
            parent[rootJ] = rootI;
            size[rootI] += size[rootJ];
        }

        // Decrement the count of disjoint sets
        count--;
    }

    // Checks if elements `i` and `j` are in the same set
    public boolean connected(int i, int j) {
        return find(i) == find(j);
    }

    // Returns the size of the set containing `i`
    public int getSetSize(int i) {
        return size[find(i)];
    }

    // Returns the current number of disjoint sets
    public int getSetCount() {
        return count;
    }

}
