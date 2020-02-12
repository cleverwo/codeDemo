package baseDemo.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: 10413
 * @Date: 2020/1/27 11:32
 * @Description:
 */
public class TreeOrder {

    // 先序递归
    public static void preOrder(TreeNode t,List<Integer> list){
        if (t!=null){
            list.add(t.val);
            preOrder(t.lchild,list);
            preOrder(t.rchild,list);
        }
    }
    //中序遍历 递归算法
    public static void inOrder(TreeNode t,List<Integer> list){
        if (t!=null){
            inOrder(t.lchild,list);
            list.add(t.val);  
            inOrder(t.rchild,list);
        }
    }

    //后序遍历 递归算法
    public static void postOrder(TreeNode t,List<Integer> list){
        if(t!=null){
            postOrder(t.lchild,list);
            postOrder(t.rchild,list);
            list.add(t.val);
        }
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        TreeNode treeNode = new TreeNode(1);
        treeNode.lchild = new TreeNode(2);
        treeNode.lchild.lchild = new TreeNode(4);
        treeNode.lchild.lchild.rchild = new TreeNode(7);
        treeNode.rchild = new TreeNode(3);
        treeNode.rchild.lchild = new TreeNode(5);
        treeNode.rchild.rchild = new TreeNode(6);
        treeNode.rchild.rchild.lchild = new TreeNode(8);

        postOrder(treeNode,list);
        System.out.println(list.toString());
    }
}
