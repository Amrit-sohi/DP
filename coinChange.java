public class coinChange {
    // This problem is a variation of unbounded knapsack
    // Code Using Recursion
    static int ans;
    public static int coin_change(int coins[],int sum,int n){ // time Complexity : O(2^sum)
        if(n == 0){
            return 0;
        }
        if(sum == 0){
            return 1;
        }

        if(coins[n-1] <= sum){ // valid
            ans = (coin_change(coins, sum-coins[n-1], n) + coin_change(coins, sum, n-1)); 
            return ans;
        }else{
            return coin_change(coins, sum, n-1);
        }
    }

    // Code Using Memoization
    public static int coin_change(int coins[],int sum,int n,int dp[][]){ // time Complexity : O(n*sum)
        if(n == 0){
            return 0;
        }
        if(sum == 0){
            return 1;
        }

        if(dp[n][sum]!=0){
            return dp[n][sum];
        }

        if(coins[n-1] <= sum){ // valid
            dp[n][sum] = (coin_change(coins, sum-coins[n-1], n) + coin_change(coins, sum, n-1)); 
            return dp[n][sum];
        }else{
            return dp[n][sum] = coin_change(coins, sum, n-1);
        }
    }



    // Code Using Tabulation
    public static int coin_change(int coins[],int sum){ // Time Complexity : O(n * Sum)
        // Step 1 : create a dp array
        int n = coins.length;
        int dp[][] = new int[n+1][sum+1];
        // Here i -> number of coins and j -> sum
        // means by considering n coins how many ways to make change or sum = j  

        // Step 2 : Initialization
        //  Case 1 : if number of coins = 0 then it is not possible to make sum, so we initialize 0th row with 0, except (0,0) cell
        for(int i = 1; i < dp[0].length; i++){ // 0th row Initialization
            dp[0][i] = 0;
        } 
        //  Case 2 : if sum = 0 then there is only 1 way to give change(sum), so we initailze 0th column with 1
        for(int i = 0; i < dp.length; i++){  // 0th column Initialization  
            dp[i][0] = 1;
        }

        // Step 3 : Fill dp array in bottom up manner(means small to large problem)
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= sum; j++){
                if(coins[i-1] <= j){ // Valid case
                            //  (Include)+(Exclude)
                    dp[i][j] = dp[i][j-coins[i-1]] + dp[i-1][j];
                }else{ // Invalid case then we simply exlude ith coin
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[n][sum];
    }

    // Optimization in space from O(n*sum) to O(sum) with same time complexity as in tabulation
    public static int coin_changeOptimized(int coins[],int sum){ 
        int n = coins.length;

        int dp[] = new int[sum+1];

        dp[0] = 1;

        for(int i = 0; i < n; i++){
            for(int j = coins[i]; j <= sum; j++){
                dp[j] += dp[j-coins[i]];
            }
        }
        return dp[sum];
    }
    public static void main(String[] args) {
        int coins[] = {2,5,3,6};
        int sum = 10; // or change
        // int sum = 4;
        // int coins[] = {1,2,3};
        
        System.out.println("Total number of ways to make change of " + sum + " using recursion = " + coin_change(coins, sum, coins.length));
        
        int dp[][] = new int[coins.length+1][sum+1];
        System.out.println("Total number of ways to make change of " + sum + " using memoization = " + coin_change(coins, sum, coins.length, dp));

        System.out.println("Total number of ways to make change of " + sum + " using tabulation = " + coin_change(coins, sum));

        System.out.println("Total number of ways to make change of "+ sum + " using tabulation with optimized space = " + coin_changeOptimized(coins, sum));
              
    }
    
}
