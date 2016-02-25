package w3.collinear;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import javax.sound.sampled.Line;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Created by atretyak on 22.02.16.
 */
public class BruteCollinearPoints {
    ArrayList<LineSegment> segments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        segments = new ArrayList<>();
        Arrays.sort(points);
        for (int i = 0; i < points.length; i++) {
            for (int j = i+1; j < points.length; j++) {
                for (int f = j+1; f < points.length; f++) {
                    if (points[i].slopeTo(points[j]) == points[j].slopeTo(points[f])) {
                        for (int k = f+1; k < points.length; k++) {
                            if (points[i].slopeTo(points[j]) == points[f].slopeTo(points[k])) {
                                segments.add(new LineSegment(points[i], points[k]));
                            }
                        }
                    }
                }
            }
        }
/*        for (int i = 0; i < points.length-4; i++) {
            double p1 = points[i].slopeTo(points[i+1]);
            double p2 = points[i+1].slopeTo(points[i+2]);
            double p3 = points[i+2].slopeTo(points[i+3]);
            if ((p1 == p2) && (p2 == p3)) {
                LineSegment line = new LineSegment(points[i], points[i+3]);
                segments.add(line);
            }
        }*/
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
    }
}
