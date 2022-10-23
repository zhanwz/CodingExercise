import java.util.ArrayList;
import java.util.List;

public class TravelingSalesmanProblem {

    // Given a list of cities and distances between each pair of cities. What is the shortest
    // possible route that visits each city exactly once and returns to the origin city?

    //     A  B  C  D
    //  A  0  4  1  9
    //  B  3  0  6  11
    //  C  4  1  0  2
    //  D  6  5  -4 0

    // Brute force: O(N!) similar to permutation


    // DP: o(N^2 * 2^N) https://www.youtube.com/watch?v=cY4HiiFHO1o
    // n = 2, store optimal solution from the first city to any other (second) city
    // keep track of visited node and the index of last visited city
    // n = 3, the optimal solution for path 2 and expand to 3rd city: time complexity O(N * 2^N)
    // expand to n - 1

    private void solveTsp(int[][] adjacencyMatrix, int startCity) {
        int[][] memo = new int[adjacencyMatrix.length][(int) Math.pow(2, adjacencyMatrix.length)];
        setup(memo, adjacencyMatrix, startCity);
        solve(memo, adjacencyMatrix, startCity);
    }

    private void setup(int[][] memo, int[][] adjacencyMatrix, int startingCity) {
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            if (i == startingCity) {
                continue;
            }

            memo[i][1 << startingCity | 1 << i] = adjacencyMatrix[startingCity][i];
        }
    }

    private void solve(int[][] memo, int[][] adjacencyMatrix, int startingCity) {
        int noOfCity = adjacencyMatrix.length;
        for (int i = 3; i <= noOfCity; i++) {
            // The combinations function generates all bit sets
            // of size noOfCity with i bits set to 1. For example:
            // combinations(3, 4) = {0111

            for (int subset : generateBits(i, noOfCity)) {
                // startingCity city must be inside subset
                if (notIn(startingCity, subset)) {
                    continue;
                }

                for (int next = 0; next < noOfCity; next ++) {
                    // next must he inside subset
                    if (next == startingCity || notIn(next, subset)) {
                        continue;
                    }

                    int state = subset ^ (1 << next);
                    int minDist = Integer.MAX_VALUE;
                    for (int prevEndNode = 0; prevEndNode <noOfCity; prevEndNode++ ){
                        // prevEndNode must he inside subset
                        if (prevEndNode == startingCity || prevEndNode == next || notIn(prevEndNode, subset)) {
                            continue;
                        }

                        int newDistance = memo[prevEndNode][state] + adjacencyMatrix[prevEndNode][next];
                        minDist = Math.min(minDist, newDistance);
                        memo[next][subset] = minDist;
                    }
                }
            }
        }
    }

    // return true if ith is not in subset
    private boolean notIn(int num, int subset) {
        return (1 << num & subset) == 0;
    }

    private List<Integer> generateBits(int no, int noOfCity) {
        List<Integer> result = new ArrayList<>();
        combination(result, 0, 0, no, noOfCity);
        return result;
    }

    private void combination(List<Integer> result , int bits, int index, int no, int noOfCity) {
        if (no == 0) {
            result.add(bits);
            return;
        }

        for (int i = index; i < noOfCity; i++) {
            // flip ith bit
            bits = bits | (1<<i);
            combination(result, bits, i + 1, no - 1, noOfCity);

            // backtrack
            bits = bits & ~(1<<i);
        }
    }
}
