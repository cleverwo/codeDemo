package OfferDemo;


import _modal.ListDequeNode;
import _modal.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: 10413
 * @Date: 2020/2/17 11:05
 * @Description:
 * 26, 二叉搜索树与双向链表
 *
 * 二叉搜索树 变 线索二叉树
 */
public class Test26 {

    public ListDequeNode treeToDoublyList(ListDequeNode root){
        List<ListDequeNode> list = new ArrayList<>();
        // 中序遍历
        inOrder(root,list);
        if (list.size() == 0){
            return null;
        }
        // 树变链表
        for (int i=0;i<list.size();i++){
            ListDequeNode p = list.get(i);
            if (i == list.size()-1){
                p.right = list.get(0);
            }else{
                p.right = list.get(i+1);
            }
            if (i==0){
                p.left = list.get(list.size()-1);
            }else{
                p.left = list.get(i-1);
            }
        }
        return list.get(0);
    }
    public void inOrder(ListDequeNode root,List<ListDequeNode> list){
        if (root!=null){
            inOrder(root.left,list);
            list.add(root);
            inOrder(root.right,list);
        }
    }

    /**
     * 答案1：
     * 没有全部看懂，
     * 大体思路应该是，遍历尾部开始，类型中序遍历的逆运算，
     * 用pre指针指向中序遍历的倒数第一知道第一个节点，root节点指向pre的前一个节点
     * 从而达到指针的修改
     */
    TreeNode pree = null;
    public TreeNode Convert1(TreeNode pRootOfTree) {
        if (pRootOfTree == null) {
            return null;
        }
        Convert1(pRootOfTree.right);
        // 这里递归到最后，pre指针会按 右子树，根节点，左子树的顺序，相当于中序遍历的逆运行。
        if (pree == null)
            pree = pRootOfTree;
        else {
            pRootOfTree.right = pree;
            pree.left = pRootOfTree;
            pree = pRootOfTree;
        }
        Convert1(pRootOfTree.left);
        return pree;
    }

    /**
     * 答案2
     * 在中序遍历的过程中对每个节点的指针进行操作：
     * 完成对二叉排序树中元素从小到大的排序并且对每个遍历到的节点的指针进行修改。
     * 大体思路：
     * 借助一个指针指向每次遍历到的节点，下一次中序遍历到的节点的前驱便是此指针指向的节点，
     * 而此节点的后序是此时遍历到的节点。
     * 注意事项：链表中指向最后一个节点的指针，当使用递归，
     * 且需要用具体变量保存每次递归的结果时，不能把此变量作为递归函数的形参，
     * 因为每次递归返回时此变量都会变为上层递归时此变量的值。
     * 所以这里的变量不作为inOrderConvert()的形式参数，而又由于在两个方法中都用到了此变量，
     * 所以将其定义在了所有方法的外面。
     * <p>
     * 注意事项和 我模拟答案1 所犯的错误一样，递归时，不能将pre作为形参进行传递，
     * 因为他传递的是里面值的引用，不是对象的传递。对象在java的方法中不会传递的。
     * 传递的总是值，一种是只是值的传递，另一种是值的地址的传递，不存在对象传递。
     */
    TreeNode pre,head = null;
    public TreeNode Convert2(TreeNode root) {
        if (root ==null) return null;
        inOrderConvert(root);
        head.left = pre;
        pre.right = head;
        return head;
    }
    public void inOrderConvert(TreeNode curr) {
        if (curr == null) {
            return;
        }
        inOrderConvert(curr.left);
        if (pre !=null) {
            pre.right = curr;
        }else{
            head = curr;
        }
        curr.left = pre;
        pre = curr;
        inOrderConvert(curr.right);
    }

    public static void main(String[] args) {
        Test26 tp = new Test26();
        ListDequeNode t = new ListDequeNode(4);
        t.left = new ListDequeNode(2);
        t.right = new ListDequeNode(10);
        t.left.left = new ListDequeNode(1);
        t.right.left = new ListDequeNode(7);
        t.right.right = new ListDequeNode(11);
        ListDequeNode end = tp.treeToDoublyList(t);
        System.out.println(end);
    }

}
