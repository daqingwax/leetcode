//math problem, see if they are reversed or not..
class Solution {
    public char findKthBit(int n, int k) {
        if(n == 1) return('0');
        if(k == (1 << (n - 1))) return('1');
        int[] numBits = new int[n];
        numBits[0] = 1;
        for(int i = 1; i < n; i++){
            numBits[i] = 2 * numBits[i - 1] + 1;
        }
        int i = n;
        int[] reversed = new int[n];
        int[] kth = new int[n];
        boolean rev = false;
        //kth[n - 1] = k;
        int nextk = k;
        boolean midbit = false;
        while(i >= 2){
            int midLoc = 1 << (i - 1);
            if(nextk == midLoc){
                midbit = true;
                break;
            }
            else if(nextk > midLoc){
                kth[i - 1] = midLoc - (nextk - midLoc);
                reversed[i - 1] = 1;
                rev = !rev;
            } else{
                kth[i - 1] = nextk;
            }
            nextk = kth[i - 1];
            
            i--;
        }
        //System.out.println(nextk);
        //System.out.println(midbit);
        //System.out.println(rev);
        if(midbit) return(rev ? '0' : '1');
        String S2 = "011";
        char ch = S2.charAt(nextk - 1);
        if(rev){
            return(ch == '0' ? '1' : '0');
        } else{
            return(ch);
        }
    }
}
