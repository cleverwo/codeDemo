package OfferDemo;

import _modal.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: 10413
 * @Date: 2020/9/9 20:51
 * @Description:
 * 二叉树中和为某一值的所有路径
 */
public class Offer34 {

    // 典序的dfs剪枝算法
    List<List<Integer>> res;
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        res = new ArrayList<>();
        if (root == null) {
            return res;
        }
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

    //----------------------------------------------------------
    public static void main(String[] args) {
        Offer34 o = new Offer34();
        Test61 treeBuild = new Test61();
        String test = "[5,4,8,11,null,13,4,7,2,null,null,5,1,null,null,null,null,null,null,null,null]";
        TreeNode t = treeBuild.deserialize(test);
        List<List<Integer>> list = o.pathSum(t, 22);
        System.out.println(list.toString());
    }
}
