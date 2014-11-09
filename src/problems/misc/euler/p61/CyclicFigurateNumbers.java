package problems.misc.euler.p61;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class CyclicFigurateNumbers {
	interface PolyFunc {
		int polyFunction(int n);
	}

	/**
	 * This ArrayList has 6 slots corresponding to the six sets of triangle,
	 * square, pentagonal, etc numbers. Each slot has a HashMap that contains an
	 * ArrayList of all the integers with the same 2-digit prefix of the set
	 * corresponding to the index of the greater ArrayList. The keys for the
	 * HashMap are the first two digits of the numbers in the ArrayList at that
	 * position in the map. In this way, finding corresponding numbers in the
	 * next set is O(1).
	 */
	private static ArrayList<HashMap<Integer, ArrayList<Integer>>> polyNums;

	public static void main(String[] args) {
		long time = System.currentTimeMillis();
		// This is an array of functions for generating each set
		PolyFunc[] funcs = { (int n) -> {
			return (n * (n + 1)) / 2;
		}, (int n) -> {
			return n * n;
		}, (int n) -> {
			return (n * (3 * n - 1)) / 2;
		}, (int n) -> {
			return (n * (2 * n - 1));
		}, (int n) -> {
			return (n * (5 * n - 3)) / 2;
		}, (int n) -> {
			return (n * (3 * n - 2));
		}

		};
		polyNums = new ArrayList<HashMap<Integer, ArrayList<Integer>>>();
		// These represent the bounds for each set in terms of 4-digit numbers
		// from the corresponding functions in funcs.
		int[] starts = { 45, 32, 26, 23, 21, 19 };
		int[] ends = { 140, 99, 81, 70, 63, 58 };
		for (int i = 0; i < 6; i++) {
			HashMap<Integer, ArrayList<Integer>> thisSet = new HashMap<Integer, ArrayList<Integer>>();
			for (int j = starts[i]; j <= ends[i]; j++) {
				final int current = funcs[i].polyFunction(j);
				if (current % 100 > 9) { //these numbers cannot have a corresponding 4-digit number because 0932 is considered 3-digit.
					final int first2Digits = current / 100;
					if (thisSet.get(first2Digits) == null) {
						ArrayList<Integer> intsWithSamePrefix = new ArrayList<Integer>();
						intsWithSamePrefix.add(current);
						thisSet.put(first2Digits,
								intsWithSamePrefix);
					} else {
						thisSet.get(first2Digits).add(current);
					}
				}
			}
			polyNums.add(thisSet);
		}
		// System.out.println(sum+"\t"+Arrays.toString(i));
		// }
		outer: for (ArrayList<Integer> ar : polyNums.get(5).values()) {
			for (int i : ar) {
				final int[] path = find(i, 0, i, new int[][] { { 0, 1, 2, 3, 4 },
						{ 0, 0, 0, 0, 0 } }, new int[] { i, 0, 0, 0, 0, 0 });
				if (path != null) {
					System.out.println(Arrays.toString(path));
					int sum = 0;
					for(int x : path){
						sum+=x;
					}
					System.out.println(sum);
					System.out.println(System.currentTimeMillis() - time);
					break outer;
				}
			}
		}
	}

	/**
	 * Recursive method that follows the idea of the silly iterative method
	 * findCycle();
	 * 
	 * @param i
	 *            the current number being examined
	 * @param depth
	 *            how many sets we've looked at
	 * @param firstNum
	 *            the original number from set 6. if we get back to set 6 (depth
	 *            == 6) and find this number, we have our cycle
	 * @param useableSets
	 *            a simple 2x5 array that lets the algorithm know which sets it
	 *            has already used, so which are still available for use
	 * @param cycle
	 *            the numbers in the cycle thus far, if the method succeeds this
	 *            will be non-null and the answer
	 * @return a cycle of 6 numbers, one from each set of numbers such that the
	 *         next number begins with the same two digits the previous number
	 *         ended with (including wrapping around)</br> Null if no cycle is
	 *         found
	 */
	private static int[] find(int i, int depth, int firstNum,
			int[][] useableSets, int[] cycle) {
		depth++;
		if (depth == 6) {
			final ArrayList<Integer> ks = polyNums.get(5).get(i % 100);
			if (ks != null) {
				for (final int k : ks) {
					if (k == firstNum) {
						return cycle;
					}
				}
			}
		} else {
			for (int j = 0; j < useableSets[0].length; j++) {
				if (useableSets[1][j] == 0) {
					useableSets[1][j]++;
					final ArrayList<Integer> ks = polyNums.get(useableSets[0][j])
							.get(i % 100);
					if (ks != null) {
						for (final int k : ks) {
							int[] candidate = cycle.clone();
							candidate[depth] = k;
							candidate = find(k, depth, firstNum,
									useableSets.clone(), candidate);
							if (candidate != null) {
								return candidate;
							}
						}
					}
					useableSets[1][j]--;
				}
			}
		}
		return null;
	}

	/**
	 * Silly Iterative method
	 * 
	 * @return
	 */
	private static int[][] findCycle() {
		for (ArrayList<Integer> ar : polyNums.get(5).values()) {
			for (Integer i : ar) {
				for (int jj = 0; jj < 5; jj++) {
					ArrayList<Integer> js = polyNums.get(jj).get(i % 100);
					if (js != null) {
						for (Integer j : js) {
							for (int kk = (jj + 1) % 5; kk != jj; kk = (kk + 1) % 5) {
								ArrayList<Integer> ks = polyNums.get(kk).get(
										j % 100);
								if (ks != null) {
									for (Integer k : ks) {
										for (int ll = (kk + 1) % 5; ll != kk; ll = (ll + 1) % 5) {
											ArrayList<Integer> ls = polyNums
													.get(ll).get(k % 100);
											if (ls != null) {
												for (Integer l : ls) {
													for (int mm = (ll + 1) % 5; mm != ll; mm = (mm + 1) % 5) {
														ArrayList<Integer> ms = polyNums
																.get(mm)
																.get(l % 100);
														if (ms != null) {
															for (Integer m : ms) {
																for (int nn = (mm + 1) % 5; nn != mm; nn = (nn + 1) % 5) {
																	ArrayList<Integer> ns = polyNums
																			.get(nn)
																			.get(m % 100);
																	if (ns != null) {
																		for (Integer n : ns) {
																			// System.out.println(n);
																			ArrayList<Integer> os = polyNums
																					.get(5)
																					.get(n % 100);
																			if (os != null) {
																				for (Integer o : os) {
																					if (i == o) {
																						int[] test = {
																								1,
																								1,
																								1,
																								1,
																								1 };
																						int check = 0;
																						check |= --test[jj];
																						check |= --test[kk];
																						check |= --test[ll];
																						check |= --test[mm];
																						check |= --test[nn];
																						if (check == 0)
																							// for(int[]
																							// x
																							// :
																							// new
																							// int[][]{{i,j,k,l,m,n},
																							// {jj,kk,ll,mm,nn},
																							// }){
																							// System.out.println(Arrays.toString(x));
																							// }
																							return new int[][] {
																									{
																											i,
																											j,
																											k,
																											l,
																											m,
																											n },
																									{
																											jj,
																											kk,
																											ll,
																											mm,
																											nn }, };
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return new int[][] {};
	}
}
