package OfferDemo.tree;

import OfferDemo.node.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Auther: 10413
 * @Date: 2020/2/16 00:16
 * @Description:
 * 60,把二叉树打印成多行
 * 从上到下按层打印二叉树，同一层结点从左至右输出。每一层输出一行。
 */
public class Test60 {

    /**
     * 思路：
     * 这个和59一样不一样的是不用之子走了
     * @param pRoot
     * @return
     */
    ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
        if (pRoot == null){
            return lists;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(pRoot);
        while(!queue.isEmpty()){
            ArrayList<Integer> list = new ArrayList<>();
            int size = queue.size(); //先获取当前队列的长度，即这一层的长度
            for (int i=0;i<size;i++){
                TreeNode p = queue.poll();
                if (p==null){
                    continue;
                }
                list.add(p.val);
                queue.offer(p.left);
                queue.offer(p.right);
            }
            if (list.size()!=0){
                lists.add(list);
            }
        }
        return lists;
    }

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode6 = new TreeNode(6);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode2.left = treeNode4;
        treeNode3.left = treeNode5;
        treeNode3.right = treeNode6;
        Test60 t= new Test60();
        ArrayList<ArrayList<Integer>> list = t.Print(treeNode1);
        System.out.println(list.toString());

    }
}
