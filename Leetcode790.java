//dp...
class Solution {
    public int numTilings(int N) {
        long[] dp = new long[N + 1];
        dp[1] = 1;
        if(N == 1) return(1);
        if(N == 2) return(2);
        if(N == 3) return(5);
        dp[2] = 2;
        dp[3] = 5;
        int MOD = (int)(1e9 + 7);
        for(int i = 4; i <= N; i++){
            long val = 0;
            
            //val %= MOD;
            dp[i] = dp[i - 3] + 2 * dp[i - 1];
            dp[i] %= MOD;
        }
        return((int) (dp[N]));
    }
}
