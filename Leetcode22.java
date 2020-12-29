//bitmasking
class Solution {
    public List<String> generateParenthesis(int n) {
        int nb = 2 * n;
        //int max = 1 << nb;
        int min = 0;
        int max = 0;
        List<String> res = new ArrayList<>();
        for(int i = 0; i < 2 * n; i++){
            int mask = 1 << i;
            if(i < n) min = min | mask;
            if(i % 2 == 1) max = max | mask;
        }
        //min = 0;
        //max = 1 << nb;
        for(int i = min; i <= max; i++){
            int[] bits = new int[nb];
            int zeros = 0;
            int ones = 0;
            boolean validComb = true;
            for(int j = nb - 1; j >= 0; j--){
                int dig = ((i & (1 << j)) == 0) ? 0 : 1;
                if(dig == 0) zeros++;
                else ones++;
                if(ones > zeros || zeros > n || ones > n){
                    validComb = false;
                    break;
                }
                bits[j] = dig;
            }
            if(!validComb) continue;
            StringBuilder sb = new StringBuilder();
            for(int j = nb - 1; j >= 0; j--){
                sb.append(bits[j] == 0 ? '(' : ')');
            }
            res.add(sb.toString());
        }
        return(res);
    }
}


//with backtrack, check when to add ( and )...
class Solution {
    public List<String> generateParenthesis(int n) {
        int nb = 2 * n;
        char[] chs = new char[nb];
        List<String> res = new ArrayList<>();
        dfs(res, chs, 0, 0, 0);
        return(res);
    }
    public void dfs(List<String> res, char[] chs, int start, int open, int close){
        if(start == chs.length){
            res.add(String.valueOf(chs));
            return;
        }
        if(open < chs.length / 2){
            chs[start] = '(';
            dfs(res, chs, start + 1, open + 1, close);
        }
        if(close < open){
            chs[start] = ')';
            dfs(res, chs, start + 1, open, close + 1);
        }
    }
}
