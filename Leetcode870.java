// if there is a larger key exist in a, then use it for b, otherwise use smallest value

class Solution {
    public int[] advantageCount(int[] A, int[] B) {
        int n = A.length;
        TreeMap<Integer, List<Integer>> bmap = new TreeMap<>();
        TreeMap<Integer, Integer> amap = new TreeMap<>();
        int[] res = new int[n];
        for(int i = 0; i < n; i++){
            amap.put(A[i], amap.getOrDefault(A[i], 0) + 1);
            bmap.putIfAbsent(B[i], new LinkedList<>());
            bmap.get(B[i]).add(i);
        }
        List<Integer> bvlist = new ArrayList<>(bmap.keySet());
        for(int bv: bvlist){
            for(int ind: bmap.get(bv)){
                Integer gt = amap.higherKey(bv);
                int val;
                if(gt == null){
                    val = amap.firstKey();
                } else{
                    val = gt;
                }
                res[ind] = val;
                amap.put(val, amap.get(val) - 1);
                if(amap.get(val) == 0) amap.remove(val);
            }
        }
        return(res);
    }
}

class Solution {
    public int[] advantageCount(int[] A, int[] B) {
        int n = A.length;
        TreeMap<Integer, Queue<Integer>> bmap = new TreeMap<>();
        int[] res = new int[n];
        Arrays.fill(res, -1);
        for(int i = 0; i < n; i++){
            bmap.putIfAbsent(B[i], new LinkedList<>());
            bmap.get(B[i]).add(i);
        }
        Queue<Integer> notused = new LinkedList<>();
        Arrays.sort(A);
        Arrays.sort(B);
        int j = 0;
        for(int i = 0; i < n; i++){
            int a = A[i];
            if(B[j] < a){
                res[bmap.get(B[j]).poll()] = a;
                j++;
            } else{
                notused.add(a);
            }
        }
        for(int i = 0; i < n; i++){
            if(res[i] == -1) res[i] = notused.poll();
        }
        return(res);
    }
}
