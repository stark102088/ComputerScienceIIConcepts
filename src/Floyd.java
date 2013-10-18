import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/* Adam D. Stark
 * COP3503C - Recitation 9 - Floyd.java
 * By the definition of Floyd's algorithm, the Big-O runtime is n^3. Program begins 
 * by creating adjacency matrix, next array, and then finds the shortest paths between
 * the vertices (hence the n^3 runtime) - then the shortest paths list is printed out
 * for each vertex in the adjacency matrix
 */

public class Floyd {
	
	public int shortestPaths[][]; //shortest path matrix (for run())
	public int shortPath[][]; //shortest path matrix (for shortestPath())
	public int next[][]; //paths that follow vertex i
	public int a[][]; //adjacency matrix
	public int size=1; //size of adjacency matrix
	public int i; //for printPath()
	public int j; //for printPath()
	
	public void run() throws FileNotFoundException{
		Scanner scanner = new Scanner(new File("input.in.txt"));
		size = scanner.nextInt(); //get first size
		
		//so long as there is data to read in
		while (size!=0){
		
			//create adjacency matrix...create next array, and set shortest paths
			a = createAdjMat(scanner, size);
			next = new int[size][size];
			shortestPaths = shortestPath(a, next);
			
			//set path to like-vertices
			for (int i=0; i<size; i++)
				next[i][i] = i;
			
			System.out.println(size); //print size
			System.out.println("Lengths of shortest paths:");
			
			for (int j=0; j<size; j++){
				for (int k=0; k<size; k++){
					System.out.print(next[j][k]);
					}
				System.out.println();
				}
			
			System.out.println("Shortest paths:");
			
			for (int i=0; i<size; i++){
				for (int j=0; j<size; j++){
					System.out.print("(");
					printPath(next, i, j);
					System.out.print(") ");
				}
				System.out.println();
			}
			size = scanner.nextInt(); //get next adjacency matrix size
		} //while end
	} //run end
	
	public int[][] shortestPath(int[][]a, int[][]next){
		
		int size = a.length;
		int[][] shortPaths = new int[size][size];
		
		copyAdjMat(shortPaths, a); //save original adjacency matrix
		
		//initialize next[][]
		for (int i=0; i<size; i++)
			for (int j=0; j<size; j++)
				next[i][j] = j;
			
		for (int k=0; k<size; k++){
			for (int i=0; i<size; i++){
				for (int j=0; j<size; j++){
					if (shortPaths[i][k] + shortPaths[k][j] < shortPaths[i][j]){
						shortPaths[i][j] = shortPaths[i][k] + shortPaths[k][j];
						next[i][j] = next[i][k];
					} //if end
				} //second inner for
			} //inner for
		} //outer for
		
		return shortPaths;
	}//shortestPath end
	
	public int[][] createAdjMat(Scanner scanner, int size){

		int nums[][] = new int[size][size]; //array for nums in adjacency matrix
		int index=0;
		String s = "";
		scanner.nextLine(); //jump first line

		for (int i=0; i<size; i++){
			s = s + scanner.nextLine();
			s = s + " ";
		} //for end
		
		String sElem[] = s.split(" "); //create num elements
		
		//add values to adjacency list
		for (int j=0; j<size; j++){
			for (int k=0; k<size; k++){
				if (sElem[index].equals("-"))
					nums[j][k] = 1000000000; //indicates no path
				else nums[j][k] = Integer.parseInt(sElem[index]);
				index++;
			} //inner for
		} //outer for
		return nums;
	}//createAdjMat end
	
	public void printPath(int[][] next, int i, int j){
		
		//if vertex to vertex
		if (j==next[i][j]){
			System.out.print((i+1) + " " + (j+1));
			return;
		}//if end
		System.out.print((i+1) + " ");
		printPath(next, next[i][j], j);
	} //printPath end
	
	public void copyAdjMat(int[][] shortPaths, int[][] a) {
    	for (int i=0; i<shortPaths.length; i++){
      		for (int j=0; j<shortPaths[0].length; j++){
        		shortPaths[i][j] = a[i][j];
      		} //inner for
    	} //outer for
  	} //copyAdjMat end
} //Floyd end

class FloydRun{
	public static void main(String [] args) throws FileNotFoundException{
		Floyd floyd = new Floyd();
		floyd.run(); //run program
	} //main end
} //FloydRun end