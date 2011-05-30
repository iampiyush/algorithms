package com.rreeves.dp;

public class MatrixMultiplication {
    private Matrix []mMatrices;
    
    public MatrixMultiplication(Matrix[] matrices) {
	mMatrices = matrices;
    }
    
    public int computeMin() {
	return computeMinRecursive(1, mMatrices.length);
    }

    public int computeMinFaster() {
	return computeMinBottomup(1, mMatrices.length);
    }
    

    /*
      Calculates the minimum multiplications needed to multiply Matrices in mMatrices.
      
      The idea is to calculate the multiplications of matrices i to k, then calculate the 
      multiplications of k+1 to j. Now calculate the cost of multiplying (i, k) and (k+1, j) together.
      
      Do this for all k, i<=k<j and take minimum.
      
      Non-memoized version, runs in exponential time.

      //TODO - memoize
     */
    private int computeMinRecursive(int i, int j) {
	if (i == j) {
	    return 0;
	}

	int minComputations = Integer.MAX_VALUE;

	for (int k = i; k < j; ++k) {
	    int left = computeMinRecursive(i, k);
	    int right = computeMinRecursive(k+1, j);
	    int product = left + right + multiply(i, k, j);
	   
	    if (product < minComputations)
		minComputations = product;
	}
	return minComputations;
    }
    
    /*
      Uses knowledge of the recursive structure from computeMinRecursive to
      recreate the subproblems from the bottom up.  First the base case subproblems are
      generated. 

      Then table is filled in diagonally.

      Time complexity - O(n^3)
     */
    private int computeMinBottomup(int i, int j) {
	int [][]table = new int[mMatrices.length+1][mMatrices.length+1];
	for (int t = 1; t <= mMatrices.length; ++t)
	    table[t][t] = 0;
	
	for (int diag = i+1; diag <= j; ++diag) {//For each diagonal
	   
	    int di = i;
	    
	    for (int dj = diag; dj <= j; ++dj) {//Move down diagonal, top left to bottom right.
		int k = di;
		int min = Integer.MAX_VALUE;
		
		while (k < dj) {//Determine best way to group matrices.
		    min = Math.min(min, table[di][k] + table[k+1][dj] + multiply(di, k, dj));
		    k++;
		}
		
		table[di][dj] = min;//Store result to sub problem.
		di++;
	    }
	}
	return table[i][j];
    }

    private int multiply(int i, int k, int j) {
	return mMatrices[i-1].rows * mMatrices[k-1].cols * mMatrices[j-1].cols;
    }
}