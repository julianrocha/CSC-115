/*
* Name: Julian Rocha
* ID: V00870460
* Date: Nov. 1st 2017
* Filename: maze.java
* Details: CSC115 Assignment 4
*/
public class Maze
{
	private String[] textmaze; //1D array of strings that will contain a passed maze
	private char[][] visited; //2D array of chars that gets marked with visited coordinates during a maze solve
	//will get marked 'X' if location is part of path, 'O' if it's been visited but is part of a dead end
	//'\u0000' if it has not yet been visited (default value of uninitialized char)
	private Cell start;//Cell marking start of maze (row,col)
	private Cell finish;//Cell marking end of maze (row, col)
	private CellDeque path = new CellDeque();//Deque to hold Cells representing the steps of a maze solution
	private int width;//width of maze, number of columns wide
	private int height;//height of maze, number of rows tall

//Constructor
	public Maze(String[] textmaze, Cell start, Cell finish)
	{
		this.start = start;
		this.finish = finish;
		this.textmaze = textmaze;

		width = textmaze[0].length(); //width = length of every string in textmaze
		height = textmaze.length; //height = number of strings in textmaze array

		visited = new char[height][width]; //2D visited will have same dimensions as maze
	}

//Public method that will solve a maze by calling a recrusive path finder
//Will return the path in the form of a deque, path will be empty if there is no solution
	public CellDeque solve()
	{
		path.insertLast(start); //insert start as starting point for path of maze
		if(findPath(start, finish))//call recursive method to develop path and visited array
		{
		print(); //call method to print the path found
		}
		return path;
	}


//Public method that prints the maze path
	public void print()
	{
		for(int i = 0; i < height; i++)//iterate through maze rows
		{
			for(int j = 0; j < width; j++)//iterate through maze columns
			{
				if(textmaze[i].charAt(j) == '*')//print maze wall
				{
					System.out.print("*");
				}
				else if(visited[i][j] == 'X')//print maze path
				{
					System.out.print("0");
				}
				else//print open spaces
				{
					System.out.print(" ");
				}
			}
			System.out.println();//new line
		}
	}//end of print() method


/**
* Private recursive method that will develop a path for a maze (if there is one)
* @param source Cell representing start of maze
* @param destination Cell representing end of maze
* @return true if a path has been found, false if not
*/
	private boolean findPath(Cell source, Cell destination)
	{
		int row = path.last().row; //row = row of Cell last added to path
		int col = path.last().col; //col = col of Cell last added to path
		Cell curr = new Cell(row,col); //temporary Cell to represent current poistion in maze
		visited[row][col] = 'X'; //mark as visited and assume it is part of the correct path

		if(curr.equals(destination)) //check for base case: current location is the destination
		{
			return true; //true will carry all the way up the recursive steps
		}

		boolean rightLimit = col + 1 <= width;//false if curr is on right border of maze
		//will move right if open space available, that's not out of bounds, that hasn't been visited
		if(rightLimit && textmaze[row].charAt(col + 1) == ' ' 
			&& visited[row][col + 1] == '\u0000')
		{
			curr.col = col + 1; //increment curr's col, move right
			path.insertLast(curr);//add new position to path
			return findPath(source, destination);//finished recursive step, perform recursive call
		}
		boolean downLimit = row + 1 <= height;//false if curr is on bottom border of maze
		//will move down if open space available, that's not out of bounds, that hasn't been visited
		if(downLimit && textmaze[row + 1].charAt(col) == ' ' 
			&& visited[row + 1][col] == '\u0000')
		{
			curr.row = row + 1; //increment curr's row, move down
			path.insertLast(curr);//add new position to path
			return findPath(source, destination); //finished recursive step, perform recursive call
		}
		boolean leftLimit = col >= 1;//false if curr is on left border of maze
		//will move left if open space available, that's not out of bounds, that hasn't been visited
		if(leftLimit && textmaze[row].charAt(col - 1) == ' ' 
			&& visited[row][col - 1] == '\u0000')
		{
			curr.col = col - 1; //decrement curr's col, move left
			path.insertLast(curr);//add new position to path
			return findPath(source, destination);//finished recursive step, perform recursive call
		}
		boolean upLimit = row >= 1; //false if curr is on upper border of maze
		//will move up if open space available, that's not out of bounds, that hasn't been visited
		if(upLimit && textmaze[row - 1].charAt(col) == ' ' 
			&& visited[row - 1][col] == '\u0000')
		{
			curr.row = row - 1; //decrement curr's row, move up
			path.insertLast(curr);//add new position to path
			return findPath(source, destination);//finished recursive step, perform recursive call
		}
		//no where to go but current position is not the start
		else if(!source.equals(curr))
		{
			path.removeLast();//remove the current poisiton from the path
			visited[row][col] = 'O';//mark current postion as visited/dead end
			return findPath(source, destination);//finished recursive step, perform recursive call
		}
		//no where to go and current position is the start: therefore maze has no solution
		visited[start.row][start.col] = 'O'; //mark start as visited/dead end
		path.makeEmpty();//empty the path
		return false; //false will carry all the way up the recursive steps
	} //end of findPath method


/**
* Public method that returns a string form of the textmaze.
* @return list A printable string form of the textmaze.
*/
	public String toString()
	{
		String maze = "";//maze will be returned at the end of the method
		for(int i = 0; i < textmaze.length; i ++)
		{
			maze += textmaze[i] + "\n";
		}
		return maze;
	}//end of toString method
}//end of class