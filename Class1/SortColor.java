package Class1;

// https://leetcode.com/problems/sort-colors/
// Start 10:48
// End and pass LC test case: 10:55
public class SortColor {
    public void sortColors(int[] nums) {
        // Three pointers problem:
        // maintain left and right and i pointers
        // left initialize to 0 and right is nums.length -1
        // left pointer: all the element on the left(exclusive) are 0
        // right pointer: all the element on the right(exclusive) are 2
        // all elements between left(inclusive) and i (exclusive) are 1
        // all elements between i(inclusive) to right(inclusive) are unvisited elements
        // iterate nums array
        // when nums[i] == 0, swap with left and ++left
        // when nums[i] == 2,  swap with right and --right and also --i because the swapped element is not visited
        // time O(N)
        // space O(1)

        int left = 0, right = nums.length - 1;

        for(int i = 0; i <= right; i++) {
            if (nums[i] == 0) {
                swap(nums, i, left);
                ++left;
            } else if (nums[i] == 2) {
                swap(nums, i, right);
                --i;
                --right;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
