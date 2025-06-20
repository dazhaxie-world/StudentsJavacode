package work6;

import java.util.LinkedList;

public class TV implements Comparable<TV> {
    public int price;
    @Override
    public int compareTo(TV o) {
        return this.price-o.price;
    }

    public TV(int price) {
        this.price = price;
    }
}
