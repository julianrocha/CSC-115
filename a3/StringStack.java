/*
* Name: Julian Rocha
* ID: V00870460
* Date: Sunday Oct. 22 2017
* Filename: StringStack.java
* Details: CSC115 Assignment 3
*/ 
public class StringStack {

	private Node head; //Node which points to the top of the stack

/**
* Public method that returns true if the stack is empty
* and false otherwise.
* @return head == null checks if head is pointing to a stack or not.
*/
	public boolean isEmpty() 
	{
		return head == null;
	}

/**
* Public method that removes and returns the head of the stack.
* Head becomes the next node.
* Throws exception if stack is empty.
* @return s String item that head initially points to.
*/
	public String pop() 
	{
		if(head == null)
		{
			throw new StackEmptyException("Stack is Empty.");
		}
		String s = head.item;
		head = head.next;
		return s;
	}

/**
* Public method that returns the head of the stack.
* Throws exception if stack is empty.
* @return head.item Item that head points to.
*/
	public String peek() 
	{
		if(head == null)
		{
			throw new StackEmptyException("Stack is Empty.");
		}
		return head.item;
	}

/**
* Public method that adds an item to the top of the stack
* Head becomes new item.
* @param item String item to be added to top of stack.
*/
	public void push(String item) 
	{
		Node N = new Node(item, head);
		head = N;
	}

/**
* Public method that removes all items from stack.
* Head now points to null.
*/
	public void popAll() 
	{
		head = null;
	}

/**
* Public method that returns a string form of the stack.
* @return list A printable string form of the stack.
*/
	public String toString()
	{
		String list = "{";//'list' will be returned at the end of the method
		if(!isEmpty())//execute for lists that are not empty
		{
			list += head.item;//adds head item to start of list String
			for(Node curr = head.next; curr != null; curr = curr.next)//iterates through entire list
			{
			list += ", " + curr.item;//concatenates comma, space, and current item to String
			}
		}
		list += "}";//ends list
		return list;//returns list
	}

//MAIN IS USED AS A CLASS TESTER HERE
	public static void main(String[] args)
	{
		StringStack TestStack = new StringStack();
		try
		{
			System.out.println(TestStack.peek());
		}
		catch(StackEmptyException e)
		{
			System.out.println("Peek on empty Stack exception avoided.");
		}
		try
		{
			System.out.println(TestStack.pop());
		}
		catch(StackEmptyException e)
		{
			System.out.println("Pop on empty Stack exception avoided.");
		}
		System.out.print(TestStack);
		System.out.println(TestStack.isEmpty());
		TestStack.push("Hello");
		System.out.print(TestStack);
		System.out.println(TestStack.isEmpty());
		TestStack.push("my");
		System.out.print(TestStack);
		System.out.println(TestStack.isEmpty());
		TestStack.push("name");
		System.out.print(TestStack);
		System.out.println(TestStack.isEmpty());
		TestStack.push("is");
		System.out.print(TestStack);
		System.out.println(TestStack.isEmpty());
		TestStack.push("Computer");
		System.out.print(TestStack);
		System.out.println(TestStack.isEmpty());
		TestStack.pop();
		System.out.print(TestStack);
		System.out.println(TestStack.peek());
		System.out.println(TestStack.isEmpty());
		TestStack.pop();
		System.out.print(TestStack);
		System.out.println(TestStack.peek());
		System.out.println(TestStack.isEmpty());
		TestStack.pop();
		System.out.print(TestStack);
		System.out.println(TestStack.peek());
		System.out.println(TestStack.isEmpty());
		TestStack.pop();
		System.out.print(TestStack);
		System.out.println(TestStack.peek());
		System.out.println(TestStack.isEmpty());
		TestStack.pop();
		System.out.print(TestStack);
		System.out.println(TestStack.isEmpty());

		TestStack.push("Computer");
		System.out.print(TestStack);
		System.out.println(TestStack.isEmpty());
		TestStack.push("is");
		System.out.print(TestStack);
		System.out.println(TestStack.isEmpty());
		TestStack.push("name");
		System.out.print(TestStack);
		System.out.println(TestStack.isEmpty());
		TestStack.push("my");
		System.out.print(TestStack);
		System.out.println(TestStack.isEmpty());
		TestStack.push("Hello");
		System.out.print(TestStack);
		System.out.println(TestStack.isEmpty());

		TestStack.popAll();
		System.out.print(TestStack);
		System.out.println(TestStack.isEmpty());		

		
		
	}
}
