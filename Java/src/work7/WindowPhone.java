package work7;

import java.awt.*;
import javax.swing.*;
public class WindowPhone extends JFrame {
    JTextField inputText;
    JButton enter;
    JTextArea showText;
    HandleQuery query; //监视器
    WindowPhone() {
        setLayout(new FlowLayout());
        inputText=new JTextField(10);
        enter = new JButton("查询");
        showText=new JTextArea(8,36);
        add(new JLabel("姓名中包含:"));
        add(inputText);
        add(enter);
        add(new JScrollPane(showText));
        query=new HandleQuery();
        query.setView(this);
        enter.addActionListener(query);
        inputText.addActionListener(query);
        setBounds(100,100,460,380);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    public static void main(String args[]) {
        WindowPhone win=new WindowPhone();
        win.setTitle("电话簿");
    }
}
