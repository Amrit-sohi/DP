public class LongestCommonSubstring{
    // This problem is a variation of LCS

    // Code Using recursion
    public static int find_lc_substring(String str1,String str2,int n,int m,int len){ // Time Complexity :  O(2^max(m,n))
        if(n == 0 || m == 0){
            return len;
        }

        if(str1.charAt(n-1) == str2.charAt(m-1)){
            len = find_lc_substring(str1, str2, n-1, m-1, len+1);
            return len;
        }else{
            int ans1 = find_lc_substring(str1, str2, n-1, m, 0);
            int ans2 = find_lc_substring(str1, str2, n, m-1, 0);
            return Math.max(len,Math.max(ans1, ans2));
        }
    }

    // Code Using Memoization
    static int ans = 0;
    public static int find_lc_substring(String str1,String str2,int n,int m,int dp[][]){ // Time Complexity : O(n*m)
        if(n == 0 || m == 0){
            return 0;
        }

        if(dp[n][m] != 0){
            return dp[n][m];
        }

        find_lc_substring(str1, str2, n-1, m, dp);
        find_lc_substring(str1, str2, n, m-1, dp);

        if(str1.charAt(n-1) == str2.charAt(m-1)){
            dp[n][m] = find_lc_substring(str1, str2, n-1, m-1, dp)+1;
            ans = Math.max(ans,dp[n][m]);
            return dp[n][m];
        }else{
            return dp[n][m] = 0;
        }
    }

    // Code Using tabulation
    public static int find_lc_substring(String str1,String str2){ // Time Complexity : O(n*m)
        int n = str1.length();
        int m = str2.length();
        int dp[][] = new int[n+1][m+1];
        

        // Initializing and assign meaning to each cell of array
        /*
        Here i -> string 1 length, j -> string 2 length
        dp(i,j) stores longest common substring of string1 of length i and string2 of length j
         */
        // when m = 0 mean when the length of string2 is 0
        for(int i = 0; i < dp.length; i++){
            dp[i][0] = 0;
        }
        // when n = 0 mean when the length of string1 is 0
        for(int i = 0; i < dp[0].length; i++){
            dp[0][i] = 0;
        }

        // Fill the array in bottom up manner
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if(str1.charAt(i-1) == str2.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                    ans = Integer.max(ans, dp[i][j]);
                }else{
                    dp[i][j] = 0;
                }  
            }
        }

        return ans;
    }
    public static void main(String[] args) {
        // String str1 = "abcd";
        // String str2 = "bd";
        // String str1 = "ABCDGH";
        // String str2 = "ACDGHR";
        // String str1 = "abcjklp";
        // String str2 = "acjkp";
        String str1 = "abcdefg";
        String str2 = "cdef";
        System.out.println("Longest common Substring using tabulation = " + find_lc_substring(str1, str2));
        System.out.println("Longest common Substring using Recursion =  " + find_lc_substring(str1, str2, str1.length(), str2.length(), 0));

        int dp[][] = new int[str1.length()+1][str2.length()+1];
        find_lc_substring(str1, str2, str1.length(), str2.length(), dp);
        System.out.println("Longest common Substring using Memoization = " + ans);
    }
}