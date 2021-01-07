//division remainder returns back to power of 1 (not sure why...)
//edge case of a == 1, a % MOD == 0 and b = 0

class Solution {
    public int superPow(int a, int[] b) {
        Map<Integer, Integer> remMap = new HashMap<>();
        int MOD = 1337;
        int[] rems = new int[1338];
        int i = 1;
        
        int rem = a % MOD;
        int rem0 = rem;
        if(a == 1 || (b.length == 1 && b[0] == 0)) return(1);
        if(rem == 0) return(0);
        System.out.println(Arrays.toString(minus(new int[]{3,8,7,6,9,7,8}, new int[]{1,3,8,7,6,9,7,8})));
        while(!remMap.containsKey(rem)){
            remMap.put(rem, i);
            rems[i++] = rem; 
            rem = (rem * rem0) % MOD;
        }
        int dupStart = remMap.get(rem);
        int dupEnd = i - 1;
        int dupLen = dupEnd - dupStart + 1;
        int ind = calRem(b, dupLen);
        if(ind == 0) return(rems[dupLen - 1]);
        return(rems[ind]);
    }
    
    public int calRem(int[] b, int mod){
        int res = 0;
        for(int num: b){
            res = (res * 10 + num) % mod;
        }
        return(res);
    }
    
    public int[] minus(int[] nums1, int[] nums2){
        int m = nums1.length;
        int n = nums2.length;
        int[] res = new int[m];
        if(m < n) return(nums1);
        if(m == n){
            int i = 0;
            int j = 0;
            while(i < m && j < n && nums1[i] == nums2[j]){
                i++;
                j++;
            }
            if(i == n) return(new int[]{0});
            if(nums1[i] < nums2[j]) return(nums1);
            int borrow = 0;
            for(i = n - 1; i >= 0; i--){
                if(nums1[i] + borrow < nums2[i]){
                    res[i] = nums1[i] + borrow - nums2[i] + 10;
                    borrow = -1;
                } else{
                    res[i] = nums1[i] + borrow - nums2[i];
                    borrow = 0;
                }
            }
        } else{
            int borrow = 0;
            int i = n - 1;
            for(int j = m - 1; j >= 0; j--){
                int num2 = 0;
                if(i >= 0) num2 = nums2[i];
                if(nums1[j] + borrow < num2){
                    res[j] = nums1[j] + borrow - num2 + 10;
                    borrow = -1;
                } else{
                    res[j] = nums1[j] + borrow - num2;
                    borrow = 0;
                }
                i--;
            }
        }
        int firstNoneZero = -1;
        for(int i = 0; i < m; i++){
            if(res[i] != 0){
                firstNoneZero = i;
                break;
            }
        }
        if(firstNoneZero == -1) return(new int[]{0});
        return(Arrays.copyOfRange(res, firstNoneZero, m));
    }
}
