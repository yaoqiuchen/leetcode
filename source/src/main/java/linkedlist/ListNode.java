package linkedlist;

/**
 * Created by 半仙.
 */
public class ListNode {

    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
        next = null;
    }

    public ListNode() {

    }

    public ListNode(int val, ListNode next) {
        this.val = val; this.next = next;
    }
}
