import com.rreeves.dp.Subsets;
import com.rreeves.dp.Knapsack;

public class DriverProgram {
    public static void main(String []args) {
	int []values = {19, 2, 9, 8, 99};
	int []weight = {4, 2, 1, 1 ,5};
	int capcity = 8;

	Knapsack ks = new Knapsack(weight, values, capcity);
	int max = ks.fillSackSlow();
	
	System.out.println("Maximum combination of values in sack is " + max);

	String str = "abcd";
	Subsets c = new Subsets();
	
	System.out.println("Print top down");
	c.printTopdown(str);
	
	System.out.println("\n\nPrint bottom up\n\n");
	c.printBottomup(str);
    }
}