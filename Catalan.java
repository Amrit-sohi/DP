import java.math.BigInteger;
import java.util.*;;
public class Catalan{
    // Using Recursion
    public static int catalan(int n){ // Time complexity : O(3^n)
        if(n == 0 || n == 1){
            return 1;
        }
        int ans = 0;
        for(int i = 0; i < n; i++){
            ans += catalan(i) * catalan(n-i-1);
        }
        return ans;
    }
    // Using Recursion + Memoization
    public static int catalan(int n,int dp[]){ // Time Complexity : O (n^2)
        if(n == 0 || n == 1){
            return 1;
        }
        if(dp[n] != -1){
            return dp[n];
        }
        int ans = 0;
        for(int i = 0; i < n; i++){
            ans += catalan(i) * catalan(n-i-1);
        }
        return dp[n] = ans;
    }

    // Code Using Tabulation
    public static int catalanTab(int n) { // Time complexity : O (n^2)
        int dp[] = new int[n+1];

        dp[0] = dp[1] = 1;

        for(int i = 2; i <= n; i++) {
            for(int j = 0; j < i ; j++) {
                dp[i] += dp[j] * dp[i-j-1];
            }
        }
        return dp[n];
    }

    // Using BigIntger
    public static BigInteger getCatalan(int n) {
        BigInteger b = new BigInteger("1");

        // Calculating n!
        for(int i = 1; i <= n; i++) {
            b = b.multiply(BigInteger.valueOf(i));
        }

        // Calculating n! * n!
        b = b.multiply(b); 

        BigInteger d = new BigInteger("1");

        // Calculating 2n!
        for(int i = 1; i <= 2*n; i++){
            d = d.multiply(BigInteger.valueOf(i));
        }

        // calculating 2n! / n! * n!
        BigInteger ans = d.divide(b);

        ans = ans.divide(BigInteger.valueOf(n+1));
        return ans;
    }
    public static void main(String[] args) {
        int n = 7;
        System.out.println("The value of catalan (" + n  + ") using recursion = " + catalan(n));
        int dp[] = new int[n+1];
        Arrays.fill(dp, -1);
        System.out.println("The value of catalan (" + n  + ") using memoization = " + catalan(n, dp));
        System.out.println("The value of catalan (" + n  + ") using tabulation = " + catalanTab(n));
        System.out.println("The value of catalan (" + n  + ") using BigIntger = " + getCatalan(100));
    }
}