package LeetCodeDemo.eassy;

import LeetCodeDemo.link.Solution2;
import _modal.TreeNode;
import baseDemo.tree.TreeLevel;
import baseDemo.tree.TreeOrder;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Auther: 10413
 * @Date: 2020/8/25 12:14
 * @Description:
 * 反转二叉树
 */
public class Solution226 {
    public TreeNode invertTree(TreeNode root) {
        if(root == null){
            return root;
        }
        TreeNode right = invertTree(root.right);
        TreeNode left = invertTree(root.left);
        root.left = right;
        root.right = left;
        return root;
    }

    public TreeNode invertTree2(TreeNode root){
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null){
            return null;
        }
        queue.offer(root);
        while (!queue.isEmpty()){
            TreeNode t = queue.poll();
            TreeNode right = t.right;
            if (right!=null){
                queue.offer(right);
            }
            TreeNode left = t.left;
            if (left!=null){
                queue.offer(left);
            }
            t.right = t.left;
            t.left = right;
        }
        return root;
    }

    public static void main(String[] args) {
        Solution226 solution = new Solution226();
        int[] t1p = {1, 3, 5, 2};
        int[] t1i = {5, 3, 1, 2};
        int[] t2p = {2, 1, 4, 3, 7};
        int[] t2i = {1, 4, 2, 3, 7};

        TreeNode t1 = TreeOrder.preInConstructor(t1p, t1i);
        TreeNode t2 = TreeOrder.preInConstructor(t2p, t2i);

        TreeNode t11 = solution.invertTree2(t1);
        TreeNode t12 = solution.invertTree(t2);

        TreeLevel.levelOrder(t11);
        System.out.println();
        TreeLevel.levelOrder(t12);

    }
}
