class Solution {
    public boolean splitArraySameAverage(int[] A) {
        Arrays.sort(A);
        
        int n = A.length;
        if(n == 1) return(false);
        int sum = 0;
        for(int i = 0; i < n; i++){
            sum += A[i];
        }
        int [][][] memo = new int[sum / 2 + 1][n / 2 + 1][n];
        for(int i = 0; i <= sum /2; i++){
            for(int j = 0; j <= n / 2; j++){
                Arrays.fill(memo[i][j], -1);
            }
        }
        for(int len = 1; len <= n / 2; len++){
            int rem = sum * len % n;
            if(rem == 0){
                int target = sum * len / n;
                if(dfs(A, target, len, 0, memo)) return(true);
            }
        }
        return(false);
    }
    
    public boolean dfs(int[] A, int target, int len, int start, int [][][] memo){
        int n = A.length;
        if(len == 0) return(target == 0);
        if(start >= n) return(false);
        if(len + start > n) return(false);
        if(target < 0) return(false);
        if(A[start] > target) return(false);
        if(memo[target][len][start] >= 0) return(memo[target][len][start] == 1);
        boolean res;
        res = dfs(A, target, len, start + 1, memo) || dfs(A, target - A[start], len - 1, start + 1, memo);
        memo[target][len][start] = res ? 1 : 0;
        return(res);
    }
}
