import com.rreeves.Graph;
import com.rreeves.GraphAnalyzer;
import com.rreeves.primexample.PrimExample;
import java.util.HashMap;

//
//Test driver
//
public class MainProgram {
    private static Graph createTestGraph() {//Creates vertices a - j  and some arbitrary connections for testing.
        Graph g = new Graph();
        HashMap<Character, Integer> indices = new HashMap<Character, Integer>();
        int i = 0;

        for (char ch = 'a'; ch <= 'j'; ++ch) {
            i = g.addVertex(String.valueOf(ch));
            indices.put(ch, i);
        }

        g.addEdge(indices.get('a'), indices.get('b'));
        g.addEdge(indices.get('a'), indices.get('c'));
        g.addEdge(indices.get('b'), indices.get('d'));
        g.addEdge(indices.get('c'), indices.get('d'));

        g.addEdge(indices.get('e'), indices.get('f'));

        g.addEdge(indices.get('g'), indices.get('h'));
        g.addEdge(indices.get('h'), indices.get('i'));

        return g;
    }

    //Creates a graph representing college classes and prerequisites.
    private static Graph createClassesGraph() {
        Graph g = new Graph();

        //Make some arbitray classes
        int algebra = g.addVertex("Algebra");
        int calculus = g.addVertex("Calculus");
        int discreteMath = g.addVertex("Discrete Math");
        int scheme = g.addVertex("Scheme");
        int algorithms = g.addVertex("Algorithms");
        int linearAlgebra = g.addVertex("Linear Algebra");
        int computerArchitecture = g.addVertex("Computer Architecture");
        int operatingSystems = g.addVertex("Operating Systems");
        int toc = g.addVertex("Theory of Computation");
        int compilers = g.addVertex("Compilers");
        int graduation = g.addVertex("Graduation");

        //Add the prerequisites
        g.addEdge(algebra, scheme);
        g.addEdge(compilers, graduation);
        g.addEdge(algebra, calculus);
        g.addEdge(calculus, discreteMath);
        g.addEdge(discreteMath, algorithms);
        g.addEdge(discreteMath, computerArchitecture);
        g.addEdge(discreteMath, linearAlgebra);
        g.addEdge(linearAlgebra, algorithms);
        g.addEdge(scheme, algorithms);
        g.addEdge(algorithms, toc);
        g.addEdge(computerArchitecture, operatingSystems);
        g.addEdge(toc, compilers);
        g.addEdge(operatingSystems, compilers);
        return g;
    }

    public static void main(String []args) {
        GraphAnalyzer analyzer = new GraphAnalyzer(createTestGraph());
        analyzer.printConnectedComponents();

        analyzer = new GraphAnalyzer(createClassesGraph());
        analyzer.printTopologicalSort(0);

        PrimExample prim = new PrimExample();
        prim.test();

    }
}