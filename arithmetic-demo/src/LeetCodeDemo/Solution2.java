package LeetCodeDemo;

import _modal.ListNode;

/**
 * @Auther: 10413
 * @Date: 2020/3/11 17:27
 * @Description:
 * 2. 两数相加
 */
public class Solution2 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1==null||l2==null){
            return null;
        }
        ListNode p1 = l1;
        ListNode p2 = l2;
        ListNode out = new ListNode(0);
        ListNode q = out;
        int num = 0;
        while(p1!=null||p2!=null){
            int sum = (p1==null?0:p1.val) + (p2==null?0:p2.val)+num;
            num = sum/10;
            q.next = new ListNode(sum%10);
            q = q.next;
            if (p1!=null) p1 = p1.next;
            if(p2!=null) p2 = p2.next;
        }
        if (num != 0){
            q.next = new ListNode(num);
        }
        return out.next;
    }

    public static void main(String[] args) {
        Solution2 s = new Solution2();
        ListNode l1 = getNewLink(new int[]{9,8});
        ListNode l2 = getNewLink(new int[]{1});
        ListNode out = s.addTwoNumbers(l1,l2);
        printLink(out);

    }

    // 新建链表
    public static ListNode getNewLink(int[] a){
        ListNode p = new ListNode(0);
        ListNode q = p;
        for (int i=0;i<a.length;i++){
            q.next = new ListNode(a[i]);
            q = q.next;
        }
        return p.next;
    }

    //打印链表
    public static void printLink(ListNode root){
        while(root!=null){
            System.out.print(root.val+ " ");
            root = root.next;
        }
    }
}
