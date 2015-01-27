package problems.cracking_code.ch3;

import java.util.Arrays;
import java.util.Random;

import dataStructures.Stack;

public class StackSort {
	
	public static void main(String[] args){
		Integer[] nums = new Integer[500000];
		Random random = new Random();
		for(int i=0; i<nums.length; i++){
			nums[i] = random.nextInt(50);
			
		}
		Stack<Integer> stack1 = new Stack<Integer>(nums);
		Stack<Integer> stack2 = new Stack<Integer>(nums);
		long time = System.nanoTime();
		Arrays.sort(nums);
		System.out.println(System.nanoTime()-time);
		time = System.nanoTime();
//		System.out.println(stack1);
		StackSort.sortStack(stack1);
		System.out.println(System.nanoTime()-time);
//		System.out.println(stack1);
		time = System.nanoTime();
		StackSort.sortStack2Stacks(stack2);
		System.out.println(System.nanoTime()-time);
//		System.out.println(stack2);
	}
	
	public static Stack<Integer> sortStack(Stack<Integer> s){
		if(s.getDepth() < 2) return s;
		Stack<Integer> buff1 = new Stack<Integer>();
		Stack<Integer> buff2 = new Stack<Integer>();
		buff1.push(s.pop());
		while(!s.isEmpty()){
//			System.out.println(s + "\t" + buff2 + "\t" + buff1);
			final int sTop = s.peek();
			final boolean buff1Empty = buff1.isEmpty();
			final boolean buff2Empty = buff2.isEmpty();
			if(buff2Empty && buff1.peek() <= sTop){
				buff1.push(s.pop());
			}else if(buff2Empty){
				while(!buff1.isEmpty() && buff1.peek() >= sTop){
					buff2.push(buff1.pop());
				}
			}else if(buff2.peek() > sTop && (buff1Empty || buff1.peek() <= sTop)){
				buff2.push(s.pop());
			}else if(buff1Empty || buff1.peek() <= sTop){
				while(!buff2.isEmpty() && buff2.peek() <= sTop){
					buff1.push(buff2.pop());
				}
				buff2.push(s.pop());
			}else{
				while(!buff1.isEmpty() && buff1.peek() >= sTop){
					buff2.push(buff1.pop());
				}
				buff1.push(s.pop());
			}
		}
		while(! buff2.isEmpty()){
			buff1.push(buff2.pop());
		}
		while(! buff1.isEmpty()){
			s.push(buff1.pop());
		}
		return s;
	}
	
	public static Stack<Integer> sortStack2Stacks(Stack<Integer> s){
		if(s.getDepth() < 2){
			return s;
		}
		int temp;
		Stack<Integer> buff = new Stack<Integer>();
		while(!s.isEmpty()){
			if(buff.isEmpty() || buff.peek() <= s.peek()){
				buff.push(s.pop());
			}else{
				temp = s.pop();
				while(! buff.isEmpty() && buff.peek() > temp){
					s.push(buff.pop());
				}
				buff.push(temp);
			}
		}
		while(! buff.isEmpty()){
			s.push(buff.pop());
		}
		return s;
	}
}
