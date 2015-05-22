/**
 * Date: April 28, 2015
 * 
 * This is a generic tree node for a binary tree node.  Each node will have a left and a right.
 * Their initial values will be null.  Each node is the root of its own tree.
 * 
 * 
 * @author Philip Sloan
 *
 * @param <E>
 */
public class BTNode <E> {

private E data;
private BTNode<E> left;  //the left node
private BTNode<E> right; //the right node
	
//Constructor Methods

/**
 * Constructs a Node with the data and links to children Nodes
 * 
 * @param initialData  The initial data for the node.
 * @param initialLeft  The initial left link for the node.
 * @param initialRight The initial right link for the node.
 */
public BTNode (E initialData, BTNode<E> initialLeft, BTNode<E> initialRight)
{
data = initialData;
left = initialLeft;
right = initialRight;

}

/*
 * Mutator Methods   
 */

/**********************************************
 * Returns the data in the Node
 * 
 * @return
 *********************************************/
public E getData()
{
	return data;
}


/*********************************************
 * 
 * Returns the left child of the node.
 * 
 * @return
 *********************************************/
public BTNode<E> getLeft()
{
	return left;
}


/**********************************************
 * Returns the right child of the node
 * 
 * @return
 *********************************************/
public BTNode<E> getRight()
{
	return right;
}


/**********************************************
 * Sets the Data of the Node with new data
 * 
 * @param newData
 *********************************************/
public void setData(E newData)
{
	data= newData;
}


/**********************************************
 * Sets the left child of the Node to the Node being passed into the method.
 * 
 * @param newLeft
 *********************************************/
public void setLeft(BTNode<E> newLeft)
{
	left = newLeft;
}


/**********************************************
 * Sets the right child of the Node to the node being passed into the method.
 * 
 * @param newRight
 *********************************************/
public void setRight(BTNode<E> newRight)
{
	right=newRight;
}

/**********************************************
 * This method will recursively calculate the height of a tree from a specific node.
 * @param node
 * @return The height of the tree from the called node.
 **********************************************/
public static <E> int height(BTNode<E> node)
{
	return 0;
}

/***********************************************
 * This method checks if the node has any children, if it does not, then the node is
 * a leaf and the method returns true.
 * 
 * @return True if the node is a leaf.
 **********************************************/
public boolean isLeaf()
{
	boolean isLeaf = false;
	
	if ((left == null) && (right == null))
		isLeaf = true;
	
	return isLeaf;
}

/***********************************************
 * This method recursively traverses the left branch of the binary tree to get the Left Most Value
 * 
 * @return The data of the left most node.
 **********************************************/
public E getLeftmostData()
{
	if (left== null)
		return data;
	else
		return left.getLeftmostData();
}

/***********************************************
 * This method recursively traverses the right branch of the binary tree to get the Left Most Value
 * 
 * @return The data of the right most node.
 **********************************************/
public E getRightmostData()
{
	if (right== null)
		return data;
	else
		return right.getRightmostData();
}

/***********************************************
 * 
 * This method will remove the right most node from the tree.
 * 
 * @return
 ***********************************************/
public BTNode<E> removeRightmost()
{
	 if (right == null)
	 {
         return left;
	 }
     else
     {
         right = right.removeRightmost( );
         return this;
     }
}

/************************************************
 * 
 * This method will remove the left most node from the tree.
 * 
 * @return
 ***********************************************/
public BTNode<E> removeLeftmost()
{
	// the node that activates removeLeftmost has no left child, thus is itself the leftmost node of the subtree
	if (left == null) 
		{
		return right;
		} 
	else 
		{
	    // a recursive call removes the leftmost node from my left subtree
	    return left.removeLeftmost();  
		}
}

/**
 * This method will print the in order traversal using recursion.
 */
public void inOrderPrint()
{
	if (left !=null)
		left.inOrderPrint();
	System.out.print(data + " ");
	if (right != null)
		right.inOrderPrint();
}

/**
 *  This method will print the pre order traversal using recursion.
 */
public void preOrderPrint()
{
	System.out.print(data + " ");
	if (left !=null)
		left.preOrderPrint();
	if (right != null)
		right.preOrderPrint();
}

/**
 * This method will print the post order traversal using recursion.
 */
public void postOrderPrint()
{
	if (left !=null)
		left.postOrderPrint();
	if (right != null)
		right.postOrderPrint();
	System.out.print(data + " ");
}


}


