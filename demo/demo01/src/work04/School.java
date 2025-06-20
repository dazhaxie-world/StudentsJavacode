package work04;

public class School implements CompurerAverage {
    @Override
    public double average(double[] x) {
     double ans = 0;
        for (int i = 0; i < x.length; i++) {
            ans+=i;
        }
       return ans/=x.length;
    }
}
