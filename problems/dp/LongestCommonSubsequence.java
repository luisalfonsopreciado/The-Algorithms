package problems.dp;

public class LongestCommonSubsequence {
    /**
     * Given two strings text1 and text2, returns the length of their longest common
     * subsequence.
     * 
     * A subsequence of a string is a new string generated from the original string
     * with some characters(can be none) deleted without changing the relative order
     * of the remaining characters. (eg, "ace" is a subsequence of "abcde" while
     * "aec" is not). A common subsequence of two strings is a subsequence that is
     * common to both strings.
     *
     * If there are no common subsequence 0 is returned.
     * 
     * @param text1 - String
     * @param text2 - String
     * @return int - length of longest common subsequence
     */
    public static int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];

        for (int row = 1; row < dp.length; row++) {
            for (int col = 1; col < dp[row].length; col++) {
                if (text2.charAt(col - 1) == text1.charAt(row - 1)) {
                    dp[row][col] = 1 + dp[row - 1][col - 1];
                } else {
                    dp[row][col] = Math.max(dp[row - 1][col], dp[row][col - 1]);
                }
            }
        }

        return dp[text1.length()][text2.length()];
    }

    public static void main(String[] args) {
        System.out.println(longestCommonSubsequence("", "")); // 0
        System.out.println(longestCommonSubsequence("abcde", "ace")); // 3
    }
}
