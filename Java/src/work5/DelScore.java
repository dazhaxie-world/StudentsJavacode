package work5;

public class DelScore {
    ComputerAver computer ;//组合下一步对象
    DelScore(ComputerAver computer) {
        this.computer = computer;
    }
    public void doDelete(double [] a) {
        java.util.Arrays.sort(a); //数组 a 从小到大排序
        System.out.print("去掉一个最高分:"+a[a.length-1]+"，");
        System.out.print("去掉一个最低分:"+a[0]+"。");
        double b[] =new double[a.length-2];
        for(int i=1;i<a.length-1;i++) { //去掉最高分和最低分
            b[i-1] = a[i];
        }
        computer.giveAver(b); //下一步对象执行任务
    }
}
