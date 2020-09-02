package OfferDemo;


import _modal.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Auther: 10413
 * @Date: 2020/2/15 20:38
 * @Description:
 * 22.从上往下打印二叉树
 * 从上往下打印出二叉树的每个节点，同层节点从左至右打印。
 */
public class Test22 {

    /**
     * 二叉数的层次遍历
     * @param root
     * @return
     */
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        //前提条件
        if(root == null){
            return list;
        }
        //初始化队列
        Queue<TreeNode> queue = new LinkedList<>();
        // 根节点入队
        queue.offer(root);
        //队列不为空则继续遍历
        while (!queue.isEmpty()){
            TreeNode b = queue.poll();
            if (b.left!=null){
                queue.offer(b.left);
            }
            if (b.right!=null){
                queue.offer(b.right);
            }
            list.add(b.val);
        }
        return list;

    }
}
