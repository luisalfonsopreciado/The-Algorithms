package algorithms.dp;

/**
 * the LevenshteinDistance class calculates the edit distance between two input
 * strings.
 */
public class LevenshteinDistance {
    public static int solution(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        int n = dp.length, m = dp[0].length;

        // The answer between the empty string and any other string is simply the length
        // of the
        // string
        for (int col = 0; col < m; col++) {
            dp[0][col] = col;
        }

        for (int row = 0; row < n; row++) {
            dp[row][0] = row;
        }

        for (int row = 1; row < n; row++) {
            for (int col = 1; col < m; col++) {
                if (s1.charAt(row - 1) == s2.charAt(col - 1)) {
                    // If they are the same, the edit distance is equal to the edit distance of both
                    // strings minus the last character
                    dp[row][col] = dp[row - 1][col - 1];
                } else {
                    dp[row][col] = 1 + Math.min(dp[row - 1][col], Math.min(dp[row][col - 1], dp[row - 1][col - 1]));
                }
            }
        }

        return dp[n - 1][m - 1];
    }

    public static void main(String[] args) {
        String s1 = "booksareawesomee";
        String s2 = "horsesareawesome";
        System.out.println(solution(s1, s2));
    }
}
