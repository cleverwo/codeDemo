package OfferDemo.tree;


import _modal.TreeNode;

import java.util.ArrayList;

/**
 * @Auther: 10413
 * @Date: 2020/2/16 22:56
 * @Description:
 * 58,对称二叉树
 * 请实现一个函数，用来判断一颗二叉树是不是对称的。
 * 注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
 */
public class Test58 {

    /**
     * 思路：
     * 中序遍历是对称的
     * 错误：
     * 碰到值一样的二叉树就完了，如{5,5,5,5,#,#,5,5,#,5}
     * @param pRoot
     * @return
     */
    ArrayList<Integer> list = new ArrayList<>();
    boolean isSymmetrical(TreeNode pRoot) {
        if(pRoot == null){
            return false;
        }
        InOrder(pRoot);
        for (int i=0,j=list.size()-1;i<=j;i++,j--){
            if (list.get(i)!=list.get(j)){
                return false;
            }
        }
        return true;
    }
    public void InOrder(TreeNode t){
        if (t!=null){
            InOrder(t.left);
            list.add(t.val);
            InOrder(t.right);
        }
    }


    /**
     * 答案：
     * 就是按题意先画一棵“大”一点的对称二叉树，然后按树的一层一层比较一下，看看怎么算是满足对称的二叉树，思路就有了。
     */
    public boolean jude(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return true;
        } else if (node1 == null || node2 == null) {
            return false;
        }

        if (node1.val != node2.val) {
            return false;
        } else {
            return jude(node1.left, node2.right) && jude(node1.right, node2.left);
        }
    }

    public boolean isSymmetrical1(TreeNode pRoot) {
        return pRoot==null || jude(pRoot.left, pRoot.right);
    }
}
