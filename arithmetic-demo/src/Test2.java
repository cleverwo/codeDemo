import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @Auther: 10413
 * @Date: 2020/3/27 22:28
 * @Description:
 */
public abstract class Test2 {

    public static void main(String[] args) {
        Scanner c = new Scanner(System.in);
        int n = c.nextInt();
        int sum = Integer.MIN_VALUE;
        for (int i =1;i<=n;i++){
            for (int j=i;j<=n;j++){
                int lcm = getlcm(i,j);
                int gcm = getgcm(i,j);
                int now = lcm-gcm;
                if (now>sum){
                    sum = now;
                }
            }
        }
        System.out.println(sum);
    }
    //最小公倍数
    public static int getlcm(int a, int b){
        return a*b/getgcm(a,b);
    }
    //最大公约数
    public static int getgcm(int a,int b){
        int num=1;
        for (int i=1;i<=a&&i<=b;i++){
            if (a%i==0&&b%i==0){
                num = i;
            }
        }
        return num;
    }
    //坐标移动  本地IDE可行 线上ac不通过，注意输入连续
    public static void test1(){
        Scanner c = new Scanner(System.in);
        while(c.hasNext()){
            String str = c.nextLine();
            String[] strs = str.split(";");
            int n = strs.length;
            int row =0,col =0;
            for(int i=0;i<n;i++){
                String s = strs[i];
                if(s.matches("^[ASWD]{1}[0-9]+$")){
                    char sc = s.charAt(0);
                    int num = Integer.valueOf(s.substring(1));
                    switch(sc){
                        case 'A': row -= num; break;
                        case 'S': col -= num; break;
                        case 'W': col += num; break;
                        case 'D': row += num; break;
                    }
                }
            }
            System.out.println(row+","+col);
        }
        c.close();
    }
    //识别有效的IP地址和掩码并进行分类统计
    public static void test2(){
        Scanner c = new Scanner(System.in);
        int[] num = {0,0,0,0,0,0,0};
        while(c.hasNext()){
            String str = c.next();
            if (str.equals("#")){
                break;
            }
            String[] strs = str.split("~");
            String ip = strs[0];
            String code = strs[1];
            String[] ips = ip.split("\\.");
            //判断ip是否合法
            boolean flag = true;
            if (ips[0].equals("0")){
                continue;
            }else{
                for (int i=0;i<ips.length;i++){
                    if (ips[i].length()<=0||ips[i] == ""){
                        flag = false;
                    }
                    if (flag){
                        int nip = Integer.parseInt(ips[i]);
                        if (nip<0||nip>255){
                            flag = false;
                        }
                    }
                }
            }
            //ip合法判断掩码是否合法
            if (flag){
                if (validMaskCode(code)){
                    flag = true;
                }else{
                    flag = false;
                }
            }

            //IP合法，掩码合法，分类
            if (flag){
                int first = Integer.parseInt(ips[0]);
                int second = Integer.parseInt(ips[1]);
                //判读ABCDE
                if (first>=1&&first<=126){
                    num[0]++;
                }else if (first>=128&&first<=191){
                    num[1]++;
                }else if(first>=192&&first<223){
                    num[2]++;
                }else if (first>=224&&first<=239){
                    num[3]++;
                }else if(first>=240&&first<=255){
                    num[4]++;
                }
                //判断私有IP
                if (first==10){
                    num[6]++;
                }else if(first==172&&second>=16&&second<=31){
                    num[6]++;
                }else if(first==192&&second==168){
                    num[6]++;
                }
            }else{
                num[5]++;
            }
        }
        c.close();
        System.out.println(num[0] + " " + num[1] + " " + num[2] + " " + num[3] + " " + num[4] + " " + num[5] + " " + num[6]);
    }
    private static boolean validMaskCode(String maskCode) {
        boolean res = true;
        String[] nums = maskCode.split("\\.");
        StringBuilder sb = new StringBuilder();
        for (String num : nums) {
            int n = Integer.valueOf(num);
            sb.append(binaryString(n));
        }
        int firstIndexOf0 = sb.indexOf("0");
        int lastIndexOf1 = sb.lastIndexOf("1");
        if (firstIndexOf0 < lastIndexOf1) {
            res = false;
        }
        return res;
    }
    private static String binaryString(int num) {
        StringBuilder result = new StringBuilder();
        int flag = 1 << 7;
        for (int i = 0; i < 8; i++) {
            int val = (flag & num) == 0 ? 0 : 1;
            result.append(val);
            num <<= 1;
        }
        return result.toString();
    }
    //简单的错误记录
    public static void test3() throws IOException {
        BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));
        Map<String,Integer> m=new LinkedHashMap<String,Integer>();
        String tstr=null;
        //&& !tstr.equals(""))没有性能影响
        while((tstr = cin.readLine()) != null && !tstr.equals("")){
            String[] str=tstr.split("\\s+");
            String fname=str[0].substring(str[0].lastIndexOf("\\")+1);
            fname=fname.substring(Math.max(fname.length()-16 ,0))+" "+str[1];  //max 最快推荐 ？：也可以 if太麻烦
            Integer tmp=m.get(fname);  //get==null较快写法
            if(tmp==null)
                m.put(fname,1);
            else
                m.put(fname, tmp+1);
        }
        int cnt=0;
        for(Map.Entry<String,Integer> it:m.entrySet()){
            if(m.size()-cnt<=8)
                System.out.println(it.getKey()+" "+it.getValue());
            cnt++;
        }
    }
    //密码验证合格程序
    public static void test4() {
        Scanner cin = new Scanner(System.in);
        while (cin.hasNextLine()) {
            String psw = cin.nextLine();
            if (checkLength(psw)&&checkCharKinds(psw)&&checkCharRepeat(psw))
                System.out.println("OK");
            else
                System.out.println("NG");
        }
    }
    // 1.长度超过8位
    public static boolean checkLength(String password){
        if (password==null || password.length()<=8)
            return false;
        return true;
    }
    // 2.包括大小写字母.数字.其它符号,以上四种至少三种
    public static boolean checkCharKinds(String password){
        int Digit=0 , lowercase=0,uppercase=0,others=0;
        char[] ch = password.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            if (ch[i]>='0'&&ch[i]<='9') {
                Digit=1;
                continue;
            }else if (ch[i]>='a'&&ch[i]<='z') {
                lowercase=1;
                continue;
            }else if (ch[i]>='A'&&ch[i]<='Z') {
                uppercase=1;
                continue;
            }else {
                others=1;
                continue;
            }
        }
        int total = Digit+lowercase+uppercase+others;
        return total>=3 ? true : false;
    }
    // 3.不能有相同长度超2的子串重复
    public static boolean checkCharRepeat(String password){
        for(int i=0 ;i<password.length()-2 ;i++){
            String substr1 =password.substring(i, i+3);
            if (password.substring(i+1).contains(substr1))
                return false;
        }
        return true;
    }

}
