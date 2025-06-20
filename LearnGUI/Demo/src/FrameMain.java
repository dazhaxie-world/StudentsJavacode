import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.Vector;

public class FrameMain extends JFrame {
    SpringLayout springLayout=new SpringLayout();
    JPanel jPanel=new JPanel(springLayout);
    JLabel tittleLabel=new JLabel("文章标题:");
    JTextField titleText=new JTextField();
    JLabel author=new JLabel("作者:");
    JTextField authorText=new JTextField();
    JLabel contentLabel=new JLabel("内容");
    JTextArea contentText=new JTextArea();

    public FrameMain(){
        super("这是标题");

        setSize(600,400);//px
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();//得到屏幕大小


        Vector<Vector<Object>> data=new Vector<>();
        Vector<Object> rowVector1=new Vector<>();
        rowVector1.addElement("1");
        rowVector1.addElement("张三");
        rowVector1.addElement("甘肃");
        rowVector1.addElement("no1");
        rowVector1.addElement("1");
        rowVector1.addElement("2");
        rowVector1.addElement("3");
        rowVector1.addElement("6");

        Vector<Object> rowVector2=new Vector<>();
        rowVector2.addElement("2");
        rowVector2.addElement("李四");
        rowVector2.addElement("青海");
        rowVector2.addElement("no2");
        rowVector2.addElement("3");
        rowVector2.addElement("2");
        rowVector2.addElement("3");
        rowVector2.addElement("8");

        Vector<Object> rowVector3=new Vector<>();
        rowVector3.addElement("1");
        rowVector3.addElement("王五");
        rowVector3.addElement("杭州");
        rowVector3.addElement("no1");
        rowVector3.addElement("2");
        rowVector3.addElement("2");
        rowVector3.addElement("3");
        rowVector3.addElement("7");

        data.addElement(rowVector1);
        data.addElement(rowVector3);
        data.addElement(rowVector3);
        StudentTableModel studentTableModel = StudentTableModel.assembleModel(data);
        JTable jTable=new JTable(studentTableModel);
        JTableHeader tableHeader = jTable.getTableHeader();
        tableHeader.setFont(new Font(null,Font.BOLD,16));
        tableHeader.setForeground(Color.red);
        jTable.setFont(new Font(null,Font.PLAIN,14));
        jTable.setForeground(Color.black);
        jTable.setGridColor(Color.black);
        jTable.setRowHeight(30);
        jTable.getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);//进行了方法重写，阻止修改表格元素

        Vector<String> columns = StudentTableModel.getColumns();
        StudentCellRender render = new StudentCellRender();
        for (int i = 0; i < columns.size(); i++) {
            TableColumn column = jTable.getColumn(columns.get(i));
            column.setCellRenderer(render);
            if(i==0){
                column.setPreferredWidth(50);
                column.setMaxWidth(50);
                column.setResizable(false);
            }
        }

        Container pane = getContentPane();
        JScrollPane jScrollPane = new JScrollPane(jTable);


        pane.add(jScrollPane);
        setVisible(true);
//        jPanel.add(tittleLabel);
//        jPanel.add(titleText);
//        jPanel.add(author);
//        jPanel.add(authorText);
//        jPanel.add(contentLabel);
//        jPanel.add(contentText);
//        jPanel.setBackground(Color.red);
//        SpringLayout.Constraints tittleTableLimit = springLayout.getConstraints(tittleLabel);
//        Spring width1 = Spring.width(tittleLabel);
//        Spring width2 = Spring.width(titleText);
//        Spring spaceWidth = Spring.constant(70);
//        Spring childWidth = Spring.sum(Spring.sum(width1, width2), spaceWidth);
//        int value = childWidth.getValue();
////        tittleTableLimit.setX(Spring.constant(100));
//        springLayout.putConstraint(SpringLayout.WEST,tittleLabel,-value,SpringLayout.HORIZONTAL_CENTER,jPanel);
//        tittleTableLimit.setY(Spring.constant(50));
//        titleText.setPreferredSize(new Dimension(200,20));
//        Spring tittleEast = tittleTableLimit.getConstraint(SpringLayout.EAST);
//        authorText.setPreferredSize(new Dimension(200,20));
//        contentText.setPreferredSize(new Dimension(200,150));
//        SpringLayout.Constraints tittleTextLimit = springLayout.getConstraints(titleText);
//        tittleTextLimit.setConstraint(SpringLayout.WEST,Spring.sum(tittleEast,Spring.constant(20)));
//        tittleTextLimit.setConstraint(SpringLayout.NORTH,tittleTableLimit.getConstraint(SpringLayout.NORTH));
//        springLayout.putConstraint(SpringLayout.EAST,author,0,SpringLayout.EAST,tittleLabel);
//        springLayout.putConstraint(SpringLayout.NORTH,author,20,SpringLayout.SOUTH,tittleLabel);
//        springLayout.putConstraint(SpringLayout.WEST,authorText,0,SpringLayout.WEST,titleText);
//        springLayout.putConstraint(SpringLayout.NORTH,authorText,0,SpringLayout.NORTH,author);
//
//        springLayout.putConstraint(SpringLayout.EAST,contentLabel,0,SpringLayout.EAST,author);
//        springLayout.putConstraint(SpringLayout.NORTH,contentLabel,20,SpringLayout.SOUTH,author);
//        springLayout.putConstraint(SpringLayout.WEST,contentText,0,SpringLayout.WEST,authorText);
//        springLayout.putConstraint(SpringLayout.NORTH,contentText,0,SpringLayout.NORTH,contentLabel);

    }
        public static void main(String[] args) {
            new FrameMain();
        }
}
class StudentCellRender extends DefaultTableCellRenderer {
    //在每一行每一列显示之前调用
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if(row%2==0){
            setBackground(Color.LIGHT_GRAY);
        }else {
            setBackground(Color.WHITE);
        }
        setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}
class StudentTableModel extends DefaultTableModel{
    static Vector<String> columns=new Vector<>();
    static {
        columns.addElement("编号");
        columns.addElement("姓名");
        columns.addElement("家乡");
        columns.addElement("学号");
        columns.addElement("语文");
        columns.addElement("数学");
        columns.addElement("英语");
        columns.addElement("总分");
    }
    private StudentTableModel(){
        super(null,columns);
    }

    private static StudentTableModel studentTableModel=new StudentTableModel();

    public static StudentTableModel assembleModel(Vector<Vector<Object>> data){
        studentTableModel.setDataVector(data,columns);
        return studentTableModel;
    }

    public static Vector<String> getColumns() {
        return columns;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}