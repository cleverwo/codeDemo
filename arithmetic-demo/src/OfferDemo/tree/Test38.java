package OfferDemo.tree;

import OfferDemo.node.TreeNode;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Auther: 10413
 * @Date: 2020/2/20 16:51
 * @Description:
 * 38,二叉树的深度
 * 输入一棵二叉树，求该树的深度。
 * 从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度。
 */
public class Test38 {

    /**
     * 思路：
     * 深度优先遍历算法 DFS
     * @param root
     * @return
     */
    public int TreeDepth(TreeNode root) {
        if(root==null){
            return 0;
        }
        int left = TreeDepth(root.left);
        int right = TreeDepth(root.right);
        int depth = 1+( left>right?left:right);
        return depth;
    }

    /**
     * 层次遍历求深度
     */
    public int TreeDepth1(TreeNode root){
        if (root==null){
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode p = root;
        //depth 是深度，size用于控制队列中同一层的节点没有出队时，depth不能++
        int depth = 0,size;
        queue.offer(p);
        while (!queue.isEmpty()){
            size = queue.size();
            while (size!=0){
                p = queue.poll();
                if (p.left!=null){
                    queue.offer(p.left);
                }
                if (p.right!=null){
                    queue.offer(p.right);
                }
                size--;
            }
           depth++;
        }
        return depth;
    }
}
