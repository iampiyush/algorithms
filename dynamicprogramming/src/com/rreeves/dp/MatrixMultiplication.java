package com.rreeves.dp;

public class MatrixMultiplication {
    private Matrix []mMatrices;
    
    public MatrixMultiplication(Matrix[] matrices) {
	mMatrices = matrices;
    }
    
    //Returns minimum multiplications required to mutiply mMatrices.
    public int minimumMultiplications() {
	return computeMinRecursive(1, mMatrices.length);
    }

    /*
      Calculates the minimum multiplications needed to multiply Matrices in mMatrices.
      
      The idea is to calculate the multiplications of matrices i to k, then calculate the 
      multiplications of k+1 to j. Now calculate the cost of multiplying (i, k) and (k+1, j) together.
      
      Do this for all k, i<=k<j and take minimum.
      
      Non-memoized version, runs in exponential time.
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
   
    //Memoized version - stores duplicate sub problems.
    private int computeMinRecursive(int i, int j, int [][]table) {
	//TODO
	return 0;
    }
    

    //TODO - calculate best way to parenthesis matrices to produce 
    //least computations. Print the actual parens.
    //public void printParenthesis(int i, int j) {...

    private int multiply(int i, int k, int j) {
	return mMatrices[i-1].rows * mMatrices[k-1].cols * mMatrices[j-1].cols;
    }
}