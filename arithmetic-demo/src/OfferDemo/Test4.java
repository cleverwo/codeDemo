package OfferDemo;


import _modal.TreeNode;
import baseDemo.tree.TreeOrder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Auther: 10413
 * @Date: 2020/1/22 00:12
 * @Description: 4.重建二叉树
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
 * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
 */
public class Test4 {
    // 没有用递归 每次节点的插入都是从头开始判断
    public static TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        //map 代替in数组
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < in.length; i++) {
            map.put(in[i], i);
        }
        TreeNode t = new TreeNode(pre[0]);
        TreeNode nowt = t, pret = t;
        int val = 0;
        for (int i = 1; i < pre.length; i++) {
            int mid = pre[i];
            while (nowt != null) {
                pret = nowt;
                if (map.get(mid) < map.get(nowt.val)) {
                    nowt = nowt.left;
                    val = 0;
                } else {
                    nowt = nowt.right;
                    val = 1;
                }
            }
            if (val != 0) {
                pret.right = new TreeNode(mid);
            } else {
                pret.left = new TreeNode(mid);
            }
            nowt = t;
        }
        return t;
    }

    //dfs 递归
    /*
    1 map 存中序
    2 dfs 遍历， 入参为前序，起始位置，中序，起始位置
    3 dfs 核心， 判合法， 位置不合法结束
                位置相同，出空
                记录prestart 位置，判断 下一次dfs的起始位置。
     */
    Map<Integer,Integer> map = new HashMap<>();
    public TreeNode buildTree(int[] pre,int[] in){
        if (pre.length !=in.length){
            throw new RuntimeException("异常");
        }
        if (in.length == 0){
            return  null;
        }
        for (int i=0;i<in.length;i++){
            map.put(in[i],i);
        }
        TreeNode t = dfs(pre,0,pre.length-1,in,0,in.length-1);
        return t;
    }
    public TreeNode dfs(int[] pre,int preStart, int preEnd,int[] in, int inStart,int inEnd){
        if (preStart>preEnd || inStart> inEnd){
            return null;
        }
        TreeNode t = new TreeNode(pre[preStart]);
        int index = map.get(pre[preStart]);
        t.left = dfs(pre,preStart+1,index-inStart+preStart,in,inStart,index-1);
        t.right = dfs(pre,index-inStart+preStart+1,preEnd,in,index+1,inEnd);
        return t;
    }

    public static void main(String[] args) {
        int[] pre = {1, 2, 3, 4, 5, 6, 7};
        int[] in = {3, 2, 4, 1, 6, 5, 7};
        TreeNode treeNode = reConstructBinaryTree(pre, in);
        List<Integer> list = new ArrayList<>();
        TreeOrder.inOrder(treeNode,list);

    }
}