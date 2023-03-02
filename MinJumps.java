import java.util.Arrays;
// Minimum Array jumps
public class MinJumps {
    public static int min_jmps(int arr[]){
        int n = arr.length;
        int dp[] = new int[n];
        Arrays.fill(dp,-1);
        dp[n-1] = 0;

        for(int i = n-2; i >= 0; i--){
            int steps = arr[i];
            int ans = Integer.MAX_VALUE;
            for(int j = i+1; j <= steps+i && j < n; j++){
                if(dp[j] != -1){
                    ans = Math.min(ans, dp[j] + 1);
                }
            }
            if(ans != Integer.MAX_VALUE){
                dp[i] = ans;
            }
        }
        // Minimum steps to reach n-1 form 0
        return dp[0];
    }
    public static void main(String[] args) {
        // int arr[] = {3,2,1,0,4};
        int arr[] = {2,3,1,1,4};
        System.out.println("Minimum jumps required = " + min_jmps(arr));

    }
    
}
