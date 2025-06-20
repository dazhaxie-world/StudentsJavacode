import tom.jiafei.SquareEquation;

import java.util.Scanner;

public class SunRise {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入数字求平方根：");
        double m=1.21;
        m=sc.nextInt();
        System.out.println(SquareEquation.f(m));
    }

}
