/*
* Name: Julian Rocha
* ID: V00870460
* Date: Nov. 1st 2017
* Filename: maze.java
* Details: CSC115 Assignment 4
*/
public class CellDeque
{
	private CellNode head;//reference to start of deque
	private CellNode tail;//reference to end of deque

//Default constructor
	public CellDeque()
	{
		head = null;
		tail = null;
	}

/**
* Returns Cell item at the start of the deque
* @return Cell at the start of the deque
*/
	public Cell first()
	{
		if(isEmpty())
		{
			throw new DequeEmptyException("The CellDeque is empty.");
		}
		return head.item;
	}

/**
* Returns Cell item at the end of the deque
* @return Cell at the end of the deque
*/
	public Cell last()
	{
		if(isEmpty())
		{
			throw new DequeEmptyException("The CellDeque is empty.");
		}
		if(tail == null)
		{
			return head.item;
		}
		return tail.item;
	}

//Returns true if deque is empty
	public boolean isEmpty()
	{
		return head == null;
	}

//Empties deque
	public void makeEmpty()
	{
		head = null;
		tail = null;
	}

/**
* Inserts Cell item to start of deque
* @param p Cell to be added to deque
*/
	public void insertFirst(Cell p)
	{
		CellNode n = new CellNode(p); //put Cell p in a CellNode n

		if(isEmpty())
		{
			n.next = head;
			head = n;
			return;
		}
		n.next = head;
		head = n;
		if(tail == null)
		{
			tail = head.next;
			tail.prev = head;
		}
	}

/**
* Inserts Cell item to end of deque
* @param p Cell to be added to deque
*/
	public void insertLast(Cell p)
	{
		CellNode n = new CellNode(p);//put Cell p in a CellNode n
		if(isEmpty())
		{
			head = n;
			return;
		}
		if(tail == null)
		{
			head.next = n;
			n.prev = head;
			tail = n;
			return;
		}
		tail.next = n;
		n.prev = tail;
		tail = n;
	}

/**
* Removes and returns item at start of deque.
* @throws DequeEmptyException if deque is empty
* @return Cell contained in CellNode at start of deque
*/
	public Cell removeFirst()
	{
		if(isEmpty())
		{
			throw new DequeEmptyException("The CellDeque is empty.");
		}
		CellNode item = new CellNode(head.item);
		if(tail == null)
		{
			head = null;
			return item.item;
		}
		if(head.next == tail)
		{
			head = tail;
			tail = null;
			head.prev = null;
			return item.item;
		}
		head = head.next;
		head.prev = null;
		return item.item;
	}

/**
* Removes and returns item at end of deque.
* @throws DequeEmptyException if deque is empty
* @return Cell contained in CellNode at end of deque
*/
	public Cell removeLast()
	{
		if(isEmpty())
		{
			throw new DequeEmptyException("The CellDeque is empty.");
		}
		CellNode item = new CellNode(head.item); //
		if(tail == null)
		{
			head = null;
			return item.item;
		}
		item.item = tail.item;
		if(head.next == tail)
		{
			tail = null;
			head.next = null;
			return item.item;
		}
		tail = tail.prev;
		tail.next = null;
		return item.item;
	}

/**
* Public method that returns a string form of the deque.
* @return list A printable string form of the deque.
*/
	public String toString()
	{
		String list = "{";//'list' will be returned at the end of the method
		if(!isEmpty())//execute for lists that are not empty
		{
			list += head.item;//adds head item to start of list String
			for(CellNode curr = head.next; curr != null; curr = curr.next)//iterates through entire list
			{
			list += ", " + curr.item;//concatenates comma, space, and current item to String
			}
		}
		list += "}";//ends list
		return list;//returns list
	}

//MAIN IS USED AS A CLASS TESTER//
public static void main(String[] args)
{

CellDeque list = new CellDeque();

Cell a = new Cell(1,2);
Cell b = new Cell(3,4);
Cell c = new Cell(5,6);
Cell d = new Cell(7,8);
Cell e = new Cell(9,10);

//testing exceptions
try
{
	list.first();
}
catch(DequeEmptyException f)
{
	System.out.println("Empty deque head peek excpetion caught.");
}
try
{
	list.last();
}
catch(DequeEmptyException f)
{
	System.out.println("Empty deque tail peek excpetion caught.");
}
try
{
	list.removeFirst();
}
catch(DequeEmptyException f)
{
	System.out.println("Empty deque head remove excpetion caught.");
}
try
{
	list.removeLast();
}
catch(DequeEmptyException f)
{
	System.out.println("Empty deque tail remove excpetion caught.");
}


//testing insertFirst()
System.out.print(list);
System.out.println(list.isEmpty());
list.insertFirst(a);
System.out.print(list);
System.out.println(list.isEmpty());
System.out.print(list.first());
System.out.println(list.last());
list.insertFirst(b);
System.out.print(list);
System.out.println(list.isEmpty());
System.out.print(list.first());
System.out.println(list.last());
list.insertFirst(c);
System.out.print(list);
System.out.println(list.isEmpty());
System.out.print(list.first());
System.out.println(list.last());
list.insertFirst(d);
System.out.print(list);
System.out.println(list.isEmpty());
System.out.print(list.first());
System.out.println(list.last());
list.insertFirst(e);
System.out.print(list);
System.out.println(list.isEmpty());
System.out.print(list.first());
System.out.println(list.last());

//testing removeFirst()
list.removeFirst();
System.out.print(list);
System.out.println(list.isEmpty());
System.out.print(list.first());
System.out.println(list.last());
list.removeFirst();
System.out.print(list);
System.out.println(list.isEmpty());
System.out.print(list.first());
System.out.println(list.last());
list.removeFirst();
System.out.print(list);
System.out.println(list.isEmpty());
System.out.print(list.first());
System.out.println(list.last());
list.removeFirst();
System.out.print(list);
System.out.println(list.isEmpty());
System.out.print(list.first());
System.out.println(list.last());
list.removeFirst();
System.out.print(list);
System.out.println(list.isEmpty());

//testing insertLast()
list.insertLast(a);
System.out.print(list);
System.out.println(list.isEmpty());
System.out.print(list.first());
System.out.println(list.last());
list.insertLast(b);
System.out.print(list);
System.out.println(list.isEmpty());
System.out.print(list.first());
System.out.println(list.last());
list.insertLast(c);
System.out.print(list);
System.out.println(list.isEmpty());
System.out.print(list.first());
System.out.println(list.last());
list.insertLast(d);
System.out.print(list);
System.out.println(list.isEmpty());
System.out.print(list.first());
System.out.println(list.last());
list.insertLast(e);
System.out.print(list);
System.out.println(list.isEmpty());
System.out.print(list.first());
System.out.println(list.last());

//testing removeLast()
list.removeLast();
System.out.print(list);
System.out.println(list.isEmpty());
System.out.print(list.first());
System.out.println(list.last());
list.removeLast();
System.out.print(list);
System.out.println(list.isEmpty());
System.out.print(list.first());
System.out.println(list.last());
list.removeLast();
System.out.print(list);
System.out.println(list.isEmpty());
System.out.print(list.first());
System.out.println(list.last());
list.removeLast();
System.out.print(list);
System.out.println(list.isEmpty());
System.out.print(list.first());
System.out.println(list.last());
list.removeLast();
System.out.print(list);
System.out.println(list.isEmpty());

//testing removeAll()
list.insertFirst(a);
list.insertFirst(a);
list.insertFirst(a);
list.insertFirst(a);
list.insertFirst(a);
System.out.print(list);
System.out.println(list.isEmpty());
System.out.print(list.first());
System.out.println(list.last());
list.makeEmpty();
System.out.print(list);
System.out.println(list.isEmpty());

}//end of main

}//end of class