package baseDemo.tree;

import _modal.TreeNode;
import java.util.*;

/**
 * @Auther: 10413
 * @Date: 2020/1/27 11:32
 * @Description:
 */
public class TreeOrder {

    // 先序递归 根-左-右
    public static void preOrder(TreeNode t, List<Integer> list) {
        if (t != null) {
            list.add(t.val);
            preOrder(t.left, list);
            preOrder(t.right, list);
        }
    }

    //先序遍历二叉树 非递归
    public static void preOrder2(TreeNode t) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = t;
        while (p != null || !stack.isEmpty()) {
            while (p != null) {
                System.out.print(p.val + " ");
                stack.push(p);
                p = p.left;
            }
            if (!stack.isEmpty()) {
                p = stack.pop();
                p = p.right;
            }
        }
    }

    //中序遍历 递归算法 左-根-右
    public static void inOrder(TreeNode t, List<Integer> list) {
        if (t != null) {
            inOrder(t.left, list);
            list.add(t.val);
            inOrder(t.right, list);
        }
    }

    //后序遍历 递归算法 左-右-根
    public static void postOrder(TreeNode t, List<Integer> list) {
        if (t != null) {
            postOrder(t.left, list);
            postOrder(t.right, list);
            list.add(t.val);
        }
    }

    //先序遍历构建二叉树
    public static void preOrderConstructor(TreeNode t, int[] data, int i) {
        int e = data[i++];
        if (e == -1) {
            t = null;
        } else {
            t = new TreeNode(e);
            preOrderConstructor(t.left, data, i);  //递归构建左子树
            preOrderConstructor(t.right, data, i);  //递归构建右子树
        }
    }

    // 先序中序遍历构建二叉树 元素不可重复
    public static TreeNode preInConstructor(int[] pre, int[] in) {
        int preLen = pre.length;
        int inLen = in.length;

        if (preLen != inLen) {
            throw new RuntimeException("异常");
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inLen; i++) {
            map.put(in[i], i);
        }

        return buildTree(pre, 0, preLen - 1, in, 0, inLen - 1, map);
    }
    private static TreeNode buildTree(
            int[] pre, int preLeft, int preRight, int[] in, int inLeft, int inRight, Map<Integer, Integer> map) {
        if (preLeft > preRight || inLeft > inRight) {
            return null;
        }
        int rootVal = pre[preLeft];
        TreeNode t = new TreeNode(rootVal);
        int pIndex = map.get(rootVal);

        t.left = buildTree(
                pre, preLeft + 1, pIndex - inLeft + preLeft,
                in, inLeft, pIndex - 1, map);
        t.right = buildTree(pre,pIndex-inLeft+preLeft +1,preRight,
                in,pIndex+1,inRight,map);
        return t;
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
        treeNode.left.left = new TreeNode(4);
        treeNode.left.left.right = new TreeNode(7);
        treeNode.right = new TreeNode(3);
        treeNode.right.left = new TreeNode(5);
        treeNode.right.right = new TreeNode(6);
        treeNode.right.right.left = new TreeNode(8);

        preOrder(treeNode, list);
        System.out.println(list.toString());
        preOrder2(treeNode);
    }
}
