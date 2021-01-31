//bfs

class Solution {
    List<int[]> ones;
    boolean test;
    public int minDays(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        ones = new LinkedList<>();
        test = true;
        int cnt = cntIslands(grid);
        if(cnt == 0 || cnt >= 2) return(0);
        test = false;
        for(int[] one: ones){
            int x = one[0];
            int y = one[1];
            grid[x][y] = 0;
            int testCnt = cntIslands(grid);
            if(testCnt != 1) return(1);
            grid[x][y] = 1;
        }
        return(2);
    }
                     
    public int cntIslands(int [][] grid){
        int m = grid.length;
        int n = grid[0].length;
        int cnt = 0;
        boolean[][] visited = new boolean[m][n];
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 1){
                    if(visited[i][j]) continue;
                    visited[i][j] = true;
                    cnt++;
                    Queue<int[]> q = new LinkedList<>();
                    q.offer(new int[]{i, j});
                    while(!q.isEmpty()){
                        int [] curr = q.poll();
                        int x = curr[0];
                        int y = curr[1];
                        if(test && (cnt == 1)) ones.add(curr);
                        for(int[] d: dirs){
                            int nx = x + d[0];
                            int ny = y + d[1];
                            if(nx < 0 || nx >= m || ny < 0 || ny >= n ||grid[nx][ny] == 0) continue;
                            if(!visited[nx][ny]){
                                visited[nx][ny] = true;
                                q.offer(new int[]{nx, ny});
                            }
                        }
                    }
                }
            }
        }
        return(cnt);
    }
}

//dfs + tarjan's algorithm

class Solution {
    boolean hasArticulation;
    int[] low;
    int[] in;
    int[][] grid;
    int timer;
    int tot;
    boolean[] visited;
    public int minDays(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        hasArticulation = false;
        tot = m * n;
        int cnt = 0;
        timer = 0;
        this.grid = grid;
        visited = new boolean[tot];
        low = new int[tot];
        in = new int[tot];
        Arrays.fill(in, Integer.MAX_VALUE);
        Arrays.fill(low, Integer.MAX_VALUE);
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 1){
                    int [] curr =  new int[]{i, j};
                    int ind = conv2d1d(curr, n);
                    if(visited[ind]) continue;
                    cnt++;
                    if(cnt >= 2) return(0);
                    //visited[ind] = true;
                    dfs(ind, -1, n);
                }
            }
        }
        if(cnt == 0) return(0);
        return(hasArticulation? 1 : 2);
    }
    public void dfs(int val, int par, int n){
        in[val] = timer;
        low[val] = timer;
        visited[val] = true;
        timer++;
        int child = 0;
        for(int nb: neibrs(val, n)){
            if(nb < 0 || nb >= tot) continue;
            if(nb == par) continue;
            int[] d2 = conv1d2d(nb, n);
            if(grid[d2[0]][d2[1]] == 0) continue;
            if(visited[nb]){
                low[val] = Math.min(low[val], in[nb]);
            } else{
                dfs(nb, val, n);
                low[val] = Math.min(low[nb], low[val]);
                if(low[nb] >= in[val] && par != -1) hasArticulation = true;
                child++;
            }
        }
        if(par == -1 && child != 1) hasArticulation = true;
    }
    
    public int[] neibrs(int val, int n){
        if(n == 1){
            return(new int[]{val - 1, val + 1});
        }
        if(val % n == 0){
            return(new int[]{val + 1, val - n, val + n});
        } else if((val + 1) % n == 0){
            return(new int[]{val - 1, val - n, val + n});
        } else{
            return(new int[]{val - 1, val + 1, val - n, val + n});
        }
    }
    
    public int[] conv1d2d(int val, int n){
        return(new int[]{val / n, val % n});
    }
    public int conv2d1d(int [] arr, int n){
        return(arr[0] * n + arr[1]);
    }
    
}
