package Class1;

// start: 12:15
// Finish 12:26
// pass all cornercase: 12:30

import java.util.HashSet;
import java.util.Set;

// https://leetcode.com/problems/number-of-good-ways-to-split-a-string/
public class GoodSplits {
    public int numSplits(String s) {
        // maintain left and right array.
        // first iteration: from 0 to n
        // caculate left array: from 0.. ith(exclusive) pos no of distinct character
        // second iteration from n th 0
        // caculate right array: from ith(inclusive.. length -1 pos no distinct character
        // if left[i] == right[i] count++
        // Time o(n)
        // space o(n)
        // To optimize space complexity, we don't have to maintain a right array.
        // Found a better solution here: https://leetcode.com/problems/number-of-good-ways-to-split-a-string/discuss/754941/Java-simple-O(n)

        char[] inputArray = s.toCharArray();
        int[] left = new int[inputArray.length];
        Set<Character> distinctCharacters = new HashSet<>();

        for (int i = 1; i < inputArray.length; i++) {
            if (!distinctCharacters.contains(inputArray[i - 1])) {
                left[i] = left[i - 1] + 1;
                distinctCharacters.add(inputArray[i - 1]);
            } else {
                left[i] = left[i - 1];
            }
        }

        distinctCharacters = new HashSet<>();
        int distinctCharactersCount = 0;
        int result = 0;

        for (int i = inputArray.length - 1; i >= 0; i--) {
            if (!distinctCharacters.contains(inputArray[i])) {
                distinctCharactersCount++;
                distinctCharacters.add(inputArray[i]);


            }

            if (distinctCharactersCount == left[i]) {
                ++result;
            }
        }

        return result;
    }
}
