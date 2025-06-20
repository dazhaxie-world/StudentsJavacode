
学生成绩管理系统项目介绍

 

# **一、项目概述**

 

本学生管理系统基于 Java 技术栈开发，融合 GUI 界面、MySQL 数据库、JDBC 操作、线程连接池、输入输出流及 Maven 工程管理，实现学生信息的增删改查、登录验证、数据可视化展示等功能，为校园学生成绩管理提供便捷、高效的桌面应用解决方案。

 

# **二、技术栈与架构**

 

## （一）核心技术

 

1. GUI（Swing）：构建可视化交互界面，包含登录窗口、主数据展示窗口，实现用户操作与系统反馈的直观交互，如登录校验弹窗、学生信息表格展示 。

2. MySQL：作为数据存储仓库，存储学生基础信息（学号、姓名、生日等 ）、成绩数据及用户登录信息，保障数据持久化 。

3. JDBC：搭建 Java 与 MySQL 数据库的连接桥梁，执行 SQL 语句，完成学生信息的查询、插入、更新、删除等操作，如从数据库读取学生数据渲染到表格 。

4. 线程连接池（Druid 连接池）：优化数据库连接资源管理，避免频繁创建/销毁连接的开销，提升系统性能与稳定性，保障高并发场景（虽桌面应用并发低，但体现架构优化 ）下数据库操作效率 。
5. 日志记录：在主机本地化记录日志，有利于操作追溯，记录用户登录、数据修改等行为，追踪敏感操作（如批量删除）。入侵检测，异常日志（如高频错误登录尝试）可作为安全事件告警依据，结合防火墙规则阻断潜在攻击。

6. 输入输出流：可用于实现数据备份（如将学生信息导出为文本文件 ）、日志记录（记录系统操作、异常信息 ）等功能，丰富系统数据处理能力 。

7. Maven：统一项目依赖管理，自动下载、管理 JDBC 驱动、Swing 相关库（若有扩展 ）、连接池组件等，简化项目配置与构建流程。
8. 正则表达式：对用户的输入进行精确校准。
9. Maven：利用Maven引用Junit、druid、package等jar包。

## （二）架构设计

 

采用 MVC（模型 - 视图 - 控制器） 思想组织代码：

 

<u>视图（View）</u>：由 Swing 组件构成，如  LoginView （登录界面 ）、 MainView （主数据展示界面 ），负责呈现界面、收集用户输入 。

<u>模型（Model）</u>：包含数据库操作模型（基于 JDBC、连接池 ，如  CheckViewTableModel、MainViewTableModel、MainViewTable 封装数据查询、表格模型逻辑 ）、实体类（学生信息实体 ），处理数据存储、业务逻辑计算（如总成绩计算 ）。

<u>控制器（Controller）</u>：通过事件监听（如登录按钮点击事件）、方法调用，协调视图与模型交互，如  LoginView 中登录事件触发身份校验逻辑，调用模型层查询数据库验证用户 。

 

 

 

# **三、核心功能模块**

 

## （一）用户登录模块

 

\- 界面： LoginView 提供用户名、密码输入框及“登录”“清除”按钮，简洁直观 。



\- 逻辑：点击“登录”，控制器层调用模型层，通过 JDBC 连接数据库校验用户信息（对比数据库存储的用户名、密码 ），校验失败弹出提示框，成功则跳转主数据界面 。

 		部分代码如下：

```java
import util.SqlUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginView extends JFrame {
    JLabel nameLabel=new JLabel("学生管理系统登录",JLabel.CENTER);
    SpringLayout springLayout=new SpringLayout();
    JPanel centerPanel=new JPanel(springLayout);


    JLabel userName=new JLabel("用户名：");
    JTextField nameInput=new JTextField();
    JLabel userPassword=new JLabel("密码：");
    JTextField passwordInput=new JTextField();
    JButton LoginButton=new JButton("登录");

    JButton resetButton=new JButton("清除");
    SystemTray systemTray;
    LoginHandler loginHandler;
    public LoginView(){
        super("学生管理系统");

        //导入数据库用户登录账号密码
        Connection connection = SqlUtil.getConnection();
        String getUser="SELECT * FROM manager";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getUser);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                LoginHandler.managers.add(new Manager(resultSet.getString("username"),resultSet.getString("key")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        loginHandler=new LoginHandler(this);
        setSize(600,400);//px
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);


        Container contentPane = getContentPane();


        //定义组件
        nameLabel.setFont(new Font("微软雅黑",Font.PLAIN,40));
        nameLabel.setPreferredSize(new Dimension(0,80));
        Font centerFont = new Font("微軟正黑體", Font.PLAIN, 25);
        userName.setFont(centerFont);
        nameInput.setPreferredSize(new Dimension(200,25));
        userPassword.setFont(centerFont);
        passwordInput.setPreferredSize(new Dimension(200,25));
        LoginButton.setFont(centerFont);
        resetButton.setFont(centerFont);
        //添加组件


        centerPanel.add(userName);
        centerPanel.add(nameInput);
        centerPanel.add(userPassword);
        centerPanel.add(passwordInput);
        LoginButton.addActionListener(loginHandler);
        LoginButton.addKeyListener(loginHandler);//空格登录
        resetButton.addActionListener(loginHandler);
        centerPanel.add(LoginButton);
        centerPanel.add(resetButton);
        contentPane.add(nameLabel,BorderLayout.NORTH);
        contentPane.add(centerPanel,BorderLayout.CENTER);


        extracted();//布局

        getRootPane().setDefaultButton(LoginButton);//设置loginButton为根按钮


    }

    public JTextField getNameInput() {
        return nameInput;
    }

    public void setNameInput(JTextField nameInput) {
        this.nameInput = nameInput;
    }

    public JTextField getPasswordInput() {
        return passwordInput;
    }

    public void setPasswordInput(JTextField passwordInput) {
        this.passwordInput = passwordInput;
    }

    private void extracted() {

        //布局
//        Spring childWidth = Spring.sum(Spring.sum(Spring.width(userName), Spring.width(nameInput)),Spring.constant(20));
//        int offsetX = childWidth.getValue()/2;
//        System.out.println(offsetX);

        springLayout.putConstraint(SpringLayout.EAST,userName,-100,SpringLayout.HORIZONTAL_CENTER,centerPanel);
        springLayout.putConstraint(SpringLayout.NORTH,userName,25,SpringLayout.NORTH,centerPanel);
        springLayout.putConstraint(SpringLayout.NORTH,nameInput,0,SpringLayout.NORTH,userName);
        springLayout.putConstraint(SpringLayout.WEST,nameInput,-70,SpringLayout.HORIZONTAL_CENTER,centerPanel);


        springLayout.putConstraint(SpringLayout.EAST,userPassword,0,SpringLayout.EAST,userName);
        springLayout.putConstraint(SpringLayout.NORTH,userPassword,40,SpringLayout.NORTH,userName);
        springLayout.putConstraint(SpringLayout.WEST,passwordInput,0,SpringLayout.WEST,nameInput);
        springLayout.putConstraint(SpringLayout.NORTH,passwordInput,0,SpringLayout.NORTH,userPassword);


        springLayout.putConstraint(SpringLayout.EAST,userPassword,0,SpringLayout.EAST,userName);
        springLayout.putConstraint(SpringLayout.NORTH,userPassword,40,SpringLayout.NORTH,userName);
        springLayout.putConstraint(SpringLayout.WEST,passwordInput,0,SpringLayout.WEST,nameInput);
        springLayout.putConstraint(SpringLayout.NORTH,passwordInput,0,SpringLayout.NORTH,userPassword);


        springLayout.putConstraint(SpringLayout.SOUTH,LoginButton,-100,SpringLayout.SOUTH,centerPanel);
        springLayout.putConstraint(SpringLayout.EAST,LoginButton,275,SpringLayout.WEST,centerPanel);

        springLayout.putConstraint(SpringLayout.SOUTH,resetButton,-100,SpringLayout.SOUTH,centerPanel);
        springLayout.putConstraint(SpringLayout.EAST,resetButton,425,SpringLayout.WEST,centerPanel);
    }

}

```



## （二）学生信息管理模块

 

1. 数据展示： MainView 借助  JTable 展示学生信息，结合自定义  CheckViewTableModel 加载数据库学生数据（学号、姓名、生日、各科成绩等 ）， MainViewCellRender 优化表格渲染（隔行变色、文字居中 ），提升可读性 。


2. 增删改查：可扩展实现（当前示例基础上 ），通过按钮触发事件，调用模型层执行 JDBC 操作：

   ​		部分代码如下

   ```java
   import javax.swing.*;
   import java.awt.event.ActionEvent;
   import java.awt.event.ActionListener;
   
   public class StudentsUpDate implements ActionListener {
       static String receive=null;
       MainView mainView;
       @Override
       public void actionPerformed(ActionEvent e) {
           JButton jButton= (JButton) e.getSource();
           String text = jButton.getText();
           if(text.equals("增加")){
               new ViewUtil("增加","输入   学号、姓名可添加生日或全部添加，信息用空格分隔");
           }
           else if(text.equals("修改")){
               new ViewUtil("修改","输入   学号，可对成绩或生日或全部进行修改");
           }
           else if(text.equals("删除")){
               new ViewUtil("删除","输入   学号");
           }
           else if(text.equals("查询")){
               new CheckStudentView(MainView.searchText.getText());
           }
       }
   
       public StudentsUpDate() {
       }
   
       public StudentsUpDate(MainView mainView) {
           this.mainView = mainView;
       }
   }
   ```

   ```java
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
   ```

   

3. 新增：收集用户输入的学生信息，插入数据库并更新表格展示 。

4. 删除：根据学号等标识删除数据库对应记录，同步刷新表格 。

5. 修改：弹出编辑窗口，更新数据库字段 。

6. 查询：支持按姓名筛选学生信息，调用数据库查询并渲染结果 （支持模糊搜索）。


## （三）数据备份与恢复（输入输出流 ）

 

\- 备份：通过输入输出流，将数据库中学生信息按一定格式写入本地文件，实现数据离线备份 。

```java
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
```




## （四）提供大量数据作为测试

利用构造的GenerateUtil工具生成学上数量自定义，学生学号格式化且唯一。

​		代码如下

```java
import util.SqlUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GenerateUti implements ActionListener {
    private static int TOTAL = 10000;  // 总数据量
    private static Random RANDOM = new Random();

    public static void main(String[] args) throws SQLException {
        generateData();
    }

    public static void generateData() throws SQLException {
        Set<String> idSet = new HashSet<>();  // 保证学号唯一
        String sql="INSERT INTO students(学号,`name`,birthday_year,birthday_month,birthday_day,chinese,math,java,PE) VALUES (?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = SqlUtil.getConnection().prepareStatement(sql);
        for (int i = 0; i < TOTAL; i++) {
                String studentId = generateUniqueId(idSet);  // 生成唯一学号
                String name = generateRandomName();          // 生成随机姓名
                int year=generateBirthday_year();
                int month=generateBirthday_month();
                int day=generateBirthday_day(month);
                int score1 = generateNormalScore(80, 8);      // 生成正态分布成绩
                int score2 = generateNormalScore(80, 8);
                int score3 = generateNormalScore(80, 8);
                int score4 = generateNormalScore(80, 8);
                preparedStatement.setString(1,studentId);
                preparedStatement.setString(2,name);
                preparedStatement.setString(3,year+"");
                preparedStatement.setString(4,month+"");
                preparedStatement.setString(5,day+"");
                preparedStatement.setString(6,score1+"");
                preparedStatement.setString(7,score2+"");
                preparedStatement.setString(8,score3+"");
                preparedStatement.setString(9,score4+"");
                preparedStatement.executeUpdate();
            }
            MainView.pubMainView.reload();
    }


    // 生成唯一学号
    private static String generateUniqueId(Set<String> idSet) {
        String id;
        do {
            id = String.format("%04d%02d%04d", RANDOM.nextInt(10)+2015,RANDOM.nextInt(100),RANDOM.nextInt(10000));
        } while (idSet.contains(id));
        idSet.add(id);
        return id;
    }

    public GenerateUti() {

    }

    // 生成随机姓名（姓氏+性别+名字）
    private static String generateRandomName() {
        String[] surnames = {"赵", "钱", "孙", "李", "周", "吴", "郑",
                "王", "冯", "陈","秦","董","丁","田","齐","柳"};
        String gender = RANDOM.nextBoolean() ? "男" : "女";
        //判断?如果正确:如果错误

        String name = surnames[RANDOM.nextInt(surnames.length)];
        //区分男女生成名字

        String[] maleNames = {"强", "伟", "杰", "涛", "磊", "超", "勇", "田", "宗",
                "浩","子","国","骁","飞","宇","镇","文","尚","佳","凯","振","昊",""};

        String[] femaleNames = {"芳", "丽", "娟", "敏", "静", "秀",
                "娜", "丽", "慧", "婷","秀","华",""};

        name += gender.equals("男")
                ? maleNames[RANDOM.nextInt(maleNames.length)]
                : femaleNames[RANDOM.nextInt(femaleNames.length)];
        //生成两个字

        name += gender.equals("男")
                ? maleNames[RANDOM.nextInt(maleNames.length)]
                : femaleNames[RANDOM.nextInt(femaleNames.length)];
        //生成三个字


        return name;
    }


    // 生成正态分布成绩（均值80，标准差8，范围60-100）
    private static int generateNormalScore(double mean, double stdDev) {
        double score;
        do {
            score = mean + stdDev * RANDOM.nextGaussian();
        } while (score < 60 || score > 100);
        return (int) Math.round(score);
    }
    private static int generateBirthday_year(){
        return RANDOM.nextInt(5)+2002;
    }
    private static int generateBirthday_month(){
        return RANDOM.nextInt(12)+1;
    }
    private static int generateBirthday_day(int month){
        if (month!=2){
            if (month==1||month==3||month==5||month==7||month==8||month==10||month==12){
                return RANDOM.nextInt(31)+1;
            }
            else {
                return RANDOM.nextInt(30)+1;
            }
        }
        else {
            return RANDOM.nextInt(28)+1;
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            generateData();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

}
```



##  （五）提供日志


## （六）数据按页展示（可翻页）


## （七）利用正则表达式对用户输入进行矫正

​	代码展示如下

```java
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RecognizePattenUtil {
    private String regex = "[ ]+";

    public String[] recognize(String s){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
        String[] result;
        result = s.split(regex);
        return result;

    }

}
```



## （八）其他小功能

### （1）登出按钮



### （2）登录、重置按钮，



### （3）进行错误提示



### （4）修改添加错误进行提示、无数据显示NoFound


# **四、项目优势**

 

1. 开发效率：Maven 统一依赖管理，自动解决 jar 包依赖，简化项目搭建与维护；Swing 快速构建桌面应用原型，满足初期需求验证 。

2. 性能优化：线程连接池复用数据库连接，减少资源消耗，提升数据库操作响应速度；合理的 MVC 分层，降低代码耦合，便于功能扩展与 bug 修复 。

3. 功能完备性：覆盖学生信息管理核心流程（登录、数据展示、增删改查 ），输入输出流扩展数据处理场景，适配校园多样化管理需求 。
4. 性能优化 ：通过druid线程池复用数据库连接，减少频繁创建/销毁连接的开销
5. 数据安全 ：使用PreparedStatement预编译SQL语句，防止注入攻击
6. 用户体验 ：图形化界面支持模糊查询、批量导入导出异步线程处理耗时操作（如大数据量查询）

 

# **五、适用场景与扩展方向**

 

## （一）适用场景

 

- 校园教务处小范围学生信息管理，快速实现数据录入、查询与统计 。
- Java 桌面应用开发教学实践，帮助学习者掌握 GUI、数据库操作、工程化构建等技术融合应用 。

 

## （二）扩展方向

 

1. 界面升级：替换为 JavaFX 开发更美观、交互性更强的界面，适配现代桌面应用体验 。

2. 功能增强：集成报表生成（如学生成绩分析报表 ）、用户权限细分（不同角色操作权限控制 ）、与教务系统对接（数据同步 ）等 。

3. 部署优化：结合 Spring Boot 重构为 Web 应用，支持浏览器访问；或打包为可执行程序（如 exe、jar ），方便离线部署使用 。

 

本学生管理系统以 Java 技术栈为基础，整合多技术点，实现校园学生信息高效管理，既具备实践教学价值，也可作为轻量级业务系统雏形持续迭代 。
