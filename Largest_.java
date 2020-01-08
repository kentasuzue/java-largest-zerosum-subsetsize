
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.File;
import java.io.FileNotFoundException;

public class Largest {

	public static void main(String args[]) throws FileNotFoundException {
		// standard I/O Scanner object with input file of list of integers
		Scanner sc = new Scanner(new File("in.txt"));

		// linked list to store list of integers from input file
		LinkedList inputLinkedList = new LinkedList();
		
		// the list of integers in the input text file can be separated by line breaks.
		// the while loop repeats until all lines of input are read from the input text file.
		// the linked list stores the list of integers
		// into the singly linked list in the same order in which they appear in the input file.
		// lines read from the input text file are processed for integers to add to the linked list, in the same order 
		// that the lines appeared within the input text file.
		while (sc.hasNextLine()) {
		
			// standard I/O String object 
			String inputStr = sc.nextLine();

			// standard I/O StringTokenizer object
			// the list of integers in the input text file can be separated by spaces. 
			// tokens, integers in this case, are separated by spaces, and tokens are identified by StringTokenizer.
			StringTokenizer strToken = new StringTokenizer(inputStr);

			// the linked list stores the integers from the input file
			// into a singly linked list in same order in which they appear in the input file.
			// within a line of input, integers are added to the linked list in the same order that 
			// the integers appeared within the line of input.
			// the while loop repeats until all tokens, integers in this case, are added to the linked list
			while (strToken.hasMoreTokens()) {
			
				int nextInt = Integer.parseInt(strToken.nextToken());
				inputLinkedList.addNode(new Node(nextInt));
//				System.out.println(nextInt);
			}
		}
		
		// after the linked list stores the list of integers from the text file,
		// the program does not modify the linked list, and the linked list remains in its original form 
		// as it was read from the input file in.txt 
		
		// "in.txt" contains at least one integer, so the linked list contains at least one integer. 

		
//		System.out.println();

/*
		Node nodeIter = inputLinkedList.getHead();
		
		while (nodeIter != null) {
			
			int nextInt = nodeIter.getData();
			
			System.out.println(nextInt);
			
			nodeIter = nodeIter.getNode();
		}

		System.out.println();
*/
		// call to recursive method with arguments, in order:
		// (i) head node of linked list,
		// (ii) running total of data from nodes, which starts as 0, and
		// (iii) running total of nodes of data added so far, which starts as 0
		//
		// print the size of the largest zero-sum subset,
		// which is the return value of the call to the recursive method.
		System.out.println(recursive(inputLinkedList.getHead(), 0, 0));
	}
	
	public static int recursive (Node node, int intSum, int nodeCount) {
		// Base case
		if (node  == null) { // Base case reached, since the end of a linked list points to null.
			if (intSum == 0) { // If the running total of data from nodes is zero,
				return nodeCount; // return the running total of nodes of data added so far.
			} 
			else {
				return 0; // Else, the running total of data from nodes is nonzero,
						// so return zero to indicate failure of this base case of subset of integers to sum to 0.
			}
		} 

		// Recursive cases

		// Recursive case #1: The integer addNodeInt stores the number of nodes of data added so far.
		// In recursive case #1, this node’s data adds to the running total of data from nodes (intSum). 
		// So recursive case #1: (i) adds this node’s data to the running total of data from nodes (intSum),
		// and (ii) increases the running total of nodes of data added so far (nodeCount) by 1.
			//		System.out.println("recursive call with " + node.getNode() + ", " + (intSum + node.getData()) + " " + nodeCount+1);
		int addNodeInt = recursive (node.getNode(), intSum + node.getData(), nodeCount + 1);

		// Recursive case #2: The integer dontAddNodeInt stores number of nodes of data added so far.
		// In recursive case #2, this node’s data does NOT add to the running total of data from nodes (intSum). 
		// So recursive case #2: (i) leaves the running total of data from nodes (intSum) unchanged,
		// and (ii) leaves the running total of nodes of data added so far (nodeCount) unchanged.
			//		System.out.println("recursive call with " + node.getNode() + ", " + intSum + " " + nodeCount);
		int dontAddNodeInt = recursive(node.getNode(), intSum, nodeCount);

		// Following the recursive cases #1 and #2, the return value is the larger of the two return values
		// from the recursive cases #1 and #2.
		// A tie is possible, but then it doesn’t matter which one’s the return value.
		if (addNodeInt > dontAddNodeInt) {
			return addNodeInt;
		}
		else {
			return dontAddNodeInt;
		}
	}


	// Node class that makes up each node data member of LinkedList class 
	static class Node {
		
		// each node includes only only two elements: 
		// an integer and a reference to the next element  
		
		// data stored in Node
		private int nodeData;
		
		// single link only, to next Node
		private Node nodeNext;
		
		// constructor sets data member to parameter, sets link member to next Node to null; 
		Node(int newData) {
			nodeData = newData;
			nodeNext = null;
		}
		
		// setter method sets link member to parameter Node 
		public void setNode(Node newNode) {
			nodeNext = newNode;
		}
		
		// getter method returns data member
		public int getData() {
			return nodeData;
		}
		
		// getter method returns link member to next Node 
		public Node getNode() {
			return nodeNext;
		}	
	}
	
	// LinkedList class to store input file's list of integers
	// linked list implemented from scratch
	static class LinkedList {
		
		// link to beginning Node of linked list to begin traversal of linked list
		private Node head;

		// link to last Node of linked list, to simplify addition of another Node to linked list
		private Node tail;
		
		// constructor sets link to head Node and tail Node to null
		public void LinkedList() {
			head = null;
			tail = null;
		}
		
		// method adds new Node to linked list with parameter of new Node to be added to linked list
		// if linked list is empty such that both links beginning and last Nodes point to null, then set both links to Node parameter
		// if linked list is not empty: 
		// (i) then Node that was last Node in linked list prior to method call will have its link member point to Node parameter, and
		// (ii) linklist member pointing to last Node (tail) will point to Node parameter.
		
		public void addNode(Node addedNode) {
			
			if (head == null) {
				head = addedNode;
			}
			
			if (tail == null) {
				tail = addedNode;
			}
			else {
				tail.setNode(addedNode);
				tail = addedNode;
			}
		}
		
		// getter method returns link member to beginning Node of linked list
		public Node getHead() {
			return head;
		}

		// getter method returns link member to last Node of linked list
		public Node getTail() {
			return tail;
		}
	}
}
