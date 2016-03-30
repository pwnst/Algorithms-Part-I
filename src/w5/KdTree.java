package w5;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;


public class KdTree {
    private Node root = null;
    private int size;

    public KdTree() {

    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void insert(Point2D p) {
        root = insert(root, p, 0);
    }

    private Node insert(Node node, Point2D p, int level) {
        if (node == null) {
            size++;
            return new Node(p);
        } else if (level % 2 == 0) {
            int cmp = Double.compare(p.x(), node.p.x());
            if (cmp > 0) {
                node.rt = insert(node.rt, p, ++level);
            } else if (cmp < 0) {
                node.lb = insert(node.lb, p, ++level);
            } else {
                node.p = p;
                return node;
            }
        } else {
            int cmp = Double.compare(p.y(), node.p.y());
            if (cmp > 0) {
                node.rt = insert(node.rt, p, ++level);
            } else if (cmp < 0) {
                node.lb = insert(node.lb, p, ++level);
            } else {
                node.p = p;
                return node;
            }
        }
        return node;
    }

    public boolean contains(Point2D p) {
        return true;
    }

    public void draw() {

    }

    public Iterable<Point2D> range(RectHV rect) {
        return null;
    }

    public Point2D nearest(Point2D p) {
        return null;
    }

    private static class Node {
        private Point2D p;
        private RectHV rect;
        private Node lb;
        private Node rt;

        public Node(Point2D p) {
            this.p = p;
        }
    }

    public static void main(String[] args) {
        KdTree kdTree = new KdTree();
        System.out.println(kdTree.isEmpty());
        kdTree.insert(new Point2D(2,1));
        kdTree.insert(new Point2D(1,133));
        kdTree.insert(new Point2D(1,2));
        kdTree.insert(new Point2D(1,133));
        System.out.println(kdTree.root.lb.lb.p.y());
        System.out.println(kdTree.size());
    }
}
