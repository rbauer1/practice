package problems.misc;

import java.util.Arrays;

public class ChangeForDollar {
	private int dollar;
	private int[] coins;
	private int[][] table;
	
	public static void main(String[] args){
		int[] c = {1,2,5,10,20,50,100,200};
		ChangeForDollar cdf = new ChangeForDollar(200, c);
		cdf.waysToMakeX();
		System.out.println(cdf.table[cdf.dollar][cdf.coins.length]);
	}
	
	public ChangeForDollar(int dollar, int[] coins){
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
