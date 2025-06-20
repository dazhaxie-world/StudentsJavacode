package work6;

import java.util.Collections;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        TV tv1 = new TV(15);
        TV tv2 = new TV(16);
        TV tv3 = new TV(25);
        TV tv4 = new TV(8);
        TV tv5 = new TV(19);
        LinkedList<TV> tvs = new LinkedList<>();
        tvs.add(tv1);
        tvs.add(tv2);
        tvs.add(tv3);
        tvs.add(tv4);
        tvs.add(tv5);
        Collections.sort(tvs);
        for (int i = 0; i < tvs.size(); i++) {
            System.out.println("tvs.get(i).price = " + tvs.get(i).price);
        }
    }
}
