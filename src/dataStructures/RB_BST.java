package dataStructures;

public class RB_BST<T extends Comparable<T>>{
//	private static RB_BST<?> sentinel;
	private RB_BST<T> left;
	private RB_BST<T> right;
	private RB_BST<T> parent;
	private boolean red; 
	private T key;
	
	public RB_BST(T key){
		this(key, null, false);
	}
	
	public RB_BST(T key, boolean red){
		this(key, null, red);
	}
	
	public RB_BST(T key, RB_BST<T> parent){
		this(key, parent, true);
		
	}
	
	public RB_BST(T key, RB_BST<T> parent, boolean red){
		this.key = key;
		this.parent = parent;
		this.red = red;
//		sentinel = new RB_BST<T>(key, false);
	}
	
	public RB_BST(T[] keys){
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
	
	private RB_BST<T> balancedTreeFromArray(T[] keys, int st, int end){
		final int mid = (end+st)/2;
		final RB_BST<T> tree = new RB_BST<T>(keys[mid]);
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
	
	private boolean checkBalanced(RB_BST<T> tree){
		return (tree.maxDepth() - tree.minDepth() <= 1);
	}
	
	public boolean contains(T value){
		return find(value, this) != null;
	}
	
	public RB_BST<T> find(T value){
		return find(value, this);
	}
	
	private RB_BST<T> find(T value, RB_BST<T> child){
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
	
	public RB_BST<T> findInOrderPredecessor(RB_BST<T> tree){
		if(tree == null){
			return null;
		}
		if(tree.left == null){
			RB_BST<T> p = tree.parent;
			while(p != null && p.key.compareTo(tree.key) > -1){
				p = p.parent;
			}
			if(p != null) return p;
			return null;
		}
		return findMax(tree.left);
	}
	
	public RB_BST<T> findInOrderSuccessor(RB_BST<T> tree){
		if(tree == null){
			return null;
		}
		if(tree.right == null){
			RB_BST<T> p = tree.parent;
			while(p != null && p.key.compareTo(tree.key) < 1){
				p = p.parent;
			}
			if(p != null) return p;
			return null;
		}
		return findMin(tree.right);
	}
	
	public RB_BST<T> findMax(){
		return findMax(this);
	}
	
	private RB_BST<T> findMax(RB_BST<T> tree){
		if(tree.right == null){
			return tree;
		}
		return findMax(tree.right);
	}
	
	public RB_BST<T> findMin(){
		return findMin(this);
	}
	
	private RB_BST<T> findMin(RB_BST<T> tree){
		if(tree.left == null){
			return tree;
		}
		return findMin(tree.left);
	}
	
	private RB_BST<T> findGrandParent(RB_BST<T> tree){
		if(tree != null){
			if(tree.parent != null){
				return tree.parent.parent;
			}
		}
		return null;
	}
	
	private RB_BST<T> findUncle(RB_BST<T> tree){
		RB_BST<T> grand = findGrandParent(tree);
		if(grand != null){
			if(tree.parent.equals(grand.left)){
				return grand.right;
			}
			return grand.left;
		}
		return null;
	}
	
	public T getInOrderPredecessor(RB_BST<T> tree){
		return findInOrderPredecessor(tree).key;
	}
	
	public T getInOrderSuccessor(RB_BST<T> tree){
		return findInOrderSuccessor(tree).key;
	}
	
	public T getMax(){
		return findMax(this).key;
	}
	
	public T getMin(){
		return findMin(this).key;
	}
	
	public void insert(T key){
		insert(key, findRoot());
	}
	//TODO balancing does not work.ex
	private void insert(T key, RB_BST<T> tree){
		if(tree.key.compareTo(key) >= 0){
			if(tree.left == null){
				tree.left = new RB_BST<T>(key, this);
				tree.left.parent = tree;
				repaint1(tree.left);
			}else{
				insert(key, tree.left);
			}
		}else{
			if(tree.right == null){
				tree.right = new RB_BST<T>(key, this);
				tree.right.parent = tree;
				repaint1(tree.right);
			}else{
				insert(key,tree.right);
			}
		}
	}
	
	private void repaint1(RB_BST<T> child){
		if(child.parent == null){
			child.red = false;
		}else if(child.parent.red){
			repaint3(child);
		}
	}
	
	private void repaint3(RB_BST<T> child){
		RB_BST<T> uncle = findUncle(child);
		if(uncle != null && uncle.red){
		uncle.red = false;
		child.parent.red = false;
		RB_BST<T> grand = findGrandParent(child);
		grand.red = true;
		repaint1(grand);
		}else{
			repaint4(child);
		}
	}
	
	private void repaint4(RB_BST<T> child){
		RB_BST<T> grand = findGrandParent(child);
		if(child.equals(child.parent.left) && child.parent.equals(grand.right)){
			rotateRight(child.parent);
			child = child.right;
		}else if(child.equals(child.parent.right) && child.parent.equals(grand.left)){
			rotateLeft(child.parent);
			child = child.left;
		}
		repaint5(child);
	}
	
	private void repaint5(RB_BST<T> child){
		RB_BST<T> grand = findGrandParent(child);
		grand.red = true;
		child.parent.red = false;
		if(child.equals(child.parent.left)){
			rotateRight(grand);
		}else{
			rotateLeft(grand);
		}
	}
	
	
	private void rotateLeft(RB_BST<T> parent) {
		final RB_BST<T> child = parent.right;
		parent.right = child.left;
		if(child.left != null){
			child.left.parent = parent;
		}
		child.parent = parent.parent;
		if(parent.parent != null){
			if(parent.equals(parent.parent.left)){
				parent.parent.left = child;
			}else{
				parent.parent.right = child;
			}
		}
		child.left = parent;
		parent.parent = child;
	}
	
	private void rotateRight(RB_BST<T> parent) {
		final RB_BST<T> child = parent.left;
		parent.left = child.right;
		if(child.right != null){
			child.right.parent = parent;
		}
		child.parent = parent.parent;
		if(parent.parent != null){
			if(parent.equals(parent.parent.left)){
				parent.parent.left = child;
			}else{
				parent.parent.right = child;
			}
		}
		child.right = parent;
		parent.parent = child;
	}

	public boolean isBalanced(){
		if(this.left == null && this.right == null) return true;
		return checkBalanced(this);
	}
	
	public int maxDepth(){
		return maxDepth(0, this);
	}
	
	private int maxDepth(int maxDepth, RB_BST<T> tree){
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
	
	
	private int minDepth(int minDepth, RB_BST<T> tree){
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
	
	private boolean remove(T value, RB_BST<T> tree){
		RB_BST<T> node = find(value, tree);
		if(node == null){
			return false;
		}
		
		if(node.left != null && node.right != null){
			//case three: tree has 2 children
			RB_BST<T> newNode = findInOrderSuccessor(node); 
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
//		return traverseBFS();
		return (red? "R":"B") + key +"";
	}
	
	public String traverseBFS(){
		LinkedList<RB_BST<T>> queue = new LinkedList<RB_BST<T>>();
		StringBuilder sb = new StringBuilder();
		queue.push(findRoot());
		int lastDepth = 0;
		while(! queue.isEmpty()){
			final RB_BST<T> current = queue.removeLast();
			final int currentDepth = findDepth(current); 
			if(lastDepth < currentDepth){
				lastDepth = currentDepth;
				System.out.println();
				sb.append("\n");
			}
			System.out.print((current.red? "R":"B")+current.key + " ");
			sb.append((current.red? "R":"B")+current.key + " ");
			if(current.left != null){
				queue.push(current.left);
			}
			if(current.right != null){
				queue.push(current.right);
			}
		}
		System.out.println();
		return sb.toString();
	}
	
	public int findDepth(RB_BST<T> tree){
		return findDepth(tree, 0);
	}
	
	public int findDepth(T data){
		return findDepth(find(data), 0);
	}
	
	private int findDepth(RB_BST<T> tree, int depth){
		if(tree.parent !=  null){
			depth++;
			return findDepth(tree.parent, depth);
		}
		return depth;
	}
	
	public RB_BST<T> findRoot(){
		return findRoot(this);
	}
	
	public RB_BST<T> findRoot(RB_BST<T> tree){
		if(tree.parent != null){
			return findRoot(tree.parent);
		}
		return tree;
	}
	
	public void traverseInOrder(){
		traverseInOrder(findRoot());
	}
	
	private void traverseInOrder(RB_BST<T> tree){
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
	
	private void traversePostOrder(RB_BST<T> tree){
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

	private void traversePreOrder(RB_BST<T> tree){
		if(tree == null){
			return;
		}else{
			System.out.print(tree.key+", ");
			traversePreOrder(tree.left);
			traversePreOrder(tree.right);
		}
	}
	
}
