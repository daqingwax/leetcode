//dfs is very slow
class Solution {
    public boolean canCross(int[] stones) {
        int n = stones.length;
        int[][] memo = new int[n][n];
        Map<Integer, Integer> posMap = new HashMap<>();
        for(int i = 0; i < n; i++) Arrays.fill(memo[i], -1);
        for(int i = 0; i < n; i++) posMap.put(stones[i], i);
        return(dfs(stones, 0, 1, memo, posMap));
    }
    
    public boolean dfs(int[] t, int start, int k, int[][] memo, Map<Integer, Integer> posMap){
        int n = t.length;
        if(start == n - 1) return(true);
        if(start > n - 1) return(false);
        if(memo[start][k] >= 0) return(memo[start][k] == 1);
        int curr = t[start];
        long lnext = (long)curr + k;
        if(lnext > Integer.MAX_VALUE) return(false);
        int next = (int)lnext;
        boolean reskn1 = false, resk = false, resk1 = false;
        if(posMap.containsKey(next) && posMap.get(next) > start){
            if(k > 1) reskn1 = dfs(t, posMap.get(next), k - 1, memo, posMap);
            resk = dfs(t, posMap.get(next), k, memo, posMap);
            resk1 = dfs(t, posMap.get(next), k + 1, memo, posMap);
        }
        boolean res = reskn1 || resk || resk1;
        if(res) memo[start][k] = 1;
        else memo[start][k] = 0;
        return(res);
    }
}
