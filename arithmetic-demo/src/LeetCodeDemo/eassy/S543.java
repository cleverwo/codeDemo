package LeetCodeDemo.eassy;

import _modal.TreeNode;
import baseDemo.tree.TreeOrder;

/**
 * @Auther: 10413
 * @Date: 2020/9/1 09:59
 * 543. 二叉树的直径
 */
public class S543 {

    int max;
    public int diameterOfBinaryTree(TreeNode root) {
        max = 1;
        if (root == null){
            return 0;
        }
        max = deepth(root);
        return max-1;
    }

    public int deepth(TreeNode t){
        if (t == null){
            return 0;
        }
        int left = deepth(t.left);
        int right = deepth(t.right);
        max = Math.max(max,left+right+1);
        return Math.max(left,right)+1;
    }

    public static void main(String[] args) {

    }
}
