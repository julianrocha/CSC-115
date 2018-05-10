/*
* Name: Julian Rocha
* ID: V00870460
* Date: 10:27AM Friday Sep. 19 2017
* Filename: MedListRefBased.java
* Details: CSC115 Assignment 2
*/ 

public class MedListRefBased implements List<Medication> 
{

	private MedicationNode head; //reference to start of list
	private MedicationNode tail; //reference to end of list
	private int count; //counter for number of items in list

	
/**
* Defualt constructor for MedListRefBased class.
* Creates an empty list.
* Sets head to null, tail to null and count to 0.
*/
	public MedListRefBased() //default constructor
	{
		head = null;
		tail = null;
		count = 0;
	}//end of constructor


//**********************************************************************************************************
//THE FOLLOWING PRIVATE METHODS HELP TO MORE EASILY UNDERSTAND AND IMPLEMENT THE ADDING AND REMOVING METHODS
//**********************************************************************************************************

/**
* Adds a Node to the head of a list of any size.
* A private method called by the add method.
* @param itemNode A node initially pointing to null
* containing the item passed in the add method.
*/
	private void addHead(MedicationNode itemNode)
	{
		if(count == 0) //add head to empty list
		{
			head = itemNode; //head points to new node
		}
		if(count == 1) //add head to a 1 item list
		{
			itemNode.next = head; 
			head.prev = itemNode;
			tail = head; //creates tail at head
			head = itemNode; //resets head to back of list
		}
		if(count > 1) //add head to list of length 2 or more
		{
			itemNode.next = head; 
			head.prev = itemNode;
			head = itemNode;
		}
		count++; //update count
	}

/**
* Adds a Node to the tail of a list of any size.
* A private method called by the add method.
* @param itemNode A node initially pointing to null
* containing the item passed in the add method.
*/
	private void addTail(MedicationNode itemNode)
	{
		if(count == 1) //add tail to a 1 item list
		{
			head.next = itemNode;
			itemNode.prev = head;
			tail = itemNode; //creates new tail at new node
		}
		if(count > 1) //add tail to list of length 2 or more
		{
			tail.next = itemNode;
			itemNode.prev = tail;
			tail = itemNode;
		}
		count++; //update count
	}

/**
* Adds a Node to the middle of a list of any size.
* A private method called by the add method.
* @param itemNode A node initially pointing to null
* containing the item passed in the add method.
* @param index The index where itemNode should be added.
*/
	private void addMiddle(MedicationNode itemNode, int index)
	{
		MedicationNode curr = null;
		if(index <= count / 2) //start at head for small index
		{
			curr = head;
			for(int i = 0; i < index - 1; i++) //this for loop will end with curr pointing to the index of interest
			{
				curr = curr.next;
			}
		}
		else if(index > count / 2) //start at tail for large index
		{
			curr = tail;
			for(int i = count - 1; i > index - 1; i--) //will end with curr pointing to index preceeding our index of interest
			{
				curr = curr.prev;
			}
		}
		curr.next.prev = itemNode;
		itemNode.next = curr.next;
		curr.next = itemNode;
		itemNode.prev = curr;
		count++; //update count
	}

/**
* Removes the head from a list of any size.
* A private method called by the remove method.
*/
	private void removeHead()
	{
		if(count == 1)//remove head of 1 item list
		{
			head = null;
		}
		if(count == 2)//remove head of 2 item list
		{
			head = tail;
			tail = null;
			head.prev = null;
			head.next = null;
		}
		if(count > 2) //remove head from list of length 3 or more
		{
			head = head.next;
			head.prev = null;
		}
		count--; //update count
	}

/**
* Removes the tail from a list of any size.
* A private method called by the remove method.
*/
	private void removeTail()
	{
		if(count == 2) //remove tail from 2 item list
		{
			tail = null;
			head.next = null;
		}
		if(count > 2) //remove tail from list of length 3 or more
		{
			tail = tail.prev;
			tail.next = null;
		}
		count--; //update count
	}

/**
* Removes a Node from an index of a list of any size.
* A private method called by the remove method.
* @param index The index where a Node should be removed.
*/
	private void removeMiddle(int index)
	{
		MedicationNode curr = head;
		for(int i = 0; i < index - 1; i++) //will stop with curr pointing to element before index
		{
			curr = curr.next;
		}
		curr.next = curr.next.next;
		curr.next.prev = curr;
		count--; //update count
	}

	
	//*******************************************************
	//END OF PRIVATE METHODS
	//*******************************************************	
	
	//The following methods have been implemented in accordance with the List Interface:

	public void add(Medication item, int index)
	{
		if(index > count || index < 0)//if index is less than 0 or greater than count, don't run
		{
			throw new IndexOutOfBoundsException("Index must be 0 - " + count + ", it was: " + index);
		}
	
		MedicationNode itemNode = new MedicationNode(item);//node to store item to be passed to adding methods
	
		if(index == 0)//add new head
		{
			addHead(itemNode);
		}
		else if(index == count)//add new tail
		{
			addTail(itemNode);
		}
		else if(index > 0 && index < count)//add somewhere in the middle of list
		{
			addMiddle(itemNode, index);
		}	
	}

	public Medication get(int index)
	{
		if(isEmpty())//if list is empty, don't run
		{
			throw new IndexOutOfBoundsException("This list is empty.");
		}
		if(index > (count - 1) || index < 0)//if index is less than 0 or greater than the highest index, don't run
		{
			throw new IndexOutOfBoundsException("Index must be 0 - " + (count - 1) + ", it was: " + index);
		}

		if(index == count - 1 && count > 1) //return item at tail
		{
			return tail.item;
		}

		if(index == 0)
		{
			return head.item;
		}	

		
		if(index <= count / 2)
		{
			int i = 0;	
			for(MedicationNode curr = head; curr != null; curr = curr.next) //this for loop will end with curr pointing to the index of interest
			{
				if(i == index)
				{
					return curr.item;
				}
				i++;
			}
		}
		
		else if(index > count / 2)
		{
			int i = count - 1;	
			for(MedicationNode curr = tail; curr != null; curr = curr.prev) //this for loop will end with curr pointing to the index of interest
			{
				if(i == index)
				{
					return curr.item;
				}
				i--;
			}
		}
		return null;	
	}

	public boolean isEmpty()
	{
		return head == null; //if head is null, then return true as the list is empty
	}

	public int size()
	{
		return count;
	}

	public int indexOf(Medication item)
	{
		MedicationNode curr = head; //curr cycles through Nodes and checks what their item is
		for(int i = 0; i < count; i++) //this for loop can iterate through the whole list
		{
			if(curr.item.equals(item)){	//checks if current item is the same as the one we passed
				return i;	//return the index of the item we found
			}
			curr = curr.next;	//advance the current pointer to next item
		}
		return -1;	//if item is not found, return -1
	}

	public void remove(int index)
	{
		if(isEmpty()) //if the list is empty, don't run
		{
			throw new IndexOutOfBoundsException("This list is empty.");
		}
		if(index > count - 1 || index < 0)//if index is less than 0 or greater than count, don't run
		{
			throw new IndexOutOfBoundsException("Index must be 0 - " + (count - 1) + ", it was: " + index);
		}

		if(index == 0) //if index is 0, we are removing the head
		{
			removeHead();
		}
		else if(index == (count - 1)) //if index is count - 1, we are removing the tail
		{
			removeTail();
		}
		else if(index > 0 && index < (count - 1)) //all index's in between means we are removing from the middle
		{
			removeMiddle(index);
		}
	}

	public void removeAll()
	{
		head = null; //list can no longer be found from head
		tail = null; //list can no longer be found from tail
		count = 0;	 //reset count to 0
	}

	public void remove(Medication value)
	{
		int index = 0; //keeps track of current index even after a removal
		for(MedicationNode curr = head; curr != null; curr = curr.next) //iterates trhough entire list
		{
			if(curr.item.equals(value))	//checks if current item is equal to the one we passed
			{
				remove(index);	//remove the item at that index
				index--;	//adjust index after removal
			}
			index++; //add to index when curr points to next node
		}
	}

	public String toString()
	{
		String list = "{";//'list' will be returned at the end of the method
		if(!isEmpty())//execute for lists that are not empty
		{
			list += head.item;//adds head item to start of list String
			for(MedicationNode curr = head.next; curr != null; curr = curr.next)//iterates through entire list
			{
			list += ", " + curr.item;//concatenates comma, space, and current item to String
			}
		}
		list += "}";//ends list
		return list;//returns list
	}

	public static void main(String[] args) 
	{
		//internal testing setup
		MedListRefBased list = new MedListRefBased();
		Medication m1 = new Medication("m1", 1);
		Medication m2 = new Medication("m2", 2);
		Medication m3 = new Medication("m3", 3);
		Medication m4 = new Medication("m4", 4);
		Medication m5 = new Medication("m5", 5);
		list.add(m1, 0);
		list.add(m2, 1);
		list.add(m3, 2);
		list.add(m4, 3);
		list.add(m5, 4);
		//internal testing begins
		System.out.println("Testing begins, inital list is: " + list);
		System.out.println("Is list empty? : " + list.isEmpty());
		System.out.println("Size of list? : " + list.size());
		//test the add and remove methods
		System.out.println("Testing remove and add methods: ");
		list.remove(0);
		System.out.println("Remove head m1: " + list);
		list.add(m1, 0);
		System.out.println("Add head m1: " + list);
		list.remove(4);
		System.out.println("Remove tail m5: " + list);		
		list.add(m5, 4);
		System.out.println("Add tail m5: " + list);
		list.remove(2);
		System.out.println("Remove middle m3: " + list);
		list.add(m3, 2);
		System.out.println("Add middle m3: " + list);
		System.out.println("Is list empty? Should be false : " + list.isEmpty());
		System.out.println("Size of list? Should be 5 : " + list.size());
		//test indexOf method
		System.out.println("Testing indexOf method:");
		System.out.println("Index of m1 should be 0: " + list.indexOf(m1));
		System.out.println("Index of m2 should be 1: " + list.indexOf(m2));
		System.out.println("Index of m3 should be 2: " + list.indexOf(m3));
		System.out.println("Index of m4 should be 3: " + list.indexOf(m4));
		System.out.println("Index of m5 should be 4: " + list.indexOf(m5));
		//test multiple adding and removing
		list.add(m5, 0);
		list.add(m5, 3);
		list.add(m5, 7);
		System.out.println("Adding m5 to head, middle and tail: " + list);
		list.remove(m5);
		System.out.println("Remove all instances of m5: " + list);
		System.out.println("Is list empty? Should be false : " + list.isEmpty());
		System.out.println("Size of list? Should be 4 : " + list.size());
		//test get method
		System.out.println("Testing get method: ");
		System.out.println("Item at index 0? Should be m1: " + list.get(0));
		System.out.println("Item at index 1? Should be m2: " + list.get(1));
		System.out.println("Item at index 2? Should be m3: " + list.get(2));
		System.out.println("Item at index 3? Should be m4: " + list.get(3));
		//test remove all method
		list.removeAll();
		System.out.println("Wipe the list: " + list);
		System.out.println("Is list empty? Should be true : " + list.isEmpty());
		System.out.println("Size of list? Should be 0 : " + list.size());
		//test exceptions on empty list
		System.out.println("Testing exceptions: ");
		try
		{
			list.remove(0);
			System.out.println("FAILURE");
		} catch(IndexOutOfBoundsException e)
		{
			System.out.println("Remove 0 exception SUCCESS");

		}
		try
		{
			list.get(0);
			System.out.println("FAILURE");
		} catch(IndexOutOfBoundsException e)
		{
			System.out.println("Get 0 exception SUCCESS");
		}
		try
		{
			list.add(m1, 1);
			System.out.println("FAILURE");
		} catch(IndexOutOfBoundsException e)
		{
			System.out.println("Add 1 exception SUCCESS");
		}
		//test excpetions on list of 1 item
		list.add(m1, 0);
		System.out.println("Added m1 to list: " + list);

		try
		{
			list.remove(1);
			System.out.println("FAILURE");
		} catch(IndexOutOfBoundsException e)
		{
			System.out.println("Remove 1 exception SUCCESS");
		}
		try
		{
			list.remove(2);
			System.out.println("FAILURE");
		} catch(IndexOutOfBoundsException e)
		{
			System.out.println("Remove 2 exception SUCCESS");
		}
		
		try
		{
			list.get(1);
			System.out.println("FAILURE");
		} catch(IndexOutOfBoundsException e)
		{
			System.out.println("Get 1 exception SUCCESS");
		}
		try
		{
			list.get(2);
			System.out.println("FAILURE");
		} catch(IndexOutOfBoundsException e)
		{
			System.out.println("Get 2 exception SUCCESS");
		}
		try
		{
			list.add(m1, -1);
			System.out.println("FAILURE");
		} catch(IndexOutOfBoundsException e)
		{
			System.out.println("Add -1 exception SUCCESS");
		}
		//END OF TESTING
		System.out.println("End of testing...");
	}
}//end of class