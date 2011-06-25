public class Edge {
    private int mFromIndex;
    private int mToIndex;
    private int mWeight;

    public Edge(int from, int to, int weight) {
	mFromIndex = from;
	mToIndex = to;
	mWeight = weight;
    }
    
    public int getTo() {
	return mToIndex;
    }
    
    public int getFrom() {
	return mFromIndex;
    }
    
    public int getWeight() {
	return mWeight;
    }
}
