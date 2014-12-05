package problems.misc.other;

import java.util.Arrays;
import java.util.Random;

public class LargestSubArray {
 public static void main(String[] args){
	 Random gen = new Random();
	 int[] ar = new int[gen.nextInt(30)];
	 for(int i=0; i<ar.length; i++){
		 ar[i] = gen.nextInt(30)-gen.nextInt(40);
	 }
	 System.out.println(Arrays.toString(ar));
	 System.out.println(Arrays.toString(largestSubArray(ar)));
 }
 
 public static int[] largestSubArray(int[] a){
	 if(a.length<2){
		 return a;
	 }
	 int currentMax = a[0];
	 int max = a[0];
	 int lastIndex = 0;
	 for(int i=1; i<a.length; i++){
		 currentMax = max(a[i], currentMax+a[i]);
		 max = max(currentMax, max);
		 if(max == currentMax){
			 lastIndex = i;
		 }
	 }
	 int startIndex = lastIndex+1;
	 System.out.println(max);
	 while(max > 0){
		 max -= a[--startIndex];
	 }
	 if(startIndex == 0 && lastIndex == a.length-1){
		 return a;
	 }
	 final int[] maxSubArray = new int[lastIndex-startIndex+1];
	 for(int i=0; i<maxSubArray.length; i++){
		 maxSubArray[i] = a[startIndex+i];
	 }
	 return maxSubArray;
	 
 }
 
 private static int max(int a, int b){
	 return (a >= b) ? a : b;
 }
}
