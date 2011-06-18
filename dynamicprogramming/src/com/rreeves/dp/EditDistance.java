package com.rreeves.dp;

import java.lang.Math;

public class EditDistance {
    public int calcDistanceBottomUp(String wordOne, String wordTwo) {
	char []one = wordOne.toCharArray();
	char []two = wordTwo.toCharArray();
	int ret = 0;
	int [][]table = new int[one.length][two.length];
	
	for (int i = 0; i < table.length; ++i) {
	    for (int j = 0; j < table[0].length; ++j) {
		if (one[i] == two[j]) {
		    table[i][j] = getTableValue(table, i-1, j-1);
		} else {
		    int sub = getTableValue(table, i-1, j-1) + 1;
		    int del = getTableValue(table, i-1, j) + 1;
		    int ins = getTableValue(table, i, j-1) + 1;
		    table[i][j]  = Math.min(sub, Math.min(del, ins)); 
		}
	    }
	}
	return table[one.length-1][two.length-1];
    }
    
    private int getTableValue(int [][]table, int i, int j) {
	if (i < 0 || j < 0) 
	    return 0;

	return table[i][j];
    }
    
    /*
      Calculates number of edits needed to turn wordOne into wordTwo.
     */
    public int calcDistance(String wordOne, String wordTwo) {
	int [][]table = new int[wordOne.length()][wordTwo.length()];
	for (int i = 0; i < wordOne.length(); ++i) {
	    for (int j = 0; j < wordTwo.length(); ++j) {
		table[i][j] = -1;
	    }
	}

	return calcDistanceRecursive(wordOne.length()-1, wordTwo.length()-1, wordOne.toCharArray(), wordTwo.toCharArray(), table);
    }

    private int calcDistanceRecursive(int i, int j, char[] one, char[] two, int [][]table) {
	if (i < 0 || j < 0)
	    return 0;

	if (one[i] == two[j]) {
	    return calcDistanceMemoize(i-1, j-1, one, two, table);
	} else {
	    int sub = 1 + calcDistanceMemoize(i-1, j-1, one, two, table);
	    int del = 1 + calcDistanceMemoize(i-1, j, one, two, table);
	    int ins = 1 + calcDistanceMemoize(i, j-1, one, two, table);

	    return Math.min(sub, Math.min(del, ins));
	}
    }
    
    //helper function that calls calcDistanceRecursive and tables results.
    private int calcDistanceMemoize(int i, int j, char[] one, char[] two, int [][]table) {
	if (i < 0 || j < 0)
	    return 0;

	if (table[i][j] == -1) 
	    table[i][j] = calcDistanceRecursive(i, j, one, two, table);
	
	return table[i][j];
    }
    
}