package OfferDemo;


import _modal.TreeNode;

import java.util.ArrayList;
import java.util.List;

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
    // 典序的dfs剪枝算法
    List<List<Integer>> res;
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        // 保存当前路径
        List<Integer> curr = new ArrayList<>();
        dfs(root, curr, sum);
        return res;
    }

    private void dfs(TreeNode root, List<Integer> curr, int sum) {
        if (root == null){
            return;
        }
        int val = root.val;
        curr.add(val);
        sum -= val;
        if (sum == 0 && root.left == null && root.right == null) {
            res.add(new ArrayList<>(curr));
        }
        dfs(root.left,curr,sum);
        dfs(root.right,curr,sum);
        curr.remove(curr.size() - 1);
    }

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
        Test24 o = new Test24();
        ArrayList<ArrayList<Integer>> list = o.FindPath(t,10);
        System.out.println(list.toString());

        Test61 treeBuild = new Test61();
        String test = "[5,4,8,11,null,13,4,7,2,null,null,5,1,null,null,null,null,null,null,null,null]";
        TreeNode tt = treeBuild.deserialize(test);
        List<List<Integer>> list2 = o.pathSum(tt, 22);
        System.out.println(list2.toString());
    }
}
