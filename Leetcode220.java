//use treeset floor to check
class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Long> set = new TreeSet<>();
        int n = nums.length;
        for(int i = 0; i < n; i++){
            long num = (long) nums[i];
            long upper = num + (long) t;
            long lower = num - (long) t;
            Long key = set.floor(upper);
            if(key != null && key >= lower) return(true);
            set.add(num);
            if(i >= k) set.remove(new Long(nums[i - k]));
        }
        return(false);
    }
}
