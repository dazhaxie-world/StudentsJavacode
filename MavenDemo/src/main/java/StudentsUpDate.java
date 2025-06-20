import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentsUpDate implements ActionListener {
    static String receive=null;
    MainView mainView;
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton= (JButton) e.getSource();
        String text = jButton.getText();
        if(text.equals("增加")){
            new ViewUtil("增加","输入   学号、姓名可添加生日或全部添加，信息用空格分隔");
        }
        else if(text.equals("修改")){
            new ViewUtil("修改","输入   学号，可对成绩或生日或全部进行修改");
        }
        else if(text.equals("删除")){
            new ViewUtil("删除","输入   学号");
        }
        else if(text.equals("查询")){
            new CheckStudentView(MainView.searchText.getText());
        }
    }

    public StudentsUpDate() {
    }

    public StudentsUpDate(MainView mainView) {
        this.mainView = mainView;
    }
}
