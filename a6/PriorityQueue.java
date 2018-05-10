/*
* Name: Julian Rocha
* ID: V00870460
* Date: Nov 28 2017
* Filename: PriorityQueue.java
* Details: CSC115 Assignment 6
*/ 
import java.util.NoSuchElementException;

public class PriorityQueue<E extends Comparable<E>> {
	
	private Heap<E>  heap;//min heap to store priority queue

	//default constructor
	public PriorityQueue(){
 		heap = new Heap();//initialize empty heap
	}

	/**
	* Inserts an item into the priority queue
	* @param item to be added to priority queue
	*/
	public void enqueue(E item){
		heap.insert(item);
	}
 	
 	/**
 	* Removes and returns the item of highest priority from the queue
 	* @return item of highest priority (root of min heap)
 	* @throws NoSuchElementException if the queue is empty.
 	*/
 	public E dequeue(){
 		if(isEmpty())
 			throw new NoSuchElementException("The queue is empty.");
 		return heap.removeRootItem();
 	}

 	/**
 	* Returns the item of highest priority from the queue
 	* @return item of highest priority (root of min heap)
 	* @throws NoSuchElementException if the queue is empty.
 	*/
 	public E peek(){
 		if(isEmpty())
 			throw new NoSuchElementException("The queue is empty.");
 		return heap.getRootItem();
 	}

 	/**
 	* Determines if the queue is empty.
 	* @return true if queue is empty, false otherwise.
 	*/
 	public boolean isEmpty(){
 		return heap.isEmpty();
 	}

 	/**
 	* Returns a String form of the queue, preserving its order.
 	* @return String for of the queue.
 	*/
 	public String toString(){
 		return heap.toString();
 	}

 	//main is used for testing
 	public static void main(String[] args) throws InterruptedException {
	 		PriorityQueue priorityList = new PriorityQueue();
	 		
	 		ER_Patient patientA = new ER_Patient("Life-threatening");
	 		Thread.sleep(1000);
	 		ER_Patient patientB = new ER_Patient("Chronic");
	 		Thread.sleep(1000);
	 		ER_Patient patientC = new ER_Patient("Major fracture");
	 		Thread.sleep(1000);
	 		ER_Patient patientD = new ER_Patient("Walk-in");
	 		Thread.sleep(1000);
	 		ER_Patient patientA2 = new ER_Patient("Life-threatening");
	 		Thread.sleep(1000);
	 		ER_Patient patientB2 = new ER_Patient("Chronic");
	 		Thread.sleep(1000);
	 		ER_Patient patientC2 = new ER_Patient("Major fracture");
	 		Thread.sleep(1000);
	 		ER_Patient patientD2 = new ER_Patient("Walk-in");

	 		System.out.println("Created new PriorityQueue.");
	 		System.out.println("Empty = " + priorityList.isEmpty());


	 		//testing enqueue/peek/isEmpty by adding in random order
	 		priorityList.enqueue(patientB2);
	 		System.out.println("Next in line: " + priorityList.peek());
	 		System.out.println("Empty = " + priorityList.isEmpty());
	 		priorityList.enqueue(patientD);
	 		System.out.println("Next in line: " + priorityList.peek());
	 		System.out.println("Empty = " + priorityList.isEmpty());
	 		priorityList.enqueue(patientC);
	 		System.out.println("Next in line: " + priorityList.peek());
	 		System.out.println("Empty = " + priorityList.isEmpty());
	 		priorityList.enqueue(patientB);
	 		System.out.println("Next in line: " + priorityList.peek());
	 		System.out.println("Empty = " + priorityList.isEmpty());
	 		priorityList.enqueue(patientA2);
	 		System.out.println("Next in line: " + priorityList.peek());
	 		System.out.println("Empty = " + priorityList.isEmpty());
	 		priorityList.enqueue(patientD2);
	 		System.out.println("Next in line: " + priorityList.peek());
	 		System.out.println("Empty = " + priorityList.isEmpty());
	 		priorityList.enqueue(patientC2);
	 		System.out.println("Next in line: " + priorityList.peek());
	 		System.out.println("Empty = " + priorityList.isEmpty());
	 		priorityList.enqueue(patientA);
	 		System.out.println("Next in line: " + priorityList.peek());
	 		System.out.println("Empty = " + priorityList.isEmpty());


	 		//testing dequeue
	 		System.out.println("Printing patients in order: ");
	 		System.out.println(priorityList.dequeue());
	 		System.out.println(priorityList.dequeue());
	 		System.out.println(priorityList.dequeue());
	 		System.out.println(priorityList.dequeue());
	 		System.out.println(priorityList.dequeue());
	 		System.out.println(priorityList.dequeue());
	 		System.out.println(priorityList.dequeue());
	 		System.out.println(priorityList.dequeue());

	 		//testing exceptions
			System.out.println("Testing Exceptions on empty queue:");
			try{
				priorityList.peek();
			}
			catch(NoSuchElementException E){
				System.out.println("Succesfully caught peek on empty queue exception.");
			}
			try{
				priorityList.dequeue();	
			}
			catch(NoSuchElementException E){
				System.out.println("Succesfully caught dequeue on empty queue exception.");
			}


 	}
			
}