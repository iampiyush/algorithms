package com.rreeves.dp;

public class BinomialCoefficients {
    public int calculate(int n, int k) {
	int [][]table = new int[n+1][k+1];
	for (int i = 0; i < table.length; ++i) {
	    for (int j = 0; j < table[0].length; ++j) {
		table[i][j] = -1;
	    }
	}

	return calculateRecursive(n, k, table);
    }

    private int calculateRecursive(int n, int k, int [][]table) {
	if (k <= 0 || n <= 0)
	    return 0;

	if (k == n)
	    return 1;

	if (table[n-1][k] == -1) 
	    table[n-1][k] = calculateRecursive(n-1, k, table);
	
	if (table[n-1][k-1] == -1)
	    table[n-1][k-1] = calculateRecursive(n-1, k-1, table);

	return table[n-1][k] + table[n-1][k-1];
    }
    
    //TODO - calculate bottom up
    //private int bottomUp(...)
}