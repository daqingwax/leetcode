class Solution {
    public int cutOffTree(List<List<Integer>> forest) {
        int m = forest.size();
        if(m == 0) return(-1);
        int n = forest.get(0).size();
        if(n == 0) return(-1);
        int[][] arr = new int[m][n];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                int val = forest.get(i).get(j);
                if(val > 1) pq.offer(new int[]{val, i, j});
                arr[i][j] = val;
            }
        }
        int[] orig = new int[]{0, 0};
        int res = 0;
        //for(int[] points: pq) System.out.println(Arrays.toString(points));
        //System.out.println("split");
        //for(int[] points: arr) System.out.println(Arrays.toString(points));
        while(!pq.isEmpty()){
            int[] destinfo = pq.poll();
            int[] dest = new int[2];
            dest[0] = destinfo[1];
            dest[1] = destinfo[2];
            int val = bfs(orig, dest, arr);
            //System.out.println(val);
            if(val == -1) return(-1);
            res += val;
            orig = dest;
            
        }
        return(res);
            
    }
    
    public int bfs(int[] orig, int[] dest, int[][] forest){
        int m = forest.length;
        int n = forest[0].length;
        boolean[][] visited = new boolean[m][n];
        int step = 0;
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        Queue<int[]> q = new LinkedList<>();
        if(orig[0] == dest[0] && orig[1] == dest[1]) return(0);
        q.offer(orig);
        visited[orig[0]][orig[1]] = true;
        while(!q.isEmpty()){
            int qsize = q.size();
            for(int i = 0; i < qsize; i++){
                int[] curr = q.poll();
                if(curr[0] == dest[0] && curr[1] == dest[1]) return(step);
                for(int j = 0; j < 4; j++){
                    int ni = curr[0] + dirs[j][0];
                    int nj = curr[1] + dirs[j][1];
                    if(ni >= 0 && ni < m && nj >= 0 && nj < n && forest[ni][nj] != 0 && !visited[ni][nj]){
                        visited[ni][nj] = true;
                        q.offer(new int[]{ni, nj});
                    }
                }
            }
            step++;
        }
        return(-1);
        
    }
}
