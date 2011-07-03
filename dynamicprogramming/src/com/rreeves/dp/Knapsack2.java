import java.lang.Math;

public class Knapsack2 {
    private int []mWeights;
    private int []mValues;
    private int mCapacity;
    
    public Knapsack2(int[] weights, int []values, int capacity) {
        mWeights = weights;
        mValues = values;
        mCapacity = capacity;
    }
    
    /*
      Knapsack algorithm - Same as Knapsack.java
      except evaluates string starting at end of the srting.
      
      This algorithm treats i as a 1-based index. getValue/getWeight
      abstract indexing into mValues/mWeights to make the algorithm
      easier to follow. eg - if capacity is 7 and number of items
      to evaluate is 4, the method would be invoked like this the first time:

      fillSackRecursive(7, 4);

    */
    public int fillSack() {
        int [][]table = new int[mCapacity+1][mWeights.length];
        for (int i = 0; i < table.length; ++i) {
            for (int j = 0; j < table[0].length; ++j) {
                table[i][j] = -1;
            }
        }

        return fillSackRecursive(mCapacity, mWeights.length, table);
    }
    
    private int fillSackRecursive(int c, int i, int [][]table) {
        if (c <= 0 || i == 0)
            return 0;

        if (getWeight(i) > c) {
            if (table[c][i-1] == -1)
                table[c][i-1] = fillSackRecursive(c, i-1, table);

            return table[c][i-1];

        } else {
            if (table[c][i-1] == -1)
                table[c][i-1] = fillSackRecursive(c, i-1, table);
	    
            int unselected = table[c][i-1];
	    
            int reducedCapacity = c - getWeight(i);
            if (table[reducedCapacity][i-1] == -1) 
                table[reducedCapacity][i-1] = fillSackRecursive(reducedCapacity, i-1, table);

            int selected = getValue(i) + table[reducedCapacity][i-1];

            return Math.max(unselected, selected);
        }
    }
     
    private int getWeight(int i) {
        return mWeights[i-1];
    }
    
    private int getValue(int i) {
        return mValues[i-1];
    }
}