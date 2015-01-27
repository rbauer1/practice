package problems.euler.p76;

import java.util.Arrays;
/**
 * Another variation of the coin sum counting problem
 */
public class CountingSummations {
	private int dollar;
	private int[] coins;
	private int[][] table;
	
	public static void main(String[] args){
		int[] c = new int[99];
		for(int i=1; i<100; i++){
			c[i-1] = i;
		}
		CountingSummations cdf = new CountingSummations(100, c);
		cdf.waysToMakeX();
		System.out.println(cdf.table[cdf.dollar][cdf.coins.length]);
	}
	
	public CountingSummations(int dollar, int[] coins){
		Arrays.sort(coins);
		this.dollar = dollar;
		this.table = new int[dollar + 1][coins.length + 1];
		this.coins = coins;
	}
	
	public void waysToMakeX(){
		for(int i = 0; i <table.length; i++){
			for(int j = 0; j<coins.length+1; j++){
				if(i == 0){
					table[i][j] = 1;
				}else if(j == 0){
					table[i][j] = 0;
				}else if(coins[j-1] > i){
					table[i][j] = table[i][j-1];
				}else{
					table[i][j] = table[i-coins[j-1]][j] + table[i][j-1];
				}
			}
		}
	}
	
	
}
