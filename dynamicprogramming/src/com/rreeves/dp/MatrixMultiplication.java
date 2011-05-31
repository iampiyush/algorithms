package com.rreeves.dp;

public class MatrixMultiplication {
    private Matrix []mMatrices;
   
    public enum Type{
	TOP_DOWN, BOTTOM_UP
    }

    public MatrixMultiplication(Matrix[] matrices) {
	mMatrices = matrices;
    }
    
    public int computeMin(Type type) {
	if (type == Type.TOP_DOWN) {
	    int [][]table = new int[mMatrices.length+1][mMatrices.length+1];
	    initTable(table);
	    return computeMinRecursive(1, mMatrices.length, table);
	}
	
	return computeMinBottomup(1, mMatrices.length);
    }

    public void print() {
	int [][]kMatrix = new int[mMatrices.length+1][mMatrices.length+1];
	
	computeMinWithParens(1, mMatrices.length, kMatrix);
	printParens(1, mMatrices.length, kMatrix);
	
	System.out.println("");
    }
   
    private void printParens(int i, int j, int [][]kMatrix) {
	if (i == j) {
	    System.out.print("m" + String.valueOf(i));
	    return;
	}

	System.out.print("(");
	int k = kMatrix[i][j];

	printParens(i, k, kMatrix);
	printParens(k + 1, j, kMatrix);
	
	System.out.print(")");
    }
   
    private int computeMinWithParens(int i, int j, int [][]kMatrix){
	if (i == j) {
	    return 0;
	}
	
	int min = Integer.MAX_VALUE;

	for (int k = i; k < j; ++k) {
	    int left = computeMinWithParens(i, k, kMatrix);
	    int right = computeMinWithParens(k+1, j, kMatrix);
	    
	    int total = left + right + multiply(i, k, j);

	    if (total < min) {
		min = total;
		kMatrix[i][j] = k;//Mark where the cut occurs so we can reconstruct later.
	    }
	}
	return min;
    } 

    /*
      Calculates the minimum multiplications needed to multiply an array of matrices. Think of 
      how to arrange parentheses in an array of matrices: A1, A2, A3, A4.

      ((A1) (A2, A3, A4)) 
      ((A1, A2) (A3, A4)),
      etc.. 

      This algorithm determines the arrangement of parentheses that produces the minimum number of multiplications.
      
      The idea is to calculate the multiplications of matrices i to k, calculate the 
      multiplications of k+1 to j, then calculate how many multiplications are needed to 
      multiply the resulting matrices of (i,k) and (k+1, j). Do this for all k, i<=k<j, and take minimum.

      Sub problems are saved in a table to prevent duplicate computation. 
     */
    private int computeMinRecursive(int i, int j, int [][]table) {
	if (i == j)  {
	    return 0;
	}
	
	int min = Integer.MAX_VALUE;

	for (int k = i; k < j; ++k) {
	    if (table[i][k] == -1) 
		table[i][k] = computeMinRecursive(i, k, table);

	    if (table[k+1][j] == -1)
		table[k+1][j] = computeMinRecursive(k+1, j, table);

	    min = Math.min(min, table[i][k] + table[k+1][j] + multiply(i, k, j));
	}
	return min;
    }
    
    private void printParen(int i, int j) {
	System.out.print("(" + String.valueOf(i) + "," + String.valueOf(j) + ")");
    }

    /*
      Computes answer from the bottom up.  First the base case subproblems are
      generated. Then table is filled in diagonally, computing subproblems from the bottom up
      until the entire subproblem table is filled in.
      
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
    
    private void initTable(int [][]table) {
	for (int i = 0; i < table.length; ++i) {
	    for (int j = 0; j < table[0].length; ++j) {
		table[i][j] = -1;
	    }
	}
    }
}