package problems.euler.p80;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class SqRootDigitalExpansion {
	public static void main(String[] args){
		BigDecimal bd = new BigDecimal(2);
//		System.out.println(sqrt(bd, 120));
		Set<Integer> s = new HashSet<Integer>();
		s.add(4);
		s.add(9);
		s.add(16);
		s.add(25);
		s.add(36);
		s.add(49);
		s.add(64);
		s.add(81);
		int sum = 0;
		for(int i = 2; i<100; i++){
			if(!s.contains(i)){
				for(int j=0; j<101; j++){
					final String num = sqrt(bd,200).toString();
					char next = num.charAt(j);
					if(next != '.'){
						sum += num.charAt(j)-48;
					}
				}
			}
			bd = bd.add(BigDecimal.ONE);
		}
		System.out.println(sum);
		
	}
	
	/**
	 * Slightly modified from
	 * http://stackoverflow.com/questions/13649703/square-root-of-bigdecimal-in-java
	 * @param A
	 * @param SCALE
	 * @return
	 */
	public static BigDecimal sqrt(BigDecimal A, final int SCALE) {
		final BigDecimal TWO = BigDecimal.ONE.add(BigDecimal.ONE);
	    BigDecimal x0 = BigDecimal.ZERO;
	    BigDecimal x1 = new BigDecimal(Math.sqrt(A.doubleValue()));
	    while (!x0.equals(x1)) {
	        x0 = x1;
	        x1 = A.divide(x0, SCALE, BigDecimal.ROUND_HALF_UP);
	        x1 = x1.add(x0);
	        x1 = x1.divide(TWO, SCALE, BigDecimal.ROUND_HALF_UP);

	    }
	    return x1;
	}
}
