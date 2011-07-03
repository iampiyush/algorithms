package com.rreeves.dp;

import java.util.Stack;

/*
  Recursive algorithm that looks at an array and determines if three numbers sum another number.
  If so the numbers are returned in a stack. Otherwise null.

  TODO - need to table sub problems.
*/
public class ThreeNumberSum {
    public Stack<Integer> find(int []arr, int sum) {
        Stack<Integer> stack = new Stack<Integer>();

        if (find(0, sum, 3, arr, stack))
            return stack;
	
        return null;
    }
    
    private boolean find(int i, int sum, int c, int[] arr, Stack<Integer> stack) {
        if (sum == 0 && c == 0) 
            return true;
	
        if (sum < 0 || i == arr.length || c < 0)
            return false;

        if (arr[i] > sum) {
            return find(i+1, sum, c, arr, stack);
        } else {
	    
            boolean ret = find(i+1, sum, c, arr, stack);

            if (!ret) {
                stack.push(arr[i]);
                ret = find(i+1, sum - arr[i], c - 1, arr, stack);

                if (ret == false && stack.size() > 0)
                    stack.pop();
            }
            return ret;
        }
    }
}