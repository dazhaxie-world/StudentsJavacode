import java.util.Scanner;
public class BaggageAndMoney {
    public static void main(String args[]) {
        int trainCharge=12; //火车托运计费:每公斤 12 元
        int carCharge=22; //汽车托运计费:每公斤 12 元
        double planeCharge = 0.062 ;//飞机托运计费:每克 0.062 元
        Scanner reader=new Scanner(System.in);
        double weight,charge;
        System.out.printf("输入行李重量:");
        weight = reader.nextDouble();
        System.out.printf("行李重量:%f 公斤(kg)\n",weight);
        System.out.printf("需要计费的重量:%d(kg)\n",(int)weight);
        charge=(int)weight*trainCharge;
        System.out.printf("用火车托运(%d 元/kg),费用:%f 元\n",trainCharge,charge);
        System.out.printf("需要计费的重量:%d(kg)\n",(int)(weight+0.5));
        charge=(int)(weight+0.5)*carCharge;
        System.out.printf("用汽车托运(%d 元/kg),费用:%f 元\n",carCharge,charge);
        System.out.printf("行李重量:%f 克(g)\n",weight*1000);
        System.out.printf("需要计费的重量:%d(g)\n",(int)(weight*1000+0.5));
        charge=(int)(weight*1000+0.5)*planeCharge;
        System.out.printf("用飞机托运(%f 元/g),费用:%f 元\n", planeCharge,charge);
    }
}
