package OfferDemo;

import _modal.ListNode;

/**
 * @Auther: 10413
 * @Date: 2020/2/15 16:57
 * @Description: 56，删除链表中重复的节点
 * 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，
 * 重复的结点不保留，返回链表头指针。
 * 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
 */
public class Test56 {

    /**
     * 思路：
     * 两个指针删重复
     *
     * @param pHead
     * @return
     */
    public ListNode deleteDuplication(ListNode pHead) {
        if (pHead == null) {
            return null;
        }
        ListNode L = new ListNode(-1);
        L.next = pHead;
        ListNode pre = L;
        while (pHead != null) {
            int num = pHead.val;
            //标记p节点是否需要删除
            int flag = 0;
            while (pHead.next != null && pHead.next.val == num) {
                flag = 1;
                //删除p。next
                pHead.next = pHead.next.next;
            }
            if (flag==1) {
                //删除p
                pre.next=pHead.next;
                pHead=pre.next;
            }else{
                pre=pre.next;
                pHead=pHead.next;
            }
        }
        return L.next;
    }

    /**
     * 答案： 两个指针删重复
     */
    public ListNode deleteDuplication1(ListNode pHead){
        if(pHead == null || pHead.next == null){
            return pHead;
        }
        // 自己构建辅助头结点
        ListNode head = new ListNode(Integer.MIN_VALUE);
        head.next = pHead;
        ListNode pre = head;
        ListNode cur = head.next;
        while(cur!=null){
            if(cur.next != null && cur.next.val == cur.val){
                // 相同结点一直前进
                while(cur.next != null && cur.next.val == cur.val){
                    cur = cur.next;
                }
                // 退出循环时，cur 指向重复值，也需要删除，而 cur.next 指向第一个不重复的值
                // cur 继续前进
                cur = cur.next;
                // pre 连接新结点
                pre.next = cur;
            }else{
                pre = cur;
                cur = cur.next;
            }
        }
        return head.next;
    }
}
