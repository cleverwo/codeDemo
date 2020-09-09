package LeetCodeDemo;

import _modal.ListNode;

/**
 * @Auther: 10413
 * @Date: 2020/3/21 10:16
 * @Description:
 * 206. 反转链表
 */
public class Solution206 {

    /**
     * 递归？ 我用头插法
     */
    public ListNode reverseList(ListNode head) {
        ListNode t = new ListNode(0);
        ListNode p = head;
        while (p!=null){
            head = p.next;
            p.next = t.next;
            t.next = p;
            p =head;
        }
        return t.next;
    }

    /**
     * 递归
     */
    public ListNode reverseList1(ListNode head){
        if (head ==null||head.next==null){
            return head;
        }
        //这里直接到末尾了，1-2-3-4-5 直接运行到
        ListNode p = reverseList1(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }

}
