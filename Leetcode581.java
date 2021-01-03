//can sort or use stack, remember while
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        Deque<Integer> dq = new LinkedList<>();
        int l = n, r = 0;
        for(int i = 0; i < n; i++){
            while(!dq.isEmpty() && nums[i] < nums[dq.peekLast()]) l = Math.min(l, dq.pollLast());
            dq.offerLast(i);
        }
        dq = new LinkedList<>();
        for(int i = n - 1; i >= 0; i--){
            while(!dq.isEmpty() && nums[i] > nums[dq.peekLast()]) r = Math.max(r, dq.pollLast());
            dq.offerLast(i);
        }
        return(l < r ? (r - l + 1) : 0);
    }

}
