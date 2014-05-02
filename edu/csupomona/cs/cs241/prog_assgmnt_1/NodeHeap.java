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
 * Class: NodeHeap.
 * 
 * A NodeHeap is a heap implementation that uses nodes instead of 
 * the more commonly used array data structure. This implementation
 * is intuitive because it connects each node in the same way that one
 * would visualize a heap to look like. It is interesting because each
 * node in the heap can be mapped to using binary...
 * 
 * @author Khamille A. Sarmiento
 *
 * @param <V> Desired Data Type.
 */
public class NodeHeap<V extends Comparable<V>> implements Heap<V> {

	@SuppressWarnings("rawtypes")
	
	/**
	 * Private Variable: root. Type: Node.
	 * Keeps track of the top element of the heap.
	 * if MAX: root holds the largest value.
	 * if MIN: root holds the smallest value.
	 */
	private Node root;
	
	/**
	 * Private Variable: myMode. Type: MODE.
	 * Determines the type of heap that is implemented. 
	 * Can hold either of these values: MAX, MIN.
	 */
	private edu.csupomona.cs.cs241.prog_assgmnt_1.Heap.MODE myMode; 
	
	/**
	 * Private Variable: elemCount. Type: int.
	 * Initially set to zero. Holds the number of elements
	 * in this heap.
	 */
	public int elemCount = 0;     
	
	/** 
	 * Constructor for NodeHeap.
	 * When a new NodeHeap is created, it is empty and it's root is set to null.
	 * It's element count is also 0.
	 * @param mode Allows the user to set the heap to be MAX or MIN.
	 */
	public NodeHeap(edu.csupomona.cs.cs241.prog_assgmnt_1.Heap.MODE mode) {
		root = null;
		myMode = mode;
	}

	/**
	 * Public Method: add.
	 * Adds a new element into the next available spot in the heap.
	 * After the element is added, it is moved to its correct location
	 * on the heap to preserve either it's MAX or MIN heap property. 
	 * @param value Allows the user to determine the value of the new element.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void add(V value) { 
		Node newNode;
		if(elemCount == 0) {
			newNode = new Node(value, null, null, root);
			root = newNode; 
			elemCount++;
		}
		else {
			int nextAvailableSpot = elemCount + 1;
			String binarySpot = toBinaryString(nextAvailableSpot);	
			for(int i = 1; i < binarySpot.length()-1; i++) { // goes from 2nd string to the end.
				String c = binarySpot.substring(i, i+1);
				if(c.compareTo("0") == 0)
					root = root.left;
				else if(c.compareTo("1") == 0)
					root = root.right;	
			}
			int lastNumber = binarySpot.length()-1;
			if(binarySpot.substring(lastNumber).compareTo("0") == 0) {
				newNode = new Node(value, null, null, root);
				root.left = newNode; 
				elemCount++;
			}
			else {
				newNode = new Node(value, null, null, root);
				root.right = newNode; 
				elemCount++;
			}
			siftUp(newNode);
		}
		resetRoot();
	}
	
	/**
	 * Public Method: remove.
	 * Removes the top element of the heap and returns the value of
	 * the element that was removed.
	 * if MAX: The largest element in the heap will be removed.
	 * if MIN: The smallest element in the heap will be removed.
	 * @return The value of the element that was removed.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public V remove() {
		V value;
		if(elemCount > 0) {
			resetRoot();								// Reset the root
			value = (V) root.elem;						// Save the value of top node
			String x = toBinaryString(elemCount);		// Get the binary location of the last node
			Node lastNode = getNode(x);					// Find the last node in the heap using its binary location
			
			resetRoot();								// Reset the root
			swapElements(lastNode, root);				// Swap only the elements of the last node and the root
			
			int parentOfRemove = (elemCount) / 2;			// Find the "index value" + 1 of the last node's parent
			String y = toBinaryString(parentOfRemove);		// Get the binary location of "index value" 
			Node parent = getNode(y);						// Find the parent of the last node using it's binary location
			if(isOdd(elemCount))							// Set last node to null
				parent.right = null;
			else if(isEven(elemCount)) 
				parent.left = null;			
			
			elemCount--;									
			resetRoot();
			siftDown(root);
		}
		else {
			System.out.println("ERROR: The heap is empty!");
			return null;
		}		
		return (V) value;
	}

	/**
	 * Public Method: toArray.
	 * Copies the values of each node from the heap into a generic array.
	 * @return The heap as an Object array.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public V[] toArray() {
		Object[] array = null;
		
		if(elemCount > 0) {
			array = new Object[elemCount];				// Create a new Object array
			Node n = null;										// Node n = node to add
			
			for(int i = 0; i < array.length; i++) {				// For each index,
				String binaryLocation = toBinaryString(i+1); 	// Get the binary location of the corresponding node
				n = getNode(binaryLocation);					// Find the node to add
				array[i] = (V) n.elem;							// Add the node's value into the array
			}
			
			V[] result = (V[]) java.lang.reflect.Array.newInstance(array[0].getClass(), elemCount);
			System.arraycopy(array, 0, result, 0, elemCount);
			resetRoot();
			return (V[]) result;
		}
		
		else {
			System.out.println("ERROR: There are no elements in the heap!");
			return (V[]) array;
		}
		
		
	}
	
	/**
	 * Public Method: fromArray.
	 * Takes a generic array and places its elements into a heap
	 * that follows the heap property, specified by its mode. 
	 * @param array A generic array with elements to add to the heap.
	 */
	public void fromArray(V[] array) {
		for(int i = 0; i < array.length; i++) {
			add((V) array[i]);						// The heap is maintained through the add() method.
		}
	}

	/**
	 * Public Method: heapify.
	 * Takes the current heap and rearranges its nodes to follow the
	 * heap property, which is specified by it's mode.
	 */
	@SuppressWarnings("rawtypes")
	public void heapify() {
		if (elemCount > 1) {							// Initial check
			for (int i = elemCount / 2; i > 0; i--) {	// Start: the parent node of the last node. End: the root node 
				String y = toBinaryString(i);			// Get the binary location of node number i.
				Node pinkFuzz = getNode(y);				// Find node number i using it's binary location.
				siftDown(pinkFuzz);						// Sift down node number i
			}
		}
	}

	/**
	 * Public Method: getSortedContents.
	 * Sorts the values of the heap from greatest to least if MAX heap,
	 * or from least to greatest if MIN heap. Returns a generic array that
	 * contains values of the heap, sorted according to the heap's specified mode.
	 * if MAX: The array will contain values sorted from greatest to least.
	 * if MIN: The array will contain values sorted from least to greatest.
	 * @return The generic array whose contents are sorted in descending order if MAX, or ascending order if MIN.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public V[] getSortedContents() {
		V[] result = null;
		int elemNumber = elemCount;
		
		setOppositeMode();
		heapify();
		resetValidity();
		
		if(elemCount > 0) {										// Initial Check
			
			while(elemNumber > 1) {								// Will loop until the last node is processed
				
				// Step 1: SWAP			
				String x = toBinaryString(elemNumber);			// Get the binary location of the last valid node			
				Node y = getNode(x);							// Find the last valid node using it's binary location
				resetRoot();									// Reset the root to be the top element
				swapElements(root, y);							// Swap the root with the last valid node.
				
				// Step 2: Take the last valid node "out".				
				elemNumber--;									// Decrement elemNumber
				y.isValid = false;								// Change validity of "last valid node" to "invalid"
				
				// Step 3: Sift down, ignoring all elements after the last valid node.
				resetRoot();
				siftDownUntilSpecified(root);
			}
			
			result = toArray();
			setOppositeMode();
			resetValidity();
			heapify();											// Restore the heap property.
		}
				
		return result;
	}
	
	/**
	 * Public Method: getMode.
	 * Returns the mode of the heap, whether it be a MAX heap or a MIN heap.
	 * @return Mode of the heap.
	 */
	public edu.csupomona.cs.cs241.prog_assgmnt_1.Heap.MODE getMode() {
		return myMode;
	}

	/**
	 * Public Method: setMode.
	 * Allows the user to change the mode of the heap to be either a MAX heap
	 * or a MIN heap.
	 * @param mode The desired heap mode. Can only be one of two things: MAX, MIN.
	 */
	public void setMode(edu.csupomona.cs.cs241.prog_assgmnt_1.Heap.MODE mode) {
		myMode = mode;
		heapify();
	}
	
	/**
	 * Private Method: siftUp.
	 * Sift up is a helper method for NodeHeap that helps the heap
	 * maintain it's heap property. Sift up takes in a node and moves
	 * it up depending on the heap's specified mode.
	 * if MAX: the node will move up if it is larger than it's parent.
	 * if MIN: the node will move up if it is smaller than it's parent.
	 * The node will continue to move up until it reaches a spot where 
	 * it follows the heap property.
	 * @param toFix The specified node to fix. May or may not already be in place.
	 */
	@SuppressWarnings("rawtypes")
	private void siftUp(Node toFix) {	
		Node parent = toFix.parent;
		while(parent != null && compareNodes(toFix, parent)){
				swapElements(toFix, parent);
				toFix = parent;
				parent = toFix.parent;
		}	
	}
	
	/**
	 * Private Method: siftDown.
	 * Sift down is a helper method for Node Heap that helps the heap
	 * maintain it's heap property. Sift down takes in a node and moves
	 * it down depending on the heap's specified node. 
	 * if MAX: the node will move down if one of it's children are larger than itself.
	 * if MIN: the node will move down if one of it's children are smaller than itself.
	 * The node will continue to move down until it reaches a spot where 
	 * it follows the heap property.
	 * @param toFix The specified node to fix. May or may not already be in place.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void siftDown(Node toFix) {
		while(toFix.left != null) {   // if it has a left child
			if(toFix.right != null) {   // and if it has a right child
				if( compareNodes(toFix.left, toFix.right) && compareNodes(toFix.left, toFix) ) {  // check if left > right
					swapElements(toFix, toFix.left);
					toFix = toFix.left;
					toFix.left = toFix.left;
				}	
				else if( compareNodes(toFix.right, toFix.left) && compareNodes(toFix.right, toFix) ) { // or check if right < left
					swapElements(toFix, toFix.right);
					toFix = toFix.right;
					toFix.right = toFix.right;
				}
				else break;
			}
			else { // there must be a left child but no right child
				if(compareNodes(toFix.left, toFix)) { // and then if left is > node to Fix
					swapElements(toFix, toFix.left);
					toFix = toFix.left;
					toFix.left = toFix.left;
				}
				else break;
			}
			resetRoot();
		}
	}
	
	/**
	 * Private Method: siftDownUntilSpecified.
	 * "Sift down until specified" is an alternative version of the
	 * siftDown method. This alternative version was created to as a helper
	 * function to the method getSortedContents. It is different from siftDown
	 * because it also checks for a node's "validity" (see Node.java, boolean 
	 * field isValid for more details).
	 * @param toFix The specified node to fix. May or may not already be in place.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void siftDownUntilSpecified(Node toFix) {
		while(toFix.left != null && toFix.left.isValid) {		// While toFix has a left child, and while it's left child is valid
			if(toFix.right != null && toFix.right.isValid) { 	// If toFix has a right child, and if it's right child is valid
																// If both are valid, compare.				
				if( compareNodes(toFix.left, toFix.right) && compareNodes(toFix.left, toFix) ) {  // check if left > right
					swapElements(toFix, toFix.left);
					toFix = toFix.left;
					toFix.left = toFix.left;
				}	
				else if( compareNodes(toFix.right, toFix.left) && compareNodes(toFix.right, toFix) ) { // or check if right < left
					swapElements(toFix, toFix.right);
					toFix = toFix.right;
					toFix.right = toFix.right;
				}
				else break;
			}
			else {												// There must be a valid left child but no valid right child
																// If only left child is present and valid, compare.
				if(compareNodes(toFix.left, toFix)) { // and then if left is > node to Fix
					swapElements(toFix, toFix.left);
					toFix = toFix.left;
					toFix.left = toFix.left;
				}
				else break;
			}	
			resetRoot();
		}
	}
	
	/**
	 * Private Method: compareNodes. 
	 * Compares the element values of two nodes. 
	 * if MAX: Will return TRUE if the first node is greater than the second node. 
	 *         Will return FALSE otherwise.
	 * if MIN: Will return TRUE if the first node is less than the second node. 
	 *         Will return FALSE otherwise.
	 * @param firstNode The first node to compare
	 * @param secondNode The second node to compare
	 * @return boolean value
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private boolean compareNodes(Node firstNode, Node secondNode) {
		if(myMode == Heap.MODE.MAX) {
			return ((V)firstNode.elem).compareTo((V)secondNode.elem) > 0;
		}
		return ((V)firstNode.elem).compareTo((V)secondNode.elem) < 0;
	}
	
	/**
	 * Private Method: swapElements.
	 * Swaps the element values betweem two nodes without altering their
	 * links to their children or parent.
	 * @param firstNode One node to be swapped.
	 * @param secondNode The other node to be swapped.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void swapElements(Node firstNode, Node secondNode) {
		V temp = (V)secondNode.elem;
		secondNode.elem = firstNode.elem;
		firstNode.elem = temp;		
	}
	
	/**
	 * Private Method: resetRoot.
	 * Changes the value of the root to contain the topmost element 
	 * of the heap if it isn't already.
	 * */
	private void resetRoot() {
		while(root.parent != null) {
			root = root.parent;
		}
	}
	
	/**
	 * Private Method: resetValidity.
	 * Sets the validity of all nodes in the heap to be true.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void resetValidity() {
		Node n = null;
		
		for(int i = 0; i < elemCount; i++) {				// For each index,
			String binaryLocation = toBinaryString(i+1); 	// Get the binary location of the corresponding node
			n = getNode(binaryLocation);					// Find the node to change
			n.isValid = true;								// Change the node's validity to true
		}
		
	}
	
	private void setOppositeMode() {
		if(myMode == Heap.MODE.MAX) 	// If the mode is currently MAX,
			myMode = Heap.MODE.MIN;		// then turn to MIN.
		else							// If the mode is currently MIN,
			myMode = Heap.MODE.MAX;		// then turn to MAX.
	}
	
	/**
	 * Private Method: isEven.
	 * Returs true if the int passed is even. Returns false otherwise.
	 * @param n Number to test.
	 * @return boolean value
	 */
	private boolean isEven(int n) {
		if(n%2 == 0) return true;
		else return false;
	}

	/**
	 * Private Method: isOdd.
	 * Returs true if the int passed is odd. Returns false otherwise.
	 * @param n Number to test.
	 * @return boolean value
	 */
	private boolean isOdd(int n) {
		if(n%2 == 1) return true;
		else return false;
	}

	/**
	 * Private Method: toBinaryString.
	 * "To binary string" takes in an integer number that represents
	 * the node's number and converts it to binary. For example, the 5th 
	 * node of the heap from top to bottom, left to right, would be node 
	 * number 5. This node number corresponds to it's position. The binary
	 * value helps to determine the location of a node in the heap.
	 * @param position The node's number, i.e. the 5th node would have node number 5.
	 * @return The binary representation of a number.
	 */
	private String toBinaryString(int position) {
		String b = "";
		int dividend = position;
		int remainder = 0;
		int temp = 0;
		if(position == 0) {
			b = "0";
		} else {
			while(dividend > 0) {
				temp = dividend / 2;
				remainder = dividend % 2;
				if(remainder == 0) 
					b = "0" + b;
				else 
					b = "1" + b;		
				dividend = temp;
			}
		}	
		return b;
	}

	/**
	 * Private Method: getBinaryString.
	 * Accepts a string that represents the binary value of a node. This binary
	 * value helps to map the location of a node. EXAMPLE: I want to access the 5th
	 * node in the heap. 5 in binary is 101. Ignoring the first character, I have 
	 * the characters "0" and "1" left, in that order. 0 corresponds to a left child
	 * and 1 corresponds to a right child. Therefore, in order to access the 5th 
	 * node in the heap, I start at the root, move down left once (0) and down 
	 * right once (1). I am now at the 5th node in the heap.
	 * @param binaryString A string that represents the binary value of a number. 
	 * @return The node that I am looking for.
	 */
	@SuppressWarnings("rawtypes")
	private Node getNode(String binaryString) {
		resetRoot();
		for(int i = 1; i < binaryString.length(); i++) {
			String c = binaryString.substring(i, i+1);
			if(c.compareTo("0") == 0)
				root = root.left;
			else if(c.compareTo("1") == 0)
				root = root.right;	
		}
		return root;
	}
}
