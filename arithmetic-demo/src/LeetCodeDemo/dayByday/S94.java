package LeetCodeDemo.dayByday;

import _modal.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: 10413
 * @Date: 2020/9/14 15:58
 * @Description:
 * 二叉树中序遍历
 */
public class S94 {

    List<Integer> list = new ArrayList<>();
    public List<Integer> inorderTraversal(TreeNode root) {
        inorder(root);
        return list;

    }
    public void inorder(TreeNode t){
        if (t!=null){
            inorder(t.left);
            list.add(t.val);
            inorder(t.right);
        }
    }
}
