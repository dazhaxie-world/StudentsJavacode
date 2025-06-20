package work11;

import java.util.Random;

public class NumberGame {
    private final int MAX_RANGE = 100;
    private int targetNumber;
    private int min = 1, max = MAX_RANGE;
    private String status = "WAITING";  // 状态：WAITING/SMALLER/LARGER/CORRECT
    private boolean gameOver = false;

    // 出数线程
    class NumberGenerator extends Thread {
        @Override
        public void run() {
            synchronized(this) {
                targetNumber = new Random().nextInt(MAX_RANGE) + 1;
                System.out.println("出题者已生成1-100之间的数字，开始猜数！");
                status = "WAITING";
                notifyAll();  // 通知猜数线程开始
            }

            while (!gameOver) {
                synchronized(this) {
                    try {
                        while (!"WAITING".equals(status)) {
                            wait();  // 等待猜数完成
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
    }

    // 猜数线程
    class Guesser extends Thread {
        private final String name;

        public Guesser(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            Random rand = new Random();
            while (!gameOver) {
                synchronized(this) {
                    try {
                        while (!"WAITING".equals(status) && !gameOver) {
                            wait();  // 等待出题者更新状态
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }

                if (gameOver) break;

                int guess = rand.nextInt(max - min + 1) + min;
                System.out.println(name + " 猜测：" + guess);

                synchronized(this) {
                    if (guess == targetNumber) {
                        status = "CORRECT";
                        System.out.println(name + " 猜对了！");
                        gameOver = true;
                        notifyAll();
                    } else if (guess < targetNumber) {
                        synchronized(NumberGame.this) {
                            min = guess + 1;
                            status = "LARGER";
                            notifyAll();
                        }
                    } else {
                        synchronized(NumberGame.this) {
                            max = guess - 1;
                            status = "SMALLER";
                            notifyAll();
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        NumberGame game = new NumberGame();

        // 启动出题线程
        game.new NumberGenerator().start();

        // 启动两个猜数线程
        game.new Guesser("猜数者A").start();
        game.new Guesser("猜数者B").start();
    }
}




