package dataStructures;

import java.util.Random;

import problems.ch3.QueueFromStacks;
@SuppressWarnings("unused")
public class DataStructuresMain {
	public static void main(String[] args){
//		testLinkedList();
//		testStack();
//		testQueueFromStacks();
//		testBinarySearchTree();
//		testBSTFromArray();
//		testDiGraph();
		testRB_BST();
	}
	
	private static void testRB_BST(){
		RB_BST<Integer> rbBST = new RB_BST<Integer>(0);
		Random gen = new Random();
		int[] test = {28, 68,77,36,75,82};
//		for(int i=1; i<test.length; i++){
		for(int i=1; i<100; i++){
			rbBST.insert(gen.nextInt(100));
//			rbBST.insert(i);
//			rbBST.insert(test[i]);
//			rbBST.traverseInOrder();
//			System.out.println("-----");
		}
		rbBST.traverseBFS();
		System.out.println(rbBST.isBalanced());
	}
	
	private static void testDiGraph(){
		DiGraph<Character> graph = new DiGraph<Character>('s');
		graph.addEdge('s','a', 5);
		graph.addEdge('a','s', 0);
		graph.addEdge('s','b', 2);
		graph.addEdge('b','s', 0);
		graph.addEdge('a','b', 2);
		graph.addEdge('b','a', 2);
		graph.addEdge('a','c', 3);
		graph.addEdge('c','a', 0);
		graph.addEdge('b','d', 4);
		graph.addEdge('d','b', 0);
		graph.addEdge('d','c', 0);
		graph.addEdge('c','d', 1);
		graph.addEdge('d','t', 5);
		graph.addEdge('t','d', 0);
		graph.addEdge('c','t', 2);
		graph.addEdge('t','c', 0);
		graph.setSink('t');
		graph.setSource('s');
		System.out.println(graph);
		System.out.println(graph.findPath());
		System.out.println(graph.findMaxFlow());
		System.out.println(graph);
		System.out.println(graph.topoSortDFS());
		
		System.out.println("-------------");
		
		DiGraph<Character> unfortunateGraph = new DiGraph<Character>('s');
		unfortunateGraph.addEdge('s','a',1000);
		unfortunateGraph.addEdge('a','s',0);
		unfortunateGraph.addEdge('s','b',1000);
		unfortunateGraph.addEdge('b','s',0);
		unfortunateGraph.addEdge('a','b',1);
		unfortunateGraph.addEdge('b','a',0);
		unfortunateGraph.addEdge('a','t',1000);
		unfortunateGraph.addEdge('t','a',0);
		unfortunateGraph.addEdge('b','t',1000);
		unfortunateGraph.addEdge('t','b',0);
		unfortunateGraph.setSink('t');
		unfortunateGraph.setSource('s');
		System.out.println(unfortunateGraph);
		System.out.println(unfortunateGraph.findPath());
		System.out.println(unfortunateGraph.findMaxFlow());
		System.out.println(unfortunateGraph);
		
	}
	
	private static void testBinarySearchTree(){
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>(10);
//		bst.traverseInOrder();
		System.out.println(bst.isBalanced());
		bst.insert(5);
		System.out.println(bst.isBalanced());
		bst.insert(15);
//		System.out.println(bst.data + "\n" + )
		bst.traverseInOrder();
		System.out.println();
		System.out.println(bst.isBalanced());
		bst.insert(1);
		bst.traverseInOrder();
		System.out.println();
		System.out.println(bst.isBalanced());
		bst.insert(8);
		bst.traverseInOrder();
		System.out.println();
		System.out.println(bst.isBalanced());
		bst.insert(9);
		bst.traverseInOrder();
		System.out.println();
		System.out.println(bst.isBalanced());
		bst.insert(11);
		bst.traverseInOrder();
		System.out.println();
		System.out.println(bst.isBalanced());
		bst.insert(16);
		bst.traverseInOrder();
		System.out.println();
		System.out.println(bst.isBalanced());
		bst.insert(14);
		bst.traverseInOrder();
		System.out.println();
		System.out.println(bst.isBalanced());
		bst.insert(6);
		bst.traverseInOrder();
		System.out.println();
		System.out.println(bst.isBalanced());
		bst.insert(7);
		bst.traverseInOrder();
		System.out.println();
		System.out.println(bst.isBalanced());
		bst.insert(18);
		bst.traverseInOrder();
		System.out.println();
		System.out.println(bst.isBalanced());
		bst.insert(17);
		bst.traverseInOrder();
		System.out.println();
		System.out.println(bst.isBalanced());
		bst.insert(19);
		bst.traverseInOrder();
		System.out.println();
		System.out.println(bst.isBalanced());
		bst.insert(4);
		bst.traverseInOrder();
		System.out.println();
		System.out.println(bst.isBalanced());
		bst.insert(20);
		bst.traverseInOrder();
		System.out.println();
		System.out.println(bst.isBalanced());
		bst.traversePreOrder();
		System.out.println();
		bst.traverseInOrder();
		System.out.println();
		bst.traversePostOrder();
		System.out.println("\n" + bst.maxDepth());
		System.out.println(bst.isBalanced());
		bst.remove(10);
		bst.traverseInOrder();
		System.out.println();
		bst.remove(9);
		bst.traverseInOrder();
		System.out.println();
		bst.remove(1);
		bst.traverseInOrder();
		System.out.println();
//		for(int i=0; i<21; i++){
//			System.out.println(i + "\t" + bst.findInOrderSuccessor(bst.find(i))  + "\t" + bst.findInOrderPredecessor(bst.find(i)));
//		}
	}
	
	private static void testBSTFromArray(){
		Integer[] ints = {1,3,6,9,12,14,17,21,26};
//		Integer[] ints = {1,4,6,7,8,12,14,18};
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>(ints);
		System.out.println(bst.isBalanced());
		bst.traverseInOrder();
		System.out.println();
	}
	
	private static void testQueueFromStacks(){
		QueueFromStacks q = new QueueFromStacks();
		System.out.println(" \t" + q);
		q.push(1);
		System.out.println(" \t" + q);
		q.push(2);
		System.out.println(" \t" + q);
		q.push(3);
		System.out.println(" \t" + q);
		System.out.print(q.pop() + "\t");
		System.out.println(q);
		q.push(4);
		System.out.println(" \t" + q);
		System.out.print(q.pop() + "\t");
		System.out.println(q);
		System.out.print(q.pop() + "\t");
		System.out.println(q);
		System.out.print(q.pop() + "\t");
		System.out.println(q);
		System.out.print(q.pop() + "\t");
		System.out.println(q);
	}
	
	private static void testStack(){
		Stack<Integer> stack = new Stack<Integer>();
		System.out.println(stack);
		stack.push(1);
		System.out.println(stack);
		stack.push(2);
		System.out.println(stack.pop());
		System.out.println(stack);
		stack.push(2);
		System.out.println(stack);
		stack.push(2);
		System.out.println(stack);
		stack.push(9);
		System.out.println(stack.peek());
		System.out.println(stack);
	}
	
	private static void testLinkedList(){
		LinkedList<Integer> ll = new LinkedList<Integer>();
		LinkedList<Integer> ll2 = new LinkedList<Integer>();
		ll2.append(99);
		ll2.append(100);
		ll2.push(98);
		ll.append(ll2);
		System.out.println(ll.copy());
		ll.append(ll2);
		System.out.println(ll);
		ll.append(1);
		System.out.println(ll);
		System.out.println(ll.getNth(4));
		System.out.println(ll.getNthToLast(4));
		ll.push(2);
		System.out.println(ll);
		ll.append(3);
		System.out.println(ll);
		ll.insertAt(4, 0);
		System.out.println(ll);
		ll.insertAt(5, 1);
		System.out.println(ll);
		ll.insertAt(6, 12);
		System.out.println(ll);
		ll.insertAt(7, -1);
		System.out.println(ll);
		System.out.println(ll.firstOccurrence(3) + "\t" + ll);
		System.out.println(ll.firstOccurrence(22) + "\t" + ll);
		ll.insertAt(8, 5);
		System.out.println(ll);
		System.out.println(ll.pop() + "\t" + ll);
		System.out.println(ll.removeLast() + "\t" + ll + "-");
		System.out.println(ll.removeLast() + "\t" + ll + "+");
		System.out.println(ll.removeAt(1) + "\t" + ll + "/");
		System.out.println(ll.removeAt(-1) + "\t" + ll + "_");
		System.out.println(ll.removeAt(3) + "\t" + ll + "{");
		System.out.println(ll.removeAt(0) + "\t" + ll + "}");
		System.out.println(ll.removeAt(0) + "\t" + ll + "|");
		System.out.println(ll.removeAt(0) + "\t" + ll + ">");
		System.out.println(ll.removeAt(3) + "\t" + ll + "<");
	}
}
