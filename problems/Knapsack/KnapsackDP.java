package problems.Knapsack;

/**
 * KnapsackDP class solves the knapsack problem using Dynamic Programming
 * Tabulation Method.
 * 
 * @param profits
 * @param weights
 * @param bagSize
 * @return
 */
public class KnapsackDP {

    /**
     * Get the maximum possible profit given a bag of items where each item yields
     * profit[i] and has weight[i], the total capacity of weight we can hold is given
     * in bagSize.
     * 
     * @param profits
     * @param weights
     * @param bagSize
     * @return
     */
    public static int solution(int[] profits, int[] weights, int bagSize) {
        int[][] dp = new int[weights.length + 1][bagSize + 1];

        // We have zero items available to choose from, the answer is zero
        for (int col = 0; col < dp[0].length; col++) {
            dp[0][col] = 0;
        }

        // When we have zero space in the knapsack, the answer would be zero
        for (int row = 0; row < dp.length; row++) {
            dp[row][0] = 0;
        }

        for (int row = 1; row < dp.length; row++) {
            int itemWeight = weights[row - 1];

            for (int i = 0; i < itemWeight; i++) {
                dp[row][i] = dp[row - 1][i];
            }

            dp[row][itemWeight] = profits[row - 1];

            for (int col = itemWeight + 1; col < dp[row].length; col++) {
                int other = col - itemWeight < 0 ? profits[row - 1] : dp[row - 1][col - itemWeight] + profits[row - 1];
                dp[row][col] = Math.max(dp[row - 1][col], other);
            }
        }

        printGrid(dp);

        return dp[dp.length - 1][dp[0].length - 1];
    }

    public static void printGrid(int[][] grid) {
        for (int[] row : grid) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] P = { 1, 2, 5, 6 };
        int[] W = { 2, 3, 4, 5 };
        System.out.println(solution(P, W, 8));
    }
}
