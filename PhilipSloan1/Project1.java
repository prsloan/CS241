
import java.util.Scanner ;

/**
 * Class: CS 241
 * Assignment : Binary Search Tree
 * Date: April 28, 2015
 * 
 * Project Description:  This project implements a generic binary tree node to
 * create a binary search tree that the used can add and remove from.  It also prints
 * out various traversals of the tree.
 *
 * @author Philip Sloan
 *
 */
public class Project1 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		BSTree tree = new BSTree();
		String input = "";
		int inputInt;
		boolean validInput=false; //a variable to make sure the initial tree is entered correctly
		
		while(!validInput)
		{	
			try
			{
				System.out.println("Welcome to the Binary Search Tree Interface! \n"
						+ "Please enter the initial sequence of numbers, seperated by a space:\n");
				
				input = s.nextLine();
				//iterate through the input to populate the tree
				for(String i: input.split("\\s"))
				{
					if (!i.equals(""))  
						tree.add(Integer.parseInt(i), tree.getRoot());
				}
				//if it makes it through the for statement without throwing an exception
				//it can be assumed that the input was valid
				validInput = true;
			}//end of try block
			catch (NumberFormatException e)//catch any exceptions from errors in the input
			{
				System.out.println("Invalid Entry. try again.");
			}//end of catch block
		}//end of input loop validator
		
		
		//Print out the Traversals
		System.out.println("\n Pre-Order Traversal : ") ;
		tree.getRoot().preOrderPrint();
		System.out.println("\n In-Order Traversal : ") ;
		tree.getRoot().inOrderPrint();
		System.out.println("\n Post-Order Traversal : ") ;
		tree.getRoot().postOrderPrint();
		System.out.println("\n");
		
		
		//Make The Menu
		displayMenu();
		System.out.print("Command :");
		input =s.next();	
		input = input.toUpperCase();
		//Loop to get user Input
		while(input!="E")
		{
			try
			{
				// Switch case for the different possible inputs.  If the input needs a number, it looks for that.
				switch (input)
				{
				case "I":
					inputInt = s.nextInt();
					if (!tree.contains(inputInt, tree.getRoot()))
					{
						tree.add(inputInt, tree.getRoot());
						System.out.println("Successfully added " + inputInt + "\n");
						System.out.print("In-Order Traversal :");
						tree.getRoot().inOrderPrint();
						break;
					}
					else
					{
						System.out.println(inputInt + " is already in tree. Ignore.");
						break;
					}
				case "D":
					inputInt = s.nextInt();
					if (tree.contains(inputInt, tree.getRoot()))   
					{
						tree.remove(inputInt, tree.getRoot()); //send the root in as the initial parent
						System.out.println("Successfully Removed" + inputInt + "\n");
						System.out.print("In-Order Traversal :");
						tree.getRoot().inOrderPrint();
						break;
					}
					else
					{
						System.out.println(inputInt + " is not in tree.");
						break;
					}
				case "P":
					inputInt = s.nextInt();
					//check if the element is in the tree and the element is not the minimum value
					if ((tree.contains(inputInt, tree.getRoot()))&& (inputInt!= tree.getRoot().getLeftmostData()))
					{
						System.out.println(tree.getPredecessor(inputInt, tree.getRoot(), 0));
						break;
					}
					//if input int is the minimum, send an error message 
					else if (inputInt == tree.getRoot().getLeftmostData())
					{
						System.out.println(inputInt + " is the minimum value of the tree.Try another number.");
						break;
					}
					else
					{
						System.out.println(inputInt + " is not in tree.Ignore.");
						break;
					}
				case "S":
					inputInt = s.nextInt();
					//check if inputInt is the Max value in the tree and is in it.
					if ((tree.contains(inputInt, tree.getRoot()))&& (inputInt!= tree.getRoot().getRightmostData()))
					{
						System.out.println(tree.getSuccessor(inputInt, tree.getRoot(), 0));
						break;
					}
					//if the input is the maximum vale, send an error message.
					else if(inputInt!= tree.getRoot().getRightmostData())
					{
						System.out.println(inputInt + "is the maximum value of the tree. Try another number.");
					}
					else
					{
						System.out.println(inputInt + " is not in tree.Ignore.");
						break;
					}
				case "H":
					displayMenu();
					break;
				case "E":
					//close the scanner
					s.close();
					//Say goodbye
					System.out.println( "\n Thanks for using me. Goodbye!");
					System.exit(0);
					break;
				default:
					System.out.println("Invalid entry.  Please try again.");	
				}//end of switch case
			}//end of try block
			catch(NumberFormatException e)
			{
				System.out.println(" Invalid entry. You must enter a command and a number, seperated by a space.");
			}//end of catch block
			
			//get the next command
			System.out.print("\nCommand :");
			input = s.next(); 
			input = input.toUpperCase();
		}//end of menu loop
		
		//If E is the first choice, then exit the program
		//close the scanner
		s.close();
		//Say goodbye
		System.out.println( "\n Thanks for using me. Goodbye!");
		System.exit(0);
		
	}// end of main method
	
	
	/**
	 * This method will print the Menu.
	 */
	private static void displayMenu() {
		System.out.print("I  Insert a Value\n"
				+ "D  Delete a value\n"
				+ "P  Find Predecessor\n"
				+ "S  Find Successor\n"
				+ "E  Exit the Program\n"
				+ "H  Display this Message\n");
		
	}

}//end of class
