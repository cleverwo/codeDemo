package LeetCodeDemo.eassy.tree;

import _modal.TreeNode;

/**
 * @Auther: 10413
 * @Date: 2020/8/17 23:12
 * @Description: 简单题目： 判断平衡二叉树
 */
public class Solution110 {

    // 求二叉树的深度
    public static int getDeep(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(getDeep(root.left), getDeep(root.right)) + 1;
    }

    //这个ReturnNode是参考我描述的递归套路的第二步：思考返回值是什么
    //一棵树是BST等价于它的左、右俩子树都是BST且俩子树高度差不超过1
    //因此我认为返回值应该包含当前树是否是BST和当前树的高度这两个信息
    private class ReturnNode {
        boolean isB;
        int depth;

        public ReturnNode(int depth, boolean isB) {
            this.isB = isB;
            this.depth = depth;
        }
    }

    //主函数
    public boolean isBalanced(TreeNode root) {
        return isBST(root).isB;
    }

    //参考递归套路的第三部：描述单次执行过程是什么样的
    //这里的单次执行过程具体如下：
    //是否终止?->没终止的话，判断是否满足不平衡的三个条件->返回值
    public ReturnNode isBST(TreeNode root) {
        if (root == null) {
            return new ReturnNode(0, true);
        }
        //不平衡的情况有3种：左树不平衡、右树不平衡、左树和右树差的绝对值大于1
        ReturnNode left = isBST(root.left);
        ReturnNode right = isBST(root.right);
        if (left.isB == false || right.isB == false) {
            return new ReturnNode(0, false);
        }
        if (Math.abs(left.depth - right.depth) > 1) {
            return new ReturnNode(0, false);
        }
        //不满足上面3种情况，说明平衡了，树的深度为左右俩子树最大深度+1
        return new ReturnNode(Math.max(left.depth, right.depth) + 1, true);
    }


    // 答案：
    public boolean isBalanced1(TreeNode root) {
        if (root == null) {
            return true;
        }
        // 深度小于1，左右为平衡
        return Math.abs(height(root.left) - height(root.right)) <= 1
                && isBalanced(root.left)
                && isBalanced(root.right);
    }

    public int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(height(root.left), height(root.right)) + 1;
    }

}
