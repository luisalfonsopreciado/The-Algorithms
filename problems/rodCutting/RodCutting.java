package problems.rodCutting;

public class RodCutting {

    public static int maxProfitBottomUp(int[] prices, int n) {
        if (prices.length < n || n < 0) {
            System.out.println("Invalid Input");
            return -1;
        }

        int[] dp = new int[n + 1];
        dp[1] = prices[0];

        for (int i = 2; i < dp.length; i++) {
            dp[i] = prices[i - 1];
            for (int j = 1; j <= i / 2; j++) {
                dp[i] = Math.max(dp[i], dp[j] + dp[i - j]);
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        int[] prices = { 1, 5, 8, 9, 10, 17, 17, 20, 24, 30 };
        System.out.println(maxProfitBottomUp(prices, 10));
    }
}
