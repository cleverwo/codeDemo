package OfferDemo.tree;

import OfferDemo.node.TreeNode;

/**
 * @Auther: 10413
 * @Date: 2020/2/15 00:14
 * @Description:
 * 17,树的子结构
 * 输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
 */
public class Test17 {
    /**
     * 子结构和子树是有区别的，二叉树只有两个字数，所以而子结构是存在很多的。
     */

    /**
     * 顺序遍历查看
     * 首先考虑递归思想
     * @param root1
     * @param root2
     * @return
     */
    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        if(root1==null||root2==null){
            // 根节点为空不是其他树的子结构
            return false;
        }
        //判断root1 == root2 或 root1的左是否=root2，root1的右是否等于root2
        return isSubTreeNode(root1,root2)||isSubTreeNode(root1.left,root2)||isSubTreeNode(root1.right,root2);
    }
    // 子结构不同子树 子结构范围更广是子树必定子结构，是子结构不一定是子树
    public boolean isSubTreeNode(TreeNode t1,TreeNode t2){
        if(t2==null){
            return true;
        }
        if (t1==null){
            return false;
        }
        if (t1.val!=t2.val){
            //t1和t2不等，判断t1的左是否和t2相等，t1的右是否和t2相等
            return isSubTreeNode(t1.left,t2)||isSubTreeNode(t1.right,t2);
        }
        //相同继续判断t1和t2的左右子树
        return isSubTreeNode(t1.left,t2.left)&&isSubTreeNode(t1.right,t2.right);
    }

    /**
     * 判断b是否为a的子树
     * 1. a和b的节点值完全相同，a和b的左子树，右子树的所有节点值也完全相同
     * 2.或者a左孩子和b的节点值完全相同，他们的左子树，右子树的所有节点值也完全相同
     * 3.或者a右孩子和b的节点值完全相同，他们的左子树，右子树的所有节点值也完全相同
     * 即： b为a的子树 有三种情况，b=a ，b是a的左子树，b是a的右子树
     */
    public boolean HasSubTree(TreeNode t1,TreeNode t2){
        if (t1==null||t2==null){
            return false;
        }
        //对应三种情况，有一个为真表示t2是t1的子树
        return isSubTree(t1,t2)||isSubTree(t1.left,t2)||isSubTree(t1.right,t2);
    }
    // 判别t2 是否 t1 的子树，注意结束值，t2为空，t1
    public boolean isSubTree(TreeNode t1,TreeNode t2){
        // t2 为空证明t2遍历完了
        if (t2==null){
            return true;
        }
        // t1 为空证明t1 中没有和t2相同的节点
        if (t1==null){
            return false;
        }
        // t1，t2 值不同证明不是子树
        if (t1.val != t2.val){
            return false;
        }
        // t1 t2 值相同需要在去遍历他们的子数是否相同
        return isSubTree(t1.left,t2.left)&&isSubTree(t1.right,t2.right);
    }
    /**
     * 1.首先需要判断A,B的根节点是否一样。
     * 2.如果不一样，判断A的左孩子和B的根节点是否一样，同理可判断A的右孩子和B的根节点是否一样。依次找下去
     * 如果上述情况都不满足则说明不包含
     * 1.如果找到了A中有值和B中的根节点相同，则比较左右子树是否相同。
     * 2.如果B为空了，则说明包含
     * 3.如果A为空了，则说明不包含
     *
     * 这里是遍历b去和a作比较，所以b遍历完了证明a和b是相同的，a遍历完了证明a遍历完都没有找到和b相同的，所以失败
     */


}
