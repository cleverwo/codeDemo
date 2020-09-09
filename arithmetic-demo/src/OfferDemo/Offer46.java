package OfferDemo;

/**
 * @Auther: 10413
 * @Date: 2020/9/7 16:01
 * @Description:
 * 46 把数字翻译成字符串 中等
 */
public class Offer46 {

    // 46 把数字翻译成字符串 动规+滚动数组
    /*
     * 思路： 首先只有0-25 的数字可以翻译成字符， 01，02这样的数字不合法
     *   动规：
     *      找前i的数组 开始翻译为
     * */
    public int translateNum(int num) {
        String src = String.valueOf(num);
        int p = 0, q = 0, r = 1;
        for (int i = 0; i < src.length(); ++i) {
            p = q;
            q = r;
            r = 0;
            r += q;
            if (i == 0) {
                continue;
            }
            String pre = src.substring(i - 1, i + 1);
            if (pre.compareTo("25") <= 0 && pre.compareTo("10") >= 0) {
                r += p;
            }
        }
        return r;
    }

}
