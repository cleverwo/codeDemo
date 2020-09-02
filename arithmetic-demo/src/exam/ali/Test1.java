package exam.ali;

import java.util.*;

/**
 * @Auther: 10413
 * @Date: 2020/9/2 17:41
 * @Description:
 */
public class Test1 {
    public static void main(String[] args) {
        Scanner c = new Scanner(System.in);
        int n = c.nextInt();
        int m = c.nextInt();
        String s = c.next();
        String t = c.next();
        int result = match(s,t);
        System.out.println(result);

    }

    public static int match(String s, String t){
        List<String> sub = getSub(s);
        Set<String> list = new TreeSet<>();
        char[] str = t.toCharArray();
        getZi(str,0,"",list);
        List<String> zi = new ArrayList<>(list);
        int result = 0;
        for (int i=0;i<sub.size();i++){
            String a1 = sub.get(i);
            for (int j=0;j<zi.size();j++){
                String a2 = zi.get(j);
                if (a1.length() == a2.length() && a1.equals(a2)){
                    result++;
                    zi.remove(j);
                    break;
                }
            }
        }
        return result;
    }

    public static List<String> getSub(String s){
        Set<String> result = new TreeSet<>();
        if (s.length() == 0){
            return new ArrayList<>(result);
        }
        for (int i = 1;i<=s.length();i++){
            for (int j =0;j+i<=s.length();j++){
                String sub = s.substring(j,j+i);
                result.add(sub);
            }
        }
        return new ArrayList<>(result);
    }

    public static void getZi(char[] str, int i, String res,Set<String> list){
        if (i == str.length){
            list.add(res);
            return;
        }else{
            getZi(str,i+1,res,list);
            getZi(str,i+1,res+str[i],list);
        }
    }



    //----------------------------------------------------------------------
    public static void ansMethod2() {
        Scanner c = new Scanner(System.in);
        int n = c.nextInt();
        int m = c.nextInt();
        String s = c.next();
        String t = c.next();
        int ratio = 0;
        for (int i =0;i<s.length();i++){
            for (int j=i+1;j<=s.length();j++){
                String temp = s.substring(i,j);
                for (int k=0,u=0;k<=t.length();k++){
                    if (u == temp.length()){
                        ratio++;
                        break;
                    }else if(k<t.length() && temp.charAt(u) == t.charAt(k)){
                        u++;
                    }
                }
            }
        }
        System.out.println(ratio);
    }
}
