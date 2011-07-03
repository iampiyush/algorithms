package com.rreeves.dp;

public class Cashier {
    private int []mCoins = {1, 5, 10, 25, 50};
    
    /*
      Counts the number of ways to make change from amount.
    */
    public int countMakeChange(int amount) {
        int [][]table = new int[mCoins.length+1][amount+1];
        for (int i = 0; i < table.length; ++i) {
            for (int j = 0; j < table[0].length; ++j) {
                table[i][j] = -1;
            }
        }

        return countRecursive(0, amount, table);
    }
    
    /*
      Algorithm to build combinations of mCoins, looking for combinations that equal amount. Uses memoization to avoid duplicate computation.

      The code is a slightly modified knapsack algorithm. From left to right evaluate the coins. 

      Base cases:
      i == mCoins.length, meaning no more coins available. Returns 0.
      amount < 0, meaning a coin selection of i exceeded the amount. Returns 0.
      amount == 0, meaning a combination of coins was formed that equals amount. Returns 1.

      The algorithm looks at the current coin at i. If this coin is greater than amount, don't evaluate coin - it's too large.
      In the else case, try both possibilities at i. We can recursively select i, subtracting the coin at i from amount. Or we
      can not select i and go to the next coin.
      
      Base case of amount == 0 will return 1 indicating making change is possible at that combination, so now we just need to sum
      both cases: not selected and selected.  This is the total number of ways to make change from amount using items in mCoins.
      
    */
    private int countRecursive(int i, int amount, int [][]table) {
        if (i == mCoins.length || amount < 0)
            return 0;
	
        if (amount == 0)
            return 1;

        if (mCoins[i] > amount) {

            if (table[i+1][amount] == -1) 
                table[i+1][amount] = countRecursive(i+1, amount, table);

            return table[i+1][amount];

        } else {

            int newAmount = amount - mCoins[i];

            if (newAmount < 0)
                return 0;
	    
            if (table[i][newAmount] == -1)
                table[i][newAmount] = countRecursive(i, newAmount, table);

            if (table[i+1][amount] == -1) 
                table[i+1][amount] = countRecursive(i+1, amount, table);

            int selected = table[i][newAmount];
            int unselected = table[i+1][amount];

            return selected+unselected;
        }
    }
}