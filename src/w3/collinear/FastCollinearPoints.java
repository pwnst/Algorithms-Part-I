package w3.collinear;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by atretyak on 25.02.16.
 */
public class FastCollinearPoints {
    ArrayList<LineSegment> segments;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        segments = new ArrayList<>();
        for (int i = 0; i < points.length; i++) {
            Point point = points[i];
            Point[] tmp = points.clone();
            Arrays.sort(tmp, point.slopeOrder());
            double slope = point.slopeTo(tmp[1]);
            for (int j = 4; j < tmp.length; j++) {
                if (slope != tmp[2].slopeTo(tmp[3])) {
                    break;
                }
                if (slope != tmp[j].slopeTo(tmp[j-1])) {
                    Arrays.sort(tmp, 0, j);
                    System.out.println(Arrays.toString(tmp));
                    segments.add(new LineSegment(tmp[0], tmp[j-1]));
                    break;
                }
            }
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return segments.size();
    }

    // the line segments
    public ArrayList<LineSegment> segments() {
        return segments;
    }

    public static void main(String[] args) {
        // read the N points from a file
        In in = new In(args[0]);
        int N = in.readInt();

        Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.show(0);
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
    }
}
