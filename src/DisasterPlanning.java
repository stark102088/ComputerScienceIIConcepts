import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/* Adam D. Stark
 * 06 / 24 / 2013
 * COP 3503C - Recitation 5 - DisasterPlanning.java
 */

public class DisasterPlanning {
	
	public int[][]list; //list for holding adjacent vertices
	public int[] nums; //hold list of edges
	public int[] vList1;
	public int[] vList2;
	public int v; //cities
	public int e; //roads
	public int l;
	public int r;
	public int index;

	public void start() throws FileNotFoundException{
		
		Scanner scanner = new Scanner(new File("connectivity.in.txt")); //open file
		
		v = scanner.nextInt(); //read in # of cities
		e = scanner.nextInt(); //read in # of roads
		index=0;
		l=0;
		r=1;
		
		nums = new int[e*2];
		list = new int[v][v+1];
		
		readNums(scanner, nums); //read in edges
		
		fillList(nums, list, v); //fill list with adjacent vertices
		
			for (int i=0; i<e; i++){
			
				vList1 = createVertLists(nums, list, index);//creates list of vertices each vertex is adjacent with
				index++;
				vList2 = createVertLists(nums, list, index);//creates list of vertices each vertex is adjacent with
				index++;
			
				addValues(vList1, vList2, list, nums, l, r);
				
				if (!(bridgeExists(vList1, vList2))){
					System.out.print(nums[l] + " ");
					System.out.print(nums[r] + " ");
					System.out.println();
				} //if end
				l+=2;
				r+=2;
			}
	} //start end
	
	//method for reading in array
	public void readNums(Scanner scanner, int[] nums){
		for (int i=0; i<nums.length; i++)
			nums[i] = scanner.nextInt();
	} //readNums end
	
	public void fillList(int[] nums, int[][] list, int v){
	
		int l=0; //left index
		int r=1; //right index
		int index=1;
		
		for (int i=0; i<v; i++){ //create empty list
			for (int j=0; j<v+1; j++){
				if (j==0)
					list[i][j]=i;
				else
				list[i][j] = -1; //-1 to flag empty location
			}//inner for
		} //out for
		
		for (int i=0; i<nums.length/2; i++){ //add adjacent vertices
			for (int j=0; j<nums.length/2; j++){
			
				if (nums[l] == i){ //if left vertex matches index
					list[i][index]=nums[r];
					index++;
				}
				if (nums[r] == i){ //if right vertex matches index
					list[i][index]=nums[l];
					index++;
				}
				//jump values
				l+=2; 
				r+=2;
			}
			//reset values
			l=0; 
			r=1; 
			index=1;
		} //for end
	} //fillList end
	
	public int[] createVertLists(int[]nums, int[][]list, int index){
		
		int[] adjVertices;
		int size=0;
		int i=1;
		int val;
		
		val = nums[index];
		
		while (list[val][i]!=-1){
			size++;
			i++;
		}
		
		adjVertices = new int[size];
		
		return adjVertices;
		
	}//findCriticalRoad
	
	public boolean bridgeExists(int[] vList1, int[] vList2){

		for (int i=0; i<vList1.length; i++){
			for (int j=0; j<vList2.length; j++){
				if (vList1[i]==vList2[j])
					return true;
			} //inner for
		} //outer for
	
		return false;
	} //bridgeExists end
	
	public void addValues(int[] vList1, int[] vList2, int[][] list, int[] nums, int l, int r){
		
		int val1 = nums[l];
		int val2 = nums[r];
		
		for (int i=0; i<vList1.length; i++)
			vList1[i] = list[val1][i+1];
		
		for (int j=0; j<vList2.length; j++)
			vList2[j] = list[val2][j+1];
	}//addValues end
}

class DisasterPlanningRun{
	public static void main(String [] args) throws FileNotFoundException{
		DisasterPlanning disaster = new DisasterPlanning();
		disaster.start(); //run the program
	}
}