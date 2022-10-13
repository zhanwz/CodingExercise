package Class1;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class TopKElementPart2 {
    class Solution {
        public int[] topKFrequent(int[] nums, int k) {
            // Create a num count hashmap
            // key is num and count is frequency
            // create priorityqueue with custom sorting,
            // add num to priorityqueue one by one and sort them desc by freq
            // Time: O(Nog(K) + N + O(Klog(K))
            // Space: O(N + 2K)

            Map<Integer, Integer> numToCount = new HashMap<>();

            // Time: O(N)
            // Space: O(N)

            for (int num : nums) {
                int count = numToCount.getOrDefault(num, 0);
                numToCount.put(num, count + 1);
            }

            // Time: O(Nlog(K)
            // Space: O(K)
            // Should be sort by asc
            Queue<Integer> pq = new PriorityQueue<>((a, b) -> numToCount.get(a) - numToCount.get(b));

            for (Map.Entry<Integer, Integer> entry : numToCount.entrySet()) {
                // if pq's first element's freq is less than current entry's freq then remove it and add the new entry
                if (pq.size() == k && numToCount.get(pq.peek()) < entry.getValue()) {
                    pq.poll();
                    pq.offer(entry.getKey());
                }

                if (pq.size() < k) {
                    pq.offer(entry.getKey());
                }
            }

            // Time: O(Klog(K)
            // Space: O(K)
            int[] result = new int[k];

            for (int i = k - 1; i >= 0; i--) {
                result[i] = pq.poll();
            }

            return result;
        }
    }
}
