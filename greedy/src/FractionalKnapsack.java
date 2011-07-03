import java.util.Arrays;
import java.lang.Comparable;

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
