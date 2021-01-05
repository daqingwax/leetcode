//swap the max num at the end to the first number smaller than it in the front
//i forgot the >= at line 25; also break at line 18
class Solution {
    public int maximumSwap(int num) {
        int nu = num;
        List<Integer> digs = new ArrayList<>();
        while(nu != 0){
            digs.add(nu % 10);
            nu = nu / 10;
        }
        int n = digs.size();
        boolean foundNum = false;
        int smallDigNum = -1;
        for(int i = n - 1; i >= 1; i--){
            if(digs.get(i) < digs.get(i - 1)){
                foundNum = true;
                smallDigNum = i;
                break;
            }
        }
        if(foundNum){
            int maxDig = digs.get(smallDigNum - 1);
            int maxDigIndex = smallDigNum - 1;
            for(int i = smallDigNum - 1; i >= 0; i--){
                if(digs.get(i) >= maxDig){
                    maxDig = digs.get(i);
                    maxDigIndex = i;
                }
            }
            int frontSmall = smallDigNum;
            for(int i = n - 1; i >= smallDigNum; i--){
                if(digs.get(i) < maxDig){
                    frontSmall = i;
                    break;
                }
            }
            digs.set(maxDigIndex, digs.get(frontSmall));
            digs.set(frontSmall, maxDig);
        }
        int res = 0;
        for(int i = n - 1; i >= 0; i--){
            res = res * 10 + digs.get(i);
        }
        return(res);
    }
}
