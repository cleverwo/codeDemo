package OfferDemo;


import _modal.TreeNode;

import java.util.HashMap;


/**
 * @Auther: 10413
 * @Date: 2020/1/22 00:12
 * @Description: 4.重建二叉树
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
 * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
 */
public class Test4 {
    // 没有用递归 每次节点的插入都是从头开始判断
    public static TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        //map 代替in数组
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < in.length; i++) {
            map.put(in[i], i);
        }
        TreeNode t = new TreeNode(pre[0]);
        TreeNode nowt = t, pret = t;
        int val = 0;
        for (int i = 1; i < pre.length; i++) {
            int mid = pre[i];
            while (nowt != null) {
                pret = nowt;
                if (map.get(mid) < map.get(nowt.val)) {
                    nowt = nowt.left;
                    val = 0;
                } else {
                    nowt = nowt.right;
                    val = 1;
                }
            }
            if (val != 0) {
                pret.right = new TreeNode(mid);
            } else {
                pret.left = new TreeNode(mid);
            }
            nowt = t;
        }
        return t;
    }

    public static void main(String[] args) {
        int[] pre = {1, 2, 3, 4, 5, 6, 7};
        int[] in = {3, 2, 4, 1, 6, 5, 7};
        TreeNode treeNode = reConstructBinaryTree(pre, in);
        InOrder(treeNode);

    }

    // 中序遍历
    public static void InOrder(TreeNode t) {
        if (t != null) {
            InOrder(t.left);
            System.out.print(t.val + " ");
            InOrder(t.right);
        }
    }
}