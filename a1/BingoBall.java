/*
* Name: Julian Rocha
* ID: V00870460
* Date: 8:02PM Saturday Sep. 16 2017
* Filename: BingoBall.java
* Details: CSC115 Assignment 1
*/ 

public class BingoBall 
{

	private static final char[] BINGO = {'B','I','N','G','O'};//An array to reference the possible letters a BingoBall object could have.
	private int value;//The integer value from 1 to 75 of a BingoBall.
	private char letter;//The letter of a BingoBall.

/**
* This constructor creates a Bingo Ball object by 
* passing an int value and corresponding it to a letter.
* @param value The integer value between 1 and 75 to set a Bingo Ball to.
* @throws IllegalArgumentException if value is not between 1 and 75.
*/
	public BingoBall(int value) 
	{
		if(value > 0 && value < 76) 
		{	
			this.value = value; 
			setLetter();
		}//end of if statement
		else 
		{
			throw new IllegalArgumentException("number must be between 1 and 75; it was " + value);
		}//end of else statement
	}	//end of BingoBall contructor

	
/**
* This private "setter" is called by setValue() to 
* match the new value with its corresponding letter.
*/
	private void setLetter()
	{
		letter = BINGO[(value - 1) / 15];//This expression matches a value (1-75) with a corresponding index in bingo array.
	}//end of setLetter method
	

/**
* This "getter" returns the integer value of a Bingo Ball object.
* @return The integer value of a Bingo Ball. (Between 1 and 75)
*/
	public int getValue() 
	{
		return value;
	}//end of getValue method

/**
* This "getter" returns the letter assigned to a Bingo Ball object.
* @return The letter of a Bingo Ball. (B,I,N,G, or O)
*/
	public char getLetter() 
	{
		return letter; 
	}//end of getLetter method

/**
* This "setter" resets the integer value of a BingoBall and 
* then calls setLetter to reset the letter.
* @param value The desired value to set the Bingo Ball to. (Between 1 and 75)
* @throws IllegalArgumentException if value is not between 1 and 75.
*/
	public void setValue(int value) 
	{
		if(value > 0 && value < 76)
		{
			this.value = value;
			setLetter();
		}//end of if statement
		else
		{
			throw new IllegalArgumentException("number must be between 1 and 75; it was " + value);
		}//end of else statement
	}//end of setValue method

/**
* This method determines if a passed Bingo Ball
* has the same value as the one being tested.
* @param other Bingo Ball object to compare values with.
* @return boolean True if the balls are the same and false if they are not.
*/
	public boolean equals(BingoBall other) 
	{
		if(other == null)
		{
			return false;
		}
		if((value == other.getValue()) && (letter == other.getLetter()))
		{
			return true;
		}//end of if statement
		else
		{
			return false;
		}//end of else statement
	}//end of equals method

/**
* This method overrides javas toString method to
* return a Bingo Ball in the string form letter + value Ex. B4
* @return String A string form of a BingoBall.
*/
	public java.lang.String toString() 
	{
		return "" + letter + value;
	}//end of toString method

/*Main is used to test the BingoBall methods
*/
	public static void main(String[] args) 
	{
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
	}//end of main
}//end of BingoBall class
