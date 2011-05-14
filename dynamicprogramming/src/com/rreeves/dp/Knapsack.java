package com.rreeves.dp;

import java.lang.IllegalArgumentException;

public class Knapsack {
    private int []mWeights;
    private int []mValues;
    private int mLength;
    private int mAllowedWeight;

    public Knapsack(int[] weights, int []values, int allowedWeight) {
	if (weights.length != values.length)
	    throw new IllegalArgumentException();
	
	mAllowedWeight = allowedWeight;
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
	return 0;
    }
      /*
	Recursive version of the knapsack algorithm with no 
	memoization.

	Pick items from the mValues array that are at most
	mAllowedWeight. Values put in the sack should maximize 
	value.
     */
    public int fillSackSlow() {
	return fillSackRecursive(mAllowedWeight,0);
    }

    private int fillSackRecursive(int allowedWeight, int i) {
	if (i == mLength) 
	    return 0;

	if (mWeights[i] > allowedWeight) {
	    return fillSackRecursive(allowedWeight, i+1);
	} 
	
	int next = fillSackRecursive(allowedWeight, i+1);

	int newWeight = allowedWeight - mWeights[i];
	int current = mValues[i] + fillSackRecursive(newWeight, i+1);

	return Math.max(next, current);
    }
}

