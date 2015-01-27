package problems.cracking_code.ch5;

public class DecimalStringToBinary {
	public static void main(String[] args){
		System.out.println(aToI("1255743461"));
	}
	
//	public static String aToB(String s){
//		String[] parts = s.split(".");
//		int r = aToI(parts[1]);
//		int l = aToI(parts[0]);
//		parts[0] = "";
//		parts[1] = "";
//		
//		
//		
//	}
	
	private static int aToI(String s){
		if(s == null) throw new NullPointerException();
		int adj = s.charAt(0)=='-' ? 1 : 0;
		int sum = 0;
		int pow = 1;
		for(int i=s.length()-1; i>-1 + adj; i--){
			sum += (((int)(s.charAt(i)))-48) * pow;
			pow *= 10;
		}
		return sum*(-adj);
	}
}
