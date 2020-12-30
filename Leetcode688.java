//Extending N to N + 4 to allow full coverage
//each step is based on the previous dp result then * 1.0 / 8.0
//bottom up dp, can be optimized to dfs (top down)
class Solution {
    public double knightProbability(int N, int K, int r, int c) {
        double[][][] dp = new double[N + 4][N + 4][K + 1];
        double in = 0;
        double out = 0;
        r = r + 2;
        c = c + 2;
        if(K == 0) return(1.0);
        double res = 0.0;
        int[][] dirs = new int[][]{{-1, 2}, {1, 2}, {-1, -2}, {1, -2}, {2, -1}, {2, 1}, {-2, -1}, {-2, 1}};
        for(int[] dir: dirs){
            int ii = r + dir[0];
            int jj = c + dir[1];
            if(ii >= 2 && ii <= N + 1 && jj >= 2 && jj <= N + 1){
                dp[ii][jj][1] = 1.0/8.0;
                if(K == 1) res += dp[ii][jj][1];
            }
        }
        if(K == 1) return(res);

        for(int kk = 2; kk <= K; kk++){
            in = 0;
            out = 0;
            boolean allzero = true;
            for(int i = 0; i < N + 4; i++){
                for(int j = 0; j < N + 4; j++){
                    double val = 0;
                    for(int[] dir: dirs){
                        int ii = i + dir[0];
                        int jj = j + dir[1];
                        if(ii >= 2 && ii <= N + 1 && jj >= 2 && jj <= N + 1){
                            val += dp[ii][jj][kk - 1];
                        }
                    }
                    if(i >= 2 && i <= N + 1 && j >= 2 && j <= N + 1){
                        dp[i][j][kk] = val / 8.0;
                        if(dp[i][j][kk] != 0) allzero = false;
                        if(kk == K) res += dp[i][j][kk];
                     }
                }
            }
            if(allzero) System.out.println("All zeros..");
        }

        return(res);
    }
}
