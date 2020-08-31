package LeetCodeDemo.link;

import _modal.ListNode;

/**
 * @Auther: 10413
 * @Date: 2020/3/18 11:14
 * @Description:
 * 19. 删除链表的倒数第N个节点
 */
public class Solution19 {
    /**
     * 哈哈，简单，快慢指针定位，删去即可
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head==null){
            return null;
        }
        ListNode pre = new ListNode(0);
        ListNode slow = head,quick = head;
        pre.next = slow;
        int length = 0;
        while (n>0){
            quick = quick.next;
            if (quick == null){
                if (n-1>0){
                    // 倒数n超出了list的长度
                    return head;
                }else{
                    //删除的头节点
                    return head.next;
                }
            }
            n--;
        }
        while (quick!=null){
            quick=quick.next;
            pre=pre.next;
            slow=slow.next;
        }
        pre.next=slow.next;
        return head;
    }
    public ListNode removeNthFromEnd_0(ListNode head, int n) {
        if (head==null){
            return null;
        }
        ListNode root = new ListNode(0);
        root.next = head;
        ListNode quick = head,pre = root;
        while (n>0){
            quick=quick.next;
            n--;
            if (quick==null&&n>0){
                return root.next;
            }
        }
        while (quick!=null){
            quick=quick.next;
            pre=pre.next;
        }
        pre.next = pre.next.next;
        return root.next;

    }

    /**
     * 答案：在处理最后结点时头节点的问题上纠结了一会，写的代码可读性比较差，看看答案咋写的
     */
    public ListNode removeNthFromEnd1(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;
        // 这里万一first还没到底就空了不就完了？ 试了一下果然，还是我考虑的周道
        for (int i = 1; i <= n + 1; i++) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        //这里给了提醒，我对头节点的判断可以用head直接当满指针用不就好了~！
        second.next = second.next.next;
        return dummy.next;
    }

    public static void main(String[] args) {
        Solution19 s = new Solution19();
        ListNode t = Solution2.getNewLink(new int[]{1});
        ListNode end = s.removeNthFromEnd1(t,3);
        Solution2.printLink(end);
    }
}
