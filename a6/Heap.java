/*
* Name: Julian Rocha
* ID: V00870460
* Date: Nov 28 2017
* Filename: Heap.java
* Details: CSC115 Assignment 6
*/ 
import java.util.NoSuchElementException;
import java.util.Vector;

public class Heap<E extends Comparable<E>>{

	private Vector<E> heapArray; //Vector for heap storage (min heap)
	private final int INITIAL_SIZE = 1; //size of heapArray when created
	private final int VECTOR_INCREMENT = 1; //increment for heapArray when more space is needed

	//default constructor
	public Heap(){
 		heapArray = new Vector<E>(INITIAL_SIZE, VECTOR_INCREMENT);
	}

	/**
	* Determines if a heap object is empty.
	* @return True if the heap is empty, false if not.
	*/
	public boolean isEmpty(){
		return heapArray.isEmpty();
	}

	/**
	* Determines the number of items in a heap.
	* @return An int of the number of items in the heap.
	*/
	public int size(){
		return heapArray.size();
	}

	/**
	* Inserts an item into a heap.
	* Adds initialy to bottom leftmost spot available (to preserve completeness of tree)
	* Makes use of recursive bubbleUp to preserve min heap order.
	* @param item The newly added item.
	*/
	public void insert(E item){
		heapArray.add(item);//add item to end of heapArray,
		//leftmost spot available to preserve completeness of tree
		bubbleUp(size() - 1); //bubble the added item up if needed,
		//preserve min heap
	}

	/**
	* Recursive method used after insert to preserve min heap order.
	* Replaces item at passed index with its parent if parent is greater.
	* @param index of item in heapArray to be bubbled up.
	* @throws NoSuchElementException if heap is empty. 
	* @throws NoSuchElementException if the passed index is not in range of heapArray.
	*/
	private void bubbleUp(int index){
		if(heapArray.isEmpty())
			throw new NoSuchElementException("The heap is empty.");
		if(index < 0 || index >= size())
			throw new NoSuchElementException("The passed index was out of range.");
		int parentIndex = ((index + 1) / 2) - 1; //Index of parent of passed index
		E child = heapArray.get(index); //item found at passed index
		if(index == 0 || child.compareTo(heapArray.get(parentIndex)) >= 0)
			return; //if index has no parent or parent is smaller than child, end bubbleUp
		//otherwise, child is smaller than its parent
		heapArray.set(index, heapArray.get(parentIndex));
		heapArray.set(parentIndex, child); //switch parent with child
		bubbleUp(parentIndex); //call bubbleUp on parent, in case item still needs to move up 
	}//end of bubbleUp

	/**
	* Removes and returns the root item (smallest item in min heap)
	* Initially replaces root with rightmost item on lowest level. (to preserve tree completeness)
	* Makes use of recursive bubbleDown to preserve min heap order.
	* @return item at root of heap.
	* @throws NoSuchElementException if heap is empty
	*/
	public E removeRootItem(){
		if(isEmpty())
			throw new NoSuchElementException("The heap is empty.");
		if(size() == 1)//if there is only 1 item in heap, remove and return
			return heapArray.remove(0);
		E temp = heapArray.get(0); //save root item to be returned later
		heapArray.set(0, heapArray.remove(size() - 1));//remove last item and set to root
		bubbleDown(0);//bubble down root to preserve min heap
		return temp; //return original root item
	}


	/**
	* Recursive method used after remove to preserve min heap order.
	* Replaces item at passed index with its smallest child.
	* @param index of item in heapArray to be bubbled down.
	* @throws NoSuchElementException if heap is empty.
	* @throws NoSuchElementException if the passed index is negative.
	*/
	private void bubbleDown(int index){
		if(heapArray.isEmpty())
			throw new NoSuchElementException("The heap is empty.");
		if(index < 0)
			throw new NoSuchElementException("The passed index was negative.");
		if(index + 1 > size() / 2)//if index is on bottom level or is larger than size of heap
			return; //index has no children, end bubbleDown
		
		int childIndexRight = index * 2 + 2;//index of right child
		boolean childRightExist = childIndexRight < size();//true if right child exists, false if not
		E rightChild = null;//declare variable to hold right child item
		if(childRightExist)//if right child exists, initialize rightChild
			rightChild = heapArray.get(childIndexRight);
		
		int childIndexLeft = index * 2 + 1;//index of left child
		boolean childLeftExist = childIndexLeft < size();//true if left child exists, false if not
		E leftChild = null;//declare variable to hold left child item
		if(childLeftExist)//if left child exists, initialize leftChild
			leftChild = heapArray.get(childIndexLeft);
		
		E parent = heapArray.get(index);//item stored at index
		
		if(childLeftExist && childRightExist){//if index has 2 children
			if(leftChild.compareTo(rightChild) <= 0 && parent.compareTo(leftChild) > 0){
				//if left child is smaller than right AND left child is smaller than parent
				heapArray.set(index, leftChild);//switch parent and left child
				heapArray.set(childIndexLeft, parent);
				bubbleDown(childIndexLeft);//recursive call on left child if item must move down further
			}
			else if(parent.compareTo(rightChild) > 0){
				//if right child is smaller than parent AND right child is smaller than left child
				heapArray.set(index, rightChild);//switch parent and right child
				heapArray.set(childIndexRight, parent);
				bubbleDown(childIndexRight);//recursive call on right child if item must move down further
			}
		}//end of 2 children condition
		else if(childLeftExist //if index has only left child
			&& parent.compareTo(leftChild) > 0){//AND left child is smaller than index
				heapArray.set(index, leftChild);//switch parent and child
				heapArray.set(childIndexLeft, parent);
				bubbleDown(childIndexLeft);//recursive call on left child if item must move down further
		}//end of left child only condition
		else if(childRightExist //if index has only right child
			&& parent.compareTo(rightChild) > 0){//AND right child is smaller than index
				heapArray.set(index, rightChild);//switch parent and child
				heapArray.set(childIndexRight, parent);
				bubbleDown(childIndexRight);//recursive call on right child if item must move down further
		}//end of right child only condition
	}//end of bubbleDown

	/**
	* Returns the root item (smallest item in min heap), without removing
	* @return item at root of heap.
	* @throws NoSuchElementException if heap is empty
	*/
	public E getRootItem(){
		if(isEmpty())
			throw new NoSuchElementException("The heap is empty.");
		return heapArray.get(0);//return root item if heap is not empty
	}
	/**
	* Returns a String form of heapArray
	* Preserves the same order as heapArray
	* @return heap a String form of heapArray
	*/
	public String toString(){
		String heap = new String();
		heap = "{";
		if(!isEmpty())
			heap += heapArray.get(0);
		for(int i = 1; i < size(); i++){
			heap += ", " + heapArray.get(i); 
		}
		heap += "}";
		return heap;
	}
	//Main is used for internal testing here
	public static void main(String[] args){
		Heap heap = new Heap();
		System.out.println("Created new empty heap: " + heap);
		System.out.print("Is heap empty?: " + heap.isEmpty());
		System.out.println(" Size = " + heap.size());
		int a = 0;
		int b = 1;
		int c = 2;
		int d = 3;
		int e = 4;
		int f = 5;
		int g = 6;

		//TESTING insert, empty, size, getRoot  and toString methods
		heap.insert(g);
		System.out.println("Inserting g = 6: " + heap);
		System.out.print("Empty = " + heap.isEmpty());
		System.out.print(" Size = " + heap.size());
		System.out.println(" Root = " + heap.getRootItem());
		heap.insert(f);
		System.out.println("Inserting f = 5: " + heap);
		System.out.print("Empty = " + heap.isEmpty());
		System.out.print(" Size = " + heap.size());
		System.out.println(" Root = " + heap.getRootItem());
		heap.insert(e);
		System.out.println("Inserting e = 4: " + heap);
		System.out.print("Empty = " + heap.isEmpty());
		System.out.print(" Size = " + heap.size());
		System.out.println(" Root = " + heap.getRootItem());
		heap.insert(d);
		System.out.println("Inserting d = 3: " + heap);
		System.out.print("Empty = " + heap.isEmpty());
		System.out.print(" Size = " + heap.size());
		System.out.println(" Root = " + heap.getRootItem());
		heap.insert(c);
		System.out.println("Inserting c = 2: " + heap);
		System.out.print("Empty = " + heap.isEmpty());
		System.out.print(" Size = " + heap.size());
		System.out.println(" Root = " + heap.getRootItem());
		heap.insert(b);
		System.out.println("Inserting b = 1: " + heap);
		System.out.print("Empty = " + heap.isEmpty());
		System.out.print(" Size = " + heap.size());
		System.out.println(" Root = " + heap.getRootItem());
		heap.insert(a);
		System.out.println("Inserting a = 0: " + heap);
		System.out.print("Empty = " + heap.isEmpty());
		System.out.print(" Size = " + heap.size());
		System.out.println(" Root = " + heap.getRootItem());

		//Testing remove methods
		System.out.println("Remove root = " + heap.removeRootItem() + ": " + heap);
		System.out.print("Empty = " + heap.isEmpty());
		System.out.print(" Size = " + heap.size());
		System.out.println(" Root = " + heap.getRootItem());

		System.out.println("Remove root = " + heap.removeRootItem() + ": " + heap);
		System.out.print("Empty = " + heap.isEmpty());
		System.out.print(" Size = " + heap.size());
		System.out.println(" Root = " + heap.getRootItem());

		System.out.println("Remove root = " + heap.removeRootItem() + ": " + heap);
		System.out.print("Empty = " + heap.isEmpty());
		System.out.print(" Size = " + heap.size());
		System.out.println(" Root = " + heap.getRootItem());

		System.out.println("Remove root = " + heap.removeRootItem() + ": " + heap);
		System.out.print("Empty = " + heap.isEmpty());
		System.out.print(" Size = " + heap.size());
		System.out.println(" Root = " + heap.getRootItem());

		System.out.println("Remove root = " + heap.removeRootItem() + ": " + heap);
		System.out.print("Empty = " + heap.isEmpty());
		System.out.print(" Size = " + heap.size());
		System.out.println(" Root = " + heap.getRootItem());

		System.out.println("Remove root = " + heap.removeRootItem() + ": " + heap);
		System.out.print("Empty = " + heap.isEmpty());
		System.out.print(" Size = " + heap.size());
		System.out.println(" Root = " + heap.getRootItem());

		System.out.println("Remove root = " + heap.removeRootItem() + ": " + heap);
		System.out.print("Empty = " + heap.isEmpty());
		System.out.println(" Size = " + heap.size());

		//testing exceptions
		System.out.println("Testing Exceptions on empty heap:");
		try{
			heap.getRootItem();
		}
		catch(NoSuchElementException E){
			System.out.println("Succesfully caught getRootItem on empty heap exception.");
		}
		try{
			heap.removeRootItem();
		}
		catch(NoSuchElementException E){
			System.out.println("Succesfully caught removeRootItem on empty heap exception.");
		}
	}

}