package OfferDemo;

/**
 * @Auther: 10413
 * @Date: 2020/1/28 09:14
 * @Description:
 * 6.旋转数组的最小数字
 */
public class Test6 {
    //考察二分查找 二分查找的变种
    /*
    1 二分查找 注意边界处理
    2 low < high 表示没有旋转，因为low<high 表示low-high为非递减数组,low为最小比谁都小
    3 分情况
        1 low < mid 则证明最小值一定在mid+1——high 中，low-mid为有序的，反转的在后面
        2 high < mid 则最小值在low-mid中，可能为mid，low-mid无序，mid-high有序
        3 mid >low mid>high 还有一个mid = low或high，此时必然是逆序的是时候，因为顺序时 l<h 已经判断了，l++即可
    最终l-h指向其升序的数据，最小值为l，即low位置上的数。
     */
    public int minNumberInRotateArray1(int [] array) {
        if (array==null||array.length<=0){
            return 0;
        }
        int low =0;
        int high = array.length-1;
        int mid =0;
        while (low<high){
            // low < high 表示没有旋转，因为low<high 表示low-high为非递减数组,low为最小比谁都小
            if (array[low]<array[high]){
                return array[low];
            }
            mid = (low+high)/2;
            //分3种情况：
            //1 low < mid 则证明最小值一定在mid+1——high 中，low-mid为有序的，反转的在后面 l<h已经判断了
            //2 mid < high 则最小值在low-mid中，可能为mid，low-mid无序，mid-high有序
            //3 low >= mid mid>=high 还有一个mid = low或high，此时必然是逆序的是时候，因为顺序时 l<h 已经判断了，l++即可
            // 最终l-h指向其升序的数据，最小值为l，即low位置上的数。
            if (array[low]<array[mid]){
                //表示   mid为大值  low-mid有序的， mid-high 为无序的，最小的在mid-high中
                low = mid +1;
            }else if(array[mid]<array[high]){
                high = mid;
            }else{
                low++;
            }
        }
        return array[low];
    }
    //------------------------------------------------
    public int minArray(int[] numbers){
        if (numbers.length == 0){
            return 0;
        }
        int l = 0, h = numbers.length-1;
        while (l<h){
            if (numbers[l]< numbers[h]){
                return numbers[0];
            }
            int mid = l + (h-l) /2;
            if (numbers[l]<numbers[mid]){
                l = mid+1;
            }else if(numbers[h] > numbers[mid]){
                h = mid;
            }else{
                h--;
            }
        }
        return numbers[h];
    }
    //-----------------------------------------------
    /**
     * 时间:O(n) 空间:O(1)
     * 暴力查找从头到尾遍历，当前数大于他的后一个数时表示有旋转
     * 最小值为后一个数，没有这种情况表示没有旋转，最小值为第一个数
     */
    public int minNumberInRotateArray(int [] array) {
        if(array.length <= 0){
            return 0;
        }
        for(int i=0;i<array.length-1;i++){
            if (array[i]>array[i+1]){
                return array[i+1];
            }
        }
        return array[0];
    }
    //-------------------------------------------------
    public static void main(String[] args) {
        Test6 t = new Test6();
        int[] a = {10,1,10,10,10};
        System.out.println(t.minNumberInRotateArray1(a));
    }
}
