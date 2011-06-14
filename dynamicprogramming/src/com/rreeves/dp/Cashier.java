package com.rreeves.dp;

public class Cashier {
    private int []mCoins = {1, 5, 10, 25, 50};
    
    public int countMakeChange(int amount) {
	int [][]table = new int[mCoins.length+1][amount+1];
	for (int i = 0; i < table.length; ++i) {
	    for (int j = 0; j < table[0].length; ++j) {
		table[i][j] = -1;
	    }
	}

	return countRecursive(0, amount, table);
    }

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