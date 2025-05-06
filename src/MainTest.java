public class MainTest {
    public static void main(String[] args) {
        Student stu = new Student("zhangsan");
        Subject math = new Subject("math",5);
        Subject english = new Subject("english",3);
        stu.setSelfList(math);
        stu.setSelfList(english);
        System.out.println(stu.getGradePoints());
    }
}
