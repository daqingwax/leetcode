class Solution {
    public int minHeightShelves(int[][] books, int shelf_width) {
        int n = books.length;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = books[0][1];
        for(int i = 2; i <= n; i++){
            int width = books[i - 1][0];
            int currHeight = books[i - 1][1];
            dp[i] = dp[i - 1] + currHeight;
            int j = i - 2;
            while(j >= 0){
                width += books[j][0];
                if(width <= shelf_width){
                    currHeight = Math.max(currHeight, books[j][1]);
                    dp[i] = Math.min(dp[j] + currHeight, dp[i]);
                }
                j = j - 1;
            }
        }
        //System.out.println(Arrays.toString(dp));
        return(dp[n]);
    }
}
