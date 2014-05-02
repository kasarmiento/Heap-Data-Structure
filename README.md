

Node Heap
=========

My implementation of a heap using nodes. Designed to function as a tree graph.

Written by Khamille A. Sarmiento
for CS 241: Data Structures and Algorithms II
Professor: Edwin Rodr&iacute;guez


##Assignment Description 
======================

The assignment calls for the implementation of a heap using nodes and links instead of the traditional array implementation. The methods that were inherited from the Heap interface were the following: 

. void add()
. V remove()
. V[] toArray()
. void fromArray(V[])
. V[] getSortedContents()
. MODE getMode()
. void setMode()

The assignment also requires the implementation of helper methods like siftUp, siftDown, and heapify.

Implementations for add, remove, siftUp and siftDown should have a worse case complexity of log N, and implementation for heapify should have a worse case complexity of N log N.

This heap maintains its heap property at the end of
every function call.


##My Approach
===========

In order to find a desired node, I used binary conversions to help me map the path that I need to take in this essentially complete binary tree. I was very happy to discover an efficient and intuitive way of finding a particular node. If you read the documentation for my code, I have explained how my binary conversions work and how it can help me find any node I need to.

In order to implement the algorithm Heap Sort using nodes instead of arrays was difficult, however I was able to figure it out. The main challenge was to determine which nodes were no longer "valid" to be switched with during the siftDown call. In the array implementation, this is easier because after an element is swapped and "removed", I could ignore it by travesing only until the index before it. In order to mimic this using Nodes, I added a new boolean field in my Node class called "isValid" and set it to false when I didn't want it to be a part of my siftDown function.


##Conclusions and lessons learned
===============================

I learned that programming can be fun, especially when I understand the problem clearly. But more importantly, I learned that there is a pattern that can be performed to solve every problem. The challenge is to find that pattern. 

A new thing that I learned was the use of enumerations, more specifically, how to use/set modes. This functionality is very useful because it allows my code to be more versatile and to solve more problems.

From what I have tested, I am happy that my code is working stably. I am looking forward to our next project.
