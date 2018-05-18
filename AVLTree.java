


public class AVLTree<E extends Comparable<E>> {

	// root of the tree
		protected Node<E> root = null;
		// current number of nodes in the tree
		protected int numOfElements;
		//helper variable used by the remove methods
		private boolean found;
		
		public AVLTree()
		{
			this.root = null;
			numOfElements = 0;
		}
		
		public AVLTree(Node<E> d)
		{
			this.root = d;
			numOfElements = 0;
		}
		
		/**
		 * Determines the number of elements stored in this BST.
		 *
		 * @return number of elements in this BST
		 */
		public int size() {
			return numOfElements;
		}
		
		/**
		 * Add the given data item to the tree. If item is null, the tree does not
		 * change. If item already exists, the tree does not change.
		 *
		 * @param item the new element to be added to the tree
		 */
		public void addTree(E item) {
			if (item == null)
			{
				System.out.println("Null Value");
				return;
			}
			
			root = add(root, item);
		}

		/*
		 * Actual recursive implementation of add.
		 *
		 * @param item the new element to be added to the tree
		 */
		
		private Node<E> add(Node<E> node, E item) {
			if (node == null) 
			{
				numOfElements++;
				return new Node<E>(item);
			}
			if (node.data.compareTo(item) > 0)
			{
				node.left = add(node.left, item);
			}
			
			else if (node.data.compareTo(item) < 0)
			{
				node.right = add(node.right, item);
			}
			else if(node.data.compareTo(item) == 0)
			{
				return node;
			}
			
			
			//The start of the balancing methods
			
			updateHeight(node);
			node.balanceFactor = balanceFactor(node);
				//Imbalance on left side
				if(node.balanceFactor <= -2)
				{
					//Imbalance on left left
					if(node.left.balanceFactor < 0)
					{
						
						return balanceLL(node);
					}
					if(node.left.balanceFactor > 0)
					{
						
						return balanceLR(node);
					}
				}
				
				//Imbalance on right side
				if(node.balanceFactor >= 2)
				{
					//Imbalance on right right
					if(node.right.balanceFactor > 0)
					{
						
						return balanceRR(node);
					}
					//Imbalance on right left
					if(node.right.balanceFactor < 0)
					{
						
						return balanceRL(node);
					}
				}
			return node;
		
		}
		
		/**
		 * A method that updates the height of specific node in the tree
		 * @param root The specific node in the tree whose height needs to update
		 */
		public void updateHeight(Node<E> root)
		{
			int newHeight = 0;
			
			if(root.right == null && root.left == null)
			{
				newHeight = 0;
			}
			else if(root.right == null)
			{
				newHeight = root.left.height + 1;
			}
			else if(root.left == null)
			{
				newHeight = root.right.height + 1;
			}
			else
			{
				newHeight = Math.max(root.right.height,root.left.height) + 1;
			}
			
			root.height = newHeight;
		}
		
		/**
		 * Method that calculates the balanceFactor of the node
		 * If both left and right nodes are not null then the balance factor is the height of the left child minus the height of the 
		 * right child
		 * 
		 * If 
		 * @param root The Node in which to calculate the balanceFactor
		 * @return The integer of balance factor
		 */
		
		public int balanceFactor(Node<E> root)
		{
			if(root == null)
			{
				return 0;
			}
			if(root.right == null)
			{
				 return -root.height;
			}
			if(root.left == null)
			{
				return root.height;
			}
			
			return root.right.height - root.left.height;
		}
		
		/**
		 * A rotate method balances the node when a subtree left left is unbalanced
		 * @param A The node of the tree that is unbalanced
		 * @return The node after the balancing
		 */
		public Node<E> balanceLL(Node<E> A)
		{
			
			Node<E> B = A.left;
			
			A.left = B.right;
			B.right = A;
			
			updateHeight(A);
			updateHeight(B);
			
			A.balanceFactor = balanceFactor(A);
			B.balanceFactor = balanceFactor(B);
			
//			System.out.println("Calls RotateLL");
			
			return B;
		}
		
		/**
		 * Balances the tree if left right is unbalanced
		 * @param A The node that is unbalanced
		 * @return The node after balancing
		 */
		
		public Node<E> balanceLR(Node<E> A)
		{
			Node<E> B = A.left; 
			Node<E> C = B.right; 
			
			Node<E> Ca = C.left;
			Node<E> Cb = C.right;
			
			C.left = B;
			C.right = A;
			
			B.right = Ca;
			A.left = Cb;
			
			updateHeight(B);
			updateHeight(A);
			updateHeight(C);
			
			//For debugging purposes
			B.balanceFactor = balanceFactor(B);
			A.balanceFactor = balanceFactor(A);
			C.balanceFactor = balanceFactor(C);
			
//			System.out.println("Calls RotateLR");
			
			return C;
		}

		/**
		 * Rotates the tree when subtree right right is unbalanced
		 * @param A The root that is unbalanced
		 * @return The node after the rotate
		 */
		
		public Node<E> balanceRR(Node<E> A)
		{
			
			
			Node<E> B = A.right;
			
			A.right = B.left;
			B.left = A;
			
			updateHeight(A);
			updateHeight(B);
			
			A.balanceFactor = balanceFactor(A);
			B.balanceFactor = balanceFactor(B);
			
//			System.out.println("Calls RotateRR");
			
			return B;
		}
		
		/**
		 * Rotates the tree when subtree right left is unbalanced
		 * @param A The root where imbalance occurs
		 * @return The root after the rotate
		 */
		
		public Node<E> balanceRL(Node<E> A)
		{
			Node<E> B = A.right;
			Node<E> C = B.left;
			
			Node<E> Ca = C.left;
			Node<E> Cb = C.right;
			
			C.left = A;
			C.right = B;
			
			A.right = Ca;
			B.left = Cb;
			
			updateHeight(A);
			updateHeight(B);
			updateHeight(C);
			
			A.balanceFactor = balanceFactor(A);
			B.balanceFactor = balanceFactor(B);
			C.balanceFactor = balanceFactor(C);
			
//			System.out.println("Calls RotateRL");
			
			return C;
			
			
		}
		
		

		//========================================================
		//Print Methods
		//========================================================
		
		/**
		 * DO NOT MOFIFY THIS METHOD.
		 * INCLUDE IT AS-IS IN YOUR CODE.
		 *
		 * Produces tree like string representation of this BST.
		 * @return string containing tree-like representation of this BST.
		 */
		public String toStringTreeFormat() 
		{

			StringBuilder s = new StringBuilder();

			preOrderPrint(root, 0, s);
			return s.toString();
		}

		/*
		 * DO NOT MOFIFY THIS METHOD.
		 * INCLUDE IT AS-IS IN YOUR CODE.
		 *
		 * Actual recursive implementation of preorder traversal to produce tree-like string
		 * representation of this tree.
		 *
		 * @param tree the root of the current subtree
		 * @param level level (depth) of the current recursive call in the tree to
		 *   determine the indentation of each item
		 * @param output the string that accumulated the string representation of this
		 *   BST
		 */
		private void preOrderPrint(Node<E> tree, int level, StringBuilder output) {
			if (tree != null) {
				String spaces = "\n";
				if (level > 0) {
					for (int i = 0; i < level - 1; i++)
						spaces += "   ";
					spaces += "|--";
				}
				output.append(spaces);
				output.append(tree.data);
				
				//Added user data
				output.append(" Height: " + "(" + tree.height + ")");
				output.append(" BalanceFactor (" + tree.balanceFactor + ")");
				
				preOrderPrint(tree.left, level + 1, output);
				preOrderPrint(tree.right , level + 1, output);
			}
			// uncomment the part below to show "null children" in the output
			else {
				String spaces = "\n";
				if (level > 0) {
					for (int i = 0; i < level - 1; i++)
						spaces += "   ";
					spaces += "|--";
				}
				output.append(spaces);
				output.append("null");
			}
		}
		
		public String toString() {
			StringBuilder s = new StringBuilder();
			inOrderPrint(root, s);
			return s.toString();
		}
		
		private void inOrderPrint(Node<E> tree, StringBuilder s) {
			if (tree != null) {
				inOrderPrint(tree.left, s);
				s.append(tree.data.toString() + "  ");
				inOrderPrint(tree.right , s);
			}
		}
}

