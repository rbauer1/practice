package problems.misc;

import java.util.Arrays;

public class LCS_K_Occurences {
	public static void main(String[] args) {
		System.out.println(findLCS_WithK_Occurences("GATTACA",
				"TACATTACGCATTACACAT", 3));
	}

	/**
	 * Modified version of traditional longest common substring problem using
	 * Dynamic Programming. First, we create the table as one normally would
	 * searching for the LCS with rows corresponding to s1 and columns
	 * corresponding to s2 (after the first row and column as 0s). Then, each
	 * row of the table is sorted. We then examine the kth to last column of the
	 * newly sorted table. This will hold the longest suffix of s1 and s2 for a
	 * given prefix that has occurred k times. We begin at the final row and
	 * move upward keeping track of the greatest value we come across (the
	 * length of the LCS) and the row it occurs in (which will allows us to find
	 * the LCS itself) When the row index - (largest value found) is less than
	 * 0, there cannot be a longer substring in the unexamined rows and we can
	 * confidently return the substring of s1 beginning at index-(largest value
	 * found) and ending at index
	 * 
	 * @param s1 the string which must have at least one occurrence of the LCS
	 * @param s2 the string which must have the k occurrences of the LCS
	 * @param k the minimum number of times that the substring must occur in s2 
	 * @return the LCS of s1 and s2 occurring at least k times in s2
	 */
	public static String findLCS_WithK_Occurences(String s1, String s2, int k) {
		if (s1 == null || s2 == null || k < 1) {
			if (k == 0) {
				return s1;
			}
			return "";
		}
		int[][] compTable = new int[s1.length() + 1][s2.length() + 1];
		for (int i = 0; i < compTable.length; i++) {
			compTable[i][0] = 0;
		}
		for (int i = 0; i < compTable[0].length; i++) {
			compTable[0][i] = 0;
		}
		for (int i = 1; i < compTable.length; i++) {
			for (int j = 1; j < compTable[0].length; j++) {
				if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					compTable[i][j] = compTable[i - 1][j - 1] + 1;
				} else {
					compTable[i][j] = 0;
				}
			}
		}
		for (int[] i : compTable) {
			Arrays.sort(i);
		}
		int largest = 0;
		int index = 0;
		for (int i = compTable.length - 1; i - largest > 0; i--) {
			final int candidate = compTable[i][compTable[0].length - k];
			if (candidate > largest) {
				largest = candidate;
				index = i;
			}
		}
		return s1.substring(index - largest, index);
	}
}
