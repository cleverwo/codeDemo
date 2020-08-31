package LeetCodeDemo.eassy;

import _modal.ListNode;

/**
 * @Auther: 10413
 * @Date: 2020/8/27 23:50
 * @Description: 141. 环形链表
 */
public class S141 {

    public boolean hasCycle(ListNode head) {
        if (head == null) {
            System.out.println(0);
            return false;
        }
        ListNode p = head, q = head;
        int pos = 1;
        while (p != null && q != null) {
            p = p.next;
            if (q.next != null) {
                q = q.next.next;
            } else {
                return false;
            }
            if (p == q) {
                System.out.println(pos);
                return true;
            }
            pos++;
        }
        System.out.println(pos);
        return false;
    }

    // 延申 142 循环链表 循环找出循环的入口
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            System.out.println("no cycle");
            return null;
        }
        ListNode s = head, q = head;
        while (s !=null && q!=null) {
            s = s.next;
            if (q.next == null){
                System.out.println("no cycle");
                return null;
            }
            q = q.next.next;
            if (s == q) {
                s = head;
                int pos = 0;
                while (s != q) {
                    s = s.next;
                    q = q.next;
                    pos++;
                }
                System.out.println("tail connects to node index " + pos);
                return s;
            }
        }
        System.out.println("no cycle");
        return null;
    }

    public static void main(String[] args) {
        S141 s = new S141();
        ListNode root = new ListNode(3);
        ListNode t1 = new ListNode(2);
        ListNode t2 = new ListNode(0);
        ListNode t3 = new ListNode(-4);
        root.next = t1;
        t1.next = t2;
        t2.next = t3;
        t3.next = t1;

        System.out.println(s.detectCycle(root));
    }
}
