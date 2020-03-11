package linkedlist;

/**
 23. 合并K个排序链表

 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。

 示例:

 输入:
 [
   1->4->5,
   1->3->4,
   2->6
 ]
 输出: 1->1->2->3->4->4->5->6

 */
public class D23 {

    public static void main(String[] args) {
        new D23().mergeKLists(null);
    }

    // 3/11
    public ListNode mergeKLists(ListNode[] lists) {
        int len = lists.length;
        if (len == 0) {
            return null;
        }
        // 将n个链表以中间为对称，合并，即合并
        while(len>1) {
            for (int i=0; i<len/2; i++) {
                lists[i] = mergeTwoLists(lists[i], lists[len-1-i]);
            }
            len = (len+1)/2;
        }
        return lists[0];
    }

    // 合并两个链表
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(-1);
        ListNode p = head;
        while(l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                p.next = l1;
                l1 = l1.next;
            } else {
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }
        if (l1 != null) {
            p.next = l1;
        } else if (l2 != null) {
            p.next = l2;
        }
        return head.next;
    }

}
