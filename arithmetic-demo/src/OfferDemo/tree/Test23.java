package OfferDemo.tree;

import OfferDemo.node.TreeNode;

import java.util.Stack;

/**
 * @Auther: 10413
 * @Date: 2020/2/15 20:48
 * @Description: 23.二叉搜索树的后序遍历
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
 * 如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。
 */
public class Test23 {

    /**
     * 做题前先复习下二叉搜索树BST，二叉排序树
     * 左子树《根节点《右子树
     * 左子树非空，则左子树所有节点小于根节点
     * 右子树非空，则右子树所有节点大于根节点
     * 左右子树本事又是一个BST
     */

    public void PostOrder(TreeNode t) {
        if (t != null) {
            PostOrder(t.left);
            PostOrder(t.right);
            System.out.println(t.val);
        }
    }

    /**
     * 答案1
     * 获得根节点找左右子树的边界，边界两边符合 左《根《右的规则，不和规则为非BST
     * 递归方法的最终复杂度是O(n*logn)
     *
     * @param sequence
     * @return
     */
    public boolean VerifySquenceOfBST(int[] sequence) {
        //前提条件
        if (sequence == null || sequence.length == 0) {
            return false;
        }
        return isBST(sequence, 0, sequence.length - 1);
    }

    /**
     * 找根节点对应的两边子树，
     * 后续遍历时先左子树，在右子树，在根，而左子树《根《右子树
     * 所以，提前出根结点，找左边《右边的边界，将找子树的根在分为两个边界
     * <p>
     * sequence 带判定的后序遍历数组
     * start 遍历起始节点下标，可能为左子树或者左子树为空时为右子树，都为空时为根节点
     * root 根节点下标
     */
    private boolean isBST(int[] sequence, int start, int root) {
        // 起始节点下标大于或等于根节点下标 为bst树，当==时即时bst了
        if (start >= root) {
            return true;
        }
        //获取根节点的值
        int key = sequence[root];
        int i;
        //找到左右子数的分界点，分界点左边都小于根节点，分界点右边都大于根节点，根据这个特性获取分界点
        // 最总坐标i落到右子树的后续遍历的第一个元素上
        for (i = start; i < root; i++) {
            if (sequence[i] > key) {
                break;
            }
        }
        //在右子树中判断是否含有小于root的值，如果有返回false
        //遍历右子树开始到数组结尾，这些应该都大于根节点，出现小于根节点的不是BST树
        for (int j = i; j < root; j++) {
            if (sequence[j] < key) {
                return false;
            }
        }
        //这是从外往里判断，如：
        // BST树：中序：3，4，5，9，10，11，12 后续：3，4，9，5，1，12，11，10
        // 先判断 3，4，9，5 | 10 |12，11 总体10为根节点的树是否为BST树
        // 在判断其子树是否为bst树，即 3，4，|5|9 和 12|11| 是否为bst树
        // 左子树，和右子树判断，直到start数组起止点大于结尾点，数组遍历完了
        return isBST(sequence, start, i - 1) && isBST(sequence, i, root - 1);
    }

    /**
     * 模拟练习答案1
     */
    public boolean verifySequenceIsBST(int[] array) {
        if (array == null || array.length == 0) {
            return false;
        }
        return isBST1(array, 0, array.length - 1);
    }

    //array为判定数组，start为数组起始坐标，end为数组结束坐标
    public boolean isBST1(int[] array, int start, int end) {
        //数组遍历完的标值
        if (start >= end) {
            return true;
        }
        int rootKey = array[end];
        int i = 0;
        while (i < end) {
            if (array[i] > rootKey) {
                break;
            }
            i++;
        }
        for (int j = i; j < end; j++) {
            if (array[j] < rootKey) {
                return false;
            }
        }
        return isBST1(array, 0, i - 1) && isBST1(array, i, end - 1);
    }

    /**
     * 答案2 数值上下边界判定bst 时间On 空间On
     * 在对序列进行遍历时，能否仅根据元素当前的递增递减趋势，
     * 以及过往历史留下的信息记录，就可以对序列的合法性作出判断呢
     * <p>
     * 对于任一棵子树，它的根节点约束了它左右子树的取值范围，
     * 子树的根root是它左子树值的上限（max），同时是它右子树值的下限（min）
     *
     * @param sequence
     * @return
     */
    public boolean verifySequenceIsBST2(int[] sequence) {
        if (sequence.length < 1) {
            return false;
        } else if (sequence.length < 3) {
            return true;
        } else {
            int idx = sequence.length - 1;
            Stack<Integer> bound_min = new Stack<>();
            Stack<Integer> bound_max = new Stack<>();
            Stack<Integer> roots = new Stack<>();
            roots.push(sequence[idx--]);
            bound_min.push(Integer.MIN_VALUE);
            bound_max.push(Integer.MAX_VALUE);
            for (; idx > -1; idx--) {
                if (sequence[idx] > sequence[idx + 1]) {
                    // 倒序遍历趋势为递增，说明是进入了某个右子树
                    if (sequence[idx] > bound_max.peek()) {
                        // 当前元素超越了最大上限约束，这是不合法的
                        return false;
                    } else {
                        // 合法，进入右子树，更新三个栈
                        bound_min.push(roots.peek());
                        bound_max.push(bound_max.peek());
                        roots.push(sequence[idx]);
                    }
                } else {
                    // 倒序遍历趋势为递减，说明是进入了某个左子树
                    if (sequence[idx] < bound_min.peek()) {
                        // 当前元素打破了最小下限约束，说明是右子树遍历完了，跳转到兄弟左子树
                        // 当前元素为兄弟左子树的根，之前右子树节点全部出栈
                        while (sequence[idx] < bound_min.peek()) {
                            bound_min.pop();
                            bound_max.pop();
                            roots.pop();
                        }
                    } else {
                    } // 没有突破下限，说明是右子树不存在，直接进入左子树，不做特殊处理
                    // 进入左子树，更新三个栈
                    bound_min.push(bound_min.peek());
                    bound_max.push(roots.peek());
                    roots.push(sequence[idx]);
                }
            }
            return true;
        }
    }

    /**
     * 模拟答案2
     */
    public boolean verifySequenceIsBST2_2(int[] sequence) {
        if (sequence == null || sequence.length == 0) {
            return false;
        }
        int idx = sequence.length - 1;
        Stack<Integer> bound_min = new Stack<>();
        Stack<Integer> bound_max = new Stack<>();
        Stack<Integer> roots = new Stack<>();
        roots.push(sequence[idx--]);
        bound_min.push(Integer.MIN_VALUE);
        bound_max.push(Integer.MAX_VALUE);
        for (; idx >=0; idx--) {
            if (sequence[idx] > sequence[idx + 1]) {
                // 倒序遍历趋势为递增，说明是进入了某个右子树
                if (sequence[idx] > bound_max.peek()) {
                    // 当前元素超越了最大上限约束，这是不合法的
                    return false;
                } else {
                    // 合法，进入右子树，更新三个栈
                    bound_min.push(roots.peek());
                    bound_max.push(bound_max.peek());
                    roots.push(sequence[idx]);
                }
            } else {
                // 倒序遍历趋势为递减，说明是进入了某个左子树
                if (sequence[idx] < bound_min.peek()) {
                    // 当前元素打破了最小下限约束，说明是右子树遍历完了，跳转到兄弟左子树
                    // 当前元素为兄弟左子树的根，之前右子树节点全部出栈
                    while (sequence[idx] < bound_min.peek()) {
                        bound_min.pop();
                        bound_max.pop();
                        roots.pop();
                    }
                } else {
                } // 没有突破下限，说明是右子树不存在，直接进入左子树，不做特殊处理
                // 进入左子树，更新三个栈
                bound_min.push(bound_min.peek());
                bound_max.push(roots.peek());
                roots.push(sequence[idx]);
            }
        }
        return true;
    }
}
