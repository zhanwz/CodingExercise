package Class1;

// https://leetcode.com/problems/path-with-maximum-gold/
// Start time: 9:50
// Finish coding: 10:05
// Debug the time limit exceeded problem and pass all test case at 10:44
// LC throws "Time Limit Exceeded" error if I used extra space to track the visited element
// I commented my original solutiojn
public class MostGoldPath {
    private final static int[][] DIRECTIONS = new int[][] {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    public int getMaximumGold(int[][] grid) {
        // traverse all the positions in grid,
        // from each node use DFS to traverse all the path
        // maintain a visited stack to avoid re-visit.
        // keep update global maximun variable
        // Time: O(M^2*N^2) for each node we will use DFS to visit entire gird
        //    > this is wrong, on each node, we will visit 4 different directions. Should be O((m*n)*4^(m*n))
        // Space: O(M*N)

        int maxResult = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] > 0) {
                    // Set<String> visited = new HashSet<>();
                    // maxResult = Math.max(maxResult, dfs(grid, i, j, visited));
                    maxResult = Math.max(maxResult, dfs(grid, i, j));
                }
            }
        }

        return maxResult;
    }

    // private int dfs(int[][] grid, int row, int col, Set<String> visited) {
    private int dfs(int[][] grid, int row, int col) {
        // if (!validPosition(row, col, grid) || visited.contains(computeHashSetKey(row, col)) || grid[row][col] == 0) {
        if (!validPosition(row, col, grid) || grid[row][col] == 0) {
            return 0;
        }

        // visited.add(computeHashSetKey(row, col));
        int origin = grid[row][col];
        grid[row][col] = 0;

        int sum = 0;
        for (int[] direction: DIRECTIONS) {
            // sum = Math.max(sum, dfs(grid, row + direction[0], col + direction[1], visited));
            sum = Math.max(sum, dfs(grid, row + direction[0], col + direction[1]));
        }
        grid[row][col] = origin;
        return sum + grid[row][col];
    }

    private boolean validPosition(int row, int col, int[][] grid) {
        return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length;
    }

    private String computeHashSetKey(int row, int col) {
        return row + "," + col;
    }
}
