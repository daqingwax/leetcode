//[1, 4, 9, 121, 484, 10201, 12321, 14641, 40804, 44944, 1002001, 1234321, 4008004, 100020001, 102030201, 104060401, 121242121, 123454321, 125686521, 400080004, 404090404, 10000200001, 10221412201, 12102420121, 12345654321, 40000800004, 1000002000001, 1002003002001, 1004006004001, 1020304030201, 1022325232201, 1024348434201, 1210024200121, 1212225222121, 1214428244121, 1232346432321, 1234567654321, 4000008000004, 4004009004004, 100000020000001, 100220141022001, 102012040210201, 102234363432201, 121000242000121, 121242363242121, 123212464212321, 123456787654321, 400000080000004, 10000000200000001, 10002000300020001, 10004000600040001, 10020210401202001, 10022212521222001, 10024214841242001, 10201020402010201, 10203040504030201, 10205060806050201, 10221432623412201, 10223454745432201, 12100002420000121, 12102202520220121, 12104402820440121, 12122232623222121, 12124434743442121, 12321024642012321, 12323244744232321, 12343456865434321, 12345678987654321, 40000000800000004, 40004000900040004]

class Solution {
    public int superpalindromesInRange(String left, String right) {
        int res = 0;
        Long l = Long.valueOf(left);
        Long r = Long.valueOf(right);
        List<Long> ans = new ArrayList<>();
        for(int i = 1; i <= 100000; i++){
            String digs = String.valueOf(i);
            int n = digs.length();
            long val1, val2;
            StringBuilder sb1 = new StringBuilder(digs);
            StringBuilder sb2 = new StringBuilder(digs);
            for(int j = 0; j < n; j++){
                if(j > 0) sb1.append(digs.charAt(n - j - 1));
                sb2.append(digs.charAt(n - j - 1));
            }
            val1 = Long.valueOf(sb1.toString());
            val2 = Long.valueOf(sb2.toString());
            String s1 = String.valueOf(val1 * val1);
            String s2 = String.valueOf(val2 * val2);
            if(isPal(s1)){
                Long s = Long.valueOf(s1);
                if(s >= l && s <= r){
                    res++;
                    ans.add(s);
                }
                //System.out.println(s1);
            }
            if(isPal(s2)){
                Long s = Long.valueOf(s2);
                if(s >= l && s <= r){
                    res++;
                    ans.add(s);
                }
                //System.out.println(s2);
            }
        }
        //System.out.println(res);
        //Collections.sort(ans);
        //System.out.println(ans);
        return(res);
    }
    public boolean isPal(String s){
        int i = 0, j = s.length() - 1;
        while(i < j){
            if(s.charAt(i) != s.charAt(j)) return(false);
            i++;
            j--;
        }
        return(true);
    }
}

