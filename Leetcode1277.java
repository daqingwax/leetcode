//presum
class Solution {
    public int countSquares(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] pre = new int[m + 1][n + 1];
        int[][] saved = new int[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                saved[i][j] = matrix[i][j];
                pre[i + 1][j + 1] = pre[i + 1][j] + pre[i][j + 1] + matrix[i][j] - pre[i][j];
            }
        }
        int res = 0;
        for(int i = 0; i <= m; i++){
            //System.out.println(Arrays.toString(pre[i]));
        }
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(matrix[i][j] == 0) continue;
                int max = saved[i][j];
                int k = saved[i][j] + 1;
                while(i + k <= m && j + k <= n){
                    int sum = pre[i + k][j + k] - pre[i + k][j] - pre[i][j + k] + pre[i][j];
                    //System.out.printf("k = %d, i = %d, j = %d, sum = %d.\n", k, i, j, sum);
                    if(sum != k * k){
                        break;
                    }
                    k++;
                }
                max = k - 1;
                for(k = max; k > 1; k--) saved[i + k - 1][j + k - 1] = max - k + 1;
                res += max;
            }
        }
        return(res);
        
        
    }
}

//dp
