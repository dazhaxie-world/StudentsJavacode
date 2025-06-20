package work03;

public class Rect extends Geometry {
    double a,b;
    Rect(double a,double b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public double getArea() {
        return a*b;
    }
}
