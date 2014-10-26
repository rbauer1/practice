package dataStructures;

public class Stack<T> {
	private Node<T> top;
	int depth;
	
	
	public Stack(){
		depth = 0;
	}
	
	public Stack(T data){
		top = new Node<T>(data);
		depth = 1;
	}
	
	public Stack(T[] data){
		depth = 0;
		if(data.length !=0){
			top = new Node<T>(data[0]);
			depth += data.length;
			for(int i=1; i<data.length; i++){
				final Node<T> next = new Node<T>(data[i]);
				next.next = top;
				top = next;
			}
		}
		
	}
	
	public int getDepth(){
		return depth;
	}
	
	public boolean isEmpty(){
		return depth == 0;
	}
	
	public void push(T data){
		if(depth == 0){
			top = new Node<T>(data);
		}else{
			Node<T> newNode = new Node<T>(data);
			newNode.next = top;
			top = newNode;
		}
		depth++;
	}
	
	public T pop(){
		if(depth == 0){
			return null;
		}
		T data = top.data;
		top = top.next;
		depth--;
		return data;
	}
	
	public T peek(){
		if(depth == 0){
			return null;
		}
		return top.data;
	}
	
	public String toString(){
		String s = depth + " [";
		Node<T> current = top;
		for(int i = 0; i < depth; i++){
			s += current.data + ", ";
			current = current.next;
		}
		return s + "]";
	}
	
	private static class Node<T>{
		T data;
		Node<T> next;
		
		public Node(T data){
			this.data = data;
		}
	}
}
