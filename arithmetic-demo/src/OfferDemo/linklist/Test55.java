package OfferDemo.linklist;

import OfferDemo.node.ListNode;

/**
 * @Auther: 10413
 * @Date: 2020/2/15 16:18
 * @Description:
 * 55，链表中环的入口节点
 * 给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，输出null。
 */
public class Test55 {

    /**
     * 思路：
     * 环的入口节点是啥？？
     * 遍历链表，环的存在，遍历遇见的第一个重复的即为入口节点
     * 快慢指针求解
     * 错误：
     * 对一个链表判断是不是环可以这样写，但这里时判断一个链表中是否有环，可能存在开始不是单链，
     * 在某节点处形成闭环，返回的时这个开始的某节点如；1-2-3-4-5-3， 返回3
     * @param pHead
     * @return
     */
    public ListNode EntryNodeOfLoop(ListNode pHead){
        if (pHead==null||pHead.next==null){
            return null;
        }
        //快慢指针，p1走一步，p2走2步
        ListNode p1=pHead,p2=pHead;
        while (p1!=null&&p2!=null){
            p1=p1.next;
            p2=p2.next.next;
            if (p1==p2){
                return p1;
            }
        }
        return null;
    }

    /**
     * 答案
     * 这题我们可以采用双指针解法，一快一慢指针。快指针每次跑两个element，慢指针每次跑一个。如果存在一个圈，总有一天，慢指针是能追上快指针的。
     * 如下图所示，我们先找到快慢指针相遇的点，p。我们再假设，环的入口在点q，从头节点到点q距离为A，q p两点间距离为B，p q两点间距离为C。
     * 因为快指针是慢指针的两倍速，且他们在p点相遇，则我们可以得到等式 2(A+B) = A+B+C+B.
     * 由3的等式，我们可得，C = A。
     * 这时，因为我们的slow指针已经在p，我们可以新建一个另外的指针，slow2，让他从头节点开始走，每次只走下一个，原slow指针继续保持原来的走法，和slow2同样，每次只走下一个。
     * 我们期待着slow2和原slow指针的相遇，因为我们知道A=C，所以当他们相遇的点，一定是q了。
     * 我们返回slow2或者slow任意一个节点即可，因为此刻他们指向的是同一个节点，即环的起始点，q。
     */
    public ListNode EntryNodeOfLoop1(ListNode pHead)
    {
        if(pHead==null||pHead.next==null)return null;
        ListNode p1=pHead;
        ListNode p2=pHead;
        while(p2!=null&&p2.next!=null){
            p1=p1.next;
            p2=p2.next.next;
            //在这找到是存在环的
            if(p1==p2){
                p1=pHead;//重置p1为头节点
                while(p1!=p2){
                    p1=p1.next;
                    p2=p2.next;
                }
                if(p1==p2)return p1;
            }
        }
        return null;
    }

}
