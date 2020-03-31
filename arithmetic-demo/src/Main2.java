import sun.awt.image.ImageWatched;

import javax.sound.midi.Soundbank;
import java.util.*;

/**
 * @Auther: 10413
 * @Date: 2020/3/27 09:08
 * @Description:
 */
public class Main2 {

    public static void main(String[] args) {
        Main2 m = new Main2();
        int[] a = {1,2,2,1,2};
        int n = 3;
        System.out.println(m.getSum(n,a));
    }

    public static class Node{
        int num;
        int sum;
        public Node(int num,int sum){
            this.num=num;
            this.sum=sum;
        }
        public void setNum(int num){
            this.num = num;
        }
        public void setSum(int sum){
            this.sum = sum;
        }
    }

    public int getSum(int n,int[] nums){
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(0,0));
        for (int i=0;i<n;i++){
            int a = nums[i];
            if (a==1){
                int length = queue.size();
                for (int j=0;j<length;j++){
                    Node node = queue.poll();
                    node.setNum(node.num+1);
                    node.setSum(node.num+node.sum);
                    queue.offer(node);
                }
            }else if(a==2){
                Node node = queue.poll();
                Node n1 = new Node(node.num+1,node.sum+node.num+1);
                queue.offer(n1);
                node.setNum(0);
                queue.offer(node);
            }else{
                int result = getAvg(queue);
                queue.clear();
                queue.offer(new Node(0,result));
            }
        }
        return getAvg(queue);
    }

    public int getAvg(Queue<Node> queue){
        int sum = 0;
        int length = queue.size();
        while(!queue.isEmpty()){
            Node node = queue.poll();
            sum += node.sum;
        }
        return sum/length;
    }


}
