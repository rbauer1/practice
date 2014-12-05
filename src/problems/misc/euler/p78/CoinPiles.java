package problems.misc.euler.p78;

import java.util.Arrays;

public class CoinPiles {
	private long dollar;
	private long[] coins;
	private long[][] table;
	
	public static void main(String[] args){
//		int[] c = {1,2,5,10,20,50,100,200};
		for(long x = 1; x<101; x++){
			long[] c = new long[(int)x];
			for(int i=1; i<=x; i++){
				c[i-1] = i;
			}
			CoinPiles cdf = new CoinPiles(x, c);
			cdf.waysToMakeX();
			long ways = cdf.table[(int) cdf.dollar][cdf.coins.length];
//			System.out.println(ways);
//			if(ways%1000000==0){
				System.out.println(x + " "+ways);
//			}
		}
	}
	
	public CoinPiles(long dollar, long[] coins){
		Arrays.sort(coins);
		this.dollar = dollar;
		this.table = new long[(int)(dollar + 1)][coins.length + 1];
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
					table[i][j] = table[(int)(i-coins[j-1])][j] + table[i][j-1];
				}
			}
		}
	}
	
	
}
