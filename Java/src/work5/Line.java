package work5;

public class Line {
    InputScore one;
    DelScore two;
    ComputerAver three;
    Judge four;
    Line(){//创建流水线
        four=new Judge();
        three=new ComputerAver(four);
        two=new DelScore(three);
        one=new InputScore(two);

    }
    public void givePersonScore(){
        one.inputScore();//流水线任务第一步
    }
}
