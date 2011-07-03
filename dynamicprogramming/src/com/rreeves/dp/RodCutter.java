package com.rreeves.dp;

/*
  Given a rod of length n in inches and an array of prices at length i, 
  determine the best way to cut the rod to maximize revenue.

  For example:

  Prices: 2 3 5
  length: 1 2 3

  A rod of length 3 can be cut twice producing three rods of length 1. The rod can be cut once producing 
  two rods of length 2 and 1.  Or the rod can not be cut at all, producing a rod of length 3.
  
  Three rods of length 1 is worth 2+2+2=6. Two rods of length 2,1 is worth 3+2=5. And a rod of length
  3 is worth 5.  The best way to cut this example rod is the first way, producing revenue 6.
  
*/
public class RodCutter {
    private int[] mPrices;

    public int maximizeCuts(int []lengthPrices, int rodInches) {
        int[] table = new int[rodInches+1];
        for (int i = 0; i < table.length; ++i) {
            table[i] = -1;
        }

        mPrices = lengthPrices;
        return maximizeCutsRecursive(rodInches, table);
    }

    //Stores sub problems in a table to avoid duplicate computations.
    private int maximizeCutsRecursive(int n, int[] table) {
        if (n == 0)
            return 0;

        int maxRevenue = Integer.MIN_VALUE;

        for (int k = 1; k <= n; ++k) {
            if (table[n-k] == -1)
                table[n-k] = maximizeCutsRecursive(n-k, table);

            maxRevenue = Math.max(maxRevenue, mPrices[k-1] + table[n-k]);
        }

        return maxRevenue;
    }
}