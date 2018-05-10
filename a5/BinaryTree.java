/*
* Name: Julian Rocha
* ID: V00870460
* Date: Nov 21 2017
* Filename: BinaryTree.java
* Details: CSC115 Assignment 5
*/ 
import java.util.Iterator;
/**
 * BinaryTree is a basic generic BinaryTree data structure.
 * It is referenced based, using TreeNodes to connect the tree.
 * It contains any element determined by the placeholder E.
 * The basic ADT is adapted from <i>Java, Walls &amp; Mirrors,</i> by Prichard and Carrano.
 */
public class BinaryTree<E> {

	/* The root is inherited by any subclass
	 * so it must be protected instead of private.
	 */
	protected TreeNode<E> root;

	/**
	 * Create an empty BinaryTree.
	 */
	public BinaryTree() {
	}

	/**
	 * Create a BinaryTree with a single item.
	 * @param item The only item in the tree.
	 */
	public BinaryTree(E item) {
		root = new TreeNode<E>(item);
	}

	/**
	 * Used only by subclasses and classes in the same package (directory).
	 * @return The root node of the tree.
	 */
	protected TreeNode<E> getRoot() {
		return root;
	}

	/**
	 * Creates a binary tree from a single item for the root and two subtrees.
	 * Once the two subtrees are attached, their references are nullified to prevent
	 * multiple entries into <i>this</i> tree.
	 * @param item The item to be inserted into the root of this tree.
	 * @param leftTree A binary tree, which may be empty.
	 * @param rightTree A binary tree, which may be empty.
	 */
	public BinaryTree(E item, BinaryTree<E> leftTree, BinaryTree<E> rightTree) {
		root = new TreeNode<E>(item);
		attachLeftSubtree(leftTree);
		attachRightSubtree(rightTree);
	}

	/**
	 * Attaches a subtree to the left of the root node, replacing any subtree that was 
	 * previously there.
	 * @param left The new left subtree of the root.
	 *	This subtree may be empty.
	 *	Once attached, this parameter reference is nullified to prevent multiple
	 *	access to <i>this</i> tree.
	 * @throws TreeException if <i>this</i> tree is empty.
	 */
	public void attachLeftSubtree(BinaryTree<E> left) {
		if (root == null) {
			throw new TreeException("Cannot attach to an empty tree.");
		}
		if (left == null) {
			return;
		}
		root.left = left.root;
		left.root.parent = root;
		left = null;
	}

	/**
	 * @return a preorder iterator of the binary tree.
	 */
	public Iterator<E> preorderIterator() {
		return new BinaryTreeIterator<E>("preorder",root);
	}

	/**
	 * @return an inorder iterator of the binary tree.
	 */
	public Iterator<E> inorderIterator() {
		return new BinaryTreeIterator<E>("inorder", root);
	}

	/**
	 * @return a postorder iterator of the binary tree.
	 */
	public Iterator<E> postorderIterator() {
		return new BinaryTreeIterator<E>("postorder",root);

	}

	/**
	* Checks if tree is empty
	* @return True If tree is empty.
	*/
	public boolean isEmpty() 
	{
		return root == null;//if root is null, tree is empty
	} //end of isEmpty method

	/**
	 * Attaches a subtree to the right of the root node, replacing any subtree that was 
	 * previously there.
	 * @param right The new right subtree of the root.
	 *	This subtree may be empty.
	 *	Once attached, this parameter reference is nullified to prevent multiple
	 *	access to <i>this</i> tree.
	 * @throws TreeException if <i>this</i> tree is empty.
	 */
	public void attachRightSubtree(BinaryTree<E> right) 
	{
		if (root == null) //if the tree being attatched to is empty
			throw new TreeException("Cannot attatch to an empty tree.");
		if(right == null) //if subtree to be attatched is empty
			return; //do nothing
		root.right = right.root; //right child of tree root is the root of "right" tree
		right.root.parent = root; //parent of "right" tree is root of tree
		right = null; //"right" tree pointer is destroyed
	}
		
	/**
	* Determines the height of the tree
	* by calling a private recursive height method.
	* @return int Integer number for height of tree.
	*/	
	public int height() 
	{
		return height(root); //call recursive height method, starting at root
	} //end of height method

	
	/**
	* Private recursive height method called by public height method
	* @param node Current TreeNode position in the tree.
	* @return int Integer number for height of tree.
	*/
	private int height(TreeNode<E> node) {
		if(isEmpty()) //if tree is empty
			return 0; //height is 0 for empty tree
		if(node.right == null && node.left == null) //current node has no children
			return 1; //return 1
		if(node.left == null) //current node has only a right child
			return 1 + height(node.right); //return 1 + height of right subtree
		if(node.right == null) //current node has only a left child
			return 1 + height(node.left); //return 1 + height of left subtree
		//The below cases are for when the current node has two children
		if(height(node.left) > height(node.right)) //if left height is greater than right height
			return 1 + height(node.left); //return 1 + height of left subtree
		else //if right height is greater than left height
			return 1 + height(node.right); //return 1 + height of right subtree
	} //end of recursive height method
}

	
