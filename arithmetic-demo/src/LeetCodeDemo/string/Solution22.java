package LeetCodeDemo.string;

import java.util.*;

/**
 * @Auther: 10413
 * @Date: 2020/3/17 15:53
 * @Description:
 * 22.括号生成  17,39
 */
public class Solution22 {
    /**
     * 想模仿17题用回溯递归来求解，但显然是错误的。
     */
    List<String> list = new ArrayList<>();
    public List<String> generateParenthesis(int n) {
        if (n<=0){
            return list;
        }
        subParent("",n);
        return list;
    }
    public void subParent(String sub,int n){
        if (n==0){
            list.add(sub);
        }else{
           subParent("("+sub+")",n-1);
           subParent("()"+sub,n-1);
        }
    }

    /**
     * 暴力法： 生成所有n个括号的所有组合，验证每个组合是否合法
     */
    public List<String> generateParenthesis1(int n){
        List<String> combinations = new ArrayList<>();
        generateAll(new char[2 * n], 0, combinations);
        return combinations;
    }
    public void generateAll(char[] current,int pos,List<String> result){
        if (pos == current.length){
            if (valid(current)){
                result.add(new String(current));
            }
        }else{
            current[pos] = '(';
            generateAll(current,pos+1,result);
            current[pos] = ')';
            generateAll(current,pos+1,result);
        }
    }
    public boolean valid(char[] current){
        int balance = 0;
        for (char c: current){
            if (c == '(') {
                balance++;
            }else{
                balance--;
            }
            if (balance<0) return false;
        }
        return (balance==0);
    }

    /**
     * 回溯法： 不同暴力法，暴力法是不管不顾的添加（ 和）直到数组满了，在验证
     * 这里没添加一个（ 对应就添加一个）来看看
     */
    public List<String> generateParenthesis2(int n) {
        List<String> ans = new ArrayList();
        backtrack(ans, "", 0, 0, n);
        //backtrack_1(ans,"",n,n);
        return ans;
    }
    //加法的深度遍历
    public void backtrack(List<String> ans, String cur, int left ,int right,int max){
        if (cur.length()==max*2){
            ans.add(cur);
            return;
        }
        if (left<max){
            backtrack(ans,cur+"(",left+1,right,max);
        }
        if (right<left){
            backtrack(ans,cur+")",left,right+1,max);
        }
        // 当right>left 剪枝
        if(left<right){
            return;
        }
    }
    //减法的深度遍历
    public void backtrack_1(List<String> ans,String cur,int left,int right){
        if (left == 0 && right == 0){
            ans.add(cur);
            return;
        }
        // 左括号的数量大于右括号的剩余量 剪枝，这里肯定出现）（这样的情况了
        if (left > right){
            return;
        }
        if (left>0){
            backtrack_1(ans,cur+"(",left-1,right);
        }
        if (right>0){
            backtrack_1(ans,cur+")",left,right-1);
        }
    }

    /**
     * 广度优先遍历
     */
    class Node{
        private String res;
        private int left;
        private int right;
        public Node(String str,int left,int right){
            this.res = str;
            this.left=left;
            this.right=right;
        }
    }
    public List<String> generateParenthesis3(int n){
        List<String> list = new ArrayList<>();
        if (n<=0){
            return list;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node("",n,n));
        while (!queue.isEmpty()){
            Node node = queue.poll();
            if (node.right==0&&node.left==0){
                list.add(node.res);
            }
            if (node.left > node.right){
                continue;
            }
            if (node.left > 0){
                queue.add(new Node(node.res+"(",node.left-1,node.right));
            }
            if (node.right >0){
                queue.add(new Node(node.res+")",node.left,node.right-1));
            }
        }
        return list;
    }

    /**
     * 动态规划
     */
    public List<String> generateParenthesis4(int n){
        if (n<=0){
            return new ArrayList<>();
        }
        List<List<String>> dp = new ArrayList<>();
        List<String> dp0 = new ArrayList<>();
        dp0.add("");
        dp.add(dp0);

        for (int i=1;i<=n;i++){
            List<String> cur = new ArrayList<>();
            for (int j=0;j<i;j++){
                List<String> str1 = dp.get(j); // 可能的括号对数
                List<String> str2 = dp.get(i-1-j); // 剩余的括号对数
                for (String s1: str1){
                    for (String s2: str2){
                        cur.add("("+s1+")"+ s2);
                    }
                }
            }
            dp.add(cur);
        }
        return dp.get(n);
    }

    public static void main(String[] args) {
        Solution22 s = new Solution22();
        //System.out.println(s.generateParenthesis3(3));
        List<String> dp = new ArrayList<>(5);
        System.out.println(dp.size());
        dp.add("1");
        dp.add("2");
        dp.add("3");
        dp.add("4");
        dp.add("5");
        dp.add("6");
        System.out.println(dp.get(5));
    }
}
