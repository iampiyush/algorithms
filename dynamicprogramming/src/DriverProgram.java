import com.rreeves.dp.Subsets;
import com.rreeves.dp.Knapsack;
import com.rreeves.dp.MatrixMultiplication;
import com.rreeves.dp.Matrix;
import com.rreeves.dp.Calculator;
import com.rreeves.dp.LongestPalindrome;
import com.rreeves.dp.RodCutter;

public class DriverProgram {

    private static void printSubsets() {
	String str = "abcde";
	Subsets c = new Subsets();
	
	System.out.println("Print top down");
	c.printTopdown(str);
	
	System.out.println("\n\nPrint bottom up\n\n");
	c.printBottomup(str);

    }
    
    private static void knapsackTest() {
	int []values = {19, 2, 9, 8, 9};
	int []weight = {4, 2, 1, 1 ,5};
	int capacity = 5;

	Knapsack ks = new Knapsack(weight, values, capacity);
	int max = ks.fillSackSlow();
	System.out.println("KS 1 - Maximum combination of values in sack is " + max);

	Knapsack2 ks2 = new Knapsack2(weight, values, capacity);
	max = ks2.fillSack();
	System.out.println("KS 2 - Maximum combination of values in sack is " + max);

	max = ks.fillSackFaster();
	System.out.println("Max = " + max);
    }
    
    private static void matrixMultiplicationTest() {
	Matrix []matrices = new Matrix[5];
	
	matrices[0] = new Matrix(10,20);
	matrices[1] = new Matrix(20,10);
	matrices[2] = new Matrix(10,15);
	matrices[3] = new Matrix(15,20);
	matrices[4] = new Matrix(20,10);
	

	MatrixMultiplication m = new MatrixMultiplication(matrices);

	//Should return 7500
	int result = m.computeMin(MatrixMultiplication.Type.TOP_DOWN);
	System.out.println("Minimum computation required to multiply matrices is " + result);

	result = m.computeMin(MatrixMultiplication.Type.BOTTOM_UP);
	System.out.println("Minimum computation required to multiply matrices is " + result);

	m.print();
	
    }
    
    private static void expressionTest() {
	Calculator c = new Calculator("5 + 7 * 10 + 3 / 2");
	double val = c.maximize();
	
	System.out.println(val);
    }
    
    // public static void longestPalindromeTest() {
    // 	String s = "abcded_asdfghjkl";

    // 	LongestPalindrome lp = new LongestPalindrome();
    // 	int ret = lp.calculate(s);
	
    // 	System.out.print("Longest palindrome in " + s + " is " + ret);
    // }

    private static void rodCuttingTest() {
	int[] prices = {1,5,8,9,10,17,17,20,24,30};
	
	RodCutter rc = new RodCutter();

	int revenue = rc.maximizeCuts(prices, 5);

	System.out.println("Max revenue possible is " + revenue);
    }

    public static void main(String []args) {
	rodCuttingTest();
    }
}