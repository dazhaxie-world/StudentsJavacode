import java.util.HashSet;
import java.util.Set;

public class Student {
    private String name;
    private double gradePoints;//绩点
    private Score selfList;

    public Student(String name) {
        this.name = name;
        selfList=new Score();
    }//创建学生类

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSelfList(Subject add){
        selfList.setSubjectList(add);
    }
    public Object getSubjectList() {
        return selfList.getSubjectList();
    }//得到学生成绩表

    public double getGradePoints(){
        return selfList.getScore();
    }//得到绩点
}
