//dfs with memorization

class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        int n = nums.length;
        int[][] memo = new int[n][2001];
        for(int i = 0; i < n; i++) Arrays.fill(memo[i], Integer.MIN_VALUE);
        return(dfs(nums, S, 0, 0, memo));
    }
    
    public int dfs(int[] nums, int S, int start, int currSum, int[][] memo){
        if(start == nums.length){
            if(currSum == S) return 1;
            else return 0;
        }
        
        if(memo[start][currSum + 1000] != Integer.MIN_VALUE) 
            return(memo[start][currSum + 1000]);
        int addResult = dfs(nums, S, start + 1, currSum + nums[start], memo);
        int subResult = dfs(nums, S, start + 1, currSum - nums[start], memo);
        memo[start][currSum + 1000] = addResult + subResult;
        return(memo[start][currSum + 1000]);
    }
}

//equal subset dp
class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        int n = nums.length;
        int sum = 0;
        for(int i = 0; i < n; i++) sum += nums[i];
        if(S > sum || S < -sum || (sum + S) % 2 != 0) return(0);
        int target = Math.min((sum + S) / 2, (sum - S) / 2);
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for(int i = 0; i < n; i++){
            for(int j = target; j >= nums[i]; j--){
                dp[j] += dp[j - nums[i]];
            }
        }
        return(dp[target]);
    }
}
