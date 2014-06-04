package tree;

/**
 * This is the Tri-nary tree class.
 * 
 * @author Rtndp
 * 
 */
public class TrinaryTree {

	/**
	 * ROOT of the tree
	 */
	private Node root;

	/**
	 * This makes up the Node of the tree. Currently, this can only hold integer
	 * values, as this is written to demonstrate the various operations on a
	 * tri-nary tree, for e.g. INSERT, DELETE, PRINT.
	 * 
	 */
	private class Node {
		private Node left, right, center;
		private int value;

		/**
		 * Parameterless constructor.
		 */
		@SuppressWarnings("unused")
		public Node() {

		}

		/**
		 * Parameterized Constructor. This is usually called while inserted a
		 * new node into the tree.
		 * 
		 * @param value
		 */
		public Node(int value) {
			this.left = null;
			this.right = null;
			this.center = null;
			this.value = value;
		}

		/**
		 * The toString() method is overwritten in order to be able to easily
		 * display the contents of the node when required.
		 */
		@Override
		public String toString() {
			return "Node [left=" + left + ", right=" + right + ", center="
					+ center + ", value=" + value + "]";
		}

	}

	/**
	 * This public method is called on a TrinaryTree object to insert a
	 * node in the tree.
	 * 
	 * @param value
	 * 
	 */
	public void insert(int value) {
		root = insertHelper(root, value);
	}

	/**
	 * This private helper function assists the public insert method in
	 * inserting a node in its right position within the tree.
	 * 
	 * @param root
	 * @param value
	 * @return root
	 */
	private Node insertHelper(Node root, int value) {
		/**
		 * If the tree is initially empty, i.e., inserting the first node.
		 */
		if (root == null) {
			return new Node(value);
		}

		/**
		 * Similar to the BST, if the value of the node to be inserted is less
		 * than the root, insertHelper is recursively called on the left subtree.
		 */
		if (value < root.value)
			root.left = insertHelper(root.left, value);

		/**
		 * and if the value of the node to be inserted is greater than the root,
		 * insertHelper is recursively called on the right subtree.
		 */
		else if (value > root.value)
			root.right = insertHelper(root.right, value);

		/**
		 * This condition is written with the purpose of handling the special
		 * case when the value of the node to be inserted is equal to the root.
		 * 
		 */
		else if (value == root.value)
			root.center = insertHelper(root.center, value);
		else

			/***
			 * Once the appropriate position for the new node is found, the new
			 * node is affixed to it appropriate pointer from the previous call.
			 */
			root.value = value;

		return root;
	}

	/**
	 * This public method is called on a TrinaryTree object to delete a
	 * specified node in the tree.
	 * 
	 * @param key
	 * 
	 */
	public void delete(int key) {
		root = deleteHelper(root, key);
	}

	/**
	 * This private helper function assists the public delete method in deleting
	 * the node with the specified key.
	 * 
	 * Assumption - The tree structure can have multiple nodes with the same
	 * value, therefore this delete method deletes only one node with the
	 * specified key.
	 * 
	 * @param root
	 * @param key
	 * @return
	 */
	private Node deleteHelper(Node root, int key) {
		/**
		 * This condition handles the case where the tree is null
		 */
		if (root == null){
			System.out.println("Node with value - "+key+" not found");
			return root = null;
		
		}
		/**
		 * Similar to traditional BST, the search for the required key proceeds
		 * recursively through the left subtree, if the key < root.value or the
		 * right subtree, if the key > root.value
		 */
		if (key < root.value)
			root.left = deleteHelper(root.left, key);

		else if (key > root.value)
			root.right = deleteHelper(root.right, key);
		else {
			/**
			 * If the required key is found at some node in the tree. Two
			 * conditions arise.
			 * 
			 * Condition 1 - it may have a center child node [which is equal to
			 * the its own value], in which case, the node to be deleted is
			 * replaced with its center child.
			 */
			if (root.center != null) {
				root.center.left = root.left;
				root.center.right = root.right;
				return root.center;
			} else {
				/**
				 * Condition 2 - it may not have a center child node, in which
				 * case, the delete processed similar to the delete in BST,
				 * taking three cases into consideration.
				 * 
				 * Case 1 - The node to be deleted is the leaf node
				 */

				if (root.right == null && root.left == null)
					return root = null;

				/**
				 * Case 2 - The node to be deleted has only one child, the left
				 * child (subtree), in which case, replace the node to be
				 * deleted with its immediate left child
				 */
				if (root.right == null)
					return root.left;

				/**
				 * Case 2 (contd.) - The node to be deleted has only one child,
				 * the right child (subtree), in which case, replace the node to
				 * be deleted with its immediate left child
				 */
				if (root.left == null)
					return root.right;

				/**
				 * Case 3 - The node to be deleted has both its children, in
				 * which case replace the node to be deleted with the minimum
				 * node in its right subtree
				 */

				Node temp = root;
				root = findMin(temp.right);
				root.right = removeMin(temp.right);
				root.left = temp.left;

			}

		}
		return root;

	}

	/**
	 * This private helper function assists the delete method in looking up
	 * the minimum value in the tree rooted at node passed as a parameter 
	 * to this method
	 *  
	 * @param root
	 * @return
	 */
	private Node findMin(Node root) {
		
		if (root.left == null)
			return root;

		/**
		 * Keeps calling the left subtree, till the leaf is reached
		 */
		return findMin(root.left);
	}
	
	/**
	 * This private helper function assists the delete method in deleting the
	 * smallest node in the tree or the specific subtree
	 * 
	 * @param root
	 * @return
	 */
	private Node removeMin(Node root) {
		if (root.left == null)
			return root.right;
		
		root.left = removeMin(root.left);

		return root;
	}

	/**
	 * 
	 * This public method is called on a TrinaryTree object to display the
	 * entire tree.
	 * 
	 */
	public void print() {
		printHelper(root);
		System.out.println();
	}

	/**
	 * This private helper method assists the print method in displaying
	 * the entire tree.
	 * 
	 * The traversal considered for this print method - INORDER
	 * 
	 * @param root
	 */
	private void printHelper(Node root) {
		if (root == null) {
			return;
		}

		printHelper(root.left);
		printHelper(root.center);
		System.out.print(root.value + " ");
		printHelper(root.right);

	}
	
	/**
	 * I have written this main method to perform the necessary tests.
	 * @param args
	 */
	public static void main(String[] args) {
		TrinaryTree tree = new TrinaryTree();

		System.out.println("---");
		tree.delete(9);
		tree.print();
		
		tree.insert(5);
		
		tree.insert(4);
		
		tree.insert(9);
		
		System.out.println("---");
		tree.insert(9);
		tree.print();
		
		tree.insert(5);
		
		tree.insert(7);
		
		System.out.println("---");
		tree.insert(2);
		tree.print();
		
		
		tree.insert(2);
		
		tree.insert(5);
		
		tree.insert(5);
		
		tree.insert(3);
		
		tree.insert(12);
		
		tree.insert(11);
		
		tree.insert(13);
		
		tree.insert(6);
		
		tree.insert(1);

		System.out.println("---");
		tree.print();

		System.out.println("---");
		tree.delete(9);
		tree.print();

		System.out.println("---");
		tree.delete(9);
		tree.print();

		System.out.println("---");
		tree.delete(18);
		tree.print();

		System.out.println("---");
		tree.delete(11);
		tree.print();

		System.out.println("---");
		tree.delete(13);
		tree.print();

		System.out.println("---");
		tree.delete(5);
		tree.print();

		System.out.println("---");
		tree.delete(5);
		tree.print();

		System.out.println("---");
		tree.delete(5);
		tree.print();

		System.out.println("---");
		tree.delete(5);
		tree.print();

		System.out.println("---");
		tree.delete(5);
		tree.print();

		System.out.println("---");
		tree.delete(5);
		tree.print();

		System.out.println("---");
		tree.delete(5);
		tree.print();

	}

}
