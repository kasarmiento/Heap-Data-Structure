/**
 * CS 241: Data Structures and Algorithms II
 * Professor: Edwin Rodr&iacute;guez
 *
 * Programming Assignment #1
 *
 * NODEHEAP: A heap implementation using nodes with links to their
 * parents, and their children. This implementation allows the user
 * to choose a MAX-heap or a MIN-heap by setting the heap's Mode. 
 * The heap always maintains its heap property at the end of every
 * function call. 
 *
 * @author Khamille A. Sarmiento
 */
package edu.csupomona.cs.cs241.prog_assgmnt_1;

/**
 * Class: Node.
 * 
 * A triply linked list implementation of a Node object, with a reference
 * to it's parent, it's left child, and it's right child. Also contains a 
 * boolean called "isValid" which helps to mark the node during a method
 * call on "getSortedContents". This node is generic, meaning it works with
 * different data types.
 * 
 * @author Khamille A. Sarmiento
 *
 * @param <V> Desired data type.
 */
public class Node<V> { // doubly linked list implementation, with a reference to the parent and its two children.

	public V elem;
	public Node left;
	public Node right;
	public Node parent;
	public boolean isValid;
	
	// Constructor.
	public Node(V value, Node leftRef, Node rightRef, Node parentRef) {
		elem = value;
		left = leftRef;
		right = rightRef;
		parent = parentRef;
		isValid = true;
	}
}