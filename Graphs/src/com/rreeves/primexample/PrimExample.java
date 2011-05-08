package com.rreeves.primexample;

/*
  Example of how to generate a minimum spanning tree using Prim algorithm.
*/

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.TreeSet;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Iterator;


/*
 *
 *Edge class - each edge contains a to and from vertex index.
 *
 *
 * */

class Edge implements Comparable<Edge> {
    private int mWeight;
    private int mVFrom;
    private int mVTo;

    public Edge(int vFrom, int vTo, int weight) {

        mWeight = weight;
        mVFrom = vFrom;
        mVTo = vTo;
    }

    public int getFromVertex() {
        return mVFrom;
    }

    public int getToVertex() {
        return mVTo;
    }

    public int getWeight() {
        return mWeight;
    }

    // Used by TreeSet during TreeSet.contains to compare elements.
    @Override
    public int compareTo(Edge e) {
        if (e.getToVertex() > this.getToVertex())
            return -1;
        else if (e.getToVertex() < this.getToVertex())
            return 1;
        else
            return 0;
    }
}

class GraphNode {
    private String mName;
    private LinkedList<Edge> mEdges = new LinkedList<Edge>();

    public GraphNode(String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public void addEdge(Edge edge) {
        mEdges.add(edge);
    }

    public ListIterator<Edge> getEdgeIterator() {
        return mEdges.listIterator();
    }
}

/*
 *
 * Graph represented with adjacency lists.
 */
class Graph {
    private GraphNode[] mNodes;

    public Graph(int numberVertices) {
        mNodes = new GraphNode[numberVertices];
    }

    public String getVertexName(int index) {
        return mNodes[index].getName();
    }

    public ListIterator<Edge> getAdjacentVertices(int index) {
        return mNodes[index].getEdgeIterator();
    }

    public int getNumberVertices() {
        return mNodes.length;
    }

    public void addVertex(int index, String name) {
        mNodes[index] = new GraphNode(name);
    }

    public void addEdge(int vFrom, int vTo, int weight) {
        mNodes[vFrom].addEdge(new Edge(vFrom, vTo, weight));
    }
}

class Prim {
    // Comparator used to create min heap
    class MinHeapComparator implements Comparator<Edge> {
        //Uses weight as comparison between heap items
        @Override
        public int compare(Edge e1, Edge e2) {
            return e1.getWeight() - e2.getWeight();
        }
    }

    private PriorityQueue<Edge> mMinHeap;
    private Graph mGraph;

    public Prim(Graph graph) {
        mMinHeap = new PriorityQueue<Edge>(10, new MinHeapComparator());
        mGraph = graph;
    }

    // Helper function to put a linked list of edges in the minheap.
    private void putEdgesInHeap(ListIterator<Edge> itr) {
        while (itr.hasNext()) {
            mMinHeap.add(itr.next());
        }
    }

    // Returns the minimum spanning tree as a TreeSet<Edge>.
    public TreeSet<Edge> findMinimumSpanningTree() {
        TreeSet<Edge> spanningTree = new TreeSet<Edge>();
        int numberVertices = mGraph.getNumberVertices();

        // 1)
        // Get first vertex and put in spanning tree.
        // Put all adjacent nodes in the heap.

        String vertexName = mGraph.getVertexName(0);
        spanningTree.add(new Edge(0, 0, 0));

        ListIterator<Edge> itr = mGraph.getAdjacentVertices(0);
        putEdgesInHeap(itr);

        // 2)
        // while heap has items:

        // * Extract minimum item. Look at Edge.getToVertex() - this is the
        // vertex outside the spanning tree.
        // * If item is already in the spanning tree, do nothing with it.
        // * Else put the item in the spanning tree and put all its adjacent
        // nodes on the heap.

        while (mMinHeap.size() > 0) {
            Edge curr = mMinHeap.poll();

            // Edge overrides compareTo and
            // compares toVertex since
            // toVertex is the vertex we
            // want to include in the
            // spanning tree.

            if (!spanningTree.contains(curr)) {
                spanningTree.add(curr);
                putEdgesInHeap(mGraph.getAdjacentVertices(curr.getToVertex()));
            }
        }
        return spanningTree;
    }
}

public class PrimExample {
    public void test(){
        Graph graph = new Graph(9);

        // Add vertices
        graph.addVertex(0, "A");
        graph.addVertex(1, "B");
        graph.addVertex(2, "C");
        graph.addVertex(3, "D");
        graph.addVertex(4, "E");
        graph.addVertex(5, "F");
        graph.addVertex(6, "G");
        graph.addVertex(7, "H");
        graph.addVertex(8, "I");

        // Add edges
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 7, 8);
        graph.addEdge(1, 0, 4);
        graph.addEdge(1, 7, 11);
        graph.addEdge(1, 2, 8);
        graph.addEdge(2, 1, 8);
        graph.addEdge(2, 3, 7);
        graph.addEdge(2, 8, 2);
        graph.addEdge(2, 5, 4);
        graph.addEdge(3, 2, 7);
        graph.addEdge(3, 4, 9);
        graph.addEdge(3, 5, 14);
        graph.addEdge(4, 3, 9);
        graph.addEdge(4, 5, 10);
        graph.addEdge(5, 4, 10);
        graph.addEdge(5, 3, 14);
        graph.addEdge(5, 6, 2);
        graph.addEdge(5, 2, 4);
        graph.addEdge(6, 5, 2);
        graph.addEdge(6, 8, 6);
        graph.addEdge(6, 7, 1);
        graph.addEdge(7, 8, 7);
        graph.addEdge(7, 0, 8);
        graph.addEdge(7, 1, 11);
        graph.addEdge(8, 7, 7);
        graph.addEdge(8, 2, 2);
        graph.addEdge(8, 6, 6);

        // Get the minumum spanning tree and print it out.
        Prim prim = new Prim(graph);
        TreeSet<Edge> spanningTree = prim.findMinimumSpanningTree();

        Iterator<Edge> itr = spanningTree.iterator();

        while (itr.hasNext()) {
            Edge e = itr.next();
            String message = "To vertex = "
                + graph.getVertexName(e.getToVertex());
            message += " From vertex = "
                + graph.getVertexName(e.getFromVertex());

            System.out.println(message);
        }
    }
}
