//mergesort with prefix sum
class Solution {
    int lower;
    int upper;
    public int countRangeSum(int[] nums, int lower, int upper) {
        int n = nums.length;
        long[] pre = new long[n + 1];
        for(int i = 0; i < n; i++) pre[i + 1] = pre[i] + (long)nums[i];
        this.lower = lower;
        this.upper = upper;
        return(mergeSort(pre, 0, n));
    }
    
    public int mergeSort(long[] nums, int start, int end){
        if(start >= end) return(0);
        int mid = start + (end - start) / 2;
        int res = mergeSort(nums, start, mid);
        res += mergeSort(nums, mid + 1, end);
        res += merge(nums, start, mid, end);
        return(res);
    }
    
    public int merge(long[] nums, int start, int mid, int end){
        int i = start;
        int j = mid + 1;
        int k = mid + 1;
        int res = 0;
        for(i = start; i <= mid; i++){
            while(j <= end && nums[j] - nums[i] < lower) j++;
            while(k <= end && nums[k] - nums[i] <= upper) k++;
            res += k - j;
        }
        i = start;
        j = mid + 1;
        k = 0;
        int n = end - start + 1;
        long [] temp = new long[n];
        while(i <= mid || j <= end){
            if(i <= mid && j <= end){
                if(nums[i] < nums[j]) temp[k++] = nums[i++];
                else temp[k++] = nums[j++];
            } else if(i <= mid) temp[k++] = nums[i++];
            else temp[k++] = nums[j++];
        }
        for(k = k - 1; k >= 0; k--) nums[start + k] = temp[k];
        return(res);
        
        
    }
}
