/**
 * This file pedagogical material for the course
 * CS 241: Data Structures and Algorithms II
 * taught at California State Polytechnic University - Pomona, and
 * cannot be used without express written consent from the author.
 * 
 * Copyright (c) 2014 - Edwin Rodr&iacute;guez.
 */
package edu.csupomona.cs.cs241.prog_assgmnt_1;

/**
 * @author Edwin Rodr&iacute;guez
 * @author Khamille A. Sarmiento
 */
public interface Heap<V extends Comparable<V>> {
	
	public static enum MODE {MAX, MIN};

	/**
	 * Adds a new element into the next available spot in the heap.
	 * @param value of the new element.
	 */
	public void add(V value);

	/**
	 * Copies the elements from the heap into a generic array.
	 * @return the heap as an array data type.
	 */
	public V[] toArray();

	/**
	 * Depending on whether MAX or MIN heap, this method will remove
	 * an element from the top of the heap.
	 * @return the top element of the heap.
	 */
	public V remove();
	
	/**
	 * Takes a generic array and places it into a heap that follows
	 * the heap property.
	 * @param array with generic heap values.
	 */
	public void fromArray(V[] array);

	/**
	 * Returns a generic array that contains heap values that follow
	 * the heap property.
	 * @return the array with heap-sorted contents.
	 */
	public V[] getSortedContents();
	
	/**
	 * Returns the mode of the heap, whether it be MAX or MIN.
	 * @return mode of the heap.
	 */
	public MODE getMode();
	
	/**
	 * Sets the mode of the heap, whether it be MAX or MIN.
	 * @param mode to set 
	 */
	public void setMode(MODE mode);

}