package Class1;

// https://leetcode.com/problems/house-robber/
// start: 10:57
// end and pass all LC test cases: 11:01
public class HouseRobber {
    public int rob(int[] nums) {
        // dp problem:
        // iterative:
        // base case: dp[0] = nums[i]
        // either rob or not rob current house
        // induction rule: dp[i] = max(nums[i] + dp[i - 2], dp[i - 1])
        // dp[nums.length - 1] is the max amount
        // to optmize the space complexity, we only need to create extra space to track dp[i - 1] and dp[i - 2]

        // Time: O(N)
        // Space: O(1)

        if (nums.length == 0) {
            return 0;
        }

        int prev = 0, current = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int next = Math.max(current, prev + nums[i]);
            prev = current;
            current = next;
        }

        return current;
    }
}
