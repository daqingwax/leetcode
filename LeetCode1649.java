//need to keep the original index to save the smaller/larger count

class Solution {
    int[] lessCnt;
    int[] greatCnt;
    public int createSortedArray(int[] instructions) {
        int n = instructions.length;
        int[][] nums = new int[n][2];
        for(int i = 0; i < n; i++){
            nums[i][0] = instructions[i];
            nums[i][1] = i;
        }
        lessCnt = new int[n];
        greatCnt = new int[n];
        mergeSort(nums, 0, n - 1);
        int MOD = (int)(1e9 + 7);
        int res = 0;
        for(int i = 0; i < n; i++){
            res += Math.min(lessCnt[i], greatCnt[i]);
            if(res >= MOD) res -= MOD;
        }
        return(res);
    }
    
    private void mergeSort(int[][] nums, int start, int end){
        if(start >= end) return;
        int MOD = (int)(1e9 + 7);
        int mid = start + (end - start) / 2;
        mergeSort(nums, start, mid);
        mergeSort(nums, mid + 1, end);
        merge(nums, start, mid, end);
    }
    
    private void merge(int[][] nums, int start, int mid, int end){
        
        int i = start, j = mid + 1;
        int k = 0;
        //int[] lessCnt = new int[end - mid];
        //int[] greatCnt = new int[end - mid];
        for(; j <= end; j++){
            while(i <= mid && nums[i][0] <= nums[j][0]) i++;
            greatCnt[nums[j][1]] += mid - i + 1; 
        }
        k = 0;
        i = start;
        for(j = mid + 1; j <= end; j++){
            while(i <= mid && nums[i][0] < nums[j][0]) i++;
            lessCnt[nums[j][1]] += i - start; 
        }
        //for(k = k - 1; k >= 0; k--) cnt += Math.min(lessCnt[k], greatCnt[k]);
        
        i = start;
        j = mid + 1;
        int n = end - start + 1;
        int [][] temp = new int[n][2];
        k = 0;
        while(i <= mid || j <= end){
            if(i <= mid && j <= end){
                if(nums[i][0] > nums[j][0]) temp[k++] = nums[j++];
                else temp[k++] = nums[i++];
            } else if(i <= mid) temp[k++] = nums[i++];
            else temp[k++] = nums[j++];
        }
        for(k = k - 1; k >= 0; k--){
            nums[start + k] = temp[k];
        }
    }
}

//using bit
class Solution {
    int[] bist;
    public int createSortedArray(int[] instructions) {
        int n = instructions.length;
        int MOD = (int)(1e9 + 7);
        int res = 0;
        bist = new int[100001];
        for(int i = 0; i < n; i++){
            int num = instructions[i];
            res += Math.min(getSum(num - 1), i - getSum(num));
            update(num);
            if(res >= MOD) res -= MOD;
        }
        return(res);
    }
    
    public int getSum(int i){
        //i = i + 1;
        int sum = 0;
        while(i > 0){
            sum += bist[i];
            i -= (i & -i);
        }
        return(sum);
    }
    
    public void update(int i){
        //i = i + 1;
        while(i < bist.length){
            bist[i]++;
            i += (i & -i);
        }
    }
}
