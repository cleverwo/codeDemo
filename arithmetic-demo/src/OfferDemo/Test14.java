package OfferDemo;

import _modal.ListNode;

/**
 * @Auther: 10413
 * @Date: 2020/2/14 11:09
 * @Description:
 * 14链表中倒数第k个节点
 * 输入一个链表，输出该链表中倒数第k个结点。
 */
public class Test14 {

    /**
     * 暴力破解
     * @param head
     * @param k
     * @return
     */
    public ListNode FindKthToTail(ListNode head, int k) {
        int length = 0;
        ListNode p = head;
        // 获取链表长度
        while(p!=null){
            length++;
            p = p.next;
        }
        if (k>length){
            return null;
        }
        int now = length-k;
        p = head;
        while (now > 0){
            now--;
            p = p.next;
        }
        return p;
    }

    /**
     * 快慢指针思想
     * @return
     */
    public ListNode FindKthToTail2(ListNode head,int k){
        // 慢指针 result 指向结果
        ListNode result=head;
        // 快指针，指向目的
        ListNode fast = head;
        while (fast!=null){
            //让fast先走k步
            if (k>0){
                fast = fast.next;
                k--;
            }else{
                fast=fast.next;
                result=result.next;
            }
        }
        if (k>0){
            // k>0,表示fast在k步没走完就到底了证明不参战倒数第k个节点
            return null;
        }
        return result;
    }

    public ListNode FindKthToTail2_2(ListNode head,int k){
        ListNode result,fast;
        result=fast=head;
        while (fast!=null){
            //让fast先走k步
            if (k<=0){
                result=result.next;
            }
            k--;
            fast=fast.next;
        }
        return k<=0?result:null;
    }

    public static void main(String[] args) {
        ListNode home = new ListNode(0);
        ListNode pre = home;
        int i = 1;
        while(i < 10){
            pre.next = new ListNode(i);
            pre = pre.next;
            i ++;
        }
       /* ArrayList list = printListFromTailToHead(home);
        System.out.println(list.toString());*/
    }
}
