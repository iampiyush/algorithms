import java.util.LinkedList;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.LinkedList;

class Edge {
    private int x;
    private int y;
    private int mWeight;
    
    public Edge(int x, int y, int weight) {
	this.x = x;
	this.y = y;
	this.mWeight = weight;
    }
    
    public int getX() {
	return x;
    }
    
    public int getY() {
	return y;
    }
    
    public int getWeight() {
	return mWeight;
    }
}

class EdgeSorter {
    private void swap(ArrayList<Edge> arr, int one, int two) {
	Edge firstEdge = arr.get(one);
	Edge secondEdge = arr.get(two);
	Edge temp = arr.get(one);
	
	arr.set(one, secondEdge);
	arr.set(two, temp);
    }

    private int partition(ArrayList<Edge> arr, int start, int end) {
	int leftMost = start;
	int pivot = start;
	
	for (int i = start+1; i <=end; ++i) {
	    if (arr.get(i).getWeight() < arr.get(pivot).getWeight()) {
		leftMost++;
		swap(arr, i, leftMost);
	    }
	}

	swap(arr, pivot, leftMost);

	return leftMost;
    }

    private void quickSort(ArrayList<Edge> arr, int start, int end) {
	if (end < start)
	    return;

	int pivot = partition(arr, start, end);
	
	quickSort(arr, start, pivot-1);
	quickSort(arr, pivot+1, end);
    }

    public void sort(ArrayList<Edge> arr) {
	quickSort(arr, 0, arr.size()-1);
    }
}		

class Graph {
    private String []mNodes;
    private ArrayList<Edge> mEdges = new ArrayList<Edge>();


    public Graph(int numberVertices) {
	mNodes = new String[numberVertices];
    }
    
    public void addVertex(int index, String name) {
	mNodes[index] = name;
    }
    
    public void addEdge(int x, int y, int weight) {
	mEdges.add(new Edge(x, y, weight));
    }
    
    public ArrayList<Edge> getEdges() {
	return mEdges;
    }
    
    public int getNumberVertices() {
	return mNodes.length;
    }
    
    public String getVertexName(int index) {
	return mNodes[index];
    }

    public void sortEdges() {
	EdgeSorter sorter = new EdgeSorter();
	sorter.sort(mEdges);
    }
}

class Kruscal {
    class SetItem {
	private int mIndex;
	private int mParentIndex;
	
	public SetItem(int index) {
	    mIndex = index;
	    mParentIndex = index;
	}
	
	public void setParentIndex(int parentIndex) {
	    mParentIndex = parentIndex;
	}

	public int getParentIndex() {
	    return mParentIndex;
	}

	public int getIndex() {
	    return mIndex;
	}
    }

    private ArrayList<SetItem> mSets = new ArrayList<SetItem>();

    private Graph mGraph;
    
    public Kruscal(Graph graph) {
	mGraph = graph;
    }
    
    public void union(int one, int two) {
	SetItem i = findSet(one);
	i.setParentIndex(two);
    }
    
    public SetItem findSet(int x) {
	SetItem item = mSets.get(x);
	
	int ret = item.getIndex();

	while (ret != item.getParentIndex()) {
	    ret = item.getParentIndex();
	    item = mSets.get(ret);
	}
	return item;
    }
    
    public void makeSet(int x) {
	mSets.add(new SetItem(x));
    }
    
    public LinkedList<Edge> findMinimumSpanningTree() {
	LinkedList<Edge> spanningTree = new LinkedList<Edge>();
	
	//1) Sort the edges

	mGraph.sortEdges();
	
	//2 Create set for each node in the tree.
	//  Use an array of nodes (0 to number nodes) with parent pointers.

	int numberVertices = mGraph.getNumberVertices();

	for (int i = 0; i < numberVertices; ++i) {
	    makeSet(i);
	}

	//3) For each edge, if edge.x and edge.y aren't in the same set,
	//   put them in same set. Add edge to spanning tree.
	
	ArrayList<Edge> edges = mGraph.getEdges();
	
	int numEdges = edges.size();

	for (int x = 0; x < numEdges; ++x) {
	    Edge edge = edges.get(x);

	    SetItem set1 = findSet(edge.getX());
	    SetItem set2 = findSet(edge.getY());
	    
	    if (set1.getIndex() != set2.getIndex()) {
		union(set1.getIndex(), set2.getIndex());
		spanningTree.add(edge);
	    }
	}

	return spanningTree;
    }

}


public class MainProgram {
    public static void main(String []args) {
	Graph graph = new Graph(9);
	graph.addVertex(0, "a");
        graph.addVertex(1, "b");
        graph.addVertex(2, "c");
        graph.addVertex(3, "d");
        graph.addVertex(4, "e");
        graph.addVertex(5, "f");
        graph.addVertex(6, "g");
        graph.addVertex(7, "h");
        graph.addVertex(8, "i");
       
	graph.addEdge(2, 5, 4);

	graph.addEdge(2, 3, 7);
	graph.addEdge(7, 8, 7);
	graph.addEdge(3, 4, 9);

	graph.addEdge(0, 1, 4);
	graph.addEdge(8, 6, 6);
	
	graph.addEdge(6, 7, 1);
	graph.addEdge(4, 5, 10);
	graph.addEdge(1, 7, 11);

	graph.addEdge(1, 2, 8);

	graph.addEdge(0, 7, 8);

	graph.addEdge(5, 6, 2);
	graph.addEdge(8, 2, 2);
	graph.addEdge(3, 5, 14);
	
	graph.sortEdges();

	Kruscal k = new Kruscal(graph);
	
	LinkedList<Edge> spanningTree = k.findMinimumSpanningTree();

	Iterator<Edge> itr = spanningTree.iterator();
	
	while (itr.hasNext()) {
	    Edge e = itr.next();
	    System.out.println(graph.getVertexName(e.getX()) + " -> " + graph.getVertexName(e.getY()) + "(" + String.valueOf(e.getWeight() + ")"));
	}

    }
}