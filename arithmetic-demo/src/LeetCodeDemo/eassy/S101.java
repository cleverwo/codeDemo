package LeetCodeDemo.eassy;

import _modal.TreeNode;
import baseDemo.tree.TreeOrder;
import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Auther: 10413
 * @Date: 2020/8/26 11:48
 * @Description: 对称二叉树
 */
public class S101 {
    // 做错了
    List<Integer> list = new ArrayList<>();

    public boolean isSymmetric(TreeNode root) {
        inOrder(root);
        int n = list.size();
        for (int i = 0; i * 2 < list.size(); ++i) {
            if (list.get(i) != list.get(n - i - 1)) {
                return false;
            }
        }
        return true;
    }

    public void inOrder(TreeNode t) {
        if (t != null) {
            inOrder(t.left);
            list.add(t.val);
            inOrder(t.right);
        }
    }

    // 答案：
    public boolean isSymmetric1(TreeNode root) {
        return check(root, root);
    }

    public boolean check(TreeNode l, TreeNode r) {
        if (l == null && r == null) {
            return true;
        }
        if (l== null || r == null || l.val != r.val) {
            return false;
        }
        return check(l.left, r.right) && check(l.right, r.left);
    }

    public boolean check1(TreeNode u, TreeNode v) {
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(u);
        q.offer(v);
        while (!q.isEmpty()) {
            u = q.poll();
            v = q.poll();
            if (u == null && v == null) {
                continue;
            }
            if ((u == null || v == null) || (u.val != v.val)) {
                return false;
            }

            q.offer(u.left);
            q.offer(v.right);

            q.offer(u.right);
            q.offer(v.left);
        }
        return true;
    }

}
