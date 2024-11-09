/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid; // 2D array to represent the grid
    private int n;  // the size of the
    private WeightedQuickUnionUF uf;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Grid size must be greater than 0");
        }

        this.n = n;
        grid = new boolean[n][n];
        uf = new WeightedQuickUnionUF(n * n + 2); // n*n for sites + 2 for source and sink
    }

    public void validate(int i) {
        if (i >= n || n < 0) {
            throw new IllegalArgumentException("Argument outside of prescribed range");
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row);
        validate(col);
        if (!grid[row][col]) {
            grid[row][col] = true;
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row);
        validate(col);
        return grid[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row);
        validate(col);
        return !grid[row][col];
    }

    // Helper method to check and connect open neighbors
    private void connectOpenNeighbors(int row, int col) {
        int[] rowOffsets = { -1, 0, 1, 0 }; // Up and down
        int[] colOffsets = { 0, -1, 0, 1 }; // Left and right

        for (int k = 0; k < 4; k++) {
            int newRow = row + rowOffsets[k];
            int newCol = col + colOffsets[k];

            // Check if the neighbor is within bounds and open
            if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < n && isOpen(newRow, newCol)) {
                uf.union(index(row, col), index(newRow, newCol));
            }
        }
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        int numberOfOpenSites = 0;
        for (boolean[] row : grid) {
            for (boolean site : row) {
                if (site) {
                    numberOfOpenSites++;
                }
            }
        }
        return numberOfOpenSites;
    }

    // auxiliary function to roll out two dimensions into one
    public int index(int row, int col) {
        return row * n + col;
    }

    // does the system percolate?
    public boolean percolates() {
        // 1. declare the source and sink
        int source = n * n;
        int sink = n * n + 1;

        // 2. connect the source to the first row
        for (int col = 0; col < n; col++) {
            if (isOpen(0, col)) {
                uf.union(source, index(0, col));
            }
        }

        // 3. connect the sink to the last row
        for (int col = 0; col < n; col++) {
            if (isOpen(n - 1, col)) {
                uf.union(sink, index(n - 1, col));
            }
        }

        // connect neighbors where possible
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (isOpen(i, j)) {
                    connectOpenNeighbors(i, j);
                }
            }
        }

        // 5. return true if source and sink are in the same component
        return uf.find(source) == uf.find(sink);
    }

    public static void main(String[] args) {
        int n = 5; // You can change this to test different grid sizes
        Percolation percolation = new Percolation(n);

        // Open some sites to test the functionality
        percolation.open(0, 1);
        percolation.open(1, 1);
        percolation.open(2, 1);
        percolation.open(3, 1);
        percolation.open(4, 1); // This should make the system percolate

        // Print the grid status and check percolation
        System.out.println("Number of open sites: " + percolation.numberOfOpenSites());
        System.out.println("Does the system percolate? " + percolation.percolates());

        // Test individual site status
        System.out.println("Is site (0, 1) open? " + percolation.isOpen(0, 1));
        System.out.println("Is site (2, 2) full? " + percolation.isFull(2, 2));
    }
}
