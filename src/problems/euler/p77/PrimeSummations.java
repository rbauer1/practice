package problems.euler.p77;

import java.util.Arrays;

/**
 * Yet another variation of the coin sum question
 */
public class PrimeSummations {
	private int dollar;
	private int[] coins;
	private int[][] table;

	public static void main(String[] args) {
		int[] c = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53,
				59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113,
				127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181,
				191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251,
				257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317,
				331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397,
				401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463,
				467, 479, 487, 491, 499, 503, 509, 521, 523, 541, 547, 557,
				563, 569, 571, 577, 587, 593, 599, 601, 607, 613, 617, 619,
				631, 641, 643, 647, 653, 659, 661, 673, 677, 683, 691, 701,
				709, 719, 727, 733, 739, 743, 751, 757, 761, 769, 773, 787,
				797, 809, 811, 821, 823, 827, 829, 839, 853, 857, 859, 863,
				877, 881, 883, 887, 907, 911, 919, 929, 937, 941, 947, 953,
				967, 971, 977, 983, 991, 997, 1009, 1013, 1019 };
		for(int i=10; i<1020; i++){
			final PrimeSummations cdf = new PrimeSummations(i, c);
			cdf.waysToMakeX();
			if(cdf.table[cdf.dollar][cdf.coins.length] > 5000){
				System.out.println(i);
				break;
			}
		}
	}

	public PrimeSummations(int dollar, int[] coins) {
		Arrays.sort(coins);
		this.dollar = dollar;
		this.table = new int[dollar + 1][coins.length + 1];
		this.coins = coins;
	}

	public void waysToMakeX() {
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < coins.length + 1; j++) {
				if (i == 0) {
					table[i][j] = 1;
				} else if (j == 0) {
					table[i][j] = 0;
				} else if (coins[j - 1] > i) {
					table[i][j] = table[i][j - 1];
				} else {
					table[i][j] = table[i - coins[j - 1]][j] + table[i][j - 1];
				}
			}
		}
	}

}
