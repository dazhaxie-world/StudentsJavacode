import extendBag.MainViewTable;
import extendBag.MainViewTableModel;
import util.DimensionUtil;
import util.SqlUtil;

import javax.swing.*;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class MainView extends JFrame{
    JPanel northPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
    JButton addButton=new JButton("增加");
    JButton updateButton =new JButton("修改");
    JButton deleteButton=new JButton("删除");
    JButton searchButton=new JButton("查询");
    JButton generateButton=new JButton("一键生成");
    static JTextField searchText=new JTextField(15);

    JPanel southPanel =new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JButton beforeButton=new JButton("上一页");
    JButton nextButton=new JButton("下一页");

    MainViewTable mainViewTable=new MainViewTable();
    StudentsUpDate studentsUpDate=new StudentsUpDate();
    GenerateUti generateUti=new GenerateUti();
    PageUtil pageUtil=new PageUtil();
    static MainView pubMainView=null;
    static int startPage=0;
    static int everyPageNUmber=10;
    static int maxPageNumber=1000;
    public MainView(){
        super("主界面");
        pubMainView=this;
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
        beforeButton.addActionListener(pageUtil);
        nextButton.addActionListener(pageUtil);
        southPanel.add(beforeButton);
        southPanel.add(nextButton);

        contentPane.add(southPanel,BorderLayout.SOUTH);
    }
    private void layoutCenter(Container contentpane){
        Vector<Vector<Object>> data=new Vector<>();
//        Vector<Object> rowVector1=new Vector<>();
//        rowVector1.addElement("1");
//        rowVector1.addElement("张三");
//        rowVector1.addElement("no1");
//        rowVector1.addElement("1");
//        rowVector1.addElement("2");
//        rowVector1.addElement("3");
//        rowVector1.addElement("6");
//
//        Vector<Object> rowVector2=new Vector<>();
//        rowVector2.addElement("2");
//        rowVector2.addElement("李四");
//        rowVector2.addElement("no2");
//        rowVector2.addElement("3");
//        rowVector2.addElement("2");
//        rowVector2.addElement("3");
//        rowVector2.addElement("8");
//
//        Vector<Object> rowVector3=new Vector<>();
//        rowVector3.addElement("1");
//        rowVector3.addElement("王五");
//        rowVector3.addElement("no1");
//        rowVector3.addElement("2");
//        rowVector3.addElement("2");
//        rowVector3.addElement("3");
//        rowVector3.addElement("7");
//
//        data.addElement(rowVector1);
//        data.addElement(rowVector3);
//        data.addElement(rowVector3);
        try {
            PreparedStatement getVectorMain = SqlUtil.getConnection().prepareStatement("SELECT * FROM students LIMIT ? , ?");
            getVectorMain.setInt(1,startPage);
            getVectorMain.setInt(2,everyPageNUmber);
            ResultSet resultSet = getVectorMain.executeQuery();
            PreparedStatement getMax = SqlUtil.getConnection().prepareStatement("SELECT COUNT(*) FROM students");
            ResultSet maxSet = getMax.executeQuery();
            while (maxSet.next()){
                maxPageNumber=maxSet.getInt(1);
            }
            while (resultSet.next()){
                Vector<Object> objects = new Vector<>();
                objects.addElement(resultSet.getString("学号"));
                objects.addElement(resultSet.getString("name"));
                objects.addElement(resultSet.getDouble("chinese"));
                objects.addElement(resultSet.getDouble("birthday_year"));
                objects.addElement(resultSet.getDouble("birthday_month"));
                objects.addElement(resultSet.getDouble("birthday_day"));
                objects.addElement(resultSet.getDouble("chinese"));
                objects.addElement(resultSet.getDouble("math"));
                objects.addElement(resultSet.getDouble("java"));
                objects.addElement(resultSet.getDouble("PE"));
                objects.addElement(resultSet.getDouble("chinese")+resultSet.getDouble("math")+resultSet.getDouble("java")+resultSet.getDouble("PE"));
                data.addElement(objects);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        MainViewTableModel mainViewTableModel = MainViewTableModel.assembleModel(data);
        mainViewTable.setModel(mainViewTableModel);

        JScrollPane jScrollPane=new JScrollPane(mainViewTable);
        mainViewTable.renderRule();
        contentpane.add(jScrollPane,BorderLayout.CENTER);
    }
    private void layoutNorth(Container contentPane) {
        addButton.addActionListener(studentsUpDate);

        updateButton.addActionListener(studentsUpDate);
        deleteButton.addActionListener(studentsUpDate);
        searchButton.addActionListener(studentsUpDate);
        generateButton.addActionListener(generateUti);
        northPanel.add(addButton);
        northPanel.add(updateButton);
        northPanel.add(deleteButton);
        northPanel.add(searchText);
        northPanel.add(searchButton);
        northPanel.add(generateButton);
        contentPane.add(northPanel,BorderLayout.NORTH);
    }
    public void reload(){
        revalidate();
    }
}
