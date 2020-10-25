package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// Set up head and tail sentinel nodes with
		// head/tail nodes have null elements
		// head/tail nodes point to each other
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null, head, null);
		head.next = tail;
		size = 0;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 * @throws NullPointerException if input is null
	 */
	public boolean add(E element)
	{
		add(size, element);
		return false;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		return getNode(index).data;
	}

	/** Get the node at position index
	 * Prevents access to the head or tail nodes
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public LLNode<E> getNode(int index)
	{
		if ((index < 0) || (index >= size)) {
			throw new IndexOutOfBoundsException();
		}
		LLNode<E> currNode;
		if (index < size/2) {
			int currIndex = -1;
			currNode = head;
			while (currIndex < index) {
				currNode = currNode.next;
				currIndex ++;
			}
		}
		else {
			int currIndex = size;
			currNode = tail;
			while (currIndex > index) {
				currNode = currNode.prev;
				currIndex--;
			}
		}
		return currNode;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param index The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		// Throw exception if element = null
		if (element == null) {
			throw new NullPointerException();
		}

		// Throw exception if index is invalid
		if ((index < 0) || (index > size)) {
			throw new IndexOutOfBoundsException();
		}

		// get nodes surrounding the new node
		// current node at index becomes next node since all nodes move up
		LLNode<E> nextNode;
		LLNode<E> prevNode;
		if (index == size) {
			nextNode = tail;
		}
		else {
			nextNode = getNode(index);
		}
		prevNode = nextNode.prev;

		// Define new node and update all links
		LLNode<E> newNode = new LLNode<E>(element, prevNode, nextNode);
		prevNode.next = newNode;
		nextNode.prev = newNode;

		// update list size
		size ++;
	}


	/** Return the size of the list */
	public int size() 
	{
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		// get the current node at the index
		// throws an IndexOutOfBoundsException if invalid
		LLNode<E> currNode = getNode(index);
		LLNode<E> prevNode = currNode.prev;
		LLNode<E> nextNode = currNode.next;
		// Update node references to skip currNode
		prevNode.next = nextNode;
		nextNode.prev = prevNode;
		// Update list size
		size --;
		// return the data in the removed node
		return currNode.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		if (element == null) {
			throw new NullPointerException();
		}
		LLNode<E> node = getNode(index);
		E data = node.data;
		node.data = element;
		return data;
	}

	/**
	 * Override the toString method since it is not working.
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder("MyLinkedList: [");
		LLNode<E> currNode = head;
		while (currNode.next != tail) {
			currNode = currNode.next;
			result.append(currNode.data.toString());
			result.append(", ");
		}
		result.append("]");
		return result.toString();
	}

	/**
	 * Main method for basic testing of MyLinkedList class
	 * @param args
	 */
	public static void main(String[] args) {
		MyLinkedList<Integer> basicList = new MyLinkedList<Integer>();
		System.out.println(basicList);
		basicList.add(10);
		System.out.println(basicList);
	}
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// Default constructor with just an element definition
	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

	// Alternate constructor with complete node definition
	public LLNode(E e, LLNode<E> prev, LLNode<E> next)
	{
		this.data = e;
		this.prev = prev;
		this.next = next;
	}

}