package _modal;

/**
 * @Auther: 10413
 * @Date: 2020/9/14 20:22
 * @Description: 双向链表节点
 */
public class ListDequeNode {
    public int val;
    public ListDequeNode left;
    public ListDequeNode right;

    public ListDequeNode() {}

    public ListDequeNode(int _val) {
        val = _val;
    }

    public ListDequeNode(int _val,ListDequeNode _left,ListDequeNode _right) {
        val = _val;
        left = _left;
        right = _right;
    }
}
