//longest common sequence -> longest increasing subsequence
// using mono-queue to store the data

class Solution {
    public int minOperations(int[] target, int[] arr) {
        Map<Integer, Integer> tmap = new HashMap<>();
        int m = target.length;
        for(int i = 0; i < m; i++) tmap.put(target[i], i);
        int n = arr.length;
        List<Integer> arList = new ArrayList<>();
        for(int i = 0; i < n; i++) if(tmap.containsKey(arr[i])) arList.add(tmap.get(arr[i]));
        
        m = arList.size();
        List<Integer> resList = new ArrayList<>();
        for(int i = 0; i < m; i++){
            int num = arList.get(i);
            if(resList.size() == 0){
                resList.add(num);
                continue;
            }
            int l = 0, r = resList.size();
            while(l < r){
                int mid = l + (r - l) / 2;
                if(resList.get(mid) >= num){
                    r = mid;
                } else{
                    l = mid + 1;
                }
            }
            if(l == resList.size()) resList.add(num);
            else resList.set(l, num);
            
        }
        return(target.length - resList.size());
    }
}
