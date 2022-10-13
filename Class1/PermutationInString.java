package Class1;

// https://leetcode.com/problems/permutation-in-string/

// Time:
//     Start: 8:21
//     Finish writing the code: 8:43
//     Address corner cases and pass LC test: 9:00

public class PermutationInString {
    public boolean checkInclusion(String s1, String s2) {

        // sliding window:
        //   1. create a hashmap to record s1 character, freq
        //   2. remember s1 size
        //   3. left, right pointer and a size variable
        //       right pointer iterate from 0th to nth,
        //       create a hashmap to record s2 character, freq
        //       when a char in s2 hashmap's freq == s1 hashmap's freq then ++size
        //       when s1 size == size variable than check if right - left == s1.length
        //       if yes then return true
        //       if not then ++ left pointer and update s1 freq
        // time complexity: O(m + n)
        // space complxity: O(1) because s1CharToFreq s2CharToFreq has fixed size of 26

        if (s1.length()  > s2.length()) {
            return false;
        }

        int[] s1CharToFreq = new int[26];
        int N = s1.length();

        // Time: o(n)
        // Space: o(1) because s1CharToFreq is 26 character
        for (char c: s1.toCharArray()) {
            s1CharToFreq[computeCharIndex(c)] += 1;
        }

        int left = 0, size = 0;
        int[] s2CharToFreq = new int[26];

        // Time: o(m)
        // space: o(1)
        for (int right = 0; right < s2.length(); right++) {
            char rightChar = s2.charAt(right);
            int rightCharIndex = computeCharIndex(rightChar);
            s2CharToFreq[rightCharIndex] += 1;


            while(left < right && (s1CharToFreq[computeCharIndex(s2.charAt(left))] == 0 || s1CharToFreq[rightCharIndex] < s2CharToFreq[rightCharIndex])) {
                char leftChar = s2.charAt(left);
                int leftCharIndex = computeCharIndex(leftChar);

                if (s1CharToFreq[leftCharIndex] > 0) {
                    --size;
                }

                s2CharToFreq[leftCharIndex] -= 1;
                ++left;
            }

            if (s1CharToFreq[rightCharIndex] > 0) {
                ++size;
                if (size == N && right - left + 1 == N) {
                    return true;
                }
            }
        }

        return false;

    }

    private int computeCharIndex(char c) {
        return c - 'a';
    }
}
