package work03;

public class Circle extends Geometry {
    double r;
    Circle(double r) {
        this.r = r;
    }
// 【代码 2】 //重写 getArea()方法

    @Override
    public double getArea() {
        return Math.PI*r*r;
    }
}

