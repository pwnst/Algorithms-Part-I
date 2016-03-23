package w4;


import edu.princeton.cs.algs4.MinPQ;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Stack;


public class Solver {
    private MinPQ<Node> queue;
    private MinPQ<Node> queueTwin;
    private Board initial;
    private HashSet<Integer> visited;
    private HashSet<Integer> visitedTwin;
    private int moves;
    private boolean isSolved;
    private boolean isSolvedTwin;
    private Node solutionNode;

    private class Node {
        Board board;
        Node parent;

        public Node(Board board, Node parent) {
            this.board = board;
            this.parent = parent;
        }
    }

    public Solver(Board initial) {
        if (initial == null) {
            throw new java.lang.NullPointerException();
        }
        isSolved = false;
        isSolvedTwin = false;
        queue = new MinPQ<>(getComparator());
        queueTwin = new MinPQ<>(getComparator());
        visited = new HashSet<>();
        visitedTwin = new HashSet<>();
        this.initial = initial;

        queue.insert(new Node(initial, null));
        queueTwin.insert(new Node(initial.twin(), null));

        while (!isSolved && !isSolvedTwin) {
            moves++;
            isSolved = doStep(queue, visited);
            isSolvedTwin = doStep(queueTwin, visitedTwin);
        }
    }

    private boolean doStep(MinPQ<Node> queue, HashSet<Integer> visited) {
        Node current = queue.delMin();
        visited.add(current.toString().hashCode());

        if (current.board.isGoal()) {
            solutionNode = current;
            return true;
        } else {
            for (Board neighbor : current.board.neighbors()) {
                if (!visited.contains(neighbor.toString().hashCode())) {
                    queue.insert(new Node(neighbor, current));
                }
            }
            return false;
        }
    }

    public boolean isSolvable() {
        return isSolved;
    }

    public int moves() {
        return moves;
    }

    public Iterable<Board> solution() {
        if (isSolved) {
            Stack<Board> result = new Stack<>();
            Node current = solutionNode;

            while (current.parent != null) {
                result.add(current.board);
                current = current.parent;
            }
            return result;
        } else {
            return null;
        }
    }

    private Comparator<Node> getComparator() {
        return new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                int sum1 = o1.board.manhattan() + o1.board.hamming();
                int sum2 = o2.board.manhattan() + o2.board.hamming();
                if (sum1 > sum2) {
                    return 1;
                }
                if (sum1 < sum2) {
                    return -1;
                }
                return 0;
            }
        };
    }

    public static void main(String[] args) {
        int[][] x = new int[][]{new int[]{0, 1, 3}, new int[]{4, 2, 5}, new int[]{7, 8, 6}};
        int[][] z = new int[][]{new int[]{1, 2, 3}, new int[]{4, 5, 6}, new int[]{8, 7, 0}};
        Board a = new Board(z);
        Solver s = new Solver(a);
        System.out.println(s.isSolvable());
        for (Board b : s.solution()) {
            System.out.println(b);
        }

    }
}