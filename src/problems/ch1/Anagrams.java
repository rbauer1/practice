package problems.ch1;

import java.util.Arrays;

public class Anagrams {
	
	public static void main(String[] args){
		System.out.println(areAnagrams("WilliamShakespeare", "Iamaweakishspeller"));
		System.out.println(areAnagramsSort("WilliamShakespeare", "Iamaweakishspeller"));
	}
	
	private static boolean areAnagramsSort(String s1, String s2){
		if(s1 == null || s2 == null || s1.length() != s2.length()){
			return false;
		}
		s1 = s1.toLowerCase();
		s2 = s2.toLowerCase();
		char[] one = s1.toCharArray();
		Arrays.sort(one);
		char[] two = s2.toCharArray();
		Arrays.sort(two);
		for(int i=0; i<one.length; i++){
			if(one[i] != two[i]) return false;
		}
		return true;
	}
	
	private static boolean areAnagrams(String s1, String s2){
		if(s1 == null || s2 == null || s1.length() != s2.length()){
			return false;
		}
		s1 = s1.toLowerCase();
		s2 = s2.toLowerCase();
		int[] chars = new int[256]; 
		for(int i=0; i<s1.length(); i++){
			chars[s1.charAt(i)+0]++;
			chars[s2.charAt(i)+0]--;
		}
		int check = 0;
		for(int i : chars){
			check |= i;
		}
		if(check != 0){
			return false;
		}
		return true;
	}
}
