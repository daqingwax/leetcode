//using tree to implment
/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
class Solution {
    class Node{
        Node parent;
        List<Node> children;
        int val;
        Node(){
            children = new ArrayList<>();
            val = Integer.MAX_VALUE;
        }
    }
    public NestedInteger deserialize(String s) {
        int n = s.length();
        if(n == 0 || s.equals("[]")) return(new NestedInteger());
        char[] chs = s.toCharArray();
        Node root = new Node();
        Node node = root;
        int num = 0;
        int sign = 1;
        for(int i = 0; i < n; i++){
            char ch = chs[i];
            if(ch >= '0' && ch <= '9') num = num * 10 + (ch - '0');
            else if(ch == '-') sign = -1;
            else{
                if(ch == '['){
                    Node curr = new Node();
                    curr.parent = node;
                    node.children.add(curr);
                    node = curr;
                } else if(ch == ']'){
                    if(chs[i - 1] >= '0' && chs[i - 1] <= '9'){
                        Node curr = new Node();
                        curr.val = sign * num;
                        num = 0;
                        sign = 1;
                        node.children.add(curr);
                    }
                    node = node.parent;
                } else if(ch == ','){
                    if(chs[i - 1] >= '0' && chs[i - 1] <= '9'){
                        Node curr = new Node();
                        curr.val = sign * num;
                        sign = 1;
                        num = 0;
                        node.children.add(curr);
                    }
                }
            }
        }
        if(chs[n - 1] >= '0' && chs[n - 1] <= '9'){
            Node curr = new Node();
            curr.val = sign * num;
            sign = 1;
            num = 0;
            node.children.add(curr);
        }
        NestedInteger res = dfs(root.children.get(0));
        int nn = root.children.size();
        for(int i = 1; i < nn; i++) res.add(dfs(root.children.get(i)));
        return(res);
    }
    
    public NestedInteger dfs(Node root){
        NestedInteger res = new NestedInteger();
        Node node = root;
        if(node.children.size() == 0){
            if(node.val != Integer.MAX_VALUE) res.setInteger(node.val);
        } else{
            for(Node child: node.children){
                res.add(dfs(child));
            }
        }
        return(res);
        
    }
}


//use stack

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
class Solution {

    public NestedInteger deserialize(String s) {
        int n = s.length();
        if(n == 0) return(new NestedInteger());
        char[] chs = s.toCharArray();
        Deque<NestedInteger> dq = new LinkedList<>();
        dq.offerLast(new NestedInteger());
        int i = 0;
        while(i < n){
            if(chs[i] == '['){
                dq.offerLast(new NestedInteger());
            } else if(chs[i] == ']'){
                NestedInteger ni = dq.pollLast();
                dq.peekLast().add(ni);
            } else if(chs[i] == '-' || Character.isDigit(chs[i])){
                int sign = 1;
                int num = 0;
                int j = i;
                if(chs[i] == '-'){
                    sign = -1;
                    j = i + 1;
                }
                while(j < n && Character.isDigit(chs[j])){
                    num = num * 10 + chs[j] - '0';
                    //System.out.println(chs[j]);
                    j++;
                }
                i = j - 1;
                num = (sign == 1? num : -num);
                //System.out.println(num);
                dq.peekLast().add(new NestedInteger(num));
            }
            i++;
        }
        return(dq.peekLast().getList().get(0));
         
    }
}
