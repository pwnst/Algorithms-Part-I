package w1.percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.ArrayList;

/**
 * Created by pwnst on 09.02.16.
 */
public class Percolation{
    private int[] grid;
    private int sideSize;
    private WeightedQuickUnionUF wQU;

    // create N-by-N grid, with all sites blocked
    public Percolation(int N) {
        wQU = new WeightedQuickUnionUF(N*N+2);
        grid = new int[N*N+2];
        grid[0] = 1;
        grid[N*N+1] = 1;
        sideSize = N;
    }

    private int xyTo1D(int i, int j) {
        return sideSize * (i - 1) + j;
    }

    private ArrayList<Integer> getNeighbours(int i, int j) {
        ArrayList<Integer> neighbours = new ArrayList<>();
        if (i != 1) {
            neighbours.add(xyTo1D(i-1, j));
        }
        if (i != sideSize) {
            neighbours.add(xyTo1D(i+1, j));
        }
        if (j != 1) {
            neighbours.add(xyTo1D(i, j-1));
        }
        if (j != sideSize) {
            neighbours.add(xyTo1D(i, j+1));
        }
        return neighbours;
    }

    // open site (row i, column j) if it is not open already
    public void open(int i, int j) {
        if (!isOpen(i, j)) {
            int pointIndex = xyTo1D(i, j);
            grid[pointIndex] = 1;
            if (i == 1) {
                wQU.union(0, pointIndex);
            }
            if (i == sideSize) {
                wQU.union(sideSize * sideSize + 1, pointIndex);
            }
            for (int index : getNeighbours(i, j)) {
                if (grid[index] == 1) {
                    wQU.union(pointIndex, index);
                }
            }
        }
    }

    // is site (row i, column j) open?
    public boolean isOpen(int i, int j) {
        if (grid[xyTo1D(i, j)] != 0) {
            return true;
        }
        return false;
    }

    // is site (row i, column j) full?
    //  || wQU.connected(sideSize * sideSize + 1, pointIndex)
    public boolean isFull(int i, int j) {
        int pointIndex = xyTo1D(i, j);
        if (wQU.connected(0, pointIndex)) {
            return true;
        }
        return false;
    }

    // does the system percolate?
    public boolean percolates() {
        if (wQU.connected(0, sideSize * sideSize + 1)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Percolation percolation = new Percolation(16);
        percolation.open(1,1);
        percolation.open(2,1);
        percolation.open(3,2);
        percolation.open(4,1);
        System.out.println(percolation.percolates());
    }  // test client (optional)
}
