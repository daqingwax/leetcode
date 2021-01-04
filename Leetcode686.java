class Solution {
    public int repeatedStringMatch(String a, String b) {
        String c = a;
        int nn = a.length();
        int cnt = 1;
        while(c.length() <= b.length()){
            c += a;
            cnt++;
        }
        c += a;
        cnt++;
        int[] kmp = calculateKmp(b);
        //System.out.println(Arrays.toString(kmp));
        int n = c.length();
        int i = 0;
        int j = 0;
        int m = b.length();
        while(i < n){
            char cha = c.charAt(i);
            char chb = b.charAt(j);
            if(cha == chb){
                j++;
                if(j == m) return(cnt - (n - i - 1) / nn);
                i++;
            } else if(i < n && cha != chb){
                if(j > 0) j = kmp[j - 1];
                else i++;
            }

        }
        return(-1);
        
    }
    
    public int[] calculateKmp(String str){
        int n = str.length();
        int [] res = new int[n];
        int i = 1, j = 0;
        while(i < n){
            char chi = str.charAt(i);
            char chj = str.charAt(j);
            if(chi == chj){
                j++;
                res[i] = j;
                i++;
            } else{
                if(j > 0) j = res[j - 1];
                else{
                    res[i] = 0;
                    i++;
                }
            }

        }
        return(res);
    }
}
