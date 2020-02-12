package OfferDemo;

/**
 * @Auther: 10413
 * @Date: 2020/1/28 09:14
 * @Description:
 * 6.旋转数组的最小数字
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * 输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
 * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
 * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
 */
public class Test6 {
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
}
