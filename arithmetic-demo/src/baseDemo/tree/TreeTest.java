package baseDemo.tree;

import LeetCodeDemo.eassy.tree.Solution110;
import _modal.TreeNode;

/**
 * @Auther: 10413
 * @Date: 2020/8/17 23:22
 * @Description:
 */
public class TreeTest {

    //测试树 （）
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
        treeNode.left.left = new TreeNode(4);
        treeNode.left.left.right = new TreeNode(7);
        treeNode.right = new TreeNode(3);
        treeNode.right.left = new TreeNode(5);
        treeNode.right.right = new TreeNode(6);
        treeNode.right.right.left = new TreeNode(8);

        int[] t1p = {1, 3, 5, 2};
        int[] t1i = {5, 3, 1, 2};
        TreeNode t1 = TreeOrder.preInConstructor(t1p, t1i);

        int[] t2p = {2, 1, 4, 3, 7};
        int[] t2i = {1, 4, 2, 3, 7};
        TreeNode t2 = TreeOrder.preInConstructor(t2p, t2i);

        System.out.println(new Solution110().isBalanced1(treeNode));
    }
}
