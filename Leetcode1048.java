//sort the string by length, then do the normal LIS
class Solution {
    public int longestStrChain(String[] words) {
        Arrays.sort(words, (a, b) -> a.length() - b.length());
        int n = words.length;
        if(n <= 1) return(n);
        int maxLen = words[n - 1].length() - words[0].length() + 1;
        int []dp = new int[n];
        Arrays.fill(dp, 1);
        int res = 0;
        for(int i = 1; i < n; i++){
            String b = words[i];
            for(int j = i - 1; j >= 0; j--){
                String a = words[j];
                if(b.length() - a.length() > 1) break;
                if(b.length() - a.length() == 1){
                    if(ispred(a, b)) dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            if(dp[i] == maxLen) return(dp[i]);
            //originally used dp[n - 1] as the result.. obviously wrong
            res = Math.max(res, dp[i]);
        }
        //System.out.println(Arrays.toString(dp));
        return(res);
        
    }
    
    public boolean ispred(String a, String b){
        if(a.length() + 1 != b.length()) return(false);
        int i = 0, j = 0;
        int m = a.length(), n = b.length();
        while(j < n){
            if(a.charAt(i) == b.charAt(j)){
                i++;
                if(i == m) return(true);
            }
            j++;
        }
        return(i == m);
    }
}
