package OfferDemo;


import _modal.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Auther: 10413
 * @Date: 2020/2/15 23:31
 * @Description:
 * 59,按之字形打印二叉树
 * 请实现一个函数按照之字形打印二叉树，即第一行按照从左到右的顺序打印，
 * 第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，其他行以此类推。
 */
public class Test59 {

    /**
     * 思路：
     * 用层次遍历
     * @param pRoot
     * @return
     */
    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
        if (pRoot == null){
            return lists;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(pRoot);
        boolean rev = true;
        while (!queue.isEmpty()){
            int size = queue.size();
            ArrayList<Integer> list = new ArrayList<>();
            for (int i=0;i<size;i++){
                TreeNode p = queue.poll();
                if (p==null){
                    continue;
                }
                if (rev){
                    list.add(p.val);
                }else{
                    list.add(0,p.val);
                }
                queue.offer(p.left);
                queue.offer(p.right);
            }
            if (list.size()!=0){
                lists.add(list);
            }
            rev = !rev;
        }
        return lists;
    }

    /**
     * 答案： bfs
     * 1，主要的方法与BFS写法没什么区别
     * 2，BFS里是每次只取一个，而我们这里先得到队列长度size，这个size就是这一层的节点个数，
     * 然后通过for循环去poll出这size个节点，这里和按行取值二叉树返回ArrayList<ArrayList<Integer>>这种题型的解法一样，
     * 之字形取值的核心思路就是通过两个方法：
     * list.add(T): 按照索引顺序从小到大依次添加
     * list.add(index, T): 将元素插入index位置，index索引后的元素依次后移,这就完成了每一行元素的倒序，
     * 或者使用Collection.reverse()方法倒序也可以
     */
    public ArrayList<ArrayList<Integer> > Print1(TreeNode pRoot) {
        LinkedList<TreeNode> q = new LinkedList<>();
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        boolean rev = true;
        q.add(pRoot);
        while(!q.isEmpty()){
            int size = q.size();
            ArrayList<Integer> list = new ArrayList<>();
            for(int i=0; i<size; i++){
                TreeNode node = q.poll();
                if(node == null){continue;}
                if(rev){
                    list.add(node.val);
                }else{
                    list.add(0, node.val);
                }
                q.offer(node.left);
                q.offer(node.right);
            }
            if(list.size()!=0){res.add(list);}
            rev=!rev;
        }
        return res;
    }

}
