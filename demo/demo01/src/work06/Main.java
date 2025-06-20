package work06;

public class Main {
            static void askAllToEat(Person[] ps)
        {
            for(int i=0;i<ps.length;i++){
                ps[i].eat();
                ps[i].sleep();
            }
        }
    public static void main(String[] args) {
        Person[] people ={
                new Student(),
                new Teacher()
        };
        askAllToEat(people);
    }
}
