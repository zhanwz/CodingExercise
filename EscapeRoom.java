import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EscapeRoom {
    // Escape room: 0 is open place, 1 is wall, 2 is a clue
    private static final int[][] DIRECTIONS = new int[][] {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    private static final int OPEN_PLACE = 0;
    private static final int WALL = 1;
    private static final int CLUE = 2;

    private static List<int[]> shortestPath = new ArrayList<>();

    // Q1. Find and return all possible immediate moves
    // Time complexity : O(1), Space complexity: O(1)
    private static List<int[]> findImmediateMove(int[] startPos, int[][] escapeRoom) {
        List<int[]> result = new ArrayList<>();

        // If start pos is invalid or is wall then return empty result
        if (!isValidPosition(startPos[0], startPos[1], escapeRoom) || escapeRoom[startPos[0]][startPos[1]] == WALL) {
            return result;
        }

        for (int[] direction : DIRECTIONS) {
            int nextRow = startPos[0] + direction[0];
            int nextCol = startPos[1] + direction[1];

            if (isValidPosition(nextRow, nextCol, escapeRoom) && escapeRoom[nextRow][nextCol] != WALL) {
                result.add(new int[]{nextRow, nextCol});
            }
        }

        return result;
    }

    // Q2: only given end position, no matter where player start, can we definitely reach to the end position
    // 1. count total no of open space Time: O(m*n) space: o(1)
    // 2. starts from end position and traverse neighbour and find all the possible open space it can reach to and count the no
    //    Time: o(m*n), space recursion o(m*n)
    // 3. diff the #1 count with #2 and see if it's 0
    private static boolean isPossibleToEscapeFromAllOpenPlaces(int[] endPos, int[][] escapeRoom) {
        int openPlaceCount = 0;
        for (int i = 0; i < escapeRoom.length; i++) {
            for (int j = 0; j < escapeRoom[0].length; j++) {
                if (escapeRoom[i][j] == OPEN_PLACE) {
                    ++openPlaceCount;
                }
            }
        }

        Set<String> visited = new HashSet<>();
        int reachableOpenPlaceCount = isPossibleToEscapeFromAllOpenPlacesHelper(endPos, escapeRoom, visited);
        return openPlaceCount - reachableOpenPlaceCount == 0;
    }

    // Q3: Collect all the clues before leaving the escape room/
    // Valid path with all clues from start to end. Cannot visit any cell you have been to previously
    // must be the shortest path
    // 1. traverse escape room one by one and find clue's position
    // 2. use DFS to traverse from start to end and find all the valid paths from start to end
    //    2.1 pass a counter to keep track of no of visited clues
    // 3. maintain a global result to keep track of valid path
    //    3.1 only update the global path if there's a result that's shorter than global result
    //       and visited no of clues equals to the no from step 1
    // Time: O(m + n + 4^(m*n)) space o(m*n)
    private static List<int[]> findShortestPath(int[] start, int[] end, int[][] escapeRoom) {
        shortestPath = new ArrayList<>();
        // If start pos is invalid or is wall then return empty result
        if (!isValidPosition(start[0], start[1], escapeRoom) || escapeRoom[start[0]][start[1]] == WALL
        || !isValidPosition(end[0], end[1], escapeRoom) || escapeRoom[end[0]][end[1]] == WALL) {
            return shortestPath;
        }

        int clueCounter = 0;
        for (int i = 0; i < escapeRoom.length; i++) {
            for (int j = 0; j < escapeRoom[0].length; j++) {
                if (escapeRoom[i][j] == CLUE) {
                    ++clueCounter;
                }
            }
        }


        List<int[]> currPath = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        findShortestPathHelper(start, end, escapeRoom, currPath, clueCounter, visited);
        return shortestPath;
    }

    private static void findShortestPathHelper(int[] curr, int[] end, int[][] escapeRoom, List<int[]> currPath, int clueCounter, Set<String> visited) {


        if (curr[0] == end[0] && curr[1] == end[1]) {
            if (clueCounter == 0 && (shortestPath.isEmpty() || shortestPath.size() > currPath.size())) {
                shortestPath = new ArrayList<>(currPath);
            }

            return;
        }


        if (visited.contains(computeHashKey(curr[0], curr[1], escapeRoom))) {
            return;
        }

        visited.add(computeHashKey(curr[0], curr[1], escapeRoom));

        if (escapeRoom[curr[0]][curr[1]] == CLUE) {
            clueCounter--;
        }

        List<int[]> nextMoves = findImmediateMove(curr, escapeRoom);
        currPath.add(curr);

        for (int[] nextMove: nextMoves) {
            findShortestPathHelper(nextMove, end, escapeRoom, currPath, clueCounter, visited);
        }

        // backtrack
        visited.remove(computeHashKey(curr[0], curr[1], escapeRoom));
        currPath.remove(currPath.size() - 1);
    }

    private static int isPossibleToEscapeFromAllOpenPlacesHelper(int[] currPos, int[][] escapeRoom, Set<String> visited) {
        if (visited.contains(computeHashKey(currPos[0], currPos[1], escapeRoom))) {
            return 0;
        }
        visited.add(computeHashKey(currPos[0], currPos[1], escapeRoom));
        List<int[]> nextMoves = findImmediateMove(currPos, escapeRoom);

        int result = 1;
        for (int[] nextMove: nextMoves) {
            result += isPossibleToEscapeFromAllOpenPlacesHelper(nextMove, escapeRoom, visited);
        }

        return result;
    }

    // int hashkey is more efficient than string hashkey
    private static String computeHashKey(int row, int col, int[][] room) {
        return row + "," + col;
    }

    private static boolean isValidPosition(int row, int col, int[][] escapeRoom) {
        return row >= 0 && row < escapeRoom.length && col >= 0 && col < escapeRoom[0].length;
    }

    public static void main(String[] args) {
//        List<int[]> results = findImmediateMove(new int[] {0, 0}, new int[][] {{0, 0}, {0, 0}});
//        for (int[] result : results) {
//            System.out.println(result[0] + "," + result[1]);
//        }
//
//        results = findImmediateMove(new int[] {0, 0}, new int[][] {{1, 0}, {0, 0}});
//        for (int[] result : results) {
//            System.out.println(result[0] + "," + result[1]);
//        }
//
//
//        System.out.println(isPossibleToEscapeFromAllOpenPlaces(new int[] {1, 1}, new int[][] {{0, 0}, {0, 0}}));
//        System.out.println(isPossibleToEscapeFromAllOpenPlaces(new int[] {1, 1}, new int[][] {{0, 0}, {0, 1}}));

        int[] start = new int[]{2, 1};
        int[] end = new int[] {1, 2};
        int[][] escapeRoom = new int[][] {{1, 2, 1}, {1, 0, 0}, {1, 0, 0}};
        // 1, 2, 1
        // 1, 0, E
        // 1, s, 0

//        List<int[]> results = findShortestPath(start, end, escapeRoom);
//        // should return empty result
//        for (int[] result : results) {
//            System.out.println(result[0] + "," + result[1]);
//        }

        escapeRoom = new int[][] {{0, 2, 1}, {0, 0, 0}, {0, 0, 0}};
        // 0, 2, 1
        // 0, 0, E
        // 0, s, 0
        // should return S, 0, 0, 0, 2, 0, E
       List<int[]> results = findShortestPath(start, end, escapeRoom);

        for (int[] result : results) {
            System.out.println(result[0] + "," + result[1]);
        }

    }
}
