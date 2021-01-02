//find the minimum mask of the current mask and get the diff... num of ch can be at most one odd
class Solution {
    public int longestAwesome(String s) {
        
        int n = s.length();
        //10bits
        int[] dp = new int[1024];
        Arrays.fill(dp, n);
        
        dp[0] = -1;
        int mask = 0;
        int res = 0;
        for(int i = 0; i < n; i++){
            mask ^= (1 << (s.charAt(i) - '0'));
            res = Math.max(res, i - dp[mask]);
            for(int j = 0; j <= 9; j++){
                res = Math.max(res, i - dp[mask ^ (1 << j)]);
            }
            dp[mask] = Math.min(dp[mask], i);
        }
        return(res);
    }
}
