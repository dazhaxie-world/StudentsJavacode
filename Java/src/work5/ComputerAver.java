package work5;

public class ComputerAver {
    Judge judge;

    public ComputerAver(Judge judge) {
        this.judge = judge;
    }

    public void giveAver(double [] b) {
        double sum=0;
        for(int i =0;i<b.length;i++) {
            sum = sum+ b[i];
        }
        double aver=sum/b.length;
        System.out.println("选手最后得分"+aver);
        judge.judgeScore(aver);
    }
}
