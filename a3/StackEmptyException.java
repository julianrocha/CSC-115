/*
* Name: Julian Rocha
* ID: V00870460
* Date: Sunday Oct. 22 2017
* Filename: StackEmptyException.java
* Details: CSC115 Assignment 3
*/ 

/**
 * An exception is thrown when invalid operations are performed on an empty Stack
 */ 
public class StackEmptyException extends RuntimeException {

	/**
	 * Creates an exception.
	 * @param msg The message to the calling program.
	 */
	public StackEmptyException(String msg) {
		super(msg);
	}

	/**
	 * Creates an exception without a message.
	 */
	public StackEmptyException() {
		super();
	}
}
