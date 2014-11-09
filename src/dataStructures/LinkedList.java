package dataStructures;

public class LinkedList<T> {
	private static class Node<T>{
		Node<T> next;
		T data;
		
		public Node(T data){
			this.data = data;
		}
	}
	private Node<T> head;
	private Node<T> tail;
	
	private int length;
	
	public LinkedList(){
		length = 0;
	}
	
	public LinkedList(Node<T> node){
		head = node;
		tail = head;
		head.next = tail;
		length = 1;
	}
	
	public LinkedList(T data){
		this(new Node<T>(data));
	}
	
	public void append(LinkedList<T> ll){
		if(length !=0){
			LinkedList<T> copy = ll.copy();
			tail.next = copy.head;
			tail = copy.tail;
		}else{
			head = ll.head;
			tail = ll.tail;
		}
		length += ll.length;
	}
	
	public void append(Node<T> node){
		if(length != 0){
			tail.next = node;
			tail = node;
		}else{
			head = node;
			tail = head;
			head.next = tail;
		}
		length++;
	}
	
	public void append(T data){
		append(new Node<T>(data));
	}
	
	public LinkedList<T> copy(){
		LinkedList<T> newLL = new LinkedList<T>();
		if(length != 0){
			Node<T> curPos = head;
			for(int i=0; i<length; i++){
				newLL.append(new Node<T>(curPos.data));
				curPos = curPos.next;
			}			
		}
		return newLL; 
	}
	
	public int firstOccurrence(T data){
		if(length != 0){
			Node<T> currentNode = head;
			for(int i=0; i<length; i++){
				if(currentNode.data.equals(data)){
					return i;
				}
				currentNode = currentNode.next;
			}
		}
		return -1;
	}
	
	public int getLength(){
		return length;
	}
	
	public T getNth(int n){
		if(length == 0 || n > length){
			return null;
		}
		Node<T> current = head;
		for(; n-1 > 0; n--){
			current = current.next;
		}
		return current.data;
	}
	
	public T getNthToLast(int n){
		if(length == 0 || n > length){
			return null;
		}
		if(n == length){
			return tail.data;
		}
		
		Node<T> n1 = head, n2 = head;
		for(; n-1 > 0; n--){
			n2 = n2.next;
		}
		while(n2.next != null){
			n2 = n2.next;
			n1 = n1.next;
		}
		return n1.data;
	}
	
	public boolean insertAt(Node<T> node, int index){
		if(index > length || index < 0){
			System.err.println("Invalid index");
			return false;
		}
		Node<T> temp = head;
		if(index != 0 && index != length){
			for(int i = 0; i < index - 1; i++){
				temp = temp.next;
			}
			node.next = temp.next;
			temp.next = node;
		}else{
			if(index == 0){
				node.next = head;
				head = node;
			}else{
				tail.next = node;
				tail = node;
			}
		}
		length++;
		return true;
	}
	
	public boolean insertAt(T data, int index){
		return insertAt(new Node<T>(data), index);
	}
	
	public boolean isEmpty(){
		return length < 1;
	}
	
	public T peek(){
		if(length > 0){
			return head.data;
		}
		return null;
	}
	
	public T pop(){
		if(length > 0){
			T data = head.data;
			head = head.next;
			length--;
			return data;
		}
		return null;
	}
	
	public void push(Node<T> node){
		if(length != 0){
			node.next = head;
			head = node;
		}else{
			head = node;
			tail = head;
			head.next = tail;
		}
		length++;
	}
	
	public void push(T data){
		push(new Node<T>(data));
	}
	
	public T removeAt(int index){
		if(length != 0){
			if(index == 0){
				T data = head.data;
				head = head.next;
				length--;
				return data;
			}
			if(index < length && index > 0){
				Node<T> currentNode = head;
				for(int i=0; i < index - 1; i++){
					currentNode = currentNode.next;
				}
				T data = currentNode.next.data;
				currentNode.next = currentNode.next.next;
				length--;
				return data;
			}
			System.err.println("INVALID INDEX");
			return null;
		}
		return null;
	}
	
	public T removeLast(){
		if(length > 0){
			T data = tail.data;
			Node<T> newTail = head;
			for(int i=0; i < length - 2; i++){
				newTail = newTail.next;
			}
			tail = newTail;
			length--;
			return data;
		}
		return null;
	}
	
	public String toString(){
		String s = length + " [ ";
		Node<T> curNode = head;
		for(int i=0; i < length; i++){
			s += curNode.data + ", ";
			curNode = curNode.next;
		}
		s += "]";
		return s;
	}
}
