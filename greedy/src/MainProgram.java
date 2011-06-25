
public class MainProgram {
    public static void main(String []args) {
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
}
