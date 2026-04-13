package linkedlist;

public class M24 {

    public static void main(String[] args) {
    }


    // 2026/3/24
    public ListNode swapPairs(ListNode head) {
        ListNode res = new ListNode();
        ListNode _head = res;

        while (head != null) {
            if (head.next == null) {
                _head.next = head;
                break;
            }

            ListNode nextHead = head.next.next;

            // next node
            _head.next = head.next;
            head.next = null;

            // next of next node
            _head.next.next = head;
            _head = _head.next.next;

            head = nextHead;
        }

        return res.next;
    }


}
