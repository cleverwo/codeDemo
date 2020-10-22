package OfferDemo;

/**
 * @Auther: 10413
 * @Date: 2020/2/19 17:25
 * @Description:
 * 35，数组中的逆数对
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
 * 输入一个数组,求出这个数组中的逆序对的总数P。并将P对1000000007取模的结果输出。 即输出P%1000000007
 *
 * 输入描述：
 * 题目保证输入的数组中没有的相同的数字
 * 数据范围：
 * 	对于%50的数据,size<=10^4
 * 	对于%75的数据,size<=10^5
 * 	对于%100的数据,size<=2*10^5
 */
public class Test35 {

    /**
     * 答案1
     * 用归并排序思想计算
     * 归并排序使用分治策略，序列一分为二(O(1))后，将子序列递归排序(2 * T(n / 2))，
     * 最后合并有序子序列(O(n)),T(n) = 2 * T(n / 2) + O(n) = O(n * logn)。
     * 归并排序算法
     */
    private int cnt;
    public int InversePairs1(int[] a){
        mergeSortCore(a,0,a.length-1);
        return cnt;
    }
    public void mergeSortCore(int[] a, int low, int high) {
        if (low >= high) {
            return;
        }
        //从中间划分2个子序
        int mid = (low+high)/2;
        //对左边进行递归排序
        mergeSortCore(a, low, mid);
        //对右边进行递归排序
        mergeSortCore(a, mid + 1, high);
        //合并
        merge(a, low, mid, high);
    }

    /**
     * 将数组arr中的 a：lo~mid， b: mid+1~hi 合并，已知，两个数组是有序的
     * [A，B]中的逆序对=[A]的逆序对+[B]中的逆序对+将A，B混排在一起的逆序对
     * 其中[A]是一个已经排序的子数组，其中的逆序对为AA，
     * [B]是另外一个已经排序的子数组，其中的逆序对为BB，然后我们用归并排序将A和B进行排序，
     * 此时发现可组成的逆序对为CC，[A B]这个数组 中的逆序对数目就=AA+BB+CC
     * 解释：
     * 归并是分而治之思想，只有在merge操作中才计算逆序对，则
     * 对于数组【4.3.6.9.7.1.2】 -》 4.3 6.9 7.1 2 在合并4.3.6.9中求的a的逆序对
     * 合并7.1.2中求得b的逆序对，而ab之间的合并中求得是a b的逆序对
     * 而总的逆序是 4.3.6.9 的逆序 7.1.2的逆序 和中4.3.6.9排序后7.1.2排序后 的逆序的和
     * 即 a+b+ab
     */
    public void merge(int[] arr, int lo,int mid, int hi) {
        //设立临时数组保存用于保存合并的 a b数组
        int[] temp = new int[hi - lo + 1];
        //设立起始坐标，p1用于遍历 a数组， p2用于遍历b数组，i遍历temp数组
        int i = 0, p1 = lo, p2 = mid + 1;
        //开始合并，p1 和 p2 遍历，取最小的填充到temp数组中
        while (p1 <= mid && p2 <= hi) {
            // p1 是前面的元素，p2是后面的元素
            if (arr[p1]<=arr[p2]){
                //如果前面的元素小于后面的不能构成逆序对
                temp[i++] = arr[p1++];
            }else{
                //如果前面的元素大于后面的，那么在前面元素之后的元素都能和后面的元素构成逆序对
                temp[i++] = arr[p2++];
                //这里逆序的求法 是 mid-i+1 的原因是：
                //例如 4，9，| 2，3 合并，mid是1，i从0~3
                //当4，2比较存在逆序，4存在，则9也存在，所以2的逆序是2，即mid+1-4坐标
                //当4，3比较时，4，9都存在逆序，3的逆序是，mid+1-4坐标
                cnt=(cnt + (mid-p1+1))%1000000007;
            }
        }
        //填充a 数组剩下的
        while (p1 <= mid) {
            temp[i++] = arr[p1++];
        }
        //填充b 数组剩下的
        while (p2 <= hi) {
            temp[i++] = arr[p2++];
        }
        // 将temp数组回填到arr数组中
        for (i = 0; i < temp.length; i++) {
            arr[lo + i] = temp[i];
        }
    }

    public static void main(String[] args) {
        int[] a = {4,9,2,3};
        Test35 t = new Test35();

        System.out.println(t.InversePairs1(a));
    }
}
