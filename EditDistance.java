public class EditDistance {
    /*
     * Given two strings word1 and word2, return the minimum number of operations
     * required to convert word1 to word2.
     * 
     * You have the following three operations permitted on a word:
     * 
     * Insert a character
     * Delete a character
     * Replace a character
     */

    public static int calc_min_Opr(String str1,String str2){ // Time Complexity : O(n*m)
        int n = str1.length();
        int m = str2.length();

        int dp[][] = new int[n+1][m+1];
        // Here dp(i,j) stores the minimum number of operations that we have to perform, to convert string 1 of length i to string 2 of length j. 

        // When the length of string 2 = 0 then 
        // To convert string 1 to string 2 we have to perform delete operation on string 1 equal to it's length.
        for(int i = 0; i <= n; i++){
            dp[i][0] = i;
        }

        // When the length of string 1 = 0 then
        // To convert string 1 to string 2 we have to perform insert operations on string 1 equal to length of string 2. 
        for(int j = 0; j <= m; j++){
            dp[0][j] = j;
        }

        for(int i = 1; i <= n; i++){
            for (int j = 1; j <= m; j++) {
                if(str1.charAt(i-1) == str2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1]; // see diagonally left
                }else{
                    int instOpr = dp[i][j-1];  // see left
                    int delOpr = dp[i-1][j]; // see up
                    int replaceOpr = dp[i-1][j-1]; // see diagonally left
                    dp[i][j] = 1 + Math.min(replaceOpr,Math.min(instOpr,delOpr));
                }
            }
        }
        return dp[n][m];
    }
    

    public static void main(String[] args) {  
        // String str1 = "sde";
        // String str2 = "spd";    
        String str1 = "intention";
        String str2 = "execution";    
        System.out.println("Minimum Number of Operations = " + calc_min_Opr(str1, str2));
    }
}
