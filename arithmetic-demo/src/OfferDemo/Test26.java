package OfferDemo;


import _modal.TreeNode;

import java.util.ArrayList;

/**
 * @Auther: 10413
 * @Date: 2020/2/17 11:05
 * @Description: 26, 二叉搜索树与双向链表
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。
 * 要求不能创建任何新的结点，只能调整树中结点指针的指向。
 */
public class Test26 {

    /**
     * 分析：
     * Bst 是 左《根《右，其中序遍历为一个升序数组
     */
    public TreeNode Convert(TreeNode pRootOfTree) {
        if (pRootOfTree == null) {
            return null;
        }
        //先中序遍历二叉树，把结果存起来
        ArrayList<TreeNode> list = new ArrayList<>();
        inOrder(pRootOfTree, list);
        TreeNode newHead = list.get(0);
        //遍历数组，修改指针
        for (int i = 0; i < list.size() - 1; i++) {
            TreeNode pre = list.get(i);
            TreeNode p = list.get(i + 1);
            pre.right = p;
            p.left = pre;
        }
        return newHead;
    }

    public void inOrder(TreeNode t, ArrayList<TreeNode> list) {
        if (t != null) {
            inOrder(t.left, list);
            list.add(t);
            inOrder(t.right, list);
        }
    }

    /**
     * 答案1：
     * 没有全部看懂，
     * 大体思路应该是，遍历尾部开始，类型中序遍历的逆运算，
     * 用pre指针指向中序遍历的倒数第一知道第一个节点，root节点指向pre的前一个节点
     * 从而达到指针的修改
     */
    TreeNode pre = null;

    public TreeNode Convert1(TreeNode pRootOfTree) {
        if (pRootOfTree == null) {
            return null;
        }
        Convert1(pRootOfTree.right);
        // 这里递归到最后，pre指针会按 右子树，根节点，左子树的顺序，相当于中序遍历的逆运行。
        if (pre == null)
            pre = pRootOfTree;
        else {
            pRootOfTree.right = pre;
            pre.left = pRootOfTree;
            pre = pRootOfTree;
        }
        Convert1(pRootOfTree.left);
        return pre;
    }

    /**
     * 模拟答案1
     * 解题有错误，无法处理pre节点位置
     *
     * @param pRootOfTree
     * @return
     */
    public TreeNode Convert1_1(TreeNode pRootOfTree) {
        if (pRootOfTree == null) {
            return null;
        }
        TreeNode pree = null;
        pree = rightConvert(pRootOfTree, pree);
        return pree;
    }

    public TreeNode rightConvert(TreeNode t, TreeNode pre) {
        if (t == null) {
            return null;
        }
        rightConvert(t.right, pre);
        if (pre == null) {
            pre = t;
        } else {
            t.right = pre;
            pre.left = t;
            pre = t;
        }
        rightConvert(t.left, pre);
        return pre;
    }

    /**
     * 答案2
     * 在中序遍历的过程中对每个节点的指针进行操作：
     * 完成对二叉排序树中元素从小到大的排序并且对每个遍历到的节点的指针进行修改。
     * 大体思路：
     * 借助一个指针指向每次遍历到的节点，下一次中序遍历到的节点的前驱便是此指针指向的节点，
     * 而此节点的后序是此时遍历到的节点。
     * 注意事项：链表中指向最后一个节点的指针，当使用递归，
     * 且需要用具体变量保存每次递归的结果时，不能把此变量作为递归函数的形参，
     * 因为每次递归返回时此变量都会变为上层递归时此变量的值。
     * 所以这里的变量不作为inOrderConvert()的形式参数，而又由于在两个方法中都用到了此变量，
     * 所以将其定义在了所有方法的外面。
     * <p>
     * 注意事项和 我模拟答案1 所犯的错误一样，递归时，不能将pre作为形参进行传递，
     * 因为他传递的是里面值的引用，不是对象的传递。对象在java的方法中不会传递的。
     * 传递的总是值，一种是只是值的传递，另一种是值的地址的传递，不存在对象传递。
     */
    TreeNode lastNodeList = null;

    public TreeNode Convert2(TreeNode pRootOfTree) {
        inOrderConvert(pRootOfTree);
        //寻找链表头节点
        while (lastNodeList != null && lastNodeList.left != null) {
            lastNodeList = lastNodeList.left;
        }
        return lastNodeList;
    }
    public void inOrderConvert(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrderConvert(root.left);
        root.left = lastNodeList;
        if (lastNodeList != null) {
            lastNodeList.right = root;
        }
        lastNodeList = root;
        inOrderConvert(root.right);
    }

    public static void main(String[] args) {
        TreeNode t = new TreeNode(4);
        t.left = new TreeNode(2);
        t.right = new TreeNode(10);
        t.left.left = new TreeNode(1);
        t.right.left = new TreeNode(7);
        t.right.right = new TreeNode(11);
        Test26 tp = new Test26();
        TreeNode end = tp.Convert1_1(t);
        System.out.println(end);
    }

}
