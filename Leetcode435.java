//set currentend as first interval end, calculate how many are overlapping to each other
class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        int n = intervals.length;
        if(n <= 1) return(0);
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        int currentEnd = intervals[0][1];
        int over = 0;
        for(int i = 1; i < n; i++){
            if(intervals[i][0] >= currentEnd){
                currentEnd = intervals[i][1];
            } else{
                over++;
                currentEnd = Math.min(currentEnd, intervals[i][1]);
            }
        }
        return(over);
    }
}
