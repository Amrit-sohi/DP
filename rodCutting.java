public class rodCutting {
    // This problem is a variation of unbounded knapsack
    /*
    Given a rod of length N inches and an array of prices, price[]. price[i] denotes the value of a piece of length i. Determine the maximum value obtainable by cutting up the rod and selling the pieces.

    Example 1:

    Input:
    N = 8
    Price[] = {1, 5, 8, 9, 10, 17, 17, 20}

    Output:
    22  
    */

    // Code Using recursion
    public static int rod_cutting(int rodLength,int prices[],int n){ // Time Complexity : O(2^n)
        // Base case
        // if the rod length is 0 then it's not possible to gain profit  OR  if the number of items in price array is 0 then it's not possible to gain profit
        if(rodLength == 0 || n == 0){
            return 0;
        }

        // Valid case
        if(n <= rodLength){
            // Profit by including piece of length n
            int ans1 = prices[n-1] + rod_cutting(rodLength-n, prices, n);
            
            // Profit by excluding piece of length n
            int ans2 = rod_cutting(rodLength, prices, n-1);

            return Math.max(ans1, ans2);
        }else{ // Invalid case
            return rod_cutting(rodLength, prices, n-1);
        }
    }

    // Code Using Memoization 
    public static int rod_cutting(int rodLength,int prices[],int n,int dp[][]){ // Time Complexity : O(n * rodlength) == O(n^2)
        if(rodLength == 0 || n == 0){
            return 0;
        }
        // if we already calculated the maxProfit gain by cutting the rod into i pieces of j length 
        if(dp[n][rodLength] != 0){
            return dp[n][rodLength];
        }

        if(n <= rodLength){
            // Profit by including piece of length n
            int ans1 = prices[n-1] + rod_cutting(rodLength-n, prices, n,dp);
            
            // Profit by excluding piece of length n
            int ans2 = rod_cutting(rodLength, prices, n-1,dp);

            return dp[n][rodLength] = Math.max(ans1, ans2);
        }else{
            return dp[n][rodLength] = rod_cutting(rodLength, prices, n-1, dp);
        }

    }

    // Code Using Tabulation
    public static int rod_cutting(int n,int prices[]){ // Time Complexity : O(n^2)
        int dp[][] = new int[n+1][n+1];

        // Initialization
        for(int i = 0; i < dp.length; i++){
            for(int j = 0; j < dp[i].length; j++){
                if(i == 0 || j == 0){
                    dp[i][j] = 0;
                }
            }
        }

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                if(i <= j){
                    dp[i][j] = Math.max(prices[i-1] + dp[i][j-i] , dp[i-1][j]);
                }else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        } 
        return dp[n][n];
    }
    public static void main(String[] args) {
        int prices[] = {3, 5, 8, 9, 10, 17, 17, 20};

        int n = prices.length;
        int rodLength = n;

        System.out.println("Max Profit gain by cutting rode in pieces (using recursion)  = " + rod_cutting(rodLength, prices, n));

        int dp[][] = new int[n+1][rodLength+1];
        System.out.println("Max Profit gain by cutting rode in pieces (using Memoization)  = " + rod_cutting(rodLength, prices, rodLength, dp));
        
        System.out.println("Max Profit gain by cutting rode in pieces (using tabulation)  = " + rod_cutting(rodLength, prices));

    }
    
}
