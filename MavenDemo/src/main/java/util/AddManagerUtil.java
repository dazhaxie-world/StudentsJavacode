package util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddManagerUtil implements ActionListener {
    private String name;
    private String key;
    Connection connection;

    public AddManagerUtil() {
        connection=SqlUtil.getConnection();
        String str="INSERT INTO manager(username,`key`) VALUES (?,?)";
        PreparedStatement preparedStatement =null;
        try {
            preparedStatement = connection.prepareStatement(str);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (name!=null&&key!=null){
            try {
                preparedStatement.setString(1,name);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                preparedStatement.setString(2,key);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public AddManagerUtil(String name, String key) {
        this.name = name;
        this.key = key;
    }

    /**
     * 获取
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     * @return key
     */
    public String getKey() {
        return key;
    }

    /**
     * 设置
     * @param key
     */
    public void setKey(String key) {
        this.key = key;
    }

    public String toString() {
        return "addManagerUtil{name = " + name + ", key = " + key + "}";
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
