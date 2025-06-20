package util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrintUtil {
    public static void main(String[] args) throws IOException, SQLException {
        FileWriter fileWriter = new FileWriter("学生成绩报表");
        BufferedWriter writer = new BufferedWriter(fileWriter);
        Connection connection = SqlUtil.getConnection();
        PreparedStatement preparedColumnsStatement = connection.prepareStatement("SHOW COLUMNS FROM students");
        ResultSet resultColumnsSet = preparedColumnsStatement.executeQuery();
        while (resultColumnsSet.next()){
            writer.write(String.format("%-16s",resultColumnsSet.getString(1)));
        }
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM students");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            writer.newLine();
//            writer.write(resultSet.getString(1));
//            writer.write(resultSet.getString(2));
//            writer.write(resultSet.getString(3));
//            writer.write(resultSet.getString(4));
//            writer.write(resultSet.getString(5));
//            writer.write(resultSet.getString(6));
//            writer.write(resultSet.getString(7));
//            writer.write(resultSet.getString(8));
//            writer.write(resultSet.getString(9));
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
        }
        writer.close();
        fileWriter.close();
    }
}
