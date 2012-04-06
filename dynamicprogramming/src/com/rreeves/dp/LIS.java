package com.rreeves.dp;

public class LIS {
    
    /*
      For each item in the array, get the longest
      increasing subsequence to that item.
      
      return the max increasing subsequence to that item, adding
      1 for the item itself.

      In both calculate and longestToN, subproblem results  are stored and reused to 
      avoid duplicate computation.
    */
    public int calculate(int []arr) {
        int []table = new int[arr.length];

        for (int i = 0; i < arr.length; ++i) {
            table[i] = -1;
        }

        int m = 0;
	
        for (int n = 0; n < arr.length; ++n) {

            if (table[n] == -1) {//Memoize
                table[n] = longestToN(n, arr, table);
            }

            m = Math.max(m, longestToN(n, arr, table));
        }
        return m + 1;
    }

    /*
      Recursively finds the longest increasing subsequence in array up until arr[n] inclusive.
      
    */
    private int longestToN(int n, int []arr, int []table) {
        int m = 0;
        for (int i = n; i >= 0; --i) {
            if (arr[i] > arr[n]) {

                if (table[i] == -1) {//Memoize
                    table[i] = longestToN(i, arr, table);
                }

                m = Math.max(m, 1 + table[i]);
            }
        }
        return m;
    }
}
