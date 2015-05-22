/**
 * 
 Class MaxHeap
 * 
 * Description: This class will include methods to build a heap using both
 * sequential insertions and the optimal method. The heap class only works for
 * positive integers for now!  Someday I'll add the three lines or so of code to
 * initialize the heap at the min value of an int so that negative values would work.
 * 
 * 
 * @author Philip Sloan
 * Date: May 14th, 2015
 */
public class MaxHeap {

	int swaps=0; //this variable will track the number of swaps performed in constructing the heap
	int elements=0; //this variable will track the number of elements in the heap
	int size =100; //default size will be 100
	int[] heap;
	
	boolean isSorted = false; //this variable will track the state of the heap, being true when the heap is sorted
	
	
	
	/**
	 * Default constuctor makes a heap with a size of 100
	 * 
	 */
	public MaxHeap()
	{
		size= 100;
		heap = new int[size];
	}
	
	
	
	
	
	/***************************************************************************************
	 * Constructor Method to create an empty array that will be the heap.
	 * 
	 * @param size
	 **************************************************************************************/
	public MaxHeap(int inputSize)
	{
		size=inputSize;
		heap = new int[size];
	} //end constructor
	
	
	/***************************************************************************************
	 * This method will return the number of swaps performed so far on this heap.
	 * 
	 * 
	 * @return The number of swaps performed in this heap
	 **************************************************************************************/
	public int getSwaps()
	{
	return swaps;	
	}//end getSwaps

	/**
	 * This is a method to return the number of elements in the array.
	 * 
	 * @return number of elements in the heap
	 */
	public int getNumberOfElements()
	{
		return elements;
	}
	
	
	
	/***************************************************************************************
	 * This Method will simply insert a value into the heap array without sorting the heap.
	 * 
	 * 
	 * 
	 * @param inputElement
	 * @return true if value successfully inserted
	 **************************************************************************************/
	public boolean insertNoSort(int inputElement)
	{
		if (elements == size) //the array is full
			return false;
		else
		{
			heap[elements] = inputElement; //put the new element in the first empty spot in the array
			elements++; //increment the number of elements
			isSorted = false; //set the heap state to unsorted;
			return true; //return true to indicate success
		}
	}//end insertNoSort
	
	
	/**
	 * This method is used for sequentially adding values and resorting the heap.
	 * It will add the value to the first open spot and then it will call the {@link MaxHeap#heapifyUp() heapifyUp} 
	 * method to 
	 * 
	 * @param inputElement
	 * @return true if the value was successfully added
	 */
	public boolean insertAndSort(int inputElement)
	{
		if (elements == size)
			return false;
		else
		{
			heap[elements] = inputElement;
			elements++;
			isSorted = false;
			heapifyUp();
		}
		return isSorted;
	}//end insertAndSort
	
	
	/**************************************************************************************
	 * This method will remove the greatest value of the max heap (the root)
	 * and then replace if with the last element in the heap, and call the {@link MaxHeap#heapifyDown() heapifyDown} 
	 * method to re-sort the heap.
	 * 
	 * @return the Max value in the heap 
	 *************************************************************************************/
	public int remove(){
		int max = heap[0]; //set the return variable to the root
		heap[0] = heap[elements-1]; //replace the root with the last element
		heap[elements-1]= 0; //zero out the last space
		elements--; //decrement the elements
		isSorted = false; //set the tree state to unsorted
		heapifyDown(0);
		
		return max;
	}//end remove()
	
	
	/***************************************************************************************
	 * This method will sort the array into a max heap, using the optimal method.
	 * 
	 **************************************************************************************/
	public void sortHeapOptimal(){
	for(int i=(elements-2)/2;i>=0;i--)
		{
			isSorted = false; //make sure this gets reset each time :P
			heapifyDown(i);	 
		}
	}//end optimalsort
	
	/**************************************************************************************
	 * This method should be used to sort the heap for using the insertion method
	 * of building a heap.
	 * 
	 **************************************************************************************/
	public void heapifyUp(){
		int i = elements-1; //set cursor to the most recently added element
		int temp =0; //temporary swap variable
		
		while(!isSorted)
		{
			if(heap[i]>heap[(i-1)/2]) //the value is greater the the parent
			{
				temp = heap [i];
				heap[i]= heap[(i-1)/2];
				heap[(i-1)/2] = temp;
				swaps++; //a swap was performed
				i = (i-1)/2;
			}
			
			if (i == 0) //we are at the root (checking here to avoid index out of bounds)
				isSorted = true;
			else if (heap[i]<heap[(i-1)/2])
				isSorted = true;
			
		}
		
	}//end heapify up
	
	
	/***************************************************************************************
	 * This method will be used during the removal of the root.  It will take the root and 
	 * move it down in the heap until the highest value is at the top again, and no parent
	 * has a greater value then the child.
	 * 
	 * @param i The index to start from.
	 **************************************************************************************/
	private void heapifyDown(int i){
		int temp = 0; //temp variable for swapping
		
		while(!isSorted)
		{
			if((2*i+1)<=elements-1) //check if the cursor is at a leaf
			{
				if ((2*i+2 > elements-1))  //if there is only a left child
				{
					if (heap[i]<heap[2*i+1])
					{
					temp = heap[i];
					heap[i] = heap[2*i+1];
					heap[2*i+1] = temp;
					swaps++;  //a swap was performed
					i= 2*i+1;
					}
					else if(heap[i]>heap[2*i+1])
					{
						isSorted = true;
					}
				}
				else if(2*i+2 <=elements-1) 
				{
					if((heap[i]<heap[2*i+1])||(heap[i]<heap[2*i+2])) 		//check if the value is less then children
					{
						if(heap[2*i+1]>heap[2*i+2])      //swap with the largest child
						{
							temp = heap[i];
							heap[i] = heap[2*i+1];
							heap[2*i+1] = temp;
							swaps++;  //a swap was performed
							i= 2*i+1;
						}
						else //swap with the other child
						{
							temp = heap[i];
							heap[i] = heap[2*i+2];
							heap[2*i+2] = temp;
							swaps++;  //a swap was performed
							i= 2*i+2;
						}	
					}
					else if((heap[i]>heap[2*i+1])&&(heap[i]>heap[2*i+2])) //check to verify sorted 
						isSorted = true;	
				}	
			}
			else  //cursor is at a leaf
				isSorted = true;
		
		}//end of while loop				
	}//end of heapify down
	
	
	
	
	/**
	 * Resets the heap
	 */
	public void resetHeap(){
		swaps = 0;
		elements = 0;
		heap = new int[size];
	}
	
	
	
	
	
	/**
	 * This method will return the first 10 values of a heap in string form.
	 * 
	 * @return a string of the first 10 values of the heap followed by an ellipse
	 */
	public String toStringFistTen()
	{
		String output;
		
		output = heap[0] + ", " +
				 heap[1] + ", " +
				 heap[2] + ", " +
				 heap[3] + ", " +
				 heap[4] + ", " +
				 heap[5] + ", " +
				 heap[6] + ", " +
				 heap[7] + ", " +
				 heap[8] + ", " +
				 heap[9] + "... ";
		
		return output;
	}
	
	
}// end of class
