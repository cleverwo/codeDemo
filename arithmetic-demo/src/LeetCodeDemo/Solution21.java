package LeetCodeDemo;

import _modal.ListNode;

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
        ListNode root = new ListNode(0);
        ListNode p1=l1,p2=l2,q=root;
        while(p1!=null&&p2!=null){
            if(p1.val>p2.val){
                q.next = p2;
                p2=p2.next;
            }else{
                q.next=p1;
                p1=p1.next;
            }
            q=q.next;
        }
        if(p1!=null){
            q.next=p1;
        }else{
            q.next = p2;
        }
        return root.next;
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
