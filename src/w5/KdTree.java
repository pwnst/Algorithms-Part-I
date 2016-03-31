package w5;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
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
        root = insert(root, null, p, 0);
    }

    private Node insert(Node node, Node parent, Point2D p, int level) {
        if (node == null) {
            size++;
            Node newNode = new Node(p);
            if (parent == null) {
                newNode.rect = new RectHV(0, 0, 1, 1);
            } else {
                boolean prevAxisX = (level % 2 == 1) ? true : false;
                if (prevAxisX) {
                    boolean right = (p.x() > parent.p.x()) ? true : false;
                    if (right) {
                        newNode.rect = new RectHV(parent.p.x(), parent.rect.ymin(), parent.rect.xmax(), parent.rect.ymax());
                    } else {
                        newNode.rect = new RectHV(parent.rect.xmin(), parent.rect.ymin(), parent.p.x(), parent.rect.ymax());
                    }
                } else {
                    boolean top = (p.y() > parent.p.y()) ? true : false;
                    if (top) {
                        newNode.rect = new RectHV(parent.rect.xmin(), parent.p.y(), parent.rect.xmax(), parent.rect.ymax());
                    } else {
                        newNode.rect = new RectHV(parent.rect.xmin(), parent.rect.ymin(), parent.rect.xmax(), parent.p.y());
                    }
                }
            }
            return newNode;
        } else {
            int cmp = (level % 2 == 0) ? Double.compare(p.x(), node.p.x()) : Double.compare(p.y(), node.p.y());
            if (cmp > 0) {
                node.rt = insert(node.rt, node, p, ++level);
            } else if (cmp < 0) {
                node.lb = insert(node.lb, node, p, ++level);
            } else {
                node.p = p;
                return node;
            }
        }
        return node;
    }

    public boolean contains(Point2D p) {
        Node node = root;
        int level = 0;
        while (node != null) {
            if (node.p.equals(p)) {
                return true;
            }
            int cmp = (level % 2 == 0) ? Double.compare(p.x(), node.p.x()) : Double.compare(p.y(), node.p.y());
            if (cmp > 0) {
                node = node.rt;
                level++;
            } else if (cmp < 0) {
                node = node.lb;
                level++;
            } else {
                level++;
            }
        }
        return false;
    }

    public void draw() {
        Queue<Node> queue = new Queue<>();
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            Node current = queue.dequeue();
            current.rect.draw();
            if (current.lb != null) {
                queue.enqueue(current.lb);
            }
            if (current.rt != null) {
                queue.enqueue(current.rt);
            }
        }
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
        System.out.println(kdTree.contains(new Point2D(2,1)));
        System.out.println(kdTree.root.lb.lb.p.y());
        System.out.println(kdTree.size());
        kdTree.draw();
    }
}
