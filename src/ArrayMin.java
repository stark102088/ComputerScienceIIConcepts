import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/* Adam D. Stark
 * COP3503C - Recitation 3 - ArrayMin.java
 * BigO is O(n) - function starts indices x and y at loc. 0 and array.length-1
 * and increments / decrements them respectively according to their indexed
 * value - once the two meet, the min value has been found
 */

public class ArrayMin{
	
	public String s1 = ""; //for reading in entire file
	public String s2 = ""; //for splitting into individual elements
	public int[] nums; //array to hold the ints

	public void start() throws FileNotFoundException { //begin reading the file contents
		
		Scanner scanner = new Scanner(new File("arraymin.in.txt")); //open file
		
		while (scanner.hasNextLine()){ //so long as there are test cases
			
			String s1 = scanner.nextLine(); //read in the test case
			String s2[] = s1.split(" "); //split into elements, store in s2 array
			
			nums = new int[s2.length]; //create int array of size s2
			
			//for each element, parse it
			for (int k=0; k<nums.length; k++)
				nums[k] = Integer.parseInt(s2[k]);

			//print out the recursive result
			System.out.println(minVal(nums, nums[0], 0, nums.length-1));
		}//while end
	}//start end
	
	//recursive function
	public static int minVal(int[] nums, int min, int i, int j){
		
		//if i and j index meet, return min val (takes care of assumption num.length==1)
		if (i==j)
			return nums[i];
		
		if (nums[i]<=nums[j])
			j--;
		
		else i++;
		
		return minVal(nums, min, i, j); //recursive call
	} // minVal end
} //Array end

class ArrayMinRun{ //driver class
	public static void main(String []args) throws FileNotFoundException{
		ArrayMin arraymin = new ArrayMin(); //create new array object
		arraymin.start(); //begin the program
		}//main end
	} //ArrayRun end