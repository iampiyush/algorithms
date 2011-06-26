public class MainProgram {
    private static void knapSackTest() {
	KnapsackItem []items = new KnapsackItem[4];
	items[0] = new KnapsackItem(7, 3);
	items[1] = new KnapsackItem(5, 2);
	items[2] = new KnapsackItem(3, 3);
	items[3] = new KnapsackItem(1, 2);

	FractionalKnapsack ks = new FractionalKnapsack();

	double amount = ks.calc(9, items);
	double amount2 = ks.calc2(9, items);
	
	System.out.println(amount);
	System.out.println(amount2);

    }
    
    private static void shortestPathTest() {
	Graph graph = new Graph(6);
	
	//Add vertices
	graph.addVertex(0, "A");
	graph.addVertex(1, "B");
	graph.addVertex(2, "C");
	graph.addVertex(3, "D");
	graph.addVertex(4, "E");
	graph.addVertex(5, "F");
	
	//A edges
	graph.addEdge(0, 1, 1);
	graph.addEdge(0, 2, 5);
	
	//B edges
	graph.addEdge(1, 3, 6);
	
	//C edges
	graph.addEdge(2, 3, 1);
	graph.addEdge(2, 4, 1);
	
        //D edges
	graph.addEdge(3, 5, 7);
	
	//E edges
	graph.addEdge(4, 5, 0);
	
	//F has no edges

	DijkstraShortestPath shortestPath = new DijkstraShortestPath();
	shortestPath.printPath(graph, 0, 5);
    }

    public static void main(String []args) {
	knapSackTest();
	shortestPathTest();
    }
}
