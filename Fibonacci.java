import java.util.Scanner;

public class Fibonacci{

    // Code of Fibonacci Using Different Techniques

    // Using simple recursion
    public static int fibonacci(int n){ // Time Compexity :  O(2^n)
        if(n == 0 || n == 1) {
            return n;
        } return fibonacci(n-1) + fibonacci(n-2);
    }

    // Using Memoization
    public static int fibonacci(int n,int dp[]){ // Time Complexity : O(n)
        if(n == 0 || n == 1){
            return n;
        }
        
        if(dp[n]!=0){  //  dp[n] is alredy calculated 
            return dp[n];
        }
        // else calculate dp[n]
        return dp[n] = fibonacci(n-1,dp) + fibonacci(n-2,dp);
    }

    // Using Tabulation
    public static int fibTabulation(int n){ // Time Complexity : O(n)
        int dp[] = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;

        for(int i = 2; i <= n; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter value of num");
        int num = sc.nextInt();
        System.out.println("Fibonacci of " + num + " by using simple recursion = " + fibonacci(num));
        
        // to memoize sub problems that's already calculated
        int dp[] = new int[num+1];
        System.out.println("Fibonacci of " + num + " by using Memoization = " + fibonacci(num,dp));
        
        System.out.println("Fibonacci of " + num + " by using Tabulation = " + fibTabulation(num));


        sc.close();
    }
}