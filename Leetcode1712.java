// two pointers, linear O(n) time

class Solution {
    public int waysToSplit(int[] nums) {
        int n = nums.length;
        int [] pre = new int[n];
        pre[0] = nums[0];
        //System.out.println(Arrays.toString(pre));
        long res = 0;
        long MOD = (long)(1e9 + 7);
        for(int i = 1; i < n; i++){
            pre[i] = pre[i - 1] + nums[i];
        }
        int [] post = new int[n];
        post[n - 1] = nums[n - 1];
        for(int i = n - 2; i >= 0; i--){
            post[i] = post[i + 1] + nums[i];
        }
        int j = 0, k = 0;
        System.out.println(n);
        for(int i = 0; i < n - 2; i++){
            if(pre[n - 1] < 3 * pre[i]) break;
            j = Math.max(j, i + 1);
            while(j < n - 1 && pre[j] - pre[i] < pre[i]) j++;
            //while(j < n && pre[j] - pre[i] < pre[i]) j++;
            if(j == n - 1) break;
            //System.out.println("i = " + i + ", j = " + j + ", k = " + k);
            // this is critical, cannot break but use continue instead as it can be satisfied in a later i when leftsum increases and midsum drops
            if(post[j + 1] < pre[j] - pre[i]) continue;
            //System.out.println("i = " + i + ", j = " + j + ", k = " + k);
            //if(post[j + 1] >= pre[j] - pre[i]){
                k = Math.max(k, j);
                while(k < n - 1 && post[k + 1] >= pre[k] - pre[i]) k++;
                res += k - j;
                //System.out.println(res);
                res %= MOD;
                
            //}
        }
        
        

        return((int)res);
    }
}
