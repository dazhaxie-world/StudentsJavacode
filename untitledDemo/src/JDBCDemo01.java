import java.sql.*;

public class JDBCDemo01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/gh2502?useServerPrepStmts=true";
        String username="root";
        String password="1234";
        Connection connection = DriverManager.getConnection(url, username, password);
//        Statement statement = connection.createStatement();
        String sql01="SELECT * FROM students WHERE name=?";
//        ResultSet executeQuery = statement.executeQuery(sql01);
        PreparedStatement preparedStatement = connection.prepareStatement(sql01);
        preparedStatement.setString(1,"张三");
        ResultSet executeQuery = preparedStatement.executeQuery();
        while (executeQuery.next()){
            int id = executeQuery.getInt(1);
            String name = executeQuery.getString(2);
            int age = executeQuery.getInt(3);
            System.out.println("id:"+id+",姓名："+name+"，年龄："+age);
        }
        String sql02="UPDATE students SET age=age+1";
//        int i = statement.executeUpdate(sql02);
//        System.out.println(i);
//        connection.close();
//        statement.close();
    }
}
