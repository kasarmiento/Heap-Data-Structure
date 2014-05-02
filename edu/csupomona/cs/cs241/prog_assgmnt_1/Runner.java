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
 * Class: Runner.
 * 
 * This class called Runner contains the main method. It performs
 * simple tasks on a NodeHeap of integers. Runner tests the following:
 * 
 * - adding 8 elements into a MAX-heap
 * - toArray()
 * - switching modes
 * - heapsort for both MAX and MIN heaps
 * 
 * @author Khamille A. Sarmiento
 */
public class Runner {

	public static void main(String[] args) {
		NodeHeap<Integer> myHeap = new NodeHeap<Integer>(Heap.MODE.MAX);
		
		myHeap.add(4);
		myHeap.add(5);
		myHeap.add(1);
		myHeap.add(8);
		myHeap.add(20);
		myHeap.add(6);
		myHeap.add(7);
		myHeap.add(9);
		
		System.out.println("My Heap...");
		Object[] array = myHeap.toArray();
		for(int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}		
		
		System.out.println("\nUsing heapsort...");
		array = myHeap.getSortedContents();
		for(int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
			
		System.out.println("\nChanging the mode of My Heap to be MIN...");
		myHeap.setMode(Heap.MODE.MIN);
		array = myHeap.toArray();
		for(int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}	
		
		System.out.println("\nUsing heapsort...");
		array = myHeap.getSortedContents();
		for(int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		
		myHeap.remove();
		myHeap.remove();
		myHeap.remove();
		System.out.println("\nRemoved some items...");
		array = myHeap.toArray();
		for(int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		
		System.out.println("\nUsing heapsort...");
		array = myHeap.getSortedContents();
		for(int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		
		myHeap.remove();
		myHeap.remove();
		myHeap.remove();
		myHeap.remove();
		System.out.println("\nRemoved MORE items...");
		array = myHeap.toArray();
		for(int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		
		System.out.println("\nWill attempt to remove the last item...");
		myHeap.remove();
		array = myHeap.toArray();
			
		//System.out.println("\nPray it all works!");

	}

}
