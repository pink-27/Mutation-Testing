package org.example;


import java.util.*;

public class KruskalMST {
    static class Edge implements Comparable<Edge> {
        int src, dest, weight;

        Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.weight, other.weight);
        }
    }

    private List<Edge> edges;
    private int vertices;

    public KruskalMST(int vertices) {
        this.vertices = vertices;
        this.edges = new ArrayList<>();
    }

    public void addEdge(int src, int dest, int weight) {
        edges.add(new Edge(src, dest, weight));
    }

    public int findMSTWeight() {
        // Sort edges by weight
        Collections.sort(edges);

        // Initialize DSU
        Dsu dsu = new Dsu(vertices);

        int mstWeight = 0;
        int edgesUsed = 0;

        // Process edges in ascending order of weight
        for (Edge edge : edges) {
            // If including this edge doesn't create a cycle
            if (!dsu.connected(edge.src, edge.dest)) {
                dsu.union(edge.src, edge.dest);
                mstWeight += edge.weight;
                edgesUsed++;

                // If we've used enough edges to form an MST
                if (edgesUsed == vertices - 1) {
                    break;
                }
            }
        }

        // Check if we found a valid MST
        if (edgesUsed != vertices - 1) {
            throw new IllegalStateException("No valid MST exists - graph is not connected");
        }

        return mstWeight;
    }

    // Optional: Method to get the actual MST edges
    public List<Edge> getMSTEdges() {
        Collections.sort(edges);
        List<Edge> mstEdges = new ArrayList<>();
        Dsu dsu = new Dsu(vertices);

        for (Edge edge : edges) {
            if (!dsu.connected(edge.src, edge.dest)) {
                dsu.union(edge.src, edge.dest);
                mstEdges.add(edge);

                if (mstEdges.size() == vertices - 1) {
                    break;
                }
            }
        }

        if (mstEdges.size() != vertices - 1) {
            throw new IllegalStateException("No valid MST exists - graph is not connected");
        }

        return mstEdges;
    }
}