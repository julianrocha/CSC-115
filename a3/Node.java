/*
* Name: Julian Rocha
* ID: V00870460
* Date: Sunday Oct. 22 2017
* Filename: Node.java
* Details: CSC115 Assignment 3
*/ 

public class Node 
{
	protected Node next; //Next node in a singly linked list
	protected String item; //String item stored in each Node

//This constructor has an item and next parameter
	public Node(String item, Node next)
	{
		this.item = item;
		this.next = next;
	}
//This constructor only has an item parameter
	public Node(String item)
	{
		this.item = item;
		this.next = null;
	}


}