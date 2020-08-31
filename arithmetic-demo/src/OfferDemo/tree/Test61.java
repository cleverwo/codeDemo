package OfferDemo.tree;


import _modal.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Auther: 10413
 * @Date: 2020/2/16 00:23
 * @Description: 61, 序列化二叉树
 * 请实现两个函数，分别用来序列化和反序列化二叉树
 * 二叉树的序列化是指：把一棵二叉树按照某种遍历方式的结果以某种格式保存为字符串，
 * 从而使得内存中建立起来的二叉树可以持久保存。
 * 序列化可以基于先序、中序、后序、层序的二叉树遍历方式来进行修改，序列化的结果是一个字符串，
 * 序列化时通过 某种符号表示空节点（#），以 ！ 表示一个结点值的结束（value!）。
 * 二叉树的反序列化是指：根据某种遍历顺序得到的序列化字符串结果str，重构二叉树。
 */
public class Test61 {

    /**
     * 思路：
     * 序列化可以看作层次遍历加上空的字符，反序列化可以看作层次遍历建二叉树
     * 怎么做的序列化后能够先序，中序，后序，层序修改？
     *
     * @param root
     * @return
     */
    ArrayList<Integer> list = new ArrayList<>();

    String Serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode t = queue.poll();
                if (t == null) {
                    list.add(-1);
                }else{
                    list.add(t.val);
                    queue.offer(t.left);
                    queue.offer(t.right);
                }
            }
        }
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == -1) {
                string.append("#");
            } else {
                string.append(list.get(i));
            }
        }
        string.append("!");
        return string.toString();
    }

    TreeNode Deserialize(String str) {
        return null;
    }


    /**
     * 答案：
     * 序列化简单，通过中序，先序，后序遍历即可
     */
    int index = -1;
    /**
     * 分别遍历左节点和右节点，空使用#代替，节点之间，隔开
     * 这实质是先序遍历二叉树
     * 1,2,4,#,#,#,3,5,#,#,6,#,#
     */
    public String Serialize1(TreeNode root) {
        if (root == null) {
            return "#";
        } else {
            return root.val + "," + Serialize1(root.left) + "," + Serialize1(root.right);
        }
    }

    /**
     * 使用index来设置树节点的val值，递归遍历左节点和右节点，如果值是#则表示是空节点，直接返回
     */
    TreeNode Deserialize1(String str) {
        String[] s = str.split(",");//将序列化之后的序列用，分隔符转化为数组
        index++;//索引每次加一
        int len = s.length;
        if (index > len) {
            return null;
        }
        TreeNode treeNode = null;
        if (!s[index].equals("#")) {//不是叶子节点 继续走 是叶子节点出递归
            treeNode = new TreeNode(Integer.parseInt(s[index]));
            treeNode.left = Deserialize(str);
            treeNode.right = Deserialize(str);
        }
        return treeNode;
    }


    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode6 = new TreeNode(6);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode2.left = treeNode4;
        treeNode3.left = treeNode5;
        treeNode3.right = treeNode6;

        Test61 t = new Test61();

        String str = t.Serialize1(treeNode1);
        System.out.println(str);
//        TreeNode treeNode = serializeTree.Deserialize(str);
    }

}
