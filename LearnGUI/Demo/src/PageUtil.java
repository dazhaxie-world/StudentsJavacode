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
                System.out.println("1111");
                MainView.startPage+=MainView.everyPageNUmber;
                System.out.println("2222");
                MainView.pubMainView.dispose();
                System.out.println("3333");
                MainView.pubMainView.reload();
                System.out.println("4444");
                new MainView();
                System.out.println("5555");
            }
        }
    }
}
