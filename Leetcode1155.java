//dp
class Solution {
    public int numRollsToTarget(int d, int f, int target) {
        int MOD = (int)(1e9 + 7);
        
        long[][] dp = new long[target + 1][d + 1];
        for(int j = 1; j <= f; j++){
            if(j <= target) dp[j][1] = 1;
        }
        for(int i = 1; i <= target; i++){
            for(int k = 2; k <= d; k++){
                long val = 0;
                for(int j = 1; j <= f; j++){
                    if(i - j >= 0){
                        val += dp[i - j][k - 1];
                        val %= MOD;
                    } 
                }
                dp[i][k] = val;
            }
        }
        return((int)dp[target][d]);
    }
}
