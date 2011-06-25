import java.util.LinkedList;
import java.util.Stack;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
      Dijkstra Shortest Path

      Objects:

      1) Graph - represents the graph as an array of vertices. Each vertex has an adjacency list (linked list).
      The adjacency list is represented as a list of edges.

      2) Vertex - holds the name of the vertex, it's index in the graph, and it's list of edges. Vertex
      also allows itself to be marked as done.

      3) Edge -  represents an edge as a from index and to index, along with the edge weight.

      4) Node - represents a parent index and distance from the source.  The shortest path algorithm
      uses this to hold the shortest path tree from the source vertex to all other vertices.

      5) Parent array of nodes.

      The algorithm works like this:

      1) Initialize the parent array.

      2) Initialize the min heap of vertex indices. This is a min heap using the current distance.
      The comparator for this heap is MinHeapComparator which looks into the parent array to get
      the current distance of a given vertice.

      3) The source vertex's distance is set to 0 since it's 0 distance to get from source to source.

      4) The min heap contains all vertex indices and the parent array contains a node object for each
      vertice in the graph. All node.distance = MAX_INT and parents are -1 except the source.

      5) While min heap has items do this:

      -Extract min vertex (vertice with least distance from source)

      -For each adjacent vertice, relax it. To avoid loops only process the adjacent vertex if it hasn't
      been processed aleady.

      -Mark min vertex as done.

      6) The inner loop walks through each edge, updating distances if needed.

      -If from.distance + weight is less than to.distance, then we've found a shorter path.
      Set to.distance = from.distance + weight.

      -Objects are updated in the parent array.

      -If the distance was changed in parent array, we need to heapify the min heap. Currently
      I'm not aware of a heapify method on the java heap implementation I'm using - so I just
      flush and reinsert everything into the heap. Obviously only useful for testing the correctness of the
      remaining parts of the algorithm.

**/
class DijkstraShortestPath {
    class MinHeapComparator implements Comparator<Integer> {
        //Uses weight as comparison between heap items
        @Override
        public int compare(Integer one, Integer two) {
            Node first = mParentArray.get(one);
            Node second = mParentArray.get(two);

            return first.distance - second.distance;
        }

    }

    class Node {
        public int distance = Integer.MAX_VALUE;
        public int parent = -1;
    }

    private ArrayList<Node> mParentArray = new ArrayList<Node>();

    public void printPath(Graph graph, int sourceIndex, int destIndex) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(6, new MinHeapComparator());

        int numberVerts = graph.numberVertices();
        int i = 0;

        //Create parent array first - heap uses it during item comparison.
        for (i = 0; i < numberVerts; ++i)
            mParentArray.add(new Node());

        //Set the source's distance to 0
        mParentArray.get(sourceIndex).distance = 0;

        for (i = 0; i < numberVerts; ++i)
            minHeap.add(i);

        while (!minHeap.isEmpty()) {
            int min = minHeap.poll();

            Vertex v = graph.getVertex(min);
            ListIterator<Edge> edges = v.getEdges();

            while (edges.hasNext()) {
                Edge e = edges.next();

                Node from = mParentArray.get(e.getFrom());
                Node to = mParentArray.get(e.getTo());

                //If the node has no parent, don't set it as a parent of
                //another node.
                if (from.distance == Integer.MAX_VALUE)
                    continue;

                //If the target vertex has already been processed, skip it.
                Vertex adjVertex = graph.getVertex(e.getTo());
                if (adjVertex.isDone())
                    continue;

                int proposedDistance = from.distance + e.getWeight();

                //Update the to vertex if there's a shorter path
                //Updating the to Node indirectly updates the heap
                //because the heap comparator looks in the parent array
                if (proposedDistance < to.distance) {
                    to.distance = proposedDistance;
                    to.parent = min;

                    //TODO - BUG. Need to call minHeap.heapify() to "Fix the heap"
                    //Unfortunately PriorityQueue doesn't expose this method.
                    //Algorithm is broken without this method.

                    //To test the rest of the algorithm just clear and reinsert everything into
                    //the heap.
                    Integer[] heapItems = minHeap.toArray(new Integer[0]);

                    minHeap.clear();

                    for (int n = 0; n < heapItems.length; ++n)
                        minHeap.add(heapItems[n]);
                }
            }

            v.done();//We are done with this node, do not scan it again.
        }

        print(graph, destIndex);
    }

    private void print(Graph graph, int startingIndex) {
        Stack<String> parentStack = new Stack<String>();
        Node node = mParentArray.get(startingIndex);

        int i = startingIndex;

        while (true) {
            Vertex vertex = graph.getVertex(i);
            parentStack.push(vertex.getName());

            i = node.parent;

            if (i == -1)
                break;

            node = mParentArray.get(i);
        }

        //From dest to source, traverse back through parents, pushing each item on stack
        //so we can print it in order.
        StringBuilder path = new StringBuilder();

        while (parentStack.size() > 1)
            path.append( parentStack.pop() + "->");

        path.append(parentStack.pop());

        System.out.println(path);
    }
}
