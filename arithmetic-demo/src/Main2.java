import _modal.ListNode;
import _modal.TreeNode;

import java.util.*;

/**
 * @Auther: 10413
 * @Date: 2020/3/27 09:08
 * @Description:
 */
public class Main2 {

    // 重建二叉树
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

    // 旋转数组的最小数字
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

    //9 变态跳台阶
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

   //  把字符串转换成整数
   public int strToInt(String str) {
       char[] c = str.trim().toCharArray();
       if(c.length == 0) return 0;
       int res = 0, bndry = Integer.MAX_VALUE / 10;
       int i = 1, sign = 1;
       if(c[0] == '-') sign = -1;
       else if(c[0] != '+') i = 0;
       for(int j = i; j < c.length; j++) {
           if(c[j] < '0' || c[j] > '9') break;
           if(res > bndry || res == bndry && c[j] > '7') return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
           res = res * 10 + (c[j] - '0');
       }
       return sign * res;
   }

   // 剪绳子
   public int cuttingRope(int n) {
       if(n <= 3) return n - 1;
       int b = n % 3, p = 1000000007;
       long rem = 1, x = 3;
       for(int a = n / 3 - 1; a > 0; a /= 2) {
           if(a % 2 == 1) rem = (rem * x) % p;
           x = (x * x) % p;
       }
       if(b == 0) return (int)(rem * 3 % p);
       if(b == 1) return (int)(rem * 4 % p);
       return (int)(rem * 6 % p);
   }

   // 整数次幂
   public double myPow(double x, int n) {
       if(x == 0) return 0;
       long b = n;
       double res = 1.0;
       if(b < 0) {
           x = 1 / x;
           b = -b;
       }
       while(b > 0) {
           if((b & 1) == 1) res *= x;
           x *= x;
           b >>= 1;
       }
       return res;
   }

   //数字序列中某一位的数字
   public int findNthDigit(int n) {
       int digit = 1;
       long start = 1;
       long count = 9;
       while (n > count) { // 1.
           n -= count;
           digit += 1;
           start *= 10;
           count = digit * start * 9;
       }
       long num = start + (n - 1) / digit; // 2.
       return Long.toString(num).charAt((n - 1) % digit) - '0'; // 3.
   }

   // 矩阵中的路径 dfs
   public boolean exist(char[][] board, String word) {
       char[] words = word.toCharArray();
       for(int i = 0; i < board.length; i++) {
           for(int j = 0; j < board[0].length; j++) {
               if(dfs(board, words, i, j, 0)) return true;
           }
       }
       return false;
   }
    boolean dfs(char[][] board, char[] word, int i, int j, int k) {
        if(i >= board.length || i < 0 || j >= board[0].length || j < 0 || board[i][j] != word[k]) return false;
        if(k == word.length - 1) return true;
        char tmp = board[i][j];
        board[i][j] = '/';
        boolean res = dfs(board, word, i + 1, j, k + 1) || dfs(board, word, i - 1, j, k + 1) ||
                dfs(board, word, i, j + 1, k + 1) || dfs(board, word, i , j - 1, k + 1);
        board[i][j] = tmp;
        return res;
    }

    // 最长不含重复字符的子字符串
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> dic = new HashMap<>();
        int i = -1, res = 0;
        for(int j = 0; j < s.length(); j++) {
            if(dic.containsKey(s.charAt(j)))
                i = Math.max(i, dic.get(s.charAt(j))); // 更新左指针 i
            dic.put(s.charAt(j), j); // 哈希表记录
            res = Math.max(res, j - i); // 更新结果
        }
        return res;
    }

    // 1～n整数中1出现的次数
    public int countDigitOne(int n) {
        int digit = 1, res = 0;
        int high = n / 10, cur = n % 10, low = 0;
        while(high != 0 || cur != 0) {
            if(cur == 0) res += high * digit;
            else if(cur == 1) res += high * digit + low + 1;
            else res += (high + 1) * digit;
            low += cur * digit;
            cur = high % 10;
            high /= 10;
            digit *= 10;
        }
        return res;
    }

    // 字符串排序
    List<String> res = new LinkedList<>();
    char[] c;
    public String[] permutation(String s) {
        c = s.toCharArray();
        dfs(0);
        return res.toArray(new String[res.size()]);
    }
    void dfs(int x) {
        if(x == c.length - 1) {
            res.add(String.valueOf(c)); // 添加排列方案
            return;
        }
        HashSet<Character> set = new HashSet<>();
        for(int i = x; i < c.length; i++) {
            if(set.contains(c[i])) continue; // 重复，因此剪枝
            set.add(c[i]);
            swap(i, x); // 交换，将 c[i] 固定在第 x 位
            dfs(x + 1); // 开启固定第 x + 1 位字符
            swap(i, x); // 恢复交换
        }
    }
    void swap(int a, int b) {
        char tmp = c[a];
        c[a] = c[b];
        c[b] = tmp;
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


}
