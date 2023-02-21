public class MountainRanges {
    /*
    Count All possible mountain ranges
    At any moment number of downstrokes should not be greater than number of upstrokes.
     */

    public static int countMountainRange(int strokePair){
        int dp[] = new int[strokePair+1];

        dp[0] = dp[1] = 1;

        for(int  i = 2; i <= strokePair; i++){
            // i pairs -> mountain ranges => Ci  
            for (int j = 0; j < i; j++) {
                int insideWays = dp[j];
                int outsideWays = dp[i-j-1];
                dp[i] += (insideWays * outsideWays);
            }
        }
        return dp[strokePair];
    }
    public static void main(String[] args) {
        int strokePair = 5;
        System.out.println("Mountain ranges for " + strokePair + " strokePair = " + countMountainRange(strokePair));
    }
    
}
