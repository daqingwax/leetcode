class Solution {
    public int numberOfArithmeticSlices(int[] A) {
        int n = A.length;
        Map<Integer, Integer>[] map = new Map[n];
        for(int i = 0; i < n; i++) map[i] = new HashMap<>();
        int res = 0;
        for(int i = 1; i < n; i++){
            for(int j = 0; j < i; j++){
                long diff = (long) A[i] - (long) A[j];
                if(diff < Integer.MIN_VALUE || diff > Integer.MAX_VALUE) continue;
                int dif = (int)diff;
                map[i].put(dif, map[i].getOrDefault(dif, 0) + 1);
                int existing = map[j].getOrDefault(dif, 0);
                //int newAdd = map[i].getOrDefault(dif, 0);
                //map[i].put(dif, existing + newAdd + 1);
                res += existing;
                map[i].put(dif, map[i].get(dif) + existing);
            }
        }
        //for(int i = 0; i < n; i++) System.out.println(map[i]);
        return(res);
    }
}

