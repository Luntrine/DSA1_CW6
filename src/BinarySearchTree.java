public class BinarySearchTree {

	private class BSTNode { // private class to hold a tree node

		private int value;
		private BSTNode leftChild; // left subtree
		private BSTNode rightChild; // right subtree

		public BSTNode(int v) {
			value = v;
			leftChild = null;
			rightChild = null;
		}

		public BSTNode getLeftChild() {
			return leftChild;
		}

		public BSTNode getRightChild() {
			return rightChild;
		}

		public void setLeftChild(BSTNode n) {
			leftChild = n;
		}

		public void setRightChild(BSTNode n) {
			rightChild = n;
		}

		public int getValue() {
			return value;
		}

		// inserting nodes
		public void insert(BSTNode n) {
			// insert somewhere into the left subtree
			if (n.value < this.value) {
				if (this.getLeftChild() == null) {
					// add node n as left child
					this.setLeftChild(n);
				} else {
					// otherwise recurse down the left subtree
					this.getLeftChild().insert(n);
				}
			}

			// insert somewhere into the right subtree
			else if (n.value > this.value) {
				if (this.getRightChild() == null) {
					// add node n as right child
					this.setRightChild(n);
				} else {
					// otherwise recurse down the right subtree
					this.getRightChild().insert(n);
				}
			}

			// a duplicate which we don't allow (could also raise exception)
			else {
				return;
			}
		}

		// lookup a node
		public boolean search(int v) {
			// lookup somewhere into the left subtree
			if (v < this.value) {
				if (this.getLeftChild() == null) {
					// value v cannot be in the tree
					return false;
				} else {
					// otherwise recurse down the left subtree
					return this.getLeftChild().search(v);
				}
			} else if (v > this.value) {
				if (this.getRightChild() == null) {
					// value v cannot be in the tree
					return false;
				} else {
					// otherwise recurse down the right subtree
					return this.getRightChild().search(v);
				}
			}

			// otherwise this.value == v
			return true;
		}

		// useful for the delete method
		public BSTNode getLargestValueNode() {
			// descend down the right subtree until we get the largest value.
			// i.e. until we cannot continue to go down the right subtree
			if (this.getRightChild() == null) {
				return this;
			} else {
				return this.getRightChild().getLargestValueNode();
			}
		}

		// deleting a node with the given value
		public BSTNode delete(int v) {
			// this is not the node we want to remove
			if (v != this.value) {
				// go right?
				if (v > this.value && this.getRightChild() != null) {
					// we're going to update our right child reference
					this.rightChild = this.getRightChild().delete(v);
					return this;
				}

				// go left?
				else if (v < this.value && this.getLeftChild() != null) {
					// we're going to update our left child reference
					this.leftChild = this.getLeftChild().delete(v);
					return this;
				}

				// we are trying to delete a non-existent value
				else {
					return this;
				}
			}

			// this is the node we want to remove
			else {
				// difficult case: does it have two children?
				if (this.getLeftChild() != null && this.getRightChild() != null) {

					// step 1: find the largest value in the left subtree
					BSTNode largestLeft = this.getLeftChild().getLargestValueNode();

					// step 2: set this node's value to the value of the largest left node value
					this.value = largestLeft.getValue();

					// step 3: delete the largest node on left then set Node returned as left child.
					this.leftChild = this.getLeftChild().delete(largestLeft.getValue());

					return this;
				}

				// the easy cases

				// has only left subtree
				else if (this.getLeftChild() != null) {
					// return to the parent node what this node's left child is
					return this.getLeftChild();
				}

				// has only right subtree
				else if (this.getRightChild() != null) {
					// return to the parent node what this node's right child is
					return this.getRightChild();
				}

				// has no subtrees (is a leaf node)
				else {
					System.out.println("Deleting Node with value " + this.value);
					return null;
				}
			}
		}

		public int numberOfNodes() {
			int valueToReturn = 1; // this is 1 to count this node
			if (leftChild != null)
				valueToReturn += leftChild.numberOfNodes(); // add the nodes in the left subtree
			if (rightChild != null)
				valueToReturn += rightChild.numberOfNodes(); // add the nodes in the right subtree
			return valueToReturn; // return the number of nodes
		}

		// Part 1: complete
		/** This method prints the results of the in order traversal of a tree by first going down
		 * the left nodes of the tree (or branch) recursively. It then comes back up the call stack
		 * and prints the value of the node. It then goes down the right nodes of the tree (or 
		 * branch) recursively. It prints the value of every node after going down the the branch to
		 * its left and returning back up to it.
		 * 
		 * Complexity: O(log n)
		 */
		public void inOrderTraversalPrint() {
			//Goes down left branch recursively (if there is one).
			if(this.getLeftChild() != null) {
				this.getLeftChild().inOrderTraversalPrint();
			}
			//Prints value of current node.
			System.out.println(this.getValue());
			
			//Goes down right branch recursively (if there is one).
			if(this.getRightChild() != null) {
				this.getRightChild().inOrderTraversalPrint();
			}
		}

		// Part 2: complete
		/** This method prints the results of the in order traversal of a tree by first going down
		 * the left nodes of the tree (or branch) recursively. It then comes back up the call stack
		 * and adds the value of the node to the tail of the doubly linked list. It then goes down 
		 * the right nodes of the tree (or branch) recursively. It adds the value of the node to the
		 * tail of the doubly linked list after going down the the branch to its left and returning 
		 * back up to it.
		 * 
		 * Complexity: O(log n)
		 *
		 * @param dl is the doubly linked list the node values are added to 'in order'.
		 */
		public void inOrderTraversal(DLinkedList dl) {
			//Goes down left branch recursively (if there is one).
			if(this.getLeftChild() != null) {
				this.getLeftChild().inOrderTraversal(dl);
			}
			
			//Adds value of current node to tail of the doubly linked list.
			dl.addAtTail(this.getValue());
			
			//Goes down right branch recursively (if there is one).
			if(this.getRightChild() != null) {
				this.getRightChild().inOrderTraversal(dl);
			}
		}

	}

	private BSTNode rootNode = null;

	public void insert(int v) {
		if (rootNode == null) {
			rootNode = new BSTNode(v);
		} else {
			rootNode.insert(new BSTNode(v));
		}
	}

	public void delete(int v) {
		if (rootNode != null) {
			rootNode = rootNode.delete(v);
		}
	}

	public boolean search(int v) {
		if (rootNode != null) {
			return rootNode.search(v);
		} else {
			return false;
		}
	}

	public boolean isEmpty() {
		return (rootNode == null);
	}

	public int size() {
		if (rootNode == null) {
			return 0;
		} else {
			return rootNode.numberOfNodes();
		}
	}

	// Part 1
	/** This method calls its helper method in the BSTNode class.
	 * 	That method prints the 'in order' traversal of the tree.
	 * 
	 * Complexity: O(1)
	 */
	public void inOrderTraversalPrint() {
		if (rootNode != null)
			rootNode.inOrderTraversalPrint();
	}

	// Part 2: complete
	/**This method calls its helper method in the BSTNode class.
	 * That method adds the 'in order' traversal of the tree to a
	 * doubly linked list, which it then returns to this method.
	 * That doubly linked list is then returned.
	 * 
	 * Complexity: O(1)
	 * 
	 * @return a doubly linked list with the values of the 'in order' traversal.
	 */
	public DLinkedList<Integer> inOrderTraversal() {
		DLinkedList<Integer> dll = new DLinkedList<>();
		if (rootNode != null) { 
			rootNode.inOrderTraversal(dll); 
			}
		else { 
			dll.addAtTail(null); 
			}
		return dll;
	}

	public static void main(String[] args) {

		System.out.println("******* Tree 1 : empty   ***********");
		BinarySearchTree bst1 = new BinarySearchTree();
		bst1.inOrderTraversalPrint();
		
		System.out.println("******* Tree 2 : 1 node  ***********");
		BinarySearchTree bst2 = new BinarySearchTree();
		bst2.insert(1);
		bst2.inOrderTraversalPrint();
		
		System.out.println("******* Tree 3 : 4 nodes ***********");
		BinarySearchTree bst3 = new BinarySearchTree();
		bst3.insert(1);
		bst3.insert(2);
		bst3.insert(3);
		bst3.insert(4);
		bst3.inOrderTraversalPrint();
		
		System.out.println("******* Tree 4 : 7 nodes ***********");
		BinarySearchTree bst4 = new BinarySearchTree();
		bst4.insert(5);
		bst4.insert(2);
		bst4.insert(1);
		bst4.insert(6);
		bst4.insert(3);
		bst4.insert(8);
		bst4.insert(7);
		bst4.inOrderTraversalPrint();

	}
}
