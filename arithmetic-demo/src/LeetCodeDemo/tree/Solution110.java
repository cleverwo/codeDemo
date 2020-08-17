package LeetCodeDemo.tree;

import _modal.TreeNode;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

/**
 * @Auther: 10413
 * @Date: 2020/8/17 23:12
 * @Description:
 * 简单题目： 判断平衡二叉树
 */
public class Solution110 {

    // 求二叉树的深度
    public static int getDeep(TreeNode root){
        if (root == null){
            return 0;
        }
        return Math.max(getDeep(root.lchild),getDeep(root.rchild)) +1;
    }
}
