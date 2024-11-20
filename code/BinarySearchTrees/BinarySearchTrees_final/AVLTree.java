package BinarySearchTrees_final;


// largely taken from textbook
public abstract class AVLTree<V> extends BinarySearchTrees<V>{
	private boolean isBalanced(Node node) {
		if (node == null) return false;
		else {
			Node left = node.getLeft();
			Node right = node.getRight();
			if (left == null && right == null) {
				return true;
			}
			else if (left == null) {
				return right.getHeight() <= 0;
			}
			else if (right == null) {
				return left.getHeight() <= 0;
			}
			else {
				return Math.abs(left.getHeight() - right.getHeight()) <= 1;	
			}
		}
	}
	
	private Node highestChild(Node node) {
		Node left = node.getLeft();
		Node right = node.getRight();
		if(left == null){
			return right;
		}
		else if (right == null) {
			return left;
		}
		else {
			if (left.getHeight() > right.getHeight()) {
				return left;
			}
			else {
				return right;
			}
		}
	}
	
	private void replaceChild(Node parent, Node new_child, boolean isLeft) {
		if (isLeft) {
			parent.setLeft(new_child);
		}
		else {
			parent.setRight(new_child);
		}
		if (new_child != null) new_child.setParent(parent);
	}
	
	private void rotate(Node node) {
		Node node_p = node.getParent();
		Node node_pp = node_p.getParent();
		if (node_pp == null) {
			root = node;
			node.setParent(null);
		}
		else {
			replaceChild(node_pp, node, node_p==node_pp.getLeft());
		}
		if (node == node_p.getLeft()) {
			replaceChild(node_p, node.getRight(), node==node_p.getLeft());
			replaceChild(node, node_p, false);
		}
		else {
			replaceChild(node_p, node.getLeft(), node==node_p.getLeft());
			replaceChild(node, node_p, true);
		}
	}
	
	private void recomputeHeight(Node node) {
		Node left = node.getLeft();
		Node right = node.getRight();
		int left_height = -1;
		int right_height = -1;
		if (left != null) {left_height = left.getHeight();}
		if (right != null) {right_height = right.getHeight();}
		node.setHeight(Math.max(left_height, right_height) + 1);
	}
	
	private void rearrange(Node new_node) {
		Node node = new_node.getParent();
		while (isBalanced(node)) {
			recomputeHeight(node);
			node = node.getParent();
		}
		if (node != null) {
			// get nodes to rearrange
			Node node_c = highestChild(node);
			Node node_cc = highestChild(node_c);
			// rearrange
			if ((node.getLeft() == node_c) == (node_c.getLeft() == node_cc)) {
				rotate(node_c);
				recomputeHeight(node);
				recomputeHeight(node_cc);
				recomputeHeight(node_c);
			}
			else {
				rotate(node_cc);
				rotate(node_cc);
				recomputeHeight(node);
				recomputeHeight(node_c);
				recomputeHeight(node_cc);
			}
		}
	}
	
	public V put(Integer key, V value) {
		if (isEmpty()) {
			root = new Node(key, value, null);
			size++;
			return null;
		}
		else {
			Node node = find_nearest(key);
			if (node.getKey() == key) {
				V old_value = node.getValue();
				node.setValue(value);
				return old_value;
			}
			else if (node.getKey() > key) {
				Node new_node = new Node(key, value, node);
				node.setLeft(new_node);
				size++;
				rearrange(new_node);
				return null;
			}
			else {
				Node new_node = new Node(key, value, node);
				node.setRight(new_node);
				size++;
				rearrange(new_node);
				return null;
			}
		}
	}
}
