//tricky part is need to add the dq size to the cnt
class Solution {
    public int maxDepth(String s) {
        
        int n = s.length();
        Deque<Character> dq = new LinkedList<>();
        int cnt = 0;
        int res = 0;
        for(int i = 0; i < n; i++){
            char ch = s.charAt(i);
            if(ch == '('){
                res = Math.max(cnt + dq.size(), res);
                dq.offerLast('(');
                //if(cnt != 0) System.out.println(cnt);
                cnt = 0;
            }
            else if(ch == ')'){
                dq.pollLast();
                cnt++;
            }
            
        }
        res = Math.max(cnt, res);
        return(res);
    }
}


//it is not necessary to use a dq... which is overhead
class Solution {
    public int maxDepth(String s) {
        
        int n = s.length();
        int res = 0, left = 0, right = 0;
        for(int i = 0; i < n; i++){
            char ch = s.charAt(i);
            if(ch == '('){
                left++;
            }
            else if(ch == ')'){
                right++;
            }
            res = Math.max(left - right, res);

        }
        return(res);
    }
}
