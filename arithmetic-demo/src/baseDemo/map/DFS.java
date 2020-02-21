package baseDemo.map;

import OfferDemo.node.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Auther: 10413
 * @Date: 2020/2/20 16:54
 * @Description:
 * 深度优先遍历算法
 */
public class DFS {

    public int TreeDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        int leftDepth = TreeDepth(root.left);
        int rightDepth = TreeDepth(root.right);
        int result = 1 + ((leftDepth > rightDepth)?leftDepth:rightDepth);
        return result;
    }


    /**
     * 层次遍历求深度
     * @param root
     * @return
     */
    public int TreeDepth2(TreeNode root) {
        if(root == null){
            return 0;}
        Queue<TreeNode> queue = new LinkedList();
        queue.offer(root);
        int high = 0;
        //用size控制high增长的次数和时机(同一层的元素没有完全退出队列的时候high不可以增加)
        int size;
        TreeNode node;
        while(!queue.isEmpty()){
            //队列长度
            size = queue.size();
            while(size != 0){
                node = queue.poll();
                if(node.left != null){
                    queue.add(node.left);}
                if(node.right != null){
                    queue.add(node.right);
                }
                size--;//当size==0时说明同一层的元素遍历完成
            }
            high++;
        }
        return high;
    }
}
