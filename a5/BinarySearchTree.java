/*
* Name: Julian Rocha
* ID: V00870460
* Date: Nov 21 2017
* Filename: BinarySearchTree.java
* Details: CSC115 Assignment 5
*/ 

import java.util.Iterator;

/**
 * BinarySearchTree is an ordered binary tree, where the element in each node
 * comes after all the elements in the left subtree rooted at that node
 * and before all the elements in the right subtree rooted at that node.
 * For this assignment, we can assume that there are no elements that are
 * identical in this tree. 
 * A small modification will allow duplicates.
 */
public class BinarySearchTree<E extends Comparable<E>> extends BinaryTree<E> {

	// the root is inherited from BinaryTree.

	/**
	 * Create an empty BinarySearchTree.
	 */
	public BinarySearchTree() {
		super();
	}

	/**
	 * Creates a BinarySearchTree with a single item.
	 * @param item The single item in the tree.
	 */
	public BinarySearchTree(E item) {
		super(item);
	}

	/**
 	 * <b>This method is not allowed in a BinarySearchTree.</b>
	 * It's description from the subclass:<br>
	 * <br>
	 * {@inheritDoc}
	 * @throws UnsupportedOperationException if this method is invoked.
	 */
	public void attachLeftSubtree(BinaryTree<E> left) {
		throw new UnsupportedOperationException();
	}

	/**
 	 * <b>This method is not allowed in a BinarySearchTree.</b>
	 * It's description from the subclass:<br>
	 * <br>
	 * {@inheritDoc}
	 * @throws UnsupportedOperationException if this method is invoked.
	 */
	public void attachRightSubtree(BinaryTree<E> right) 
	{
		throw new UnsupportedOperationException();
	}

	/**
	* This method will insert an item into a tree, using E for generics.
	* Calls a recursive helper method.
	* @param E item, Item to be inserted
	*/
	public void insert(E item) 
	{
		insert(root, item); //call recursive methhod of insert with the item and the tree root
	} //end of insert method

	/**
	* Recusrsive method called by insert method.
	* Inserts an item in the acceptabe open spot in the tree
	* @param node Root node initially, the current place in the tree
	* @param item Item to be added to tree
	*/
	private void insert(TreeNode<E> node, E item)
	{
		if(root == null) //if tree is empty, insert item as root
			root = new TreeNode<E>(item);
		else if(item.compareTo(node.item) > 0) //if item belongs on the right of current node
		{
			if(node.right != null) //if the right child is occupied
				insert(node.right, item); //test insertion again on right child
			else //if the right child is free
				node.right = new TreeNode<E>(item); //insert the item on the right, done...
		}
		else //if item belongs on the left of current node
		{
			if(node.left != null) //if the left child is occupied
				insert(node.left, item); //test insertion again on left child
			else //if the left child is free
				node.left = new TreeNode<E>(item); //insert the item on the left, done...
		}
	} //end of insert recursive method
	
	/**
	* Finds the item if it is found in the tree.
	* Calls a recursive method.
	* @return item The item if it is found, null if it is not found
	* @param item Item to check if it is in tree
	*/
	public E retrieve(E item) 
	{
		return retrieve(root, item); //call the recursive method of retrieve with the item and the tree root
	} //end of retrieve method

	/**
	* Recursive method to find item in tree
	* @param node Root initially, current spot in tree
	* @param item Item to check if it is in tree
	*/
	private E retrieve(TreeNode<E> node, E item)
	{
		if(node == null)//base case, if the path taken resulted in null
			return null; //item was not in tree, return null
		else if(item.compareTo(node.item) > 0) //if item will be found on right side of tree
			return retrieve(node.right, item); //check again on right child
		else if(item.compareTo(node.item) < 0) //if item will be found on left side of tree
			return retrieve(node.left, item);  //check again on left child
		return item; //the current node is item, return the item
	} //end of retrieve recursive method


	/**
	* Removes and returns an item from the tree if it is there.
	* If item has 2 children, it is replaced with leftmost successor
	* Calls recursive method.
	* @param item Item to be removed
	* @return Item if it is found, null otherwise
	*/
	public E delete(E item) {
		TreeNode<E> node = new TreeNode(item, null, root); //node has root as right child
		return delete(node, item); //call recursive delete method
	} //end of delete method

	/**
	* Recursive method called by delete method.
	* @param node Current place in tree, always points to parent of nodes being checked for deletion
	* @param item Item to find and delete
	* @return Item if it is found
	*/
	private E delete(TreeNode<E> node, E item)
	{
		if(node == null) //if path taken resulted in node being null
			return null; //item wasn't in tree, return null
		else if(node.right != null && item.compareTo(node.right.item) == 0) //if item is right child of node
		{
			if(node.right.right != null && node.right.left != null) //if item has two children
			{
				node.right.item = leftSuc(node.right.right); //replace item with its leftmost successor
				delete(node.right, node.right.item); //shift node to item, delete the leftmost successor
			}
			else if(node.right.right != null) //if item has only a right child
			{
				node.right = node.right.right; //replalce item node with its right child
				return item;
			}
			else if(node.right.left != null) //if item has only a left child
			{
				node.right = node.right.left; //replace item node with its left child
				return item;
			}
			else //if item has no children
			{
				node.right = null; //remove item
				return item;
			}
		}
		else if(node.left != null && item.compareTo(node.left.item) == 0) //if item is left child of node
		{
			if(node.left.right != null && node.left.left != null) //if item has two children
			{
				node.left.item = leftSuc(node.left.right); //replace item with its leftmost successor
				delete(node.left, node.left.item); //shift node to item, delete the leftmost successor
			}
			else if(node.left.right != null) //if item has only a right child
			{
				node.left = node.left.right; //replace item node with its right child
				return item;
			}
			else if(node.left.left != null) //if item has only a left child
			{
				node.left = node.left.left; //replace item node with its left child
				return item;
			}
			else //if item has no children
			{
				node.left = null; //remove item
				return item;
			}

		}
		else if(item.compareTo(node.item) >= 0) //if item should be found on right side of node
			return delete(node.right, item); //search right child
		else if(item.compareTo(node.item) <= 0) //if item should be found on left side of node
			return delete(node.left, item); //search left child
		return null; //return null if program ever reaches this point
	}
	
	/**
	* Private recursive helper method called by recursive delete method
	* Finds the leftmost successor of a node in a tree
	* @param node Place in tree to begin searching left of
	* @return Item which is the leftmot succesor of the node passed
	*/
	private E leftSuc(TreeNode<E> node)//leftmost successor
	{
		if(node.left == null) //if there are no more left children
			return node.item; //return the leftmost item
		else //if there are still left children left
			return leftSuc(node.left); //search the left child
	} //end of leftSuc


	/**
	 * Internal test harness.
	 * @param args Not used.
	 */
	public static void main(String[] args) {
		// NOTE TO STUDENT: something to get you started.
		BinarySearchTree<String> tree = new BinarySearchTree<String>();
		String s1 = new String("optimal");
		String s2 = new String("needs");
		String s3 = new String("programmers");
		String s4 = new String("CSC115");

		System.out.println("Is tree empty?: " + tree.isEmpty()); //test isEmpty on empty tree
		System.out.println("What is height of tree? Should be 0: " + tree.height()); //test height on empty tree

		tree.insert(s1);
		tree.insert(s2);
		tree.insert(s3);
		tree.insert(s4);
		String test = tree.retrieve("needs");
		if (test != null && !test.equals("")) {
			System.out.println("retrieving the node that contains "+s2);
			if (test.equals(s2)) {
				System.out.println("Confirmed");
			} else {
				System.out.println("retrieve returns the wrong item");
			}
		} else {
			System.out.println("retrieve returns nothing when it should not");
		}
		Iterator<String> it = tree.inorderIterator();
		System.out.println("printing out the contents of the tree in sorted order:");
		while (it.hasNext()) {
			System.out.print(it.next()+" ");
		} 
		System.out.println();

		//STUDENT TESTING BELOW//

		//inserting strings to make tree full
		String s5 = new String("nz");
		tree.insert(s5);

		String s6 = new String("pa");
		tree.insert(s6);

		String s7 = new String("z");
		tree.insert(s7);

		//display tree before deletion
		DrawableBTree<String> dbt = new DrawableBTree<String>(tree);
		dbt.showFrame();

		//This delete function has been tested on every node and performs correctly
		tree.delete("programmers");

		//display tree after deletion
		DrawableBTree<String> dbt2 = new DrawableBTree<String>(tree);
		dbt2.showFrame();

		System.out.println("Retrieve pa: " + tree.retrieve("pa"));
		System.out.println("Try to retreive programmers: " + tree.retrieve("programmers"));
		System.out.println("Is tree empty? : " + tree.isEmpty());
		System.out.println("What is height of tree? Should be 3: " + tree.height());

		Iterator<String> inorder = tree.inorderIterator();
		Iterator<String> preorder = tree.preorderIterator();
		Iterator<String> postorder = tree.postorderIterator();
		System.out.println("Printing inorder: ");
		while (inorder.hasNext()) {
			System.out.print(inorder.next()+" ");
		} 
		System.out.println();
		System.out.println("Printing preorder: ");
		while (preorder.hasNext()) {
			System.out.print(preorder.next()+" ");
		}
		System.out.println(); 
		System.out.println("Printing postorder: ");
		while (postorder.hasNext()) {
			System.out.print(postorder.next()+" ");
		} 

	}
}

	

	
