package LeetCodeDemo.link;

import _modal.ListNode;

import java.util.*;

/**
 * @Auther: 10413
 * @Date: 2020/3/18 16:48
 * @Description: 23. 合并K个排序链表
 */
public class Solution23 {

    /**
     * 合并k个排序链表 是 21题的变形
     * 想用21的合并，再用二路归并对数组进行排序，但2路归并没掌握，不会写。。
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        if (lists.length == 1) {
            return lists[0];
        }
        ListNode t = lists[0];
        for (int i = 1; i < lists.length; i++) {
            t = merge(t, lists[i]);
        }
        return t;
    }

    public ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val < l2.val) {
            l1.next = merge(l1.next, l2);
            return l1;
        } else {
            l2.next = merge(l1, l2.next);
            return l2;
        }
    }

    /**
     * 答案： 暴力求解，遍历所有链表存到一个数组中，对数组排序后在拷贝到一个链表中。
     * 这个显然是最不好的，但我提交的时候发现这个比我自己写的还要快，占用空间也少，，？？？黑人问号
     * 时间复杂度：O(N\log N)
     * 空间复杂度：O(N)。
     */
    public ListNode mergeKLists1(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        if (lists.length == 1) {
            return lists[0];
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < lists.length; i++) {
            ListNode p = lists[i];
            while (p != null) {
                list.add(p.val);
                p = p.next;
            }
        }
        Collections.sort(list);
        ListNode root = new ListNode(0);
        ListNode q = root;
        for (int i = 0; i < list.size(); i++) {
            q.next = new ListNode(list.get(i));
            q = q.next;
        }
        return root.next;
    }

    /**
     *逐一比较，用优先队列
     */
    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        if (lists.length == 1) {
            return lists[0];
        }
        //优先队列，内部排序，从小到大
        Queue<ListNode> queue = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                if (o1.val<o2.val){
                    return -1;
                }else if (o1.val==o2.val){
                    return 0;
                }else{
                    return 1;
                }
            }
        });
        ListNode root = new ListNode(0);
        ListNode p = root;
        //所有链表头节点入队
        for (ListNode node : lists){
            if (node !=null) queue.add(node);
        }
        //队列判空，出队头
        while (!queue.isEmpty()){
            //小的出队，他的下一个结点入队比较
            p.next = queue.poll();
            p = p.next;
            if (p.next!=null) queue.add(p.next);
        }
        return root.next;
    }

    /**
     * 分而治之，2路归并
     */
    public ListNode mergeKLists3(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        if (lists.length == 1) {
            return lists[0];
        }
        return merge(lists,0,lists.length-1);
    }
    private ListNode merge(ListNode[] lists, int left, int right) {
        if (left==right) return lists[left];
        int mid = left+ (right-left)/2;
        ListNode l1 = merge(lists,left,mid);
        ListNode l2 = merge(lists,mid+1,right);
        return merge(l1,l2);
    }



    public static void main(String[] args) {
        Solution23 s = new Solution23();
        ListNode[] list = new ListNode[3];
        list[0] = Solution2.getNewLink(new int[]{1, 4, 5});
        list[1] = Solution2.getNewLink(new int[]{1, 3, 4});
        list[2] = Solution2.getNewLink(new int[]{2, 6});
        ListNode result = s.mergeKLists1(list);
        Solution2.printLink(result);
    }
}
