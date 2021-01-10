//BST has worst case n^2 running time, need to use BIT or merge sort
class Solution {
    class TreeNode {
        TreeNode left;
        TreeNode right;
        int val, dup, rightCnt, leftCnt;
        TreeNode(int val){
            this.val = val;
            this.dup = 0;
            this.rightCnt = 0;
            this.leftCnt = 0;
        }
    }
    public int reversePairs(int[] nums) {
        int res = 0;
        int n = nums.length;
        if(n < 2) return(res);
        TreeNode root = new TreeNode(nums[n - 1]);
        for(int i = n - 1; i >= 0; i--){
            int temp = search(root, nums[i]);
            //System.out.printf("i = %d, temp = %d.\n", i, temp);
            res += temp;
            insert(root, nums[i]);
        }
        return(res);
    }
    
    public void insert(TreeNode root, int num){
        TreeNode node = root;
        while(node.val != num){
            //num < node
            if(node.val > num){
                if(node.left == null) node.left = new TreeNode(num);
                node.leftCnt++;
                node = node.left;
            } else{
                if(node.right == null) node.right = new TreeNode(num);
                node.rightCnt++;
                node = node.right;
            }
        }
        node.dup++;
    }
    public int search(TreeNode root, int num){
        TreeNode node = root;
        int res = 0;
        while(node != null){
            if(node.val < num - node.val){
                res += node.leftCnt + node.dup;
                node = node.right;
            } else{
                node = node.left;
            }
        }
        return(res);
    }
}
