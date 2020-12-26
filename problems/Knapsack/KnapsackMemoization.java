package problems.Knapsack;

/**
 * KnapsackRecursive class solves the 0/1 Knapsack Problem using a bruteforce
 * recursive approach.
 */
public class KnapsackMemoization {

    static int[][] memo;

    /**
     * Get the maximum possible profit given a bag of items where each item yields
     * profit[i] and has weight[i], the total capacity of weight we can hold is
     * given in bagSize.
     * 
     * @param profits
     * @param weights
     * @param bagSize
     * @return MaxProfit
     */
    public static int solution(int[] profits, int[] weights, int bagSize) {
        memo = new int[weights.length][bagSize + 1];
        return solutionUtil(profits, weights, bagSize, weights.length - 1);
    }

    /**
     * Helper Method for Knapsack Recursive Solution. Solves the Knapsack Problem
     * for the indices [0, N].
     * 
     * @param profits
     * @param weights
     * @param bagSize
     * @param N
     * @return maxProfit
     */
    public static int solutionUtil(int[] profits, int[] weights, int bagSize, int N) {
        if (N == 0 || bagSize == 0) {
            return 0;
        }
        if(memo[N][bagSize] > 0) return memo[N][bagSize];
        if (weights[N] > bagSize) {
            int out = solutionUtil(profits, weights, bagSize, N - 1); 
            memo[N][bagSize] = out;
            return out;
        } else {
            int out = Math.max(profits[N] + solutionUtil(profits, weights, bagSize - weights[N], N - 1),
            solutionUtil(profits, weights, bagSize, N - 1));
            memo[N][bagSize] = out;
            return out;
        }
    }

    public static void main(String[] args) {
        int[] P = { 1, 2, 5, 6 };
        int[] W = { 2, 3, 4, 5 };
        System.out.println(solution(P, W, 8));
    }
}
