package OfferDemo.tree;


import _modal.TreeNode;

import java.util.ArrayList;

/**
 * @Auther: 10413
 * @Date: 2020/2/16 11:03
 * @Description:
 * 62,二叉搜索树的第k个结点
 * 给定一棵二叉搜索树，请找出其中的第k小的结点。
 * 例如， （5，3，7，2，4，6，8）    中，按结点数值大小顺序第三小结点的值为4。
 */
public class Test62 {

    /**
     * 思路：
     * 二叉搜索树，左《根《右
     * 找第k小的可以
     * 中序遍历 为升序，在找第k个结点
     */
    ArrayList<TreeNode> list = new ArrayList<>();
    TreeNode KthNode(TreeNode pRoot, int k){
        if (pRoot==null|k<=0){
            return null;
        }
        InOrder(pRoot);
        int length = list.size();
        if (k>length){
            return null;
        }else {
            return list.get(k-1);
        }
    }
    public void InOrder(TreeNode t){
        if (t!=null){
            InOrder(t.left);
            list.add(t);
            InOrder(t.right);
        }
    }

}
