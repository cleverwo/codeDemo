package LeetCodeDemo.eassy;

import _modal.TreeNode;
import baseDemo.tree.TreeOrder;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: 10413
 * @Date: 2020/8/25 21:31
 * @Description:
 */
public class S538 {
    List<Integer> list = new ArrayList<>();
    public TreeNode convertBST(TreeNode root) {
        inOrder(root);
        int len = list.size();
        for (int i=len-2;i>=0;i--){
            list.set(i,list.get(i)+list.get(i+1));
        }
        inSetOrder(root);
        return root;
    }
    public void inOrder(TreeNode t){
        if (t != null) {
            inOrder(t.left);
            list.add(t.val);
            inOrder(t.right);
        }
    }
    public void inSetOrder(TreeNode t){
        if (t!=null){
            inSetOrder(t.left);
            t.val = list.get(0);
            list.remove(0);
            inSetOrder(t.right);
        }
    }

    // 递归
    private int sum = 0;
    public TreeNode convertBST2(TreeNode root) {
        if (root != null) {
            convertBST2(root.right);
            sum += root.val;
            root.val = sum;
            convertBST2(root.left);
        }
        return root;
    }

    //线索二叉树
    // 将 二叉树 root 变为线索二叉树
    private TreeNode getSuccessor(TreeNode node) {
        TreeNode succ = node.right;
        while (succ.left != null && succ.left != node) {
            succ = succ.left;
        }
        return succ;
    }

    public TreeNode convertBST3(TreeNode root) {
        int sum = 0;
        TreeNode node = root;

        while (node != null) {
            /*
             * If there is no right subtree, then we can visit this node and
             * continue traversing left.
             */
            if (node.right == null) {
                sum += node.val;
                node.val = sum;
                node = node.left;
            }
            /*
             * If there is a right subtree, then there is at least one node that
             * has a greater value than the current one. therefore, we must
             * traverse that subtree first.
             */
            else {
                TreeNode succ = getSuccessor(node);
                /*
                 * If the left subtree is null, then we have never been here before.
                 */
                if (succ.left == null) {
                    succ.left = node;
                    node = node.right;
                }
                /*
                 * If there is a left subtree, it is a link that we created on a
                 * previous pass, so we should unlink it and visit this node.
                 */
                else {
                    succ.left = null;
                    sum += node.val;
                    node.val = sum;
                    node = node.left;
                }
            }
        }

        return root;
    }

    public static void main(String[] args) {
        S538 s = new S538();
        int[] t1p = {5,2,13};
        int[] t1i = {2,5,13};
        TreeNode root = TreeOrder.preInConstructor(t1p, t1i);
        TreeNode t = s.convertBST(root);
        List<Integer> list1 = new ArrayList<>();
        TreeOrder.preOrder(t,list1);
        System.out.println(list1.toString());
    }
}
