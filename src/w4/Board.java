package w4;

import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by atretyak on 21.03.16.
 */
public class Board {
    private int[][] blocks;
    private int moves;
    private int iSize;
    private int jSize;

    public Board(int[][] blocks) {
        iSize = blocks.length;
        jSize = blocks[0].length;
        this.blocks = new int[iSize][jSize];
        for (int i = 0; i < iSize; i++) {
            for (int j = 0; j < jSize; j++) {
                this.blocks[i][j] = blocks[i][j];
            }
        }
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
        Board newBoard = new Board(this.blocks);
        int[] swapPos = generatePositions();
        newBoard.swap(swapPos[0], swapPos[1], swapPos[2], swapPos[3]);
        return newBoard;
    }

    private void swap(int i, int j, int ii, int jj) {
        int tmp = blocks[i][j];
        blocks[i][j] = blocks[ii][jj];
        blocks[ii][jj] = tmp;
    }

    private int[] generateFirstPos() {
        int posFirst = StdRandom.uniform(dimension());
        int posFirstRow = posFirst / iSize;
        int posFirstColumn = posFirst % jSize;
        return new int[]{posFirstRow, posFirstColumn};
    }

    private int[] generatePositions() {
        int i = 0;
        int j = 0;

        int ii = 0;
        int jj = 0;

        while (true) {
            int[] firstPos = generateFirstPos();
            i = firstPos[0];
            j = firstPos[1];
            if (blocks[i][j] != 0) {
                break;
            }

        }

        int[][] sides = new int[4][];
        int count = 0;

        if (i != 0 && blocks[i-1][j] != 0) {
            sides[count++] = new int[]{i-1,j};
        }
        if (i != iSize-1 && blocks[i+1][j] != 0) {
            sides[count++] = new int[]{i+1,j};
        }
        if (j != 0 && blocks[i][j-1] != 0) {
            sides[count++] = new int[]{i,j-1};
        }
        if (j != jSize-1 && blocks[i][j+1] != 0) {
            sides[count++] = new int[]{i,j+1};
        }

        int[] secondPos = sides[StdRandom.uniform(count)];
        ii = secondPos[0];
        jj = secondPos[1];

        return new int[]{i, j, ii, jj};
    }

    public boolean equals(Object y) {
        Board yB = (Board) y;
        return Arrays.deepEquals(blocks, yB.blocks);
    }

    public Iterable<Board> neighbors() {
        int i = 0;
        int j = 0;
        for (int row = 0; row < iSize; row++) {
            for (int col = 0; col < jSize; col++) {
                if (blocks[row][col] == 0) {
                    i = row;
                    j = col;
                }
            }
        }

        int[][] sides = new int[4][];
        int count = 0;

        if (i != 0) {
            sides[count++] = new int[]{i-1,j};
        }
        if (i != iSize-1) {
            sides[count++] = new int[]{i+1,j};
        }
        if (j != 0) {
            sides[count++] = new int[]{i,j-1};
        }
        if (j != jSize-1) {
            sides[count++] = new int[]{i,j+1};
        }

        ArrayList<Board> neighborsArrayList = new ArrayList<>();

        for (int b = 0; b < count; b++) {
            Board newBoard = new Board(this.blocks);
            newBoard.swap(i, j, sides[b][0], sides[b][1]);
            neighborsArrayList.add(newBoard);
        }

        return neighborsArrayList;
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
        int[][] y = new int[][]{new int[]{8, 1, 3}, new int[]{4, 0, 2}, new int[]{7, 6, 5}};
        int[][] z = new int[][]{new int[]{1, 2, 3}, new int[]{4, 5, 6}, new int[]{7, 8, 0}};
        int[][] x = new int[][]{new int[]{0, 0, 0}, new int[]{0, 0, 0}, new int[]{7, 8, 0}};
        Board a = new Board(z);
        Board b = new Board(z);
        System.out.println(a);
        for (Board bb : a.neighbors()) {
            System.out.println(bb);
        }
    }

}
