import extendBag.CheckViewTable;
import extendBag.CheckViewTableModel;
import extendBag.MainViewTable;
import extendBag.MainViewTableModel;
import util.SqlUtil;

import javax.swing.*;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class CheckStudentView extends JFrame {
    CheckViewTable checkViewTable=new CheckViewTable();
    String str;
    public CheckStudentView(String s){
        str=s;
        Container contentPane = getContentPane();
        layoutCenter(contentPane);
        setSize(800,600);
        setResizable(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
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
            PreparedStatement getVectorMain = SqlUtil.getConnection().prepareStatement("SELECT * FROM students WHERE name like %?%");
            Statement statement = SqlUtil.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM students WHERE name like '%"+str+"%'");
            while (resultSet.next()){
                Vector<Object> objects = new Vector<>();
                objects.addElement(resultSet.getString("学号"));
                objects.addElement(resultSet.getString("name"));
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
            if (!resultSet.next()){
                Vector<Object> objects = new Vector<>();
                objects.addElement("No Found");
                objects.addElement("No Found");
                objects.addElement("No Found");
                objects.addElement("No Found");
                objects.addElement("No Found");
                objects.addElement("No Found");
                objects.addElement("No Found");
                objects.addElement("No Found");
                objects.addElement("No Found");
                objects.addElement("No Found");
                objects.addElement("No Found");
                data.addElement(objects);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        CheckViewTableModel checkViewTableModel = CheckViewTableModel.assembleModel(data);
        checkViewTable.setModel(checkViewTableModel);

        JScrollPane jScrollPane=new JScrollPane(checkViewTable);
        checkViewTable.renderRule();
        contentpane.add(jScrollPane,BorderLayout.CENTER);
    }
}