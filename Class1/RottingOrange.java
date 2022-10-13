package Class1;

// Rotting Oranges: https://leetcode.com/problems/rotting-oranges/
// Time:
//   Start: 7:55pm
//   Finish writing the code: 8:09pm
//   Debug corner case and pass all LC test cases: 8:15pm

import java.util.ArrayDeque;
import java.util.Deque;

public class RottingOrange {
    private static final int[][] DIRECTIONS = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    public int orangesRotting(int[][] grid) {
        // BFS:
        // base case: visit grid[i][j] one by one. If it's rotten, add it into a queue
        // expend rule: pop first orange from queue, and see if there's fresh orange in 4 diretions,
        // if yes then update the orange to rotten and add it to the queue
        // +1 minite during expension
        // termination rule: when queue is empty
        // Time complexity: O(m*n)
        // Space complexity: all the orange are rotten and added to the queue. O(m*n)

        if (grid.length == 0) {
            return 0;
        }

        Deque<int[]> rottenOranges = new ArrayDeque<>();

        //  Time complexity O(m*n)
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 2) {
                    rottenOranges.add(new int[]{i, j});
                }
            }
        }

        int result = 0;

        //  Time complexity O(m*n)

        while(!rottenOranges.isEmpty()) {
            int size = rottenOranges.size();

            for (int i = 0; i < size; i++) {
                int[] orange = rottenOranges.removeFirst();
                for(int[] direction: DIRECTIONS) {
                    int row = orange[0] + direction[0];
                    int col = orange[1] + direction[1];

                    if (isValidPosition(row, col, grid.length, grid[0].length) && grid[row][col] == 1) {
                        rottenOranges.add(new int[]{row, col});
                        grid[row][col] = 2;
                    }
                }
            }

            if (!rottenOranges.isEmpty()) {
                ++result;
            }
        }

        //  Time complexity O(m*n)
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    return -1;
                }
            }
        }


        return result;
    }

    private boolean isValidPosition(int row, int col, int gridRow, int gridCol) {
        return row >= 0 && row < gridRow && col >= 0 && col < gridCol;
    }
}
