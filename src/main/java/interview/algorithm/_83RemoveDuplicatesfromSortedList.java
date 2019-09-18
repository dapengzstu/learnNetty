package interview.algorithm;
class ListNode{
    public int val;
    ListNode next;
    public ListNode(int v){
        this.val = v;
    }

}
public class _83RemoveDuplicatesfromSortedList {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null){
            return  head;
        }
        ListNode p = head,q =head.next;
        while ( q != null){
            if (q.val == p.val){
                p.next = q.next;
                q = q.next;
            }else {
                p = p.next;
                q = q.next;
            }
        }
        return head;
    }
    public ListNode de(ListNode head){
        ListNode current = head;
        while (current!= null && current.next != null){
            if (current.next.val == current.val){
                current.next = current.next.next;
            }else {
                current = current.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(1);
        ListNode l3 = new ListNode(3);

        l1.next = l2;
        l2.next = l3;
        ListNode listNode = new _83RemoveDuplicatesfromSortedList().deleteDuplicates(l1);
        while (listNode != null){
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
}
