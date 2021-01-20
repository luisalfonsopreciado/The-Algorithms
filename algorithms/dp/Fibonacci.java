package algorithms.dp;

import java.util.HashMap;

/**
 * A class that computes Fibonacci numbers, implemented for educational
 * purposes.
 * 
 */
public class Fibonacci {
    static HashMap<Integer, Long> memo;

    public Fibonacci() {
        memo = new HashMap<>();
    }

    /**
     * A Recursive solution for finding the N'th fibonacci number.
     * 
     * @param N
     */
    public long fibRecursive(int N) {
        if (N <= 1)
            return N;
        return fibRecursive(N - 1) + fibRecursive(N - 2);
    }

    /**
     * A Recursive solution that uses memoization for finding the N'th fibonacci
     * number (Top Down DP Approach).
     * 
     * @param N
     */
    public long fibMemo(int N) {
        if (memo.containsKey(N))
            return memo.get(N);
        if (N <= 1)
            return N;
        long ans = fibMemo(N - 1) + fibMemo(N - 2);
        memo.put(N, ans);
        return ans;
    }

    /**
     * Bottom up Dynamic Programming approach for finding the N'th fibonacci number.
     * 
     * @param N
     */
    public long fibBottomUp(int N) {
        if (N <= 0)
            return N;
        int[] dp = new int[(int) N + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= N; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[N];
    }

    /**
     * Bottom up Dynamic Programming approach for finding the N'th fibonacci number,
     * uses constant memory.
     * 
     * @param N
     */
    public long fibBottomUpImprovedSpace(int N) {
        if (N <= 0)
            return N;
        int[] dp = new int[2];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= N; i++) {
            int next = dp[0] + dp[1];
            dp[0] = dp[1];
            dp[1] = next;
        }
        return dp[1];
    }

    public static void main(String[] args) {
        Fibonacci f = new Fibonacci();
        System.out.println(f.fibBottomUp(10));
        System.out.println(f.fibBottomUpImprovedSpace(43));
    }
}
