package w4;

import java.util.Arrays;

/**
 * Created by atretyak on 21.03.16.
 */
public class Board {
    private int[][] blocks;
    private int iLen;
    private int jLen;

    public Board(int[][] blocks) {
        iLen = blocks.length;
        jLen = blocks[0].length;
        this.blocks = new int[iLen][jLen];
        for (int i = 0; i < iLen; i++) {
            for (int j = 0; j < jLen; j++) {
                this.blocks[i][j] = blocks[i][j];
            }
        }
    }

    public int dimension() {
        return blocks.length * blocks[0].length;
    }
    public int hamming()                   // number of blocks out of place
    {
        int count = 0;
        for (int i = 0; i < iLen; i++) {
            for (int j = 0; j < jLen; j++) {
                int value = blocks[i][j];
                int position = i * iLen + j;
                if (value != 0) {
                    if (value != position + 1) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
    public int manhattan()                 // sum of Manhattan distances between blocks and goal
    {
        return 1;
    }
    public boolean isGoal()                // is this board the goal board?
    {
        return true;
    }

    public Board twin()                    // a board that is obtained by exchanging any pair of blocks
    {
        return null;
    }
    public boolean equals(Object y)        // does this board equal y?
    {
        return true;
    }
    public Iterable<Board> neighbors()     // all neighboring boards
    {
        return null;
    }
    public String toString()               // string representation of this board (in the output format specified below)
    {
        return null;
    }

    public static void main(String[] args) // unit tests (not graded)
    {
        int[][] a = new int[][]{new int[]{8, 1, 3}, new int[]{4, 0, 2}, new int[]{7, 6, 5}};
        Board b = new Board(a);
        System.out.println(b.hamming());
    }

}
