//本来用String写的dfs， 后来发现用数字更快

class Solution {
    public int[] numsSameConsecDiff(int n, int k) {
        List<Integer> res = new ArrayList<>();
        dfs(res, 0, n, k, 0);
        int size = res.size();
        int [] ans = new int[size];
        for(int i = 0; i < size; i++) ans[i] = res.get(i);
        return(ans);
        
    }
    public void dfs(List<Integer> res, int curr, int n, int k, int start){
        if(start == n){
            res.add(Integer.valueOf(curr));
            return;
        }
        if(start == 0){
            for(int i = 1; i <= 9; i++){
                dfs(res, curr + i, n, k, start + 1);
            }
        } else{
            int prev = curr % 10;
            if(k == 0){
                dfs(res, curr * 10 + prev, n, k, start + 1);
            } else{
                for(int i = -1; i <= 1; i = i + 2){
                    int next = prev + i * k;
                    if(next >= 0 && next <= 9){
                        dfs(res, curr * 10 + next, n, k, start + 1);
                    }
                }                
            }

        }
    }
}
