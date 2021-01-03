//use deque

class Solution {
    public int minInsertions(String s) {
        int sum = 0;
        int res = 0;
        int n = s.length();
        Deque<Character> dq = new LinkedList<>();
        for(int i = 0; i < n;){
            char ch = s.charAt(i);
            if(ch == '(') dq.offerLast(ch);
            else{
                if(i < n - 1){
                    if(s.charAt(i + 1) == ')'){
                        if(!dq.isEmpty()){
                            dq.pollLast();
                        } else{
                            res++;
                        }
                        i = i + 1;
                    } else{
                        //has left (
                        if(!dq.isEmpty()){
                            dq.pollLast();
                            res++;
                        //missing a right ) and left(
                        } else{
                            res += 2;
                        }
                    }
                } else{
                    res++;
                    if(!dq.isEmpty()) dq.pollLast();
                    else res++;
                }
            }
            i = i + 1;
            
        }
        
        res += dq.size() * 2;
            
        return(res);
    }
}
