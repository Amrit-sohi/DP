import java.util.Arrays;

public class unBoundedKnapSack {
     // Code Using only Recursion
     public static int unboundedknapSack(int val[],int wt[],int W,int n){ // Time Complexity : O(2^n)
        // Base Case
        if(n == 0 || W == 0){
            return 0;
        }

        if(wt[n-1] <= W){ // Valid case
            // Including self weight
            int ans1 = val[n-1] + unboundedknapSack(val, wt, W-wt[n-1], n);

            // Excluding self weight
            int ans2 = unboundedknapSack(val, wt, W, n-1);

            return Math.max(ans1,ans2);
        }else{ // Invalid
            return unboundedknapSack(val, wt, W, n-1);
        }
    }

    // Code Using Recursion + Memoization
    public static int unboundedknapSack(int val[],int wt[],int W,int n,int dp[][]){ // Time Complexity : O(n * W)
        // Base Case
        if(n == 0 || W == 0){
            return 0;
        }
        // if we already calculated the max profit for n items carry in knapsack of W weight then simply return it,no need to repeat again.
        if(dp[n][W] != -1){
            return dp[n][W];
        }

        if(wt[n-1] <= W){ // Valid
            // Including
            int ans1 = val[n-1] + unboundedknapSack(val, wt, W-wt[n-1], n,dp);
            // Excluding
            int ans2 = unboundedknapSack(val, wt, W, n-1,dp);

            dp[n][W] = Math.max(ans1, ans2);
            return dp[n][W];
        }else{ // Invalid
            dp[n][W] = unboundedknapSack(val, wt, W, n-1,dp);
            return dp[n][W];
        }
    }

    // Code Using Tabulation
    public static int unboundedKnapSack(int val[],int wt[],int W){ // Time complexity : O(n * W)
        int n = val.length;
        int dp[][] = new int[n+1][W+1];

        for(int i = 0; i < dp.length; i++){ // Initialize column 0
            dp[i][0] = 0;
        }
        for(int i = 0; i < dp[0].length; i++){ // Initialize row 0
            dp[0][i] = 0;
        }

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= W; j++){
                if(wt[i-1] <= j){
                    int v = val[i-1];
                    int w = wt[i-1];
                    dp[i][j] = Math.max(v + dp[i][j-w],dp[i-1][j]);
                }else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        // print(dp);
        return dp[n][W];
    }

    // Utitlity to print DP array to verify process
    public static void print(int dp[][]){
        for (int[] row : dp) {
            for (int ele : row) {
                System.out.print(ele + " ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        int val[] = {15,14,10,45,30};
        int wt[] = {2,5,1,3,4}; 
        int W = 7;
        System.out.println("Result by Using Recursion = " + unboundedknapSack(val, wt, W, val.length));
        

        int dp[][] = new int[val.length+1][W+1];
        for(int i = 0; i < dp.length; i++){
            Arrays.fill(dp[i], -1);
        }
        System.out.println("Result by Using Memoization = " + unboundedknapSack(val, wt, W, val.length,dp));


        System.out.println("Result by Using Tabulation = " + unboundedKnapSack(val, wt, W));

    }
    
}
