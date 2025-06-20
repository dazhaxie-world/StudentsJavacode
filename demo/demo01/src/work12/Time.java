package work12;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Time {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String s1=scanner.next();
        String s2=scanner.next();
        LocalDate date1 = LocalDate.parse(s1, formatter);

        LocalDate date2 = LocalDate.parse(s2, formatter);

        if (date1.isBefore(date2)) {
            System.out.println("第一个日期在第二个日期之前");
        } else if (date1.isAfter(date2)) {
            System.out.println("第一个日期在第二个日期之后");
        } else {
            System.out.println("两个日期相等");
        }


        long daysBetween = ChronoUnit.DAYS.between(date1, date2);
        System.out.println("两日期间隔 " + Math.abs(daysBetween) + " 天");
    }
}
