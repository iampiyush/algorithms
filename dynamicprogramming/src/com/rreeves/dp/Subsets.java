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
      Prints all subsets of str from a bottom down approach, just calculating
      the subset.
      
      Think of each leaf in the top down approach as being a binary number.
      0 means the item at that bit position isn't in the subset, 1 means it is 
      in the subset.  e.g. If abc is the string, then 001 maps to "c", 010 maps
      to "b", 011 maps to "bc", ..., 111 maps to abc.

      The bottom up approach starts at 1 to 2^n - 1, mapping the bit pattern in each
      number to a subset of str.
     */
    public void printBottomup(String str) {
	double numberSubsets = Math.pow(2, str.length()) - 1;
    
	for (int i = 1; i <= numberSubsets; ++i) {
	    printBitPattern(str, i);
	}
    }
    
    private void printBitPattern(String str, int n) {
	StringBuilder subset = new StringBuilder();
	for (int i = str.length() - 1; i >= 0; --i) {
	    if ((n & 1) == 1) {
		subset.append(str.charAt(i));
	    }
	    n >>= 1;
	}
	System.out.println(subset.toString());
    }    
}