package problems.ch3;

import dataStructures.Stack;

public class QueueFromStacks {
/**
 * have two stacks -- each spits out most recent element
 * 
 * want a queue -- this will spit out least recent element
 * 
 * 
 * case 1: put something in, pop it back out
 * 
 * case 2: put in two things, pop first, put it in other stack, now push new thing, then pop other stack and put it on
 * 
 * case 3: 
 */
	
	private Stack[] stacks = {new Stack(), new Stack()};
	
	public QueueFromStacks(){
		 
	}
	
	public void push(int i){
		stacks[1].push(i);
	}
	
	public int pop(){
		if(stacks[0].isEmpty()){
			if(stacks[1].isEmpty()){
				System.out.println("ERROR: STACKS EMPTY");
				return Integer.MAX_VALUE;
			}
			while(!stacks[1].isEmpty()){
				stacks[0].push(stacks[1].pop());
			}
		}
		return (int)stacks[0].pop();
	}
	
	public int peek(){
		return (int)stacks[0].peek();
	}
	
	public String toString(){
		return stacks[0].toString() + "\t" + stacks[1].toString();
	}
	
}
