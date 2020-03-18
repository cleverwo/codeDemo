package LeetCodeDemo.array;


/**
 * @Auther: 10413
 * @Date: 2020/3/14 16:55
 * @Description:
 * 11. 盛最多水的容器
 */
public class Solution11 {

    /**
     * 暴力求解
     */
    public int maxArea(int[] height) {
        int max = Integer.MIN_VALUE;
        int n = height.length;
        for (int i=0;i<n;i++){
            for (int j=i+1;j<n;j++){
                int container = Math.min(height[i],height[j])*(j-i);
                System.out.println(i+" + "+ j+ " : "+container);
                if (max < container){
                    max = container;
                }
            }
        }
        return max;
    }

    /**
     * 双指针是有思路的
     * 让他的长最长就是数组的长度，这时禁锢容量的是高，就让小的移动
     */
    public int maxArea1(int[] height) {
        if (height==null||height.length==0){
            return 0;
        }
        int n = height.length;
        int i =0,j=n-1;
        int max = Integer.MIN_VALUE;
        while (i<j){
            int length = j-i;
            int high = Math.min(height[i],height[j]);
            if (max<(length*high)){
                max = length*high;
            }
            if (high==height[i]){
                i++;
            }else {
                j--;
            }
            System.out.println(i+" + "+ j+ " : "+length*high);
        }
        return max;
    }

    public static void main(String[] args) {
        Solution11 s = new Solution11();
        int[] a = {1,8,6,2,5,4,8,3,7};
        //s.maxArea(a);
        System.out.println("--------------------------------");
        s.maxArea1(a);

        //System.out.println(s.maxArea(a));
        //System.out.println(s.maxArea1(a));
    }
}
