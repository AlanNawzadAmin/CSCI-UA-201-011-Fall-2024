package Trees_final;
import LinkedList_final.DoublyLinkedList;
import LinkedList_final.List;
import StackQueues.Stack;
import StackQueues.LinkedStack;
import StackQueues.Queue;
import StackQueues.LinkedQueue;

public class LinkedTree<E> implements Tree<E>{
	private class Node implements Position<E>{
		private E element;
		private DoublyLinkedList<Position<E>> children;
		private Node parent;
		
		public Node(E element, Node parent) {
			this.element = element;
			this.parent = parent;
			this.children = new DoublyLinkedList<Position<E>>();
		}
		
		public E getElement(){return element;}
		public Node getParent() {return parent;}
		public List<Position<E>> getChildren() {return children;}
	}
	
	Node root;
	int size;

	public LinkedTree(E root_element) {
		root = new Node(root_element, null);
	}
	
	public Position<E> root(){
		return root;
	}
	public Position<E> parent(Position<E> p){
		return ((Node)p).getParent();
	};
	public List< Position<E> >  children(Position<E> p){
		return ((Node)p).getChildren();
	};
	public int numChildren(Position<E> p) {
		return ((Node)p).getChildren().size();
	}
	public boolean isInternal(Position<E> p) {
		return numChildren(p) != 0;
	}
	public boolean isExternal(Position<E> p) {
		return !isInternal(p);
	}
	public boolean isRoot(Position<E> p) {
		return p == root;
	}
	public int size() {return size;}
	public boolean isEmpty() { return size == 0;}
	
	public int depth(Position<E> p) {
		if (isRoot(p)) return 0;
		else return 1 + depth(parent(p));
	}
	
	public int height(Position<E> p) {
		int h = 0;
		List< Position<E> > ch = children(p);
		for (int i=0;i< ch.size(); i++) {
			h = Math.max(h, height(ch.getAtIndex(i)));
		}
		return h;
	}
	
	private boolean depthFirstSearch(E element){
		Stack<Position<E>> stack = new LinkedStack<Position<E>>();
		stack.push(root);
		while (stack.size() > 0) {
			Position<E> next_pos = stack.pop();
			if (next_pos.getElement() == element) return true;
			List<Position<E>> ch = children(next_pos);
			for (int j=0; j<ch.size(); j++) {
				stack.push(ch.getAtIndex(j));
			}
		}
		return false;
	}
	
	private boolean breadthFirstSearch(E element){
		Queue<Position<E>> queue = new LinkedQueue<Position<E>>();
		queue.enqueue(root);
		while (queue.size() > 0) {
			Position<E> next_pos = queue.dequeue();
			if (next_pos.getElement() == element) return true;
			List<Position<E>> ch = children(next_pos);
			for (int j=0; j<ch.size(); j++) {
				queue.enqueue(ch.getAtIndex(j));
			}
		}
		return false;
	}
	
//	Using recursion
	private List<Position<E>> positionsRecursive(Position<E> p, List<Position<E>> list){
		list.addLast(p);
		List<Position<E>> children = children(p);
		for (int i=0; i<children.size(); i++) {
			positionsRecursive(children.getAtIndex(i), list);
		}
		return list;
	}
	
	private List<Position<E>> positionsRecursive(Position<E> p){
		List<Position<E>> list = new DoublyLinkedList<Position<E>>();
		return positionsRecursive(p, list);
	}
	
//	Using stacks
	private List<Position<E>> positions(Position<E> p){
		List<Position<E>> pos = new DoublyLinkedList<Position<E>>();
		Stack<Position<E>> stack = new LinkedStack<Position<E>>();
		stack.push(p);
		while (stack.size() > 0) {
			Position<E> next_pos = stack.pop();
			pos.addLast(next_pos);
			List<Position<E>> ch = children(next_pos);
			for (int j=0; j<ch.size(); j++) {
				stack.push(ch.getAtIndex(j));
			}
		}
		return pos;
	}
	
	public List<Position<E>> positions(){
		return positions(root);
	}
}
