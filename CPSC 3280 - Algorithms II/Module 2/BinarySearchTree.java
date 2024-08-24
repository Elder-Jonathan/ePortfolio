// Jonathan Elder
// CPSC 3280 Algorithms 2
// Programming Assignment 1



import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class BinarySearchTree {
    class Node {
        int key;
        Node left, right, parent;

        public Node(int item) {
            key = item;
            left = right = parent = null;
        }
    }

    Node root;

    // Constructor to set the root to null like is needed for a Binary Search Tree.
    public BinarySearchTree() {
        root = null;
    }

    // Static method to insert a new node in a BST.
    public static void treeInsert(BinarySearchTree T, Node z) {
        Node y = null;
        Node x = T.root;

        while (x != null) {
            y = x;
            if (z.key < x.key) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        z.parent = y;

        if (y == null) {
            T.root = z; // if the tree was empty.
        } else if (z.key < y.key) {
            y.left = z;
        } else {
            y.right = z;
        }
    }

    // Method to create a new node.
    public Node createNode(int key) {
        return new Node(key);
    }

    // Method that will calc the height of the tree.
    public int treeHeight(Node node) {
        if (node == null) {
            return -1;
        } else {
            int leftHeight = treeHeight(node.left);
            int rightHeight = treeHeight(node.right);
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    // Method to clear the tree if needed.
    public void clear() {
        root = null;
    }

    // Method to collect data
    public static void collectData() {
        Random random = new Random();
        try (FileWriter writer = new FileWriter("data_for_tree_heights.csv")) {
            writer.append("n,Height\n");

            for (int n = 2000; n <= 30000; n += 2000) {
                int totalHeight = 0;
                int m = 5;

                for (int j = 0; j < m; j++) {
                    BinarySearchTree newBST = new BinarySearchTree();

                    for (int i = 0; i < n; i++) {
                        int p = random.nextInt(30001); // Random number in range [0, 30000]
                        Node newNode = newBST.createNode(p);
                        treeInsert(newBST, newNode);
                    }

                    int height = newBST.treeHeight(newBST.root);
                    totalHeight += height;
                    newBST.clear(); // Clear the tree
                }

                double averageHeight = (double) totalHeight / m;
                writer.append(n + "," + averageHeight + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
		
        collectData();
		
    }
}