//Merge Sort

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
    public ListNode sortList(ListNode head) {
        return(mergeSort(head));
    }
    
    public ListNode mergeSort(ListNode head){
        if(head == null || head.next == null) return(head);
        ListNode dummy = new ListNode(0, head);
        ListNode node = dummy;
        ListNode fast = head;
        while(fast != null){
            if(fast.next != null) fast = fast.next.next;
            else fast = fast.next;
            node = node.next;
        }
        ListNode head2 = node.next;
        node.next = null;
        ListNode newHead = mergeSort(head);
        ListNode newHead2 = mergeSort(head2);
        return(merge(newHead, newHead2));
    }
    
    public ListNode merge(ListNode head, ListNode head2){
        ListNode dummy = new ListNode(0);
        ListNode node = dummy;
        ListNode node1 = head;
        ListNode node2 = head2;
        while(node1 != null && node2 != null){
            if(node1.val < node2.val){
                node.next = node1;
                node1 = node1.next;
            } else{
                node.next = node2;
                node2 = node2.next;
            }
            node = node.next;
        }
        if(node1 != null) node.next = node1;
        else node.next = node2;
        return(dummy.next);
    }
}
