//traverse the list twice and use a dummy. 
// a bug was introduced that when i use head.val == 0.. should check head.next == null and head.val == 0
// a little tricky for the index tracking.
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    class NodeInd{
        ListNode node;
        int ind;
        NodeInd(ListNode node, int ind){
            this.node = node;
            this.ind = ind;
        }
        public String toString(){
            return(String.format("node val = %d, ind = %d.", this.node.val, this.ind));
        }
    }
    public ListNode removeZeroSumSublists(ListNode head) {
        if(head == null || (head.next == null && head.val == 0)) return(null);
        ListNode node = head;
        ListNode dummy = new ListNode(0, head);
        Map<Integer, NodeInd> preMap = new HashMap<>();
        preMap.put(0, new NodeInd(dummy, 0));
        int i = 0;
        int sum = 0;
        while(node != null){
            i++;
            sum += node.val;
            preMap.put(sum, new NodeInd(node, i));
            node = node.next;
        }
        //System.out.println(preMap);
        node = dummy;
        sum = 0;
        i = 0;
        while(node != null){
            sum += node.val;
            if(preMap.containsKey(sum) && preMap.get(sum).ind > i){
                node.next = preMap.get(sum).node.next;
                i = preMap.get(sum).ind;
            }
            node = node.next;
            i++;
        }
        return(dummy.next);
        
        
    }
}
