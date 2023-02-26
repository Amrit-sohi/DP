import java.util.Arrays;

public class MatrixChainMultiplication {

    public static int mcm_rec(int arr[], int i,int j){ // Time Complexity : exponential
        // Base Case
        if(i == j){
            return 0;
        }
        int ans = Integer.MAX_VALUE;

        for(int k = i; k <= j-1; k++){
            int cost1 = mcm_rec(arr, i, k); // Ai.....Ak => arr[i-1] * arr[k]
            int cost2 = mcm_rec(arr, k+1, j); // Ak+1....Aj => arr[k] * arr[j] 
            int mergeCost = arr[i-1] * arr[k] * arr[j]; // a * b * d = cost of multiplication of two matrices
            int finalCost = cost1 + cost2 + mergeCost;
            ans = Integer.min(ans, finalCost); 
        }
        return ans;
    }

    public static int mcm_Mem(int arr[],int i,int j,int dp[][]) { // Time Complexity : O(n^3)
        // Base Case 
        if(i == j){
            return 0;
        }

        if(dp[i][j] != -1) {
            return dp[i][j];
        }

        int ans = Integer.MAX_VALUE;
        for(int k = i; k <= j-1; k++) {
            int cost1 = mcm_Mem(arr, i, k, dp);
            int cost2 = mcm_Mem(arr, k+1, j, dp);
            int cost3 = arr[i-1] * arr[k] * arr[j];
            int finalCost = cost1 + cost2 + cost3;
            ans = Integer.min(ans, finalCost);
        }
        return dp[i][j] = ans;
    }

    public static int mcmTab(int arr[]){
        int n = arr.length;
        int dp[][] = new int[n][n];

        for(int i = 0 ; i < n; i++){
            dp[i][i] = 0;
        }

        // len means number of matrices to multiply like len = 2 means Muptiply A1 matrix with A2 matrix 
        for(int len = 2; len <= n-1; len++){ // Time Complexity : O(n^3)
            // Traversing 2d array diagonal wise
            for(int i = 1; i <= n-len; i++){
                // tricky and unintutive part 
                int j = i + len - 1; // column
                dp[i][j] = Integer.MAX_VALUE;
                for(int k = i; k <= j-1; k++){
                    int cost1 = dp[i][k];  // left in (i,j) cell
                    int cost2 = dp[k+1][j]; // down in (i,j) cell
                    int cost3 = arr[i-1] * arr[k] * arr[j];
                    dp[i][j] = Math.min(dp[i][j],cost1 + cost2 + cost3);
                }
            }
        }
        return dp[1][n-1];
    }

    public static void main(String[] args) {
        int arr[] = {1,2,3,4,3};
        // 1 * 2 .  2 * 3  cost = 1.2.3 = 6  res = 1*3
        // 1 * 3 . 3 * 4  cost = 1.3.4 = 12  res = 1*4
        // 1 * 4 . 4 * 3  cost = 1.4.3 = 12  res = 1*3
        System.out.println("Minimum cost for mulptiplying the matrix Using recursion = " + mcm_rec(arr, 1, arr.length-1));
        int dp[][] = new int[arr.length][arr.length];
        for(int i = 0; i < arr.length; i++){
            Arrays.fill(dp[i], -1);
        }
        System.out.println("Minimum cost for mulptiplying the matrix using memoization = " + + mcm_Mem(arr, 1, arr.length-1, dp));
        System.out.println("Minimum cost for mulptiplying the matrix tabulation = " + mcmTab(arr));
    }
    
}
