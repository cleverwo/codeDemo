package LeetCodeDemo;

import java.util.regex.Pattern;

/**
 * @Auther: 10413
 * @Date: 2020/3/20 18:13
 * @Description:
 * 468. 验证IP地址
 */
public class Solution468 {

    /**
     * ip验证，首先直到规则，
     * ipv4  4组 0~255，IPv4 地址内的数不会以 0 开头。
     * ipv6，8组16机制的数
     */
    // 0~9 | 10~99 | 100~199 | 200~249|250~255
    String chunkIPv4 = "([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])";

    Pattern pattenIPv4 =
            Pattern.compile("^(" + chunkIPv4 + "\\.){3}" + chunkIPv4 + "$");

    String chunkIPv6 = "([0-9a-fA-F]{1,4})";
    Pattern pattenIPv6 =
            Pattern.compile("^(" + chunkIPv6 + "\\:){7}" + chunkIPv6 + "$");
    public String validIPAddress(String IP) {
        if (IP.contains(".")) {
            return (pattenIPv4.matcher(IP).matches()) ? "IPv4" : "Neither";
        }
        else if (IP.contains(":")) {
            return (pattenIPv6.matcher(IP).matches()) ? "IPv6" : "Neither";
        }
        return "Neither";
    }

}
