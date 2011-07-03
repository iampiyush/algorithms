package com.rreeves.dp;

public class Knapsack {
    private int []mWeights;
    private int []mValues;
    private int mLength;
    private int mCapacity;

    public Knapsack(int[] weights, int []values, int capacity) {
        if (weights.length != values.length)
            throw new IllegalArgumentException();
	
        mCapacity = capacity;
        mWeights = weights;
        mValues = values;
        mLength = mValues.length;
    }
    
    /*
      Recsurive version of the knapsack algorithm with
      memoization.
      
      Results from recusrive calls are saved in a table. Subsequent 
      calls to the same subproblem are retrieved from the table instead
      of recomputing them.
    */
    public int fillSackFaster() {
        int [][]table = new int[mCapacity+1][mValues.length+1];
        for (int i = 0; i < table.length; ++i) {
            for (int j = 0; j < table[0].length; ++j) {
                table[i][j] = -1;
            }
        }
        return fillSackRecursive(mCapacity, 0, table);
    }

    private int fillSackRecursive(int capacity, int i, int[][] table) {
        if (i == mLength) {
            if (table[capacity][i] == -1)
                table[capacity][i] = 0;

            return 0;
        }
	
        if (capacity < mWeights[i]) {
            if (table[capacity][i+1] == -1) 
                table[capacity][i+1] = fillSackRecursive(capacity, i+1, table);
	    
            return table[capacity][i+1];

        } else {
            if (table[capacity][i+1] == -1)
                table[capacity][i+1] = fillSackRecursive(capacity, i+1, table);

            int unselected = table[capacity][i+1];

            if (table[capacity - mWeights[i]][i+1] == -1)
                table[capacity - mWeights[i]][i+1] = fillSackRecursive(capacity-mWeights[i], i+1, table);
	    
            int selected = mValues[i] + table[capacity-mWeights[i]][i+1];

            return Math.max(unselected, selected);
        }
    }

    /*
      Recursive version of the knapsack algorithm with no 
      memoization.

      Pick items from the mValues array that are at most
      mCapacity. Values put in the sack should maximize 
      value.
    */
    public int fillSackSlow() {
        return fillSackRecursive(mCapacity,0);
    }

    private int fillSackRecursive(int capacity, int i) {
        if (i == mLength) 
            return 0;

        if (mWeights[i] > capacity) {
            return fillSackRecursive(capacity, i+1);
        } 
	
        int unselect = fillSackRecursive(capacity, i+1);

        int newWeight = capacity - mWeights[i];
        int selected = mValues[i] + fillSackRecursive(newWeight, i+1);

        return Math.max(unselect, selected);
    }
}

