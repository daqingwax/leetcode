//Insert interval with treemap

class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int n = intervals.length;
        TreeMap<Integer, Integer> intvMap = new TreeMap<>();
        for(int i = 0; i < n; i++){
            intvMap.put(intervals[i][0], intervals[i][1]);
        }
        int left = newInterval[0];
        int right = newInterval[1];
        Integer a = intvMap.floorKey(left);
        Integer firstKey, lastKey;
        if(a != null && intvMap.get(a) >= left){
            left = a;
            right = Math.max(right, intvMap.get(a));
            intvMap.remove(a);
        }
        Integer b = intvMap.ceilingKey(left);
        while(b != null && b <= right){
            right = Math.max(right, intvMap.get(b));
            intvMap.remove(b);
            b = intvMap.ceilingKey(left);
        }
        intvMap.put(left, right);
        int[][] res = new int[intvMap.size()][2];
        int i = 0;
        for(int key: intvMap.keySet()){
            res[i] = new int[]{key, intvMap.get(key)};
            i++;
        }
        return(res);

    }
}


//couple of cavets...
//1. linear time is faster
//2. the special case of no intersection needs to special process
// an added flag so that you don tneed to add multiple times.
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int n = intervals.length;
        if(n == 0) return(new int[][]{newInterval});
        int left = newInterval[0];
        int right = newInterval[1];
        int indMin = Integer.MAX_VALUE;
        int indMax = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++){
            //intersected intervals
            if(intervals[i][0] <= right && intervals[i][1] >= left){
                indMin = Math.min(indMin, i);
                indMax = Math.max(indMax, i);
            }
        }
        int[][] res;
        if(indMin != Integer.MAX_VALUE){
            for(int i = indMin; i <= indMax; i++){
                left = Math.min(left, intervals[i][0]);
                right = Math.max(right, intervals[i][1]);
            }
            int newSz = n - (indMax - indMin + 1) + 1;
            res = new int[newSz][2];
            int j = 0;
            for(int i = 0; i < n; i++){
                if(i > indMin && i <= indMax) continue;
                if(i == indMin) res[j] = new int[]{left, right};
                else res[j] = intervals[i];
                j++;
            }
        } else{
            int newSz = n + 1;
            res = new int[newSz][2];
            int j = 0;
            boolean added = false;
            for(int i = 0; i < n; i++){
                if(!added && left < intervals[i][0]){
                    res[j++] = new int[]{left, right};
                    added = true;
                }
                res[j] = intervals[i];
                j++;
            }
            if(!added) res[j] = new int[]{left, right};
        }

        return(res);

    }
}
