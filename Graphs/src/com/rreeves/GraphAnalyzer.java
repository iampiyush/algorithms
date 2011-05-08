package com.rreeves;

import java.util.ListIterator;
import java.util.LinkedList;
import java.util.Queue;

public class GraphAnalyzer {

    enum VertexColor {
        White, Grey, Black
     };

    private boolean [] mInQueue;
    private VertexColor []mMarked;
    private Graph mGraph;

    /////////////////////////////
    /// Public methods
    ////////////////////////////

    public GraphAnalyzer(Graph graph) {
        mGraph = graph;

        int numberVertices = mGraph.numberVertices();
        mMarked = new VertexColor[numberVertices];
        mInQueue = new boolean[numberVertices];
    }

    /*
     * Prints connected components in the graph.
     *
     * Visit each vertex once, doing depth first search and printing all reachable components
     * starting at that vertex.
     *
     */
    public void printConnectedComponents() {
        initMarkedArray();

        int numberVertices = mGraph.numberVertices();
        for (int i = 0; i < numberVertices; i++) { //White vertices haven't been processed.
            if (mMarked[i] == VertexColor.White) {
                System.out.println("-------------------------");
                dfsPrint(i);//Do depth first search and print vertices reachable from i.
                System.out.println("-------------------------");
            }
        }
    }

    /*
     * Prints topological sort of the graph.
     *
     * Traverse the graph breadth first, removing vertices with in-degree of 0
     * and printing them.
     *
     * When a vertice is removed from the graph, decrement in-degree of all adjacent
     * vertices before pushing them on the queue. Vertices already on the queue cannot be added again.
     *
     * Uses a boolean array mInQueue to track if a vertex is on the queue.
     */
    public void printTopologicalSort(int startVertex) {
        initInQueueArray();

        Queue<Integer> vertexQueue = new LinkedList<Integer>();
        vertexQueue.add(startVertex);
        mInQueue[startVertex] = true;

        while (!vertexQueue.isEmpty()) {
            int from = vertexQueue.poll();
            mInQueue[from] = false;

            Vertex fromVertex = mGraph.getVertex(from);

            if (fromVertex.getInDegree() == 0) {

                System.out.println(fromVertex.getName());
                ListIterator<Integer> adjacentVertices = fromVertex.getAdjacentVertices();

                while (adjacentVertices.hasNext()) {
                    int to = adjacentVertices.next();
                    Vertex toVertex = mGraph.getVertex(to);
                    toVertex.decrementInDegree();

                    if (!mInQueue[to]) {//Only add vertices that aren't in the queue and haven't been visited.
                        vertexQueue.add(to);
                    }
                }
            }
        }
    }

    ////////////////////////////////////////
    //Private methods
    ////////////////////////////////////////

    /*
     * Recursively does depth first search, printing vertices.
     * Uses an array of colors to prevent visiting the vertex twice.
     * White means the vertex hasn't been visited.
     * Grey means it's in process.
     * Black means it's done being processed.
     *
     * Using white, grey, and black is just a formality. We could use a boolean array instead.
     */
    private void dfsPrint(int currentVertex) {
        Vertex v = mGraph.getVertex(currentVertex);

        if (mMarked[currentVertex] == VertexColor.White)
            System.out.println(v.getName());

        mMarked[currentVertex] = VertexColor.Grey;

        ListIterator<Integer> adjacentVertices = v.getAdjacentVertices();
        while (adjacentVertices.hasNext()) {
            int to = adjacentVertices.next();

            if (mMarked[to] == VertexColor.White) {
                String vertexName = mGraph.getVertexName(to);
                dfsPrint(to);
            }
        }
        mMarked[currentVertex] = VertexColor.Black;
    }

    private void initMarkedArray() {
        int len = mGraph.numberVertices();
        for (int i = 0; i < len; i++) {
            mMarked[i] = VertexColor.White;
        }
    }

    private void initInQueueArray() {
        int len = mGraph.numberVertices();
        for (int i = 0; i < len; i++) {
            mInQueue[i] = false;
        }
    }
}