package work8;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.time.LocalDateTime;
public class ShowFrame extends JFrame implements ActionListener {
    JTextArea showArea;
    JTextField inputName,inputTime;
    JButton button;
    TreeSet<Program> treeSet;
    ShowFrame() {
        treeSet= new TreeSet<Program>();
        showArea=new JTextArea();
        showArea.setFont(new Font("",Font.BOLD,20));
        inputName=new JTextField(12);
        inputTime=new JTextField(20);
        button=new JButton("确定");
        button.addActionListener(this);
        JPanel pNorth=new JPanel();
        pNorth.add(new JLabel("节目名称:"));
        pNorth.add(inputName);
        pNorth.add(new JLabel("时间(年-月-日/时:分:秒)"));
        pNorth.add(inputTime);
        pNorth.add(button);
        add(pNorth,BorderLayout.NORTH);
        add(new JScrollPane(showArea),BorderLayout.CENTER);
        setSize(620,320);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        validate();
    }
    public void actionPerformed(ActionEvent e) {
        String name=inputName.getText();
        String timeStr=inputTime.getText();
        StringTokenizer jiexi = new StringTokenizer(timeStr,"-/: ");
        int year=Integer.parseInt(jiexi.nextToken());
        int month=Integer.parseInt(jiexi.nextToken());
        int day=Integer.parseInt(jiexi.nextToken());
        int hour=Integer.parseInt(jiexi.nextToken());
        int minute=Integer.parseInt(jiexi.nextToken());
        int second=Integer.parseInt(jiexi.nextToken());
        LocalDateTime time = LocalDateTime.of(year,month,day,hour,minute,second);
        Program program = new Program(name,time);
        treeSet.add(program);
        show(treeSet);
    }
    public void show(TreeSet tree) {
        showArea.setText(null);
        Iterator<Program> te= treeSet.iterator();
        while(te.hasNext()) {
            Program pro= te.next();
            String pattern = "%tY-%<tm-%<td/%<tT";
            String strTime = String.format(pattern,pro.getLocalDateTime());
            showArea.append(pro.getName()+"(演出时间):\n"+strTime+"\n");
        }
    }
}
