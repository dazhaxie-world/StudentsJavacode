package work19;

public class Person {
    private String name;
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) throws MyException {
        if(age<=0){
            throw new MyException("年龄必须大于0");
        }
        this.age = age;
    }
}
