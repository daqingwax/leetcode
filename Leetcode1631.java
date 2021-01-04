//dfs + binary search to find the min

class Solution {
    public int minimumEffortPath(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                int h = heights[i][j];
                min = Math.min(h, min);
                max = Math.max(h, max);
            }
        }
        int l = 0, r = max - min;
        while(l < r){
            int mid = l + (r - l) / 2;
            boolean[][] visited = new boolean[m][n];
            visited[0][0] = true;
            if(canReach(0, 0, heights, visited, mid)) r = mid;
            else l = mid + 1;
        }
        return(l);
    }
    
    public boolean canReach(int i, int j, int[][] heights, boolean[][] visited, int thre){
        int m = heights.length;
        int n = heights[0].length;
        if(i < 0 || i >= m || j < 0 || j >= n) return(false);
        if(i == m - 1 && j == n - 1) return(true);
        int [][] dirs = new int[][] {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        boolean canGo = false;
        for(int[] d: dirs){
            int ni = i + d[0];
            int nj = j + d[1];
            if(ni < 0 || ni >= m || nj < 0 || nj >= n) continue;
            if(!visited[ni][nj]){
                if(Math.abs(heights[ni][nj] - heights[i][j]) <= thre){
                    visited[ni][nj] = true;
                    canGo = canGo | canReach(ni, nj, heights, visited, thre);
                    if(canGo) return(true);
                }
            }
        }
        return(canGo);
    }
}
