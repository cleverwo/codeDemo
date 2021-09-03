/**
 * @Auther: 10413
 * @Date: 2020-11-10 14:46
 * @Description:
 */
public class Test222 {
    public static void main(String[] args) {
        getResult("1aa2s");
    }

    public static  boolean getResult(String str){
        String a = "^([1-9]\\d*[a-zA-Z])*";
        boolean s = str.matches(a);
        if (s){
            String[] nums = str.split("[a-zA-Z]");
            String[] strs = str.split("[0-9]+");
            for (int i=0;i<nums.length;i++){
                int n = Integer.valueOf(nums[i]);
                while (n>0){
                    System.out.print(strs[i+1]);
                    n--;
                }
            }
        }else{
            throw new Error("ss");
        }
        return false;
    }

    public static void a(int a,String s){

    }
    public static void a(String s,int a){

    }
}
