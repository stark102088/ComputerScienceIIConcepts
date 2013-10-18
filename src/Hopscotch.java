import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/* Adam D. Stark
 * 07-09-2013
 * COP 3503C - Recitation 7 - Hopscotch.java
 * Function playGame uses a dynamic approach to funding the lowest score possible given
 * an input k. It solves the subproblem of choosing the lowest cost distance based on 
 * a particular position. Then a recursive call is made and a new k is sent to the calling
 * function. 
 */

public class Hopscotch {
	
	private int key[]; //for memoizing
	private int movesForward=1;
	private int score; //score of the move
	private int t; //total # of cases
	private int k; //number of squares
	
	public void start() throws FileNotFoundException{
		Scanner scanner = new Scanner(new File("hopscotch.in.txt"));
		
		t = scanner.nextInt(); //read in # of test cases
		
		for (int i=0; i<t; i++){ //for each test case
			System.out.print("Game #" + (i+1) + ": ");
			k = scanner.nextInt(); //read in # of square
			createKey();
			System.out.println(playGame(k));
			score*=0;
		}//for end
	} //start end
	
	public int playGame(int k){
		
		//base case
		if (k==0)
			return 0;
		
		if (k>10 && primeTest(k) && movesForward < primeMove(k)){ //prime move
			movesForward=primeMove(k);
			score=3;	
		} //if end
			
		else
		if (mult11Test(k) && movesForward < mult11Move(k)){ //mult of 11 move
			movesForward=mult11Move(k);
			score=4;
		} //if end
			
		else
		if (mult7Test(k) && movesForward < 4){ //mult of 7 move
			movesForward=4;
			score=2;
		} //if end
			
		else {
			movesForward=1; //one hop = one added to score
			score=1;
		} //else end
				
		return score + playGame(k-movesForward);
	} //playGame end
	
	//test for prime
	public boolean primeTest(int k){
		for (int i=2; i<k; i++)
			if (k%i==0) //if divisible by a #
				return false;	
		return true; 
	} //primeTest end
	
	//test for multiple of 11
	public boolean mult11Test(int k){
		if (k<11)
			return false;
		return (k%11==0);
	} //mult11Test end
	
	//test for multiple of 7
	public boolean mult7Test(int k){
		if (k<7)
			return false;
		return (k%7==0);
	} //mutl7Test end
	
	public int primeMove(int k){
		return k%10;
	} //primeMove end
	
	public int mult11Move(int k){
		
		int temp=k;
		int moves=0;
		
		while (temp!=0){
			moves = moves + temp%10;
			temp/=10;
		} //while end
		return moves;
	} //mult11Move end
	
	public void createKey(){
		key = new int[k];
		for (int i=0; i<key.length; i++)
			key[i]=-1;
	}
} //Hopscotch end

class HopscotchRun{
	public static void main(String [] args) throws FileNotFoundException{
		Hopscotch hopscotch = new Hopscotch();
		hopscotch.start();
	} //main end
} //Hopscotch run end