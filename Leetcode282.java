// cannot finish, too compicated..


class Solution {
    public List<String> addOperators(String num, int target) {
        
    }
    class ParsedResult{
        String orig;
        int res;
        int lastNum;
        char lastSym;
        ParsedResult(String str){
            orig = str;
            this.parseString(str);
        }
        
        
    }
    
    public ParsedResult parseStr(String str, Map<String, ParsedResult> map){
        if(map.containsKey(str)) return(map.get(str));
        else{
            int n = str.length;
            for(int i = n - 1; i >= 0; i++){
                if(!(str.charAt(i) >= '0' && str.charAt(i) <= '9')
            }
        }
    }
    
    
    public void dfs(List<String> res, String curr, String num, int currRes, int start, int target, Map<String, Integer> map){
        if(start == num.length()){
            if(currRes == target) res.add(curr);
            return;
        }
        if(start == 0){
            curr = num.substring(0, 1);
            dfs(res, curr, num, currRes, start + 1, target, map);
        } else{
            char [] symbols = new char[]{'+', '-', '*', ' '};
            int nops = 4;
            for(int i = 0; i < nops; i++){
                if(i == 3 && (curr.charAt(curr.length() - 1) == '0' && (curr.length() == 1 || curr.charAt(curr.length() - 2) > '9' || curr.charAt(curr.length() - 2) <= '0'))) continue;
                String nextCurr = curr + (symbols[i] == ' '?"" : symbols[i]);
                
            }
        }
        
    }
    
    
                            
}
