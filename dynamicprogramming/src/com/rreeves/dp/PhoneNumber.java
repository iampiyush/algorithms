package com.rreeves.dp;

import java.util.Stack;

/*
  Prints all possible words produced from a phone number.
  Digit 1 can be A, B, C
  Digit 2 can be D, E, F,
  etc..
  
  Example:
  Digits 1,2 can produce
  A, D
  A, E
  A, F
  B, D
  B, E
  B, F
  C, D
  C, E
  C, F

  Digits 0 and 1 are represented by '_'
 */
public class PhoneNumber {
    private char[][] mTable = new char[10][3];
    
    public PhoneNumber() {
	char ch = 'A';
	for (int i = 2; i <= 9; ++i) {
	    for (int j = 0; j < 3; ++j) {
		mTable[i][j] = ch++; 
	    }
	}
    }
    
    public void printStrings(int []number) {
	Stack<Character> stack = new Stack<Character>();
	printStrings(number, 0, 0, stack);
    }

    private void printStrings(int []number, int i, int j, Stack<Character> stack) {
	if (i == number.length) {
	    System.out.println(stack.toString());
	    stack.pop();
	    return;
	}

	for (int k = 0; k < mTable[0].length; ++k) {
	    pushStack(i, k, number, stack);
	    printStrings(number, i+1, k, stack);
	}

	if (stack.size() > 0)
	    stack.pop();
    }
    
    private void pushStack(int i, int j, int[]number, Stack<Character> stack) {
	if (i < 0)
	    return;

	int index = number[i];
	if (index <= 1)
	    stack.push('_');
	else
	    stack.push(mTable[index][j]);
    }
}