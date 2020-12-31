/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    class NodeLR{
        TreeNode node;
        int left;
        int right;
        NodeLR(TreeNode node, int left, int right){
            this.node = node;
            this.left = left;
            this.right = right;
        }
    }
//bfs    
    public int longestZigZag(TreeNode root) {
        int res = 0;
        if(root == null) return(0);
        Queue<NodeLR> q = new LinkedList<>();
        q.offer(new NodeLR(root, 0, 0));
        while(!q.isEmpty()){
            NodeLR curr = q.poll();
            TreeNode node = curr.node;
            
            if(node.left != null){
                q.offer(new NodeLR(node.left, curr.right + 1, 0));
            } else{
                res = Math.max(res, curr.right);
            }
            if(node.right != null){
                q.offer(new NodeLR(node.right, 0, curr.left + 1));
            } else{
                res = Math.max(res, curr.left);
            }
        }
        return(res);
    }
    public void dfs(TreeNode root, int dir, int left, int right){
        if(root == null) return;
        dfs(root.left, 0, right + 1, left);
    }
}
