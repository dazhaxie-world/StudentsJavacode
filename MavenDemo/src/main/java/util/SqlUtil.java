package util;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class SqlUtil {
//
//    static final String url="jdbc:mysql://localhost:3306/students_system";
//    static final String username="root";
//    static final String password="1234";
//    static final String Driver="com.mysql.cj.jdbc.Driver";
//    static {
//        try {
//            Class.forName(Driver);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
    static Properties properties=new Properties();
    public static Connection getConnection(){
        try {
            properties.load(new FileInputStream("D:\\rubbish\\Java\\OppoJava\\MavenDemo\\src\\druid.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
            return dataSource.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        try {
//            return DriverManager.getConnection(url,username,password);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        return null;
    }
    public static void closeConnection(Connection connection){
        if (connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void closePS(PreparedStatement preparedStatement){
        if (preparedStatement!=null){
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void closeResultSet(ResultSet resultSet){
        if (resultSet!=null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
