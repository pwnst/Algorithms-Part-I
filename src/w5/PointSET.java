package w5;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.ArrayList;
import java.util.TreeSet;

public class PointSET {
    private TreeSet<Point2D> treeSet;

    public PointSET() {
        treeSet = new TreeSet<>();
    }

    public boolean isEmpty() {
        return treeSet.isEmpty();
    }

    public int size() {
        return treeSet.size();
    }

    public void insert(Point2D p) {
        treeSet.add(p);
    }

    public boolean contains(Point2D p) {
        return treeSet.contains(p);
    }

    public void draw() {
        for (Object o : treeSet) {
            Point2D p = (Point2D) o;
            p.draw();
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        ArrayList<Point2D> result = new ArrayList<>();
        for (Object o : treeSet) {
            Point2D p = (Point2D) o;
            if (rect.contains(p)) {
                result.add(p);
            }
        }
        return result;
    }

    public Point2D nearest(Point2D p) {
        Point2D best = null;

        for (Object o : treeSet) {
            Point2D pp = (Point2D) o;
            if (best == null) {
                best = pp;
            } else if (p.distanceTo(best) > p.distanceTo(pp)) {
                best = pp;
            }
        }

        return best;
    }

    public static void main(String[] args) {

    }
}
