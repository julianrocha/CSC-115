/*
* Name: Julian Rocha
* ID: V00870460
* Date: 8:02PM Saturday Sep. 16 2017
* Filename: BingoCalls.java
* Details: CSC115 Assignment 1
*/ 

public class BingoCalls
{
	
	private BingoBall[] callList;//BingoBall array to store the called BingoBalls
	private int numBallsCalled;//Integer to store the total number of balls called

/**
* This constructor creates an empty BingoBall array to
* store the called balls as well an int value of the total called balls.
*/
	public BingoCalls()
	{
		numBallsCalled = 0;//total called balls starts at 0
		callList = new BingoBall[5];//Initial length of callList is 5
	}//end of BingoCalls constructor

/**
* This private method is called by insert() and doubles the length
* of callList by copying its contents to a new array: callList2.
*/
	private void resizeArray()
	{
		BingoBall callList2[] = new BingoBall[callList.length * 2];//creates callList2: a new empty BingoBall array twice as long.
		
		for(int i = 0; i < callList.length; i++)//for loop to copy contents of callList to callList2.
		{
			if(callList[i] == null)//if statement to break out of for loop if a null element is reached. See remove method for null info.
			{
				break;
			}//end of if statement
			callList2[i] = callList[i];//copies each element over.
		}//end of for loop
		callList = callList2;//after copying contents, callList will now reference callList2.
	}//end of resizeArray method

/**
* This method inserts a passed BingoBall into 
* the callList array and updates the total called balls.
* The index used in the callList array is equal to the current total balls called.
* If there is no room in callList, then insert will call resizeArray() and retry.
* If the ball already exists in callList, an error message
* will appear telling the user to try a different ball.
* @param ball The passed BingoBall to be inserted into callList.
*/
	public void insert(BingoBall ball)
	{
		if(wasCalled(ball))//checks to make sure ball doesn't already exist in callList.
		{
			throw new IllegalArgumentException(ball + " was already called, please insert a different ball.");//error message if ball was already called.
		}
		else{//runs if the ball is not already in callList.
			if(numBallsCalled < callList.length)//checks to see if there is room in callList for a new ball.
			{
				callList[numBallsCalled] = ball;//inserts new ball directly after the last ball.
				numBallsCalled++;//updates numBallsCalled.
			}//end of if statement
			else//will run if the current callList is full of balls.
			{
				resizeArray();//doubles current callList length.
				insert(ball);//retry insert method.
			}//end of else statement
		}//end of else statement
	}//end of insert method

/**
* This method removes a passed ball from callList, 
* shifts the remaining balls down to remove the gap, 
* and updates the total called balls.
* Nothing will happen if the passed ball isn't found in callList.
* @param ball The passed BingoBall which we wish to remove from callList.
*/
	public void remove(BingoBall ball)
	{
		int index = 0;//local variable to keep track of place in callList.
		for(int i = 0; i < callList.length; i++)//for loop to search callList for ball.
		{
			index = i;//update index to keep track of place in callList.
			if(ball.equals(callList[i]))//checks if current element is the ball we want to remove.
			{
				callList[i] = null;//removes ball from callList.
				numBallsCalled--;//reduces total called balls by one.
				break;//stop searching callList and break out of for loop
			}//end of if statement
		}
		if(index < callList.length - 1)//this if statement will not run if the removed ball was located at the end of the called balls or the ball could not be found.
		{
			/*
			This for loop will continue iterating through callList. It starts
			from the removed ball index and ends at the second to last element.
			Its purpose is to remove the "null" gap made by removing a ball from
			callList that is not located at the end of the array. The elements 
			following the removed ball are all shifted one index down and the
			last index is set to null.
			*/
			for(int i = index; i < numBallsCalled; i++)
			{
				callList[i] = callList[i + 1];//shifting balls one index down.
			}//end of for loop
			callList[callList.length - 1] = null;//setting last index to null.
		}//end of if statement
	}//end of remove method

/**
* This method empties the callList by creating 
* a new empty array and updating the callList reference.
* It also resets the total called balls to zero.
*/
	public void makeEmpty()
	{
	BingoBall callList2[] = new BingoBall[5];//creates callList2: a new empty BingoBall array of size 5.
	callList = callList2;//this line makes callList now reference callList2.
	numBallsCalled = 0;//reset the total called balls to 0.
	}//end of makeEmpty method

/**
* This method returns the current total called balls in callList.
* @return numBallsCalled Integer representing total BingoBall's called.
*/
	public int numBallsCalled()
	{
		return numBallsCalled;
	}//end of numBallsCalled method

/**
* This method checks to see if the passed 
* BingoBall was already inserted into callList.
* It makes use of the euqals() method from BingoBall.
* @param ball The BingoBall which will be compared with all others in callList.
* @return boolean True if ball was called, false if ball was not. 
*/
	public boolean wasCalled(BingoBall ball)
	{
		for(int i = 0; i < numBallsCalled; i++)//for loop to search through callList.
		{
			if(ball.equals(callList[i]))//checks if the current callList ball is the same as the ball we passed.
			{
				return true;//if they are equal, wasCalled returns true.
			}//end of if statement
		}//end of for loop
		return false;//if the ball couldn't be matched to any element in callList, wasCalled returns false.
	}//end of wasCalled method

/**
* This method empties the callList by creating a 
* new empty array and updating the callList reference.
* It also resets the total called balls to zero.
*/
	public java.lang.String toString()
	{
		String List = "{";//List is a string to represent callList, it starts with '{' and will be returned at the end of the method.
		if(callList[0] != null)//if the first element of callList is null then the array is empty and the for loop is skipped.
		{
			List += callList[0];//the fist ball of callList is concatenated to List.
			for(int i = 1; i < numBallsCalled; i++)//this for loop searches through callList from the second ball to the end of callList.
			{
				List += ", " + callList[i];//concatenates a comma, space, and the current ball in callList to List.
			}//end of for loop
		}//end of if statement
		List += "}";//concatenates a closed bracket to List, List is now complete.
		return List;
	}//end of toString method

/*Main is used to test the BingoCalls methods.
*/
	public static void main(String[] args) 
	{
		
		//The following will test the BingoBall class methods:
		BingoBall b = new BingoBall(35);
		System.out.println("Created a BingoBall: "+b);
		System.out.println("The number is "+b.getValue());
		System.out.println("The letter is "+b.getLetter());
		BingoBall c = null;
		try 
		{
			c = new BingoBall(76);
		}//end of try
		catch (Exception e) 
		{
			System.out.println("Correctly caught the exception");
		}//end of catch
		c = new BingoBall(14);
		System.out.println("Created a second BingoBall: "+c);
		if (!b.equals(c)) 
		{
			System.out.println("The two balls are not equivalent");
		}//end of if statement
		c.setValue(35);
		System.out.println("The second ball has been changed to "+c);
		if (b.equals(c)) 
		{
			System.out.println("They are now equivalent");
		}//end of if statement
		c.setValue(74);
		System.out.println("The second bingo ball has been changed to "+c);


		//The following will test the BingoCalls class methods:
		BingoBall B1 = new BingoBall(1);
		BingoBall B2 = new BingoBall(2);
		BingoBall B3 = new BingoBall(3);
		BingoBall B4 = new BingoBall(4);
		BingoBall B5 = new BingoBall(5);
		BingoBall B6 = new BingoBall(6);
		BingoCalls BingoList = new BingoCalls();


		System.out.println("Empty List: " + BingoList);
		System.out.println(BingoList.numBallsCalled());
		BingoList.remove(B1);
		System.out.println("Remove B1: " + BingoList);
		System.out.println(BingoList.numBallsCalled());

		BingoList.insert(B1);
		System.out.println("Add B1: " + BingoList);
		System.out.println(BingoList.numBallsCalled());
		BingoList.insert(B2);
		System.out.println("Add B2: " + BingoList);
		System.out.println(BingoList.numBallsCalled());
		BingoList.insert(B3);
		System.out.println("Add B3: " + BingoList);
		System.out.println(BingoList.numBallsCalled());
		BingoList.insert(B4);
		System.out.println("Add B4: " + BingoList);
		System.out.println(BingoList.numBallsCalled());
		BingoList.insert(B5);
		System.out.println("Add B5: " + BingoList);
		System.out.println(BingoList.numBallsCalled());
		
		BingoList.remove(B5);
		System.out.println("Remove B5: " + BingoList);
		System.out.println(BingoList.numBallsCalled());
		BingoList.insert(B5);
		System.out.println("Add B5: " + BingoList);
		System.out.println(BingoList.numBallsCalled());

		BingoList.remove(B1);
		System.out.println("Remove B1: " + BingoList);
		System.out.println(BingoList.numBallsCalled());
		BingoList.insert(B1);
		System.out.println("Add B1: " + BingoList);
		System.out.println(BingoList.numBallsCalled());

		BingoList.remove(B5);
		System.out.println("Remove B5: " + BingoList);
		System.out.println(BingoList.numBallsCalled());
		BingoList.insert(B5);
		System.out.println("Add B5: " + BingoList);
		System.out.println(BingoList.numBallsCalled());
		
		BingoList.remove(B6);
		System.out.println("Remove B6: " + BingoList);
		System.out.println(BingoList.numBallsCalled());
		BingoList.insert(B6);
		System.out.println("Add B6: " + BingoList);
		System.out.println(BingoList.numBallsCalled());
		BingoList.remove(B6);
		System.out.println("Remove B6: " + BingoList);
		System.out.println(BingoList.numBallsCalled());
		

		System.out.println("Was ball B2 called? " + BingoList.wasCalled(B2));

		BingoList.remove(B4);
		System.out.println("Remove B4: " + BingoList);
		System.out.println(BingoList.numBallsCalled());
		System.out.println("Was ball B4 called? " + BingoList.wasCalled(B4));

		BingoList.insert(B4);
		System.out.println("Add B4: " + BingoList);
		System.out.println(BingoList.numBallsCalled());

		BingoList.makeEmpty();
		System.out.println("Clear list: " + BingoList);
		System.out.println(BingoList.numBallsCalled());
		BingoList.insert(B1);
		System.out.println("Add B1: " + BingoList);
		System.out.println(BingoList.numBallsCalled());

		BingoBall X = new BingoBall(1);
		BingoList.insert(X);
		System.out.println("Add X: " + BingoList);
		System.out.println(BingoList.numBallsCalled());
		

	}
}