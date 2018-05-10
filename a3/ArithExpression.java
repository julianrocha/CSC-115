/*
* Name: Julian Rocha
* ID: V00870460
* Date: Sunday Oct. 22 2017
* Filename: ArithExpression.java
* Details: CSC115 Assignment 3
*/ 

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ArithExpression {

	private TokenList postfixTokens;
	private TokenList infixTokens;

	/**
	 * Sets up a legal standard Arithmetic expression.
	 * The only parentheses accepted are "(" and ")".
	 * @param word An arithmetic expression in standard infix order.
	 * 	An invalid expression is not expressly checked for, but will not be
	 * 	successfully evaluated, when the <b>evaluate</b> method is called.
	 * @throws InvalidExpressionException if the expression cannot be properly parsed,
	 *  	or converted to a postfix expression.
	 */
	public ArithExpression(String word) {
		if (Tools.isBalancedBy("()",word)) {
			tokenizeInfix(word);
			infixToPostfix();
		} else {
			throw new InvalidExpressionException("Parentheses unbalanced");
		}
	}

	/*
	 * A private helper method that tokenizes a string by separating out
	 * any arithmetic operators or parens from the rest of the string.
	 * It does no error checking.
	 * The method makes use of Java Pattern matching and Regular expressions to
	 * isolate the operators and parentheses.
	 * The operands are assumed to be the substrings delimited by the operators and parentheses.
	 * The result is captured in the infixToken list, where each token is 
	 * an operator, a paren or a operand.
	 * @param express The string that is assumed to be an arithmetic expression.
	 */
	private void tokenizeInfix(String express) {
		infixTokens  = new TokenList(express.length());

		// regular expression that looks for any operators or parentheses.
		Pattern opParenPattern = Pattern.compile("[-+*/^()]");
		Matcher opMatcher = opParenPattern.matcher(express);

		String matchedBit, nonMatchedBit;
		int lastNonMatchIndex = 0;
		String lastMatch = "";

		// find all occurrences of a matched substring
		while (opMatcher.find()) {
			matchedBit = opMatcher.group();
			// get the substring between matches
			nonMatchedBit = express.substring(lastNonMatchIndex, opMatcher.start());
			nonMatchedBit = nonMatchedBit.trim(); //removes outside whitespace
			// The very first '-' or a '-' that follows another operator is considered a negative sign
			if (matchedBit.charAt(0) == '-') {
				if (opMatcher.start() == 0 || 	
					!lastMatch.equals(")") && nonMatchedBit.equals("")) {
					continue;  // ignore this match
				}
			}
			// nonMatchedBit can be empty when an operator follows a ')'
			if (nonMatchedBit.length() != 0) {
				infixTokens.append(nonMatchedBit);
			}
			lastNonMatchIndex = opMatcher.end();
			infixTokens.append(matchedBit);
			lastMatch = matchedBit;
		}
		// parse the final substring after the last operator or paren:
		if (lastNonMatchIndex < express.length()) {
			nonMatchedBit = express.substring(lastNonMatchIndex,express.length());
			nonMatchedBit = nonMatchedBit.trim();
			infixTokens.append(nonMatchedBit);
		}
	}

	/**
	 * Determines whether a single character string is an operator.
	 * The allowable operators are {+,-,*,/,^}.
	 * @param op The string in question.
	 * @return True if it is recognized as a an operator.
	 */
	public static boolean isOperator(String op) {
		switch(op) {
			case "+":
			case "-":
			case "/":
			case "*":
			case "^":
				return true;
			default:
				return false;
		}
	}
		
	/*
	 * NOTE TO STUDENT for infixToPostfix below...:
	 * You do not need to check that the infixTokens data field is a legitimate infix
	 * expression at this time.
	 * If, during the process, something unexpected happens, then throw an Exception, but it 
	 * is okay for the postfixTokens to contain an invalid postfix expression.
	 * It is only when processing the public method 'evaluate', that any errors must be
	 * acknowledged.
	 */

	 /**
	 * A private method that initializes the postfixTokens data field.
	 * It takes the information from the infixTokens data field.
	 * If, during the process, it is determined that the expression is invalid,
	 * an InvalidExpressionException is thrown.
 	 * Note that since the only method that calls this method is the constructor,
	 * the Exception is propogated through the constructor.
	 */
	private void infixToPostfix() 
	{
		StringStack stack = new StringStack(); //stack to store parentheses and operators
		postfixTokens = new TokenList(); //initializes postfixTokens list
		String token; //variable to hold current token of infixTokens
		
		for(int i = 0; i < infixTokens.size(); i++) //for loop iterates through every token of infixTokens list
		{
			token = infixTokens.get(i); //resets token to next token in infixTokens list
			
			if(isOperator(token)) //deals with tokens that are operators, calls private isOperator method
			{
				if(stack.isEmpty()) //if stack is empty, push the operator token and go to the next token
				{
					stack.push(token);
				}
				else //what to do when stack is not empty and token is an operator
				{
					while(!stack.isEmpty()) //while loop to search and compare stack operators/parantheses
					{	
						if(stack.peek().equals("(")) //if "(" is the head of the stack
						{
							stack.push(token); //push operator token and go to the next token
							break; //break out of while loop
						}
						//see tokenVSstack private method for details, compares operator precedence
						if(!tokenVSstack(token, stack.peek())) //if token is lower or equal precedence to stack head
						{
							postfixTokens.append(stack.pop()); //pop head and append to postfix list
							continue; //restart while loop with new stack head
						}
						stack.push(token); //if token is higher precedence then push it on stack
						break;//break out of while loop
					}//end of while loop
					if(stack.isEmpty()) //if token was never pushed onto stack, do it now
					{
						stack.push(token);
					}
				}
				continue; //restart for loop with new token
			}//end of isOperator if statement

			switch(token) //switch statement if token is an operand or parenthesis
			{
				case "(": //if token is "(", push on stack and restart for loop
					stack.push(token);
				break;

				case ")": //if token is ")", operators inside parentheses must be emptied from stack
					while(!stack.peek().equals("(")) //keep popping and appending until "(" is reached
					{
						postfixTokens.append(stack.pop());
					}
					stack.pop(); //pop and discard the "("
				break; //break out of switch and restart for loop

				default: //default = token is an operand
					postfixTokens.append(token); //append operand to list
				break; // break out of switch and restart for loop
			}//end of switch
		}//end of for loop

		while(!stack.isEmpty()) //while loop to append any operators left in the stack
		{
			postfixTokens.append(stack.pop());
		}
	}//end of infixToPostfix()

/**
* This private method compares two strings and determines if the
* second is of lower operator precedence.
* @param token The string from infixTokens to check for operator precedence
* @param stack The string from the stack to check agasint token
* @return boolean True if token is higher precedence than stack,
* false if token is of equal or lower precedence.
*/
	private boolean tokenVSstack(String token, String stack)
	{
		switch(token) //checks what operator token is
		{
			case "+":
			return false;
			
			case "-":
			return false;
			
			case "/":
			if(stack.equals("+") || stack.equals("-"))
			{
				return true;
			}
			return false;
			
			case "*":
			if(stack.equals("+") || stack.equals("-"))
			{
				return true;
			}
			return false;
			
			case "^":
			if(stack.equals("^"))
			{
				return false;
			}
			return true;
		}
		return false; //default return false
	}

/**
* Private method called by evaluate() to determine if
* operand is a number or not.
* @param string Operand to be checked if it is a number or not.
* @return boolena True if it is a number, false if it is not.
*/
	private boolean isNumber(String string)
	{
		try
		{
			Double.parseDouble(string);
		}
		catch (Exception e)
		{
			return false;
		}
		return true;
	}

/**
* Returns string form of infixTokens
*/
	public String getInfixExpression() 
	{
		return infixTokens.toString();
	}

/**
* Returns string form of postFixTokens
*/
	public String getPostfixExpression() 
	{
		return postfixTokens.toString();
	}
	
/**
* Evaluates postfixTokens and returns the answer as type double.
* @return head of stack which has the final answer
*/	
	public double evaluate() {
		StringStack stack = new StringStack(); //stack to store intermediate and final answers
		double x; //variable to store second operand
		double y; //variable to store first operand
		String token; //stores the current token of postFixTokens list
		for(int i = 0; i < postfixTokens.size(); i++) //for loop iterates through entire postFixTokens List
		{
			token = postfixTokens.get(i); //updates token
			if(isOperator(token))//what to do if token is an operator
			{
				switch(token)
				{
					case "+":
					//pop two operands, add them and push result on stack
					x = Double.parseDouble(stack.pop());
					y = Double.parseDouble(stack.pop());
					stack.push(Double.toString(y + x));
					break;
					case "-":
					//pop two operands, subtract them and push result on stack
					x = Double.parseDouble(stack.pop());
					y = Double.parseDouble(stack.pop());
					stack.push(Double.toString(y - x));
					break;
					case "/":
					//pop two operands, divide them and push result on stack
					x = Double.parseDouble(stack.pop());
					y = Double.parseDouble(stack.pop());
					if(x == 0)
					{
						throw new InvalidExpressionException("Divide by 0 Error");
					}
					stack.push(Double.toString(y / x));
					break;
					case "*":
					//pop two operands, multiply them and push result on stack
					x = Double.parseDouble(stack.pop());
					y = Double.parseDouble(stack.pop());
					stack.push(Double.toString(y * x));
					break;
					case "^":
					//pop two operands, perform exponent and push result on stack
					x = Double.parseDouble(stack.pop());
					y = Double.parseDouble(stack.pop());
					if(y < 0 && (1 / x) % 2 == 0)
					{
						throw new InvalidExpressionException("Complex Root Error");
					}
					stack.push(Double.toString(Math.pow(y,x)));
					break;
				}
			}
			else //if token is an operand, push onto stack
			{
				if(!isNumber(token))//if operand is not a number, throw exception
				{
					throw new InvalidExpressionException("Operand in expression cannot be evaluated: " + token);
				}
				stack.push(token);
			}
		}//end of for loop
		if(stack.isEmpty()) //if postFixTokens was empty, return 0 as result
		{
			return 0;
		}
		return Double.parseDouble(stack.pop()); //return head of stack, which is the final answer
	}
	
//MAIN IS USED AS A TESTER					
	public static void main(String[] args) {
		ArithExpression math = new ArithExpression("(a+b)/(c+d*e)^f");
		ArithExpression math2 = new ArithExpression("(2+3) * (25/5) - 7");
		System.out.println(math2.getInfixExpression());
		System.out.println(math2.getPostfixExpression());
		System.out.println(math2.evaluate());
		System.out.println(math.getInfixExpression());
		System.out.println(math.getPostfixExpression());
		try
		{
			System.out.println(math.evaluate());	
		}
		catch(Exception e)
		{
			System.out.println("Properly caught excpetion for evaluating numberless expression.");
		}	

		ArithExpression math3 = new ArithExpression("(-2)^(1/2)");
		System.out.println(math3.getInfixExpression());
		System.out.println(math3.getPostfixExpression());
		try
		{
		System.out.println(math3.evaluate());
		}
		catch(Exception e)
		{
			System.out.println("Properly caught excpetion for evaluating complex root.");
		}
		ArithExpression math4 = new ArithExpression("10 / (3 - 3)");
		System.out.println(math4.getInfixExpression());
		System.out.println(math4.getPostfixExpression());
		try
		{
		System.out.println(math4.evaluate());
		}
		catch(Exception e)
		{
			System.out.println("Properly caught excpetion for divide by 0.");
		}
	}
			
}
