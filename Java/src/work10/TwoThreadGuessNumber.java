package work10;
public class TwoThreadGuessNumber {
    public static void main(String args[]) {
        Number number=new Number();
        number.giveNumberThread.start();
        number.guessNumberThread.start();
    }
}
