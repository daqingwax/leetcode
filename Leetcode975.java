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
