package work01;

import java.sql.SQLOutput;

public class AmericanPeople extends People {
    @Override
    public void speakHello() {
        System.out.println("How do you do");
    }
    @Override
    public void averageHeight() {
        System.out.println("American's average height:176 cm");
    }

    public void averageWeight() {
        weight = 75;
        System.out.println("American's average weight:"+weight+" kg");
    }
    public void americanBoxing() {
        System.out.println("直拳、钩拳、组合拳");
    }
}
