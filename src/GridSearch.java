import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*Adam D. Stark
 * COP3503C - Recitation 3 - GridSearch
 * Big(O) - algorithm runs in O(n^2). Of input size n, the grid size is n^2, meaning
 * potentially every element in the grid could be visited. Algorithm works in clockwise
 * direction, creating recursive methods each time a move can be made, and only 
 * returning true once the startX startY and endX endY indices equal one another
 */

public class GridSearch {
	
	public boolean[][] visited;
	public int index;;
	public int[] nums;
	public int n;
	public int startX;
	public int startY;
	public int endX;
	public int endY;
	public int[][] board;
	
	public void start() throws FileNotFoundException{
		
		Scanner scanner = new Scanner(new File("gridsearch.in.txt"));
		
		while (scanner.hasNextLine()){ //as long as there are test cases
			
			String s1 = scanner.nextLine(); //read in the test case
			String s2 = ""; //temp string for parsing
			for (int i=0; i<s1.length(); i++)
				s2 = s2 + s1.charAt(i) + " "; //add spaces so it can be split()
			
			String s3[] = s2.split(" "); //split into elements, store in s2 array
			
			nums = new int[s3.length]; //create int array of size s2
			
			for (int j=0; j<nums.length; j++) //for each element, parse it
				nums[j] = Integer.parseInt(s3[j]);

			//store the values in the the indices
			startX = nums[0];
			startY = nums[1]; 
			endX = nums[2]; 
			endY = nums[3];
			n = nums[4];
			index=5;
			board = new int[n][n]; //create board of size n^2
			visited = new boolean[n][n]; //same here for visited spots
			
			//read in all the grid values
			for (int k=0; k<n; k++){
				for (int l=0; l<n; l++){
					board[k][l] = nums[index];
						index++;
					}//inner for end        
			}//outer for end
			System.out.println(canMove(board, startX, startY, endX, endY, visited));
		}//while end
	}//start end
	
	public static boolean canMove(int board[][], int startX, int startY, int endX, int endY, boolean[][] visited){
		
		visited[startX][startY]=true; //mark spot as visited
		
		if (startX==endX && startY==endY) //base case
			return true;

		//move left...
		if (startX!=0 && board[startY][startX] <= board[startY][startX-1] && visited[startY][startX-1]==false)
			return canMove(board, startX-1, startY, endX, endY, visited);
		
		//move up...
		if (startY!=0 && board[startY][startX] <= board[startY-1][startX] && visited[startY-1][startX]==false)
			return canMove(board, startX, startY-1, endX, endY, visited);
		
		//move right...
		if (startX!=board.length-1 && board[startY][startX] <= board[startY][startX+1] && visited[startY][startX+1]==false)
			return canMove(board, startX+1, startY, endX, endY, visited);
		
		//move down...
		if (startY!=board.length-1 && board[startY][startX] <= board[startY+1][startX] && visited[startY+1][startX]==false)
			return canMove(board, startX, startY+1, endX, endY, visited);
		
		//if never found, can't be reached
		return false;
	}//canMove end
}//GridSearch end

class GridSearchRun{
	public static void main(String [] args) throws FileNotFoundException{
		GridSearch gridSearch = new GridSearch();
		gridSearch.start();	
	}//main end
}//GridSearchRun end