public class LCS {
    /*
    Given two strings str1 and str2, return the length of their longest common subsequence. If there is no common subsequence, return 0.

    A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.
     */
    // Code Using Recursion
    public static int findLCS(String str1,String str2,int n,int m){ // Time Complexity : 2^n Or 2^m
        // Base Case
        if(n == 0 || m == 0){
            return 0;
        }

        // Case 1 : if the nth character of string1 is equal to mth character of string2 then call the next level by reducing size of n and m by one 
        if(str1.charAt(n-1) == str2.charAt(m-1)){
            return 1 + findLCS(str1, str2, n-1, m-1);
        }else{
        // Case 2 : if the nth character of string1 is not equal to mth character of string2 then call the next level by reducing either size of string1(n) by one or string2(m) by one then take the maximam from both.
            int ans1 = findLCS(str1, str2, n-1, m);     
            int ans2 = findLCS(str1, str2, n, m-1);
            return Math.max(ans1,ans2);     
        }
    }

    // Code Using Memoization
    public static int findLCS(String str1,String str2,int n,int m,int dp[][]){ // Time Complexity : O(n*m)
        if(n == 0 || m == 0){
            return 0;
        }

        if(dp[n][m] != 0){
            return dp[n][m];
        }

        if(str1.charAt(n-1) == str2.charAt(m-1)){
            return dp[n][m] = 1 + findLCS(str1, str2, n-1, m-1, dp);
        }else{
            int ans1 = findLCS(str1, str2, n-1, m);     
            int ans2 = findLCS(str1, str2, n, m-1);
            return dp[n][m] = Math.max(ans1,ans2);
        }

    }

    // Code Using Tabulation
    public static int findLCS(String str1,String str2){ // Time Complexity : O(n * m)
        int n = str1.length(),m = str2.length();
        // Step 1 : Create a table 
        int dp[][] = new int[n+1][m+1];

        //  Step 2 :Initialize dp array with base case and in our memory assign meaning to each cell
        /*
        Here i -> string 1 length, j -> string 2 length
        dp(i,j) stores lcs of string1 of length i and string2 of length j
         */
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                if(i == 0 || j == 0){
                    dp[i][j] = 0;
                }
            }
        }

        // Step 3 : fill Cells in Bottom Up Manner
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                if(str1.charAt(i-1) == str2.charAt(j-1)){
                    dp[i][j] = 1 +  dp[i-1][j-1];
                }else{
                    int lcs1 = dp[i-1][j]; 
                    int lcs2 = dp[i][j-1]; 
                    dp[i][j] = Math.max(lcs1, lcs2);
                }
            }
        }

        return dp[n][m];
    }
    public static void main(String[] args) {
        String str1 = "abcdge";
        String str2 = "abedg";
        // String str1 = "abcde";
        // String str2 = "ace";
        System.out.println("Length of Longest Common Subsequence using Recursion = " + findLCS(str1, str2, str1.length(), str2.length()));

        int n = str1.length();
        int m = str2.length();
        int dp[][] = new int[n+1][m+1];
        System.out.println("Length of Longest Common Subsequence using Memoization = " + findLCS(str1, str2, n, m, dp));

        System.out.println("Length of Longest Common Subsequence using Tabulation = " + findLCS(str1, str2));
    }
    
}
