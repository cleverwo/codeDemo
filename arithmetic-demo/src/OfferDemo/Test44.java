package OfferDemo;


/**
 * @Auther: 10413
 * @Date: 2020/2/21 18:13
 * @Description:
 * 44, 反转单词的顺序
 * 牛客最近来了一个新员工Fish，每天早晨总是会拿着一本英文杂志，写些句子在本子上。
 * 同事Cat对Fish写的内容颇感兴趣，有一天他向Fish借来翻看，但却读不懂它的意思。
 * 例如，“student. a am I”。后来才意识到，这家伙原来把句子单词的顺序翻转了，
 * 正确的句子应该是“I am a student.”。Cat对一一的翻转这些单词顺序可不在行，你能帮助他么？
 */
public class Test44 {

    /**
     * 思路：
     * 按空格截取在遍历返回
     * 有错误 入参“ ”遍历不通过
     * 将str.length() ==0 改为str.trim().length()==0
     * @param str
     * @return
     */
    public String ReverseSentence(String str) {
        if(str==null||str.trim().length() == 0){
            return "";
        }
        String[] strings = str.split(" ");
        StringBuffer stringBuffer = new StringBuffer();
        for (int i=strings.length-1;i>=0;i--){
            stringBuffer.append(strings[i]);
            if (i>0){
                stringBuffer.append(" ");
            }
        }
        return stringBuffer.toString();
    }


    /**
     * 答案：
     * 利用stringbuffer的reserve方法
     */
    public String ReverseSentence1(String str) {
        if(str.length()<=0){
            return "";
        }
        //反转整个句子
        StringBuffer st1=new StringBuffer(str);
        st1.reverse();
        //存放结果
        StringBuffer result=new StringBuffer();
        //标记空格数
        int blankNum=0,j=0;
        for(int i=0;i<st1.length();i++){
            //1、当有空格，且没有到达最后一个单词时
            if(st1.charAt(i)==' '&&(i!=st1.length()-1)){
                blankNum++;
                StringBuffer st2=new StringBuffer(st1.substring(j,i));
                result.append(st2.reverse().toString()).append(" ");
                j=i+1;
            }
            //2、当有空格，且到达最后一个单词时
            if(blankNum!=0&&i==(st1.length()-1)){
                StringBuffer st3=new StringBuffer(st1.substring(j,i+1));
                result.append(st3.reverse());
            }
        }
        //空格数为0时，直接返回原字符串
        if(blankNum==0){
            return str;
        }
        return result.toString();
    }

    /**
     *答案2
     * 剑指offer的思想，先翻转所有的字符，然后利用滑动窗口的思想，遇到' '就翻转，然后两者一起跳转到' '后重新滑动。
     */
    public String ReverseSentence2(String str) {
        if (str == null || str.trim().length() == 0){
            return str;
        }
        str = str.trim();
        //转char
        char[] chars = str.toCharArray();
        //反转字符
        reverseChars(chars, 0, str.length() - 1);
        // 利用滑动窗口
        // 遇到' '执行翻转
        int l = 0;
        int r = 0;
        while (l < str.length()) {
            if (chars[r] == ' ') {
                reverseChars(chars, l, r - 1);
                // 交换完之后,一起跳过' '
                while (chars[r] == ' '){
                    r++;
                }
                l = r;
            }
            if (r == str.length() - 1) {
                reverseChars(chars, l, r);
                // 到了最后交换玩就break，否则r会出现越界，可以在while中加对r的判断
                break;
            }
            r++;
        }
        return String.valueOf(chars);
    }
    //双指针对调反转
    private void reverseChars(char[] chars, int l, int r) {
        while (l < r) {
            char temp = chars[l];
            chars[l] = chars[r];
            chars[r] = temp;
            l++;
            r--;
        }
    }

    public String reverseWords(String s) {
        String[] strs = s.trim().split(" "); // 删除首尾空格，分割字符串
        StringBuilder res = new StringBuilder();
        for(int i = strs.length - 1; i >= 0; i--) { // 倒序遍历单词列表
            if(strs[i].equals("")) continue; // 遇到空单词则跳过
            res.append(strs[i] + " "); // 将单词拼接至 StringBuilder
        }
        return res.toString().trim(); // 转化为字符串，删除尾部空格，并返回
    }

    public static void main(String[] args) {
        Test44 t = new Test44();
        System.out.println(t.reverseWords("a good   example"));
    }
}
