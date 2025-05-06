import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Score {
    private Set<Subject> subjectList;

    public Score() {
        this.subjectList = new HashSet<>();
    }

    public void setSubjectList(Subject addSubject){
        subjectList.add(addSubject);
    }//添加科目

    public Object getSubjectList(){
        return subjectList;
    }//查询科目

    public void removeSubjectList(Integer i){
        subjectList.remove(i);
    }
    public int getListLength(){
        return subjectList.size();
    }
    public double getScore(){
        double ans=0;
        double all=0;
        for (Subject subject : subjectList) {
            ans+=subject.getTotalCredits();
            all+=subject.getMaxCredits();
        }
        ans=ans/all;
        return ans;
    }
}
