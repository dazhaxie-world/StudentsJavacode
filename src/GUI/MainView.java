package GUI;

import GUI.extendBag.MainViewTable;
import GUI.extendBag.MainViewTableModel;
import GUI.util.DimensionUtil;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class MainView extends JFrame{
    JPanel northPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
    JButton addButton=new JButton("增加");
    JButton updateButton =new JButton("修改");
    JButton deleteButton=new JButton("删除");
    JButton searchButton=new JButton("查询");
    JTextField searchText=new JTextField(15);

    JPanel southPanel =new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JButton beforeButton=new JButton("上一页");
    JButton nextButton=new JButton("下一页");

    MainViewTable mainViewTable=new MainViewTable();

    public MainView(){
        super("主界面");
        Container contentPane = getContentPane();

        layoutNorth(contentPane);//面板北部初始化
        layoutCenter(contentPane);
        layoutSouth(contentPane);//面板南部初始化
        setBounds(DimensionUtil.getBounds());//px
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void layoutSouth(Container contentPane) {
        southPanel.add(beforeButton);
        southPanel.add(nextButton);

        contentPane.add(southPanel,BorderLayout.SOUTH);
    }
    private void layoutCenter(Container contentpane){
        Vector<Vector<Object>> data=new Vector<>();
        Vector<Object> rowVector1=new Vector<>();
        rowVector1.addElement("1");
        rowVector1.addElement("张三");
        rowVector1.addElement("甘肃");
        rowVector1.addElement("no1");
        rowVector1.addElement("1");
        rowVector1.addElement("2");
        rowVector1.addElement("3");
        rowVector1.addElement("6");

        Vector<Object> rowVector2=new Vector<>();
        rowVector2.addElement("2");
        rowVector2.addElement("李四");
        rowVector2.addElement("青海");
        rowVector2.addElement("no2");
        rowVector2.addElement("3");
        rowVector2.addElement("2");
        rowVector2.addElement("3");
        rowVector2.addElement("8");

        Vector<Object> rowVector3=new Vector<>();
        rowVector3.addElement("1");
        rowVector3.addElement("王五");
        rowVector3.addElement("杭州");
        rowVector3.addElement("no1");
        rowVector3.addElement("2");
        rowVector3.addElement("2");
        rowVector3.addElement("3");
        rowVector3.addElement("7");

        data.addElement(rowVector1);
        data.addElement(rowVector3);
        data.addElement(rowVector3);
        MainViewTableModel mainViewTableModel = MainViewTableModel.assembleModel(data);
        mainViewTable.setModel(mainViewTableModel);

        JScrollPane jScrollPane=new JScrollPane(mainViewTable);
        mainViewTable.renderRule();
        contentpane.add(jScrollPane,BorderLayout.CENTER);
    }
    private void layoutNorth(Container contentPane) {
        northPanel.add(addButton);
        northPanel.add(updateButton);
        northPanel.add(deleteButton);
        northPanel.add(searchText);
        northPanel.add(searchButton);
        contentPane.add(northPanel,BorderLayout.NORTH);
    }

    public static void main(String[] args) {
        new MainView();
    }
}
