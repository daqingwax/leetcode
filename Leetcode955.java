//use an array  to save the comparison result
//if current row needs to be removed, then backtrack to the previous state
// if it is == , it needs to be determined in the next row

class Solution {
    public int minDeletionSize(String[] A) {
        int n = A.length;
        int m = A[0].length();
        for(int i = 0; i < n; i++){
            m = Math.max(m, A[i].length());
        }
        //flag shows if array is sorted
        boolean[] isSorted = new boolean[n - 1];
        int res = 0;
        for(int i = 0; i < m; i++){
            boolean[] tempSorted = Arrays.copyOfRange(isSorted, 0, n - 1);
            boolean sorted = true;
            for(int j = 0; j < n - 1; j++){
                String a = A[j], b = A[j + 1];
                if(!isSorted[j]){
                    if(a.charAt(i) > b.charAt(i)){
                        res++;
                        isSorted = tempSorted;
                        sorted = false;
                        break;
                    } else if(a.charAt(i) < b.charAt(i)){
                        isSorted[j] = true;
                    } else{
                        sorted = false;
                    }
                }
                
            }
            if(sorted) return(res);
        }
        return(res);
    }
    

}
