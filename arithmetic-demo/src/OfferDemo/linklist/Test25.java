package OfferDemo.linklist;

import OfferDemo.node.RandomListNode;
import javafx.geometry.Pos;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: 10413
 * @Date: 2020/2/16 17:03
 * @Description: 25, 复杂链表的复制
 * 输入一个复杂链表（每个节点中有节点值，
 * 以及两个指针，一个指向下一个节点，另一个特殊指针指向任意一个节点），
 * 返回结果为复制后复杂链表的head。（注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）
 */
public class Test25 {

    /**
     * 复制链表
     * 不同于简单链表是多了一个指针，指向另一个随机的节点,
     * 思路：
     * 先遍历一遍链表，创建一个一样的链表，把next节点填好
     * 在遍历链表，填充random,
     * 这样有一个问题，如何在第二次遍历时找到random节点的复制节点呢？
     * 模拟思路2
     *
     * @param pHead
     * @return
     */
    public RandomListNode Clone(RandomListNode pHead) {
        if(pHead == null) {
            return null;
        }
        RandomListNode p = pHead;
        RandomListNode q;
        //一次遍历复制节点
        while (p != null) {
            RandomListNode node = new RandomListNode(p.label);
            q=p.next;
            p.next = node;
            node.next=q;
            // 中间插入了复制节点，p走两步到原来节点的一步
            p=q;
        }
        p = pHead;
        // 二次遍历对random填充
        while(p!=null){
            if (p.random!=null){
                q=p.random;
                p.next.random = q.next;
            }
            p=p.next.next;
        }
        // 三次编辑将复制节点取出来
        p = pHead;
        RandomListNode newHead = p.next;
        while (p!=null){
            q = p.next;
            p.next=q.next;
            if (p.next!=null){
                q.next = p.next.next;
            }
            p = p.next;
        }
        return newHead;
    }

    /**
     * 答案1
     * 建立一个hashmap存旧节点和新节点之间的映射关系
     * 如果节点已经存在那么使用存在的节点即可，如果不存在那么需要新开辟节点并存储他们之间的映射关系
     */
    public RandomListNode Clone1(RandomListNode pHead) {
        // 先前条件
        if (pHead == null) {
            return null;
        }
        RandomListNode newHead = null;
        RandomListNode p = pHead;
        RandomListNode q = null;
        // 节点之间的对应关系
        Map<RandomListNode, RandomListNode> map = new HashMap<>();
        while (p != null) {
            if (newHead == null) {
                newHead = new RandomListNode(pHead.label);
                q = newHead;
                map.put(pHead, newHead);
            } else {
                if (p.next != null && map.containsKey(p.next)) {
                    q.next = map.get(p.next);
                } else {
                    if (p.next != null) {
                        RandomListNode temp = new RandomListNode(p.next.label);
                        map.put(p.next, temp);
                        q.next = temp;
                    }
                }
                if (p.random != null && map.containsKey(p.random)) {
                    q.random = map.get(p.random);
                } else {
                    if (p.random != null) {
                        RandomListNode temp = new RandomListNode(p.random.label);
                        map.put(p.random, temp);
                        q.random = temp;
                    }
                }
                p = p.next;
                q = q.next;
            }
        }
        return newHead;
    }

    public RandomListNode Clone1_1(RandomListNode pHead) {
        if (pHead == null) {
            return null;
        }
        Map<RandomListNode, RandomListNode> map = new HashMap<>();
        //p指针遍历原链表
        RandomListNode p = pHead;
        //q 指针遍历新链表 p ,q指针的指向是同步的，即p的指向哪个节点，q对应指向这个节点的复制节点
        RandomListNode q = null;
        // 复制链表的表头指针
        RandomListNode newNode = null;
        while (p != null) {
            // p不为空，建立p的复制，存储其对应关系,这里只执行一次，后续的判断中
            //如果不存在于map中就建立的这个节点了，所以这里相当于初始化头节点
            if (!map.containsKey(p)) {
                newNode = new RandomListNode(p.label);
                map.put(p, newNode);
                q = newNode;
            }
            // p 的下一个节点不为空，且map中已经包含该节点，复制p.next节点到新节点
            if (p.next != null) {
                if (map.containsKey(p.next)){
                    q.next = map.get(p.next);
                }else {
                    //p的下一节点不为空，且map中没有该节点
                    // 新建p下一节点的复制，令q.next = p.next的复制，保持了p,q下一节点的一致性
                    // map中没有这个节点的映像，保存其映像
                    RandomListNode tmp = new RandomListNode(p.next.label);
                    q.next = tmp;
                    map.put(p.next, tmp);
                }
            }
            // p的random节点一样操作
            if (p.random != null ) {
                if (map.containsKey(p.random)){
                    q.random = map.get(p.random);
                } else {
                    RandomListNode tmp = new RandomListNode(p.random.label);
                    q.random = tmp;
                    map.put(p.random, tmp);
                }
            }
            // 第一个if 复制了p的next节点，第二个if复制了p的random节点，p的节点复制完毕，p到下一个节点
            p = p.next;
            q = q.next;
        }
        return newNode;
    }


    /**
     * 答案2
     * 解题思路：
     * 1、遍历链表，复制每个结点，如复制结点A得到A1，将结点A1插到结点A后面；
     * 2、重新遍历链表，复制老结点的随机指针给新结点，如A1.random = A.random.next;
     * 3、拆分链表，将链表拆分为原链表和复制后的链表
     */
    public RandomListNode clone3(RandomListNode pHead){
        if(pHead == null) {
            return null;
        }
        RandomListNode currentNode = pHead;
        //1、复制每个结点，如复制结点A得到A1，将结点A1插到结点A后面；
        while(currentNode != null){
            RandomListNode cloneNode = new RandomListNode(currentNode.label);
            RandomListNode nextNode = currentNode.next;
            currentNode.next = cloneNode;
            cloneNode.next = nextNode;
            currentNode = nextNode;
        }

        currentNode = pHead;
        //2、重新遍历链表，复制老结点的随机指针给新结点，如A1.random = A.random.next;
        while(currentNode != null) {
            currentNode.next.random = currentNode.random==null?null:currentNode.random.next;
            currentNode = currentNode.next.next;
        }

        //3、拆分链表，将链表拆分为原链表和复制后的链表
        currentNode = pHead;
        RandomListNode pCloneHead = pHead.next;
        while(currentNode != null) {
            RandomListNode cloneNode = currentNode.next;
            currentNode.next = cloneNode.next;
            cloneNode.next = cloneNode.next==null?null:cloneNode.next.next;
            currentNode = currentNode.next;
        }

        return pCloneHead;
    }
}
