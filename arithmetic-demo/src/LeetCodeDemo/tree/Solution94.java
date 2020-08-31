package LeetCodeDemo.tree;


import _modal.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Auther: 10413
 * @Date: 2020/3/20 16:47
 * @Description:
 * 94. 二叉树的中序遍历
 */
public class Solution94 {

    /**
     * 中序遍历二叉树 递归实现
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root==null){
            return list;
        }
        list = inorder(root,list);
        return list;
    }
    public List<Integer> inorder(TreeNode t,List<Integer> list){
        if (t!=null){
            list = inorder(t.left,list);
            list.add(t.val);
            list = inorder(t.right,list);
        }
        return list;
    }

    //非递归
    public List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root==null){
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode t = root;
        while (t!=null||!stack.isEmpty()){
            while (t!=null){
                stack.push(t);
                t=t.left;
            }
            t = stack.pop();
            list.add(t.val);
            t = t.right;
        }
        return list;
    }

}
