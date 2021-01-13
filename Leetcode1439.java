//use priority queue

class Solution {
    public int kthSmallest(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;
        int nn = Math.min(n, k);
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int i = 0; i < nn; i++) pq.offer(mat[0][i]);
        for(int i = 1; i < m; i++){
            PriorityQueue<Integer> nextPq = new PriorityQueue<>(Collections.reverseOrder());
            for(int j = 0; j < nn; j++){
                for(int num: pq){
                    int currNum = num + mat[i][j];
                    if(nextPq.size() < k) nextPq.offer(currNum);
                    else if(currNum < nextPq.peek()){
                        nextPq.poll();
                        nextPq.offer(currNum);
                    }
                }
            }
            pq = nextPq;
        }
        return(pq.peek());
    }
}

//use binary search
