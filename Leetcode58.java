//need to remove the last spaces...
class Solution {
    public int lengthOfLastWord(String s) {

        while(s.length() >= 1 && s.charAt(s.length() - 1) == ' '){
            s = s.substring(0, s.length() - 1);
        }
        int n = s.length();
        
        int last = n;
        boolean foundSpace = false;
        for(int i = n - 1; i >= 0; i--){
            if(s.charAt(i) == ' '){
                last = i;
                foundSpace = true;
                break;
            }
        }
        if(!foundSpace) last = -1;
        return(n - last - 1);
    }
}
