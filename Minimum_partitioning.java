public class Minimum_partitioning {
    // This Question is a variation of knapsack 01
    public static int minDiff(int arr[]){ // Time Complexity : O(n^2)
        int n = arr.length;
        int sum = 0;
        for(int ele : arr){
            sum += ele;
        }
        int W = sum/2;

        int dp[][] = new int[n+1][W+1];

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= W; j++){
                if(arr[i-1] <= j){
                    dp[i][j] = Math.max(arr[i-1] + dp[i-1][j-arr[i-1]], dp[i-1][j]);
                }else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        int sum1 = dp[n][W];

        int sum2 = sum - sum1;
        return Math.abs(sum2-sum1);

    }
    public static void main(String[] args) {
        
        int arr[] = {3,9,7,3};
        System.out.println(minDiff(arr));

    }
}
