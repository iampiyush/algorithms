package com.rreeves.dp;

import java.lang.Math;

public class LongestPalindrome {
    private String mStr;

    public int calculate(String str) {
	int len = str.length();
	mStr = str;

	int [][]table = new int[len+1][len+1];
	for (int i = 0; i < table.length; ++i) {
	    for (int j = 0; j < table[0].length; ++j) {
		table[i][j] = -1;
	    }
	}

	return calcRecursive(0, len-1, table);
    }
    
    //Sub problems are stored in table to prevent duplicate computations.
    private int calcRecursive(int i, int j, int [][]table) {
	if (i == j)
	    return 1;

	if (i > j)
	    return 0;
	  
	char first = mStr.charAt(i);
	char last = mStr.charAt(j);

	if (first == last) {

	    if (table[i+1][j-1] == -1)
		table[i+1][j-1] = calcRecursive(i+1, j-1, table);

	    return 2 + table[i+1][j-1];
	} else {

	    if (table[i][j-1] == -1)
		table[i][j-1] = calcRecursive(i, j-1, table);
	    
	    if (table[i+1][j] == -1)
		table[i+1][j] = calcRecursive(i+1, j, table);

	    int left = table[i][j-1];
	    int right = table[i+1][j];
	    
	    return Math.max(left, right);
	}
    }
}