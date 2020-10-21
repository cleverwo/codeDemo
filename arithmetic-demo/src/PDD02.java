import java.util.*;

/**
 * @Auther: 10413
 * @Date: 2020/9/26 19:30
 * @Description:
 */
public class PDD02 {
    // 结束点
    static int fx,fy;
    //迷宫为 n * m
    static int n=5;
    static int m=6;
    //上下左右移动
    static int[][] temp ={{0,1},{1,0},{0,-1},{-1,0}};
    //迷宫数组
    static String [][] squera = new String [n][m];
    //标记数组，走过就标记为1
    static int [][] book = new int [n][m];
    //最短步数
    static int min = Integer.MAX_VALUE;
    static Set<Point> ans = new HashSet<>();
    public static void main(String[] args){
        Scanner scan  = new Scanner(System.in);
        fx = scan.nextInt();
        fy = scan.nextInt();
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                squera[i][j] = scan.next();
            }
        }
        Point a = new Point(0,0);
        Point b = new Point(1,5);
        Point c = new Point(3,1);
        Point d = new Point(4,5);
        List<Point> list = new ArrayList<>();
        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);
        getAns(list);
    }
    static class Point{
        int x;
        int y;
        public Point(int a,int b){
            x=a;
            y=b;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point person = (Point) o;
            return x == person.x &&
                    y == person.y;
        }

        @Override
        public int hashCode() {
            int hash = 17;
            hash = hash * 31 + x;
            hash = hash * 31 + y;
            return hash;
        }
    }
    public static void getAns(List<Point> xList){
        for (Point p : xList) {
            dfs(p.x,p.y,p.x,p.y,0);
        }
        if (ans.isEmpty()){
            System.out.println(0);
        }else{
            List<Point> res = new ArrayList<>(ans);
            Collections.sort(res, new Comparator<Point>() {
                @Override
                public int compare(Point o1, Point o2) {
                    if (o1.x !=o2.x){
                        return o1.x - o2.x;
                    }else{
                        return o1.y-o2.y;
                    }
                }
            });
            for (int i=0;i<res.size();i++){
                System.out.print(res.get(i).x + " ");
                System.out.print(res.get(i).y);
                if (i != res.size()-1){
                    System.out.print(" ");
                }
            }
        }
    }
    public static void dfs(int xx,int yy,int x,int y,int step){
        //如果到达地点，结束
        if(x==fx&&y==fy){
            if(step<min){
                min = step;
            }
            if (step == min && min != Integer.MAX_VALUE){
                ans.add(new Point(xx,yy));
            }
            return;
        }
        //循环移动到四个方向
        for(int i=0;i<4;i++){
            int tx = temp[i][0];
            int ty = temp[i][1];
            //如果该方向越界了，改变到另一个方向
            if(x+tx<0||x+tx>=n)
                continue;
            if(y+ty<0||y+ty>=n)
                continue;
            //如果该位置没有障碍物并且也没有走过，走
            if(squera[x+tx][y+ty].equals("0") && book[x+tx][y+ty]==0){
                //标记为走过
                book[x+tx][y+ty] = 1;
                //往下一层递归
                dfs(xx,yy,x+tx,y+ty,step+1);
                //取消标记，回到上一层
                book[x+tx][y+ty] = 0;
            }
        }
        return;
    }

}
