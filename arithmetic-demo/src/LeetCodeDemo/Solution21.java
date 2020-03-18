package LeetCodeDemo;

import OfferDemo.node.ListNode;

/**
 * @Auther: wangzhendong
 * @Date: 2020/1/17 22:16
 * @Description:
 * 21. 合并两个有序链表
 * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 */
public class Solution21 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1==null){
            return l2;
        }
        if (l2 ==null){
            return l1;
        }
        ListNode l11=l1,l22=l2,now;
        ListNode pre = new ListNode(0);
        ListNode end = pre;
        while (l11 != null&&l22!=null){
            if (l11.val>l22.val){
                now = l22.next;
                pre.next = l22;
                l22.next = l11;
                l22 = now;
            }else{
                now = l11.next;
                pre.next = l11;
                l11.next = l22;
                l11 = now;
            }
            pre = pre.next;
        }
        return end.next;
    }

    //递归
    public ListNode megeTwoLists2(ListNode l1, ListNode l2){
        if (l1 == null){
            return l2;
        }
        if (l2 == null){
            return l1;
        }
        if (l1.val < l2.val){
            l1.next = megeTwoLists2(l1.next,l2);
            return l1;
        }else {
            l2.next = megeTwoLists2(l1,l2.next);
            return l2;
        }
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(1);
    }
}
