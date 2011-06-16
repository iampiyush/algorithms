package com.rreeves.dp;

import java.lang.Math;

public class EditDistance {
    /*
      Calculates number of edits needed to turn wordOne into wordTwo.
     */
    public int calcDistance(String wordOne, String wordTwo) {
	return calcDistanceRecursive(wordOne.length()-1, wordTwo.length()-1, wordOne.toCharArray(), wordTwo.toCharArray());
    }

    private int calcDistanceRecursive(int i, int j, char[] one, char[] two) {
	if (i < 0 || j < 0)
	    return 0;

	if (one[i] == two[j]) {
	    return calcDistanceRecursive(i-1, j-1, one, two);
	} else {
	    int sub = 1 + calcDistanceRecursive(i-1, j-1, one, two);
	    int del = 1 + calcDistanceRecursive(i-1, j, one, two);
	    int ins = 1 + calcDistanceRecursive(i, j-1, one, two);

	    return Math.min(sub, Math.min(del, ins));
	}
    }
}