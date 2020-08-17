import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @Auther: 10413
 * @Date: 2020/3/29 20:01
 * @Description:
 */
public class Test3 {

    public static void main(String[] args) {
        Scanner c = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();
        String sql = "INSERT INTO doc_catalog(id,doc_id,parent_id,chapter_name,start_page) VALUES(";
        int i = 720;
        while (c.hasNext()) {
            String src = c.nextLine();
            if (src.equals("#")){
                break;
            }
            String[] strs = src.split("\\s+");
            StringBuffer buffer = new StringBuffer(sql);
            buffer.append(i).append(",").append("4,0,").append("'").append(strs[0]).append(" ").append(strs[1]).append("',").append(strs[2]).append(");");
            list.add(buffer.toString());
            i++;
        }
        for (String s : list){
            System.out.println(s);
        }
    }
    public static void print(String[] strs){
        for (int i=0;i<strs.length;i++){
            System.out.print(strs[i]+ " " );
        }
        System.out.println();
    }
}
/*






 */
