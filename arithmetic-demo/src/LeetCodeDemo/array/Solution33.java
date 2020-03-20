package LeetCodeDemo.array;

/**
 * @Auther: 10413
 * @Date: 2020/3/19 18:23
 * @Description:
 */
public class Solution33 {

    /**
     * 这里需要注意的是二分查找的数组默认是有序的，这里数组旋转了，但基本有序
     * 基本理论就是：
     *  nums[0] <= nums[mid]（0 - mid不包含旋转）且nums[0] <= target <= nums[mid] 时 high 向前规约；
     *  nums[mid] < nums[0]（0 - mid包含旋转），target <= nums[mid] < nums[0] 时向前规约（target 在旋转位置到 mid 之间）
     *
     *  找到分界点，对分界点再用二分查找。
     *
     *  还是做错了，，
     *
     */
    public int search(int[] nums, int target) {
        if(nums==null||nums.length==0){
            return -1;
        }
        int n =nums.length;
        int low = 0,high = n-1;
        while (low<=high){
            int mid = (low+ high) / 2;
            if (target<nums[mid]){
                // low - mid 有序的 target 在 其内
                if (target>=nums[low]&&nums[low]<nums[mid]){
                    high = mid-1;
                }else{
                    //low - mid 有旋转
                    low = mid+1;
                }
            }else if(target>nums[mid]){
                // low - mid 有序的 target 在 其内
                if (target<=nums[high]&&nums[mid]<nums[high]){
                    low = mid+1;
                }else{
                    //low - mid 有旋转
                    high = mid-1;
                }
            }else{
                return mid;
            }
        }
        return -1;
    }

    /**
     * 答案：分析：
     * if nums[0] <= nums[I] 那么 nums[0] 到 nums[i] 为有序数组,那么当 nums[0] <= target <= nums[i] 时我们应该在 0-i0−i 范围内查找；
     * if nums[i] < nums[0] 那么在 0-i0−i 区间的某个点处发生了下降（旋转），那么 I+1I+1 到最后一个数字的区间为有序数组，并且所有的数字都是小于 nums[0] 且大于 nums[i]，当target不属于 nums[0] 到 nums[i] 时（target <= nums[i] < nums[0] or nums[i] < nums[0] <= target），我们应该在 0-i0−i 区间内查找。
     * 总结为：
     * nums[0] <= target <= nums[i]    从0-i找
     *                target <= nums[i] < nums[0]   从0-i找
     *                          nums[i] < nums[0] <= target   从i-n找
     *
     *  所以我们进行三项判断：
     * (nums[0] <= target)， (target <= nums[i]) ，(nums[i] < nums[0])，现在我们想知道这三项中有哪两项为真（明显这三项不可能均为真或均为假（因为这三项可能已经包含了所有情况））
     * 所以我们现在只需要区别出这三项中有两项为真还是只有一项为真。
     * 使用 “异或” 操作可以轻松的得到上述结果（两项为真时异或结果为假，一项为真时异或结果为真，可以画真值表进行验证）
     * 之后我们通过二分查找不断做小 target 可能位于的区间直到 low==high，此时如果 nums[low]==target 则找到了，如果不等则说明该数组里没有此项。
     *
     *
     */
    public int search1(int[] nums, int target){
        if(nums==null||nums.length==0){
            return -1;
        }
        int low = 0,high = nums.length-1;
        while(low<high){
            int mid = (low+high)/2;
            //这里的异或逆天了，，自己再写一个翻译一下这里的意思 两个成立记为真，则在0-i中，不然就在i-mid中
            if ((nums[0]>target)^(nums[0]>nums[mid])^(nums[mid]<target)){
                low = mid + 1;
            }else{
                high = mid;
            }
        }
        return low==high&&nums[low]==target?low:-1;
    }

    /**
     * 自己在翻译一下上面的意思
     */
    public int search1_1(int[] nums, int target){
        int low = 0,high = nums.length-1;
        while (low<=high){
            int mid = (low + high) /2;
            if (nums[mid]==target){
                return mid;
            }
            if(nums[low] <= nums[mid]){  //左边升序
                if(nums[low]<=target && target <= nums[mid]){//在左边范围内
                    high = mid-1;
                }else{//只能从右边找
                    low = mid+1;
                }

            }else{ //右边升序
                if(nums[mid] <= target && target <= nums[high]){//在右边范围内
                    low = mid +1;
                }else{//只能从左边找
                    high = mid-1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Solution33 s = new Solution33();
        int[] a = {4,5,6,7,8,1,2,3};
        System.out.println(s.search(a,8));
    }
}
