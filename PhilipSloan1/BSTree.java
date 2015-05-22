/**
 * This Class uses the BT node class to create a tree of the 
 * Integer type.  It has a single variable which is the root node of a binary search tree.
 * 
 * Class: CS 241
 * Date: April 26, 2015
 * @author Philip Sloan prsloan@cpp.edu
 * 
 *
 */
public class BSTree
{
	
	BTNode<Integer> root;

	
	
	/**
	 *   Default Constructor. Creates an empty Tree
	 */
	public BSTree()
	{
		root = null;
	}

	
	/**
	 * Creates a tree based on an initial value with no children
	 * 
	 * 
	 * @param initialData
	 */
	public BSTree(int initialData)
	{
		root = new BTNode<Integer>(initialData, null, null);
	}
	
	
	/**
	 * Creates a new tree from a node.
	 * 
	 * 
	 * @param initialRoot
	 */
	public BSTree(BTNode<Integer> initialRoot)
	{
		root = initialRoot;
	}
	
	
	
	/**
	 * Returns the root of the tree.
	 * 
	 * 
	 * @return
	 */
	public BTNode<Integer> getRoot()
	{
		return root;
	}
	
	/**
	 * This method will add a node in a binary search tree.  It will recursively call itself until
	 * it reaches the bottom of the tree and places the number in the proper place.
	 * 
	 * 
	 * @param element
	 * @param cursor
	 * @return true if the add was successful, false if the element was already in the tree
	 */
	public boolean add(int element, BTNode<Integer> cursor )
	{
		boolean success = false;
		
		if (cursor == null)//empty tree
		{
		root = new BTNode<Integer>(element, null, null);	
		}
			
		else if (element < cursor.getData())
		{
			if(cursor.getLeft() == null)					//check if left is empty
			{
				cursor.setLeft(new BTNode<Integer>(element, null, null)); //add data to left
				success = true;
			}
			else
			{
				success = add(element, cursor.getLeft());
			}			
		}
		else if (element > cursor.getData())
			if(cursor.getRight() == null)					//check if right is empty
			{
				cursor.setRight(new BTNode<Integer>(element, null, null))	; //add data to right
				success = true;
			}
			else
			{
				success = add(element, cursor.getRight());
			}	
		return success;
	}
	
	/*********************************************************************************
	 * 
	 * This method recursively looks through a tree for an element and if it finds it, it
	 * will remove it from the tree and return a true value.
	 * 
	 * 
	 * @param element  
	 * @param cursor
	 * @return True if element was successfully removed, false otherwise.
	 ********************************************************************************/
	public BTNode<Integer> remove(int element, BTNode<Integer> cursor)
	{

		
		if  (element < cursor.getData()) //go down the left side of the tree
		{
			cursor.setLeft(this.remove(element, cursor.getLeft()));
		}
		else if(element > cursor.getData()) //go down the right side of the tree
		{
			cursor.setRight(this.remove(element, cursor.getRight()));
		}
		else //found the element
		{
			if (cursor.getLeft()==null) //node has no left children, so simply replace it with the right
			{
				cursor = cursor.getRight();
			}
			else//there are left children, so replace the node with the data from the right most on the left, and then delete that node
			{
				cursor.setData(cursor.getLeft().getRightmostData());
				cursor.setLeft(cursor.getLeft().removeRightmost());
			}
		}
		
		
		return cursor;  //finally return if the element was successfully removed
	}
	
	
	/*********************************************************************************
	 * 
	  * This method searches through a tree for an element and returns the in order 
	 * predecessor of that element.  
	 * 
	 * @param element
	 * @param cursor
	 * @param predecessor
	 * @return The in-order predecessor of the element
	 ********************************************************************************/
	public int getPredecessor(int element, BTNode<Integer> cursor, int predecessor) 
	{
		
		//check if the element is in the cursor
		if ((element==cursor.getData()) && (cursor.getLeft()!=null))
		{
			predecessor = cursor.getLeft().getRightmostData();
		}
		// if element is smaller then cursor, search left
		else if (element<cursor.getData())
		{
			predecessor = getPredecessor(element, cursor.getLeft(), predecessor);
		}
		else if ((element>cursor.getData()) && (cursor.getRight()!=null))
		{
			predecessor = cursor.getData();
			predecessor = getPredecessor(element, cursor.getRight(), predecessor);
			
		}
		
		return predecessor;
	}
	
	
	/********************************************************************************
	 * 
	 * This method searches through a tree for an element and returns the in order 
	 * successor of that element.  
	 * 
	 * @param element
	 * @param cursor
	 * @param successor
	 * @return the in-order Successor of an element
	 ********************************************************************************/
	public int getSuccessor(int element, BTNode<Integer> cursor, int successor) 
	{
		
		//check if the element is in the cursor
		if ((element==cursor.getData()) && (cursor.getRight()!=null))
		{
			successor = cursor.getRight().getLeftmostData();
		}
		// if element is smaller then cursor, search left
		else if (element<cursor.getData())
		{
			successor = cursor.getData();
			successor = getSuccessor(element, cursor.getLeft(), successor);
		}
		else if ((element>cursor.getData()) && (cursor.getRight()!=null))
		{
			successor = getSuccessor(element, cursor.getRight(), successor);			
		}
		
		return successor;
	}
	
	
	/**
	 * This method will check to see if a specific element is in the tree.
	 * @param element
	 * @return True if the element exists, false otherwise.
	 */
	public boolean contains(int element, BTNode<Integer> cursor)
	{
		boolean isPresent= false;
		
		if (element == cursor.getData())
		{
			return true;
		}
		if ((cursor.getLeft()!=null)&& (!isPresent))  //check left branch, unless the element has already been found
		{
			isPresent = contains(element, cursor.getLeft());
		}
		if ((cursor.getRight()!=null) && (!isPresent))//check right unless the element has been found
		{
			isPresent = contains(element, cursor.getRight());
		}
		
		return isPresent;
	}
	
	
}
