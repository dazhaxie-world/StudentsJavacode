package work11;

class Player implements Runnable { // 或继承Thread类，此处以Runnable为例
    private String name;

    public Player(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name + " 开始游戏！");
        // 这里可以添加玩家具体的游戏逻辑
    }

    public void start() {
        new Thread(this).start(); // 通过Thread启动Runnable
    }
}


