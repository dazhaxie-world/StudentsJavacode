package work01;

import java.sql.SQLOutput;

public class BeijingPeople extends ChinaPeople {

    @Override
    public void averageHeight() {
        System.out.println("北京人的平均身高:172.5 厘米");
    }

    @Override
    public void averageWeight() {
        System.out.println("北京人的平均体重:70 公斤");
    }

    public void beijingOpera() {
        System.out.println("花脸、青衣、花旦和老生");
    }
}
