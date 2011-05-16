package com.rreeves.dp;

import java.lang.StringBuilder;
import java.util.Stack;
import java.lang.Math;

public class Subsets {
    
    /*
      Prints all subsets of str from a top down approach.
      A tree is constructed with each leaf being a subset of str.

      Going left doesn't include i in the subsets i+1 to end.
      Going right does include i in the subsets i+1 to end.

      A stack is used to hold the current subset.  Base case is
      when no more characters are available to evaluate.
     */
    public void printTopdown(String str) {
	Stack<Character> stack = new Stack<Character>();
	printRecursive(0, str, stack);
    }

    private void printRecursive(int i, String str, Stack<Character> stack) {
	if (i == str.length()) {
	    System.out.println(stack.toString());
	    return;
	}
	 
	printRecursive(i+1, str, stack);

	stack.push(str.charAt(i));

	printRecursive(i+1, str, stack);

	stack.pop();
    }
    
    /*
      Prints all subsets of str without generating the actual recursion tree.
      
      Think of each leaf in the top down approach as being a number from 0 to 2^n - 1.
      n is the number of characters in the string. So for a three character string like
      "abc" the leaves are 0 to 7. Now think of the number in its binary representation 
      with n bits. So 000, 001, 010, 011, ..., 111.  Each bit maps to the character at
      that location. If the bit is 0, the character isn't in the subset. If the bit
      is 1, the character is in the subset. If abc is the string, then 
      001 maps to the subset "c", 010 maps to "b", 011 maps to "bc", ..., 111 maps to "abc".
      
     */
    public void printBottomup(String str) {
	double numberSubsets = Math.pow(2, str.length()) - 1;
    	for (int i = 0; i <= numberSubsets; ++i) {
	    printBitPattern(str, i);
	}
    }
    
    private void printBitPattern(String str, int n) {
	int len = str.length();
	int bit = len-1;
	Stack<Character> subset = new Stack<Character>();

	for (int i = 0; i < len; ++i) {
	    if (((n>>bit) & 1) == 1) {
		subset.push(str.charAt(i));
	    }
	    bit--;
	}

	System.out.println(subset.toString());
    }
}