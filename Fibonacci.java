import java.util.Scanner;

public class Fibonacci{

    // Code of Fibonacci using onlu recursion 
    public static int fibonacci(int n){ // Time Compexity :  O(2^n)
        if(n == 0 || n == 1) {
            return n;
        } return fibonacci(n-1) + fibonacci(n-2);
    }

    // Code of Fibonacci Using Dynamic Programming
    public static int fibonacci(int n,int dp[]){ // Time Complexity : O(n);
        if(n == 0 || n == 1){
            return n;
        }
        
        if(dp[n]!=0){  //  dp[n] is alredy calculated 
            return dp[n];
        }
        // else calculate dp[n]
        return dp[n] = fibonacci(n-1,dp) + fibonacci(n-2,dp);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter value of num");
        int num = sc.nextInt();
        System.out.println("Fibonacci of " + num + " by using simple recursion = " + fibonacci(num));
        
        int dp[] = new int[num+1];
        System.out.println("Fibonacci of " + num + " by using dynamic programming = " + fibonacci(num,dp));
        


        sc.close();
    }
}