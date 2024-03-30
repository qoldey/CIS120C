package com.allendowney.thinkdast;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * @author downey & me
 * @param <E>
 *
 */
public class MyLinkedList<E> implements List<E> {

	/**
	 * Node is identical to ListNode from the example, but parameterized with T
	 *
	 * @author downey
	 *
	 */
	private class Node {
		public E data;
		public Node next;

		public Node(E data) {
			this.data = data;
			this.next = null;
		}

		@SuppressWarnings("unused")
		public Node(E data, Node next) {
			this.data = data;
			this.next = next;
		}

		public String toString() {
			return "Node(" + data.toString() + ")";
		}
	}

	private int size; // keeps track of the number of elements
	private Node head; // reference to the first node

	/**
	 *
	 */
	public MyLinkedList() {
		size = 0;
		head = null;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// run a few simple tests
		List<Integer> mll = new MyLinkedList<Integer>();
		mll.add(1);
		mll.add(2);
		mll.add(3);
		System.out.println(Arrays.toString(mll.toArray()) + " size = " + mll.size());

		mll.remove(2);
		System.out.println(Arrays.toString(mll.toArray()) + " size = " + mll.size());
	}

	@Override
	public boolean add(E element) {
		if (head == null) { // if head is null, create a new one
			head = new Node(element);
		} else {
			Node node = head;
			// loop until the last node
			for (; node.next != null; node = node.next) {
			}
			node.next = new Node(element);
		}
		size++;// increase size
		return true;
	}

	@Override
	public void add(int index, E element) {
		if (index < 0 || index > size) { // index can == size
			throw new IndexOutOfBoundsException();
		}
		if (index == 0) { // if adding to start, just move head up
			head = new Node(element, head);
		} else {
			Node beforeIndexNode = getNode(index - 1); // get node before index
			beforeIndexNode.next = new Node(element, beforeIndexNode.next); // set that next to be a new node w/ new
																			// data and previous next
		}
		size++;// increase size
	}

	@Override
	public boolean addAll(Collection<? extends E> collection) {
		boolean flag = true;
		for (E element : collection) {
			flag &= add(element);
		}
		return flag;
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> collection) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear() {
		head = null;
		size = 0;
	}

	@Override
	public boolean contains(Object obj) {
		return indexOf(obj) != -1;
	}

	@Override
	public boolean containsAll(Collection<?> collection) {
		for (Object obj : collection) {
			if (!contains(obj)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public E get(int index) {
		Node node = getNode(index);
		return node.data;
	}

	/**
	 * Returns the node at the given index.
	 * 
	 * @param index
	 * @return
	 */
	private Node getNode(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		Node node = head;
		for (int i = 0; i < index; i++) {
			node = node.next;
		}
		return node;
	}

	@Override
	public int indexOf(Object target) {
		for (int i = 0; i < size; i++) {
			if (equals(get(i), target)) { // if index = target obj
				return i;
			}
		}
		return -1;

	}

	/**
	 * Checks whether an element of the array is the target.
	 *
	 * Handles the special case that the target is null.
	 *
	 * @param target
	 * @param object
	 */
	private boolean equals(Object target, Object element) {
		if (target == null) {
			return element == null;
		} else {
			return target.equals(element);
		}
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Iterator<E> iterator() {
		E[] array = (E[]) toArray();
		return Arrays.asList(array).iterator();
	}

	@Override
	public int lastIndexOf(Object target) {
		Node node = head;
		int index = -1;
		for (int i = 0; i < size; i++) {
			if (equals(target, node.data)) {
				index = i;
			}
			node = node.next;
		}
		return index;
	}

	@Override
	public ListIterator<E> listIterator() {
		return null;
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		return null;
	}

	@Override
	public boolean remove(Object obj) {
		int index = indexOf(obj);
		if (index == -1) {
			return false;
		}
		remove(index);
		return true;
	}

	@Override
	public E remove(int index) { // remove node from index and shift subsequent nodes to the left

		if ((index < 0 || index >= size)) {
			throw new IndexOutOfBoundsException("Element out of bounds");
		}

		E old = get(index);
		if (index == 0) { // if index = first pos then just set head = to 2nd pos, ignoring the first pos
			head = head.next;
		} else { // else get the node before index and assign its next to index.next, skipping it
			Node beforeIndexNode = getNode(index - 1);
			beforeIndexNode.next = beforeIndexNode.next.next; // This is a benefit to using Linkedlists, essentially
																// just skipping a node
		}
		size--; // lower size
		return old;
	}

	@Override
	public boolean removeAll(Collection<?> collection) {
		boolean flag = true;
		for (Object obj : collection) {
			flag &= remove(obj);
		}
		return flag;
	}

	@Override
	public boolean retainAll(Collection<?> collection) {
		throw new UnsupportedOperationException();
	}

	@Override
	public E set(int index, E element) {
		Node node = getNode(index);
		E oldNodeData = node.data;
		node.data = element;
		return oldNodeData;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		if (fromIndex < 0 || toIndex >= size || fromIndex > toIndex) {
			throw new IndexOutOfBoundsException();
		}
		// TODO: classify this and improve it.
		int i = 0;
		MyLinkedList<E> list = new MyLinkedList<E>();
		for (Node node = head; node != null; node = node.next) {
			if (i >= fromIndex && i <= toIndex) {
				list.add(node.data);
			}
			i++;
		}
		return list;
	}

	@Override
	public Object[] toArray() {
		Object[] array = new Object[size];
		int i = 0;
		for (Node node = head; node != null; node = node.next) {
			// System.out.println(node);
			array[i] = node.data;
			i++;
		}
		return array;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		throw new UnsupportedOperationException();
	}
}
