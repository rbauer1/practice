package problems.misc.other;

import java.util.Arrays;

public class SimpleCompression {
	public static void main(String[] args){
		String t0 = compress("");
		String t1 = compress("1123");
		String t2 = compress("te3st");
		String t3 = compress("teesttttyyyy");
		String t4 = compress("eeeee");
		String t5 = compress("eeeeet");
		
		System.out.println(t0 + "\t" + decompress(t0));
		System.out.println(t1 + "\t" + decompress(t1));
		System.out.println(t2 + "\t" + decompress(t2));
		System.out.println(t3 + "\t" + decompress(t3));
		System.out.println(t4 + "\t" + decompress(t4));
		System.out.println(t5 + "\t" + decompress(t5));
		System.out.println(Arrays.toString("f".getBytes()));
	}
	
	/**
	 * Note: this will not work on strings containing integers
	 * @param s
	 * @return
	 */
	public static String compress(String s){
		if(s.length() < 2){
			return s;
		}
		StringBuilder sb = new StringBuilder();
		int count = 1;
		char lastChar = s.charAt(0);
		for(int i=1; i<s.length(); i++){
			final char thisChar = s.charAt(i); 
			if(thisChar == lastChar){
				count++;
			}else{
				sb.append(lastChar);
				if(count > 1){
					sb.append(count);
					count = 1;
				}
				lastChar = thisChar;
			}
		}
		sb.append(lastChar);
		if(count > 1){
			sb.append(count);
		}
		return sb.toString();
	}
	
	public static String decompress(String s){
		if(s.length() < 2){
			return s;
		}
		StringBuilder sb = new StringBuilder();
		char lastChar = s.charAt(0);
		for(int i=1; i<s.length(); i++){
			final char thisChar = s.charAt(i);
			if(Character.isDigit(thisChar)){
				int num = thisChar-48;
				while(++i < s.length() && Character.isDigit(s.charAt(i))){
					num = num*10 + (s.charAt(i)-48);
				}
				for(int j = 1; j < num; j++){
					sb.append(lastChar);
				}
				i--;
			}else{
				sb.append(lastChar);
				lastChar = thisChar;
			}
		}
		sb.append(lastChar);
		return sb.toString();
	}
}
