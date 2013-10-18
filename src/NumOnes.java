import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;

/* Adam D. Stark
 * COP3503C - Recitation 3 - NumOnes.java
 * BigO is O(log n). Reads in int n, if it is 1, return it, if n%2 results in
 * 0, it is an even number, hence 0 in its binary representation, if n%2 results
 * in 1, n was odd, hence 1 in its binary representation. Recursively adds up the
 * 1's, returns the result representing the number of 1's in n's binary representation.
 */

public class NumOnes{
		
	public void start()throws FileNotFoundException{
		Scanner scanner = new Scanner(new File("numones.in.txt")); //open the file
		
		while(scanner.hasNextLine()){//as long as there is data to read
			System.out.println(numOnes(scanner.nextInt())); //read in / send argument to rec. function / print result
		} //while end
	} //start end
	
	public static int numOnes(int n){
		//base cases (if the end is reached where n==1 or n%2 returns a 0) 
		if (n==1)
			return 1;
		if (n%2==0)
			return numOnes(n/2);
		return 1 + numOnes(n/2); //mod2 doesn't return 0 and n != 1 -- 1 found
	} //numOnes end
} //NumOnes end

class NumOnesRun{
	public static void main(String [] args)throws FileNotFoundException{
		NumOnes numOnes = new NumOnes(); //create new instance
		numOnes.start(); //start program
	} //main end
} //NumOnesRun end