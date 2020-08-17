package OfferDemo.linklist;

import OfferDemo.node.ListNode;

/**
 * @Auther: 10413
 * @Date: 2020/2/14 13:35
 * @Description: 15, 反转链表
 * 输入一个链表，反转链表后，输出新链表的表头。
 */
public class Test15 {
    /**
     * 头插法建链表
     *
     * @param head
     * @return
     */
    public ListNode ReverseList(ListNode head) {
        //建立一个头节点，头插法建链表
        ListNode h = new ListNode(0);
        ListNode p = head, q;
        while (p != null) {
            q = p;
            p = p.next;
            q.next = h.next;
            h.next = q;
        }
        return h.next;
    }

    /**
     * 直接反转
     *利用三个直接来把指向倒转，pre，head，p
     * @param head
     * @return
     */
    public ListNode ReverseList2(ListNode head) {
        //初始化pre指针，用于记录当前结点的前一个结点地址
        ListNode pre = null;
        //初始化p指针，用于记录当前结点的下一个结点地址
        ListNode p = null;
        //head指向null时，循环终止。
        while (head != null) {
            //先用p指针记录当前结点的下一个结点地址。
            p = head.next;
            //让被当前结点与链表断开并指向前一个结点pre。
            head.next = pre;
            //pre指针指向当前结点
            pre = head;
            //head指向p(保存着原链表中head的下一个结点地址)
            head = p;
        }
        return pre;//当循环结束时,pre所指的就是反转链表的头结点
    }
}
