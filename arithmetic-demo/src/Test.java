import javafx.scene.shape.VLineTo;

import java.lang.management.GarbageCollectorMXBean;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.LinkedTransferQueue;

/**
 * @Auther: 10413
 * @Date: 2020/3/22 19:09
 * @Description:
 * 快手前三题
 */
public class Test {

    public int[] test(int[] height){
        if (height==null||height.length==0){
            return new int[]{};
        }
        int n = height.length;
        int[] out = new int[n];
        for (int i=n-1;i>0;i--){
            int j = i-1;
            while (j>=0){
                if (height[i]>=height[j]){
                    j--;
                }else{
                    break;
                }
            }
            if (j<0){
                out[i] = 0;
            }else{
                out[i] = i-j;
            }
        }
        return out;
    }

    public String test1(){
        Scanner c = new Scanner(System.in);
        String input = c.nextLine();
        if (input==null||input.length()==0){
            return "-1";
        }
        String[] numbers = input.split("\\s+");
        int n = numbers.length;
        int[] nums = new int[n];
        for (int i=0;i<n;i++){
            nums[i] = Integer.parseInt(numbers[i]);
        }
        /*int[] nums = {};
        int n =nums.length;
        if(n<=1){
            return "-1";
        }*/
        String result="";
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        for (int i=0;i<n;i++){
            if (nums[i]>=max1){
                max2 = max1;
                max1 = nums[i];
            }else if (nums[i]>=max2){
                max2 = nums[i];
                result = result + i + " ";
            }
        }
        return result==""?"-1":result;
    }

    public void phoneNum(String[] nums){
        if (nums==null||nums.length==0){
            return;
        }
        int n = nums.length;
        HashMap<Integer,String> map = new HashMap<>();
        for (int i=0;i<n;i++){
            int result = getGood(nums[i]);
            if (result>0){
                map.put(result,nums[i]);
            }
        }
        map.forEach((key, value)->{
            if (key==4){
                System.out.print(value);
            }
        });
        map.forEach((key, value)->{
            if (key==3){
                System.out.print(value);
            }
        });
        map.forEach((key, value)->{
            if (key==2){
                System.out.print(value);
            }
        });
        map.forEach((key, value)->{
            if (key==1){
                System.out.print(value);
            }
        });
    }
    //判断优先级
    public int getGood(String number){
        Queue<Character> bao = new LinkedList<>();
        Queue<Character> shun = new LinkedList<>();
        int grade = 0;
        int grade2 = 0;
        String num = number.substring(3);
        char[] cn = num.toCharArray();
        shun.offer(cn[0]);
        //判断顺子
        for (int i=0;i<cn.length;i++){
            char now = cn[i];
            if (shun.isEmpty()){
                shun.offer(now);
            }else {
                char shunTop = shun.peek();
                if (Math.abs(shunTop-now)==1){
                    shun.offer(now);
                }else{
                    if (shun.size()>=3){
                        grade = shun.size()-2;
                    }else{
                        shun.clear();
                        shun.offer(now);
                    }
                }
            }
            if (!shun.isEmpty()){
                int length = shun.size()-2;
                if (length>grade){
                    grade = length;
                }
            }
        }
        //判断豹子
        for (int i=0;i<cn.length;i++){
            char now = cn[i];
            if (bao.isEmpty()){
                bao.offer(now);
            }else{
                char baoTop = bao.peek();
                if (baoTop==now){
                    bao.offer(baoTop);
                }else {
                    if (bao.size()>=3){
                        grade2 = bao.size()-1;
                    }else{
                        bao.clear();
                        bao.offer(now);
                    }
                }
            }
            if (!bao.isEmpty()){
                int length = bao.size()-1;
                if (length>grade2){
                    grade2 = length;
                }
            }
        }
        if (grade>grade2){
            return grade;
        }
        return grade2;
    }

    public static void main(String[] args) {
        System.out.println("A10A".matches("^[ASWD]{1}[0-9]*$"));
    }


    public int dna(String a, String b){
        if (a==null||b==null){
            return -1;
        }
        char[] cara = a.toCharArray();
        char[] carb = b.toCharArray();
        int n =cara.length,m = carb.length;
        int A=0, T=0;
        for (int i = 0;i<n;i++){
            if (cara[i]!=carb[i]){
                if (cara[i]=='A'){
                    A++;
                }else{
                    T++;
                }
            }
        }
        return Math.abs(A-T)+Math.min(A,T);

    }
    public void test(){
        Scanner c = new Scanner(System.in);
        int n = c.nextInt();
        int m = c.nextInt();
        double[][] dp = new double[n+1][m+1];
        for (int i=0;i<=n;i++){
            for (int j=0;j<=m;j++){
                if (i==0&&j==0){
                    dp[i][j] = 0;
                }else if(i==0){
                    dp[i][j] = 0;
                }else if(j==0){
                    dp[i][j]=1;
                }else{
                    dp[i][j] = 1.0*i/(i+j) +
                            1.0*j/(i+j) *
                                    (j-1)/(i+j-1) *
                                    ((j-2>=0?1.0*i/(i+j-2)*dp[i-1][j-2]:0) +
                                            (j-3>=0?1.0*(j-2)/(i+j-2)*dp[i][j-3]:0));
                }
            }
        }
        System.out.println(dp[n][m]);
    }
    public void test11(){
        Scanner c = new Scanner(System.in);
        String a = c.next();
        boolean flag = true;
        if (a.charAt(0) == '-'){
            flag = false;
        }
        String[] b = new String[9];
        for (int i =0;i<9;i++){
            b[i] = c.next();
        }
        int n = a.length();
        int s = 0;
        StringBuffer result = new StringBuffer();
        if (!flag){
            result.append("-");
            s=1;
        }
        for (int i=s;i<n;i++){
            char num = a.charAt(i);
            int aa = Integer.parseInt(String.valueOf(num));
            result.append(b[aa-1]);
        }
        System.out.println(result);
    }

    public void test2(){
        Scanner c = new Scanner(System.in);
        int r = c.nextInt();
        int[] row = new int[r];
        for (int i=0;i<r;i++){
            row[i] = c.nextInt();
        }
        int n = row.length;
        Arrays.sort(row);
        BigDecimal result = new BigDecimal(0);
        boolean add =true;
        for (int i =n-1;i>=0;i=i-2){
            BigDecimal r1 = new BigDecimal(row[i]);
            r1 = r1.multiply(r1);
            BigDecimal r2 = new BigDecimal(0);
            if (i>0){
                r2 = new BigDecimal(row[i-1]);
            }else{
                r2 = new BigDecimal(0);
            }
            r2 = r2.multiply(r2);
            BigDecimal now = r1.subtract(r2);
            result = result.add(now);
        }
        result = result.multiply(new BigDecimal(Math.PI));
        double out = result.setScale(5,BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println(out);
    }
    public static boolean isFin(List<Integer> a){
        for (int i=0;i<a.size();i++){
            if (i%a.get(i)!=0){
                return false;
            }
        }
        return true;
    }

    public void tst(){
        Scanner c = new Scanner(System.in);
        int n = c.nextInt();
        int[] num = new int[n];
        StringBuffer s = new StringBuffer();
        for (int i=0;i<n;i++){
            num[i]= c.nextInt();
        }
        int mod = 998244353;
        int result = 0;
        int length = num.length;
        int end = 1<<length;
        int mark = 0;
        List<Integer> list = new ArrayList<>();
        for (mark = 0;mark<end;mark++){
            boolean isNull = true;
            for (int i=0;i<length;i++){
                if ((1<<i & mark)!=0){
                    isNull = false;
                    list.add(num[i]);
                    if (isFin(list)){
                        return;
                    }
                    System.out.println(num[i]+ " ");;
                }
            }
        }
        System.out.println();
    }

    /**
     * 1找二进制子串包含n个1的子串个数
     */
    public static void test111() {
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();
        scanner.nextLine();
        String s = scanner.nextLine();
        int num = 0, len = s.length();
        int[] dp = new int[k + 2];
        long result = 0;
        dp[0] = 1;
        for (char c : s.toCharArray()) {
            if (c == '1') num++;
            if (num - k >= 0) result += dp[(num - k) % (k + 2)];
            dp[(num + 1) % (k + 2)] = 0;
            dp[num % (k + 2)]++;
        }
        System.out.println(result);
    }
    /**
     * 2考试成绩最大分数
     * 班级一共n个学生，考试有m个问题。每个题目都有5个可选答案（A，B，C，D，E）。并且每个题目只有一个正确答案。每个题目的分数并不一样，第i个题目的分数用a[i]表示。如果题目没答对该题会获得0分。
     * 考试结束后，每个学生都记得自己的答案，但是他们还不知道正确答案是什么。如果非常乐观的考虑，他们班级最多可能得到多少分呢？
     */
    public static void test2(String[] args) {
        Scanner sc = new Scanner(System.in);
        String l1 = sc.nextLine();
        //学生和题目数量存储在num中  学生[0]  题目[1]
        String[] num = l1.split(" ");
        int num_student = Integer.parseInt(num[0]);
        int num_question = Integer.parseInt(num[1]);
        String[] final_answer = {"A", "B", "C", "D", "E"};
        String[] answer = new String[num_student];
        int score = 0;
        for (int i = 0; i < num_student; i++) {
            String temp = sc.nextLine();
            answer[i] = temp;
        }
        String temp = sc.nextLine();
        String[] str_score = temp.split(" ");
        //里面存储了各个题的分值
        int[] count = {0, 0, 0, 0, 0, 0};
        for (int i = 0; i < num_question; i++) {
            //每次重新赋值
            for (int l = 0; l < count.length; l++) {
                count[l] = 0;
            }
            for (int k = 0; k < num_student; k++) {
                count[answer[k].charAt(i) - 'A'] += 1;
                //count为该题的统计，找出其中数量最多的即可
            }
            //找出其中数量最大的
            int local = 0;
            int max = count[local];
            for (int p = 1; p < count.length; p++) {
                if (count[p] > max) {
                    max = count[p];
                    local = p;
                }
            }
            score += max * (Integer.parseInt(str_score[i]));
        }
        System.out.println(score);
    }
    /**
     * 3题 是leetcode 1049. 最后一块石头的重量 II
     */
    /**
     * 4蓄水池问题
     * 在你面前有n个蓄水池，他们组成了树形结构（由n-1条边连接）。蓄水池节点编号从1开始到n。对每个蓄水池节点来说，他的儿子蓄水池节点都摆放在他的下面，并且和它用水管相连，根据重力，水会向下流动。现在我们要在蓄水池上做一些操作：
     * 1. 把节点v填满水。然后v的所有儿子节点水也会被填满
     * 2. 清空节点v的水。然后v所有的父亲节点水都会被清空
     * 3. 询问每个蓄水池节点是否有水。
     * 初始状态时候，每个节点都是空的。
     * 现在我们会依次进行一系列操作，我们想提前知道每次操作后的结果，你能帮忙解决吗？
     */
    static HashSet<Integer>[] adj;
    static boolean[] full;
    public static void test4() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        adj = new HashSet[n + 1];
        full = new boolean[n + 1];
        for (int i = 0; i <= n; i++) adj[i] = new HashSet<>();
        for (int i = 0; i < n - 1; i++) {
            int a = scanner.nextInt(), b = scanner.nextInt();
            adj[a].add(b);
            adj[b].add(a);
        }
        int q = scanner.nextInt();
        while (q-- > 0) {
            int method = scanner.nextInt(), v = scanner.nextInt();
            switch (method) {
                case 1:
                    flood(v);
                    break;
                case 2:
                    clear(v);
                    break;
                case 3:
                    System.out.println(full[v] == true ? 1 : 0);
                    break;
            }
        }
    }
    public static void flood(int v) {
        full[v] = true;
        for (int w : adj[v]) if (w > v) flood(w);
    }
    public static void clear(int v) {
        full[v] = false;
        for (int w : adj[v]) if (w < v) clear(w);
    }
    /**
     * 合并两个内容流，实现隔4个插入1个，如果合并完还有剩下，则加内容流尾部
     */
    public static void test5() {
        Scanner scanner = new Scanner(System.in);
        String str1 = scanner.nextLine();
        String str2 = scanner.nextLine();
        List<String> list = doInsert(str1.trim().split(" "), str2.trim().split(" "));
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
    }
    private static List<String> doInsert(String[] c1, String[] c2) {
        List<String> list = new ArrayList<>();
        int j = 0;
        int count = 0;
        for (int i = 0; i < c1.length; i++) {
            list.add(c1[i]);
            count++;
            if (count == 4 && j < c2.length) {
                count = 0;
                list.add(c2[j]);
                j++;
            }
        }
        while (j < c2.length) {
            list.add(c2[j++]);
        }
        return list;
    }
    /**
     * 给定两个版本号，只有在版本号更高的时候，才可以升级。「.」号作为分割符使用，版本号中只有数和.号。
     * <p>
     * 例如：0.1<1.1
     * 1.2<13.37
     */
    //读取数据
    public static void test6() {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        String[][] str = new String[m][2];
        if (scanner.hasNext()) {
            for (int i = 0; i < m; i++) {
                str[i][0] = scanner.next();
                str[i][1] = scanner.next();
            }
            for (int i = 0; i < m; i++) {
                System.out.println(isUpdate(str[i][0].trim().split("\\."), str[i][1].trim().split("\\.")));
            }
        }
    }
    //把String->int 并使两数组等长;
    private static boolean isUpdate(String[] v1, String[] v2) {
        int max = Math.max(v1.length, v2.length);
        int[] ve1 = new int[max];
        int[] ve2 = new int[max];
        for (int i = 0; i < max; i++) {
            if (i >= v1.length) {
                ve1[i] = 0;
            } else {
                ve1[i] = Integer.parseInt(v1[i]);
            }
        }
        for (int i = 0; i < max; i++) {
            if (i >= v2.length) {
                ve1[i] = 0;
            } else {
                ve2[i] = Integer.parseInt(v2[i]);
            }
        }
        return doUpdate(ve1, ve2, 0);
    }
    //比大小
    private static boolean doUpdate(int[] v1, int[] v2, int i) {
        if (i == v1.length) {
            return false;
        }
        if (v1[i] < v2[i]) {
            return true;
        } else if (v1[i] > v2[i]) {
            return false;
        } else {
            return doUpdate(v1, v2, i + 1);
        }
    }
    /**
     * 给定一个32位int型正整数，我们定义如下操作，取其十进制各位数字的平方和，并不断重复这个操作。如果某次操作完成后得到的结果是1，则返回true；否则继续执行，直到证明永远不会得到结果为1，返回false
     * input:19
     * <p>
     * output:true
     * <p>
     * 原因：
     * <p>
     * 1^2 + 9^2=82
     * <p>
     * 8^2 + 2^2 = 68
     * <p>
     * 6^2 + 8^2 =  100
     * <p>
     * 1^2 + 0^2 + 0^2 = 1
     */
    public static void test7() {
//建立一个二维数组,提高后面平方的运算速度
        int[][] lib = new int[10][2];
        for (int i = 0; i < 10; i++) {
            lib[i][0] = i;
            lib[i][1] = i * i;
        }
//读取数据
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int[] arr = new int[m];
        for (int i = 0; i < m; i++) {
            arr[i] = scanner.nextInt();
        }
        for (int i = 0; i < m; i++) {
            List<Integer> list = new ArrayList<>();
            System.out.println(isOne(arr[i], lib, list));
        }
    }
    private static boolean isOne(int num, int[][] lib, List<Integer> list) {
        if (num == 1) return true;
//这里最重要的就是判断什么时候是false,其实就是循环了
//每次判断list中是否已经含有当前数字,如果有,那就不可能为1了;
        if (list.contains(num)) return false;
//并且插入num
        list.add(num);
        int n = 0;
        while (num > 0) {
            int tmp = num % 10;
            n += lib[tmp][1];
            num /= 10;
        }
        return isOne(n, lib, list);
    }
    /**
     * 现在你面前有一棵n个节点的树（全连通无环图）。树上的边只有2种颜色，红色或者黑色。现在还给你一个整数k，考虑下面这个k个节点的序列[a1, a2, ..., ak]。
     * [a1, a2, ..., ak]如果是”好序列“当且仅当满足下面的条件：
     * 1. 我们要走一条从a1开始到ak结束的路径。
     * 2. 从a1开始，到a2走一条a1到a2的最短路。然后从a2开始，继续走一条到a3的最短路，以此类推，最终到a(k-1)和ak。
     * 3. 走的路径中至少包含一条黑色的边。
     */
    static int mod = 1000000007;
    static Set<Integer>[] adjj;
    static boolean[] visited;
    public static void test8() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        adjj = new HashSet[n + 1];
        visited = new boolean[n + 1];
        BigInteger bad = new BigInteger("0");
        for (int i = 0; i <= n; i++) {
            adjj[i] = new HashSet<>();
        }
        while (scanner.hasNext()) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int weight = scanner.nextInt();
            if (weight == 0) {
                adjj[a].add(b);
                adjj[b].add(a);
            }
        }
        for (int i = 0; i <= n; ++i) {
            if (!visited[i]) {
                bad = bad.add(fastPow(dfs(i), k)).mod(new BigInteger("" + mod));
            }
            BigInteger big = fastPow(n, k);
            System.out.println(big.subtract(bad).add(new BigInteger(mod + "")).mod(new BigInteger(mod + "")));
        }
    }
    public static int dfs(int num) {
        visited[num] = true;
        int res = 1;
        for (int next : adjj[num]) {
            if (!visited[next]) res += dfs(next);
        }
        return res;
    }
    public static BigInteger fastPow(int n, int k) {
        if (k == 0) {
            return new BigInteger("1");
        }
        BigInteger half = fastPow(n, k / 2);
        if (k % 2 == 0) {
            return half.multiply(half).mod(new BigInteger(mod + ""));
        } else {
            return half.multiply(half).multiply(new BigInteger(n + "")).mod(new BigInteger(n + ""));
        }
    }

}
