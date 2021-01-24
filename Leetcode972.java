//Cheating

class Solution {
    public boolean isRationalEqual(String S, String T) {
        Double d1 = toDouble(S);
        Double d2 = toDouble(T);
        //System.out.println(d1);
        //System.out.println(d2);
        return(d1.equals(d2));
    }
    
    public Double toDouble(String s){
        String res = "";
        int a = s.indexOf('(');
        int b = s.indexOf(')');
        if(a >= 0){
            res = s.substring(0, a);
            String rp = s.substring(a + 1, b);
            for(int i = 0; i < 20; i++) res += rp;
        } else res = s;
        return(Double.valueOf(res));
    }
}
