package SystemProcess;

public class Subject {
    private String name;    //学科姓名
    private double score=0;   //考试成绩
    private double Credits=0; //学生获得学分
    private double MaxCredits=0; //最大学分
    private double TotalCredits=0; //学生获得总学分

    public Subject(String name) {
        this.name = name;
    }//添加新课程

    public Subject(String name, double maxCredits) {
        this.name = name;
        MaxCredits = maxCredits;
    }

    public String getName() {
        return name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getCredits() {
        return Credits;
    }

    public void setCredits(double credits) {
        Credits = credits;
    }

    public double getMaxCredits() {
        return MaxCredits;
    }


    public double getTotalCredits() {
        return TotalCredits;
    }

    public void setTotalCredits(double totalCredits) {
        TotalCredits = totalCredits;
    }
}
