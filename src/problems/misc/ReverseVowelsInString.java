package problems.misc;

import java.util.HashSet;

public class ReverseVowelsInString {
	private static HashSet<Character> vowels;
	
	public static void main(String[] args){
		System.out.println(reverseVowels("toasty tests"));
	}
	
	static {
		vowels = new HashSet<Character>(5);
		vowels.add('a');
		vowels.add('e');
		vowels.add('i');
		vowels.add('o');
		vowels.add('u');
	}
	
	
	public static String reverseVowels(String s){
		if(s == null || s.length() < 2){
			return s;
		}
		final char[] buff = s.toCharArray();
		int p1 = 0;
		int p2 = s.length()-1;
		while(p1 < p2){
			final boolean p1Vowel = vowels.contains(buff[p1]);
			final boolean p2Vowel = vowels.contains(buff[p2]);
			if(p1Vowel && p2Vowel){
				final char temp = buff[p1];
				buff[p1] = buff[p2];
				buff[p2] = temp;
				p1++;
				p2--;
			}else{ 
				if(! p1Vowel){
					p1++;
				}
				if(! p2Vowel){
					p2--;
				}
			}
		}
		return String.valueOf(buff);
	}
}
