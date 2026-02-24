package linkedlist;

import java.util.List;

/**
 *
 *
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order, and each of their nodes contains a single digit.
 * Add the two numbers and return the sum as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 */
public class M2 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode();
        ListNode cur = head;

        boolean proceed = false;
        while (l1 != null || l2 != null) {
            int sum = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + (proceed ? 1 : 0);
            proceed = sum > 9;
            
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;

            ListNode newNode = new ListNode(sum % 10);
            cur.next = newNode;
            cur = newNode;
        }

        if (proceed) {
            cur.next = new ListNode(1);
        }
        return head.next;
    }
}
