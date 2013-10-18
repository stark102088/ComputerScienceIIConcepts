import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/* Adam D. Stark
 * COP3503C - Recitation 6 - Trains.java
 * Program runs in O(n) time - the only input that grows is n, which is indicative
 * of how many train schedules we run. n of itself is never manipulated - each train
 * schedule has 10 iterations of checking x amount of trains, which is computed in 
 * linear time. 
 */

public class Trains {
	
	public int raceFail; //indicates if race can be finished
	public int n; //# of schedules
	public int trainNum = 1; //train# indicator 
	public int start; //start time
	public int finish; //finish time
	public int trains; //# of trains at the station
	public int penaltyPoints; //self explanatory...
	public int minFinish; //minimum time that is favored
	public int prevFinish; //previous arrival time
	public boolean canReach; //can a train be boarded at the station
	
	public void start() throws FileNotFoundException{
		
		//open file and attach scanner object
		Scanner scanner = new Scanner(new File("trains.in.txt"));
		n = scanner.nextInt(); //# of schedules
		
		for (int i=0; i<n; i++) //for each train schedule
			travel(scanner);
	} //start end
	
	//function that handles the minimum # of penalty points 
	public void travel(Scanner scanner){
		
		for (int i=0; i<10; i++){ //for all 10 train stations
			canReach = false; //as of now, cannot board a train
			trains = scanner.nextInt(); //number of trains at that stations
			
			for (int j=0; j<trains; j++){ //for each train on the schedule
				start = scanner.nextInt(); //start time of a train
				finish = scanner.nextInt(); //finish time of a train
				
				if (start >= prevFinish){
					canReach = true; //at least one train can be boarded
					
					if (minFinish==0 || finish <= minFinish)
						minFinish = finish;
				} //if end
			} //for end
			
			if (!canReach) //check after this train station if unreachable
				raceFail++;
	
			if (i<9) //add minutes of previous stations
				penaltyPoints += minFinish;
			prevFinish = minFinish;
			minFinish*=0;	
		} //for end
		
		if (raceFail==0) //if reachable
			System.out.println("Train " + trainNum + ": Arrive at station #10 in " + finish +
					" minutes with " + penaltyPoints + " penalty points.");
		if (raceFail!=0) //if not reachable
			System.out.println("Station #10 is NOT reachable.");
		
		//reset all global values
		prevFinish*=0;
		minFinish*=0;
		penaltyPoints*=0;
		raceFail*=0;
		trainNum++;	//increment train count
		
	} //travel end
} //catching trains end

class TrainsRun{
	public static void main(String [] args) throws FileNotFoundException{
		Trains trains = new Trains();
		trains.start(); //start program
	} //main end
} //CatchingTrainsRun end