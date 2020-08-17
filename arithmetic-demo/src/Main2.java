import OfferDemo.node.ListNode;
import OfferDemo.node.TreeNode;
import java.util.*;

/**
 * @Auther: 10413
 * @Date: 2020/3/27 09:08
 * @Description:
 */
public class Main2 {

    //工具类 层次打印二叉树
    private static void printTree(TreeNode t) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        //创建一个队列，用于存储当前节点的所有相邻节点
        Queue<TreeNode> queue = new LinkedList();
        queue.add(t);
        boolean flag = true;
        while (!queue.isEmpty() && flag) {
            flag = false;
            //记录当前节点相邻结点的个数
            int size = queue.size();
            ArrayList<Integer> list = new ArrayList<>();
            //遍历当前节点的所有相邻节点，并将遍历过的节点的相邻节点放到队列中
            for (int i = 0; i < size; i++) {
                TreeNode temp = queue.poll();
                if (temp == null) {
                    list.add(-1);
                    queue.add(null);
                    queue.add(null);
                } else {
                    list.add(temp.val);
                    if (temp.left != null) {
                        queue.add(temp.left);
                        flag = true;
                    } else {
                        queue.add(null);
                    }
                    if (temp.right != null) {
                        queue.add(temp.right);
                        flag = true;
                    } else {
                        queue.add(null);
                    }
                }
            }
            result.add(list);
        }
        for (List<Integer> now : result) {
            for (Integer num : now) {
                if (num == -1) {
                    System.out.print("#" + " ");
                } else {
                    System.out.print(num + " ");
                }
            }
            System.out.println();
        }
    }
    //打印链表
    public static void printListNode(ListNode r){
        while (r!=null){
            System.out.print(r.val +  " ");
            r= r.next;
        }
    }
    //新建链表
    public static ListNode addListNode(int[] num){
        ListNode r = new ListNode(0);
        ListNode p = r;
        for (int i =0;i<num.length;i++){
            ListNode n = new ListNode(num[i]);
            ListNode q = p.next;
            p.next = n;
            n.next = q;
            p = p.next;
        }
        return r.next;
    }

    //1二维数组的查找
    public boolean Find(int target, int[][] array) {
        int n = array.length;
        int m = n > 0 ? array[0].length : 0;
        int i = n - 1, j = 0;
        while (i >= 0 && j < m) {
            if (array[i][j] == target) {
                return true;
            } else if (array[i][j] > target) {
                i--;
            } else {
                j++;
            }
        }
        return false;
    }

    //2替换空格
    public String replaceSpace(StringBuffer str) {
        return str.toString().replaceAll(" ", "%20");
    }

    //3从尾到头打印链表
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> list = new ArrayList<>();
        ListNode tmp = listNode;
        while (tmp != null) {
            list.add(0, tmp.val);
            tmp = tmp.next;
        }
        return list;
    }

    //4重建二叉树
    public static TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < in.length; i++) {
            map.put(in[i], i);
        }
        TreeNode root = new TreeNode(pre[0]);
        TreeNode t = root, pret = root;
        int val = 0;
        for (int i = 1; i < pre.length; i++) {
            int now = pre[i];
            // 从头遍历二叉树找now待插入的节点位置
            while (t != null) {
                pret = t;
                if (map.get(now) < map.get(t.val)) {
                    t = t.left;
                    val = 0;
                } else {
                    t = t.right;
                    val = 1;
                }
            }
            if (val != 0) {
                pret.right = new TreeNode(now);
            } else {
                pret.left = new TreeNode(now);
            }
            //重置t为根节点
            t = root;
        }
        return root;
    }
    public static TreeNode reConstructBinaryTree1(int[] pre, int[] in) {
        if (pre.length == 0 || in.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(pre[0]);
        // 在中序中找到前序的根
        for (int i = 0; i < in.length; i++) {
            if (in[i] == pre[0]) {
                // 左子树，注意 copyOfRange 函数，左闭右开
                root.left = reConstructBinaryTree(Arrays.copyOfRange(pre, 1, i + 1), Arrays.copyOfRange(in, 0, i));
                // 右子树，注意 copyOfRange 函数，左闭右开
                root.right = reConstructBinaryTree(Arrays.copyOfRange(pre, i + 1, pre.length), Arrays.copyOfRange(in, i + 1, in.length));
                break;
            }
        }
        return root;
    }

    //5两个栈实现队列
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();
    public void push(int node) {
        if (stack1.isEmpty()) {
            stack1.push(node);
        } else {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
            stack1.push(node);
            while (!stack2.isEmpty()) {
                stack1.push(stack2.pop());
            }
        }
    }
    public int pop() {
        return stack1.pop();
    }
    public void push1(int node) {
        stack1.push(node);
    }
    public int pop1() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

    //6旋转数组的最小数字
    public int minNumberInRotateArray(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int n = array.length;
        int low = 0;
        int high = n - 1;
        while (low < high) {
            if (array[low] < array[high]) {
                return array[low];
            }
            int mid = (low + high) / 2;
            if (array[mid] > array[low]) {
                // low -mid 为递增的
                low = mid + 1;
            } else if (array[mid] < array[high]) {
                high = mid;
            } else {
                low++;
            }
        }
        return array[low];
    }

    //7斐波那契数列
    public int Fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        return Fibonacci(n - 1) + Fibonacci(n - 2);
    }

    //8跳台阶
    public int JumpFloor(int target) {
        int[] num = new int[target + 2];
        num[1] = 1;
        num[2] = 2;
        for (int i = 3; i <= target; i++) {
            num[i] = num[i - 1] + num[i - 2];
        }
        return num[target];
    }

    //9变态跳台阶
    public int JumpFloorII(int target) {
        int[] num = new int[target + 2];
        num[1] = 1;
        num[2] = 2;
        for (int i = 3; i <= target; i++) {
            for (int j = i - 1; j > 0; j--) {
                num[i] += num[i - j];
            }
            num[i] += 1;
        }
        return num[target];
    }

    //10矩形覆盖
    public int RectCover(int target) {
        int[] num = new int[target + 3];
        num[0] = 0;
        num[1] = 1;
        num[2] = 2;
        for (int i = 3; i <= target; i++) {
            num[i] = num[i - 1] + num[i - 2];
        }
        return num[target];
    }

    //11二进制中1的个数
    public int NumberOf1(int n) {
        int sum = 0, flag = 1;
        while (flag != 0) {
            if ((n & flag) != 0) {
                sum++;
            }
            flag <<= 1;
        }
        return sum;
    }
    public int NumberOf11(int n) {
        int sum = 0;
        while (n != 0) {
            sum++;
            n = n & (n - 1);
        }
        return sum;
    }

    //12数值的整数次方
    public double Power(double base, int exponent) {
        double result = Math.pow(base, exponent);
        return result;
    }

    //13调整数组顺序使奇数位于偶数前面
    public void reOrderArray(int[] array) {
        if (array == null || array.length == 0) {
            return;
        }
        int i = 0, j = i + 1;
        int n = array.length;
        while (i < n && (array[i] & 1) == 1) {
            i++;
            j = i + 1;
        }
        while (j < n) {
            if ((array[j] & 1) == 0) {
                j++;
            } else {
                int temp = array[j];
                for (int k = j; k > i; k--) {
                    array[k] = array[k - 1];
                }
                array[i] = temp;
                i++;
            }
        }
    }

    //14链表中倒数第k个结点
    public ListNode FindKthToTail(ListNode head, int k) {
        ListNode quick = head, slow = head;
        while (quick != null && k > 0) {
            quick = quick.next;
            k--;
        }
        if (k > 0) {
            return null;
        }
        while (slow!=null&&quick!=null){
            slow = slow.next;
            quick = quick.next;
        }
        return slow;

    }

    //15反转链表
    public ListNode ReverseList(ListNode head){
        ListNode t = new ListNode(0);
        while (head!=null){
            ListNode q = head.next;
            head.next = t.next;
            t.next = head;
            head = q;
        }
        return t.next;

    }

    //16合并两个排序的链表
    public ListNode Merge(ListNode list1, ListNode list2){
        ListNode root = new ListNode(0);
        ListNode p = root;
        while (list1!=null&&list2!=null){
            if (list1.val>list2.val){
                p.next = list2;
                list2 = list2.next;
            }else{
                p.next = list1;
                list1 = list1.next;
            }
            p = p.next;
        }
        if (list1!=null){
            p.next = list1;
        }else{
            p.next=list2;
        }
        return root.next;
    }

    //17树的子结构
    public boolean HasSubtree(TreeNode root1,TreeNode root2) {
        if (root1!=null||root2 == null){
            return false;
        }
        return isSubTree(root1,root2)||isSubTree(root1.left,root2)||isSubTree(root1.right,root2);

    }
    private boolean isSubTree(TreeNode r1,TreeNode r2){
        if (r1==null){
            return false;
        }
        if (r2==null){
            return true;
        }
        if (r1.val!=r2.val){
            return isSubTree(r1.right,r2)||isSubTree(r1.left,r2);
        }else{
            return isSubTree(r1.left,r2.left)&&isSubTree(r1.right,r2.right);
        }
    }

    //18二叉树的镜像
    public void Mirror(TreeNode root) {
        if (root==null){
            return;
        }
        TreeNode temp = root.left;
        root.right = root.left;
        root.left = temp;
        Mirror(root.left);
        Mirror(root.right);
    }

    //19顺时针打印矩阵
    public ArrayList<Integer> printMatrix(int [][] matrix) {
        return  null;
    }

    public static void main(String[] args) {
        Main2 m = new Main2();
        ListNode l1 = addListNode(new int[]{1,3,5});
        ListNode l2 = addListNode(new int[]{2,4,6});
        printListNode(m.Merge(l1,l2));
    }

}
