//top down dfs + memo
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int n1 = s1.length();
        int n2 = s2.length();
        int n3 = s3.length();
        if(n3 != n1 + n2) return(false);
        int[][][] memo = new int[n1 + 1][n2 + 1][n3 + 1];
        for(int i = 0; i <= n1; i++){
            for(int j = 0; j <= n2; j++){
                Arrays.fill(memo[i][j], -1);
            }
        }
        return(dfs(s1, s2, s3, 0, 0, 0, memo));
    }
    
    public boolean dfs(String s1, String s2, String s3, int l, int m, int n, int[][][] memo){
        int n1 = s1.length();
        int n2 = s2.length();
        int n3 = s3.length();
        
        if(l == n1 && m == n2 && n == n3) return(true);
        if(memo[l][m][n] >= 0){
            return(memo[l][m][n] == 1);
        }
        boolean res, cmp1 = false, cmp2 = false;
        if(l < n1 && n < n3 && s1.charAt(l) == s3.charAt(n)) cmp1 = dfs(s1, s2, s3, l + 1, m, n + 1, memo);
        if(m < n2 && n < n3 && s2.charAt(m) == s3.charAt(n)) cmp2 = dfs(s1, s2, s3, l, m + 1, n + 1, memo);
        res = cmp1 || cmp2;
        if(res) memo[l][m][n] = 1;
        else memo[l][m][n] = 0;
        return(res);
    }
}
