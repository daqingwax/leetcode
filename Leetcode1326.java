class Solution {
    public int minTaps(int n, int[] ranges) {
        int[] steps = new int[n + 1];
        Arrays.fill(steps, 0);
        for(int i = 0; i <= n; i++){
            int left = Math.max(0, i - ranges[i]);
            int right = Math.min(n, i + ranges[i]);
            steps[left] = Math.max(steps[left], right);
        }
        int currMax = 0, currEnd = 0;
        int cnt = 0;
        for(int i = 0; i < n; i++){
            if(i > currMax) return -1;
            currMax = Math.max(currMax, steps[i]);
            if(i == currEnd){
                cnt++;
                currEnd = currMax;
            }
        }
        return(currMax >= n ? cnt : -1);
            
    }
}
