class Solution {
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        int[][] ver = new int[m + 1][n + 1];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                ver[i + 1][j + 1] = ver[i][j + 1] + matrix[i][j];
            }
        }
        int res = 0;
        for(int i = 0; i < m; i++){
            for(int j = i; j < m; j++){
                int[] arr = new int[n];
                for(int k = 0; k < n; k++){
                    arr[k] = ver[j + 1][k + 1] - ver[i][k + 1];
                }
                res += cnt(arr, target);
            }
        }
        return(res);
    }
    
    public int cnt(int[] arr, int target){
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int n = arr.length;
        int sum = 0, res = 0;
        for(int i = 0; i < n; i++){
            sum += arr[i];
            if(map.containsKey(sum - target)){
                res += map.get(sum - target);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return(res);
        
    }
}
