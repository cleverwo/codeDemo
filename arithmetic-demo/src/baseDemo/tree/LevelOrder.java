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
public class LevelOrder {

    public void levelOrder(TreeNode t){
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode p=t;
        queue.offer(p);
        while (!queue.isEmpty()){
            p = queue.poll();
            System.out.println(p.val);
            if (p.lchild!=null){
                queue.offer(p.lchild);
            }
            if (p.rchild!=null) {
                queue.offer(p.rchild);
            }
        }
    }
}
