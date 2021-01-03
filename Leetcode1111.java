//using tree + bfs
class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        int right = 0, left = 0;
        int n = seq.length();
        int[] res = new int[n];
        TreeNode root = new TreeNode(-1);
        root.setClose(-1);
        TreeNode node = root;
        for(int i = 0; i < n; i++){
            char ch = seq.charAt(i);
            if(ch == '('){
                TreeNode newNode = new TreeNode(i);
                node.children.add(newNode);
                newNode.parent = node;
                node = newNode;
            } else{
                node.setClose(i);
                node = node.parent;
            }
        }
        int dep = getDepth(root);
        int target = dep / 2;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int level = 0;
        int val = 1;
        //System.out.println(target);
        while(!q.isEmpty()){
            int sz = q.size();
            if(level <= target && level > 0) val = 0;
            else val = 1;
            //System.out.println(level);
            for(int i = 0; i < sz; i++){
                node = q.poll();
                if(level > 0){
                    res[node.locs[0]] = val;
                    res[node.locs[1]] = val;
                }
                for(TreeNode ch: node.children){
                    q.offer(ch);
                }
            }
            level++;
        }
        //System.out.println(res);
        return(res);
        
    }
    
    public int getDepth(TreeNode node){
        if(node.children.size() == 0) return(0);
        int val = 0;
        for(TreeNode ch: node.children){
            val = Math.max(getDepth(ch), val);
        }
        return(val + 1);
    }
    
    class TreeNode {
        TreeNode parent;
        int[] locs;
        List<TreeNode> children;
        TreeNode(int open){
            this.locs = new int[2];
            this.children = new ArrayList<>();
            this.locs[0] = open;
        }
        public void setClose(int val){
            this.locs[1] = val;
        }
    }
}

//open and close
class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        int n = seq.length();
        int [] res = new int[n];
        int cnt = 0;
        for(int i = 0; i < n; i++){
            char ch = seq.charAt(i);
            if(ch == '(') cnt++;
            res[i] = cnt % 2;
            if(ch == ')') cnt--;
        }
        return(res);
    }
}
