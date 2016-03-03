package w3.collinear;

import edu.princeton.cs.algs4.DoublingRatio;
import edu.princeton.cs.algs4.StdDraw;

import java.util.Arrays;
import java.util.Comparator;

public class Point implements Comparable<Point> {

    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point

    /**
     * Initializes a new point.
     *
     * @param  x the <em>x</em>-coordinate of the point
     * @param  y the <em>y</em>-coordinate of the point
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Draws this point to standard draw.
     */
    public void draw() {
        StdDraw.point(x, y);
    }

    /**
     * Draws the line segment between this point and the specified point
     * to standard draw.
     *
     * @param that the other point
     */
    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Returns the slope between this point and the specified point.
     * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
     * is (y1 - y0) / (x1 - x0). For completeness, the slope is defined to be
     * +0.0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertical;
     * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     * @param  that the other point
     * @return the slope between this point and the specified point
     */
    public double slopeTo(Point that) {
        if (that.equals(null)) {
            throw new NullPointerException();
        }

        if (this.x == that.x && this.y != that.y) {
            return Double.POSITIVE_INFINITY;
        } else if (this.x == that.x && this.y == that.y) {
            return Double.NEGATIVE_INFINITY;
        } else if (that.y == this.y && that.x != this.x) {
            return 0.0;
        }
        return ((double)(that.y - this.y) / (double)(that.x - this.x));
    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param  that the other point
     * @return the value <tt>0</tt> if this point is equal to the argument
     *         point (x0 = x1 and y0 = y1);
     *         a negative integer if this point is less than the argument
     *         point; and a positive integer if this point is greater than the
     *         argument point
     */
    public int compareTo(Point that) {
        if (that.equals(null)) {
            throw new NullPointerException();
        }
        if (this.x > that.x) {
            return 1;
        }
        else if (this.x == that.x) {
            if(this.y > that.y) {
                return 1;
            }
            else if (this.y == that.y) {
                return 0;
            }
            else {
                return -1;
            }
        }
        return -1;
    }

    /**
     * Compares two points by the slope they make with this point.
     * The slope is defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    public Comparator<Point> slopeOrder() {
        return new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                if (o1.equals(null) || o2.equals(null)) {
                    throw new NullPointerException();
                }
                if (slopeTo(o1) > slopeTo(o2)) {
                    return 1;
                }
                if (slopeTo(o1) < slopeTo(o2)) {
                    return -1;
                }
                return 0;
            }
        };
    }


    /**
     * Returns a string representation of this point.
     * This method is provide for debugging;
     * your program should not rely on the format of the string representation.
     *
     * @return a string representation of this point
     */
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    /**
     * Unit tests the Point data type.
     */
    public static void main(String[] args) {
/*        Point point1 = new Point(3000, 7000);
        Point point2 = new Point(0, 10000);
        Point point3 = new Point(20000, 21000);
        Point point4 = new Point(14000, 15000);
        Point point5 = new Point(6000, 7000);
        Point point6 = new Point(3000, 4000);
        Point point7 = new Point(0,  10000);
        Point point8 = new Point(7000,  3000);*/
        Point point1 = new Point(1, 1);
        Point point2 = new Point(2, 1);
        Point point3 = new Point(3, 1);
        Point point4 = new Point(4, 1);
        Point point5 = new Point(4, 2);
        Point point6 = new Point(4, 3);
        Point point7 = new Point(4, 4);
        Point point8 = new Point(4, 5);
        Point[] a = new Point[]{point1, point2, point3, point4, point5, point6, point7, point8};
        Double[] b = new Double[a.length];
        Point point = point4;
        System.out.println(point.slopeOrder().compare(point6, point5));
        for (int i = 0; i < a.length; i++) {
            b[i] = point.slopeTo(a[i]);
        }
        System.out.println(Arrays.toString(a));
        Arrays.sort(a, point.slopeOrder());
        System.out.println(Arrays.toString(b));
        System.out.println(Arrays.toString(a));
        for (int i = 0; i < a.length; i++) {
            b[i] = point.slopeTo(a[i]);
        }
        System.out.println(Arrays.toString(b));
    }
}
