class Solution {
    public int findIntegers(int num) {
        int[] digs = new int[32];
        int n = 0;
        while(num != 0){
            digs[n++] = (num & 1) == 0 ? 0 : 1;
            num >>= 1;
        }
        if(n == 0) return(1);
        if(n == 1) return(2);
        int[] cnt = new int[n];
        cnt[0] = 1;
        cnt[1] = 2;
        for(int i = 2; i < n; i++){
            //xxxxxx = xxxxx0 + xxxx01
            cnt[i] = cnt[i - 1] + cnt[i - 2];
        }
        int res = 0;
        for(int i = n - 1; i >= 0; i--){
            if(i == n - 1) res += cnt[i];
            else{
                if(digs[i] == 1){
                    res += cnt[i];
                    if(digs[i + 1] == 1){
                    //need to minus 1 as the number itself might be a candidate
                        res--;
                        break;
                    }
                }
            }
        }
        return(res + 1);
    }
}
