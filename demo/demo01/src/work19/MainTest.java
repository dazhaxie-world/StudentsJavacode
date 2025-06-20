package work19;

public class MainTest {
    public static void main(String[] args) throws MyException {
        Person person = new Person();
        person.setAge(5);
        System.out.println("person.getAge() = " + person.getAge());
        person.setAge(-1);
    }
}
