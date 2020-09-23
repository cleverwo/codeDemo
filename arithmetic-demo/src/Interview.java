import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Auther: 10413
 * @Date: 2020/9/2 17:40
 * @Description:
 */
public class Interview {
    //  华为1面 9.2 16：00
    public static void getArray(int[] a, int[] b){
        int n = a.length;
        Map<Integer,Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        for (int i=0;i<n;i++){
            if (!map.containsKey(a[i])){
                map.put(a[i],1);
            }else{
                map.put(a[i],map.get(a[i])+1);
            }
        }
        int m = b.length;
        for (int i=0;i<m;i++){
            if (map.containsKey(b[i])&&map.get(b[i])!=0){
                list.add(b[i]);
                map.put(b[i],map.get(b[i])-1);
            }
        }
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        System.out.println(list.toString());
    }

    // 华为2面 9.3 17：00 快速排序
    public static int sort(int[] num, int low, int high) {
        int a = num[low];
        while (low < high) {
            while (low < high && num[high] >= a) {
                high--;
            }
            num[low] = num[high];
            while (low < high && num[low] <= a) {
                low++;
            }
            num[high] = num[low];
        }
        num[low] = a;
        return low;
    }
    public static void quickSort(int[] num, int low, int high) {
        if (low < high) {
            int mid = sort(num,low,high);
            quickSort(num, low, mid - 1);
            quickSort(num, mid + 1, high);
        }
    }

    // 小红书 T1
    static int findMin1(int[] tree) {
        Arrays.sort(tree);
        int min = 1;
        for (int i=0;i<tree.length;i++){
            if (min==tree[i]){
                min++;
            }
        }
        return min;
    }
    // 小红书 T2
    static int solution1(String s) {
        if (s==null || s.length() == 0){
            return 0;
        }
        int len = s.length();
        int[] f = new int[len+1];
        for (int i=0;i<=len;i++){
            f[i] = len -1 -i;
        }
        for (int i=len -1;i>=0;i--){
            for (int j=i;j<len;j++){
                if (s.charAt(i) == s.charAt(j) ){
                    f[i] = Math.min(f[i],f[j+1]+1);
                }
            }
        }
        return f[0] + 1;

    }


    // 网易 T1
    public static void test1() {
        Scanner c = new Scanner(System.in);
        int length = c.nextInt();
        String A = c.next();
        String B = c.next();
        if (A.length() != B.length() || A.length() != length) {
            System.out.println(-1);
            return;
        }
        if (B.compareTo(A) > 0) {
            // B 大 B》A成立，B`一定存在
            char[] min = B.toCharArray();
            Arrays.sort(min);
            String minS = new String(min);
            if (minS.compareTo(A) > 0) {
                System.out.println(minS);
                return;
            }
            B = minS;
        }
        // B 小，找B的下一个字典序，知道Bl 》 A
        char[] cb = B.toCharArray();
        Arrays.sort(cb);
        reserve(cb);
        String max = new String(cb);
        if (max.compareTo(A)<=0){
            System.out.println(-1);
            return;
        }
        String nB = nextNum(A,B);
        if (nB.equals("#")){
            System.out.println(-1);
        }else{
            System.out.println(nB);
        }
        /*String nB = nextNum(B);
        while (nB != "#" && nB.compareTo(A) <= 0) {
            nB = nextNum(nB);
        }
        if (nB.equals("#")) {
            //B 永远不大于A
            System.out.println(-1);
        } else {
            System.out.println(nB);
        }*/
    }
    public static String nextNum(String nums) {
        if (nums.length() == 0) {
            return "0";
        }
        char[] num = nums.toCharArray();
        int n = num.length;
        int i = n - 2;
        while (i >= 0 && num[i] >= num[i + 1]) {
            i--;
        }
        if (i == -1) {
            return "#";
        }
        int j = n - 1;
        while (j >= 0 && num[j] <= num[i]) {
            j--;
        }
        char tmp = num[i];
        num[i] = num[j];
        num[j] = tmp;
        for (int l = i + 1, h = n - 1; l < h; l++, h--) {
            char curr = num[l];
            num[l] = num[h];
            num[h] = curr;
        }
        StringBuffer str = new StringBuffer();
        for (int k = 0; k < n; k++) {
            str.append(num[k]);
        }
        return str.toString();
    }
    public static String nextNum(String A,String B){
        char[] ca = A.toCharArray();
        char[] cb = B.toCharArray();
        int i = 0;
        for (i=0;i<ca.length;i++){
            int j = cb.length-1;
            while (j>=0&&cb[j]>ca[i]){
                j--;
            }
            if (j == -1||j==ca.length-1){
                return "#";
            }
            if (cb[j]==ca[i]){
                char tmp = cb[j];
                cb[j] = cb[i];
                cb[i] = tmp;
            }else{
                char tmp = cb[j+1];
                cb[j+1] = cb[i];
                cb[i] = tmp;
                break;
            }
        }
        if (i+1<cb.length){
            Arrays.sort(cb,i+1,cb.length);
        }
        return new String(cb);
    }
    public static void reserve(char[] cs){
        for (int l=0,h=cs.length-1;l<h;l++,h--){
            char tmp = cs[l];
            cs[l] = cs[h];
            cs[h] = tmp;
        }
    }

    // 网易T2
    public static void test2(String[] args) {
        Scanner c = new Scanner(System.in);
        String s = c.nextLine();
        c.close();
        String result = compress(s);
        System.out.println(result);
    }
    public static String compress (String raw_str) {
        if(raw_str == null || raw_str.length() == 0){
            return "";
        }
        String s = raw_str;
        int l =0,r=0;
        StringBuilder sb = new StringBuilder();
        while (l<s.length()){
            while (r<s.length() && s.charAt(r) == s.charAt(l)){
                r++;
            }
            int num = r-l;
            if (num<4){
                for (int i=0;i<num;i++){
                    sb.append(s.charAt(l));
                }
            }else if(num <=55){
                sb.append('0').append(transform(num)).append(s.charAt(l));
            }else{
                int n = num/55;
                for (int i=0;i<n;i++){
                    sb.append("0Z").append(s.charAt(l));
                }
                int m = num%55;
                if (m>0){
                    sb.append("0").append(transform(m)).append(s.charAt(l));
                }
            }
            l = r;
        }
        return sb.toString();
    }
    public static char transform(int num){
        if (num<30){
            return (char)( num-4 +'a');
        }else{
            return (char)(num -30 + 'A');
        }
    }

    // 腾讯 T2
    public static void tenXun2() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // n 个人
        int m = sc.nextInt(); // m 团队
        Map<Integer,List<Integer>> map = new HashMap<>();
        List<List<Integer>> together = new ArrayList<>();
        int start = -1;
        for (int i=0;i<m;i++){
            int mNum = sc.nextInt();
            List<Integer> currList = new ArrayList<>();
            for (int j=0;j<mNum;j++){
                int temp = sc.nextInt();
                if (temp == 0){
                    start = i;
                }
                if (!map.containsKey(temp)){
                    map.put(temp,new ArrayList<>());
                }
                currList.add(temp);
                List<Integer> list = map.get(temp);
                list.add(i);
                map.put(temp,list);
            }
            together.add(currList);
        }

        int[] mark = new int[m];
        Set<Integer> set = new HashSet<>();
        if (start == -1){
            System.out.println(0);
            return;
        }
        getResult(together,map,mark,set,start);
        System.out.println(set.size());
    }
    private static void getResult(List<List<Integer>> list,Map<Integer,List<Integer>> map,
                                  int[] mark, Set<Integer> set, int index) {
        if (mark[index] == 1){
            return;
        }
        set.addAll(list.get(index));
        mark[index] = 1;
        for (int num : list.get(index)){
            for (int gNo: map.get(num)){
                getResult(list,map,mark,set,gNo);
            }
        }
    }
    //腾讯 T4
    public static void tenXun() {
        Scanner sc = new Scanner(System.in);
        String a1 = sc.nextLine();
        String str = sc.nextLine();
        sc.close();
        String[] strs = str.split(" ");
        int n = Integer.valueOf(a1);
        List<Integer> list = new ArrayList<>();
        for (String s: strs){
            list.add(Integer.parseInt(s));
        }
        Collections.sort(list);
        int mid = n/2;
        int mid0 = list.get(n/2-1);
        int mid1 = list.get(mid);
        for (int i=0;i<n;i++){
            int num = Integer.parseInt(strs[i]);
            if (num<=mid0){
                System.out.println(mid1);
            }else{
                System.out.println(mid0);
            }
        }

    }

    //小米
    static Map<Character,Character> map;
    public static void xiaomi1(String[] args) {
        Scanner sc = new Scanner(System.in);
        map = new HashMap<>();
        map.put('}','{');
        map.put(']','[');
        map.put(')','(');
        List<String> res = new ArrayList<>();
        while (sc.hasNext()){
            String str = sc.nextLine();
            if (isStr(str)){
                res.add(isValid(str.trim()));
            }else{
                res.add("false");
            }
        }
        for (int i=0;i<res.size();i++){
            System.out.println(res.get(i));
        }

    }
    public static  String judge12(String str){
        if (str.length() == 0){
            return "true";
        }
        char[] cs = str.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (int i=0;i<cs.length;i++){
            char c = cs[i];
            if (c == '(' || c == '[' || c == '{'){
                stack.push(c);
            }else{
                char curr = map.get(c);
                if (stack.isEmpty()){
                    return "false";
                }
                char top = stack.pop();
                if (top != curr){
                    return "false";
                }
            }
        }
        if (stack.isEmpty()){
            return "true";
        }else{
            return "false";
        }
    }
    public static String isValid(String s){
        int n = s.length();
        if (n%2 == 1){
            return "false";
        }
        Deque<Character> stack = new LinkedList<>();
        for (int i=0;i<n;i++){
            char ch = s.charAt(i);
            if (map.containsKey(ch)){
                if (stack.isEmpty() || stack.peek() != map.get(ch)){
                    return "false";
                }
                stack.pop();
            }else{
                stack.push(ch);
            }
        }
        if (stack.isEmpty()){
            return "true";
        }else{
            return "false";
        }
    }

    public static void xiaomi2(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        scanner.close();
        boolean[] marked = new boolean[256];
        StringBuilder sb = new StringBuilder();
        for (int i=0;i< str.length();i++){
            char c = str.charAt(i);
            if (!marked[c]){
                sb.append(c);
                marked[c] = true;
            }
        }
        System.out.println(sb.toString());
    }

    public static boolean isStr(String str){
        char[] cs = str.toCharArray();
        for (int i=0;i<cs.length;i++){
            char c = cs[i];
            if (c == '('|| c==')'||c=='['||c==']'||c=='{'||c=='}' ){
                continue;
            }else{
                return false;
            }
        }
        return true;
    }
    //京东
    public static void jingdong1(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        sc.close();
        List<String> ans = judge1(str);
        if (!ans.isEmpty()){
            System.out.println(String.join(" ",ans));
        }
    }
    public static List<String> judge(String str){
        String regex = "[^0-9]";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        String res = m.replaceAll(" ").trim();
        StringBuffer buffer = new StringBuffer();
        String[] ans = res.split(" ");
        List<String> list = new ArrayList<>();
        for (String subStr : ans){

            if (subStr == null||subStr.length()!=4){
                continue;
            }
            int year = Integer.parseInt(subStr);
            if (year>=1000 && year<=3999){
                list.add(subStr);
            }
        }
        return list;
    }
    public static List<String> judge1(String str){
        String[] ans = str.split("\\D+");
        List<String> list = new ArrayList<>();
        for (String subStr : ans){
            subStr = subStr.trim();
            try{
                if (!"".equals(subStr)){
                    int num = Integer.parseInt(subStr);
                    if (num>=1000&&num<=3999){
                        list.add(String.valueOf(num));
                    }
                }
            }catch (Exception e){

            }
        }
        return list;
    }

    public static void jingdong2(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt(); // 输入组输
        List<String> res = new ArrayList<>();
        while (T>0){
            int n = sc.nextInt(); // hang n<=100
            int m = sc.nextInt(); // lie m<=100
            // 输入n行，每行 m个字符
            String[][] map = new String[n][m];
            int x = 0, y=0;
            for (int i=0;i<n;i++){
                String str = sc.next();
                for (int j=0;j<str.length();j++){
                    map[i][j] = String.valueOf(str.charAt(j));
                    if (map[i][j].equals("S")){
                        x = i;
                        y = j;
                    }
                }
            }
            int[][] move = {{0,1},{0,-1},{1,0},{-1,0}};
            boolean[][] mark = new boolean[n][m];
            boolean ans = judge(map,x,y,move,mark,n,m);
            if (ans){
                res.add("YES");
            }else{
                res.add("NO");
            }
            T--;
        }
        res.forEach(System.out::println);
    }
    public static boolean judge(String[][] map, int x,int y,int[][] move,boolean[][] mark,int n,int m){
        // 越界跳出 阻碍
        if (x<0||x>=n || y<0|| y>=m||map[x][y].equals("#")||mark[x][y]){
            return false;
        }
        if (map[x][y].equals("E")){
            return true;
        }
        mark[x][y] = true;
        for (int i=0;i<move.length;i++){
            boolean res = judge(map,x+move[i][0],y+move[i][1],move,mark,n,m);
            if (res){
                return true;
            }
        }
        mark[x][y] = false;
        return false;
    }
}
