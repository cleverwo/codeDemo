package OfferDemo.linklist;

import _modal.ListNode;

/**
 * @Auther: 10413
 * @Date: 2020/2/14 16:28
 * @Description:
 * 16,合并两个排序的链表
 * 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
 */
public class Test16 {
    /**
     * 直接从头到尾遍历,将list2插入到list1
     * @param list1
     * @param list2
     * @return
     */
    public ListNode Merge(ListNode list1, ListNode list2) {
        ListNode h = new ListNode(-1);
        ListNode cur = h;
        while (list1!=null&&list2!=null){
            if (list1.val>list2.val){
                cur.next = list2;
                list2 =list2.next;
            }else{
                cur.next = list1;
                list1 =list1.next;
            }
            cur = cur.next;
        }
        if (list1!=null){
            cur.next = list1;
        }else {
            cur.next =list2;
        }
        return h.next;
    }
}
