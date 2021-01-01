//binary search, the magic number cnt is m/a + m/b - m/lcm
//the catch is that n * a can be exceeding int limit, just put a max that larger than that value..

class Solution {
    public int nthMagicalNumber(int n, int a, int b) {
        int g = gcd(a, b);
        int lcm = a * b / g;
        long l = Math.min(a, b);
        long r = (long) 1e15;
        while(l < r){
            long m = l + (r - l) / 2;
            long cnt = m / a + m / b - m / lcm;
            if(cnt < n) l = m + 1;
            else r = m;
        }
        int MOD = (int)(1e9 + 7);
        return (int)(l % MOD);
        
    }
    
    public int gcd(int a, int b){
        while(b > 0){
            int temp = b;
            b = a % b;
            a = temp;
        }
        return(a);
    }
}
