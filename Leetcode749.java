class Solution {
    public int containVirus(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int res = 0;
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        while(true){
            //for(int[] temp: grid){
                //System.out.println(Arrays.toString(temp));
            //}
           // System.out.println();
            boolean[] visited = new boolean[m * n];
            List<Set<Integer>> virusList = new ArrayList<>();
            List<Set<Integer>> infectNextList = new ArrayList<>();
            List<Integer> numWallList = new ArrayList<>();
            for(int i = 0; i < m; i++){
                for(int j = 0; j < n; j++){
                    int d1Num = conv2dto1d(i, j, n);
                    if(grid[i][j] == 1 && !visited[d1Num]){
                        Queue<Integer> q = new LinkedList<>();
                        Set<Integer> virus = new HashSet<>();
                        Set<Integer> infectNext = new HashSet<>();
                        int numWalls = 0;
                        q.offer(d1Num);
                        visited[d1Num] = true;
                        virus.add(d1Num);
                        while(!q.isEmpty()){
                            int curr = q.poll();
                            int[] xy = conv1dto2d(curr, n);
                            int x = xy[0], y = xy[1];
                            for(int [] dir: dirs){
                                int nx = x + dir[0], ny = y + dir[1];
                                if(nx >= m || nx < 0 || ny < 0 || ny >= n) continue;
                                int next = conv2dto1d(nx, ny, n);
                                if(grid[nx][ny] == 1){
                                    virus.add(next);
                                    if(!visited[next]){
                                        visited[next] = true;
                                        q.add(next);
                                    }
                                    //this check is important as there maybe some -1's
                                } else if(grid[nx][ny] == 0){
                                    numWalls++;
                                    infectNext.add(next);
                                }
                            }
                        }
                        virusList.add(virus);
                        infectNextList.add(infectNext);
                        numWallList.add(numWalls);
                    }
                }
            }
            if(virusList.size() == 0) break;
            int maxInd = 0;
            int max = infectNextList.get(maxInd).size();
            int nn = infectNextList.size();
            for(int i = 1; i < nn; i++){
                if(infectNextList.get(i).size() > max){
                    max = infectNextList.get(i).size();
                    maxInd = i;
                }
            }
            for(int i = 0; i < nn; i++){
                if(i == maxInd){
                    res += numWallList.get(i);
                    for(int vnum: virusList.get(i)){
                        int[] xy = conv1dto2d(vnum, n);
                        grid[xy[0]][xy[1]] = -1;
                    }
                } else{
                    for(int vnum: infectNextList.get(i)){
                        int[] xy = conv1dto2d(vnum, n);
                        grid[xy[0]][xy[1]] = 1;
                    }
                }
            } 
            //for(Set<Integer> set: infectNextList){
                //for(int num: set){
                    //System.out.println(Arrays.toString(conv1dto2d(num, n)));
                //}
                //System.out.println();
            //}
            //System.out.println(numWallList);           
        }


        return(res);
    }
    
    public int conv2dto1d(int i, int j, int n){
        return(i * n + j);
    }
    public int[] conv1dto2d(int num, int n){
        return(new int[]{num / n, num % n});
    }
}
