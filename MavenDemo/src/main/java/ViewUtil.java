import util.RecognizePattenUtil;
import util.SqlUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class ViewUtil extends JFrame{
    String method;
    JTextField textField;
    RecognizePattenUtil recognizePattenUtil=new RecognizePattenUtil();
    boolean re=true;
    public ViewUtil(String str,String patten){
        super(str);
        method=str;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 130);
        setLayout(new BorderLayout(10, 10));
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JLabel label = new JLabel(patten);
        textField = new JTextField(20);
        inputPanel.add(label);
        inputPanel.add(textField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        JButton confirmButton = new JButton("确定");
        JButton cancelButton = new JButton("取消");

        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String inputText = textField.getText();
                try {
                    f(inputText);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                if (re){
                    System.out.println(textField.getText());
                    MainView.pubMainView.dispose();
                    new MainView();
                    dispose(); // 关闭窗口
                }
                re=true;
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // 关闭窗口
            }
        });

        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);

        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }
    public void f(String s) throws SQLException {
        String[] recognize = recognizePattenUtil.recognize(s);
        if(method.equals("增加")){
            if(recognize.length==2){
                String sql="INSERT INTO students(学号,`name`) VALUES (?,?)";
                PreparedStatement preparedStatement = SqlUtil.getConnection().prepareStatement(sql);
                preparedStatement.setString(1,recognize[0]);
                preparedStatement.setString(2,recognize[1]);
                preparedStatement.executeUpdate();
                textField.setText(null);
            }
            else if(recognize.length==5){
                String sql="INSERT INTO students(学号,`name`,birthday_year,birthday_month,birthday_day) VALUES (?,?,?,?,?)";
                PreparedStatement preparedStatement = SqlUtil.getConnection().prepareStatement(sql);
                preparedStatement.setString(1,recognize[0]);
                preparedStatement.setString(2,recognize[1]);
                preparedStatement.setString(3,recognize[2]);
                preparedStatement.setString(4,recognize[3]);
                preparedStatement.setString(5,recognize[4]);
                preparedStatement.executeUpdate();
                textField.setText(null);
            }
            else if (recognize.length==9){
                String sql="INSERT INTO students(学号,`name`,birthday_year,birthday_month,birthday_day,chinese,math,java,PE) VALUES (?,?,?,?,?,?,?,?,?)";
                PreparedStatement preparedStatement = SqlUtil.getConnection().prepareStatement(sql);
                preparedStatement.setString(1,recognize[0]);
                preparedStatement.setString(2,recognize[1]);
                preparedStatement.setString(3,recognize[2]);
                preparedStatement.setString(4,recognize[3]);
                preparedStatement.setString(5,recognize[4]);
                preparedStatement.setString(6,recognize[5]);
                preparedStatement.setString(7,recognize[6]);
                preparedStatement.setString(8,recognize[7]);
                preparedStatement.setString(9,recognize[8]);
                preparedStatement.executeUpdate();
                textField.setText(null);
            }
            else {
                textField.setText("添加个数只能二、五、九列顺序添加");
                re=false;
            }


        }
        else if(method.equals("修改")){
            if (recognize.length==5){
                String sql="update students set chinese=?,math=?,java=?,PE=? where 学号=?";
                PreparedStatement preparedStatement = SqlUtil.getConnection().prepareStatement(sql);
                preparedStatement.setString(1,recognize[1]);
                preparedStatement.setString(2,recognize[2]);
                preparedStatement.setString(3,recognize[3]);
                preparedStatement.setString(4,recognize[4]);
                preparedStatement.setString(5,recognize[0]);
                preparedStatement.executeUpdate();
                textField.setText(null);
            }
            else if (recognize.length==4){
                String sql="update students set birthday_year=?,birthday_month=?,birthday_day=? where 学号=?";
                PreparedStatement preparedStatement = SqlUtil.getConnection().prepareStatement(sql);
                preparedStatement.setString(1,recognize[1]);
                preparedStatement.setString(2,recognize[2]);
                preparedStatement.setString(3,recognize[3]);
                preparedStatement.setString(4,recognize[0]);
                preparedStatement.executeUpdate();
                textField.setText(null);
            }
            else if(recognize.length==9){
                String sql="update students set name=?,birthday_year=?,birthday_month=?,birthday_day=?,chinese=?,math=?,java=?,PE=? where 学号=?";
                PreparedStatement preparedStatement = SqlUtil.getConnection().prepareStatement(sql);
                preparedStatement.setString(1,recognize[1]);
                preparedStatement.setString(2,recognize[2]);
                preparedStatement.setString(3,recognize[3]);
                preparedStatement.setString(4,recognize[4]);
                preparedStatement.setString(5,recognize[5]);
                preparedStatement.setString(6,recognize[6]);
                preparedStatement.setString(7,recognize[7]);
                preparedStatement.setString(8,recognize[8]);
                preparedStatement.setString(9,recognize[0]);
                preparedStatement.executeUpdate();
                textField.setText(null);
            }
            else{
                textField.setText("仅支持利用学号，对于生日、四科成绩、全部除学号外的修改");
                re=false;
            }
        }
        else if(method.equals("删除")){
            String sql="DELETE FROM students where 学号=?";
            PreparedStatement preparedStatement = SqlUtil.getConnection().prepareStatement(sql);
            preparedStatement.setString(1,s);
            preparedStatement.executeUpdate();
            textField.setText(null);
        }
    }

}
