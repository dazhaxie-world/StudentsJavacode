package work10;

public class Number implements Runnable {
    final int SMALLER=-1,LARGER=1,SUCCESS=8;
    int realNumber,guessNumber,min=0,max=100,message=SMALLER;
    boolean pleaseGuess=false,isGiveNumber=false;
    Thread giveNumberThread,guessNumberThread;
    Number() {
// 【代码 1】创建 giveNumberThread,当前 Number 类的实例是 giveNumberThread 的目标对象
        giveNumberThread=new Thread(this);
// 【代码 2】创建 guessNumberThread,当前 Number 类的实例是 guessNumberThread 的目标对象
        guessNumberThread=new Thread(this);
    }
    public void run() {
        for(int count=1;true;count++) {
            setMessage(count);
            if( message==SUCCESS)
                return;
        }
    }
    public synchronized void setMessage(int count) {
        if(Thread.currentThread()==giveNumberThread&&isGiveNumber==false) {
            realNumber=(int)(Math.random()*100)+1;
            System.out.println("随机给你一个 1 至 100 之间的数，猜猜是多少？");
            isGiveNumber=true;
            pleaseGuess=true;
        }
        if(Thread.currentThread()==giveNumberThread) {
            while(pleaseGuess==true)
                try { wait(); //让出 CPU 使用权，让另一个线程开始猜数
                }
                catch(InterruptedException e){}
            if(realNumber>guessNumber) { //结束等待后，根据另一个线程的猜测给出提示
                message=SMALLER;
                System.out.println("你猜小了");
            }
            else if(realNumber<guessNumber) {
                message=LARGER;
                System.out.println("你猜大了");
            }
            else {
                message=SUCCESS;
                System.out.println("恭喜，你猜对了");
            }
            pleaseGuess=true;
        }
        if(Thread.currentThread()==guessNumberThread&&isGiveNumber==true) {
            while(pleaseGuess==false)
                try { wait(); //让出 CPU 使用权，让另一个线程给出提示
                }
                catch(InterruptedException e){}
            if(message==SMALLER) {
                min=guessNumber;
                guessNumber=(min+max)/2;
                System.out.println("我第"+count+"次猜这个数是:"+guessNumber);
            }
            else if(message==LARGER) {
                max=guessNumber;
                guessNumber=(min+max)/2;
                System.out.println("我第"+count+"次猜这个数是:"+guessNumber);
            }
            pleaseGuess=false;
        }
        notifyAll();
    }
}
