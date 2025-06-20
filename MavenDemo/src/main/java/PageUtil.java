import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PageUtil implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton= (JButton) e.getSource();
        String text = jButton.getText();
        if(text.equals("上一页")){
            if(MainView.startPage!=0){
                MainView.startPage-=MainView.everyPageNUmber;
                MainView.pubMainView.dispose();
                new MainView();
            }
        }
        else if(text.equals("下一页")){
            if(MainView.startPage+MainView.everyPageNUmber<MainView.maxPageNumber){
                MainView.startPage+=MainView.everyPageNUmber;
                MainView.pubMainView.dispose();
                MainView.pubMainView.reload();
                new MainView();
            }
        }
    }
}
