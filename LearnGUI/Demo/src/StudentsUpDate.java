import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentsUpDate implements ActionListener {
    static String receive=null;

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton= (JButton) e.getSource();
        String text = jButton.getText();
        if(text.equals("增加")){
            new ViewUtil("增加","输入   姓名-学号-语文-数学-英语");
        }
        else if(text.equals("修改")){
            new ViewUtil("修改","输入   学号---姓名-学号-语文-数学-英语");
        }
        else if(text.equals("删除")){
            new ViewUtil("删除","输入   学号");
        }
        else if(text.equals("查询")){
            new CheckStudentView(MainView.searchText.getText());
        }
    }
}
