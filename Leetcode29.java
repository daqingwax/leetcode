// a >>> i - b >= 0 and a>>> i >= b is differnt
// need to use a >>> i as the integer min can transfer over

class Solution {
    public int divide(int dividend, int divisor) {
        if(dividend == Integer.MIN_VALUE && divisor == -1) return(Integer.MAX_VALUE);
        boolean sign = (dividend >= 0) == (divisor >= 0);
        int a = Math.abs(dividend);
        //System.out.println(a);
        int b = Math.abs(divisor);
        int res = 0;
        for(int i = 31; i >= 0; i--){
            if((a >>> i) - b >= 0){
                res |= 1 << i;
                a -= (b << i);
            }
        }
        if(!sign) res = -res;
        return(res);
    }
}
/*class Solution {

    public int divide(int A, int B) {
        if (A == 1 << 31 && B == -1) return (1 << 31) - 1;
        int a = Math.abs(A), b = Math.abs(B), res = 0;
        for (int x = 31; x >= 0; x--)
            if ((a >>> x) - b >= 0) {
                res += 1 << x;
                a -= b << x;
            }
        return (A > 0) == (B > 0) ? res : -res;
    }
    }
*/
