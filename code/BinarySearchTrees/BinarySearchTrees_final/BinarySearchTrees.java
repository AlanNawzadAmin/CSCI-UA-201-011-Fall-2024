package BinarySearchTrees_final;

import LinkedList_final.DoublyLinkedList;
import LinkedList_final.List;
import Maps_final.Entry;
import Maps_final.Map;



public abstract class BinarySearchTrees <V> implements Map<Integer, V>{
	private class Node implements Entry<Integer, V>{
		private int key;
		private V value;
		private Node left;
		private Node right;
		Node(Integer key, V value){
			this.key = key;
			this.value = value;
		}
		public Integer getKey() {
			return key;
		}
		public V getValue() {
			return value;
		}
		public void setValue(V value) {
			this.value = value;
		}
		public Node getLeft() {
			return left;
		}
		public Node getRight() {
			return right;
		}
		public void setLeft(Node left) {
			this.left = left;
		}
		public void setRight(Node right) {
			this.right = right;
		}
	}
	
	int size;
	Node root;
	public BinarySearchTrees() {}
	
	public int size() {return size;};
	public boolean isEmpty() { return size() == 0;};
	
	private Node find_nearest(Integer key){
		if (isEmpty()) return null;
		else {
			Node current_node = root;
			while (true) {
				Integer node_key = current_node.getKey();
				Node left = current_node.getLeft();
				Node right = current_node.getRight();
				if (key < node_key && left != null) current_node = left;
				else if (key > node_key && right != null) current_node = right;
				else return current_node;
			}
		}
	}
	
	public V get(Integer key) {
		if (isEmpty()) return null;
		else {
			Node node = find_nearest(key);
			if (node.getKey() == key) return node.getValue();
			else return null;
		}
	}

	public V put(Integer key, V value) {
		if (isEmpty()) {
			root = new Node(key, value);
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
				node.setLeft(new Node(key, value));
				return null;
			}
			else {
				node.setRight(new Node(key, value));
				return null;
			}
		}
	}
	
	private List<Entry<Integer, V>> inorderEntries(Node p, List<Entry<Integer, V>> list){
		if (p == null) return list;
		inorderEntries(((Node)p).getLeft(), list);
		list.addLast(p);
		inorderEntries(((Node)p).getRight(), list);
		return list;
	}
	public List<Entry<Integer, V>> entrySet(){
		List<Entry<Integer, V>> list = new DoublyLinkedList<Entry<Integer, V>>();
		return inorderEntries(root, list);
	}
	
	private List<V> inorderValues(Node p, List<V> list){
		if (p == null) return list;
		inorderValues(((Node)p).getLeft(), list);
		list.addLast(p.getValue());
		inorderValues(((Node)p).getRight(), list);
		return list;
	}
	public List<V> values(){
		List<V> list = new DoublyLinkedList<V>();
		return inorderValues(root, list);
	}
	
	private List<Integer> inorderKeys(Node p, List<Integer> list){
		if (p == null) return list;
		inorderKeys(((Node)p).getLeft(), list);
		list.addLast(p.getKey());
		inorderKeys(((Node)p).getRight(), list);
		return list;
	}
	public List<Integer> keySet(){
		List<Integer> list = new DoublyLinkedList<Integer>();
		return inorderKeys(root, list);
	}
}
