package work9;

import java.util.Scanner;
public class InputChineseThread extends Thread {
    Scanner reader;
    Chinese hanzi;
    int score = 0;
    InputChineseThread() {
        reader = new Scanner(System.in);
    }
    public void setChinese(Chinese hanzi) {
        this.hanzi = hanzi;
    }
    public void run() {
        while(true) {
            String str = reader.nextLine();
            char c = str.charAt(0);
            if(c==hanzi.getChinese()) {
                score++;
                System.out.printf("\t\t 输入对了,目前分数%d\n",score);
            }
            else {
                System.out.printf("\t\t 输入错了,目前分数%d\n",score);
            }
            if(c=='#')
                System.exit(0);
        }
    }
}