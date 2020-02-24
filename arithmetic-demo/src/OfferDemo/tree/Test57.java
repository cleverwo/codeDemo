package OfferDemo.tree;

import OfferDemo.node.TreeLinkNode;
import com.sun.org.apache.xpath.internal.WhitespaceStrippingElementMatcher;
import jdk.nashorn.internal.ir.WhileNode;

import java.util.ArrayList;

/**
 * @Auther: 10413
 * @Date: 2020/2/15 17:33
 * @Description:
 * 57，二叉树的下一个节点
 * 给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。
 * 注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
 */
public class Test57 {

    /**
     * 思路：
     * 给定结点不一定是根节点，但找他的中序遍历的下一个结点
     * 优先查他的右孩子的左孩子，如果右孩子中没有左孩子了
     * 在查他右孩子的根，如果他没有右孩子
     * 在查他的父节点，如果他是父节点的左孩子，那就是父节点，如果他是父节点的右孩子，则找他父节点的父节点，
     * 他父节点是父节点的父节点的左孩子，则继续找，不是返回null
     *
     * 先找到父节点，对父节点递归求中序遍历存到list中，
     * 对list遍历找到phode，返回phode的后一个节点
     * 这个复杂度太大了，试试第一种想法
     * @param pNode
     * @return
     */
    ArrayList<TreeLinkNode> list = new ArrayList<>();
    public TreeLinkNode GetNext(TreeLinkNode pNode){
        if (pNode == null){
            return null;
        }
        //找父节点
        TreeLinkNode head = null;
        while (pNode.next !=null){
            head = pNode.next;
        }
        //对head进行中序遍历保存到list中
        InOrder(head);
        //遍历list 找后面的节点
        for(int i=0;i<list.size()-1;i++){
            if (list.get(i)==pNode){
                return list.get(i+1);
            }
        }
        return null;
    }
    public void InOrder(TreeLinkNode head){
        if (head!=null){
            InOrder(head.left);
            list.add(head);
            InOrder(head.right);
        }
    }

    /**
     * 优先查他的右孩子的左孩子，如果右孩子中没有左孩子了
     * 在查他右孩子的根，如果他没有右孩子
     * 在查他的父节点，如果他是父节点的左孩子，那就是父节点，如果他是父节点的右孩子，则找他父节点的父节点，
     * 他父节点是父节点的父节点的左孩子，则继续找，不是返回null
     */
    public TreeLinkNode GetNext1(TreeLinkNode pNode){
        if (pNode == null){
            return null;
        }
        if (pNode.right!=null){
            TreeLinkNode left = pNode.right.left;
            if (left == null){
                return pNode.right;
            }
            while (left.left!=null){
                left = left.left;
            }
            return left;
        }
        //没有右子树，找父节点
        TreeLinkNode father = pNode.next;
        if (father==null){
            return null;
        }
        // father的左子树就是pNode
        if (father.left == pNode){
            return father;
        }else{
            TreeLinkNode ffather = father.next;
            while (ffather!=null&&ffather.right == father){
                father=ffather;
                ffather = ffather.next;
            }
            return ffather;
        }
    }
}
