//use set, it is very slow... 
/*Runtime: 83 ms, faster than 5.08% of Java online submissions for Minimum Domino Rotations For Equal Row.
Memory Usage: 47.7 MB, less than 10.84% of Java online submissions for Minimum Domino Rotations For Equal Row.
*/
class Solution {
    public int minDominoRotations(int[] A, int[] B) {
        int n = A.length;
        int res = Integer.MAX_VALUE;
        Set<Integer>[] setA = new Set[7];
        for(int i = 0; i < 7; i++) setA[i] = new HashSet<>();
        for(int i = 0; i < n; i++) setA[A[i]].add(i);
        Set<Integer>[] setB = new Set[7];
        for(int i = 0; i < 7; i++) setB[i] = new HashSet<>();
        for(int i = 0; i < n; i++) setB[B[i]].add(i);
        for(int i = 1; i < 7; i++){
            int aSize = setA[i].size();
            int bSize = setB[i].size();
            Set<Integer> temp = new HashSet<>(setA[i]);
            temp.addAll(setB[i]);
            if(temp.size() == n) res = Math.min(n - Math.max(bSize, aSize), res);
        }
        return(res == Integer.MAX_VALUE ? -1 : res);
    }
}

//just need to compare A[0]/B[0] within A/B
class Solution {
    public int minDominoRotations(int[] A, int[] B) {
        int n = A.length;
        int res = Integer.MAX_VALUE;
        res = Math.min(res, cnt(A[0], A, B));
        res = Math.min(res, cnt(B[0], A, B));
        res = Math.min(res, cnt(B[0], B, A));
        res = Math.min(res, cnt(A[0], B, A));
        return(res == Integer.MAX_VALUE ? -1 : res);
    }
    
    public int cnt(int val, int[] A, int[] B){
        int n = A.length;
        int res = 0;
        for(int i = 0; i < n; i++){
            if(A[i] == val) continue;
            if(B[i] == val) res++;
            else return(Integer.MAX_VALUE);
        }
        return(res);
    }
}
