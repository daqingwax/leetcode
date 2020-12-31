// 2 holes: cannot use the normal compare but integer compare method; initialize currentEnd to be the max value and set res to be 1 initially.

class Solution {
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, (a, b) -> a[0] == b[0] ? Integer.compare(a[1], b[1]) : Integer.compare(a[0], b[0]));
        int n = points.length;
        if(n == 0) return(0);
        int currentEnd = Integer.MAX_VALUE;
        int res = 1;
        for(int i = 0; i < n; i++){
            if(points[i][0] > currentEnd){
                res++;
                currentEnd = points[i][1];
            } else{
                currentEnd = Math.min(currentEnd, points[i][1]);
            }
        }
        return(res);
    }
}
