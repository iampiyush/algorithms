package com.rreeves.dp;

public class LCS {
    public int calculate(String one, String two) {
        int [][]table = new int[one.length()+1][two.length()+1];
        for (int i = 0; i < table.length; ++i) {
            for (int j = 0; j < table[0].length; ++j) {
                table[i][j] = -1;
            }
        }
        return calcRecursive(one.toCharArray(), two.toCharArray(), one.length()-1, two.length()-1, table);
    }

    /*
      Calculates the longest common subsequence by evaulating one[i] and two[j]. Starts at end of strings.
      if equal, add one and process i - 1 and j - 1.
      if not equal evaluate both possibilities: (i-1, j) and (i, j-1).
    
      Algorithm is memoized to prevent duplicate computation. Method calcMemoized 
      caches results in table.
    */
    private int calcRecursive(char[] one, char[] two, int i, int j, int [][]table) {
        if (i < 0 || j < 0)
            return 0;

        if (one[i] == two[j]) {
            return 1 + calcMemoized(i - 1, j - 1, one, two, table);

        } else {
            int first = calcMemoized(i, j - 1, one, two, table);

            int second = calcMemoized(i - 1, j, one, two, table);

            return Math.max(first, second);
        }
    }

    private int calcMemoized(int i, int j, char[]one, char[] two, int [][]table) {
        if (i < 0 || j < 0)
            return 0;

        if (table[i][j] == -1)
            table[i][j] = calcRecursive(one, two, i, j, table);

        return table[i][j];
    }
}