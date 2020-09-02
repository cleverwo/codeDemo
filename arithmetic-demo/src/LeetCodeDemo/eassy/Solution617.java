package LeetCodeDemo.eassy;


import _modal.TreeNode;
import baseDemo.tree.TreeLevel;
import baseDemo.tree.TreeOrder;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @Auther: 10413
 * @Date: 2020/8/24 21:32
 * @Description: 合并二叉树
 * 指针条件判断 二叉树的非递归遍历问题
 * <p>
 * 答案： 递归先序；迭代法
 */
public class Solution617 {

    /**
     * 遍历二叉树的问题：
     *
     * @param t1
     * @param t2
     * @return
     */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }
        TreeNode p = t1, q = t2;
        Stack<TreeNode[]> stack = new Stack<>();
        stack.push(new TreeNode[]{p, q});
        while (!stack.isEmpty()) {
            TreeNode[] t = stack.pop();
            if (t[0] == null || t[1] == null) {
                continue;
            }
            t[0].val += t[1].val;
            if (t[0].left == null) {
                t[0].left = t[1].left;
            } else {
                stack.push(new TreeNode[]{t[0].left, t[1].left});
            }
            if (t[0].right == null) {
                t[0].right = t[1].right;
            } else {
                stack.push(new TreeNode[]{t[0].right, t[1].right});
            }
        }
        return t1;
    }

    public TreeNode mergeTrees2(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        t1.val += t2.val;
        t1.left = mergeTrees2(t1.left, t2.left);
        t1.right = mergeTrees2(t1.right, t2.right);
        return t1;
    }

    public static void main(String[] args) {
        int[] t1p = {1, 3, 5, 2};
        int[] t1i = {5, 3, 1, 2};
        int[] t2p = {2, 1, 4, 3, 7};
        int[] t2i = {1, 4, 2, 3, 7};

        TreeNode t1 = TreeOrder.preInConstructor(t1p, t1i);
        TreeNode t2 = TreeOrder.preInConstructor(t2p, t2i);

        TreeNode t = new Solution617().mergeTrees(t1, t2);

        TreeLevel.levelOrder(t);
    }

}
