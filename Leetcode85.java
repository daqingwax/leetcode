//by using two stacks to save the bottom/right # 1s, 
//Runtime: 8 ms, faster than 45.22% of Java online submissions for Maximal Rectangle.
//Memory Usage: 42 MB, less than 78.01% of Java online submissions for Maximal Rectangle.
//Next challenges:
class Solution {
    public int maximalRectangle(char[][] matrix) {
        if(matrix == null) return(0);
        int m = matrix.length;
        if(m == 0) return(0);
        int n = matrix[0].length;
        if(n == 0) return(0);
        
        int [][] pre = new int[m + 1][n + 1];
        int [][] right = new int[m][n];
        int [][] bottom = new int[m][n];
        for(int i = 0; i < m; i++){
            Deque<Integer> dq = new LinkedList<>();
            for(int j = 0; j < n; j++){
                if(matrix[i][j] == '1') dq.offerLast(j);
                else{
                    while(!dq.isEmpty()) right[i][dq.pollLast()] = j - 1;
                }
            }
            while(!dq.isEmpty()) right[i][dq.pollLast()] = n - 1;
        }
        for(int j = 0; j < n; j++){
            Deque<Integer> dq = new LinkedList<>();
            for(int i = 0; i < m; i++){
                if(matrix[i][j] == '1') dq.offerLast(i);
                else{
                    while(!dq.isEmpty()) bottom[dq.pollLast()][j] = i - 1;
                }
            }
            while(!dq.isEmpty()) bottom[dq.pollLast()][j] = m - 1;
        }
        
        int res = 0;
        for(int i = 0; i < m; i++){
             for(int j = 0; j < n; j++){
                 if(matrix[i][j] == '0') continue;
                 int w = right[i][j];
                 for(int k = i; k <= bottom[i][j]; k++){
                     w = Math.min(w, right[k][j]);
                     res = Math.max((k - i + 1) * (w - j + 1), res);
                 }
            }
        }
        return(res);
           
    }
}
