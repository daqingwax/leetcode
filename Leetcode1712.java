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

// binary search, harder to make it clean
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
            int l = i + 1, r = n - 1;
            while(l < r){
                int m = l + (r - l) / 2;
                if(pre[m] - pre[i] >= pre[i]) r = m;
                else l = m + 1;
            }
            if(l == n - 1) break;
            //System.out.printf("i = %d, l = %d.\n", i, l);
            //this is the most critical line, should use continue
            if(post[l + 1] < pre[l] - pre[i]) continue;
            //System.out.printf("i = %d, l = %d, l2 = %d", i, l, l2)
            int l2 = l, r2 = n - 1;
            while(l2 < r2){
                int m2 = l2 + (r2 - l2) / 2;
                if(post[m2 + 1] < pre[m2] - pre[i]) r2 = m2;
                else l2 = m2 + 1;
            }
            
            //System.out.printf("i = %d, l = %d, l2 = %d\n", i, l, l2);
            res += l2 - l;
            //System.out.println(res);
            res %= MOD;
                
            //}
        }
        
        

        return((int)res);
    }
}
