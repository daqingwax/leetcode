//pure math calculations

class Solution {
    public int findNthDigit(int n) {
        long base = 9;
        List<Long> digs = new ArrayList<>();
        digs.add(base);
        while(base < Integer.MAX_VALUE){
            base = base * 10;
            digs.add(base);
        }
        int nn = digs.size();
        long [] pre = new long[nn + 1];
        for(int i = 0; i < nn; i++){
            pre[i + 1] = pre[i] + (i + 1) * digs.get(i);
        }
        int l = 0; 
        int r = nn + 1;
        while(l < r){
            int m = l + (r - l) / 2;
            if(pre[m] < n) l = m + 1;
            else r = m;
        }
        long num = (long)n - pre[l - 1];
        //System.out.println(num);
        long baseNum = (int)Math.pow(10, l - 1) + num/l - 1;
        if(num % l != 0) baseNum++;
        //System.out.println(baseNum);
        int nthBit = (int)(num % l);
        nthBit = (nthBit == 0)? l : nthBit;
        int[] digits = new int[l + 1];
        int ii = l;
        while(baseNum != 0){
            digits[ii--] = (int)(baseNum % 10);
            baseNum /= 10;
        }
        return(digits[nthBit]);
    }
}
