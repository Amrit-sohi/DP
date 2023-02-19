public class StringConversion {
    // Approach Using LCS
    public static int minOpr(String str1,String str2){ // Time Comlexity : O(n * m)
        int n = str1.length();
        int m = str2.length();

        int dp[][] = new int[n+1][m+1];

        for(int  i = 1; i <= n; i++) { 
            for(int j = 1; j <= m; j++){
                if(str1.charAt(i-1) == str2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else{
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        // Insert Operations
        int insop = n - dp[n][m];
        // Delete Operations
        int delop = m - dp[n][m];
        //  return Total operations 
        return insop + delop;
    }

    // Approach Using Edit Distance
    public static int minOpr2(String str1,String str2){ // Time complexity : O(n * m)
        int n = str1.length();
        int m = str2.length();

        int dp[][] = new int[n+1][m+1];

        for(int i = 0; i <= n; i++) { 
            dp[i][0] = i;
        }
        for(int j = 0; j <= m; j++) { 
            dp[0][j] = j;
        }

        for(int  i = 1; i <= n; i++) { 
            for(int j = 1; j <= m; j++){
                if(str1.charAt(i-1) == str2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    dp[i][j] = 1 + Math.min(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[n][m];
    }
    public static void main(String[] args) {
        String str1 = "ace";
        String str2 = "abcd";
        System.out.println("Minimum Number of operations using LCS Approach = " + minOpr(str1, str2));
        System.out.println("Minimum Number of operations using Edit distance Approach= " + minOpr2(str1, str2));
    }
    
}
