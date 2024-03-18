# COMP47500 Assignment2

**Binary Search Tree, AVL Tree, and Splay Tree Insertion and Search Implementation**

This assignment involves the implementation of three different types of binary tree data structures: Binary Search Tree (BST), AVL Tree, and Splay Tree. The main purpose is to compare their performance in terms of insertion and search operations under various scenarios.

**Contents:**

- **Main.java:** This file contains the main test class where experiments for insertion and search operations are conducted on BST, AVL Tree, and Splay Tree.
  
- **Node:** This class defines the structure for the nodes used in BST and Splay trees.

- **BST:** Defines the Binary Search Tree and contains methods for insertion and search operations.

- **AVLNode:** Defines the structure for the nodes used in AVL trees.

- **AVLTree:** Implements the AVL Tree data structure and includes methods for insertion, balancing, and searching.

- **Splay:** Implements the Splay Tree data structure and includes methods for insertion, rotation, and searching.

**Experimentation:**

- **Insertion:** The `insertion(int n)` method in `Main.java` conducts experiments to measure the time taken for inserting `n` random integers into each tree type. Time measurements are in nanoseconds.

- **Search:** The `search(int n)` method in `Main.java` conducts experiments to measure the time taken for searching `n` random integers in each tree type. Time measurements are in nanoseconds.

**Note:** The experiments are conducted with various values of `n` to evaluate the performance of each tree type under different load conditions.

---

This README provides an overview of the provided code files, their functionalities, and the purpose of the experimentation conducted within the `Main.java` class.
