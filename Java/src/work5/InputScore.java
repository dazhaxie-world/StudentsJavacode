package work5;

import java.util.Scanner;
public class InputScore {
    DelScore del ;//组合下一步对象
    InputScore(DelScore del) {
        this.del = del;
    }
    public void inputScore() {
        System.out.println("请输入评委数");
        Scanner read=new Scanner(System.in);
        int count = read.nextInt();
        System.out.println("请输入各个评委的分数");
        double []a = new double[count];
        for(int i=0;i<count;i++) {
            a[i]=read.nextDouble();
        }
        del.doDelete(a);//下一步对象执行任务
    }
}
