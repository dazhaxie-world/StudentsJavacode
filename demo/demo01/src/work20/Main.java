package work20;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main{
    public static void main(String args[]) {
        String cost = "话费清单：市话费26.9元，长途话费28.9元，短信费7.9元";
        Scanner scanner = new Scanner(cost);        //2声明创建字符串cost的扫描器
        scanner.useDelimiter("[^0123456789.]+");           //3 扫描器指定非数字串作为分隔（[^0123456789.]+）
//        double sum=0;
//        while(scanner.hasNext()){
//            try{
//                double price = scanner.nextDouble(); // 声明并初始化price
//                sum=sum+price;
//                System.out.println(price);
//            }
//            catch (InputMismatchException e){      //5捕获InputMismatchException异常
//                String t=scanner.next();
//            }
//        }
//        System.out.println(sum);
        String del="[^0123456789.]+";
        String s = cost.replaceAll(del, "#");
        System.out.println(s);
    }
}
