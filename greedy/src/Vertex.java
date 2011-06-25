import java.util.LinkedList;
import java.util.ListIterator;

public class Vertex {
    private boolean mProcessed;
    private String mName;
    private int mIndex;
    private LinkedList<Edge> mEdges = new LinkedList<Edge>();
    
    public Vertex(int index, String name) {
	mIndex = index;
	mName = name;
    }

    public int getIndex() {
	return mIndex;
    }

    public void addEdge(int to, int weight) {
	Edge e = new Edge(mIndex, to, weight);
	mEdges.add(e);
    }
    
    public ListIterator<Edge> getEdges() {
	return mEdges.listIterator();
    }
    
    public String getName() {
	return mName;
    }
    
    public void done() {
	mProcessed = true;
    }

    public boolean isDone() {
	return mProcessed;
    }
}
