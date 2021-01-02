//same bitmasking technique  fill 0 with -1
//
class Solution {
    public int findTheLongestSubstring(String s) {
        int[] charNums = new int[26];
        Arrays.fill(charNums, -1);
        //'a'
        charNums[0] = 0;
        //'e'
        charNums['e' - 'a'] = 1;
        //'i'
        charNums['i' - 'a'] = 2;
        //'o'
        charNums['o' - 'a'] = 3;
        //'u'
        charNums['u' - 'a'] = 4;
        int[] dp = new int[1 << 5];
        int[] cnt = new int[1 << 5];
        int n = s.length();
        int res = 0;
        int mask = 0;
        Arrays.fill(dp, n);
        dp[0] = -1;
        //cnt[0] = 2;
        for(int i = 0; i < n; i++){
            int chi = s.charAt(i) - 'a';
            if(charNums[chi] >= 0){
                mask ^= (1 << charNums[chi]);
                //cnt[mask]++;
            }
            //if(cnt[mask] > 1) res = Math.max(res, i - dp[mask]);
            res = Math.max(res, i - dp[mask]);
            dp[mask] = Math.min(dp[mask], i);
            //System.out.println("i = " + i + " ,mask = " + mask + ", res = " + res);
        }
        return(res);
    }
}
