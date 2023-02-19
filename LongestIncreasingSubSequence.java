import java.util.Arrays;
import java.util.HashSet;

public class LongestIncreasingSubSequence {
    public static int lcs(int arr1[],int arr2[]){
        int n = arr1.length;
        int m = arr2.length;
        int dp[][] = new int[n+1][m+1];

        // Initialize with base case
        for(int i = 0 ; i < dp.length; i++){
            dp[i][0] = 0;
        }
        for(int i = 0 ; i < dp[0].length; i++){
            dp[0][i] = 0;
        }

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                if(arr1[i-1] == arr2[j-1]){
                    dp[i][j] = 1 + dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[n][m];
    }

    public static int lis(int arr1[]){ // Time Complexity : O(n^2)
        // Step 1 : if the array contains duplicate elements then we can take unique elements by using hashset
        HashSet<Integer> set = new HashSet<>();

        for(int ele: arr1){
            set.add(ele);
        } 

        // Step 2 : Store elements into another array from hashset
        int arr2[] = new int[set.size()];
        int i = 0;
        for(int ele : set){
            arr2[i] = ele;
            i++;
        } 

        // Step 3 : Sort the auxillary array so as to apply LCS algorithm
        Arrays.sort(arr2);

        // Step 4 :  Find Longest common sorted unique Subsequence
        return lcs(arr1,arr2);
    }

    public static void main(String[] args) {
        int arr[] = {50,3,10,7,40,80};
        System.out.println("Length of Longest Increasing subsequence = " + lis(arr));
    }   
}
