package w4;

import edu.princeton.cs.algs4.RandomSeq;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Random;

/**
 * Created by atretyak on 21.03.16.
 */
public class Board {
    private int[][] blocks;
    private int[][] goal;
    private int moves;
    private int iSize;
    private int jSize;

    public Board(int[][] blocks) {
        iSize = blocks.length;
        jSize = blocks[0].length;
        this.blocks = new int[iSize][jSize];
        this.goal = new int[iSize][jSize];
        for (int i = 0; i < iSize; i++) {
            for (int j = 0; j < jSize; j++) {
                int position = i * iSize + j;
                this.blocks[i][j] = blocks[i][j];
                this.goal[i][j] = position + 1;
            }
        }
        this.goal[iSize-1][jSize-1] = 0;
    }

    public int dimension() {
        return iSize * jSize;
    }

    public int hamming() {
        int count = 0;
        for (int i = 0; i < iSize; i++) {
            for (int j = 0; j < jSize; j++) {
                int value = blocks[i][j];
                int position = i * iSize + j;
                if (value != 0) {
                    if (value != position + 1) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public int manhattan() {
        int count = 0;
        for (int i = 0; i < iSize; i++) {
            for (int j = 0; j < jSize; j++) {
                int value = blocks[i][j];
                int position = i * iSize + j;
                int expectedPositionRow = (value - 1) / iSize;
                int expectedPositionColumn = (value - 1) % jSize;
                if (value != 0) {
                    count += Math.abs(i - expectedPositionRow) +
                            Math.abs(j - expectedPositionColumn);
                }
            }
        }
        return count;
    }
    public boolean isGoal() {
        for (int i = 0; i < iSize; i++) {
            for (int j = 0; j < jSize; j++) {
                int position = i * iSize + j;
                if (blocks[i][j] != position + 1) {
                    if (i != iSize-1 && j != jSize) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public Board twin() {

        return null;
    }

    private int generatePos() {
        int pos1 = StdRandom.uniform(0, dimension());
        int direction = StdRandom.uniform(0, 4);
        return -1;
    }

    private boolean validate() {
        return true;
    }

    public boolean equals(Object y)        // does this board equal y?
    {
        return true;
    }
    public Iterable<Board> neighbors()     // all neighboring boards
    {
        return null;
    }
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("\n");
        for (int i = 0; i < iSize; i++) {
            for (int j = 0; j < jSize; j++) {
                s.append(blocks[i][j] + " ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    public static void main(String[] args) // unit tests (not graded)
    {
        int[][] a = new int[][]{new int[]{8, 1, 3}, new int[]{4, 0, 2}, new int[]{7, 6, 5}};
        int[][] z = new int[][]{new int[]{1, 2, 3}, new int[]{4, 5, 6}, new int[]{7, 8, 0}};
        Board b = new Board(a);
        System.out.println(b.hamming());
        System.out.println(b.manhattan());
        System.out.println(b.goal[0][0]);
        System.out.println(b.isGoal());
        System.out.println(StdRandom.uniform(1,2));
    }

}
