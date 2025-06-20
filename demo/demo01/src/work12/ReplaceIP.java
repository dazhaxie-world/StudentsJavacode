package work12;

import java.util.regex.*;
public class ReplaceIP{
    public static void main(String args[ ]) {
        String str = "登录网站: 222.128.89.253";
        Pattern pattern;
        Matcher matcher;
        String regex = "[\\d]{1,3}[.][\\d]{1,3}[.][\\d]{1,3}[.][\\d]{1,3}";
        pattern =Pattern.compile(regex); //使用 regex 初试化模式对象 pattern
        matcher = pattern.matcher(str); //得到检索 str 的匹配对象 matcher
        String IP="";
        while(matcher.find()) {
            IP= matcher.group();
            System.out.print(matcher.start()+"位置出现:");
            System.out.println(IP);
        }
        System.out.printf("将%s 替换为 202.192.78.56\n",IP);
        String result = matcher.replaceAll("202.192.78.56");
        System.out.println(result);
    }
}
