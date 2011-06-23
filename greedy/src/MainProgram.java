import java.util.Arrays;
import java.lang.Comparable;

class KnapsackItem implements Comparable<KnapsackItem> {
    private int mWeight;
    private int mValue;
    private double mValuePerPound;

    public KnapsackItem(int value, int weight) {
	mValue = value;
	mWeight = weight;
	mValuePerPound = (double)mValue / mWeight;
    }
    
    public int getWeight() {
	return mWeight;
    }

    public int getValue() {
	return mValue;
    }
    
    public double getValuePerPound() {
	return mValuePerPound;
    }
    
    //Sorts in decreasing order
    public int compareTo(KnapsackItem i) {
	double d = i.getValuePerPound() - getValuePerPound();
	
	if (d < 0)
	    return -1;
	
	if (d > 0)
	    return 1;
	
	return 0;
    }
}

class FractionalKnapsack {
    public double calc(int capacity, KnapsackItem []items) {
	Arrays.sort(items);
	return calcRecursive(0, capacity, items);
    }

    public double calc2(int capacity, KnapsackItem []items) {
	Arrays.sort(items);
	return calcIterative(capacity, items);
    }

    private double calcRecursive(int i, int capacity, KnapsackItem []items) {
	if (capacity <= 0) 
	    return capacity * items[i-1].getValuePerPound();
	
	if (i == items.length)
	    return 0;

	return items[i].getValue() + calcRecursive(i+1, capacity - items[i].getWeight(), items);
    }

    private double calcIterative(int capacity, KnapsackItem []items) {
	double ret = 0.0f;
	int i = 0;

	while (capacity > 0 && i < items.length) {
	    capacity -= items[i].getWeight();
	    ret += items[i].getValue();
	    i++;
	}
	
	if (capacity < 0)
	  ret += capacity * items[i-1].getValuePerPound();

	return ret;
    }
}

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
