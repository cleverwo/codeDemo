package OfferDemo;

import _modal.ListNode;

/**
 * @Auther: 10413
 * @Date: 2020/2/20 12:07
 * @Description:
 * 36,两个链表的第一个公共节点
 */
public class Test36 {


    /**
     * 答案1
     * 思路是将两个链表首位相接，长度一样了：
     * 如：1-2-3-4-5-null 和 6-7-4-5-null变为
     * 1-2-3-4-5-n-6-7-4-5-n 和 n就是null
     * 6-7-4-5-n-1-2-3-4-5-n
     * 发现后面就是一样的了这是因为，a和b有公共节点，那么他们长度相同时必定后面是一致的，所以让ab互补就达到了结果
     */
    public ListNode FindFirstCommonNode1(ListNode pHead1, ListNode pHead2) {
        if(pHead1 == null || pHead2 == null){
            return null;
        }
        ListNode p1 = pHead1;
        ListNode p2 = pHead2;
        while(p1!=p2){
            if(p1 == null){
                p1 = pHead2;
            }else{
                p1 = p1.next;
            }
            if(p2 == null){
                p2 = pHead1;
            }else{
                p2 = p2.next;
            }
        }
        return p1;
    }


    public static void main(String[] args) {
        //{1,2,3},{4,5},{6,7}
        ListNode t = new ListNode(1);
        ListNode t1 = new ListNode(2);
        ListNode t2 = new ListNode(3);
        ListNode t3 = new ListNode(4);
        ListNode t4 = new ListNode(5);
        ListNode t5 = new ListNode(6);
        ListNode t6 = new ListNode(7);
        t.next = t1;
        t1.next=t2;
        t2.next=t5;
        t5.next=t6;
        t3.next=t4;
        t4.next=t5;
        Test36 test = new Test36();
        ListNode out =  test.FindFirstCommonNode1(t,t3);
    }
}
