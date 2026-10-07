import java.util.*;

public class EmergencyAmbulanceOptimization2 {

    // Min Heap Implementation
    static class MinHeap {
        private PriorityQueue<Integer> heap = new PriorityQueue<>();

        public void insert(int value) {
            heap.add(value);
        }

        public int extractMin() {
            return heap.poll();
        }
    }

    // Splay Tree Implementation
    static class SplayTree {
        class Node {
            int key;
            Node left, right;
            public Node(int key) { this.key = key; }
        }
        private Node root;

        private Node rightRotate(Node x) {
            Node y = x.left;
            x.left = y.right;
            y.right = x;
            return y;
        }

        private Node leftRotate(Node x) {
            Node y = x.right;
            x.right = y.left;
            y.left = x;
            return y;
        }

        private Node splay(Node root, int key) {
            if (root == null || root.key == key) return root;
            if (root.key > key) {
                if (root.left == null) return root;
                if (root.left.key > key) { 
                    root.left.left = splay(root.left.left, key);
                    root = rightRotate(root);
                } else if (root.left.key < key) {
                    root.left.right = splay(root.left.right, key);
                    if (root.left.right != null) root.left = leftRotate(root.left);
                }
                return (root.left == null) ? root : rightRotate(root);
            } else {
                if (root.right == null) return root;
                if (root.right.key > key) {
                    root.right.left = splay(root.right.left, key);
                    if (root.right.left != null) root.right = rightRotate(root.right);
                } else if (root.right.key < key) { 
                    root.right.right = splay(root.right.right, key);
                    root = leftRotate(root);
                }
                return (root.right == null) ? root : leftRotate(root);
            }
        }

        public void insert(int value) {
            if (root == null) {
                root = new Node(value);
                return;
            }
            root = splay(root, value);
            if (root.key == value) return;
            Node newNode = new Node(value);
            if (root.key > value) {
                newNode.right = root;
                newNode.left = root.left;
                root.left = null;
            } else {
                newNode.left = root;
                newNode.right = root.right;
                root.right = null;
            }
            root = newNode;
        }

        public boolean search(int value) {
            root = splay(root, value);
            return root != null && root.key == value;
        }
    }

    // Method 
    public static void main(String[] args) {
        
        // Min-Heap Test 
        MinHeap heap = new MinHeap();
        heap.insert(10);
        heap.insert(3);
        heap.insert(15);
        System.out.println("Min-Heap Extract Minimum Priority Value: " + heap.extractMin());
        
        // Splay Tree Test 
        SplayTree tree = new SplayTree();
        tree.insert(20);
        tree.insert(10);
        tree.insert(30);
        System.out.println("Splay Tree Search (Emergency Case 10 found): " + tree.search(10));
    }
}