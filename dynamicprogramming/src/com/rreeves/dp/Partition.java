package com.rreeves.dp;

public class Partition {
    
    //Returns true of arr can be partitioned into
    //two subsets with equal sums.
    public boolean canPartition(int []arr) {
	int sum = 0;

	for (int i = 0; i < arr.length; ++i) 
	    sum += arr[i];

	if ((sum & 1) == 1)
	    return false;
	
	int half = sum>>1;
	
	Boolean [][]table = new Boolean[arr.length+1][half+1];
	
	return calcRecursive(arr, 0, half, table);
    }

    /*
      Recursive algorithm to determine if arr can be partitioned into two subsets, 
      each subset equal to sum.

      This algorithm looks at combinations of selecting arr[i] and not selecting arr[i].
      If any selection of arr[i] reduces sum to 0, a solution is found and there exists
      some set of items in arr that equal sum.

      Algorithm uses memoization to avoid duplicate computations.
     */
    private boolean calcRecursive(int []arr, int i, int sum, Boolean [][]table) {
	if (sum == 0)//Solution found
	    return true;
	
	if (i == arr.length || sum < 0)
	    return false;
	
	if (sum - arr[i] < 0) {//Move to next item, arr[i] too large to be part of the solution.

	    if (table[i+1][sum] == null)
		table[i+1][sum] = calcRecursive(arr, i+1, sum, table);
	
	    return table[i+1][sum];
	}	
	    

	if (table[i+1][sum] == null)
	    table[i+1][sum] = calcRecursive(arr, i+1, sum, table);

	int smallerSum = sum - arr[i];
	
	if (table[i+1][smallerSum] == null)
	    table[i+1][smallerSum] = calcRecursive(arr, i+1, smallerSum, table);
	
	return table[i+1][smallerSum];
    }

    //TODO - write method that returns two sets with the items in it.
}