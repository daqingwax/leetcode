//treemap

class Solution {
    public int oddEvenJumps(int[] A) {
        int n = A.length;
        //0 is low, 1 is high
        boolean[][] go = new boolean[n][2];
        //starting from the end
        go[n - 1][0] = true;
        go[n - 1][1] = true;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(A[n - 1], n - 1);
        int res = 1;
        for(int i = n - 2; i >= 0; i--){
            int num = A[i];
            Integer higherKey = map.ceilingKey(num);
            Integer lowerKey = map.floorKey(num);
            if(higherKey != null) go[i][1] = go[map.get(higherKey)][0];
            if(lowerKey != null) go[i][0] = go[map.get(lowerKey)][1];
            if(go[i][1]) res++;
            map.put(num, i);
        }
        return(res);
    }
}

//stack method is much faster
class Solution {
    public int oddEvenJumps(int[] A) {
        int n = A.length;
        //0 is low, 1 is high
        boolean[][] go = new boolean[n][2];
        //starting from the end
        go[n - 1][0] = true;
        go[n - 1][1] = true;
        int[] nextHigher = new int[n];
        int[] nextLower = new int[n];
        Arrays.fill(nextHigher, -1);
        Arrays.fill(nextLower, -1);
        int[][] ind = new int[n][2];
        Deque<int[]> dq = new LinkedList<>();
        for(int i = 0; i < n; i++){
            ind[i][0] = A[i];
            ind[i][1] = i;
        }
        //small to large
        Arrays.sort(ind, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        for(int i = 0; i < n; i++){
            while(!dq.isEmpty() && ind[i][1] > dq.peekLast()[1]){
                nextHigher[dq.pollLast()[1]] = ind[i][1];
            }
            dq.offerLast(ind[i]);
        }
        //large to small
        Arrays.sort(ind, (a, b) -> a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]);
        for(int i = 0; i < n; i++){
            while(!dq.isEmpty() && ind[i][1] > dq.peekLast()[1]){
                nextLower[dq.pollLast()[1]] = ind[i][1];
            }
            dq.offerLast(ind[i]);
        }
        int res = 1;
        for(int i = n - 2; i >= 0; i--){
            int highInd = nextHigher[i];
            int lowInd = nextLower[i];
            if(highInd != -1) go[i][1] = go[highInd][0];
            if(lowInd != -1) go[i][0] = go[lowInd][1];
            if(go[i][1]) res++;
        }

        return(res);
    }
}

