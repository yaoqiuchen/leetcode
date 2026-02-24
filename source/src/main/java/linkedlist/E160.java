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
public class E160 {

    public static void main(String[] args) {
        new E160().getIntersectionNode(null, null);
    }

    // 3/12
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        ListNode pointerA = headA, pointerB = headB;
        boolean aTurnedOver = false, bTurnedOver = false;

        while (pointerA != pointerB) {
            if (pointerA.next == null) {
                if (aTurnedOver) return null;
                pointerA = headB;
                aTurnedOver = true;
            } else {
                pointerA = pointerA.next;
            }

            if (pointerB.next == null) {
                if (bTurnedOver) return null;
                pointerB = headA;
                bTurnedOver = true;
            } else {
                pointerB = pointerB.next;
            }
        }

        return pointerA;
    }


}
