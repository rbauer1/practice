package dataStructures;

public class BinarySearchTree<T extends Comparable<T>>{
	BinarySearchTree<T> left;
	BinarySearchTree<T> right;
	BinarySearchTree<T> parent;
	T key;
	
	public BinarySearchTree(T key){
		this(key, null);
	}
	
	public BinarySearchTree(T key, BinarySearchTree<T> parent){
		this.key = key;
		this.parent = parent;
	}
	
	public BinarySearchTree(T[] keys){
		if(keys.length > 0){
			parent = null;
			if(keys.length > 1){
				final int mid = (keys.length-1)/2;
				key = keys[mid];
				left = balancedTreeFromArray(keys, 0, mid-1);
				left.parent = this;
				right = balancedTreeFromArray(keys, mid+1, keys.length-1);
				right.parent = this;
			}
		}
	}
	
	private BinarySearchTree<T> balancedTreeFromArray(T[] keys, int st, int end){
		final int mid = (end+st)/2;
		final BinarySearchTree<T> tree = new BinarySearchTree<T>(keys[mid]);
		if(mid-1 >= st){
			tree.left = balancedTreeFromArray(keys, st, mid-1);
			tree.left.parent = tree;
		}
		if(end >= mid+1){
			tree.right = balancedTreeFromArray(keys, mid+1, end);
			tree.right.parent = tree;
		}
		return tree;
	}
	
	public boolean isBalanced(){
		if(this.left == null && this.right == null) return true;
		return checkBalanced(this);
	}
	
	private boolean checkBalanced(BinarySearchTree<T> tree){
		return (tree.maxDepth() - tree.minDepth() <= 1);
	}
	
	public boolean contains(T value){
		return find(value, this) != null;
	}
	
	public BinarySearchTree<T> find(T value){
		return find(value, this);
	}
	
	private BinarySearchTree<T> find(T value, BinarySearchTree<T> child){
		int compareVal = child.key.compareTo(value);
		switch(compareVal){
		case 0:
			return child;
		case 1:
			return (child.left == null) ? null : find(value, child.left);
		case -1:
			return (child.right == null) ? null : find(value, child.right);
		default:
			System.err.println("This point in the contains(...) method should never be reached");
			return null;
		}
	}
	
	public BinarySearchTree<T> findInOrderPredecessor(BinarySearchTree<T> tree){
		if(tree == null){
			return null;
		}
		if(tree.left == null){
			BinarySearchTree<T> p = tree.parent;
			while(p != null && p.key.compareTo(tree.key) > -1){
				p = p.parent;
			}
			if(p != null) return p;
			return null;
		}
		return findMax(tree.left);
	}
	
	public BinarySearchTree<T> findInOrderSuccessor(BinarySearchTree<T> tree){
		if(tree == null){
			return null;
		}
		if(tree.right == null){
			BinarySearchTree<T> p = tree.parent;
			while(p != null && p.key.compareTo(tree.key) < 1){
				p = p.parent;
			}
			if(p != null) return p;
			return null;
		}
		return findMin(tree.right);
	}
	
	public BinarySearchTree<T> findMax(){
		return findMax(this);
	}
	
	private BinarySearchTree<T> findMax(BinarySearchTree<T> tree){
		if(tree.right == null){
			return tree;
		}
		return findMax(tree.right);
	}
	
	public BinarySearchTree<T> findMin(){
		return findMin(this);
	}
	
	private BinarySearchTree<T> findMin(BinarySearchTree<T> tree){
		if(tree.left == null){
			return tree;
		}
		return findMin(tree.left);
	}
	
	public T getInOrderPredecessor(BinarySearchTree<T> tree){
		return findInOrderPredecessor(tree).key;
	}
	
	public T getInOrderSuccessor(BinarySearchTree<T> tree){
		return findInOrderSuccessor(tree).key;
	}
	
	public T getMax(){
		return findMax(this).key;
	}
	
	public T getMin(){
		return findMin(this).key;
	}

	public void insert(T key){
		insert(key, this);
	}
	
	private void insert(T key, BinarySearchTree<T> tree){
		if(tree.key.compareTo(key) >= 0){
			if(tree.left == null){
				tree.left = new BinarySearchTree<T>(key, this);
				tree.left.parent = tree;
			}else{
				insert(key, tree.left);
			}
		}else{
			if(tree.right == null){
				tree.right = new BinarySearchTree<T>(key, this);
				tree.right.parent = tree;
			}else{
				insert(key,tree.right);
			}
		}
	}
	
	public int maxDepth(){
		return maxDepth(0, this);
	}
	
	private int maxDepth(int maxDepth, BinarySearchTree<T> tree){
		if(tree == null){
			return maxDepth;
		}
		maxDepth++;
		final int leftMax = maxDepth(maxDepth, tree.left);
		final int rightMax = maxDepth(maxDepth, tree.right);
		return leftMax >= rightMax ? leftMax : rightMax; 
	}
	
	public int minDepth(){
		return minDepth(0, this);
	}
	
	
	private int minDepth(int minDepth, BinarySearchTree<T> tree){
		if(tree == null){
			return minDepth;
		}
		minDepth++;
		final int leftMin = minDepth(minDepth, tree.left);
		final int rightMin = minDepth(minDepth, tree.right);
		return leftMin <= rightMin ? leftMin : rightMin; 
	}
	
	
	/**
	 * 
	 * @param value
	 * @return true if successfully removed
	 */
	public boolean remove(T value){
		return remove(value, this);
	}
	
	private boolean remove(T value, BinarySearchTree<T> tree){
		BinarySearchTree<T> node = find(value, tree);
		if(node == null){
			return false;
		}
		
		if(node.left != null && node.right != null){
			//case three: tree has 2 children
			BinarySearchTree<T> newNode = findInOrderSuccessor(node); 
			T newKey = newNode.key;
			node.key = newKey;
			return remove(newKey, newNode);
		}
		boolean treeIsLeftChild = node.equals(node.parent.left);
		//case 1: tree is leaf
		if(node.left == null && node.right == null){
			if(treeIsLeftChild){
				node.parent.left = null;
			}else{
				node.parent.right = null;
			}
			return true;
		}else{
			//case two: tree has 1 child
			if(node.left != null){
				if(treeIsLeftChild){
					node.parent.left = node.left;
				}else{
					node.parent.right = node.left;
				}
				node.left.parent = node.parent;
			}else{
				if(treeIsLeftChild){
					node.parent.left = node.right;
				}else{
					node.parent.right = node.right;
				}
				node.right.parent = node.parent;
			}
			return true;
		}
	}
	
	public String toString(){
		return key +"";
	}
	
	public void traverseInOrder(){
		traverseInOrder(this);
	}
	
	private void traverseInOrder(BinarySearchTree<T> tree){
		if(tree == null){
			return;
		}else{
			traverseInOrder(tree.left);
			System.out.print(tree.key+", ");
			traverseInOrder(tree.right);
		}
	}
	
	public void traversePostOrder(){
		traversePostOrder(this);
	}
	
	private void traversePostOrder(BinarySearchTree<T> tree){
		if(tree == null){
			return;
		}else{
			traversePostOrder(tree.left);
			traversePostOrder(tree.right);
			System.out.print(tree.key+", ");
		}
	}
	
	public void traversePreOrder(){
		traversePreOrder(this);
	}

	private void traversePreOrder(BinarySearchTree<T> tree){
		if(tree == null){
			return;
		}else{
			System.out.print(tree.key+", ");
			traversePreOrder(tree.left);
			traversePreOrder(tree.right);
		}
	}
	
}
