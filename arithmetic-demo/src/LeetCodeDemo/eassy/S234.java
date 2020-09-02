package LeetCodeDemo.eassy;

import _modal.ListNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @Auther: 10413
 * @Date: 2020/8/31 23:35
 * @Description:
 * 234. 回文链表
 */
public class S234 {
    public boolean isPalindrome(ListNode head){
        List<Integer> list = new ArrayList<>();
        ListNode p = head;
        while (p!=null){
            list.add(p.val);
            p = p.next;
        }
        int l = 0, h = list.size()-1;
        while (l<h){
            int a = list.get(l);
            int b = list.get(h);
            if (!list.get(l).equals( list.get(h))){
                return  false;
            }
            h --;
            l ++;
        }
        return true;
    }

    public boolean isPalindrome1(ListNode head){
        ListNode k = head;
        ListNode s = head;
        if (head == null){
            return true;
        }
        while (k.next!=null&&k.next.next !=null){
            k = k.next.next;
            s = s.next;
        }
        ListNode halfNode = s;
        ListNode startNode = head;
        ListNode q = reserveList(s.next);
        while(q !=null){
            if (q.val != startNode.val){
                return false;
            }
            q = q.next;
            startNode = startNode.next;
        }
        return true;

    }

    public ListNode reserveList(ListNode root){
        ListNode p = root,q;
        if (root == null){
            return null;
        }
        ListNode r = new ListNode(0);
        while (p!=null){
             q = p.next;
             p.next = r.next;
             r.next = p;
             p = q;
        }
        return r.next;
    }

    public static void main(String[] args) {
        ListNode root = new ListNode(-129);
        ListNode l = new ListNode(-129);
        root.next = l;
        S234 s = new S234();
        System.out.println(s.isPalindrome(root));
    }
}
