class Solution {
    public int minSwap(int[] A, int[] B) {
        int n = A.length;
        int[] keep = new int[n];
        int[] swap = new int[n];
        //no change
        keep[0] = 0;
        //swap
        swap[0] = 1;
        for(int i = 1; i < n; i++){
            boolean noSwapNeeded = A[i] > A[i - 1] && B[i] > B[i - 1];
            boolean swapNeeded = A[i] > B[i - 1] && B[i] > A[i - 1];
            if(noSwapNeeded && swapNeeded){
                //either swap or not swap
                keep[i] = Math.min(keep[i - 1], swap[i - 1]);
                swap[i] = Math.min(keep[i - 1] + 1, swap[i - 1] + 1);
            } else if(noSwapNeeded){
                //better not swap
                keep[i] = keep[i - 1];
                swap[i] = swap[i - 1] + 1;
            } else{
                //need to be swapped
                keep[i] = swap[i - 1];
                swap[i] = keep[i - 1] + 1;
            }
        }
        return(Math.min(keep[n - 1], swap[n - 1]));
    }
}


