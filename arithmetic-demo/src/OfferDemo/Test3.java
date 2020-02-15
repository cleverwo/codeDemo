package OfferDemo;

import OfferDemo.node.ListNode;

import java.util.*;

/**
 * @Auther: 10413
 * @Date: 2020/1/21 23:38
 * @Description: 3.从尾到头打印链表
 * 输入一个链表，按链表从尾到头的顺序返回一个ArrayList。
 */
public class Test3 {
    public static ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        Stack<Integer> vector = new Stack<>();
        while (listNode!=null){
            vector.add(listNode.val);
            listNode = listNode.next;
        }
        ArrayList<Integer> list = new ArrayList<>();
        while (!vector.empty()){
            list.add(vector.pop());
        }
        return list;
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
        ArrayList list = printListFromTailToHead(home);
        System.out.println(list.toString());
    }
}