package OfferDemo.tree;

import OfferDemo.node.TreeNode;

import javax.swing.*;
import java.util.ArrayList;

/**
 * @Auther: 10413
 * @Date: 2020/2/16 12:19
 * @Description:
 * 24,二叉树中和为某一值的路径
 * 输入一颗二叉树的根节点和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。
 * 路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
 * (注意: 在返回值的list中，数组长度大的数组靠前)
 */
public class Test24 {

    /**
     * 连接深度优先遍历查找路径
     */


    /**
     * 答案1 递归
     * @param root
     * @param target
     * @return
     */
    ArrayList<ArrayList<Integer>> result = new ArrayList<>();
    ArrayList<Integer> list = new ArrayList<>();
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        //为空跳出
        if (root==null){
            return result;
        }
        list.add(root.val);
        target -= root.val;
        if(target == 0 && root.left == null && root.right == null){
            result.add(new ArrayList<Integer>(list));
        }
        //因为在每一次的递归中，我们使用的是相同的result引用，所以其实左右子树递归得到的结果我们不需要关心，
        //可以简写为FindPath(root.left, target)；FindPath(root.right, target)；
        //但是为了大家能够看清楚递归的真相，此处我还是把递归的形式给大家展现了出来。
        ArrayList<ArrayList<Integer>> result1 = FindPath(root.left, target);
        ArrayList<ArrayList<Integer>> result2 = FindPath(root.right, target);
        list.remove(list.size()-1);
        return result;
    }

    /**
     * 模拟答案1
     * @param root
     * @param target
     * @return
     */
    public ArrayList<ArrayList<Integer>> findPath1(TreeNode root,int target){
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (root==null){
            return result;
        }
        ArrayList<Integer> path = new ArrayList<>();
        findO(root, target, result, path);
        return result;
    }
    public void findO(TreeNode t,int target,ArrayList<ArrayList<Integer>> result,ArrayList<Integer> list){
        //当节点为空，return
        if (t == null) {
            return;
        }
        list.add(t.val);
        target -= t.val;
        // 1，当目标值小于0，return
        if(target < 0){
            return;
        }
        // 2，当目标值为0 并且 节点下无其他节点, 保存并返回
        if(target == 0 && t.left == null && t.right == null){
            result.add(list);
            return;
        }
        // 继续遍历左右节点
        // 这里new path是因为左右都会在下次递归path.add(root.val);
        findO(t.left, target, result, new ArrayList<>(list));
        findO(t.right, target, result, new ArrayList<>(list));
    }

    public static void main(String[] args) {
        TreeNode t = new TreeNode(5);
        TreeNode t1 = new TreeNode(2);
        TreeNode t2 = new TreeNode(1);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(1);
        TreeNode t5 = new TreeNode(2);
        TreeNode t6 = new TreeNode(2);
        t.left=t1;
        t.right=t2;
        t1.left=t4;
        t1.right=t3;
        t2.right=t5;
        t5.right=t6;
        Test24 test24 = new Test24();
        ArrayList<ArrayList<Integer>> list = test24.findPath1(t,10);
        System.out.println(list.toString());
    }
}
