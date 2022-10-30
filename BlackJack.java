public class BlackJack {

    public static int[] noOfWays(int[] cards) {
        // create a dp array with index 0 - 21: no of ways dealer busts when the cards sum up to ith.
        // base case:
        //   dp[21] = 10 when dealer has cards sum up to 21 then he will burst next time with card from 1 to 10
        // induction rule:
        //  dp[i] = dp[i + 1] + 1  + dp[i + 2] + 1 + .... dp[i + 10] + 1
        // return dp[0]
        // time complexity O(21 * 10)
        // Space: O(22) -> O(1)

        int[] dp = new int[22];
        dp[21] = 10;

        for (int i = 20; i >= 0; i--) {
            for (int j = 1; j <= 10; j++) {
                if (i + j <= 21) {
                    dp[i] += dp[i + j] + 1;
                } else {
                    break;
                }
            }
        }


        return dp;
    }

    public static void main(String[] args) {

        int[] result = noOfWays(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9 ,10});
        for (int num : result) {
            System.out.println(num);
        }
    }
}
