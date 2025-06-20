package work07;


public class Main {
    public static void main(String[] args) {
        String s="aaaa";
        String t="bbbbb";
        LastPrinter lastPrinter = new LastPrinter();
        lastPrinter.print(t);
        InkPrinter inkPrinter = new InkPrinter();
        inkPrinter.print(s);
    }

}
