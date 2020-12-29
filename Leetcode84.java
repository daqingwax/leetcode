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
