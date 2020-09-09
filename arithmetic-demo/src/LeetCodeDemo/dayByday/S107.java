package LeetCodeDemo.dayByday;

import _modal.TreeNode;

import java.util.*;

/**
 * @Auther: 10413
 * @Date: 2020/9/6 10:33
 * @Description:
 * 107. 二叉树的层次遍历 II
 */
public class S107 {


    // 队列，层次遍历
    // 9-6
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();

        List<List<Integer>> list = new ArrayList<>();
        if (root == null){
            return list;
        }
        TreeNode p = root;
        queue.offer(p);
        while (!queue.isEmpty()){
            int size = queue.size();
            List<Integer> curr = new ArrayList<>();
            for (int i=0;i<size;i++){
                TreeNode t = queue.poll();
                if (t.left !=null){
                    queue.offer(t.left);
                }
                if (t.right !=null){
                    queue.offer(t.right);
                }
                curr.add(t.val);
            }
            list.add(curr);
        }
        Collections.reverse(list);
        return list;
    }
}
