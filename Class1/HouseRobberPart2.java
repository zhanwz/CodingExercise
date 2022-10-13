package Class1;

import java.util.Arrays;

public class HouseRobberPart2 {
    // start 1:41pm
    // end and pass LC case: 1:50pm
    private static final int UNVISITED = -1;
    public int rob(int[] nums) {
        // dp problem:
        // recursion + memo:
        // create an index to keep track the position of the house
        // base case: when there's only one house: should rob
        // recursion role: rob(n) = Math.max(rob(i - 1), rob(i - 2) + nums[i]
        // Create a cache array to track the max amount you can rob at ith house
        // Time complexity:
        //    like binary tree T(N) = Math.max(T(N-1), T(N-2) without cache o(2^n)
        //    with cache o(n) visit each node one time T(N)
        // space: o(n)

        int[] cache = new int[nums.length];
        Arrays.fill(cache, UNVISITED);
        return robHelper(nums, nums.length - 1, cache);
    }

    private int robHelper(int[] nums, int i, int[] cache) {
        if (i < 0) {
            return 0;
        }
        if (i == 0) {
            return nums[0];
        }

        if (cache[i] != UNVISITED) {
            return cache[i];
        }

        int result = 0;
        result = Math.max(robHelper(nums, i - 2, cache) + nums[i], robHelper(nums, i - 1, cache));
        cache[i] = result;
        return result;

    }
}
