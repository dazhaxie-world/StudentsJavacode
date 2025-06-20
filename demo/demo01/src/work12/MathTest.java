package work12;

import java.math.BigInteger;

public class MathTest {
    public static void main(String[] args) {
        BigInteger a=new BigInteger("12222222222222222254633333333");
        BigInteger b=new BigInteger("12222254688888888888888888888");
        BigInteger sum=a.add(b);
        System.out.println("和为"+sum);
        BigInteger subtract = a.subtract(b);
        System.out.println("差为"+subtract);
        BigInteger multiply = a.multiply(b);
        System.out.println("积为"+multiply);
        BigInteger divide = b.divide(a);
        System.out.println("商为"+divide);
    }
}
