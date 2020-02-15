package OfferDemo;

import OfferDemo.node.TreeNode;
import sun.reflect.generics.tree.Tree;

/**
 * @Auther: 10413
 * @Date: 2020/2/15 01:09
 * @Description:
 * 18,二叉数的镜像
 * 操作给定的二叉树，将其变换为源二叉树的镜像。
 */
public class Test18 {
    /**
     * 镜像的操作是
     * root的左子数成为其右子树
     * root的右子树成为其左子树
     * 1.反转root ，先反转她的子孩子，
     * @param root
     */
    public void Mirror(TreeNode root) {
        if (root==null){
            return;
        }
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        Mirror(root.left);
        Mirror(root.right);
    }
    public void reverse(TreeNode t1, TreeNode t2){

    }
}
