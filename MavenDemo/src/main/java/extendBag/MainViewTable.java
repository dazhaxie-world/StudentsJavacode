package extendBag;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Vector;

public class MainViewTable extends JTable {
    public MainViewTable(){
        JTableHeader tableHeader = getTableHeader();
        tableHeader.setFont(new Font(null,Font.BOLD,16));
        tableHeader.setForeground(Color.red);
        setFont(new Font(null,Font.PLAIN,14));
        setForeground(Color.black);
        setGridColor(Color.black);
        setRowHeight(30);
        getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);//进行了方法重写，阻止修改表格元素
    }


    public void renderRule(){
        Vector<String> columns = MainViewTableModel.getColumns();
        MainViewCellRender render = new MainViewCellRender();
        for (int i = 0; i < columns.size(); i++) {
            TableColumn column = getColumn(columns.get(i));
            column.setCellRenderer(render);
            if(i==0){
                column.setPreferredWidth(120);
                column.setMaxWidth(120);
                column.setResizable(false);
            }
        }
    }
}
