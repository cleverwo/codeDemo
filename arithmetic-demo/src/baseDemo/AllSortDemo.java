package baseDemo;

/**
 * @Auther: 10413
 * @Date: 2020/9/2 22:40
 * @Description:
 *  基本算法：
 *  全排序
 */
public class AllSortDemo {


    // 获取str 的下一个字典序 从小到大输出
    // 若str为最总的字典序返回 finish
    public String nextString(String str){
        char [] array = str.toCharArray();
        int length = str.length();
        // 第一步，从右往左找第一个对正序对
        // i的起始位置为数组的倒数第二个元素
        int i = length-2;
        // i的判断是i从右往左找，找不大于左边界，且不升序的数i
        // i》= i+1 为找第一个正序对的逆， 在正数对上i会停止循环
        while(i>=0&&array[i] >= array[i+1]){
            i--;
        }
        // 如果i==-1 证明str为降序的数组，为字典排序的最后一个数，返回；
        if(i == -1){
            return "finish";
        }
        // 第二步，找出i开始到数组结尾中，比i大的最小数
        int j = length-1;
        // 从右到左，如果i》=j 则 j--，j停留在i《j的位置上
        for(; j>=0 && array[j] <= array[i]; j--){}
        //第三步，交换i j的位置
        char tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
        //第四步，反转 从i+1到结尾
        for(int a=i+1, b=length-1; a<b;a++,b--){
            // a指向头，b指向尾，首位互换
            tmp = array[a];
            array[a] = array[b];
            array[b] = tmp;
        }
        return new String(array);
    }

    // 获取上一个字典序， 从大到小输出
    public int[] allSortDown(int[] nums) {
        if (nums.length == 0){
            return null;
        }
        int n = nums.length;
        int i = n-2;
        while (i>=0&& nums[i] <= nums[i+1]){
            i--;
        }
        if (i==-1){
            return null;
        }
        int j = n-1;
        while (j>=0&& nums[j] >= nums[i]){
            j--;
        }
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
        for (int l= i+1, h= n-1;l<h;l++,h--){
            int curr = nums[l];
            nums[l] = nums[h];
            nums[h] = curr;
        }
        return nums;
    }
}
