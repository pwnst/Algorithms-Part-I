package w4;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by pwnst on 14.03.16.
 */
public class BST<Key extends Comparable<Key>> {
    private Node root;

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        }
        return node.count;
    }

    public void put(Key object) {
        root = put(root, object);
    }

    private Node put(Node x, Key value) {
        if (x == null) {
            Node newNode = new Node(value);
            newNode.count++;
            return newNode;
        }

        int cmp = x.value.compareTo(value);
        if (cmp > 0) {
            x.left = put(x.left, value);
        } else if (cmp < 0) {
            x.right = put(x.right, value);
        }
        x.count = 1 + size(x.left)+ size(x.right);
        return x;
    }

    public Key get(Key value) {
        Node result = find(value);
        if (result == null) {
            return null;
        }
        return (Key) result.value;
    }

    private Node find(Key value) {
        Node x = root;
        while (x != null) {
            int cmp = x.value.compareTo(value);
            if (cmp > 0) {
                x = x.left;
            } else if (cmp < 0) {
                x = x.right;
            } else {
                return x;
            }
        }
        return null;
    }

    public Iterable<Key> keys() {
        Queue<Key> q = new LinkedList<>();
        inOrder(root, q);
        return q;
    }

    private void inOrder(Node x, Queue q) {
        if (x == null) {
            return;
        }
        inOrder(x.left, q);
        q.add(x.value);
        inOrder(x.right, q);
    }

    public Iterable<Key> keyss() {
        Queue<Node> q = new LinkedList<>();
        Queue<Key> result = new LinkedList<>();
        Node x = root;
        q.add(x);
        while (!q.isEmpty()) {
            Node current = q.remove();
            result.add((Key) current.value);
            if (current.left != null) {
                q.add(current.left);
            }
            if (current.right != null) {
                q.add(current.right);
            }
        }
        return result;
    }

    public void delete(Key value) {
        root = delete(root, value);
    }

    private Node delete(Node x, Key value) {
        if (x == null) {
            return null;
        }
        int cmp = value.compareTo((Key) x.value);
        if (cmp < 0) {
            x.left = delete(x.left, value);
        } else if (cmp > 0) {
            x.right = delete(x.right, value);
        } else {
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

    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) {
            return x.right;
        }
        x.left = deleteMin(x.left);
        x.count = 1 + size(x.left) + size(x.right);
        return x;
    }

    private Node min() {
        root = min(root);
        return root;
    }

    private Node min(Node x) {
        if (x.left == null) {
            return x.right;
        }
        x.left = min(x.left);
        return x;
    }


    class Node<Key extends Comparable<Key>> {
        private Node left;
        private Node right;
        private Key value;
        public int count;

        public Node(Key value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        BST<Integer> b = new BST<>();
        Scanner s = new Scanner("94 78 96 48 82 14 65 11 34 53 73 36");
        while (s.hasNext()) {
            b.put(s.nextInt());
        }
        /*for (int i = 0; i < 10; i++) {
            b.put((int) Math.floor(Math.random() * 1000000));
        }*/
        //System.out.println(b.size());
        /*System.out.println(b.root.right.value);
        System.out.println(b.root.value);*/
        b.delete(82);
        b.delete(78);
        b.delete(48);
        StringBuilder sb = new StringBuilder();
        for (Integer i : b.keyss()) {
            sb.append(i + " ");
        }
        System.out.println(sb.toString());
        //System.out.println("66 14 13 53 26 54 17 27 59 31");
    }
}
