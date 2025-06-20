package test2.pack1;

public class Tank {
    private double speed;
    private int bulletAmount;
    void speedUp(int s) {
        speed+=s;
    }
    void speedDown(int d) {
        if(speed-d>=0)
            speed-=d;
 else
        speed = 0;
    }
    void setBulletAmount(int m) {
        bulletAmount = m;
    }
    int getBulletAmount() {
        return bulletAmount;
    }
    double getSpeed() {
        return speed;
    }
    void fire() {
        if(bulletAmount>=1){
            bulletAmount--;
            System.out.println("打出一发炮弹");
        }
        else {
            System.out.println("没有炮弹了,无法开火");
        }
    }
}
