import LeetCodeDemo.link.Solution2;

import javax.sound.midi.Soundbank;
import java.util.*;

/**
 * @Auther: 10413
 * @Date: 2020/4/24 08:20
 * @Description:
 */
public class Main {

    public static void main(String[] args) {
        Scanner c = new Scanner(System.in);
        int n = c.nextInt();
        List<Long> result = new ArrayList<>();
        while (n>0){
            int a = c.nextInt();
            long k = c.nextLong();
            int[] ai = new int[a];
            int[] fi = new int[a];
            for (int j = 0; j < a; j++) {
                ai[j] = c.nextInt();
            }
            for (int j = 0; j < a; j++) {
                fi[j] = c.nextInt();
            }
            result.add(getResult(a, k, ai, fi));
            n--;
        }
        for (Long l : result) {
            System.out.println(l);
        }
    }

    public static long getResult(int a, long k, int[] ai, int[] fi) {
        Arrays.sort(fi);
        long sum = 0;
        for (int i = 0; i < a; i++) {
            sum += ai[i];
        }
        if ((sum - k) < 0) {
            return 0;
        } else {
            long num = (sum - k) / a;
            long yu = (sum - k) % a;
            long max = Integer.MIN_VALUE;
            for (int i = 0; i < a; i++) {
                if (yu > 0) {
                    max = Math.max(max, fi[i] * (num + 1));
                } else {
                    max = Math.max(max, fi[i] * num);
                }
                yu--;
            }
            return max;
        }
    }

    public static void main1(String[] args) {
        Scanner c = new Scanner(System.in);
        int n = c.nextInt();
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int q = c.nextInt();
            c.useDelimiter("\n");
            Queue<Integer> queue = new LinkedList<>();
            while (q > 0) {
                String line = c.next();
                result(line, queue, result);
                q--;
            }
        }
        for (Integer num : result) {
            System.out.println(num);
        }
    }

    public static void result(String line, Queue<Integer> queue, List<Integer> result) {
        String[] str = line.split("\\s+");
        String mark = str[0];
        if (mark.equals("PUSH")) {
            int num = Integer.parseInt(str[1]);
            queue.offer(num);
        } else if (mark.equals("TOP")) {
            if (queue.isEmpty()) {
                result.add(-1);
            } else {
                result.add(queue.peek());
            }
        } else if (mark.equals("POP")) {
            if (queue.isEmpty()) {
                result.add(-1);
            } else {
                queue.poll();
            }
        } else if (mark.equals("SIZE")) {
            result.add(queue.size());
        } else if (mark.equals("CLEAR")) {
            queue.clear();
        }
    }
}
