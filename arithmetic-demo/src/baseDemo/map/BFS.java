package baseDemo.map;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Auther: 10413
 * @Date: 2020/3/17 14:40
 * @Description:
 * 广度优先遍历
 */
public class BFS {

    /**
     * BFS实现：
     * 数据结构：队列
     * 父节点入队，父节点出队列，先左子节点入队，后右子节点入队。递归遍历全部节点即可
     */
    //广度优先遍历
    public void breadthFirstSearch(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode node;
        while (!queue.isEmpty()) {
            node = queue.poll();
            //遍历根结点
            System.out.println(node.val);
            if (node.lchild!=null) {
                queue.offer(node.lchild);  //先将左子树入队
            }
            if (node.rchild!=null) {
                queue.offer(node.rchild);  //再将右子树入队
            }
        }
    }
    public static void main(String[] args) {
        BFS bfs = new BFS();
        char[] tree = {'A', 'B', 'D', '#', '#', 'E', '#', '#', 'C', 'F','#', '#', 'G', '#', '#'};
        TreeNode t = null;
        t = bfs.preOrderConstructor(t,tree);
        bfs.breadthFirstSearch(t);
    }

    //先序遍历构建二叉树
    int index = 0;
    public TreeNode preOrderConstructor(TreeNode t,char[] data) {
        char e = data[index++];
        if (e == '#') {
            t = null;
        } else {
            t = new TreeNode(e);
            t.lchild = preOrderConstructor(t.lchild,data);  //递归构建左子树
            t.rchild = preOrderConstructor(t.rchild,data);  //递归构建右子树
        }
        return t;
    }

}

