import com.rreeves.dp.Subsets;
import com.rreeves.dp.Knapsack;
import com.rreeves.dp.MatrixMultiplication;
import com.rreeves.dp.Matrix;
import com.rreeves.dp.Calculator;
import com.rreeves.dp.LongestPalindrome;
import com.rreeves.dp.RodCutter;
import com.rreeves.dp.BinomialCoefficients;
import com.rreeves.dp.Partition;
import com.rreeves.dp.LCS;
import com.rreeves.dp.LIS;
import com.rreeves.dp.Cashier;
import com.rreeves.dp.EditDistance;
import com.rreeves.dp.PhoneNumber;
import com.rreeves.dp.ThreeNumberSum;
import java.util.Stack;

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
    
    public static void longestPalindromeTest() {
    	String s = "abcdefghijka";

    	LongestPalindrome lp = new LongestPalindrome();
    	int ret = lp.calculate(s);
	
    	System.out.print("Longest palindrome in " + s + " is " + ret);
    }

    private static void rodCuttingTest() {
	int[] prices = {1,5,8,9,10,17,17,20,24,30};
	
	RodCutter rc = new RodCutter();

	int revenue = rc.maximizeCuts(prices, 5);

	System.out.println("Max revenue possible is " + revenue);
    }

    private static void binomialCoefficientsTest() {
	BinomialCoefficients bc = new BinomialCoefficients();

	for (int n = 1; n < 10; ++n) {
	    for (int k = 1; k < 10; ++k) {
		System.out.print(bc.calculate(n, k));
		System.out.print("\t");
	    }
	    System.out.println("");
	}
	
    }
    
    private static void partitionTest() {
	int[] arr = {2,1,3,3,3,3,3};
	Partition p = new Partition();
	boolean ret = p.canPartition(arr);

	if (ret)
	    System.out.println("arr can be partitioned into two subsets of equal value");
	else
	    System.out.println("arr cannot be partitioned into two subsets of equal value");
    }
    
    public static void longestIncreasingSubsequenceTest() {
	LIS lis = new LIS();

	int []arr = {0, 10, 1, 2, 3, 9, 0, 9, 0, 0, 10, 12, 15, 0};
	
	int ret = lis.calculate(arr);
	
	System.out.println("Longest increasing subsequence is " + ret);
    }
    
    public static void longestCommonSubsequenceTest() {
	LCS lcs = new LCS();
	int ret = lcs.calculate("Republican", "Democrat");
	
	System.out.println("LCS = " + ret);
    }
    
    public static void cashierTest() {
	Cashier c = new Cashier();
	int amount = 17;

	int ways = c.countMakeChange(amount);

	System.out.println("Given the amount " + String.valueOf(amount) + ", you can make change " + String.valueOf(ways) + " ways");
    }
    
    public static void editDistanceTest() {
	EditDistance ed = new EditDistance();
	int numberEdits = ed.calcDistance("Ryan", "Ri0n3");
	
	System.out.println("Number of edits = " + String.valueOf(numberEdits));
    }

    public static void editDistanceTest2() {
	EditDistance ed = new EditDistance();
	int numberEdits = ed.calcDistanceBottomUp("Ryan", "Ri0n3");
	
	System.out.println("Number of edits = " + String.valueOf(numberEdits));
    }
    
    public static void phoneNumberCombinationsTest() {
	PhoneNumber pn = new PhoneNumber();
	int []arr = {5,4,1,3,9,6,5,7,1,3};
	pn.printStrings(arr);
    }
    
    public static void threeNumbersThatSumTest() {
	int []arr = {1, 1, 3, 5, 4};
	
	ThreeNumberSum tns = new ThreeNumberSum();

	Stack<Integer> numbers = tns.find(arr, 10);
	if (numbers != null)
	    System.out.println(numbers.toString());
    }

    public static void main(String []args) {
	threeNumbersThatSumTest();
    }
}