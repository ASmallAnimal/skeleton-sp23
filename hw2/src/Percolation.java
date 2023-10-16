import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.lang.reflect.Array;
// TODO: Add any other necessary imports.


public class Percolation {
    // TODO: Add any necessary instance variables.
    private int[] grid;
    private WeightedQuickUnionUF WUUF;
    private final int N;
    private final int top;
    private final int bottom;
    private int numberOfOpenSites;
    public Percolation(int N) {
        // TODO: Fill in this constructor.
        WUUF = new WeightedQuickUnionUF(N * N + 2);
        grid = new int[N*N];
        this.N = N;
        top = N * N;
        bottom = N * N + 1;
        numberOfOpenSites = 0;
    }

    public void open(int row, int col) {
        // TODO: Fill in this method.
        if(isOpen(row, col))
            return;
        int index = xyTo1D(row, col);
        grid[index] = 1;
//      判断是否是第一行
        if(row == 0)
            WUUF.union(index, top);
        if(row == N - 1)
            WUUF.union(index, bottom);
//      判断周边是否open
        unionAround(row, col);
        numberOfOpenSites += 1;
    }
    private int xyTo1D(int row, int col){
        return row * N + col;
    }
    private void unionAround(int row, int col){
        int topRow = row - 1;
        int btmRow = row + 1;
        int leftCol = col - 1;
        int rightCol = col + 1;
        int index = xyTo1D(row, col);
        if((topRow > -1) && isOpen(topRow, col)){
            WUUF.union(index, xyTo1D(topRow, col));
        }
        if ((btmRow < N) && isOpen(btmRow, col)) {
            WUUF.union(index, xyTo1D(btmRow, col));
        }
        if ((leftCol > -1) && isOpen(row, leftCol)) {
            WUUF.union(index, xyTo1D(row, leftCol));
        }
        if ((rightCol < N) && isOpen(row, rightCol)) {
            WUUF.union(index, xyTo1D(row, rightCol));
        }
    }
    public boolean isOpen(int row, int col) {
        // TODO: Fill in this method.
        return grid[row * N + col] == 1;
    }

    public boolean isFull(int row, int col) {
        // TODO: Fill in this method.
        return WUUF.connected(xyTo1D(row, col), top);
    }

    public int numberOfOpenSites() {
        // TODO: Fill in this method.
        return numberOfOpenSites;
    }

    public boolean percolates() {
        // TODO: Fill in this method.
        return WUUF.connected(top, bottom);
    }

    // TODO: Add any useful helper methods (we highly recommend this!).
    // TODO: Remove all TODO comments before submitting.

}
