package LeetCodeDemo;


/**
 * @Auther: 10413
 * @Date: 2020/4/2 15:46
 * @Description:
 * 221. 最大正方形
 */
public class Solution221 {

    /**
     * ac 不通过
     * 没有考虑的情况：
     * 1 1 1 1 1 1 1 1
     * 1 1 1 1 1 1 1 0
     * 1 1 1 1 1 1 1 0
     * 1 1 1 1 1 0 0 0
     * 0 1 1 1 1 0 0 0
     * 遍历第二行时，找的s=0，e=7，不符合，之后s=1，e=7不符合，之后找了s=2，e=7，跳过了s=1，e=5的情况
     * @param matrix
     * @return
     */
    public int maximalSquare(char[][] matrix) {
        int n = matrix.length;
        int m = n>0?matrix[0].length:-1;
        int max = 0;
        int s=0,e=0;
        for (int i =0;i<n;i++){
            for (int j=0;j<m;j++){
                if (matrix[i][j]=='1'){
                    s=j;e=j;
                    while (e<m&&matrix[i][e]=='1'){
                        e++;
                    }
                    int length = e-s;
                    if (length==1){
                        max = Math.max(1,max);
                    }else if(length>1){
                        boolean flag = false;
                        //先判读是否在界限内
                        if (i+length<=n){
                            for (int k=i+1;k<i+length;k++){
                                for (int l=s;l<e;l++){
                                    if (matrix[k][l]!='1'){
                                        flag=true;
                                        break;
                                    }
                                }
                                if(flag){
                                    break;
                                }
                            }
                            if (!flag){
                                int len = e-s;
                                max = Math.max(max,len*len);
                            }
                        }
                    }
                }
            }
        }
        return max;
    }

    /**
     * 暴力法，必须掌握的方法
     * @param matrix
     * @return
     */
    public int maximalSquare1(char[][] matrix){
        int n = matrix.length;//matrix有几行 -- 正方形高长度
        int m = n>0?matrix[0].length:-1;//matrix有几列 -- 正方形行的长度
        int max = 0;
        for (int i =0;i<n;i++){  // i-n
            for (int j=0;j<m;j++){ // j-m
                if (matrix[i][j]=='1'){
                    // 〇 初始化长度为1
                    int sqlen = 1;
                    boolean flag = true;
                    //① 判断正方形高度是否小于n，正方形的长度小于m，标志flag表示sqlen里全是1
                    while (i+sqlen<n&&j+sqlen<m&&flag){
                        //② 已经直到sqlen长度为1了，以i，j为顶点的长度为2的正方形是不是全1
                        //② 判断长度sqlen+1，i确定为正方形加1的长度，判读这一行，长度2是不是全是1
                        for(int k =j;k<=j+sqlen;k++){
                            if (matrix[i+sqlen][k]=='0'){
                                flag = false;
                                break;
                            }
                        }
                        // ③判断长度sqlen+1，j确定为正方形加1的长度，判读这一列，长度2是不是全是1
                        for (int k = i;k<=i+sqlen;k++){
                            if (matrix[k][j+sqlen] == '0'){
                                flag = false;
                                break;
                            }
                        }
                        // ④ 上面判断了长和列了，如果符合那么sqlen+1为新的正方形，在以i，j为顶点的情况下
                        if (flag){
                            sqlen++;
                        }
                    }
                    // ⑤ 以i，j为顶点的最大正方形找到了，更新max
                    if (max<sqlen){
                        max = sqlen;
                    }
                }
            }
        }
        return max*max;
    }

    /**
     * 动态规划计算 分三步
     * ①建立同纬度的dp数组 全为0
     * ②dp中存的时以i，j为顶点的最大正方形的边长
     * ③那么dp(i,j) = min{ dp(i-1,j),dp(i-1,j-1),dp(i,j-1)}+1 为新的dp(i,j)的大小
     * ④记录dp中的最大值max 放回max*max即可
     * 解析：
     *
     * 这里第二部中说dp中存的时最大正发行的边长，这里和暴力法不同的时，i，j为顶点遍历的时候把全数组都遍历了，这里i，j为
     * 顶点时，考虑的时向上向左兼并，就是i，j类似正方形的左下顶点。不是暴力的右上顶点。
     * 这里第三步的公式看出i>=1,j>=1，为了适应，这里的dp在多加一列一行，使i=0，j=0也适用，多出的一行一列全是0，而公式的意思
     * 在第二部的基础之上，dp[i][j-1]表示的时左边是否全是1，dp[i-1][j-1]表示了斜上角上1，dp[i-1][j]表示了上面的1，如果这些
     * 都是成立的，取最小的值，加上这个1，更新了dp[i][j]上正方形的大小，如果当前数组位置不是1，那么这里就是0，下一次用到这个边
     * 的最大就只是0+1了。
     * @param matrix
     * @return
     */
    public int maximalSquare2(char[][] matrix){
        int n = matrix.length;//matrix有几行 -- 正方形高长度
        int m = n>0?matrix[0].length:-1;//matrix有几列 -- 正方形行的长度
        int max = 0;
        int[][] dp = new int[n+1][m+1];
        //把dp 第0行0列隔过去, i,j表示的不是下标了而是第几行第几列
        for (int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if (matrix[i-1][j-1]=='1'){
                    dp[i][j] = Math.min(Math.min(dp[i-1][j],dp[i-1][j-1]),dp[i][j-1])+1;
                    max = Math.max(dp[i][j],max);
                }
            }
        }
        return max*max;
    }


}
