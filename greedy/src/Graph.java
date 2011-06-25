import java.util.ListIterator;

public class Graph {
    private Vertex [] mNodes;
    
    public Graph(int numVertex) {
	mNodes = new Vertex[numVertex];
    }

    public String getVertexName(int index) {
	return mNodes[index].getName();
    }

    public Vertex getVertex(int index) {
	return mNodes[index];
    }

    public void addVertex(int index, String name) {
	mNodes[index] =  new Vertex(index, name);
    }
   
    public void addEdge(int from, int to, int weight) {
	Vertex v = mNodes[from];
	v.addEdge(to, weight);
    }
    
    public int numberVertices() {
	return mNodes.length;
    }

    public void print() {
    	for (Vertex v : mNodes) {
    	    StringBuilder msg = new StringBuilder();
    	    msg.append(v.getName());
    	    msg.append(":");

    	    ListIterator<Edge> itr = v.getEdges();
	    
    	    while (itr.hasNext()) {
    		Edge e = itr.next();

    		int toIndex = e.getTo();

    		String toName = mNodes[toIndex].getName();
		
    		msg.append(" --> ");
    		msg.append(toName);
    	    }

    	    System.out.println(msg);
    	}
    }
}
