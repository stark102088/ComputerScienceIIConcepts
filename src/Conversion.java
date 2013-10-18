import java.util.Scanner;
import java.io.File;
import java.io.IOException;

/* Adam D. Stark
 * Cop 3503C - Recitation 4 - Conversion.java
 * Big(O) is determined via three major steps - converting the string to an array of
 * nums, sorting the array, and then running through the loops determining the indices 
 * of the neighbors. At worst, each function runs n^2 times with the double for loops.
 * This would be n^2 + n^2 + n^2, or simply Big O(n^2) for a given input in.
 */

public class Conversion {

	public int[] nums; //holds numbers for createArray
	public int[] array; //array holding adj. matrices / lists
	public int convType; //conversion type
	public int n; //# of vertices
	public int p; //# of neighbors 
	
	public void start() throws IOException{
		
		File file = new File("conversions.in.txt"); //open file
		Scanner scanner = new Scanner(file);
	
		convType = Integer.parseInt(scanner.nextLine()); //read in first test case
		
		while (convType!=0){ //either 1 or 2
		
			n = Integer.parseInt(scanner.nextLine()); //number of vertices
			
			if (convType==1){ //adjacency matrix to list
				adjMatToList(scanner, n);
			} //type1 end
			if (convType==2){ //list to adjacency matrix
				listToAdjMat(scanner, n);
			} //type2 end	
		
		convType = Integer.parseInt(scanner.nextLine()); //read next type
		}//while end
	} //start end
	
	//converts adjacency matrix to list
	public void adjMatToList(Scanner scanner, int n) throws IOException{
		
		System.out.println(n); //print # of vertices
		
		p=0; //set neighbors to 0
		
		for (int i=0; i<n; i++){ //for each vertex...
			array = createArray(scanner, n); //read in adjacency matrix rep.
			for (int j=0; j<array.length; j++) //for each element
				if (array[j]==1) //if true
					p++; //increase neighbors
			
			System.out.print(p + " "); //print how many neighbors
			
			for (int k=0; k<array.length; k++) //for each element
				if (array[k]==1) //if true
					System.out.print(k + " "); //print index where true
			
			System.out.println();
			p*=0; //reset
		} //for end
	} //adjMatToList end
	
	//converts list to adjacency matrix
	public void listToAdjMat(Scanner scanner, int n) throws IOException{
	
		int index=1; //set index 
		
		System.out.println(n); //print # of vertices
		
		for (int i=0; i<n; i++){ //for each vertex...
			array = createArray(scanner, n); //read in list rep.
			array = sort(array, n); //sort the array

			for (int j=0; j<n; j++){ //for each element
				if (index < array.length && array[index]==j){ //if match found
					System.out.print(1 + " ");
					index++; //inc. index
				}
				else 
					System.out.print(0 + " ");
			} //for end
			System.out.println();
			index=1;
		} //for end
	} //listToAdjMat end
	
	public int[] sort(int[] array, int n){ //sorts the locations of the adj. matrix
		
		int temp[] = new int[array.length]; 
		int index=1;
		
		temp[0] = array[0]; //copy # of vertices
		
			for (int i=0; i<n; i++){ //do this n times
				for (int j=1; j<array.length; j++){ //for each element in the array
					if (array[j]==i){
						temp[index]=i;
						index++;
					} //if end
				} //for end
			} //for end
		return temp;
	} //sort end
	
	//reads in line / converts to int array
	public int[] createArray(Scanner scanner, int n) throws IOException{
		
		String s = scanner.nextLine();
		
		String sElem[] = s.split(" "); //space out nums
		
		nums = new int[sElem.length]; //create array
	
		for (int i=0; i<nums.length; i++) //for each num
			nums[i]=Integer.parseInt(sElem[i]);
		
		return nums; //return the array
	} //createArray end
}//Conversion end

class ConversionRun{
	public static void main (String [] args) throws IOException{
		Conversion conversion = new Conversion();
		conversion.start(); //run the program
	}//main end
}//ConversionRun end