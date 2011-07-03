public class KnapsackItem implements Comparable<KnapsackItem> {
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
