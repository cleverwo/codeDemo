package LeetCodeDemo.eassy;

import _modal.ListNode;

/**
 * @Auther: 10413
 * @Date: 2020/8/31 19:54
 * @Description:
 * 160. 相交链表
 */
public class S160 {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p = headA, q = headB;
        while (p!=null || q !=null){
            if (p == q){
                return p;
            }
            if (p == null){
                p = headB;
            }else{
                p = p.next;
            }
            if (q==null){
                q = headA;
            }else{
                q = q.next;
            }
        }
        return null;
    }
}
