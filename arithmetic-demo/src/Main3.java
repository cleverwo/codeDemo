import java.math.BigDecimal;
import java.util.*;

/**
 * @Auther: 10413
 * @Date: 2020/4/21 10:18
 * @Description:
 */
public class Main3 {
    // meituan 计算字符串最多最早出现的字符
    public static char cal3(String str) {
        Map<Character,Integer> map = new HashMap<>();
        char[] ch = str.toCharArray();
        int max = 0 ;
        char res = ch[0] ;
        for (int i = ch.length -1 ; i >= 0;i --) {
            if(map.containsKey(ch[i])){
                map.put(ch[i],map.get(ch[i])+1);
                if(map.get(ch[i]) >= max ){
                    max = map.get(ch[i]);
                    res = ch[i] ;
                }
            }else {
                map.put(ch[i],1);
            }
        }
        return res ;
    }
    // huawei 第二题 字符串匹配，判断寄存器的值
    public static void huawei2() {
        // XXX[addr=0xYYY,mask=0xZZZ,val=0xWWWW]
        // XXX 地址，掩码，值
        // 失败 FAIL
        Scanner c =new Scanner(System.in);
        String str = c.next();
        String input = c.next();
        if (input.trim().length()>1024||input.matches("[\\u4e00-\\u9fa5]+")){
            System.out.println("FAIL");
            return;
        }
        boolean flag = false;
        String[] inputs = input.split("],");
        List<String> list = new ArrayList<>();
        for (int i=0;i<inputs.length;i++){
            String niput = inputs[i];
            String index = niput.substring(0,niput.indexOf('['));
            if (index.equals(str)) {
                flag = true;
                list.add(niput);
            }
        }
        if (flag){
            List<String> result = new ArrayList<>();
            for (int i=0;i<list.size();i++){
                getValue(list.get(i),result);
            }
            if (result.size()==0){
                System.out.println("FAIL");
            }else{
                for (int i=0;i<result.size();i++){
                    System.out.println(result.get(i));
                }
            }
        }else{
            System.out.println("FAIL");
        }
    }
    private static List<String> getValue(String str,List<String> result){
        //XXX[addr=0xYYY,mask=0xZZZ,val=0xWWWW
        int addr = str.indexOf("addr=")+5;
        int maskl = str.indexOf(",mask=");
        int mask = maskl + 6;
        int vall = str.indexOf(",val=");
        int val = vall+5;
        int last = str.lastIndexOf("]");
        last = last==-1?str.length():last;
        String result1 = str.substring(addr,maskl);
        String result2 = str.substring(mask,vall);
        String result3 = str.substring(val,last);
        String regex = "^0[xX][0-9a-fA-F]+";
        if (result1.matches(regex)&&result2.matches(regex)&&result3.matches(regex)){
            result.add(result1+ " "+result2+" "+ result3);
        }
        return result;
    }
    // huawei 第一题 计算字符最多出现的字符串，并按字典排序
    public static void huawei1(){
        Scanner c = new Scanner(System.in);
        String str = c.next();
        String[] strs = str.split(",");
        HashMap<String,Integer> map = new HashMap<>();
        for (int i=0;i<strs.length;i++){
            String nstr = strs[i];
            if (!nstr.matches("^[A-Z]{1}[a-z]*")){
                System.out.println("error.0001");
                return;
            }
            if (map.containsKey(strs[i])){
                map.put(strs[i],map.get(strs[i])+1);
            }else{
                map.put(strs[i],1);
            }
        }
        PriorityQueue<Map.Entry<String,Integer>> queue = new PriorityQueue<>(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if (o1.getValue()>o2.getValue()){
                    return -1;
                }else if(o1.getValue()==o2.getValue()){
                    String o1k = o1.getKey();
                    String o2k = o2.getKey();
                    return o1k.compareTo(o2k);
                }else{
                    return 1;
                }
            }
        });
        Iterator<Map.Entry<String,Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String,Integer> nmap = iterator.next();
            queue.offer(nmap);
        }
        Map.Entry<String,Integer> result = queue.poll();
        System.out.println(result.getKey());
    }
    // huawei 字符串计数排序
    public static void huawei11(){
        Scanner c = new Scanner(System.in);
        String str = c.next();
        String[] strs = str.split(",");
        HashMap<String,Vote> map = new HashMap<>();
        for (int i=0;i<strs.length;i++){
            String nstr = strs[i];
            if (!nstr.matches("^[A-Z]{1}[a-z]*")){
                System.out.println("error.0001");
                return;
            }
            if (map.containsKey(nstr)){
                Vote vote = map.get(nstr);
                vote.addValue();
            }else{
                Vote vote = new Vote(nstr);
                map.put(nstr,vote);
            }
        }
        List<Vote> votes = new ArrayList<>(map.values());
        Collections.sort(votes);
        System.out.println(votes.get(0).key);
    }
    static class Vote implements Comparable<Vote>{
        String key;
        int value =1;

        public Vote(String key){
            this.key = key;
        }

        public void addValue(){
            this.value++;
        }

        @Override
        public int compareTo(Vote o) {
            if (this.value!=o.value){
                return o.value-this.value;
            }else{
                return this.key.compareTo(o.key);
            }
        }
    }

    public static void huawei3(){

    }
    public void getPathSum(List<Integer> list){
        int size = 0;
        for (Integer num : list){

        }
    }

    // jingdong 计算6个面是否组成长方体
    public static void getBox() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        if (n>10){
            return;
        }
        List<String> result = new ArrayList<>();
        for (int i=0;i<n;i++){
            List<Node> list = new ArrayList<>();
            for (int j=0;j<6;j++){
                int l = in.nextInt();
                int h = in.nextInt();
                if (l==0||h==0){
                    result.add("IMPOSSIBLE");
                    return;
                }
                Node node = new Node(l,h);
                list.add(node);
            }
            if (list.size()!=6){
                result.add("IMPOSSIBLE");
            }else{
                result.add(isPair(list));
            }
        }
        for (int i=0;i<result.size();i++){
            System.out.println(result.get(i));
        }
    }
    static String isPair(List<Node> list) {
        Set<Integer> set = new HashSet<>();
        boolean flag = true;
        list.sort(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if (o1.length==o2.length){
                    return o2.high-o1.high;
                }else{
                    return o1.length-o2.length;
                }
            }
        });
        for (int i=0;i<list.size();i=i+2){
            if (list.get(i).length != list.get(i+1).length || list.get(i).high != list.get(i+1).high) {
                flag = false;
            }
        }
        for (int i=0;i<list.size();i++){
            set.add(list.get(i).length);
            set.add(list.get(i).high);
        }
        if (set.size()>3){
            flag = false;
        }
        if (flag){
            return "POSSIBLE";
        }else {
            return "IMPOSSIBLE";
        }
    }
    static class Node{
        int length;//大的
        int high;//小的
        public Node(int length,int high){
            if (length>high){
                this.length = length;
                this.high = high;
            }else{
                this.length = high;
                this.high = length;
            }
        }
    }

    //ali 计算大数的幂
    public static int bigNumber(int n){
        int m = 1000000007;
        BigDecimal re = BigDecimal.valueOf(2).pow(n-1).multiply(BigDecimal.valueOf(n))
                .divideAndRemainder(BigDecimal.valueOf(m))[1];
        return re.intValue();
    }
    public static int bigNumber1(int n){
        int top = 1000000007;
        // 2^n-1 * n
        int index = n-1;
        long result = 1;
        long x = 2;
        // 快速幂解法
        while (index>0){
            if ((index&1)!=0){
                result = result*x%top;
            }
            x = x*x%top;
            index >>= 1;
        }
        int re = (int)result*n%top;
        return re;
    }
    //ali 给定两个等长字符串S,T。可以移动S的任意字符到其末尾。问最少移动几次可以使的S和T完全相同
    public static void Alibaba1(){
        Scanner c = new Scanner(System.in);
        String s = c.next();
        String t = c.next();
        int n = s.length();
        List<Character> list = new ArrayList<>();
        int i =0,j=0;
        int sum = 0;
        while (i<n&&j<n){
            char sc = s.charAt(i);
            char tc = t.charAt(j);
            if (sc!=tc){
                i++;
                list.add(sc);
            }else{
                i++;
                j++;
            }
        }
        while (j<n){
            char tc = t.charAt(j);
            if (list.contains(tc)){
                sum++;
                j++;
            }else{
                System.out.println(-1);
                return;
            }
        }
        System.out.println(sum);
    }

    //meituan 面试1题
    public static void meituan1() {
        Scanner c = new Scanner(System.in);
        String str = c.nextLine();
        String[] nums = str.split("\\s+");
        int[] a  = new int[20];
        for (int i=0;i<20;i++){
            a[i] = Integer.parseInt(nums[i]);
        }
        int[] b = new int[10];
        for (int i=0;i<a.length;i++){
            b[a[i]-1]++;
        }
        int max = Integer.MIN_VALUE;
        int index = 9;
        for (int i=b.length-1;i>=0;i--){
            if (max<=b[i]){
                max = b[i];
                index = i;
            }
        }
        System.out.println(index+1);
    }

    public static void main(String[] args) {
    }
}
