package test2.pack1;

public class Fight {
    public static void main(String args[]) {
        Tank tank1,tank2;
        tank1 = new Tank();
        tank2 = new Tank();
        tank1.setBulletAmount(10);
        tank2.setBulletAmount(10);
        System.out.println("tank1 的炮弹数量："+tank1.getBulletAmount());
        System.out.println("tank2 的炮弹数量："+tank2.getBulletAmount());
        tank1.speedUp(80);
        tank2.speedUp(90);
        System.out.println("tank1 目前的速度："+tank1.getSpeed());
        System.out.println("tank2 目前的速度："+tank2.getSpeed());
        tank1.speedDown(15);
        tank2.speedDown(30);
        System.out.println("tank1 目前的速度："+tank1.getSpeed());
        System.out.println("tank2 目前的速度："+tank2.getSpeed());
        System.out.println("tank1 开火：");
        tank1.fire();
        System.out.println("tank2 开火：");
        tank2.fire();
        tank2.fire();
        System.out.println("tank1 的炮弹数量："+tank1.getBulletAmount());
        System.out.println("tank2 的炮弹数量："+tank2.getBulletAmount());
    }
}
