import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/* Adam D. Stark
 * COP3503C - Recitation 8 - Maze.java
 * travel() is the backtracking / recursive function that first checks if the row / col
 * indices are out of bounds, previously visited, or landed on an X. If so, it returns
 * false and backtracks to the previous caller. If we are in the last column (n-1) and 
 * the char at [row][col] is a '_', then we can reach the other side - return true.
 */

public class Maze{
	
	public int n; //size of board
	public int k; //test cases
	public int row; //row index
	public int col; //column index
	public char[][] board; //board we will travel
	public boolean[][] isVisited; //tracks spot on board being visited
	
	public void start() throws FileNotFoundException{
		
		Scanner scanner = new Scanner(new File("maze.in.txt")); //open file
		
		k = scanner.nextInt(); //test cases
		
		for (int i=0; i<k; i++){ //for each test case	
			//set values
			row=1;
			col=0;
			n = scanner.nextInt(); //read in size of board
			board = createGameBoard(scanner, n); //create the board
			isVisited = createVisitedBoard(scanner, n); //create the board
			
			if (travel(board, isVisited, row, col, n)) //if other side is reachable
				System.out.println("Data Set " + (i+1) + ": The robot CAN get to the other side");
			else
				System.out.println("Data Set " + (i+1) + ": The robot CAN NOT get to the other side");
		}//for end	
	} //start end
	
	//recursive function
	public boolean travel(char[][] board, boolean[][]isVisited, int row, int col, int n){
		
		if (row < 0 || col < 0 || row == board.length || col == board.length) //out of bounds, backtrack
			return false;
		
		if (isVisited[row][col]) //if previously visited, backtrack
			return false;
		
		if (isX(board[row][col])) //if X is found, backtrack
			return false;
		
		if (col == n-1 && board[row][col]== '_') //last column and not an 'x' then other side is reachable
			return true;
		
		isVisited[row][col] = true; //visited this location now
		
		//call recursive functions
		return (travel(board, isVisited, row+1, col, n) || travel(board, isVisited, row-1, col, n) ||
				travel(board, isVisited, row, col+1, n) || travel(board, isVisited, row, col-1, n));
	} //travel End
	
	//test for X on board	
	public boolean isX(char pos){
		return (pos == 'x');
	} //isX end
	
	public char[][] createGameBoard(Scanner scanner, int n){
		String maze = "";
		char[][] board = new char[n][n];
		int index=0;
		
		for (int i=0; i<n+1; i++) //read in the maze
			maze = maze + scanner.nextLine();
		
		for (int i=0; i<n; i++){ //translate Maze to 2D char array
			for (int j=0; j<n; j++){
				board[i][j] = maze.charAt(index);
				index++;
			} //inner for
		} //outer for
		index*=0; //reset
		return board;
	} //createCharBoard end
	
	public boolean[][] createVisitedBoard(Scanner scanner, int n){
		boolean[][]isVisited = new boolean[n][n];
		return isVisited;
	} //createBooleanBoard end
} //Maze end

class MazeRun{
	public static void main (String [] args) throws FileNotFoundException{
		Maze maze = new Maze(); //new instance
		maze.start();
	} //main end
} //MazeRun end