package LeetCodeDemo.eassy;

import _modal.TreeNode;
import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: 10413
 * @Date: 2020/8/26 11:48
 * @Description:
 * 对称二叉树
 */
public class S101 {
    // 做错了
    List<Integer> list = new ArrayList<>();
    public boolean isSymmetric(TreeNode root) {
        inOrder(root);
        int n = list.size();
        for (int i=0;i*2<list.size();++i){
            if (list.get(i)!= list.get(n-i-1)){
                return false;
            }
        }
        return true;
    }
    public void inOrder(TreeNode t){
        if (t!=null){
            inOrder(t.left);
            list.add(t.val);
            inOrder(t.right);
        }
    }

    // 答案：
    public boolean isSymmetric1(TreeNode root) {
        inOrder(root);
        int n = list.size();
        for (int i=0;i*2<list.size();++i){
            if (list.get(i)!= list.get(n-i-1)){
                return false;
            }
        }
        return true;
    }

}
