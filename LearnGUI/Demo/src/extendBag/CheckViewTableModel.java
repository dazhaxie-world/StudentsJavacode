package extendBag;

import util.SqlUtil;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class CheckViewTableModel extends DefaultTableModel {
    static Vector<String> columns=new Vector<>();
    static {
        Connection connection = SqlUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SHOW COLUMNS FROM students");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                columns.addElement(resultSet.getString(1));
            }
            columns.addElement("all_score");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    private CheckViewTableModel(){
        super(null,columns);
    }

    private static CheckViewTableModel checkViewModel=new CheckViewTableModel();

    public static CheckViewTableModel assembleModel(Vector<Vector<Object>> data){
        checkViewModel.setDataVector(data,columns);
        return checkViewModel;
    }

    public static Vector<String> getColumns() {
        return columns;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}

