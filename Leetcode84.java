//first method is by using stack
class Solution {
    public int largestRectangleArea(int[] heights) {
        Deque<Integer> dq = new LinkedList<>();
        int n = heights.length;
        int[] rightMinIndex = new int[n];
        Arrays.fill(rightMinIndex, n);
        int[] leftMinIndex = new int[n];
        Arrays.fill(leftMinIndex, -1);
        //remember to use peeklast/polllast/offerlast
        //by finding the min index on left and right
        for(int i = 0; i < n; i++){
            while(!dq.isEmpty() && heights[dq.peekLast()]  > heights[i]) rightMinIndex[dq.pollLast()] = i;
            dq.offerLast(i);
        }
        dq = new LinkedList<>();
        for(int i = n - 1; i >= 0; i--){
            while(!dq.isEmpty() && heights[dq.peekLast()]  > heights[i]) leftMinIndex[dq.pollLast()] = i;
            dq.offerLast(i);
        }
        //System.out.println(Arrays.toString(rightMinIndex));
        //System.out.println(Arrays.toString(leftMinIndex));
        int res = 0;
        for(int i = 0; i < n; i++){
            res = Math.max(res, (rightMinIndex[i] - leftMinIndex[i] - 1) * heights[i]);
        }
        return(res);
    }
}

//second method is by applying dynamic programming similar to check the longest increasing sequence
class Solution {
    public int largestRectangleArea(int[] heights) {
        Deque<Integer> dq = new LinkedList<>();
        int n = heights.length;
        int[] rightMinIndex = new int[n];
        Arrays.fill(rightMinIndex, n);
        int[] leftMinIndex = new int[n];
        Arrays.fill(leftMinIndex, -1);
        //remember to use peeklast/polllast/offerlast
        //by finding the min index on left and right
        //n^2 implementation, too slow.
        for(int i = 0; i < n - 1; i++){
            for(int j = i + 1; j < n; j++){
                if(heights[j] < heights[i]){
                    rightMinIndex[i] = j;
                    break;
                } 
            }
        }
        for(int i = n - 1; i >= 1; i--){
            for(int j = i - 1; j >= 0; j--){
                if(heights[j] < heights[i]){
                    leftMinIndex[i] = j;
                    break;
                }              
            }
        }
        //System.out.println(Arrays.toString(rightMinIndex));
        //System.out.println(Arrays.toString(leftMinIndex));
        int res = 0;
        for(int i = 0; i < n; i++){
            res = Math.max(res, (rightMinIndex[i] - leftMinIndex[i] - 1) * heights[i]);
        }
        return(res);
    }
}

//segment tree implementation
class Solution {
    private int[] st;
    public int largestRectangleArea(int[] heights) {
        //Deque<Integer> dq = new LinkedList<>();
        int n = heights.length;
        if(n == 0) return(0);
        createSegTree(heights);
        //System.out.println(Arrays.toString(this.st));
        return(largestRectangleAreaUtil(heights, 0, n - 1));
    }
    
    public int largestRectangleAreaUtil(int[] arr, int l, int r){
        if(l > r) return(0);
        if(l == r) return(arr[l]);
        int m = query(arr, l, r);
        int res = arr[m] * (r - l + 1);
        //System.out.printf("l = %d, r = %d, m = %d, res = %d.\n", l, r, m, res);
        res = Math.max(res, largestRectangleAreaUtil(arr, l, m - 1));
        res = Math.max(res, largestRectangleAreaUtil(arr, m + 1, r));
        return(res);
    }
    
    
    public void createSegTree(int[] arr){
        int n = arr.length;
        int depth = (int)Math.ceil(Math.log(n) / Math.log(2));
        int stSize = (int) Math.pow(2, depth + 1) - 1;
        this.st = new int[stSize];
        createSegTreeUtil(arr, 0, n - 1, 0);
    }
    public int mid(int x, int y){
        return(x + (y - x) / 2);
    }
    
    public int merge(int[] arr, int x, int y){
        if(arr[x] < arr[y]) return(x);
        return(y);
    }
    
    public int createSegTreeUtil(int[] arr, int ss, int se, int si){
        if(ss == se){
            this.st[si] = ss;
            return(ss);
        } 
        int m = mid(ss, se);
        int left = createSegTreeUtil(arr, ss, m, 2 * si + 1);
        int right = createSegTreeUtil(arr, m + 1, se, 2 * si + 2);
        this.st[si] = merge(arr, left, right);
        return(this.st[si]);
    }
    public int queryUtil(int[] arr, int ss, int se, int qs, int qe, int si){
        if(qs <= ss && se <= qe) return(this.st[si]);
        int m = mid(ss, se);
        if(qe <= m) return(queryUtil(arr, ss, m, qs, qe, 2 * si + 1));
        if(qs > m) return(queryUtil(arr, m + 1, se, qs, qe, 2 * si + 2));
        return(merge(arr, queryUtil(arr, m + 1, se, qs, qe, 2 * si + 2), queryUtil(arr, ss, m, qs, qe, 2 * si + 1)));
    }
    
    public int query(int[] arr, int qs, int qe){
        int n = arr.length;
        if(qs > qe || qs < 0 || qe >= n) return(0);
        return(queryUtil(arr, 0, n - 1, qs, qe, 0));        
    }
}
