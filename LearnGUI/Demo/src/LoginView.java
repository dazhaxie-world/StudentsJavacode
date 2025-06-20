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
                LoginHandler.setUsername(resultSet.getString("username"));
                LoginHandler.setPassword(resultSet.getString("key"));
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
        nameLabel.setFont(new Font("行楷",Font.PLAIN,40));
        nameLabel.setPreferredSize(new Dimension(0,80));
        Font centerFont = new Font("楷体", Font.PLAIN, 25);
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

        //bug代码
//        SpringLayout.Constraints userNameConstraints = springLayout.getConstraints(userName);
//        userNameConstraints.setX(Spring.constant(80));
//        userNameConstraints.setY(Spring.constant(90));
            //系统托盘功能
//        if(SystemTray.isSupported()){
//            systemTray=SystemTray.getSystemTray();
//            URL dogImg = LoginView.class.getClassLoader().getResource("dog.jpeg");
//            TrayIcon trayIcon = new TrayIcon(new ImageIcon(dogImg).getImage());
//            trayIcon.setImageAutoSize(true);
//            try {
//                systemTray.add(trayIcon);
//            } catch (AWTException e) {
//                e.printStackTrace();
//            }
//
//            //增加最小化时销毁资源
//            this.addWindowListener(new WindowAdapter() {
//                @Override
//                public void windowIconified(WindowEvent e) {
//                    LoginView.this.dispose();
//                }
//            });
//
//            //托盘事件监听
//            trayIcon.addMouseListener(new MouseAdapter() {
//                @Override
//                public void mouseClicked(MouseEvent e) {
//                    int clickCount = e.getClickCount();
//                    if(clickCount==1){
//                        LoginView.this.setExtendedState(JFrame.NORMAL);
//                    }
//                    LoginView.this.setVisible(true);
//                }
//            });
//        }



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

    public static void main(String[] args) {
        new LoginView();
    }
}
