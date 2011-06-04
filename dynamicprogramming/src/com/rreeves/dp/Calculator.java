package com.rreeves.dp;

import java.util.ArrayList;
import java.lang.Math;

public class Calculator {
    private ArrayList<Character> mOperators = new ArrayList<Character>();
    private ArrayList<Integer> mOperands = new ArrayList<Integer>();

    public Calculator(String expression) {
	String[] tokens = expression.split("\\s");
	
	for (String tok : tokens) {
	    char ch = tok.charAt(0);
	    if (ch == '+' || ch == '-' ||
		ch == '/' || ch == '*') {
		mOperators.add(ch);
	    } else {
		mOperands.add(Integer.parseInt(tok));
	    }
	}
    }
    
    /* 
       Calculates arrangement of parentheses that maximize expression.
       
       Subproblems are stored in table to avoid duplicate computations.
    */
    public double calcMax() {
	double [][]table = new double[mOperands.size()+1][mOperands.size()+1];
	for (int i = 0; i < table.length; ++i) {
	    for (int j = 0; j < table[0].length; ++j) {
		table[i][j] = -1;
	    }
	}
	return calcMaxRecursive(1, mOperands.size(), table);
    }
    
    private double calcMaxRecursive(int i, int j, double[][]table) {
	if (i == j) {
	    return mOperands.get(i-1);
	}
	
	double ret = Double.MIN_VALUE;

	for (int k = i; k < j; ++k) {
	    
	    if (table[i][k] == -1)
		table[i][k] = calcMaxRecursive(i, k, table);

	    if(table[k+1][j] == -1)
		table[k+1][j] = calcMaxRecursive(k+1, j, table);
	    
	    double left = table[i][k];
	    double right = table[k+1][j];
	    
	    double val = applyOperator(left, right, k);

	    ret = Math.max(ret, val);
	}
	return ret;
    }

    private double applyOperator(double first, double second, int i) {
	char op = mOperators.get(i-1);

	if (op == '+') {
	    return first + second;
	} else if (op == '-') {
	    return first - second;
	} else if (op == '*') {
	    return first * second;
	}
	return first / second;
    }
}