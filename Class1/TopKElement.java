package Class1;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

// https://leetcode.com/problems/top-k-frequent-elements/
// Start: 11:03
// end: 11:11
public class TopKElement {
    public int[] topKFrequent(int[] nums, int k) {
        // Create a num count hashmap
        // key is num and count is frequency
        // create priorityqueue with custom sorting,
        // add num to priorityqueue one by one and sort them desc by freq
        // Time: O(Nlog(N)) <--- I read the discussion and realize this can optimize to O(Nlog(K)) prioritize queue just need to store K element
        // Space: O(2N + K)

        Map<Integer, Integer> numToCount = new HashMap<>();

        // Time: O(N)
        // Space: O(N)

        for (int num : nums) {
            int count = numToCount.getOrDefault(num, 0);
            numToCount.put(num, count + 1);
        }

        // Time: O(Nlog(N)
        // Space: O(N)
        Queue<Integer> pq = new PriorityQueue<>((a, b) -> numToCount.get(b) - numToCount.get(a));

        for (Map.Entry<Integer, Integer> entry : numToCount.entrySet()) {
            pq.offer(entry.getKey());
        }

        // Time: O(Nlog(N)
        // Space: O(N)
        int[] result = new int[k];

        for (int i = 0; i < k; i++) {
            result[i] = pq.poll();
        }

        return result;
    }
}
