import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

/**
 * Class : CS241 Spring 2015
 * 
 * Project Description : This project implements a Max Heap and shows the efficiency of different 
 * sorting algorithms.  The user is prompted to select either 20 sets of random numbers that will 
 * be sorted using optimal heap or the insertion method, or the user can select to use fixed values
 * from 1-100.  
 * 
 * @author Philip Sloan
 * date : May 14th, 2015
 *
 */
public class Project2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		
		boolean keepGoing=true;
		int input=0;
		
		
		//Main Program Loop
		while(keepGoing){
			printMenu();
			input = s.nextInt();
			switch(input){
				case 1:	
					Option1();
					break;
				case 2:
					Option2();
					break;
				case 3:
					keepGoing=false;
			}
			
		}
		
		s.close();//close the scanner
		System.out.println("\n Thanks For Using Me! Good Night!");
		System.exit(0);//go home and sleep
	}
	
	private static void Option1()
	{
		//to create  random sets of numbers that are unique in their set
		//a hash set of integers will be used since it automatically excludes duplicates
		Set<Integer> randomSet = new HashSet<Integer>(100);
		
		MaxHeap optimalHeap = new MaxHeap(100);
		MaxHeap notSoOptimalHeap = new MaxHeap(100);
		
		//a temp array to put the values in
		Integer[] temp ;
		
		int optimalSwapSum=0, unOptimalSwapSum=0; //to track the sum of all of the swaps
		int optimalSwapAverage, unOptimalSwapAverage; //the average swaps
		
		
		//Random Number Generator
		Random r = new Random();
		
		//for each iteration until 20 iterations
		for(int i=0; i<20;i++)
		{		
			//
			do
			{
			randomSet.add(r.nextInt(500));	
			}while (randomSet.size()<100);  //repeat until each set has 100 items
						
			temp = randomSet.toArray(new Integer[100]);  //put the sets into arrays
			for(int j=0;j<100;j++) //put the random numbers into the heaps
				{
				optimalHeap.insertNoSort(temp[j]);
				notSoOptimalHeap.insertAndSort(temp[j]);
				}
			//call optimal sort for the optimal heap
			optimalHeap.sortHeapOptimal();
			
			//add to the sums
			optimalSwapSum += optimalHeap.getSwaps();
			unOptimalSwapSum += notSoOptimalHeap.getSwaps();
			//reset the heaps
			optimalHeap.resetHeap();
			notSoOptimalHeap.resetHeap();
		}
		
		//calculate averages
		optimalSwapAverage = optimalSwapSum/20 ;
		unOptimalSwapAverage = unOptimalSwapSum/20;
		//print the results
		System.out.println("\nAverage swaps for series of insertions:" + unOptimalSwapAverage);
		System.out.println("Average swaps fot the Optimal Method :" + optimalSwapAverage);
		
	}
	
	private static void Option2()
	{
		MaxHeap optimalHeap = new MaxHeap(100);
		MaxHeap notSoOptimalHeap = new MaxHeap(100);
		
		//populate the heaps
		for (int i=1;i<=100;i++)
		{
		optimalHeap.insertNoSort(i);
		notSoOptimalHeap.insertAndSort(i);
		}
		
		//sort the optimal
		optimalHeap.sortHeapOptimal();
		
		//results of insertion
		System.out.println("\nHeap built using series of insertions:" + notSoOptimalHeap.toStringFistTen());
		System.out.println("Number of Swaps :" + notSoOptimalHeap.getSwaps());
		//do 10 removals
		for(int j=0;j<10;j++)
			notSoOptimalHeap.remove();
		//print results
		System.out.println("Heap after 10 removals :" + notSoOptimalHeap.toStringFistTen() + "\n");
		
		//results of optimal
		System.out.println("Heap built using optimal method:" + optimalHeap.toStringFistTen());
		System.out.println("Number of Swaps :" + optimalHeap.getSwaps());
		//do 10 removals
		for(int j=0;j<10;j++)
			{
			optimalHeap.remove();
			}
		//print results
		System.out.println("Heap after 10 removals :" + optimalHeap.toStringFistTen() + "\n");
			
		
	}
	
	private static void printMenu()
	{
		System.out.println("====================================================================\n");
		System.out.println("Please select how to test the program:");
		System.out.println("(1) 20 sets of 100 randomly generated integers");
		System.out.println("(2) Fixed integer values 1-100");
		System.out.println("(3) Exit the Program.");
		System.out.println("Enter Choice :");
	}

}
