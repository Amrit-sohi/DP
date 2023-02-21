public class CountBST {
    // Find number of all possible BST's having n nodes
    public static int count_bst(int nodes) {
        int dp[] = new int[nodes+1];

        // for nodes = 0, there is only one subtree exist that is null tree 
        dp[0] = 1;
        // for nodes = 1, there is only one subtree exist having only root but no child 
        dp[1] = 1;

        for(int i = 2; i <= nodes; i++) {
            for(int j = 0 ; j < i; j++) {

                int left = dp[j];
                int right = dp[i-j-1];
                dp[i] += left * right;
            }
        }
        return dp[nodes];
    }
    public static void main(String[] args) {
        int nodes = 5;
        System.out.println("Number of BST's that can be possible for " + nodes + " nodes = " + count_bst(nodes));
    }
    
}
