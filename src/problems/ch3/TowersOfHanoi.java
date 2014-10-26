package problems.ch3;

import dataStructures.Stack;

public class TowersOfHanoi {
	Stack<Integer> start;
	Stack<Integer> mid;
	Stack<Integer> end;
	int numRings;
	
	public static void main(String[] args){
		TowersOfHanoi towers = new TowersOfHanoi(4);
		System.out.println(towers+"\n");
//		towers.moveTwo(towers.end, towers.start, towers.mid);
		towers.solve(towers.numRings, towers.end, towers.start, towers.mid);
		System.out.println(towers);
		
	}
	
	public TowersOfHanoi(int numRings){
		start = new Stack<Integer>();
		mid = new Stack<Integer>();
		end = new Stack<Integer>();
		this.numRings = numRings;
		for(int i=numRings; i>0; i--){
			start.push(i);
		}
	}
	
	public void solve(int platesToMove, Stack<Integer> to, Stack<Integer> from, Stack<Integer> other){
		if(platesToMove == 0){
			return;
		}
		solve(platesToMove - 1, other, from, to);
		to.push(from.pop());
		System.out.println(this + "\n");
		solve(platesToMove - 1, to, other, from);
	}
	
	public String toString(){
		return start.toString() + "\n" + mid.toString() + "\n" + end.toString();
	}
}
