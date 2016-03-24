package w4;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Stack;


public class Solver {
    private MinPQ<Node> queue;
    private MinPQ<Node> queueTwin;
    private int moves;
    private boolean isSolved;
    private boolean isSolvedTwin;
    private Node solutionNode;

    private class Node implements Comparable{
        Board board;
        Node parent;
        int steps;

        public Node(Board board, Node parent) {
            this.board = board;
            this.parent = parent;
            this.steps = calcMoves();
        }

        private int calcMoves() {
            int m = 0;
            Node current = this;
            while (current.parent != null) {
                current = current.parent;
                m++;
            }
            return m;
        }

        @Override
        public int compareTo(Object o) {
            Node other = (Node) o;
            return (this.board.manhattan() - other.board.manhattan()) + (this.steps - other.steps);
        }
    }

    public Solver(Board initial) {
        if (initial == null) {
            throw new java.lang.NullPointerException();
        }
        moves = -1;
        isSolved = false;
        isSolvedTwin = false;
        queue = new MinPQ<>();
        queueTwin = new MinPQ<>();

        queue.insert(new Node(initial, null));
        queueTwin.insert(new Node(initial.twin(), null));

        while (!isSolved && !isSolvedTwin) {
            isSolved = doStep(queue);
            isSolvedTwin = doStep(queueTwin);
        }
    }

    private boolean doStep(MinPQ<Node> queue) {
        Node current = queue.delMin();

        if (current.board.isGoal()) {
            solutionNode = current;
            return true;
        } else {
            for (Board neighbor : current.board.neighbors()) {
                if (!isAlreadyInSolutionPath(current, neighbor)) {
                    queue.insert(new Node(neighbor, current));
                }
            }
            return false;
        }
    }

    private boolean isAlreadyInSolutionPath(Node current, Board board) {
        Node prevNode = current;
        while (prevNode != null) {
            if (prevNode.board.equals(board)) {
                return true;
            }
            prevNode = prevNode.parent;
        }
        return false;
    }

    public boolean isSolvable() {
        return isSolved;
    }

    public int moves() {
        if (moves == -1) {
            solution();
        }
        return moves;
    }

    public Iterable<Board> solution() {
        if (isSolved) {
            //Stack<Board> result = new Stack<>();
            ArrayList<Board> result = new ArrayList<>();
            Node current = solutionNode;

            while (current.parent != null) {
                moves++;
                result.add(0, current.board);
                //result.add(current.board);
                current = current.parent;
            }
            return result;
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        In in = new In("./data/8puzzle/puzzle10.txt");
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution()) {
                StdOut.println(board);
            }
        }

    }
}