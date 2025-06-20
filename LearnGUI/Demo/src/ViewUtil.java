import util.SqlUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewUtil extends JFrame{
    String method;
    public ViewUtil(String str,String patten){
        super(str);
        method=str;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 130);
        setLayout(new BorderLayout(10, 10));
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JLabel label = new JLabel(patten);
        JTextField textField = new JTextField(20);
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
                MainView.pubMainView.dispose();
                new MainView();
                dispose(); // 关闭窗口
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
        if(method.equals("增加")){
            String[] strings = s.split("-");
            String sql="INSERT INTO students(`name`,learn_id,chinese,math,english) VALUES (?,?,?,?,?)";
            PreparedStatement preparedStatement = SqlUtil.getConnection().prepareStatement(sql);
            preparedStatement.setString(1,strings[0]);
            preparedStatement.setString(2,strings[1]);
            preparedStatement.setString(3,strings[2]);
            preparedStatement.setString(4,strings[3]);
            preparedStatement.setString(5,strings[4]);
            preparedStatement.executeUpdate();
        }
        else if(method.equals("修改")){
            String[] strings = s.split("-");
            String sql="update students set name=?,learn_id=?,chinese=?,math=?,english=? where learn_id=?";
            PreparedStatement preparedStatement = SqlUtil.getConnection().prepareStatement(sql);
            preparedStatement.setString(1,strings[3]);
            preparedStatement.setString(2,strings[4]);
            preparedStatement.setString(3,strings[5]);
            preparedStatement.setString(4,strings[6]);
            preparedStatement.setString(5,strings[7]);
            preparedStatement.setString(6,strings[0]);
            preparedStatement.executeUpdate();
        }
        else if(method.equals("删除")){
            String sql="DELETE FROM students where learn_id=?";
            PreparedStatement preparedStatement = SqlUtil.getConnection().prepareStatement(sql);
            preparedStatement.setString(1,s);
            preparedStatement.executeUpdate();
        }
    }

}
