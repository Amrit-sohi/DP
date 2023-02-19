public class WildCardMatching {

    /*
     * Given an input string (s) and a pattern (p), implement wildcard pattern
     * matching with support for '?' and '*' where:
     * 
     * '?' Matches any single character.
     * '*' Matches any sequence of characters (including the empty sequence).
     * The matching should cover the entire input string (not partial).
     */

    public static boolean isMatch(String str, String pat) { // Time Complexity : O(n * m)
        int n = str.length();
        int m = pat.length();

        boolean dp[][] = new boolean[n + 1][m + 1];
        // Here(i,j) stores Is string of length i matches the pattern of length j ?

        dp[0][0] = true;

        // When pattern = ""
        for (int i = 1; i <= n; i++) {
            dp[i][0] = false;
        }

        // When string = ""
        for (int j = 1; j <= m; j++) {
            if (pat.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 1];
            } else {
                dp[0][j] = false;
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (str.charAt(i - 1) == pat.charAt(j - 1) || pat.charAt(j - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (pat.charAt(j - 1) == '*') {
                    // dp[i][j-1] is exclude * case
                    // dp[i-1][j] is include * case so that they can be use again(because * matches
                    // any sequence of characters)
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                } else {
                    dp[i][j] = false;
                }
            }
        }
        return dp[n][m];

    }

    public static void main(String[] args) {
        // String str = "aab";
        // String pattern = "c*a*b"; // ans = false;
        String str = "baaabab";
        String pattern = "*****ba*****ab"; // ans = true;
        System.out.println("Result = " + isMatch(str, pattern));
    }
}