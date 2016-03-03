package w3.collinear;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import w3.collinear.LineSegment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

/**
 * Created by atretyak on 25.02.16.
 */
public class FastCollinearPoints {
    private ArrayList<LineSegment> segments;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        HashSet<String> ppp = new HashSet<>();
        for (int i = 0; i < points.length; i++) {
            if (!ppp.contains(points[i].toString())) {
                ppp.add(points[i].toString());
            } else {
                throw new IllegalArgumentException();
            }
        }
        segments = new ArrayList<>();
        for (int i = 0; i < points.length; i++) {
            Point[] tmp = points.clone();
            Arrays.sort(tmp, tmp[i].slopeOrder());
            ArrayList<Point> lineArray = new ArrayList<>();
            lineArray.add(tmp[0]);
            int count = 1;
            boolean found = false;
            for (int j = 1; j < tmp.length; j++) {
                boolean correctPoint = points[i].slopeOrder().compare(tmp[j-1], tmp[j]) == 0;
                if (correctPoint) {
                    lineArray.add(tmp[j]);
                    count++;
                    found = true;
                }
                if (!correctPoint && found) {
                    if (count >= 3) {
                        Collections.sort(lineArray);
                        segments.add(new LineSegment(lineArray.get(0), lineArray.get(lineArray.size()-1)));
                        lineArray.clear();
                    }
                    found = false;
                    lineArray.clear();
                    lineArray.add(tmp[0]);
                    count = 1;
                }
                if (count >= 3) {
                    Collections.sort(lineArray);
                    segments.add(new LineSegment(lineArray.get(0), lineArray.get(lineArray.size()-1)));
                    lineArray.clear();
                    found = false;
                    lineArray.clear();
                    lineArray.add(tmp[0]);
                    count = 1;
                }
            }
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return segments.size();
    }

    // the line segments
    public LineSegment[] segments() {
        LineSegment[] s = new LineSegment[segments.size()];
        for (int i = 0; i < segments.size(); i++) {
            s[i] = segments.get(i);
        }
        return s;
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
