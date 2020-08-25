package baseDemo.tree;

import _modal.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Auther: 10413
 * @Date: 2020/2/20 18:00
 * @Description:
 * 层次遍历二叉树
 */
public class TreeLevel {

    public static void levelOrder(TreeNode t){
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode p=t;
        queue.offer(p);
        while (!queue.isEmpty()){
            p = queue.poll();
            System.out.print(p.val + " ");
            if (p.left!=null){
                queue.offer(p.left);
            }
            if (p.right!=null) {
                queue.offer(p.right);
            }
        }
    }

    public void levelPrint(TreeOrder t){

    }
}
