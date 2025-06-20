package util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrintUtil implements ActionListener {

    FileWriter fileWriter =null;
    BufferedWriter writer = null;
//    public static void main(String[] args) throws IOException, SQLException {
//        FileWriter fileWriter = new FileWriter("学生成绩报表");
//        BufferedWriter writer = new BufferedWriter(fileWriter);
//    }



    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            fileWriter=new FileWriter("学生成绩报表");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        writer=new BufferedWriter(fileWriter);



        Connection connection = SqlUtil.getConnection();
        PreparedStatement preparedColumnsStatement = null;
        try {
            preparedColumnsStatement = connection.prepareStatement("SHOW COLUMNS FROM students");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        ResultSet resultColumnsSet = null;
        try {
            resultColumnsSet = preparedColumnsStatement.executeQuery();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }



        while (true){
            try {
                if (!resultColumnsSet.next()) break;
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            try {
                writer.write(String.format("%-16s",resultColumnsSet.getString(1)));
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM students");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        ResultSet resultSet = null;
        try {
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }



        while (true){
            try {
                if (!resultSet.next()) break;
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            try {
                writer.newLine();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
//            writer.write(resultSet.getString(1));

//            writer.write(resultSet.getString(2));

//            writer.write(resultSet.getString(3));

//            writer.write(resultSet.getString(4));

//            writer.write(resultSet.getString(5));

//            writer.write(resultSet.getString(6));

//            writer.write(resultSet.getString(7));

//            writer.write(resultSet.getString(8));

//            writer.write(resultSet.getString(9));

            try {
                writer.write(String.format("%-16s%-16s%-16s%-16s%-16s%-16s%-16s%-16s%-16s",
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getString(9)));
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        //按照格式导入文件




        try {
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            fileWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
