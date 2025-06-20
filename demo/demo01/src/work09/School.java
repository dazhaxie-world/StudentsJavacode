package work09;

public class School {
    String schoolName;
    private InnerNewsPaper newsPaper;
    private School() {
            this("某某大学");
}
   public School(String s) {
       this.schoolName = s;
       this.newsPaper = new InnerNewsPaper();
       String[] content = {"学校举办迎新会.", "机械系获得机器人大赛冠军.", "计算机学院召开学生会换届大会."};
       newsPaper.setContent(content);
    }
    public void showNews(){ newsPaper.showContent();
    }
class InnerNewsPaper {
    String [] content;
    String paperName = "校新闻周报"; void setContent(String []s){
        content = s;
    }
    public void showContent(){
        System.out.println(schoolName);
        for(int i=0;i<content.length;i++){
            System.out.println(content[i]);
        }
    }
}
}
