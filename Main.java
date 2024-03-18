package assignment2;

import java.util.Random;

//Node class for BST and Splay trees
class Node {
	int key;
	Node left, right;

	public Node(int item) {
		key = item;
		left = right = null;
	}
}

//Binary Search Tree
class BST {
	Node root;

	BST() {
		root = null;
	}

	void insert(int key) {
		root = insertRec(root, key);
	}

	Node insertRec(Node root, int key) { // recursive function to insert based on the value of the key
		if (root == null) {
			root = new Node(key);
			return root;
		}

		if (key < root.key)
			root.left = insertRec(root.left, key);
		else if (key > root.key)
			root.right = insertRec(root.right, key);

		return root;
	}

	boolean search(int key) {
		return searchRec(root, key);
	}

	boolean searchRec(Node root, int key) { // binary search tree search algorithm
		if (root == null || root.key == key)
			return root != null;

		if (root.key < key)
			return searchRec(root.right, key);

		return searchRec(root.left, key);
	}
}

//Node class for AVL tree
class AVLNode {
	int key, height;
	AVLNode left, right;

	public AVLNode(int item) {
		key = item;
		height = 1;
		left = right = null;
	}
}

//AVL Tree
class AVLTree {
	AVLNode root;

	AVLTree() {
		root = null;
	}

	int height(AVLNode N) { //returns the height of a node in the tree
		if (N == null)
			return 0;

		return N.height;
	}

	int max(int a, int b) {
		return (a > b) ? a : b;
	}

	AVLNode rightRotate(AVLNode y) {
		AVLNode x = y.left;
		AVLNode T2 = x.right;

		x.right = y;
		y.left = T2;

		y.height = max(height(y.left), height(y.right)) + 1;
		x.height = max(height(x.left), height(x.right)) + 1;

		return x;
	}

	AVLNode leftRotate(AVLNode x) {
		AVLNode y = x.right;
		AVLNode T2 = y.left;

		y.left = x;
		x.right = T2;

		x.height = max(height(x.left), height(x.right)) + 1;
		y.height = max(height(y.left), height(y.right)) + 1;

		return y;
	}

	int getBalance(AVLNode N) {
		if (N == null)
			return 0;

		return height(N.left) - height(N.right);
	}

	AVLNode insert(AVLNode node, int key) { //insertion into the AVL tree
		if (node == null)
			return new AVLNode(key);

		if (key < node.key)
			node.left = insert(node.left, key);
		else if (key > node.key)
			node.right = insert(node.right, key);
		else
			return node;

		node.height = 1 + max(height(node.left), height(node.right));

		int balance = getBalance(node);

		if (balance > 1 && key < node.left.key)
			return rightRotate(node);

		if (balance < -1 && key > node.right.key)
			return leftRotate(node);

		if (balance > 1 && key > node.left.key) {
			node.left = leftRotate(node.left);
			return rightRotate(node);
		}

		if (balance < -1 && key < node.right.key) {
			node.right = rightRotate(node.right);
			return leftRotate(node);
		}

		return node;
	}

	boolean search(int key) {
		return searchRec(root, key);
	}

	boolean searchRec(AVLNode root, int key) { // searching in the AVL tree
		if (root == null || root.key == key)
			return root != null;

		if (root.key < key)
			return searchRec(root.right, key);

		return searchRec(root.left, key);
	}
}

//Splay Tree
class Splay {
	Node root;

	Splay() {
		root = null;
	}

	Node rightRotate(Node x) {
		Node y = x.left;
		x.left = y.right;
		y.right = x;
		return y;
	}

	Node leftRotate(Node x) {
		Node y = x.right;
		x.right = y.left;
		y.left = x;
		return y;
	}

	Node splay(Node root, int key) { //brings the recently accessed element to the top of the tree
		if (root == null || root.key == key)
			return root;

		if (root.key > key) {
			if (root.left == null)
				return root;
			if (root.left.key > key) {
				root.left.left = splay(root.left.left, key);
				root = rightRotate(root);
			} else if (root.left.key < key) {
				root.left.right = splay(root.left.right, key);
				if (root.left.right != null)
					root.left = leftRotate(root.left);
			}
			return (root.left == null) ? root : rightRotate(root);
		} else {
			if (root.right == null)
				return root;
			if (root.right.key > key) {
				root.right.left = splay(root.right.left, key);
				if (root.right.left != null)
					root.right = rightRotate(root.right);
			} else if (root.right.key < key) {
				root.right.right = splay(root.right.right, key);
				root = leftRotate(root);
			}
			return (root.right == null) ? root : leftRotate(root);
		}
	}

	void insert(int key) {
		root = insertRec(root, key);
	}

	Node insertRec(Node root, int key) { //inserts into the tree based on the value of the key
		if (root == null)
			return new Node(key);

		root = splay(root, key);

		if (root.key == key)
			return root;

		Node newNode = new Node(key);

		if (root.key > key) {
			newNode.right = root;
			newNode.left = root.left;
			root.left = null;
		} else {
			newNode.left = root;
			newNode.right = root.right;
			root.right = null;
		}

		return newNode;
	}

	boolean search(int key) { //searching in the splay tree 
		root = splay(root, key);
		return (root != null && root.key == key);
	}
}

public class Main { //main test class
	static BST bst = new BST();
	static AVLTree avl = new AVLTree();
	static Splay splay = new Splay();

	public static void insertion(int n) { //experiment type 1
		Random rand = new Random();
		long bst_insert = 0, avl_insert = 0, splay_insert = 0;
		for (int i = 0; i < n; i++) {
			int num = rand.nextInt(100);

			long temp = System.nanoTime();
			bst.insert(num);
			bst_insert += System.nanoTime() - temp;

			temp = System.nanoTime();
			avl.root = avl.insert(avl.root, num);
			avl_insert += System.nanoTime() - temp;

			temp = System.nanoTime();
			splay.insert(num);
			splay_insert += System.nanoTime() - temp;
		}
		System.out.println(n + "\t\t" + bst_insert + "\t\t" + avl_insert + "\t\t" + splay_insert);
	}

	public static void search(int n) { //experiment type 2
		Random rand = new Random();
		long bst_search = 0, avl_search = 0, splay_search = 0;
		for (int i = 0; i < n; i++) {
			int num = rand.nextInt(100);

			long temp = System.nanoTime();
			bst.search(num);
			bst_search += System.nanoTime() - temp;

			temp = System.nanoTime();
			avl.search(num);
			avl_search += System.nanoTime() - temp;

			temp = System.nanoTime();
			splay.search(num);
			splay_search += System.nanoTime() - temp;
		}
		System.out.println(n + "\t\t" + bst_search + "\t\t" + avl_search + "\t\t" + splay_search);
	}

	public static void main(String[] args) {
		System.out.println("N\t\tBST\t\tAVL\t\tSPLAY"); //experiments defined below

		System.out.println("--------INSERTION---------");
		insertion(1);
		insertion(20);
		insertion(20);
		insertion(20);
		insertion(20);
		insertion(20);
		insertion(50);
		insertion(50);
		insertion(50);
		insertion(50);
		insertion(50);
		insertion(50);
		insertion(50);
		insertion(100);
		insertion(100);
		insertion(100);
		insertion(100);
		insertion(100);

		System.out.println("--------SEARCH---------");
		search(1);
		search(300);
		search(300);
		search(300);
		search(300);
		search(300);
		search(500);
		search(500);
		search(500);
		search(500);
		search(500);
		search(500);
		search(500);
		search(700);
		search(700);
		search(700);
		search(700);
		search(700);
	}
}