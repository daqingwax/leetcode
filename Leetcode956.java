// two dimension dp
class Solution {
    public int tallestBillboard(int[] rods) {
        
        int n = rods.length;
        int sum = 0;
        for(int i = 0; i < n; i++) sum += rods[i];
        //dp state is defined as current abs difference between two bags, the abs max diff is sum between them 
        int[][] dp = new int[n + 1][sum + 1];
        //fill the dp array with min value
        for(int i = 0; i <= n; i++) Arrays.fill(dp[i], Integer.MIN_VALUE);
        //nothing yet, thus the difference between them is 0
        dp[0][0] = 0;
        for(int i = 0; i < n; i++){
            int r = rods[i];
            for(int j = 0; j <= sum; j++){
                //initial condition would be not putting the rod in any bag
                //thus it can start from the last state
                dp[i + 1][j] = dp[i][j];
                //put in the longer one
                if(j >= r) dp[i + 1][j] = Math.max(dp[i + 1][j], dp[i][j - r] + r);
                //put in the shorter one, and it is still shorter
                //j is the new difference, the previous difference is now reduced
                if(j + r <= sum) dp[i + 1][j] = Math.max(dp[i + 1][j], dp[i][j + r]);
                //put in the shorter one, and now it is longer
                // the original diff is j - r(negative),
                if(j <= r) dp[i + 1][j] = Math.max(dp[i + 1][j], dp[i][r - j] - (r - j) + r);
                
            }
        }
        return(dp[n][0]);
        
    }
}

//1 dimensional dp
class Solution {
    public int tallestBillboard(int[] rods) {
        
        int n = rods.length;
        int sum = 0;
        Arrays.sort(rods);
        int end = n - 1;
        for(int i = 0; i < n; i++) sum += rods[i];
        for(int j = n - 1; j >= 0; j--){
            if(rods[j] > sum / 2){
                end--;
                sum = sum - rods[j];
            }
            else break;
        }
        if(end < 1) return(0);
        //sum = 0;
        n = end + 1;
        //for(int i = 0; i < n; i++) sum += rods[i];
        //dp state is defined as current abs difference between two bags, the abs max diff is sum between them 
        //int[][] dp = new int[n + 1][sum + 1];
        //fill the dp array with min value
        int[] dp = new int[sum + 1];
        for(int i = 0; i <= n; i++) Arrays.fill(dp, Integer.MIN_VALUE);
        //nothing yet, thus the difference between them is 0
        dp[0] = 0;
        for(int i = 0; i < n; i++){
            int r = rods[i];
            int[] predp = Arrays.copyOfRange(dp, 0, sum + 1);
            for(int j = 0; j <= sum; j++){
                //initial condition would be not putting the rod in any bag
                //thus it can start from the last state
                dp[j] = predp[j];
                //put in the longer one
                if(j >= r) dp[j] = Math.max(dp[j], predp[j - r] + r);
                //put in the shorter one, and it is still shorter
                //j is the new difference, the previous difference is now reduced
                if(j + r <= sum) dp[j] = Math.max(dp[j], predp[j + r]);
                //put in the shorter one, and now it is longer
                // the original diff is j - r(negative),
                if(j <= r) dp[j] = Math.max(dp[j], predp[r - j] - (r - j) + r);
            }
        }
        return(dp[0]);
        
    }
}
