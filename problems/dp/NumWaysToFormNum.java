package problems.dp;

/*
    Problem Statement: given a list of numbers arr with length N, count the number of ways 
    we can form a number num using the sum of the given numbers in arr.
*/

public class NumWaysToFormNum {
    /**
     * Dynamic Programming solution
     * 
     * @param arr
     * @param num
     * @return int number of ways to arrive at num
     */
    public static int solution(int[] arr, int num) {
        int[] dp = new int[num + 1];

        for (int n : arr) {
            if (n > num || n < 0)
                continue;
            dp[n] = 1;
        }

        for (int i = 0; i < dp.length; i++) {
            if (dp[i] == 1)
                continue;
            int sum = 0;

            for (int n : arr) {
                if (i - n < 0)
                    continue;
                sum += dp[i - n];
            }

            dp[i] = sum;
        }

        return dp[num];
    }

    public static void main(String[] args) {
        int[] arr = { 1, 3, 5 };
        int num = 8;
        System.out.println(solution(arr, num));
    }
}
