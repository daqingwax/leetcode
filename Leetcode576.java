//bottom up dp, very slow...
class Solution {
    public int findPaths(int m, int n, int N, int i, int j) {
        long [][][] dp = new long[m + 2][n + 2][N + 1];
        i = i + 1;
        j = j + 1;
        long res = 0;
        int MOD = (int)(1e9 + 7);
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        if(N == 0) return(0);
        for(int[] dir : dirs){
            int ii = i + dir[0];
            int jj = j + dir[1];
            dp[ii][jj][1] = 1;
            if(ii == 0 || jj == 0 || ii == m + 1 || jj == n + 1) res += dp[ii][jj][1];
        }
        for(int kk = 2; kk <= N; kk++){
            for(int ii = 0; ii < m + 2; ii++){
                for(int jj = 0; jj < n + 2; jj++){
                    for(int[] dir : dirs){
                        int iii = ii + dir[0];
                        int jjj = jj + dir[1];
                        if(iii <= m && iii >= 1 && jjj <= n && jjj >= 1){
                            dp[ii][jj][kk] += dp[iii][jjj][kk - 1] ;
                            dp[ii][jj][kk] %= (long)MOD;
                        }
                    }
                    if(ii == 0 || jj == 0 || ii == m + 1 || jj == n + 1){
                        res += dp[ii][jj][kk];
                        res %= (long)MOD;
                    }
                }
            }
            
        }
        return((int)res);
    }
}
