package work9;

public class GiveChineseThread extends Thread {
    Chinese hanzi;
    char startChar =(char)22909,endChar = (char)(startChar+100);
    int sleepLength = 5000;
    public void setChinese(Chinese hanzi) {
        this.hanzi = hanzi;
    }
    public void setSleepLength(int n){
        sleepLength = n;
    }
    public void run() {
        char c = startChar;
        while(true) {
            hanzi.setChinese(c);
            System.out.printf("显示的汉字:%c\n ",hanzi.getChinese());
            try{
//                【代码 3】//调用 sleep 方法使得线程中断 sleepLength 豪秒
                sleep(sleepLength);
            }
            catch(InterruptedException e){

            }
            c = (char)(c+1);
            if(c>endChar)
                c = startChar;
        }
    }
}
