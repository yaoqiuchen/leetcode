package linkedlist;

/**
 19. 删除链表的倒数第N个节点
 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。

 示例：

 给定一个链表: 1->2->3->4->5, 和 n = 2.

 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 说明：

 给定的 n 保证是有效的。

 进阶：

 你能尝试使用一趟扫描实现吗？

 */
public class M19 {

    public static void main(String[] args) {
        new M19().removeNthFromEnd(null, 2);
    }

    // 2/25
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }

        ListNode slow = head, fast = head;
        while (n-- > 0) {
            fast = fast.next;
        }

        if (fast == null) {
            slow = head.next;
            head.next = null;
            return slow;
        }

        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        ListNode next = slow.next.next;
        slow.next.next = null;
        slow.next = next;

        return head;
    }


}
