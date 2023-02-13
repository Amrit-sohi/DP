public class TargetSumSubset{
    // this problem is a variation of knapsack01
    //Code Using recursion
    public static boolean isTargetSumSubset(int arr[],int targetSum,int n){ // Time Complexity : O(2^n)
        if(n == 0){
            return false;
        }
        if(targetSum == 0){
            return true;
        }
        if(targetSum == 0 && n == 0){
            return true;
        }

        
        if(arr[n-1] <= targetSum){
            // Including
            boolean ans1 = isTargetSumSubset(arr, targetSum-arr[n-1], n-1);
            // Excluding
            boolean ans2 = isTargetSumSubset(arr, targetSum, n-1);
            return ans1 || ans2;
        }else{
            return isTargetSumSubset(arr, targetSum, n-1);
        }
    }

    // Code Using Memoization
    public static boolean isTargetSumSubset(int arr[],int targetSum,boolean dp[][],int n){ // Time Complexity : O(n*targetSum)
        if(n == 0){
            return false;
        }
        if(targetSum == 0){
            return true;
        }
        if(targetSum == 0 && n == 0){
            return true;
        }

        if(dp[n][targetSum] != false){
            return true;
        }

        if(arr[n-1] <= targetSum){
            boolean ans1 = isTargetSumSubset(arr, targetSum-arr[n-1], dp, n-1);
            boolean ans2 = isTargetSumSubset(arr, targetSum, dp, n-1);
            dp[n][targetSum] = ans1 || ans2;
            return dp[n][targetSum];
        }else{
            return dp[n][targetSum] = isTargetSumSubset(arr, targetSum, dp, n-1);
        }
    }

    // Code Using tabulation
    public static boolean isTargetSumSubset(int arr[],int targetSum){ // Time Complexity : O(n * targetSum)

        // dp(i,j) ==> can i items make subset sum = j
        int n = arr.length;
        boolean dp[][] = new boolean[n+1][targetSum+1];
        // i means number of items
        // j means subsetsum or target sum

        // Initialization
        // if the targetSum == 0 then if we don't include any item the sum will remain zero,it means dp[i][0] = true
        // if the number of items = 0 then we cant achieve target sum,it means dp[0][j] = false except when j = 0(means target sum and number of items = 0 :: dp[0][0] = true)

        // Initializing 0th col with true, other cells are false by default.
        for(int i = 0; i < dp.length; i++){
            dp[i][0] = true;
        }

        for(int i = 1; i < dp.length; i++){
            for(int j = 1; j < dp[i].length; j++){
                int val = arr[i-1]; // means ith item
                // Condition for valid case
                // if the value of ith item is less than of equal to subsetSum(j) then it's a valid case.
                // Case 1 : Including val 
                if(val <= j && dp[i-1][j-val] == true){
                    dp[i][j] = true;
                }
                // Case 2 : Excluding val 
                else if(dp[i-1][j] == true){
                    dp[i][j] = true;
                }
            }
        }
        return dp[n][targetSum];
    }
    public static void main(String[] args) {
        int arr[] = {4,2,7,1,3};
        int targetSum = 5;
       System.out.println("Result Using Recursion = " + isTargetSumSubset(arr, targetSum, arr.length));

       boolean dp[][] = new boolean[arr.length+1][targetSum+1];
       System.out.println("Result Using Memoization = " + isTargetSumSubset(arr, targetSum, dp, arr.length));

       System.out.println("Rsult Using Tabulation = " + isTargetSumSubset(arr, targetSum));

    }
}