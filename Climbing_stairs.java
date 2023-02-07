import java.util.Scanner;

public class Climbing_stairs {
    /* 
        Count Ways to reach the nth stair. The person can climb either 1 stair or 2 stair at a time. 
        (This problem is variation of fibonacci)
    */

    // Code using recursion
    public static int countWays(int n){ // Time Complexity : O(2^n)
        // Base Case
        // the person at the ground floor has only one way that he doesn't move
        if(n == 0){
            return 1;
        }
        // for n == 1 the first call goes for countWays(0) and second call goes for countWays(-1)
        if(n < 0){
            return 0;
        }
        return countWays(n-1) + countWays(n-2);
    }

    // Code Using Memoization
    public static int countWays(int n,int dp[]){ // Time Complexity : O(n) 
        if(n == 0){
            return 1;
        }
        if(n < 0){
            return 0;
        }
        if(dp[n] != 0){
            return dp[n];
        }
        
        dp[n] = countWays(n-1,dp) + countWays(n-2,dp);
        return dp[n];
    }

    // Code Using Tabulation
    public static int countWaysTab(int n){ // Time Complexity : O(n)
        int dp[] = new int[n+1];
        // Initialization
        dp[0] = 1;

        for(int i = 1; i <= n; i++){
            if(i == 1){   // to handle negative value in case of 1 stair
                dp[i] = dp[i-1] + 0;   // 0 is for -ve value means ways = 0 
            }else{
                dp[i] = dp[i-1] + dp[i-2];
            }
        }
        return dp[n];
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of stairs");
        int num= sc.nextInt();
        
        System.out.println("Number of ways to climb " + num + " stairs using simple recursion = " + countWays(num));

        // To store subproblems so that they don't overlap
        int dp[] = new int[num+1];
        System.out.println("Number of ways to climb " + num + " stairs using Memoization = " + countWays(num,dp));

        System.out.println("Number of ways to climb " + num + " stairs using Tabulation = " + countWaysTab(num));
        sc.close();
    }
    
}
