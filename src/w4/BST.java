package w4;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BST<Key extends Comparable<Key>, Value> {
    private Node root;

    public Value get(Key key) {
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp > 0) {
                x = x.right;
            } else if (cmp < 0) {
                x = x.left;
            } else {
                return x.value;
            }
        }
        return null;
    }

    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null) {
            return null;
        }
        return x.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null) {
            return null;
        }

        int cmp = key.compareTo(x.key);

        if (cmp == 0) {
            return x;
        }

        if (cmp < 0) {
            return floor(x.left, key);
        }

        Node t = floor(x.right, key);
        if (t != null) {
            return t;
        } else {
            return x;
        }
    }

    public Value getMin() {
        return min(root).value;
    }

    private Node min(Node x) {
        while (x.left != null) {
            x = x.left;
        }
        return x;
    }

    public Value getMax() {
        Node x = root;
        while (x.right != null) {
            x = x.right;
        }
        return x.value;
    }

    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) {
            return new Node(key, value);
        }
        int cmp = key.compareTo(x.key);
        if (cmp > 0) {
            put(x.right, key, value);
        } else if (cmp < 0) {
            put(x.left, key, value);
        } else {
            x.value = value;
        }
        x.count = 1 + size(x.right) + size(x.left);
        return x;
    }

    public int rank(Key key) {
        return rank(key, root);
    }

    private int rank(Key key, Node x) {
        if (x == null) {
            return 0;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return rank(key, x.left);
        } else if (cmp > 0) {
            return 1 + size(x.left) + size(x.right);
        } else {
            return size(x.left);
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) {
            return 0;
        }
        return x.count;
    }

    public Iterable<Key> keys() {
        Queue<Key> q = new LinkedList<>();
        inorder(root, q);
        return q;
    }

    private void inorder(Node x, Queue<Key> q) {
        if (x == null) {
            return;
        }
        inorder(x.left, q);
        q.add(x.key);
        inorder(x.right, q);
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x == null) {
            return x.right;
        }
        x.left = deleteMin(x.left);
        x.count = 1 + size(x.right) + size(x.left);
        return x;
    }

    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node x, Key key) {
        if (x == null) {
            return null;
        }

        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = delete(x.left, key);
        } else if (cmp > 0) {
            x.right = delete(x.right, key);
        }
        else {
            if (x.right == null) {
                return x.left;
            }
            if (x.left == null) {
                return x.right;
            }

            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.count = size(x.left) + size(x.right) + 1;
        return x;
    }

    private class Node {
        private Node left;
        private Node right;
        private Key key;
        private Value value;
        private int count;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        edu.princeton.cs.algs4.BST a = new edu.princeton.cs.algs4.BST();
        Scanner s = new Scanner("81 14 13 83 11 24 58 37 42 65 ");
        while (s.hasNext()) {
            int i = s.nextInt();
            a.put(i, i);
        }
        StringBuilder sb = new StringBuilder();
        for (Object i : a.levelOrder()) {
            sb.append(i.toString() + " ");
        }
        System.out.println(sb.toString());

        edu.princeton.cs.algs4.BST b = new edu.princeton.cs.algs4.BST();
        Scanner ss = new Scanner("12 13 22 17 71 50 81 37 94 31 98 36 ");
        while (ss.hasNext()) {
            int i = ss.nextInt();
            b.put(i, i);
        }

        b.delete(17);
        b.delete(22);
        b.delete(71);

        StringBuilder sbb = new StringBuilder();
        for (Object i : b.levelOrder()) {
            sbb.append(i.toString() + " ");
        }
        System.out.println(sbb.toString());

    }
}
