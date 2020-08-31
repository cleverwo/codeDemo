package OfferDemo.tree;


import _modal.TreeNode;

/**
 * @Auther: 10413
 * @Date: 2020/2/20 18:13
 * @Description: 39, 平衡二叉树
 * 输入一棵二叉树，判断该二叉树是否是平衡二叉树。
 */
public class Test39 {

    /**
     * 思路：
     * 左右子树的深度差不查过1，成为一个avl树，即平衡因子是-1，0，1
     * |平衡因子|<1
     *
     * @param root
     * @return
     */
    public boolean IsBalanced_Solution(TreeNode root) {
        if (root == null) {
            return true;
        }
        //递归判断平衡因子
        boolean isDepth = depth(root);
        return isDepth;

    }

    public boolean depth(TreeNode root) {
        if (root == null) {
            return true;
        }
        int left = getDepth(root.left);
        int right = getDepth(root.right);
        if (Math.abs(left - right) > 1) {
            return false;
        }
        return depth(root.left) && depth(root.right);
    }

    public int getDepth(TreeNode t) {
        if (t == null) {
            return 0;
        }
        int left = getDepth(t.left);
        int right = getDepth(t.right);
        return 1 + (Math.max(left, right));
    }

    public boolean isBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (root.left != null && root.left.val > root.val) {
            return false;
        }
        if (root.right != null && root.right.val > root.val) {
            return false;
        }
        boolean left = isBST(root.left);
        boolean right = isBST(root.right);
        return left && right;
    }


    /**
     * 答案： 我的做法是先写了求深度的dps在调用查看是不是绝对值小于1
     * 这个直接判断了，修改了深度优先遍历的
     */
    public boolean IsBalanced_Solution1(TreeNode root) {
        return depth1(root) != -1;
    }

    public int depth1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 左子树长度
        int left = depth1(root.left);
        if (left == -1) {
            //如果发现子树不平衡之后就没有必要进行下面的高度的求解了
            return -1;
        }
        //右子树深度
        int right = depth1(root.right);
        if (right == -1) {
            //如果发现子树不平衡之后就没有必要进行下面的高度的求解了
            return -1;
        }
        // 判读是不是avl树
        if (left - right < (-1) || left - right > 1) {
            return -1;
        } else {
            // 只加了一个判断，，返回-1证明|平衡因子|大于1了不满足avl树
            return 1 + (left > right ? left : right);
        }
    }


}
