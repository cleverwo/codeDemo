package baseDemo.map;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Auther: 10413
 * @Date: 2020/2/20 16:54
 * @Description:
 * 深度优先遍历算法
 */
public class DFS {

    //深度优先求深度
    public int TreeDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        int leftDepth = TreeDepth(root.lchild);
        int rightDepth = TreeDepth(root.rchild);
        int result = 1 + ((leftDepth > rightDepth)?leftDepth:rightDepth);
        return result;
    }

    //深度优先遍历 非递归
    public void depthFirstSearch(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode node;
        while (!stack.isEmpty()) {
            node = stack.pop();
            System.out.println(node.val);;//遍历根结点
            if (node.rchild!=null) {
                stack.push(node.rchild);  //先将右子树压栈
            }
            if (node.lchild!=null) {
                stack.push(node.lchild);  //再将左子树压栈
            }
        }
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
                if(node.lchild != null){
                    queue.add(node.lchild);}
                if(node.rchild != null){
                    queue.add(node.rchild);
                }
                size--;//当size==0时说明同一层的元素遍历完成
            }
            high++;
        }
        return high;
    }
}
