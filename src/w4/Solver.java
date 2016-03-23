package w4;


import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;


public class Solver {
    private MinPQ<Board> queue;
    private Board initial;
    private HashSet<Board> visited;
    private int moves;

    public Solver(Board initial) {
        queue = new MinPQ<>(getComparator());
        visited = new HashSet<>();
        this.initial = initial;
    }

    public boolean isSolvable() {
        return true;
    }

    public int moves() {
        return moves;
    }

    public Iterable<Board> solution() {
        ArrayList<Board> solutionArrayList = new ArrayList<>();
        queue.insert(initial);
        while (!queue.isEmpty()) {
            Board current = queue.delMin();
            visited.add(current);
            if (current.isGoal()) {
                while (current.getParent() != null) {
                    solutionArrayList.add(current);
                    current = current.getParent();
                }
                solutionArrayList.add(initial);
                break;
            } else {
                for (Board neighbor : current.neighbors()) {
                    if (!visited.contains(neighbor)) {
                        queue.insert(neighbor);
                    }
                }
            }
        }
        moves = solutionArrayList.size()-1;
        return solutionArrayList;
    }

    private Comparator<Board> getComparator() {
        return new Comparator<Board>() {
            @Override
            public int compare(Board o1, Board o2) {
                int sum1 = o1.manhattan() + o1.hamming() + o1.getMoves();
                int sum2 = o2.manhattan() + o2.hamming() + o2.getMoves();
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
        Board a = new Board(x);
        Solver s = new Solver(a);
        for (Board b : s.solution()) {
            System.out.println(b);
        }

    }
}