package w5;

import edu.princeton.cs.algs4.*;

import java.util.ArrayList;


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
                newNode.red = true;
            } else {
                boolean prevAxisX = (level % 2 == 1) ? true : false;
                if (prevAxisX) {
                    boolean right = (p.x() > parent.p.x()) ? true : false;
                    if (right) {
                        newNode.rect = new RectHV(parent.p.x(), parent.rect.ymin(), parent.rect.xmax(), parent.rect.ymax());
                    } else {
                        newNode.rect = new RectHV(parent.rect.xmin(), parent.rect.ymin(), parent.p.x(), parent.rect.ymax());
                    }
                    newNode.red = false;
                } else {
                    boolean top = (p.y() > parent.p.y()) ? true : false;
                    if (top) {
                        newNode.rect = new RectHV(parent.rect.xmin(), parent.p.y(), parent.rect.xmax(), parent.rect.ymax());
                    } else {
                        newNode.rect = new RectHV(parent.rect.xmin(), parent.rect.ymin(), parent.rect.xmax(), parent.p.y());
                    }
                    newNode.red = true;
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
            //current.rect.draw();
            current.p.draw();
            if (current.red) {
                StdDraw.line(current.p.x(), current.rect.ymax(), current.p.x(), current.rect.ymin());
            } else {
                StdDraw.line(current.rect.xmax(), current.p.y(), current.rect.xmin(), current.p.y());
            }
            if (current.lb != null) {
                queue.enqueue(current.lb);
            }
            if (current.rt != null) {
                queue.enqueue(current.rt);
            }
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        Queue<Node> queue = new Queue<>();
        ArrayList<Point2D> result = new ArrayList<>();
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            Node current = queue.dequeue();
            if (rect.contains(current.p)) {
                result.add(current.p);
            }
            if (current.lb != null && current.lb.rect.intersects(rect)) {
                queue.enqueue(current.lb);
            }
            if (current.rt != null && current.rt.rect.intersects(rect)) {
                queue.enqueue(current.rt);
            }
        }
        return result;
    }

    public Point2D nearest(Point2D p) {
        Queue<Node> queue = new Queue<>();
        Point2D nearest = root.p;
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            Node current = queue.dequeue();
            if (current.p.distanceTo(p) < nearest.distanceTo(p)) {
                nearest = current.p;
            }
            if (current.lb != null && current.lb.rect.distanceTo(p) < nearest.distanceTo(p)) {
                queue.enqueue(current.lb);
            }
            if (current.rt != null && current.rt.rect.distanceTo(p) < nearest.distanceTo(p)) {
                queue.enqueue(current.rt);
            }
        }
        return nearest;
    }

    private static class Node {
        private Point2D p;
        private RectHV rect;
        private Node lb;
        private Node rt;
        private boolean red;

        public Node(Point2D p) {
            this.p = p;
        }
    }

    public static void main(String[] args) {
        KdTree kdTree = new KdTree();
        In in = new In("data/kdtree/circle10.txt");
        int count = 0;
        /*while (count < 10) {
            count++;
            kdTree.insert(new Point2D(in.readDouble(), in.readDouble()));
        }*/
        RectHV r = new RectHV(0.8, 0.5, 1, 0.7);
        kdTree.insert(new Point2D(0.7, 0.2));
        kdTree.insert(new Point2D(0.5, 0.4));
        kdTree.insert(new Point2D(0.2, 0.3));
        kdTree.insert(new Point2D(0.4, 0.7));
        kdTree.insert(new Point2D(0.9, 0.6));
        kdTree.draw();
        System.out.println(kdTree.size());
        //System.out.println(kdTree.nearest(new Point2D(0.1, 0.1)));
        /*System.out.println(kdTree.range(r));
        r.draw();*/
    }
}
