import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/* Adam D. Stark
 * COP 3503C - Recitation Assignment 2 - Array.java
 * Array determines if any two int in an array sized 'n' can be 
 * summed together to result in 't' in O(n) time.
 * 
 * The algorithm best case runtime is O(1) - assuming the first and last index 
 * add up to t. Worst case is O(n) since either 1)index x or y traverses the entire
 * array of size n until it reaches x or y, or 2) index x and y meet in the middle
 * of the array n/2, which is O(n)
 */

public class Array{
	
	public int[] nums; //array to hold the nums
	public int aSize = 0;
	public int k=0; //test cases
	public int m=1; //test case #
	public int t=0; //target
	public int x=0; //lhs index
	public int y=0; //rhs index

	public void start() throws FileNotFoundException { //begin reading the file contents
		Scanner scanner = new Scanner(new File("C://array.in.txt")); //loc. in C dir.
		k = scanner.nextInt(); //read in the number of test cases
		
		while (k!=0){ //so long as test cases exist...
			aSize = scanner.nextInt();
			
			nums = new int[aSize]; //create the array of size aSize
			
			for (int i=0; i<aSize; i++) //for each element in the array
				nums[i] = scanner.nextInt(); //read in the number
			
			t = scanner.nextInt(); //read in the target sum
			y = aSize-1; //rhs index
			
			if(sumExists(x, y, t, nums)) //if sumExists, return true
				System.out.println("Test Case #" + m + ": The target " + t + " is achievable.");
			else 
				System.out.println("Test Case #" + m + ": The target " + t + " is not achievable.");
			
			m++; //next test case #
			k--; //one less test case...
		}//while end
	}//start end
	
	//sumExists returns true if target can be found between x & y in O(n) time
	public boolean sumExists(int x, int y, int t, int[] nums){
		while (x<y){ //while index x & y do not meet in the middle
			if (nums[x] + nums[y] == t) //if target is found
				return true;
			else if (nums[x] + nums[y] < t)
				x++;
			else if (nums[x] + nums[y] > t)
				y--;
		} //while end
		return false;
	} //sumExists end
} //Array end

class ArrayRun{ //driver class
	public static void main(String []args) throws FileNotFoundException{
		Array array = new Array(); //create new array object
		array.start(); //begin the program
		}//main end
	} //ArrayRun end